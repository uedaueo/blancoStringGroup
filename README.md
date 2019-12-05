# blancoSringGroup 上田版

このプロジェクトはblancoSringGroupの上田版です。

## maven 対応について

まだbuildは通りません。(2019/12/05) 

### jar ファイルの作成

buildが通らないのでjarファイルの作成もできません。antで作成したjarファイルをプロジェクトのルートディレクトリに、あらかじめコピーしておきます。

### deploy

maven リポジトリは github 上のpublicリポジトリに作成される前提としてます。

ただし現時点ではbuildが通らないので、compileやjarなど、直前のlifecycleをスキップする必要があります。

```
mvn deploy -Dmaven.main.skip=true -Dmaven.test.skip=true
```

### 独自mavenリポジトリ

独自mavenリポジトリを作成したい場合は以下の手順で。

* https://github.com/uedaueo/blancofw-maven2 を clone
* github から access_key を取得
* pom.xml のリポジトリURLをclone先に変更

~/.m2/settings.xml に以下のように記述することで、deploy可能となります。（useridとaccess_keyは実在のものをご使用下さい）

```~/.m2/settings.xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>userid</username>
      <password>access_key</password>
    </server>
  </servers>
</settings>
```
