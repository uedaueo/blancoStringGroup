/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */

/**
 * デフォルトコンストラクタ
 * @fileoverview このソースコードは blanco Frameworkにより自動生成されました。
 * @class 文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。
 * @constructor
 */
function SampleStringGroup() {
    /* パラメータの数、型チェックを行います。 */
    if (arguments.length !== 0) {
        throw new Error("[ArgumentException]: SampleStringGroup.SampleStringGroup のパラメータは[0]個である必要があります。しかし実際には[" + arguments.length +  "]個のパラメータを伴って呼び出されました。");
    }

}

/**
 * No.1 説明:アルファベットの文字列定義。
 * @type int
 */
SampleStringGroup.ABCDEFG = 1;

/**
 * No.2 説明:全角の文字列定義。
 * @type int
 */
SampleStringGroup.AIUEO = 2;

/**
 * No.3 説明:シングルクオートが展開されることの確認。
 * @type int
 */
SampleStringGroup.QUOTE = 3;

/**
 * No.4 説明:ダブルクオートが展開されることの確認。
 * @type int
 */
SampleStringGroup.DOUBLE_QUOTE = 4;

/**
 * No.5 説明:バックスラッシュが展開されることの確認。
 * @type int
 */
SampleStringGroup.BACK_SLASH = 5;

/**
 * No.7
 * @type int
 */
SampleStringGroup.WITHOUT_DESC = 7;

/**
 * No.8 説明:途中の空白が適切に処理されることの確認。
 * @type int
 */
SampleStringGroup.TEST_SPACE = 8;

/**
 * No.9 説明:×マーク。
 * @type int
 */
SampleStringGroup.BATU = 9;

/**
 * Undefined. A string or constant other than a string group that is undefined.
 * @type int
 */
SampleStringGroup.NOT_DEFINED = -1;

/**
 * Determines if a string is part of a string group.
 * @addon
 * @param {string} argCheck A string to be checked.
 * @return true is the string is part of a string group, false otherwise.
 * @type boolean
 */
SampleStringGroup.prototype.match = function(/* string */ argCheck) {
    /* パラメータの数、型チェックを行います。 */
    if (arguments.length !== 1) {
        throw new Error("[ArgumentException]: SampleStringGroup.match のパラメータは[1]個である必要があります。しかし実際には[" + arguments.length +  "]個のパラメータを伴って呼び出されました。");
    }
    if (typeof(argCheck) != "string") {
        throw new Error("[ArgumentException]: SampleStringGroup.match の1番目のパラメータは[string]型でなくてはなりません。しかし実際には[" + typeof(argCheck) + "]型が与えられました。");
    }

    // No.1
    // 説明:アルファベットの文字列定義。
    if ("ABCDEFG" == argCheck) {
        return true;
    }
    // No.2
    // 説明:全角の文字列定義。
    if ("あいうえお" == argCheck) {
        return true;
    }
    // No.3
    // 説明:シングルクオートが展開されることの確認。
    if ("'" == argCheck) {
        return true;
    }
    // No.4
    // 説明:ダブルクオートが展開されることの確認。
    if ("\"" == argCheck) {
        return true;
    }
    // No.5
    // 説明:バックスラッシュが展開されることの確認。
    if ("\\" == argCheck) {
        return true;
    }
    // No.6
    // 説明:定数が省略された定義。
    if ("STRING ONLY" == argCheck) {
        return true;
    }
    // No.7
    if ("説明を省略" == argCheck) {
        return true;
    }
    // No.8
    // 説明:途中の空白が適切に処理されることの確認。
    if ("ABC DEF" == argCheck) {
        return true;
    }
    // No.9
    // 説明:×マーク。
    if ("×" == argCheck) {
        return true;
    }
    return false;
};

/**
 * Determines if a string is part of a string group in a case-insentive manner.
 * @addon
 * @param {string} argCheck A string to be checked.
 * @return true is the string is part of a string group, false otherwise.
 * @type boolean
 */
SampleStringGroup.prototype.matchIgnoreCase = function(/* string */ argCheck) {
    /* パラメータの数、型チェックを行います。 */
    if (arguments.length !== 1) {
        throw new Error("[ArgumentException]: SampleStringGroup.matchIgnoreCase のパラメータは[1]個である必要があります。しかし実際には[" + arguments.length +  "]個のパラメータを伴って呼び出されました。");
    }
    if (typeof(argCheck) != "string") {
        throw new Error("[ArgumentException]: SampleStringGroup.matchIgnoreCase の1番目のパラメータは[string]型でなくてはなりません。しかし実際には[" + typeof(argCheck) + "]型が与えられました。");
    }

    // No.1
    // 説明:アルファベットの文字列定義。
    if ("ABCDEFG".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.2
    // 説明:全角の文字列定義。
    if ("あいうえお".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.3
    // 説明:シングルクオートが展開されることの確認。
    if ("'".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.4
    // 説明:ダブルクオートが展開されることの確認。
    if ("\"".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.5
    // 説明:バックスラッシュが展開されることの確認。
    if ("\\".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.6
    // 説明:定数が省略された定義。
    if ("STRING ONLY".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.7
    if ("説明を省略".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.8
    // 説明:途中の空白が適切に処理されることの確認。
    if ("ABC DEF".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    // No.9
    // 説明:×マーク。
    if ("×".toUpperCase() == argCheck.toUpperCase()) {
        return true;
    }
    return false;
};

/**
 * Converts a string to a constant.
 * @addon
 * @param {string} argCheck A string to be converted.
 * @return The value after conversion to a constant.
 * @type int
 * Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.
 */
SampleStringGroup.prototype.convertToInt = function(/* string */ argCheck) {
    /* パラメータの数、型チェックを行います。 */
    if (arguments.length !== 1) {
        throw new Error("[ArgumentException]: SampleStringGroup.convertToInt のパラメータは[1]個である必要があります。しかし実際には[" + arguments.length +  "]個のパラメータを伴って呼び出されました。");
    }
    if (typeof(argCheck) != "string") {
        throw new Error("[ArgumentException]: SampleStringGroup.convertToInt の1番目のパラメータは[string]型でなくてはなりません。しかし実際には[" + typeof(argCheck) + "]型が与えられました。");
    }

    // No.1
    // 説明:アルファベットの文字列定義。
    if ("ABCDEFG" == argCheck) {
        return SampleStringGroup.ABCDEFG;
    }
    // No.2
    // 説明:全角の文字列定義。
    if ("あいうえお" == argCheck) {
        return SampleStringGroup.AIUEO;
    }
    // No.3
    // 説明:シングルクオートが展開されることの確認。
    if ("'" == argCheck) {
        return SampleStringGroup.QUOTE;
    }
    // No.4
    // 説明:ダブルクオートが展開されることの確認。
    if ("\"" == argCheck) {
        return SampleStringGroup.DOUBLE_QUOTE;
    }
    // No.5
    // 説明:バックスラッシュが展開されることの確認。
    if ("\\" == argCheck) {
        return SampleStringGroup.BACK_SLASH;
    }
    // No.7
    if ("説明を省略" == argCheck) {
        return SampleStringGroup.WITHOUT_DESC;
    }
    // No.8
    // 説明:途中の空白が適切に処理されることの確認。
    if ("ABC DEF" == argCheck) {
        return SampleStringGroup.TEST_SPACE;
    }
    // No.9
    // 説明:×マーク。
    if ("×" == argCheck) {
        return SampleStringGroup.BATU;
    }

    // No matching constants were found.
    return SampleStringGroup.NOT_DEFINED;
};
/* クラス[SampleStringGroup]宣言の終了。 */
