package com.stackroute.activitystream.ActivityStreamBackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.stackroute.activitystream.ActivityStreamBackend")

public class DBConfig {

	Logger log = LoggerFactory.getLogger(DBConfig.class);
	
	private static String DATABASE_URL = "jdbc:mysql://localhost:3306/loginApp";
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	private static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";

	
	@Bean
	public DataSource getDataSource() {
		log.debug("Creating Data Source");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(JDBC_DRIVER_CLASS);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		log.debug("Data Source Created and Returned");
		return dataSource;
	}
	
	public Properties getProperties()
	{
		log.debug("Trying to Set Properties");
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		log.debug("Properties Created and Returned");
		return properties;
	}

	@Bean
	
	public SessionFactory getSessionFactory(DataSource dataSource) {
		log.debug("Creating Session Factory");
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(getDataSource());
		lsf.addProperties(getProperties());
		
		log.debug("Session Factory Returned");
		lsf.scanPackages("com.stackroute.activitystream.ActivityStreamBackend.model");
		return lsf.buildSessionFactory();
	}
	
	@Bean
	
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		log.debug("Creating TransactionManager");
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		log.debug("Transaction Manager Returned");
		return txManager;
	}
}
