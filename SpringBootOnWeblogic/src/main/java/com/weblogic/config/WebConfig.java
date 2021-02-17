package com.weblogic.config;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;
@Configuration
//@EnableConfigurationProperties
public class WebConfig {
	@Bean(name = "db1")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplate1")
	public JdbcTemplate jdbcTemplate1(@Qualifier("db1") DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	
	@Bean(name = "secondary")
	@ConfigurationProperties(prefix = "spring.datasourcesec")
    public JndiPropertyHolder secondary() {
        return new JndiPropertyHolder();
    }
	
	
	@Bean(name = "db2")
	public DataSource dataSource2() {
        DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			System.out.println(secondary().getJndiName());
			ds = (DataSource) ctx.lookup(secondary().getJndiName());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ds;
	}

	@Bean(name = "jdbcTemplate2")
	public JdbcTemplate jdbcTemplate2(@Qualifier("db2") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	private static class JndiPropertyHolder {
		private String jndiName;

		public String getJndiName() {
			return jndiName;
		}

		public void setJndiName(String jndiName) {
			this.jndiName = jndiName;
		}
	}
}
