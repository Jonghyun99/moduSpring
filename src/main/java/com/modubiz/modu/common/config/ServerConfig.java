package com.modubiz.modu.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.time.Duration;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.modubiz.modu")
@MapperScan(basePackages = "com.modubiz.modu.kona.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class ServerConfig implements WebMvcConfigurer {

    @Value("${mybatis.mapper-locations}")
    private String mybatisLocation;

    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

//    @Value("${server.port.http}")
//    private int serverPortHttp;
//
//    @Bean
//    public ServletWebServerFactory serverFactory() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/callback/kona/user/unregister");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(getHttpConnector());
//        return tomcat;
//    }
//
//    private Connector getHttpConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(serverPortHttp);
//        connector.setSecure(false);
//        return connector;
//    }

    @Bean(name = "DataSource")
    @Primary
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources(mybatisLocation);
        sqlSessionFactoryBean.setMapperLocations(arrResource);
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.getObject().getConfiguration().setCallSettersOnNulls(true);
        sqlSessionFactoryBean.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);

        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "sqlSession")
    @Primary
    public SqlSession sqlSession(@Autowired @Qualifier("sqlSessionFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
            .setConnectTimeout(Duration.ofMillis(5000)) // connection-timeout
            .setReadTimeout(Duration.ofMillis(5000)) // read-timeout
            .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
            .build();
    }
}
