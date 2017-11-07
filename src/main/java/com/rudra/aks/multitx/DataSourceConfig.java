package com.rudra.aks.multitx;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.rudra.aks.multitx.repository", entityManagerFactoryRef = "factoryBean")
public class DataSourceConfig {

	
	@Bean
	public PlatformTransactionManager	transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(factoryBean().getObject());
		return txManager;
	}
	
	
	@Bean
	public	LocalContainerEntityManagerFactoryBean 	factoryBean() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.rudra.aks.multitx.domain");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}
	

	@Bean
	public DataSource 	dataSource() {
		DataSourceBuilder builder = DataSourceBuilder.create();
		builder.driverClassName("com.mysql.cj.jdbc.Driver");
		builder.url("jdbc:mysql://localhost:3306/test");
		builder.username("root");
		builder.password("root");
		return builder.build();
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
		return properties;
	}
}
