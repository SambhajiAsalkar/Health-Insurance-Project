package com.nt.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.nt.repository.prod", 
    entityManagerFactoryRef = "oraEMF", 
    transactionManagerRef = "oraTxMgmr"
)
public class OracleDBConfig {

    @Bean(name = "OraDs")
    @ConfigurationProperties(prefix = "oracle.datasource")
    @Primary
    public DataSource createOraDs() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oraEMF")
    @Primary
    public LocalContainerEntityManagerFactoryBean createOraEMF(DataSource createOraDs) {
        // Configure Hibernate properties
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");  // Updated to Oracle 12c dialect
        props.put("hibernate.hbm2ddl.auto", "update"); // Or "validate" for production
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true"); // Optional for formatted SQL logging
        props.put("hibernate.jdbc.batch_size", "20");
        props.put("hibernate.jdbc.fetch_size", "50");
        props.put("hibernate.jdbc.lob.non_contextual_creation", "true");

        // Create and configure the EntityManagerFactory bean
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(createOraDs); // Pass the DataSource directly
        entityManagerFactoryBean.setPackagesToScan("com.nt.model.prod"); // Your package for entity classes
        entityManagerFactoryBean.setJpaPropertyMap(props); // Hibernate properties
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter); // Set the JPA vendor adapter

        // Log when the bean is created for debugging purposes (optional)
        System.out.println("Oracle EntityManagerFactory bean is being created...");

        return entityManagerFactoryBean;
    }

    @Bean(name = "oraTxMgmr")
    @Primary
    public JpaTransactionManager createOraTxMgmr(@Qualifier("oraEMF") EntityManagerFactory factory) {
        // Create the JpaTransactionManager bean and inject the EntityManagerFactory
        return new JpaTransactionManager(factory);
    }
}
