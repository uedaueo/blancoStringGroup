# -*- coding: cp932 -*-
"""このソースコードは blanco Frameworkにより自動生成されました。
"""
class SampleStringGroup:
    """文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。
    """

    """No.1 説明:アルファベットの文字列定義。
    """
    ABCDEFG = 1

    """No.2 説明:全角の文字列定義。
    """
    AIUEO = 2

    """No.3 説明:シングルクオートが展開されることの確認。
    """
    QUOTE = 3

    """No.4 説明:ダブルクオートが展開されることの確認。
    """
    DOUBLE_QUOTE = 4

    """No.5 説明:バックスラッシュが展開されることの確認。
    """
    BACK_SLASH = 5

    """No.7
    """
    WITHOUT_DESC = 7

    """No.8 説明:途中の空白が適切に処理されることの確認。
    """
    TEST_SPACE = 8

    """No.9 説明:×マーク。
    """
    BATU = 9

    """未定義。文字列グループ以外の文字列または定数が未定義のもの。
    """
    NOT_DEFINED = -1

    def match(self, argCheck):
        """文字列グループに含まれる文字列であるかどうかを判定します。

        self -- このメソッドを含むクラス自身。
        argCheck -- チェックを行いたい文字列。
        return -- 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
        """
        # No.1
        # 説明:アルファベットの文字列定義。
        if 'ABCDEFG' == argCheck:
            return True
        #end
        # No.2
        # 説明:全角の文字列定義。
        if 'あいうえお' == argCheck:
            return True
        #end
        # No.3
        # 説明:シングルクオートが展開されることの確認。
        if '\'' == argCheck:
            return True
        #end
        # No.4
        # 説明:ダブルクオートが展開されることの確認。
        if '"' == argCheck:
            return True
        #end
        # No.5
        # 説明:バックスラッシュが展開されることの確認。
        if '\\' == argCheck:
            return True
        #end
        # No.6
        # 説明:定数が省略された定義。
        if 'STRING ONLY' == argCheck:
            return True
        #end
        # No.7
        if '説明を省略' == argCheck:
            return True
        #end
        # No.8
        # 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF' == argCheck:
            return True
        #end
        # No.9
        # 説明:×マーク。
        if '×' == argCheck:
            return True
        #end
        return False
    #end

    def matchIgnoreCase(self, argCheck):
        """文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。

        self -- このメソッドを含むクラス自身。
        argCheck -- チェックを行いたい文字列。
        return -- 文字列グループに含まれていればture。グループに含まれない文字列であればfalse。
        """
        # No.1
        # 説明:アルファベットの文字列定義。
        if 'ABCDEFG'.upper() == argCheck.upper():
            return True
        #end
        # No.2
        # 説明:全角の文字列定義。
        if 'あいうえお'.upper() == argCheck.upper():
            return True
        #end
        # No.3
        # 説明:シングルクオートが展開されることの確認。
        if '\''.upper() == argCheck.upper():
            return True
        #end
        # No.4
        # 説明:ダブルクオートが展開されることの確認。
        if '"'.upper() == argCheck.upper():
            return True
        #end
        # No.5
        # 説明:バックスラッシュが展開されることの確認。
        if '\\'.upper() == argCheck.upper():
            return True
        #end
        # No.6
        # 説明:定数が省略された定義。
        if 'STRING ONLY'.upper() == argCheck.upper():
            return True
        #end
        # No.7
        if '説明を省略'.upper() == argCheck.upper():
            return True
        #end
        # No.8
        # 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF'.upper() == argCheck.upper():
            return True
        #end
        # No.9
        # 説明:×マーク。
        if '×'.upper() == argCheck.upper():
            return True
        #end
        return False
    #end

    def convertToInt(self, argCheck):
        """文字列から定数に変換します。

        定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。

        self -- このメソッドを含むクラス自身。
        argCheck -- 変換を行いたい文字列。
        return -- 定数に変換後の値。
        """
        # No.1
        # 説明:アルファベットの文字列定義。
        if 'ABCDEFG' == argCheck:
            return self.ABCDEFG
        #end
        # No.2
        # 説明:全角の文字列定義。
        if 'あいうえお' == argCheck:
            return self.AIUEO
        #end
        # No.3
        # 説明:シングルクオートが展開されることの確認。
        if '\'' == argCheck:
            return self.QUOTE
        #end
        # No.4
        # 説明:ダブルクオートが展開されることの確認。
        if '"' == argCheck:
            return self.DOUBLE_QUOTE
        #end
        # No.5
        # 説明:バックスラッシュが展開されることの確認。
        if '\\' == argCheck:
            return self.BACK_SLASH
        #end
        # No.7
        if '説明を省略' == argCheck:
            return self.WITHOUT_DESC
        #end
        # No.8
        # 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF' == argCheck:
            return self.TEST_SPACE
        #end
        # No.9
        # 説明:×マーク。
        if '×' == argCheck:
            return self.BATU
        #end

        # 該当する定数が見つかりませんでした。
        return self.NOT_DEFINED
    #end
#end
