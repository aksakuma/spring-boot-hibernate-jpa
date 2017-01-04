package jp.co.maxa.com.context;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import lombok.Data;
import lombok.Setter;

@Setter
public abstract class AbstractRepository implements Repository {

    public OrmTemplate tmpl() {
        return new OrmTemplate(sessionFactory());
    }

    public abstract SessionFactory sessionFactory();

    @Override
    public <T extends Entity> Optional<T> get(Class<T> clazz, Serializable id) {
        return Optional.ofNullable(tmpl().ht().get(clazz, id));
    }

    @Override
    public <T extends Entity> T load(Class<T> clazz, Serializable id) {
        try {
            T m = tmpl().ht().load(clazz, id);
            m.hashCode(); // force loading
            return m;
        } catch (HibernateObjectRetrievalFailureException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public <T extends Entity> List<T> findAll(Class<T> clazz) {
        return tmpl().ht().loadAll(clazz);
    }

    @Override
    public <T extends Entity> T regist(T entity) {
        tmpl().ht().save(entity);
        return entity;
    }

    @Override
    public <T extends Entity> T update(T entity) {
        return tmpl().ht().merge(entity);
    }

    @Override
    public <T extends Entity> T delete(T entity) {
        tmpl().ht().delete(entity);
        return entity;
    }

    @Data
    public static class RepositoryConfig {
    	/** 接続するDBのDialect */
        private String dialect = "org.hibernate.dialect.H2Dialect";
        /** スキーマ紐付け対象とするパッケージ。(annotatedClassesとどちらかを設定) */
        private String packagesToScan;
        /** SQLログ表示 */
        private boolean showSql;
        /** DDL自動生成 */
        private boolean createDrop;
        /** クエリ置換設定 */
        private String substitutions = "true 1, false 0";

    	public LocalSessionFactoryBean sessionFactory(final DataSource dataSource) {
            LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setPackagesToScan(packagesToScan != null ? packagesToScan.split(",") : null);
            bean.setHibernateProperties(hibernateProperties());
            bean.setPhysicalNamingStrategy(new OrmNamingStrategy());
            bean.setImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE);
            return bean;
        }

    	public Properties hibernateProperties(){
    		Properties prop = new Properties();
            prop.put("hibernate.dialect", dialect);
            prop.put("hibernate.show_sql", showSql);
            prop.put("hibernate.query.substitutions", substitutions);
            prop.put("hibernate.hbm2ddl.auto", createDrop ? "create-drop" : "validate");
            return prop;
    	}

    	public HibernateTransactionManager transactionManager(
                final SessionFactory sessionFactory) {
            return new HibernateTransactionManager(sessionFactory);
        }
    }

    /** Hibernate5用のPhysicalNamingStrategy。 */
    protected static class OrmNamingStrategy implements PhysicalNamingStrategy {
        private SpringNamingStrategy strategy = new SpringNamingStrategy();

        @Override
        public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnv) {
            return convert(identifier);
        }

        @Override
        public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnv) {
            return convert(identifier);
        }

        @Override
        public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnv) {
            return convert(identifier);
        }

        @Override
        public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnv) {
            return convert(identifier);
        }

        @Override
        public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnv) {
            return convert(identifier);
        }

        private Identifier convert(Identifier identifier) {
            if (identifier == null || StringUtils.isBlank(identifier.getText())) {
                return identifier;
            }
            return Identifier.toIdentifier(strategy.tableName(identifier.getText()));
        }
    }
}
