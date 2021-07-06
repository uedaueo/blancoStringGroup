// このソースコードは blanco Frameworkにより自動生成されました。
unit SampleStringGroup;

interface
    uses SysUtils;

type
    // 文字列グループのサンプル。このクラスは単にサンプルです。実際の動作には利用されません。
    TSampleStringGroup = class(TObject)

    public

        // No.1 説明:アルファベットの文字列定義。
        const ABCDEFG: Integer;

        // No.2 説明:全角の文字列定義。
        const AIUEO: Integer;

        // No.3 説明:シングルクオートが展開されることの確認。
        const QUOTE: Integer;

        // No.4 説明:ダブルクオートが展開されることの確認。
        const DOUBLE_QUOTE: Integer;

        // No.5 説明:バックスラッシュが展開されることの確認。
        const BACK_SLASH: Integer;

        // No.7
        const WITHOUT_DESC: Integer;

        // No.8 説明:途中の空白が適切に処理されることの確認。
        const TEST_SPACE: Integer;

        // No.9 説明:×マーク。
        const BATU: Integer;

        // Undefined. A string or constant other than a string group that is undefined.
        const NOT_DEFINED: Integer;

    public

        function match(argCheck: java.lang.String): boolean;

        function matchIgnoreCase(argCheck: java.lang.String): boolean;

        function convertToInt(argCheck: java.lang.String): Integer;
    end;
implementation

    function TSampleStringGroup.match(argCheck: java.lang.String): boolean;
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

    function TSampleStringGroup.matchIgnoreCase(argCheck: java.lang.String): boolean;
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

    function TSampleStringGroup.convertToInt(argCheck: java.lang.String): Integer;
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

        // No matching constants were found.
        result := NOT_DEFINED;
        exit;
    end;
end.

