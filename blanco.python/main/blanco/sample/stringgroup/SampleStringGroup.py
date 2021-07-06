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

    """Undefined. A string or constant other than a string group that is undefined.
    """
    NOT_DEFINED = -1

    def match(self, argCheck):
        """Determines if a string is part of a string group.

            self -- このメソッドを含むクラス自身。
            argCheck -- A string to be checked.
            return -- true is the string is part of a string group, false otherwise.
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
            """Determines if a string is part of a string group in a case-insentive manner.

                self -- このメソッドを含むクラス自身。
                argCheck -- A string to be checked.
                return -- true is the string is part of a string group, false otherwise.
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
                """Converts a string to a constant.

                Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.

                    self -- このメソッドを含むクラス自身。
                    argCheck -- A string to be converted.
                    return -- The value after conversion to a constant.
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

                    # No matching constants were found.
                    return self.NOT_DEFINED
                #end
            #end
