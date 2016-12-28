// このソースコードは blanco Frameworkにより自動生成されました。
unit SampleStringGroup;

interface
    uses SysUtils;

type
    { 文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。 }
    TSampleStringGroup = class(TObject)

        { No.1 説明:アルファベットの文字列定義。 }
        const ABCDEFG = 1;

        { No.2 説明:全角の文字列定義。 }
        const AIUEO = 2;

        { No.3 説明:シングルクオートが展開されることの確認。 }
        const QUOTE = 3;

        { No.4 説明:ダブルクオートが展開されることの確認。 }
        const DOUBLE_QUOTE = 4;

        { No.5 説明:バックスラッシュが展開されることの確認。 }
        const BACK_SLASH = 5;

        { No.7  }
        const WITHOUT_DESC = 7;

        { No.8 説明:途中の空白が適切に処理されることの確認。 }
        const TEST_SPACE = 8;

        { No.9 説明:×マーク。 }
        const BATU = 9;

        { 未定義。文字列グループ以外の文字列または定数が未定義のもの。 }
        const NOT_DEFINED = -1;

        function match(argCheck: String): boolean;

        function matchIgnoreCase(argCheck: String): boolean;

        function convertToInt(argCheck: String): Integer;
    end;
implementation

    function TSampleStringGroup.match(argCheck: String): boolean;
    begin
        // No.1
        // 説明:アルファベットの文字列定義。
        if 'ABCDEFG' = argCheck then begin
            result := True;
            exit;
        end;
        // No.2
        // 説明:全角の文字列定義。
        if 'あいうえお' = argCheck then begin
            result := True;
            exit;
        end;
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if '''' = argCheck then begin
            result := True;
            exit;
        end;
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if '"' = argCheck then begin
            result := True;
            exit;
        end;
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if '\' = argCheck then begin
            result := True;
            exit;
        end;
        // No.6
        // 説明:定数が省略された定義。
        if 'STRING ONLY' = argCheck then begin
            result := True;
            exit;
        end;
        // No.7
        if '説明を省略' = argCheck then begin
            result := True;
            exit;
        end;
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF' = argCheck then begin
            result := True;
            exit;
        end;
        // No.9
        // 説明:×マーク。
        if '×' = argCheck then begin
            result := True;
            exit;
        end;
        result := False;
        exit;
    end;

    function TSampleStringGroup.matchIgnoreCase(argCheck: String): boolean;
    begin
        // No.1
        // 説明:アルファベットの文字列定義。
        if UpperCase('ABCDEFG') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.2
        // 説明:全角の文字列定義。
        if UpperCase('あいうえお') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if UpperCase('''') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if UpperCase('"') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if UpperCase('\') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.6
        // 説明:定数が省略された定義。
        if UpperCase('STRING ONLY') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.7
        if UpperCase('説明を省略') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if UpperCase('ABC DEF') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        // No.9
        // 説明:×マーク。
        if UpperCase('×') = UpperCase(argCheck) then begin
            result := True;
            exit;
        end;
        result := False;
        exit;
    end;

    function TSampleStringGroup.convertToInt(argCheck: String): Integer;
    begin
        // No.1
        // 説明:アルファベットの文字列定義。
        if 'ABCDEFG' = argCheck then begin
            result := ABCDEFG;
            exit;
        end;
        // No.2
        // 説明:全角の文字列定義。
        if 'あいうえお' = argCheck then begin
            result := AIUEO;
            exit;
        end;
        // No.3
        // 説明:シングルクオートが展開されることの確認。
        if '''' = argCheck then begin
            result := QUOTE;
            exit;
        end;
        // No.4
        // 説明:ダブルクオートが展開されることの確認。
        if '"' = argCheck then begin
            result := DOUBLE_QUOTE;
            exit;
        end;
        // No.5
        // 説明:バックスラッシュが展開されることの確認。
        if '\' = argCheck then begin
            result := BACK_SLASH;
            exit;
        end;
        // No.7
        if '説明を省略' = argCheck then begin
            result := WITHOUT_DESC;
            exit;
        end;
        // No.8
        // 説明:途中の空白が適切に処理されることの確認。
        if 'ABC DEF' = argCheck then begin
            result := TEST_SPACE;
            exit;
        end;
        // No.9
        // 説明:×マーク。
        if '×' = argCheck then begin
            result := BATU;
            exit;
        end;

        // 該当する定数が見つかりませんでした。
        result := NOT_DEFINED;
        exit;
    end;
end.

