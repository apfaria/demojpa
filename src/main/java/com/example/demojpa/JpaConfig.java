package com.example.demojpa;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    /**
     * @return DataSource
     */
    @Bean
    public DataSource getDataSource() {
        try {
            final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName("org.postgresql.Driver");
            String plUrl = "jdbc:postgresql://localhost:5432/atendimento";
            dataSourceBuilder.url(plUrl);
            dataSourceBuilder.username("postgres");
            dataSourceBuilder.password("010203");
            return dataSourceBuilder.build();
        } catch (Exception e) {

        }
        return null;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan(new String[] { "com.example.demojpa" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getVendorProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    protected Properties getVendorProperties() {
        Properties properties = new Properties();
        properties.setProperty("spring.jpa.show-sql", "true");
        properties.setProperty("spring.jpa.hibernate.ddl-auto", "update");
        properties.setProperty("spring.jpa.hibernate.naming.implicit-strategy",
                "org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl");
        properties.setProperty("spring.jpa.properties.hibernate.format_sql", "true");
        properties.setProperty("spring.jpa.properties.hibernate.show_sql", "true");
        properties.setProperty("spring.jpa.properties.hibernate.enable_lazy_load_no_trans", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        return properties;
    }

}
