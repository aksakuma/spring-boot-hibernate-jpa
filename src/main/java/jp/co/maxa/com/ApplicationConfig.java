package jp.co.maxa.com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * アプリケーションにおけるBean定義を表現します。
 * <p>クラス側でコンポーネント定義していない時はこちらで明示的に記載してください。
 */
@Configuration
public class ApplicationConfig {

    /** SpringMvcの拡張コンフィギュレーション */
    @Configuration
    public static class WebMvcConfig extends WebMvcConfigurerAdapter {
        /** HibernateのLazyLoading回避対応。  see JacksonAutoConfiguration */
        @Bean
        public Hibernate5Module jsonHibernate5Module() {
            return new Hibernate5Module();
        }
    }
}
