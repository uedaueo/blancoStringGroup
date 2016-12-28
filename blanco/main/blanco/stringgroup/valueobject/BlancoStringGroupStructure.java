/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.stringgroup.valueobject;

import java.util.List;

/**
 * BlancoStringGroupのなかで利用されるValueObjectです。
 */
public class BlancoStringGroupStructure {
    /**
     * フィールド [name]
     *
     * 項目の型 [java.lang.String]<br>
     * 文字列グループIDを指定します。必須項目です。
     */
    private String fName;

    /**
     * フィールド [package]
     *
     * 項目の型 [java.lang.String]<br>
     * パッケージ名を指定します。必須項目です。
     */
    private String fPackage;

    /**
     * フィールド [description]
     *
     * 項目の型 [java.lang.String]<br>
     * 文字列グループの説明を記載します。
     */
    private String fDescription;

    /**
     * フィールド [suffix]
     *
     * 項目の型 [java.lang.String]<br>
     * クラス名の後方に付与されるサフィックス。
     */
    private String fSuffix;

    /**
     * フィールド [fieldList]
     *
     * 項目の型 [java.util.List<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure>]<br>
     * 規定値   [new java.util.ArrayList<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure>()]<br>
     * フィールドを保持するリスト。
     */
    private List<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure> fFieldList = new java.util.ArrayList<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure>();

    /**
     * フィールド [name]のセッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * 文字列グループIDを指定します。必須項目です。
     *
     * @param argName フィールド[name]に格納したい値
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド[name]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * 文字列グループIDを指定します。必須項目です。
     *
     * @return フィールド[name]に格納されている値
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [package]のセッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * パッケージ名を指定します。必須項目です。
     *
     * @param argPackage フィールド[package]に格納したい値
     */
    public void setPackage(final String argPackage) {
        fPackage = argPackage;
    }

    /**
     * フィールド[package]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * パッケージ名を指定します。必須項目です。
     *
     * @return フィールド[package]に格納されている値
     */
    public String getPackage() {
        return fPackage;
    }

    /**
     * フィールド [description]のセッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * 文字列グループの説明を記載します。
     *
     * @param argDescription フィールド[description]に格納したい値
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド[description]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * 文字列グループの説明を記載します。
     *
     * @return フィールド[description]に格納されている値
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [suffix]のセッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * クラス名の後方に付与されるサフィックス。
     *
     * @param argSuffix フィールド[suffix]に格納したい値
     */
    public void setSuffix(final String argSuffix) {
        fSuffix = argSuffix;
    }

    /**
     * フィールド[suffix]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]<br>
     * クラス名の後方に付与されるサフィックス。
     *
     * @return フィールド[suffix]に格納されている値
     */
    public String getSuffix() {
        return fSuffix;
    }

    /**
     * フィールド [fieldList]のセッターメソッド
     *
     * 項目の型 [java.util.List<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure>]<br>
     * フィールドを保持するリスト。
     *
     * @param argFieldList フィールド[fieldList]に格納したい値
     */
    public void setFieldList(final List<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure> argFieldList) {
        fFieldList = argFieldList;
    }

    /**
     * フィールド[fieldList]のゲッターメソッド
     *
     * 項目の型 [java.util.List<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure>]<br>
     * 規定値   [new java.util.ArrayList<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure>()]<br>
     * フィールドを保持するリスト。
     *
     * @return フィールド[fieldList]に格納されている値
     */
    public List<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure> getFieldList() {
        return fFieldList;
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * オブジェクトのシャロー範囲でしかtoStringされない点に注意して利用してください。
     *
     * @return バリューオブジェクトの文字列表現。
     */
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.stringgroup.valueobject.BlancoStringGroupStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",description=" + fDescription);
        buf.append(",suffix=" + fSuffix);
        buf.append(",fieldList=" + fFieldList);
        buf.append("]");
        return buf.toString();
    }
}
