package jp.co.maxa.com.context;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Data;

@Data
public class DataSourceConfig {

    private String driverClassName = "org.h2.Driver";
    private String url;
    private String username;
    private String password;
    private Long connectionTimeout;
    private Properties props = new Properties();
    /** 最低接続プーリング数 */
    private int minIdle = 2;
    /** 最大接続プーリング数 */
    private int maxPoolSize = 20;

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMinimumIdle(minIdle);
        config.setMaximumPoolSize(maxPoolSize);
        config.setDataSourceProperties(props);
        config.setConnectionTimeout(connectionTimeout);;
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
