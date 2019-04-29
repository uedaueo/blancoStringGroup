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

        ''' <summary>未定義。文字列グループ以外の文字列または定数が未定義のもの。</summary>
        Public Const NOT_DEFINED As Integer = -1

        ''' <summary>文字列グループに含まれる文字列であるかどうかを判定します。</summary>
        ''' <param name="argCheck">チェックを行いたい文字列。</param>
        ''' <returns>文字列グループに含まれていればture。グループに含まれない文字列であればfalse。</returns>
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

        ''' <summary>文字列グループに含まれる文字列であるかどうかを、大文字小文字を区別せず判定します。</summary>
        ''' <param name="argCheck">チェックを行いたい文字列。</param>
        ''' <returns>文字列グループに含まれていればture。グループに含まれない文字列であればfalse。</returns>
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

        ''' <summary>文字列から定数に変換します。</summary>
        ''' <remarks>
        ''' <newpara>定数が未定義の場合や 与えられた文字列が文字列グループ外の場合には NOT_DEFINED を戻します。</newpara>
        ''' </remarks>
        ''' <param name="argCheck">変換を行いたい文字列。</param>
        ''' <returns>定数に変換後の値。</returns>
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

            ' 該当する定数が見つかりませんでした。
            Return NOT_DEFINED
        End Function
    End Class
End Namespace
