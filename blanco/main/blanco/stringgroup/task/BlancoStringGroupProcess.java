/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.stringgroup.task;

import java.io.IOException;

import blanco.stringgroup.task.valueobject.BlancoStringGroupProcessInput;

/**
 * 処理 [BlancoStringGroupProcess]インタフェース。
 *
 * このインタフェースを継承して [blanco.stringgroup.task]パッケージに[BlancoStringGroupProcess]クラスを作成して実際のバッチ処理を実装してください。<br>
 */
interface BlancoStringGroupProcess {
    /**
     * クラスをインスタンス化して処理を実行する際のエントリポイントです。
     *
     * @param input 処理の入力パラメータ。
     * @return 処理の実行結果。
     * @throws IOException 入出力例外が発生した場合。
     * @throws IllegalArgumentException 入力値に不正が見つかった場合。
     */
    int execute(final BlancoStringGroupProcessInput input) throws IOException, IllegalArgumentException;
}
