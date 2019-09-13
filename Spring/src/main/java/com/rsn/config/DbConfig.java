package com.rsn.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DbConfig {
	@Autowired
	Environment env;
	
	@Bean
	public DataSource connect() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(  System.getenv("db_driver")  );
		dataSource.setUrl(  System.getenv("db_url")  );
		dataSource.setUsername(  System.getenv("db_username")  );
		dataSource.setPassword(  System.getenv("db_password")  );
		
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource( connect() );
		sessionFactory.setPackagesToScan(new String[] { "com.rsn.entity" });
		sessionFactory.setHibernateProperties( hibernateProperties() );

		return sessionFactory;
	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", "update");
				setProperty("hibernate.dialect", System.getenv("db_dialect"));
				setProperty("hibernate.globally_quoted_identifiers", "true");
			}
		};
	}

}