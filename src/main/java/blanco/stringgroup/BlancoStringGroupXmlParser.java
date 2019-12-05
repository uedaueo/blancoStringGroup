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
 * このクラスは、中間XMLファイルから情報抽出する機能を担います。
 * 
 * @author IGA Tosiki
 */
public class BlancoStringGroupXmlParser {
    /**
     * メッセージ定義。
     */
    protected final BlancoStringGroupMessage fMsg = new BlancoStringGroupMessage();

    /**
     * 中間XMLファイルのXMLドキュメントをパースして、情報の配列を取得します。
     * 
     * @param argMetaXmlSourceFile
     *            中間XMLファイル。
     * @return パースの結果得られた情報の配列。
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
     * 中間XMLファイル形式のXMLドキュメントをパースして、バリューオブジェクト情報の配列を取得します。
     * 
     * @param argXmlDocument
     *            中間XMLファイルのXMLドキュメント。
     * @return パースの結果得られたバリューオブジェクト情報の配列。
     */
    public BlancoStringGroupStructure[] parse(
            final BlancoXmlDocument argXmlDocument) {
        final List<BlancoStringGroupStructure> listStructure = new ArrayList<BlancoStringGroupStructure>();
        // ルートエレメントを取得します。
        final BlancoXmlElement elementRoot = BlancoXmlBindingUtil
                .getDocumentElement(argXmlDocument);
        if (elementRoot == null) {
            // ルートエレメントが無い場合には処理中断します。
            return null;
        }

        // sheet(Excelシート)のリストを取得します。
        final List<BlancoXmlElement> listSheet = BlancoXmlBindingUtil
                .getElementsByTagName(elementRoot, "sheet");
        final int sizeListSheet = listSheet.size();
        for (int index = 0; index < sizeListSheet; index++) {
            final BlancoXmlElement elementSheet = listSheet.get(index);

            final BlancoStringGroupStructure structure = parseElementSheet(elementSheet);
            if (structure != null) {
                // 得られた情報を記憶します。
                listStructure.add(structure);
            }
        }

        final BlancoStringGroupStructure[] result = new BlancoStringGroupStructure[listStructure
                .size()];
        listStructure.toArray(result);
        return result;
    }

    /**
     * 中間XMLファイル形式の「sheet」XMLエレメントをパースして、バリューオブジェクト情報を取得します。
     * 
     * @param argElementSheet
     *            中間XMLファイルの「sheet」XMLエレメント。
     * @return パースの結果得られたバリューオブジェクト情報。「name」が見つからなかった場合には nullを戻します。
     */
    public BlancoStringGroupStructure parseElementSheet(
            final BlancoXmlElement argElementSheet) {
        final BlancoStringGroupStructure structure = new BlancoStringGroupStructure();
        // 入力パラメータ情報を取得します。

        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancostringgroup-common");
        if (listCommon.size() == 0) {
            // commonが無い場合にはスキップします。
            return null;
        }

        // 最初のアイテムのみ処理しています。
        final BlancoXmlElement elementCommon = listCommon.get(0);

        // シートから詳細な情報を取得します。
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

        // 一覧の内容を取得します。
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
                // valueが指定されていない場合には処理しません。
                continue;
            }

            fieldStructure.setConstant(BlancoXmlBindingUtil.getTextContent(
                    elementField, "constant"));

            fieldStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                    elementField, "description"));

            // 既に同じ内容が登録されていないかどうかのチェック。
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
