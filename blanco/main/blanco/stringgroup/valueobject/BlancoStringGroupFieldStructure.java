package blanco.stringgroup.valueobject;

/**
 * BlancoStringGroupのなかで利用されるValueObjectです。
 */
public class BlancoStringGroupFieldStructure {
    /**
     * 項目番号
     *
     * フィールド: [no]。
     */
    private String fNo;

    /**
     * 文字列を指定します。必須項目です。
     *
     * フィールド: [value]。
     */
    private String fValue;

    /**
     * 文字列に割り付けられる定数を指定します。
     *
     * フィールド: [constant]。
     */
    private String fConstant;

    /**
     * 文字列に対する説明を指定します。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * フィールド [no] の値を設定します。
     *
     * フィールドの説明: [項目番号]。
     *
     * @param argNo フィールド[no]に設定する値。
     */
    public void setNo(final String argNo) {
        fNo = argNo;
    }

    /**
     * フィールド [no] の値を取得します。
     *
     * フィールドの説明: [項目番号]。
     *
     * @return フィールド[no]から取得した値。
     */
    public String getNo() {
        return fNo;
    }

    /**
     * フィールド [value] の値を設定します。
     *
     * フィールドの説明: [文字列を指定します。必須項目です。]。
     *
     * @param argValue フィールド[value]に設定する値。
     */
    public void setValue(final String argValue) {
        fValue = argValue;
    }

    /**
     * フィールド [value] の値を取得します。
     *
     * フィールドの説明: [文字列を指定します。必須項目です。]。
     *
     * @return フィールド[value]から取得した値。
     */
    public String getValue() {
        return fValue;
    }

    /**
     * フィールド [constant] の値を設定します。
     *
     * フィールドの説明: [文字列に割り付けられる定数を指定します。]。
     *
     * @param argConstant フィールド[constant]に設定する値。
     */
    public void setConstant(final String argConstant) {
        fConstant = argConstant;
    }

    /**
     * フィールド [constant] の値を取得します。
     *
     * フィールドの説明: [文字列に割り付けられる定数を指定します。]。
     *
     * @return フィールド[constant]から取得した値。
     */
    public String getConstant() {
        return fConstant;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [文字列に対する説明を指定します。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [文字列に対する説明を指定します。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
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
        buf.append("blanco.stringgroup.valueobject.BlancoStringGroupFieldStructure[");
        buf.append("no=" + fNo);
        buf.append(",value=" + fValue);
        buf.append(",constant=" + fConstant);
        buf.append(",description=" + fDescription);
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
    public void copyTo(final BlancoStringGroupFieldStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoStringGroupFieldStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fNo
        // Type: java.lang.String
        target.fNo = this.fNo;
        // Name: fValue
        // Type: java.lang.String
        target.fValue = this.fValue;
        // Name: fConstant
        // Type: java.lang.String
        target.fConstant = this.fConstant;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
    }
}
