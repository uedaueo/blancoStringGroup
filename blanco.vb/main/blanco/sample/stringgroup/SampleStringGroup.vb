' このソースコードは blanco Frameworkにより自動生成されました。
Namespace blanco.sample.stringgroup
    ''' <summary>文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。</summary>
    Public Class SampleStringGroup

        ''' <summary>No.1 説明:アルファベットの文字列定義。</summary>
        Public Const ABCDEFG As Integer = 1

        ''' <summary>No.2 説明:全角の文字列定義。</summary>
        Public Const AIUEO As Integer = 2

        ''' <summary>No.3 説明:シングルクオートが展開されることの確認。</summary>
        Public Const QUOTE As Integer = 3

        ''' <summary>No.4 説明:ダブルクオートが展開されることの確認。</summary>
        Public Const DOUBLE_QUOTE As Integer = 4

        ''' <summary>No.5 説明:バックスラッシュが展開されることの確認。</summary>
        Public Const BACK_SLASH As Integer = 5

        ''' <summary>No.7 </summary>
        Public Const WITHOUT_DESC As Integer = 7

        ''' <summary>No.8 説明:途中の空白が適切に処理されることの確認。</summary>
        Public Const TEST_SPACE As Integer = 8

        ''' <summary>No.9 説明:×マーク。</summary>
        Public Const BATU As Integer = 9

        ''' <summary>Undefined. A string or constant other than a string group that is undefined.</summary>
        Public Const NOT_DEFINED As Integer = -1

        ''' <summary>Determines if a string is part of a string group.</summary>
        ''' <param name="argCheck">A string to be checked.</param>
        ''' <returns>true is the string is part of a string group, false otherwise.</returns>
        Public Function Match(argCheck As String) As Boolean
            ' No.1
            ' 説明:アルファベットの文字列定義。
            If ("ABCDEFG" = argCheck) Then
                Return true
            End If
            ' No.2
            ' 説明:全角の文字列定義。
            If ("あいうえお" = argCheck) Then
                Return true
            End If
            ' No.3
            ' 説明:シングルクオートが展開されることの確認。
            If ("'" = argCheck) Then
                Return true
            End If
            ' No.4
            ' 説明:ダブルクオートが展開されることの確認。
            If ("""" = argCheck) Then
                Return true
            End If
            ' No.5
            ' 説明:バックスラッシュが展開されることの確認。
            If ("\\" = argCheck) Then
                Return true
            End If
            ' No.6
            ' 説明:定数が省略された定義。
            If ("STRING ONLY" = argCheck) Then
                Return true
            End If
            ' No.7
            If ("説明を省略" = argCheck) Then
                Return true
            End If
            ' No.8
            ' 説明:途中の空白が適切に処理されることの確認。
            If ("ABC DEF" = argCheck) Then
                Return true
            End If
            ' No.9
            ' 説明:×マーク。
            If ("×" = argCheck) Then
                Return true
            End If
            Return false
        End Function

        ''' <summary>Determines if a string is part of a string group in a case-insentive manner.</summary>
        ''' <param name="argCheck">A string to be checked.</param>
        ''' <returns>true is the string is part of a string group, false otherwise.</returns>
        Public Function MatchIgnoreCase(argCheck As String) As Boolean
            ' No.1
            ' 説明:アルファベットの文字列定義。
            If ("ABCDEFG".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.2
            ' 説明:全角の文字列定義。
            If ("あいうえお".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.3
            ' 説明:シングルクオートが展開されることの確認。
            If ("'".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.4
            ' 説明:ダブルクオートが展開されることの確認。
            If ("""".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.5
            ' 説明:バックスラッシュが展開されることの確認。
            If ("\\".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.6
            ' 説明:定数が省略された定義。
            If ("STRING ONLY".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.7
            If ("説明を省略".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.8
            ' 説明:途中の空白が適切に処理されることの確認。
            If ("ABC DEF".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            ' No.9
            ' 説明:×マーク。
            If ("×".ToUpper() = argCheck.ToUpper()) Then
                Return true
            End If
            Return false
        End Function

        ''' <summary>Converts a string to a constant.</summary>
        ''' <remarks>
        ''' <newpara>Returns NOT_DEFINED if the constant is undefined or if the given string is outside the string group.</newpara>
        ''' </remarks>
        ''' <param name="argCheck">A string to be converted.</param>
        ''' <returns>The value after conversion to a constant.</returns>
        Public Function ConvertToInt(argCheck As String) As Integer
            ' No.1
            ' 説明:アルファベットの文字列定義。
            If ("ABCDEFG" = argCheck) Then
                Return ABCDEFG
            End If
            ' No.2
            ' 説明:全角の文字列定義。
            If ("あいうえお" = argCheck) Then
                Return AIUEO
            End If
            ' No.3
            ' 説明:シングルクオートが展開されることの確認。
            If ("'" = argCheck) Then
                Return QUOTE
            End If
            ' No.4
            ' 説明:ダブルクオートが展開されることの確認。
            If ("""" = argCheck) Then
                Return DOUBLE_QUOTE
            End If
            ' No.5
            ' 説明:バックスラッシュが展開されることの確認。
            If ("\\" = argCheck) Then
                Return BACK_SLASH
            End If
            ' No.7
            If ("説明を省略" = argCheck) Then
                Return WITHOUT_DESC
            End If
            ' No.8
            ' 説明:途中の空白が適切に処理されることの確認。
            If ("ABC DEF" = argCheck) Then
                Return TEST_SPACE
            End If
            ' No.9
            ' 説明:×マーク。
            If ("×" = argCheck) Then
                Return BATU
            End If

            ' No matching constants were found.
            Return NOT_DEFINED
        End Function
    End Class
End Namespace
