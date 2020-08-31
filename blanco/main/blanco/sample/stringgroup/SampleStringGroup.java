package blanco.sample.stringgroup;

/**
 * 文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。
 */
public class SampleStringGroup {
    /**
     * No.1 説明:アルファベットの文字列定義。
     */
    public static final int ABCDEFG = 1;

    /**
     * No.2 説明:全角の文字列定義。
     */
    public static final int AIUEO = 2;

    /**
     * No.3 説明:シングルクオートが展開されることの確認。
     */
    public static final int QUOTE = 3;

    /**
     * No.4 説明:ダブルクオートが展開されることの確認。
     */
    public static final int DOUBLE_QUOTE = 4;

    /**
     * No.5 説明:バックスラッシュが展開されることの確認。
     */
    public static final int BACK_SLASH = 5;

    /**
     * No.7
     */
    public static final int WITHOUT_DESC = 7;

    /**
     * No.8 説明:途中の空白が適切に処理されることの確認。
     */
    public static final int TEST_SPACE = 8;

    /**
     * No.9 説明:×マーク。
     */
    public static final int BATU = 9;

    /**
     * 未定義。文字列グループ以外の文字列または定数が未定義のもの。
     */
    public static final int NOT_DEFINED = -1;

    /**
     * 文字列グループに含まれる文字列であるかどうかを判定します。
     *
     * @param argCheck チェックを行いたい文字列。
     * @return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
     */
    public boolean match(final String argCheck) {
        // No.1
        // 説明:アルファベットの文字列定義。
        if ("ABCDEFG".equals(argCheck)) {
            return true;
        }
        // No.2
        // 説明:全角の文字列定義。
        if ("あいうえお".equals(argCheck)) {
            return true;
        }
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if ("'".equals(argCheck)) {
            return true;
        }
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if ("\"".equals(argCheck)) {
            return true;
        }
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if ("\\".equals(argCheck)) {
            return true;
        }
        // No.6
        // 説明:定数が省略された定義。
        if ("STRING ONLY".equals(argCheck)) {
            return true;
        }
        // No.7
        if ("説明を省略".equals(argCheck)) {
            return true;
        }
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if ("ABC DEF".equals(argCheck)) {
            return true;
        }
        // No.9
        // 説明:×マーク。
        if ("×".equals(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * 文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。
     *
     * @param argCheck チェックを行いたい文字列。
     * @return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
     */
    public boolean matchIgnoreCase(final String argCheck) {
        // No.1
        // 説明:アルファベットの文字列定義。
        if ("ABCDEFG".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.2
        // 説明:全角の文字列定義。
        if ("あいうえお".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if ("'".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if ("\"".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if ("\\".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.6
        // 説明:定数が省略された定義。
        if ("STRING ONLY".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.7
        if ("説明を省略".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if ("ABC DEF".equalsIgnoreCase(argCheck)) {
            return true;
        }
        // No.9
        // 説明:×マーク。
        if ("×".equalsIgnoreCase(argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * 文字列から定数に変換します。
     *
     * 定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。
     *
     * @param argCheck 変換を行いたい文字列。
     * @return 定数に変換後の値。
     */
    public int convertToInt(final String argCheck) {
        // No.1
        // 説明:アルファベットの文字列定義。
        if ("ABCDEFG".equals(argCheck)) {
            return ABCDEFG;
        }
        // No.2
        // 説明:全角の文字列定義。
        if ("あいうえお".equals(argCheck)) {
            return AIUEO;
        }
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if ("'".equals(argCheck)) {
            return QUOTE;
        }
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if ("\"".equals(argCheck)) {
            return DOUBLE_QUOTE;
        }
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if ("\\".equals(argCheck)) {
            return BACK_SLASH;
        }
        // No.7
        if ("説明を省略".equals(argCheck)) {
            return WITHOUT_DESC;
        }
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if ("ABC DEF".equals(argCheck)) {
            return TEST_SPACE;
        }
        // No.9
        // 説明:×マーク。
        if ("×".equals(argCheck)) {
            return BATU;
        }

        // 該当する定数が見つかりませんでした。
        return NOT_DEFINED;
    }

    /**
     * 定数から文字列に変換します。
     *
     * 定数と対応づく文字列に変換します。
     *
     * @param argCheck 変換を行いたい文字定数。
     * @return 文字列に変換後の値。NOT_DEFINEDの場合には長さ0の文字列。
     */
    public String convertToString(final int argCheck) {
        // No.1
        // 説明:アルファベットの文字列定義。
        if (argCheck == ABCDEFG) {
            return "ABCDEFG";
        }
        // No.2
        // 説明:全角の文字列定義。
        if (argCheck == AIUEO) {
            return "あいうえお";
        }
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if (argCheck == QUOTE) {
            return "'";
        }
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if (argCheck == DOUBLE_QUOTE) {
            return "\"";
        }
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if (argCheck == BACK_SLASH) {
            return "\\";
        }
        // No.7
        if (argCheck == WITHOUT_DESC) {
            return "説明を省略";
        }
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if (argCheck == TEST_SPACE) {
            return "ABC DEF";
        }
        // No.9
        // 説明:×マーク。
        if (argCheck == BATU) {
            return "×";
        }
        // 未定義。
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // いずれにも該当しませんでした。
        throw new IllegalArgumentException("与えられた値(" + argCheck + ")は文字列グループ[Sample]では定義されない値です。");
    }
}
