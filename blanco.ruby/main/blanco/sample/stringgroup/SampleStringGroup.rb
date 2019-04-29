#
# このソースコードは blanco Frameworkにより自動生成されました。
#

#
# 文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。
#
class SampleStringGroup

    #
    # No.1 説明:アルファベットの文字列定義。
    #
    ABCDEFG = 1

    #
    # No.2 説明:全角の文字列定義。
    #
    AIUEO = 2

    #
    # No.3 説明:シングルクオートが展開されることの確認。
    #
    QUOTE = 3

    #
    # No.4 説明:ダブルクオートが展開されることの確認。
    #
    DOUBLE_QUOTE = 4

    #
    # No.5 説明:バックスラッシュが展開されることの確認。
    #
    BACK_SLASH = 5

    #
    # No.7
    #
    WITHOUT_DESC = 7

    #
    # No.8 説明:途中の空白が適切に処理されることの確認。
    #
    TEST_SPACE = 8

    #
    # No.9 説明:×マーク。
    #
    BATU = 9

    #
    # 未定義。文字列グループ以外の文字列または定数が未定義のもの。
    #
    NOT_DEFINED = -1

    #
    # 文字列グループに含まれる文字列であるかどうかを判定します。
    #
    # param argCheck チェックを行いたい文字列。
    # return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
    #
    def match(argCheck)
        # No.1
        # 説明:アルファベットの文字列定義。
        if 'ABCDEFG' == argCheck
            return true
        end
        # No.2
        # 説明:全角の文字列定義。
        if 'あいうえお' == argCheck
            return true
        end
        # No.3
        # 説明:シングルクオートが展開されることの確認。
        if '\'' == argCheck
            return true
        end
        # No.4
        # 説明:ダブルクオートが展開されることの確認。
        if '"' == argCheck
            return true
        end
        # No.5
        # 説明:バックスラッシュが展開されることの確認。
        if '\\' == argCheck
            return true
        end
        # No.6
        # 説明:定数が省略された定義。
        if 'STRING ONLY' == argCheck
            return true
        end
        # No.7
        if '説明を省略' == argCheck
            return true
        end
        # No.8
        # 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF' == argCheck
            return true
        end
        # No.9
        # 説明:×マーク。
        if '×' == argCheck
            return true
        end
        return false
    end

    #
    # 文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。
    #
    # param argCheck チェックを行いたい文字列。
    # return 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
    #
    def matchIgnoreCase(argCheck)
        # No.1
        # 説明:アルファベットの文字列定義。
        if 'ABCDEFG'.upcase() == argCheck.upcase()
            return true
        end
        # No.2
        # 説明:全角の文字列定義。
        if 'あいうえお'.upcase() == argCheck.upcase()
            return true
        end
        # No.3
        # 説明:シングルクオートが展開されることの確認。
        if '\''.upcase() == argCheck.upcase()
            return true
        end
        # No.4
        # 説明:ダブルクオートが展開されることの確認。
        if '"'.upcase() == argCheck.upcase()
            return true
        end
        # No.5
        # 説明:バックスラッシュが展開されることの確認。
        if '\\'.upcase() == argCheck.upcase()
            return true
        end
        # No.6
        # 説明:定数が省略された定義。
        if 'STRING ONLY'.upcase() == argCheck.upcase()
            return true
        end
        # No.7
        if '説明を省略'.upcase() == argCheck.upcase()
            return true
        end
        # No.8
        # 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF'.upcase() == argCheck.upcase()
            return true
        end
        # No.9
        # 説明:×マーク。
        if '×'.upcase() == argCheck.upcase()
            return true
        end
        return false
    end

    #
    # 文字列から定数に変換します。
    #
    # 定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。
    #
    # param argCheck 変換を行いたい文字列。
    # return 定数に変換後の値。
    #
    def convertToInt(argCheck)
        # No.1
        # 説明:アルファベットの文字列定義。
        if 'ABCDEFG' == argCheck
            return ABCDEFG
        end
        # No.2
        # 説明:全角の文字列定義。
        if 'あいうえお' == argCheck
            return AIUEO
        end
        # No.3
        # 説明:シングルクオートが展開されることの確認。
        if '\'' == argCheck
            return QUOTE
        end
        # No.4
        # 説明:ダブルクオートが展開されることの確認。
        if '"' == argCheck
            return DOUBLE_QUOTE
        end
        # No.5
        # 説明:バックスラッシュが展開されることの確認。
        if '\\' == argCheck
            return BACK_SLASH
        end
        # No.7
        if '説明を省略' == argCheck
            return WITHOUT_DESC
        end
        # No.8
        # 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF' == argCheck
            return TEST_SPACE
        end
        # No.9
        # 説明:×マーク。
        if '×' == argCheck
            return BATU
        end

        # 該当する定数が見つかりませんでした。
        return NOT_DEFINED
    end
end
