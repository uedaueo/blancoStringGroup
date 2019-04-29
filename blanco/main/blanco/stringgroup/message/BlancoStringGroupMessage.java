/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.stringgroup.message;

/**
 * blancoStringGroupのなかで利用されるメッセージです。
 */
public class BlancoStringGroupMessage {
    /**
     * メッセージをプロパティファイル対応させるための内部的に利用するリソースバンドルクラス。
     */
    protected final BlancoStringGroupMessageResourceBundle fBundle = new BlancoStringGroupMessageResourceBundle();

    /**
     * メッセージ定義ID[BlancoStringGroup]、キー[MBSGI001]の文字列を取得します。
     *
     * No.2:
     * 文字列[文字列グループ定義ID[{0}]のパッケージ名が指定されていません。]
     *
     * @param arg0 置換文字列{0}の値。
     * @return メッセージ文字列。
     */
    public String getMbsgi001(final String arg0) {
        if (arg0 == null) {
            throw new IllegalArgumentException("メソッド[getMbsgi001]のパラメータ[arg0]にnullが与えられました。しかし、このパラメータにnullを与えることはできません。");
        }

        return "[MBSGI001] " + fBundle.getMbsgi001(arg0);
    }

    /**
     * メッセージ定義ID[BlancoStringGroup]、キー[MBSGI002]の文字列を取得します。
     *
     * No.3:
     * 文字列[サポートしない出力プログラミング言語処理系[{0}]が指定されました。]
     *
     * @param arg0 置換文字列{0}の値。
     * @return メッセージ文字列。
     */
    public String getMbsgi002(final String arg0) {
        if (arg0 == null) {
            throw new IllegalArgumentException("メソッド[getMbsgi002]のパラメータ[arg0]にnullが与えられました。しかし、このパラメータにnullを与えることはできません。");
        }

        return "[MBSGI002] " + fBundle.getMbsgi002(arg0);
    }

    /**
     * メッセージ定義ID[BlancoStringGroup]、キー[MBSGI003]の文字列を取得します。
     *
     * No.4:
     * 文字列[文字列グループ定義ID[{0}]において、文字列[{1}]が2回以上あらわれました。同じ値は2回以上指定できません。]
     *
     * @param arg0 置換文字列{0}の値。
     * @param arg1 置換文字列{1}の値。
     * @return メッセージ文字列。
     */
    public String getMbsgi003(final String arg0, final String arg1) {
        if (arg0 == null) {
            throw new IllegalArgumentException("メソッド[getMbsgi003]のパラメータ[arg0]にnullが与えられました。しかし、このパラメータにnullを与えることはできません。");
        }
        if (arg1 == null) {
            throw new IllegalArgumentException("メソッド[getMbsgi003]のパラメータ[arg1]にnullが与えられました。しかし、このパラメータにnullを与えることはできません。");
        }

        return "[MBSGI003] " + fBundle.getMbsgi003(arg0, arg1);
    }

    /**
     * メッセージ定義ID[BlancoStringGroup]、キー[MBSGA001]の文字列を取得します。
     *
     * No.7:
     * 文字列[メタディレクトリ[{0}]が存在しません。]
     *
     * @param arg0 置換文字列{0}の値。
     * @return メッセージ文字列。
     */
    public String getMbsga001(final String arg0) {
        if (arg0 == null) {
            throw new IllegalArgumentException("メソッド[getMbsga001]のパラメータ[arg0]にnullが与えられました。しかし、このパラメータにnullを与えることはできません。");
        }

        return "[MBSGA001] " + fBundle.getMbsga001(arg0);
    }
}
