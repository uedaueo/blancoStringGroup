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
 * 「文字列グループ定義書」Excel様式から文字列グループを処理するクラス・ソースコードを生成。
 * 
 * このクラスは、中間XMLファイルからソースコードを自動生成する機能を担います。
 * 
 * @author IGA Tosiki
 */
public class BlancoStringGroupXml2SourceFile {
    /**
     * メッセージ定義。
     */
    private final BlancoStringGroupMessage fMsg = new BlancoStringGroupMessage();

    /**
     * このプロダクトのリソースバンドルへのアクセスオブジェクト。
     */
    private final BlancoStringGroupResourceBundle fBundle = new BlancoStringGroupResourceBundle();

    /**
     * 出力対象となるプログラミング言語。
     */
    private int fTargetLang = BlancoCgSupportedLang.NOT_DEFINED;

    /**
     * 内部的に利用するblancoCg用ファクトリ。
     */
    private BlancoCgObjectFactory fCgFactory = null;

    /**
     * 内部的に利用するblancoCg用ソースファイル情報。
     */
    private BlancoCgSourceFile fCgSourceFile = null;

    /**
     * 内部的に利用するblancoCg用クラス情報。
     */
    private BlancoCgClass fCgClass = null;

    /**
     * 自動生成するソースファイルの文字エンコーディング。
     */
    private String fEncoding = null;

    public void setEncoding(final String argEncoding) {
        fEncoding = argEncoding;
    }

    /**
     * 中間XMLファイルからソースコードを自動生成します。
     * 
     * @param argMetaXmlSourceFile
     *            メタ情報が含まれているXMLファイル。
     * @param argTargetLang
     *            出力対象となるプログラミング言語。
     * @param argDirectoryTarget
     *            ソースコード生成先ディレクトリ (/mainを除く部分を指定します)。
     * @throws IOException
     *             入出力例外が発生した場合。
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
            // メタ情報の解析結果をもとにソースコード自動生成を実行します。
            structure2Source(structures[index], argDirectoryTarget);
        }
    }

    /**
     * 収集された情報を元に、ソースコードを自動生成します。
     * 
     * @param argStructure
     *            メタファイルから収集できた処理構造データ。
     * @param argDirectoryTarget
     *            ソースコードの出力先フォルダ。
     */
    public void structure2Source(final BlancoStringGroupStructure argStructure,
            final File argDirectoryTarget) {
        // 従来と互換性を持たせるため、/mainサブフォルダに出力します。
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
            // Delphi言語では、Unit名との名前衝突を避けるため、慣例に従いクラス名に強制的にTをつけます。
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

        // TODO 現在 Java言語にしか対応していません。
        switch (fTargetLang) {
        case BlancoCgSupportedLang.JAVA:
            expandMethodConvertToString(argStructure);
            break;
        }

        BlancoCgTransformerFactory.getSourceTransformer(fTargetLang).transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * 定数フィールドを展開します。
     * 
     * @param argProcessStructure
     *            メタファイルから収集できた処理構造データ。
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
            // ここから個別の文字に対する処理を記述します。
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
                    getTypeInt(), "未定義。文字列グループ以外の文字列または定数が未定義のもの。");
            fCgClass.getFieldList().add(cgField);

            cgField.setAccess("public");
            cgField.setStatic(true);
            cgField.setFinal(true);
            cgField.setDefault("-1");
        }
    }

    /**
     * matchメソッドを展開します。
     * 
     * @param argProcessStructure
     *            メタファイルから収集できた処理構造データ。
     */
    private void expandMethodMatch(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("match"), "文字列グループに含まれる文字列であるかどうかを判定します。");
        fCgClass.getMethodList().add(cgMethod);
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeString(),
                        "チェックを行いたい文字列。"));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeBoolean(),
                "文字列グループに含まれていればture。グループに含まれない文字列であればfalse。"));

        final List<java.lang.String> lineList = cgMethod.getLineList();

        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);

            // ここから個別の文字に対する処理を記述します。
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
     * matchIgnoreCaseメソッドを展開します。
     * 
     * @param argProcessStructure
     *            メタファイルから収集できた処理構造データ。
     */
    private void expandMethodMatchIgnoreCase(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("matchIgnoreCase"),
                "文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。");
        fCgClass.getMethodList().add(cgMethod);
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeString(),
                        "チェックを行いたい文字列。"));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeBoolean(),
                "文字列グループに含まれていればture。グループに含まれない文字列であればfalse。"));

        final List<java.lang.String> lineList = cgMethod.getLineList();

        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);

            // ここから個別の文字に対する処理を記述します。
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
     * convertToIntメソッドを展開します。
     * 
     * @param argProcessStructure
     *            メタファイルから収集できた処理構造データ。
     */
    private void expandMethodConvertToInt(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("convertToInt"), "文字列から定数に変換します。");
        fCgClass.getMethodList().add(cgMethod);

        cgMethod.getLangDoc().getDescriptionList().add(
                "定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。");
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeString(),
                        "変換を行いたい文字列。"));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeInt(), "定数に変換後の値。"));

        final List<java.lang.String> lineList = cgMethod.getLineList();

        for (int indexField = 0; indexField < argProcessStructure
                .getFieldList().size(); indexField++) {
            final BlancoStringGroupFieldStructure fieldLook = (BlancoStringGroupFieldStructure) argProcessStructure
                    .getFieldList().get(indexField);

            if (BlancoStringUtil.null2Blank(fieldLook.getConstant()).length() == 0) {
                // 定数が未定義のものはスキップします。
                continue;
            }

            // ここから個別の文字に対する処理を記述します。
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
                + "該当する定数が見つかりませんでした。");

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
     * convertToStringメソッドを展開します。
     * 
     * TODO Java言語にのみ対応しています。他の言語には対応していません。
     * 
     * @param argProcessStructure
     *            メタファイルから収集できた処理構造データ。
     */
    private void expandMethodConvertToString(
            final BlancoStringGroupStructure argProcessStructure) {

        final BlancoCgMethod cgMethod = fCgFactory.createMethod(
                getMethodName("convertToString"), "定数から文字列に変換します。");
        fCgClass.getMethodList().add(cgMethod);

        cgMethod.getLangDoc().getDescriptionList().add("定数と対応づく文字列に変換します。");
        cgMethod.getParameterList().add(
                fCgFactory.createParameter("argCheck", getTypeInt(),
                        "変換を行いたい文字定数。"));
        cgMethod.setReturn(fCgFactory.createReturn(getTypeString(),
                "文字列に変換後の値。NOT_DEFINEDの場合には長さ0の文字列。"));

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
                + "未定義。");
        lineList.add("if (argCheck == NOT_DEFINED) {");
        lineList.add("return "
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + BlancoCgLineUtil.getTerminator(fTargetLang));
        lineList.add("}");

        lineList.add("");
        lineList.add(BlancoCgLineUtil.getSingleLineCommentPrefix(fTargetLang)
                + "いずれにも該当しませんでした。");
        lineList.add("throw new IllegalArgumentException("
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + "与えられた値("
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + " + argCheck + "
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang)
                + ")は文字列グループ[" + argProcessStructure.getName()
                + "]では定義されない値です。"
                + BlancoCgLineUtil.getStringLiteralEnclosure(fTargetLang) + ")"
                + BlancoCgLineUtil.getTerminator(fTargetLang));
    }

    /**
     * プログラミング言語処理系に合った boolean 型の名称を取得します。
     * 
     * 型の読み替え。
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
     * プログラミング言語処理系に合った String 型の名称を取得します。
     * 
     * 型の読み替え。
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
     * プログラミング言語処理系に合った メソッドの名前変形をおこないます。
     * 
     * メソッド名の読み替え。
     * 
     * @param argMethodName
     *            メソッド名。
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
