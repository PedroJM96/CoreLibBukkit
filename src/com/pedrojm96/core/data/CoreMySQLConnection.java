package com.pedrojm96.core.data;

import java.sql.Connection;
import java.sql.SQLException;

import com.pedrojm96.core.CoreLog;
import com.pedrojm96.core.CorePlugin;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



/**
 * Facilita la creacion de la conexion a la base de datos mysql.
 * 
 * @author PedroJM96
 * @version 1.1 05-09-2022
 *
 */
public class CoreMySQLConnection {

	private CoreLog log;
	private String host, database, username, password;
	private int port;
	private HikariDataSource dataSource;
	private boolean useSSL = false;
	
	public CoreMySQLConnection(CorePlugin plugin,String host,int port,String database,String username,String password,boolean useSSL) {
		this.log = plugin.getLog();
		this.log.info("Data set to MySQL");
		this.host = host;
		if((this.host==null)||(this.host.equals(""))){
			this.log.alert("DMYSQL() - host nulo");
		}
		this.port = port;
		this.database = database;
		if((this.database==null)||(this.database.equals(""))){
			this.log.alert("DMYSQL() - database nulo");
		}
		this.username = username;
		if((this.username==null)||(this.username.equals(""))){
			this.log.alert("DMYSQL() - username nulo");
		}
		this.password = password;
		if((this.password==null)||(this.password.equals(""))){
			this.log.alert("DMYSQL() - password nulo");
		}
		this.useSSL = useSSL;
		HikariConfig config = new HikariConfig();
		config.setPoolName(plugin.getInstance().getName()+"-MySQLPool");
		config.setJdbcUrl("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database+"?useSSL="+(this.useSSL?"true":"false")  );
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.setUsername(this.username);
		config.setPassword(this.password);
		config.setMaxLifetime(180000L);
		config.setIdleTimeout(60000L);
		config.setMinimumIdle(2);
		config.setMaximumPoolSize(15);
		config.setConnectionTimeout(10000);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("useServerPrepStmts", "true");
		config.addDataSourceProperty("useLocalSessionState", "true");
		//config.addDataSourceProperty("rewriteBatchedStatements", "true");
		config.addDataSourceProperty("cacheResultSetMetadata", "true");
		config.addDataSourceProperty("cacheServerConfiguration", "true");
		config.addDataSourceProperty("elideSetAutoCommits", "true");
		config.addDataSourceProperty("maintainTimeStats", "false");
		config.addDataSourceProperty("characterEncoding", "utf8");
		config.addDataSourceProperty("encoding", "UTF-8");
		config.addDataSourceProperty("useUnicode", "true");
		config.setLeakDetectionThreshold(10000);
		this.dataSource = new HikariDataSource(config);
	}
	
	public CoreLog getLog() {
		return this.log;
	}
	
	public void close() {
		dataSource.close();
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
