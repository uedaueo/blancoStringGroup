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
    # Undefined. A string or constant other than a string group that is undefined.
    #
    NOT_DEFINED = -1

    #
    # Determines if a string is part of a string group.
        #
        # param argCheck A string to be checked.
        # return true is the string is part of a string group, false otherwise.
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
        # Determines if a string is part of a string group in a case-insentive manner.
            #
            # param argCheck A string to be checked.
            # return true is the string is part of a string group, false otherwise.
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
            # Converts a string to a constant.
            #
            # Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.
                #
                # param argCheck A string to be converted.
                # return The value after conversion to a constant.
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

                    # No matching constants were found.
                    return NOT_DEFINED
                end
            end
