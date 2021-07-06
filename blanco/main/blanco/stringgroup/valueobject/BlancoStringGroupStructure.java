package blanco.stringgroup.valueobject;

import java.util.List;

/**
 * BlancoStringGroupのなかで利用されるValueObjectです。
 */
public class BlancoStringGroupStructure {
    /**
     * 文字列グループIDを指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * パッケージ名を指定します。必須項目です。
     *
     * フィールド: [package]。
     */
    private String fPackage;

    /**
     * 文字列グループの説明を記載します。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * クラス名の後方に付与されるサフィックス。
     *
     * フィールド: [suffix]。
     */
    private String fSuffix;

    /**
     * フィールドを保持するリスト。
     *
     * フィールド: [fieldList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure&gt;()]。
     */
    private List<BlancoStringGroupFieldStructure> fFieldList = new java.util.ArrayList<blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure>();

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [文字列グループIDを指定します。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [文字列グループIDを指定します。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [package] の値を設定します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @param argPackage フィールド[package]に設定する値。
     */
    public void setPackage(final String argPackage) {
        fPackage = argPackage;
    }

    /**
     * フィールド [package] の値を取得します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @return フィールド[package]から取得した値。
     */
    public String getPackage() {
        return fPackage;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [文字列グループの説明を記載します。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [文字列グループの説明を記載します。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [suffix] の値を設定します。
     *
     * フィールドの説明: [クラス名の後方に付与されるサフィックス。]。
     *
     * @param argSuffix フィールド[suffix]に設定する値。
     */
    public void setSuffix(final String argSuffix) {
        fSuffix = argSuffix;
    }

    /**
     * フィールド [suffix] の値を取得します。
     *
     * フィールドの説明: [クラス名の後方に付与されるサフィックス。]。
     *
     * @return フィールド[suffix]から取得した値。
     */
    public String getSuffix() {
        return fSuffix;
    }

    /**
     * フィールド [fieldList] の値を設定します。
     *
     * フィールドの説明: [フィールドを保持するリスト。]。
     *
     * @param argFieldList フィールド[fieldList]に設定する値。
     */
    public void setFieldList(final List<BlancoStringGroupFieldStructure> argFieldList) {
        fFieldList = argFieldList;
    }

    /**
     * フィールド [fieldList] の値を取得します。
     *
     * フィールドの説明: [フィールドを保持するリスト。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure&gt;()]。
     *
     * @return フィールド[fieldList]から取得した値。
     */
    public List<BlancoStringGroupFieldStructure> getFieldList() {
        return fFieldList;
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ文字列化の処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
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

    /**
     * このバリューオブジェクトを指定のターゲットに複写します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ複写処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @param target target value object.
     */
    public void copyTo(final BlancoStringGroupStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoStringGroupStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fPackage
        // Type: java.lang.String
        target.fPackage = this.fPackage;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fSuffix
        // Type: java.lang.String
        target.fSuffix = this.fSuffix;
        // Name: fFieldList
        // Type: java.util.List
        // フィールド[fFieldList]はサポート外の型[java.util.Listblanco.stringgroup.valueobject.BlancoStringGroupFieldStructure]です。
    }
}
