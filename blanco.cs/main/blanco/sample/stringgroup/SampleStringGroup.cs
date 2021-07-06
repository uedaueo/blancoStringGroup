// このソースコードは blanco Frameworkにより自動生成されました。
using System;

namespace blanco.sample.stringgroup
{
    /// <summary>文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。</summary>
    public class SampleStringGroup
    {
        /// <summary>No.1 説明:アルファベットの文字列定義。</summary>
        public static readonly int ABCDEFG = 1;

        /// <summary>No.2 説明:全角の文字列定義。</summary>
        public static readonly int AIUEO = 2;

        /// <summary>No.3 説明:シングルクオートが展開されることの確認。</summary>
        public static readonly int QUOTE = 3;

        /// <summary>No.4 説明:ダブルクオートが展開されることの確認。</summary>
        public static readonly int DOUBLE_QUOTE = 4;

        /// <summary>No.5 説明:バックスラッシュが展開されることの確認。</summary>
        public static readonly int BACK_SLASH = 5;

        /// <summary>No.7 </summary>
        public static readonly int WITHOUT_DESC = 7;

        /// <summary>No.8 説明:途中の空白が適切に処理されることの確認。</summary>
        public static readonly int TEST_SPACE = 8;

        /// <summary>No.9 説明:×マーク。</summary>
        public static readonly int BATU = 9;

        /// <summary>Undefined. A string or constant other than a string group that is undefined.</summary>
        public static readonly int NOT_DEFINED = -1;

        /// <summary>Determines if a string is part of a string group.</summary>
        /// <param name="argCheck">A string to be checked.</param>
        /// <returns>true is the string is part of a string group, false otherwise.</returns>
        public virtual bool Match(string argCheck)
        {
            // No.1
            // 説明:アルファベットの文字列定義。
            if ("ABCDEFG".Equals(argCheck)) {
                return true;
            }
            // No.2
            // 説明:全角の文字列定義。
            if ("あいうえお".Equals(argCheck)) {
                return true;
            }
            // No.3
            // 説明:シングルクオートが展開されることの確認。
            if ("'".Equals(argCheck)) {
                return true;
            }
            // No.4
            // 説明:ダブルクオートが展開されることの確認。
            if ("\"".Equals(argCheck)) {
                return true;
            }
            // No.5
            // 説明:バックスラッシュが展開されることの確認。
            if ("\\".Equals(argCheck)) {
                return true;
            }
            // No.6
            // 説明:定数が省略された定義。
            if ("STRING ONLY".Equals(argCheck)) {
                return true;
            }
            // No.7
            if ("説明を省略".Equals(argCheck)) {
                return true;
            }
            // No.8
            // 説明:途中の空白が適切に処理されることの確認。
            if ("ABC DEF".Equals(argCheck)) {
                return true;
            }
            // No.9
            // 説明:×マーク。
            if ("×".Equals(argCheck)) {
                return true;
            }
            return false;
        }

        /// <summary>Determines if a string is part of a string group in a case-insentive manner.</summary>
        /// <param name="argCheck">A string to be checked.</param>
        /// <returns>true is the string is part of a string group, false otherwise.</returns>
        public virtual bool MatchIgnoreCase(string argCheck)
        {
            // No.1
            // 説明:アルファベットの文字列定義。
            if ("ABCDEFG".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.2
            // 説明:全角の文字列定義。
            if ("あいうえお".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.3
            // 説明:シングルクオートが展開されることの確認。
            if ("'".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.4
            // 説明:ダブルクオートが展開されることの確認。
            if ("\"".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.5
            // 説明:バックスラッシュが展開されることの確認。
            if ("\\".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.6
            // 説明:定数が省略された定義。
            if ("STRING ONLY".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.7
            if ("説明を省略".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.8
            // 説明:途中の空白が適切に処理されることの確認。
            if ("ABC DEF".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            // No.9
            // 説明:×マーク。
            if ("×".Equals(argCheck, StringComparison.CurrentCultureIgnoreCase)) {
                return true;
            }
            return false;
        }

        /// <summary>Converts a string to a constant.</summary>
        /// <remarks>
        /// <newpara>Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.</newpara>
        /// </remarks>
        /// <param name="argCheck">A string to be converted.</param>
        /// <returns>The value after conversion to a constant.</returns>
        public virtual int ConvertToInt(string argCheck)
        {
            // No.1
            // 説明:アルファベットの文字列定義。
            if ("ABCDEFG".Equals(argCheck)) {
                return ABCDEFG;
            }
            // No.2
            // 説明:全角の文字列定義。
            if ("あいうえお".Equals(argCheck)) {
                return AIUEO;
            }
            // No.3
            // 説明:シングルクオートが展開されることの確認。
            if ("'".Equals(argCheck)) {
                return QUOTE;
            }
            // No.4
            // 説明:ダブルクオートが展開されることの確認。
            if ("\"".Equals(argCheck)) {
                return DOUBLE_QUOTE;
            }
            // No.5
            // 説明:バックスラッシュが展開されることの確認。
            if ("\\".Equals(argCheck)) {
                return BACK_SLASH;
            }
            // No.7
            if ("説明を省略".Equals(argCheck)) {
                return WITHOUT_DESC;
            }
            // No.8
            // 説明:途中の空白が適切に処理されることの確認。
            if ("ABC DEF".Equals(argCheck)) {
                return TEST_SPACE;
            }
            // No.9
            // 説明:×マーク。
            if ("×".Equals(argCheck)) {
                return BATU;
            }

            // No matching constants were found.
            return NOT_DEFINED;
        }
    }
}
