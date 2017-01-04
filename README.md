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
