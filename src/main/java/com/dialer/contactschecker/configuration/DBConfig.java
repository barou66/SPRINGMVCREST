package com.dialer.contactschecker.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dialer.contactschecker.util.AppEncrypt;

@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
@EnableTransactionManagement
@Configuration
public class DBConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try {
			
			dataSource.setDriverClassName(env.getProperty("datasource.jdbc.driver"));
			dataSource.setUrl(env.getProperty("datasource.jdbc.url"));
			dataSource.setUsername(env.getProperty("datasource.jdbc.username"));
			dataSource.setPassword(AppEncrypt.decryptDefault(env.getProperty("datasource.jdbc.password")));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager txManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
		return transactionManager;
	}
}
