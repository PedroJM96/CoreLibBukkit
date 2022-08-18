package com.pedrojm96.core.data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pedrojm96.core.CoreLog;
import com.pedrojm96.core.CorePlugin;

public class CoreSQLiteConnection {
	
	private CoreLog log;

	private CorePlugin plugin;
	private Connection connection;
	
	private File dataFolder;
	
	public CoreSQLiteConnection(CorePlugin cplugin) {
		this.log = cplugin.getLog();
		this.log.info("Data set to SQLite");
		this.plugin = cplugin;
		
		dataFolder = new File(this.plugin.getInstance().getDataFolder(),"sqlite.db");
	    
	    if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
            	 this.log.error("File write error: data.db",e); 
            }
        }
		
		connection = getConnection();
	}
	
	public CorePlugin getPlugin() {
		return this.plugin;
	}
	
	
	public void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		
	    try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
            
        } catch (SQLException e) {
        	 this.log.error("SQLite exception on initialize.",e); 
        	
        } catch (ClassNotFoundException e) {
        	 this.log.error("You need the SQLite JBDC library. Google it. Put it in /lib folder.",e); 
        	
        }
	    return null;
	}
	
}
