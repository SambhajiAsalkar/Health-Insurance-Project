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
@EnableJpaRepositories(basePackages = "com.nt.repository.promotions",entityManagerFactoryRef="mySqlEMF",transactionManagerRef="mySqlTxMgmr")
public class MySqlDBConfig {
	
	
	
    @Bean(name = "mySqlDs")
    @ConfigurationProperties(prefix = "mysql.datasource")
    
   public DataSource createMySqlDs() 
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="mySqlEMF")
	
	public LocalContainerEntityManagerFactoryBean createMySqlEMF(DataSource createMySqlDs) {
		   Map<String, String> props = new HashMap<>();
		    props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		    props.put("hibernate.hbm2ddl.auto", "update");
		    props.put("hibernate.show_sql", "true");

		    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		    entityManagerFactoryBean.setDataSource(createMySqlDs); // Pass the DataSource directly
		    entityManagerFactoryBean.setPackagesToScan("com.nt.model.promotions"); // Your package for entity classes
		    entityManagerFactoryBean.setJpaPropertyMap(props); // Hibernate properties

		    // Add JpaVendorAdapter
		    JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter); // Set the JPA vendor adapter

		    return entityManagerFactoryBean;
		}
	
	@Bean(name="mySqlTxMgmr")
	
	public JpaTransactionManager createMySqlTxMgmr(@Qualifier("mySqlEMF")EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}
	}
