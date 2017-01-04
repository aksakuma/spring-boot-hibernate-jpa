package jp.co.maxa.com.context;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import lombok.Setter;

@org.springframework.stereotype.Repository
@Setter
public class DefaultRepository extends AbstractRepository {
    public static final String BEAN_DATA_SOURCE = "dataSource";
    public static final String BEAN_SESSION_FACTORY = "sessionFactory";
    public static final String BEAN_TRANSACTION_MANAGER = "TransactionManager";

    @Autowired
    @Qualifier(BEAN_SESSION_FACTORY)
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory sessionFactory(){
        return sessionFactory;
    }

    @ConfigurationProperties(prefix = "extension.hibernate.default")
    public static class DefaultRepositoryConfig extends RepositoryConfig{
        @Bean(name = BEAN_SESSION_FACTORY)
        @Override
        public LocalSessionFactoryBean sessionFactory(
            @Qualifier(BEAN_DATA_SOURCE) final DataSource dataSource){
            return super.sessionFactory(dataSource);
        }

        @Bean(name = BEAN_TRANSACTION_MANAGER)
        @Override
        public HibernateTransactionManager transactionManager(
                @Qualifier(BEAN_SESSION_FACTORY) final SessionFactory sessionFactory) {
            return super.transactionManager(sessionFactory);
        }
    }

    @ConfigurationProperties(prefix = "extension.datasource.default")
    public static class RepositoryDataSourceConfig extends  DataSourceConfig {
    	@Bean(name = BEAN_DATA_SOURCE, destroyMethod = "shutdown")
    	public DataSource dataSource() {
    		return super.dataSource();
    	}
    }

}
