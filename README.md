# 基盤(ライブラリ)
* SpringBoot
* Hibernate(JPA)
* Gradle
* lombok
* H2 Database

# 説明
* 3層モデルを採用しています。
* Repositoryとエンティティは「1対N」になるようにしています。
* ローカル起動時は、HibernateでDDLを流して、H2DB上に環境を構築しています。
* RepositoryへのアクセスにはHibernateTemplateをラッピングしたものを使用します。

# 使い方
* git clone https://github.com/aksakuma/spring-boot-hibernate-jpa.git
* 本ディレクトリに移動
* gradle build
* java -jar build/libs/a.sakuma-0.0.1-SNAPSHOT.jar
* http://localhost:8080/api/master/getItemsにアクセス
