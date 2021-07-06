/*
 * blanco Framework
 * Copyright (C) 2004-2007 IGA Tosiki
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.stringgroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import blanco.commons.util.BlancoStringUtil;
import blanco.stringgroup.message.BlancoStringGroupMessage;
import blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure;
import blanco.stringgroup.valueobject.BlancoStringGroupStructure;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.BlancoXmlUnmarshaller;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

/**
 * This class is responsible for extracting information from intermediate XML files.
 * 
 * @author IGA Tosiki
 */
public class BlancoStringGroupXmlParser {
    /**
     * Message definition.
     */
    protected final BlancoStringGroupMessage fMsg = new BlancoStringGroupMessage();

    /**
     * Parses an XML document in an intermediate XML file to get an array of information.
     * 
     * @param argMetaXmlSourceFile
     *            An intermediate XML file.
     * @return An array of information obtained as a result of parsing.
     */
    public BlancoStringGroupStructure[] parse(final File argMetaXmlSourceFile) {
        final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                .unmarshal(argMetaXmlSourceFile);
        if (documentMeta == null) {
            return null;
        }

        return parse(documentMeta);
    }

    /**
     * Parses an XML document in an intermediate XML file to get an array of value object information.
     * 
     * @param argXmlDocument
     *            XML document of an intermediate XML file.
     * @return An array of value object information obtained as a result of parsing.
     */
    public BlancoStringGroupStructure[] parse(
            final BlancoXmlDocument argXmlDocument) {
        final List<BlancoStringGroupStructure> listStructure = new ArrayList<BlancoStringGroupStructure>();
        // Gets the root element.
        final BlancoXmlElement elementRoot = BlancoXmlBindingUtil
                .getDocumentElement(argXmlDocument);
        if (elementRoot == null) {
            // The process is aborted if there is no root element.
            return null;
        }

        // Gets a list of sheets (Excel sheets).
        final List<BlancoXmlElement> listSheet = BlancoXmlBindingUtil
                .getElementsByTagName(elementRoot, "sheet");
        final int sizeListSheet = listSheet.size();
        for (int index = 0; index < sizeListSheet; index++) {
            final BlancoXmlElement elementSheet = listSheet.get(index);

            final BlancoStringGroupStructure structure = parseElementSheet(elementSheet);
            if (structure != null) {
                // Memorizes the obtained information.
                listStructure.add(structure);
            }
        }

        final BlancoStringGroupStructure[] result = new BlancoStringGroupStructure[listStructure
                .size()];
        listStructure.toArray(result);
        return result;
    }

    /**
     * Parses the "sheet" XML element in the intermediate XML file to get the value object information.
     * 
     * @param argElementSheet
     *            "sheet" XML element in the intermediate XML file.
     * @return Value object information obtained as a result of parsing. Null is returned if "name" is not found.
     */
    public BlancoStringGroupStructure parseElementSheet(
            final BlancoXmlElement argElementSheet) {
        final BlancoStringGroupStructure structure = new BlancoStringGroupStructure();
        // Gets the input parameter information.

        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancostringgroup-common");
        if (listCommon.size() == 0) {
            // Skips if there is no common.
            return null;
        }

        // Processes only the first item.
        final BlancoXmlElement elementCommon = listCommon.get(0);

        // Gets detailed information from the sheet.
        structure.setName(BlancoXmlBindingUtil.getTextContent(elementCommon,
                "name"));
        structure.setPackage(BlancoXmlBindingUtil.getTextContent(elementCommon,
                "package"));

        if (BlancoStringUtil.null2Blank(structure.getName()).length() == 0) {
            return null;
        }

        if (BlancoStringUtil.null2Blank(structure.getPackage()).trim().length() == 0) {
            throw new IllegalArgumentException(fMsg.getMbsgi001(structure
                    .getName()));
        }

        if (BlancoXmlBindingUtil.getTextContent(elementCommon, "description") != null) {
            structure.setDescription(BlancoXmlBindingUtil.getTextContent(
                    elementCommon, "description"));
        }

        if (BlancoXmlBindingUtil.getTextContent(elementCommon, "suffix") != null) {
            structure.setSuffix(BlancoXmlBindingUtil.getTextContent(
                    elementCommon, "suffix"));
        }

        final BlancoXmlElement elementStringGroupList = BlancoXmlBindingUtil
                .getElement(argElementSheet, "blancostringgroup-list");
        if (elementStringGroupList == null) {
            return null;
        }

        // Gets the contents of the list.
        final List<BlancoXmlElement> listField = BlancoXmlBindingUtil
                .getElementsByTagName(elementStringGroupList, "field");
        for (int indexField = 0; indexField < listField.size(); indexField++) {
            final BlancoXmlElement elementField = listField.get(indexField);

            final BlancoStringGroupFieldStructure fieldStructure = new BlancoStringGroupFieldStructure();

            fieldStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementField, "no"));
            fieldStructure.setValue(BlancoXmlBindingUtil.getTextContent(
                    elementField, "value"));
            if (fieldStructure.getValue() == null) {
                // Skips if value is not specified.
                continue;
            }

            fieldStructure.setConstant(BlancoXmlBindingUtil.getTextContent(
                    elementField, "constant"));

            fieldStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                    elementField, "description"));

            // Checks to see if the same content has already been registered.
            for (int indexPast = 0; indexPast < structure.getFieldList().size(); indexPast++) {
                final BlancoStringGroupFieldStructure fieldPast = (BlancoStringGroupFieldStructure) structure
                        .getFieldList().get(indexPast);
                if (fieldPast.getValue().equals(fieldStructure.getValue())) {
                    throw new IllegalArgumentException(fMsg.getMbsgi003(
                            structure.getName(), fieldStructure.getValue()));
                }
            }

            structure.getFieldList().add(fieldStructure);
        }

        return structure;
    }
}
