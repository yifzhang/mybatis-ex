package com.penglecode.mybatis.ex.test.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

public class Dbcp2DataSourceFactory implements DataSourceFactory {

	private final BasicDataSource dataSource;
	
	public Dbcp2DataSourceFactory() {
		super();
		this.dataSource = new BasicDataSource();
	}

	public void setProperties(Properties props) {
		dataSource.setDriverClassName(props.getProperty("driverClassName"));
		dataSource.setUrl(props.getProperty("url"));
		dataSource.setUsername(props.getProperty("username"));
		dataSource.setPassword(props.getProperty("password"));
        
		dataSource.setInitialSize(Integer.parseInt(props.getProperty("initialSize","5")));
		dataSource.setMaxTotal(Integer.parseInt(props.getProperty("maxTotal","20")));
        dataSource.setMaxIdle(Integer.parseInt(props.getProperty("maxIdle","20")));
        dataSource.setMinIdle(Integer.parseInt(props.getProperty("minIdle","5")));
        dataSource.setMaxWaitMillis(Long.parseLong(props.getProperty("maxWaitMillis","3000")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(props.getProperty("timeBetweenEvictionRunsMillis", "30000")));
        dataSource.setDefaultAutoCommit(Boolean.parseBoolean(props.getProperty("defaultAutoCommit","false")));
        dataSource.setConnectionProperties(props.getProperty("connectionProperties", "rewriteBatchedStatements=true"));
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

}
