<?php
/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
namespace blanco.sample.stringgroup;

/*. require_module 'standard'; .*/;

/**
 * 文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。
 */
class SampleStringGroup {
    /**
     * No.1 説明:アルファベットの文字列定義。
     */
    const ABCDEFG = 1;

    /**
     * No.2 説明:全角の文字列定義。
     */
    const AIUEO = 2;

    /**
     * No.3 説明:シングルクオートが展開されることの確認。
     */
    const QUOTE = 3;

    /**
     * No.4 説明:ダブルクオートが展開されることの確認。
     */
    const DOUBLE_QUOTE = 4;

    /**
     * No.5 説明:バックスラッシュが展開されることの確認。
     */
    const BACK_SLASH = 5;

    /**
     * No.7
     */
    const WITHOUT_DESC = 7;

    /**
     * No.8 説明:途中の空白が適切に処理されることの確認。
     */
    const TEST_SPACE = 8;

    /**
     * No.9 説明:×マーク。
     */
    const BATU = 9;

    /**
     * Undefined. A string or constant other than a string group that is undefined.
     */
    const NOT_DEFINED = -1;

    /**
     * Determines if a string is part of a string group.
     *
     * @param string $argCheck A string to be checked.
     * @return boolean true is the string is part of a string group, false otherwise.
     */
    public /*.boolean.*/ function match(/*.string.*/ $argCheck) {
        /* パラメータの数、型チェックを行います。 */
        if (func_num_args() !== 1) {
            throw new \Exception('[ArgumentException]: SampleStringGroup.match のパラメータは[1]個である必要があります。しかし実際には[' . func_num_args() .  ']個のパラメータを伴って呼び出されました。');
        }
        if (gettype($argCheck) !== 'string' && gettype($argCheck) !== 'NULL') {
            throw new \Exception('[ArgumentException]: SampleStringGroup.match の1番目のパラメータは[string]型でなくてはなりません。しかし実際には[' . gettype($argCheck) . ']型が与えられました。');
        }

        // No.1
        // 説明:アルファベットの文字列定義。
        if ('ABCDEFG' === $argCheck) {
            return true;
        }
        // No.2
        // 説明:全角の文字列定義。
        if ('あいうえお' === $argCheck) {
            return true;
        }
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if ('\'' === $argCheck) {
            return true;
        }
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if ('"' === $argCheck) {
            return true;
        }
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if ('\\' === $argCheck) {
            return true;
        }
        // No.6
        // 説明:定数が省略された定義。
        if ('STRING ONLY' === $argCheck) {
            return true;
        }
        // No.7
        if ('説明を省略' === $argCheck) {
            return true;
        }
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if ('ABC DEF' === $argCheck) {
            return true;
        }
        // No.9
        // 説明:×マーク。
        if ('×' === $argCheck) {
            return true;
        }
        return false;
    }

    /**
     * Determines if a string is part of a string group in a case-insentive manner.
     *
     * @param string $argCheck A string to be checked.
     * @return boolean true is the string is part of a string group, false otherwise.
     */
    public /*.boolean.*/ function matchIgnoreCase(/*.string.*/ $argCheck) {
        /* パラメータの数、型チェックを行います。 */
        if (func_num_args() !== 1) {
            throw new \Exception('[ArgumentException]: SampleStringGroup.matchIgnoreCase のパラメータは[1]個である必要があります。しかし実際には[' . func_num_args() .  ']個のパラメータを伴って呼び出されました。');
        }
        if (gettype($argCheck) !== 'string' && gettype($argCheck) !== 'NULL') {
            throw new \Exception('[ArgumentException]: SampleStringGroup.matchIgnoreCase の1番目のパラメータは[string]型でなくてはなりません。しかし実際には[' . gettype($argCheck) . ']型が与えられました。');
        }

        // No.1
        // 説明:アルファベットの文字列定義。
        if (strtoupper('ABCDEFG') === strtoupper($argCheck)) {
            return true;
        }
        // No.2
        // 説明:全角の文字列定義。
        if (strtoupper('あいうえお') === strtoupper($argCheck)) {
            return true;
        }
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if (strtoupper('\'') === strtoupper($argCheck)) {
            return true;
        }
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if (strtoupper('"') === strtoupper($argCheck)) {
            return true;
        }
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if (strtoupper('\\') === strtoupper($argCheck)) {
            return true;
        }
        // No.6
        // 説明:定数が省略された定義。
        if (strtoupper('STRING ONLY') === strtoupper($argCheck)) {
            return true;
        }
        // No.7
        if (strtoupper('説明を省略') === strtoupper($argCheck)) {
            return true;
        }
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if (strtoupper('ABC DEF') === strtoupper($argCheck)) {
            return true;
        }
        // No.9
        // 説明:×マーク。
        if (strtoupper('×') === strtoupper($argCheck)) {
            return true;
        }
        return false;
    }

    /**
     * Converts a string to a constant.
     *
     * Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.
     *
     * @param string $argCheck A string to be converted.
     * @return integer The value after conversion to a constant.
     */
    public /*.int.*/ function convertToInt(/*.string.*/ $argCheck) {
        /* パラメータの数、型チェックを行います。 */
        if (func_num_args() !== 1) {
            throw new \Exception('[ArgumentException]: SampleStringGroup.convertToInt のパラメータは[1]個である必要があります。しかし実際には[' . func_num_args() .  ']個のパラメータを伴って呼び出されました。');
        }
        if (gettype($argCheck) !== 'string' && gettype($argCheck) !== 'NULL') {
            throw new \Exception('[ArgumentException]: SampleStringGroup.convertToInt の1番目のパラメータは[string]型でなくてはなりません。しかし実際には[' . gettype($argCheck) . ']型が与えられました。');
        }

        // No.1
        // 説明:アルファベットの文字列定義。
        if ('ABCDEFG' === $argCheck) {
            return self::ABCDEFG;
        }
        // No.2
        // 説明:全角の文字列定義。
        if ('あいうえお' === $argCheck) {
            return self::AIUEO;
        }
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if ('\'' === $argCheck) {
            return self::QUOTE;
        }
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if ('"' === $argCheck) {
            return self::DOUBLE_QUOTE;
        }
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if ('\\' === $argCheck) {
            return self::BACK_SLASH;
        }
        // No.7
        if ('説明を省略' === $argCheck) {
            return self::WITHOUT_DESC;
        }
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if ('ABC DEF' === $argCheck) {
            return self::TEST_SPACE;
        }
        // No.9
        // 説明:×マーク。
        if ('×' === $argCheck) {
            return self::BATU;
        }

        // No matching constants were found.
        return self::NOT_DEFINED;
    }
}
?>
