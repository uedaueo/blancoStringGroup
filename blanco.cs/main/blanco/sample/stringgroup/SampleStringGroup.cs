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

        /// <summary>未定義。文字列グループ以外の文字列または定数が未定義のもの。</summary>
        public static readonly int NOT_DEFINED = -1;

        /// <summary>文字列グループに含まれる文字列であるかどうかを判定します。</summary>
        /// <param name="argCheck">チェックを行いたい文字列。</param>
        /// <returns>文字列グループに含まれていればture。グループに含まれない文字列であればfalse。</returns>
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

        /// <summary>文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。</summary>
        /// <param name="argCheck">チェックを行いたい文字列。</param>
        /// <returns>文字列グループに含まれていればture。グループに含まれない文字列であればfalse。</returns>
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

        /// <summary>文字列から定数に変換します。</summary>
        /// <remarks>
        /// <newpara>定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。</newpara>
        /// </remarks>
        /// <param name="argCheck">変換を行いたい文字列。</param>
        /// <returns>定数に変換後の値。</returns>
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

            // 該当する定数が見つかりませんでした。
            return NOT_DEFINED;
        }
    }
}
