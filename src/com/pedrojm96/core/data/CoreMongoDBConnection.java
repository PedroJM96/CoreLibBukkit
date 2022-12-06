package com.pedrojm96.core.data;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.pedrojm96.core.CoreLog;
import com.pedrojm96.core.CorePlugin;

public class CoreMongoDBConnection {
	private CoreLog log;
	private CorePlugin plugin;
	private String host, database, username, password;
	private int port;
	private MongoClient client;
	private MongoDatabase datastore;
	
	
	
	public CoreMongoDBConnection(CorePlugin plugin,String host,int port,String database,String username,String password) {
		this.log = plugin.getLog();
		this.log.info("Data set to MongoDB");
		this.plugin = plugin;
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
		System.setProperty("jdk.tls.trustNameService", "true");
		client = getClient();
	}
	
	public CorePlugin getPlugin() {
		return this.plugin;
	}
	
	public CoreLog getLog() {
		return this.log;
	}
	
	public MongoDatabase getDataStore() {
		if(datastore!=null){
            return datastore;
        }
		
		datastore = client.getDatabase(this.database);
		return this.datastore;
	}
	
	private MongoClient getClient() {
		if(client!=null){
            return client;
        }
		ServerAddress addr = new ServerAddress(this.host, this.port);
       MongoCredential credentials = MongoCredential.createCredential(this.username, this.database, this.password.toCharArray());
      
        client = new MongoClient(addr, Arrays.asList(credentials));

		return client;
	}
	
}
