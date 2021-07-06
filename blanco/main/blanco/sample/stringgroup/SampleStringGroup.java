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
     * Undefined. A string or constant other than a string group that is undefined.
     */
    public static final int NOT_DEFINED = -1;

    /**
     * Determines if a string is part of a string group.
     *
     * @param argCheck A string to be checked.
     * @return true is the string is part of a string group, false otherwise.
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
     * Determines if a string is part of a string group in a case-insentive manner.
     *
     * @param argCheck A string to be checked.
     * @return true is the string is part of a string group, false otherwise.
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
     * Converts a string to a constant.
     *
     * Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.
     *
     * @param argCheck A string to be converted.
     * @return The value after conversion to a constant.
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

        // No matching constants were found.
        return NOT_DEFINED;
    }

    /**
     * Converts a constant to a string.
     *
     * Converts to a string corresponding to a constant.
     *
     * @param argCheck A constant to be converted.
     * @return The value after conversion to a string, or a zero-length string if NOT_DEFINED.
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
        // Undefined.
        if (argCheck == NOT_DEFINED) {
            return "";
        }

        // None of them were applicable.
        throw new IllegalArgumentException("The given value (" + argCheck + ") is a value that is not defined in the string group [Sample].");
    }
}
