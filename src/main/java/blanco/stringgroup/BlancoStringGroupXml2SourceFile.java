/*
 * blanco Framework
 * Copyright (C) 2004-2006 IGA Tosiki
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.stringgroup;

import java.io.File;
import java.io.IOException;
import java.util.List;

import blanco.cg.BlancoCgObjectFactory;
import blanco.cg.BlancoCgSupportedLang;
import blanco.cg.transformer.BlancoCgTransformerFactory;
import blanco.cg.util.BlancoCgLineUtil;
import blanco.cg.util.BlancoCgSourceUtil;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoStringUtil;
import blanco.stringgroup.message.BlancoStringGroupMessage;
import blanco.stringgroup.resourcebundle.BlancoStringGroupResourceBundle;
import blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure;
import blanco.stringgroup.valueobject.BlancoStringGroupStructure;

/**
 * Generates class source code to process string group from "String Group Definition" Excel format.
 * 
 * This class is responsible for generation of source code from intermediate XML files.
 * 
 * @author IGA Tosiki
 */
public class BlancoStringGroupXml2SourceFile {
    /**
     * Message definition.
     */
    private final BlancoStringGroupMessage fMsg = new BlancoStringGroupMessage();

    /**
     * An access object to the resource bundle for this product.
     */
    private final BlancoStringGroupResourceBundle fBundle = new BlancoStringGroupResourceBundle();

    /**
     * Target programming language.
     */
    private int fTargetLang = BlancoCgSupportedLang.NOT_DEFINED;

    /**
     * A factory for blancoCg to be used internally.
     */
    private BlancoCgObjectFactory fCgFactory = null;

    /**
     * Source file information for blancoCg to be used internally.
     */
    private BlancoCgSourceFile fCgSourceFile = null;

    /**
     * Class information for blancoCg to be used internally.
     */
    private BlancoCgClass fCgClass = null;

    /**
     * Character encoding of auto-generated source files.
     */
    private String fEncoding = null;

    public void setEncoding(final String argEncoding) {
        fEncoding = argEncoding;
    }

    /**
     * Auto-generates source code from intermediate XML files.
     * 
     * @param argMetaXmlSourceFile
     *            An XML file that contains meta-information.
     * @param argTargetLang
     *            Target programming language.
     * @param argDirectoryTarget
     *            Output directory of the generated source code (specify the part excluding /main).
     * @throws IOException
     *             If an I/O exception occurs.
     */
    public void process(final File argMetaXmlSourceFile,
            final String argTargetLang, final File argDirectoryTarget)
            throws IOException {

        fTargetLang = new BlancoCgSupportedLang().convertToInt(argTargetLang);
        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
        case BlancoCgSupportedLang.CS:
        case BlancoCgSupportedLang.JS:
        case BlancoCgSupportedLang.VB:
        case BlancoCgSupportedLang.PHP:
        case BlancoCgSupportedLang.RUBY:
        case BlancoCgSupportedLang.PYTHON:
        case BlancoCgSupportedLang.DELPHI:
            break;
        default:
            throw new IllegalArgumentException(fMsg.getMbsgi002(argTargetLang));
        }

        final BlancoStringGroupStructure[] structures = new BlancoStringGroupXmlParser()
                .parse(argMetaXmlSourceFile);

        for (int index = 0; index < structures.length; index++) {
            // Auto-generates source code based on the analysis results of meta information.
            structure2Source(structures[index], argDirectoryTarget);
        }
    }

    /**
     * Auto-generates source code based on the collected information.
     * 
     * @param argStructure
     *            Process structure data collected from metafiles.
     * @param argDirectoryTarget
     *            Output directory of the generated source code.
     */
    public void structure2Source(final BlancoStringGroupStructure argStructure,
            final File argDirectoryTarget) {
        // To make it compatible with the previous version, outputs to the /main subfolder.
        final File fileBlancoMain = new File(argDirectoryTarget
                .getAbsolutePath()
                + "/main");

        fCgFactory = BlancoCgObjectFactory.getInstance();
        fCgSourceFile = fCgFactory.createSourceFile(argStructure.getPackage(),
                null);
        fCgSourceFile.setName(argStructure.getName()
                + BlancoStringUtil.null2Blank(argStructure.getSuffix()));
        fCgSourceFile.setEncoding(fEncoding);
        switch (fTargetLang) {
        case BlancoCgSupportedLang.DELPHI:
            // In Delph, it forces the class name to have "T" in order to avoid name collision with the Unit name, as is customary.
            fCgClass = fCgFactory.createClass("T" + argStructure.getName()
                    + BlancoStringUtil.null2Blank(argStructure.getSuffix()),
                    BlancoStringUtil.null2Blank(argStructure.getDescription()));
            break;
        default:
            fCgClass = fCgFactory.createClass(argStructure.getName()
                    + BlancoStringUtil.null2Blank(argStructure.getSuffix()),
                    BlancoStringUtil.null2Blank(argStructure.getDescription()));
            break;
        }
        fCgSourceFile.getClassList().add(fCgClass);

        expandField(argStructure);
        expandMethodMatch(argStructure);
        expandMethodMatchIgnoreCase(argStructure);
        expandMethodConvertToInt(argStructure);

        // TODO: Currently, only Java is supported.
        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
            expandMethodConvertToString(argStructure);
            break;
        }

        BlancoCgTransformerFactory.getSourceTransformer(fTargetLang).transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Expands the constant field.
     * 
     * @param argProcessStructure
     *            Process structure data collected from metafiles.
     */
    private void expandField(
            final BlancoStringGroupStructure argProcessStructure) {

        boolean isProcessed = false;
        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);
            if (BlancoStringUtil.null2Blank(fieldLook.getConstant()).length() == 0) {
                continue;
            }

            String description = "";
            // From here, the processing for each character is described.
            if (fieldLook.getNo() != null) {
                description += fBundle.getXml2sourceFileFieldNo(fieldLook
                        .getNo()
                        + " ");
            }
            if (fieldLook.getDescription() != null) {
                description += fBundle.getXml2sourceFileDescription(fieldLook
                        .getDescription());
            }

            final BlancoCgField cgField = fCgFactory.createField(fieldLook
                    .getConstant().toUpperCase(), getTypeInt(), description);
            fCgClass.getFieldList().add(cgField);

            cgField.setAccess("public");
            cgField.setStatic(true);
            cgField.setFinal(true);
            cgField.setDefault(Integer.toString(indexField + 1));
            isProcessed = true;
        }

        if (isProcessed) {
            final BlancoCgField cgField = fCgFactory.createField("NOT_DEFINED",
                    getTypeInt(), "Undefined. A string or constant other than a string group that is undefined.");
            fCgClass.getFieldList().add(cgField);

            cgField.setAccess("public");
            cgField.setStatic(true);
            cgField.setFinal(true);
            cgField.setDefault("-1");
        }
    }

    /**
     * Expands "match" method.
     * 
     * @param argProcessStructure
     *            Process structure data collected from metafiles.
     */
    private void expandMethodMatch(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("match"), "Determines if a string is part of a string group.");
        fCgClass.getMethodList().add(cgMethod);
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeString(),
                        "A string to be checked."));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeBoolean(),
                "true is the string is part of a string group, false otherwise."));

        final List<java.lang.String> lineList = cgMethod.getLineList();

        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);

            // From here, the processing for each character is described.
            if (fieldLook.getNo() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileFieldNo(fieldLook.getNo()));
            }
            if (fieldLook.getDescription() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileDescription(fieldLook
                                .getDescription()));
            }

            switch (fTargetLang) {
            case BlancoCgSupportedLang.JAVA:
            case BlancoCgSupportedLang.CS:
                lineList
                        .add(BlancoCgLineUtil
                                .getIfBegin(
                                        fTargetLang,
                                        BlancoCgLineUtil
                                                .getStringLiteralEnclosure(fTargetLang)
                                                + BlancoCgSourceUtil
                                                        .escapeStringAsSource(
                                                                fTargetLang,
                                                                fieldLook
                                                                        .getValue())
                                                + BlancoCgLineUtil
                                                        .getStringLiteralEnclosure(fTargetLang)
                                                + "."
                                                + getMethodName("equals")
                                                + "(argCheck)"));
                break;
            case BlancoCgSupportedLang.JS:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " == argCheck"));
                break;
            case BlancoCgSupportedLang.VB:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " = argCheck"));
                break;
            case BlancoCgSupportedLang.PHP:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " === $argCheck"));
                break;
            case BlancoCgSupportedLang.RUBY:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " == argCheck"));
                break;
            case BlancoCgSupportedLang.PYTHON:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " == argCheck"));
                break;
            case BlancoCgSupportedLang.DELPHI:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " = argCheck"));
                break;
            }

            switch (fTargetLang) {
            case BlancoCgSupportedLang.PYTHON:
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "True")
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.DELPHI:
                lineList.add("result := True"
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                lineList.add("exit"
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            default:
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "true")
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            }
            lineList.add(BlancoCgLineUtil.getIfEnd(fTargetLang));
        }
        switch (fTargetLang) {
        case BlancoCgSupportedLang.PYTHON:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "False")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        case BlancoCgSupportedLang.DELPHI:
            lineList.add("result := False"
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            lineList.add("exit" + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        default:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "false")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        }
    }

    /**
     * Expands "matchIgnoreCase" method.
     * 
     * @param argProcessStructure
     *            Process structure data collected from metafiles.
     */
    private void expandMethodMatchIgnoreCase(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("matchIgnoreCase"),
                "Determines if a string is part of a string group in a case-insentive manner.");
        fCgClass.getMethodList().add(cgMethod);
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeString(),
                        "A string to be checked."));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeBoolean(),
                "true is the string is part of a string group, false otherwise."));

        final List<java.lang.String> lineList = cgMethod.getLineList();

        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);

            // From here, the processing for each character is described.
            if (fieldLook.getNo() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileFieldNo(fieldLook.getNo()));
            }
            if (fieldLook.getDescription() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileDescription(fieldLook
                                .getDescription()));
            }

            switch (fTargetLang) {
            case BlancoCgSupportedLang.JAVA:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + "." + getMethodName("equalsIgnoreCase")
                                + "(argCheck)"));
                break;
            case BlancoCgSupportedLang.CS:
                lineList
                        .add(BlancoCgLineUtil
                                .getIfBegin(
                                        fTargetLang,
                                        BlancoCgLineUtil
                                                .getStringLiteralEnclosure(fTargetLang)
                                                + BlancoCgSourceUtil
                                                        .escapeStringAsSource(
                                                                fTargetLang,
                                                                fieldLook
                                                                        .getValue())
                                                + BlancoCgLineUtil
                                                        .getStringLiteralEnclosure(fTargetLang)
                                                + "."
                                                + getMethodName("equals")
                                                + "(argCheck, StringComparison.CurrentCultureIgnoreCase)"));
                fCgSourceFile.getImportList().add("System.StringComparison");
                break;
            case BlancoCgSupportedLang.JS:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + ".toUpperCase() == argCheck.toUpperCase()"));
                break;
            case BlancoCgSupportedLang.VB:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + ".ToUpper() = argCheck.ToUpper()"));
                break;
            case BlancoCgSupportedLang.PHP:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        "strtoupper("
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + ") === strtoupper($argCheck)"));
                break;
            case BlancoCgSupportedLang.RUBY:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + ".upcase() == argCheck.upcase()"));
                break;
            case BlancoCgSupportedLang.PYTHON:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + ".upper() == argCheck.upper()"));
                break;
            case BlancoCgSupportedLang.DELPHI:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        "UpperCase("
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + ") = UpperCase(argCheck)"));
                fCgSourceFile.getImportList().add("SysUtils");
                break;
            }
            switch (fTargetLang) {
            case BlancoCgSupportedLang.PYTHON:
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "True")
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.DELPHI:
                lineList.add("result := True"
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                lineList.add("exit"
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            default:
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "true")
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            }
            lineList.add(BlancoCgLineUtil.getIfEnd(fTargetLang));
        }
        switch (fTargetLang) {
        case BlancoCgSupportedLang.PYTHON:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "False")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        case BlancoCgSupportedLang.DELPHI:
            lineList.add("result := False"
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            lineList.add("exit" + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        default:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "false")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        }
    }

    /**
     * Expands "convertToInt" method.
     * 
     * @param argProcessStructure
     *            Process structure data collected from metafiles.
     */
    private void expandMethodConvertToInt(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("convertToInt"), "Converts a string to a constant.");
        fCgClass.getMethodList().add(cgMethod);

        cgMethod.getLangDoc().getDescriptionList().add(
                "Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.");
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeString(),
                        "A string to be converted."));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeInt(), "The value after conversion to a constant."));

        final List<java.lang.String> lineList = cgMethod.getLineList();

        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);

            if (BlancoStringUtil.null2Blank(fieldLook.getConstant()).length() == 0) {
                // Skips those constants that are undefined.
                continue;
            }

            // From here, the processing for each character is described.
            if (fieldLook.getNo() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileFieldNo(fieldLook.getNo()));
            }
            if (fieldLook.getDescription() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileDescription(fieldLook
                                .getDescription()));
            }

            switch (fTargetLang) {
            case BlancoCgSupportedLang.JAVA:
            case BlancoCgSupportedLang.CS:
                lineList
                        .add(BlancoCgLineUtil
                                .getIfBegin(
                                        fTargetLang,
                                        BlancoCgLineUtil
                                                .getStringLiteralEnclosure(fTargetLang)
                                                + BlancoCgSourceUtil
                                                        .escapeStringAsSource(
                                                                fTargetLang,
                                                                fieldLook
                                                                        .getValue())
                                                + BlancoCgLineUtil
                                                        .getStringLiteralEnclosure(fTargetLang)
                                                + "."
                                                + getMethodName("equals")
                                                + "(argCheck)"));
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, fieldLook
                        .getConstant().toUpperCase())
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.JS:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " == argCheck"));
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, fCgClass
                        .getName()
                        + "." + fieldLook.getConstant().toUpperCase())
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.VB:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " = argCheck"));
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, fieldLook
                        .getConstant().toUpperCase())
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.PHP:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " === $argCheck"));
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "self::"
                        + fieldLook.getConstant().toUpperCase())
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.RUBY:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " == argCheck"));
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, fieldLook
                        .getConstant().toUpperCase())
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.PYTHON:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " == argCheck"));
                lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "self."
                        + fieldLook.getConstant().toUpperCase())
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            case BlancoCgSupportedLang.DELPHI:
                lineList.add(BlancoCgLineUtil.getIfBegin(fTargetLang,
                        BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                                + BlancoCgSourceUtil.escapeStringAsSource(
                                        fTargetLang, fieldLook.getValue())
                                + BlancoCgLineUtil
                                        .getStringLiteralEnclosure(fTargetLang)
                                + " = argCheck"));
                lineList.add("result := "
                        + fieldLook.getConstant().toUpperCase()
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                lineList.add("exit"
                        + BlancoCgLineUtil.getTerminator(fTargetLang));
                break;
            }
            lineList.add(BlancoCgLineUtil.getIfEnd(fTargetLang));
        }

        lineList.add("");
        lineList.add(BlancoCgLineUtil.getSingleLineCommentPrefix(fTargetLang)
                + "No matching constants were found.");

        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
        case BlancoCgSupportedLang.CS:
        case BlancoCgSupportedLang.VB:
        case BlancoCgSupportedLang.RUBY:
        default:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, "NOT_DEFINED")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        case BlancoCgSupportedLang.JS:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang, fCgClass
                    .getName()
                    + ".NOT_DEFINED")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        case BlancoCgSupportedLang.PHP:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang,
                    "self::NOT_DEFINED")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        case BlancoCgSupportedLang.PYTHON:
            lineList.add(BlancoCgLineUtil.getReturn(fTargetLang,
                    "self.NOT_DEFINED")
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        case BlancoCgSupportedLang.DELPHI:
            lineList.add("result := NOT_DEFINED"
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            lineList.add("exit" + BlancoCgLineUtil.getTerminator(fTargetLang));
            break;
        }
    }

    /**
     * Expands "convertToString" method.
     * 
     * TODO: Only Java is supported. Other languages are not supported.
     * 
     * @param argProcessStructure
     *            Process structure data collected from metafiles.
     */
    private void expandMethodConvertToString(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("convertToString"), "Converts a constant to a string.");
        fCgClass.getMethodList().add(cgMethod);

        cgMethod.getLangDoc().getDescriptionList().add("Converts to a string corresponding to a constant.");
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeInt(),
                        "A constant to be converted."));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeString(),
                "The value after conversion to a string, or a zero-length string if NOT_DEFINED."));

        final List<java.lang.String> lineList = cgMethod.getLineList();

        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);

            if (BlancoStringUtil.null2Blank(fieldLook.getConstant()).length() == 0) {
                continue;
            }

            if (fieldLook.getNo() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileFieldNo(fieldLook.getNo()));
            }
            if (fieldLook.getDescription() != null) {
                lineList.add(BlancoCgLineUtil
                        .getSingleLineCommentPrefix(fTargetLang)
                        + fBundle.getXml2sourceFileDescription(fieldLook
                                .getDescription()));
            }

            lineList.add("if (argCheck == "
                    + fieldLook.getConstant().toUpperCase() + ") {");
            lineList.add("return "
                    + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                    + BlancoCgSourceUtil.escapeStringAsSource(fTargetLang,
                            fieldLook.getValue())
                    + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                    + BlancoCgLineUtil.getTerminator(fTargetLang));
            lineList.add("}");
        }

        lineList.add(BlancoCgLineUtil.getSingleLineCommentPrefix(fTargetLang)
                + "Undefined.");
        lineList.add("if (argCheck == NOT_DEFINED) {");
        lineList.add("return "
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + BlancoCgLineUtil.getTerminator(fTargetLang));
        lineList.add("}");

        lineList.add("");
        lineList.add(BlancoCgLineUtil.getSingleLineCommentPrefix(fTargetLang)
                + "None of them were applicable.");
        lineList.add("throw new IllegalArgumentException("
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + "The given value ("
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + " + argCheck + "
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + ") is a value that is not defined in the string group [" + argProcessStructure.getName()
                + "]."
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang) + ")"
                + BlancoCgLineUtil.getTerminator(fTargetLang));
    }

    /**
     * Gets the name of the boolean type that matches the programming language.
     * 
     * Changes the reading of the type.
     * 
     * @return
     */
    private final String getTypeBoolean() {
        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
        default:
            return "boolean";
        case BlancoCgSupportedLang.CS:
            return "bool";
        case BlancoCgSupportedLang.JS:
            return "boolean";
        case BlancoCgSupportedLang.VB:
            return "Boolean";
        }
    }

    /**
     * Gets the name of the String type that matches the programming language.
     * 
     * Changes the reading of the type.
     * 
     * @return
     */
    private final String getTypeString() {
        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
        default:
            return "java.lang.String";
        case BlancoCgSupportedLang.CS:
            return "string";
        case BlancoCgSupportedLang.JS:
            return "string";
        case BlancoCgSupportedLang.VB:
            return "String";
        case BlancoCgSupportedLang.PHP:
            return "string";
        }
    }

    private final String getTypeInt() {
        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
        case BlancoCgSupportedLang.CS:
        case BlancoCgSupportedLang.JS:
        default:
            return "int";
        case BlancoCgSupportedLang.VB:
        case BlancoCgSupportedLang.DELPHI:
            return "Integer";
        case BlancoCgSupportedLang.PHP:
            return "integer";
        }
    }

    /**
     * Transforms the method name to match the programming language.
     * 
     * Changes the reading of the method name.
     * 
     * @param argMethodName
     *            The method name.
     * @return
     */
    private final String getMethodName(final String argMethodName) {
        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
        default:
            return argMethodName;
        case BlancoCgSupportedLang.CS:
        case BlancoCgSupportedLang.VB:
            return BlancoNameAdjuster.toUpperCaseTitle(argMethodName);
        case BlancoCgSupportedLang.JS:
            return argMethodName;
        }
    }
}
