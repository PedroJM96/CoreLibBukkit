package com.pedrojm96.core.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.pedrojm96.core.CoreLog;
import com.pedrojm96.core.CorePlugin;

public class CoreMongoDBConnection {
	private CoreLog log;
	private CorePlugin plugin;
	private String database;
	private MongoClient client;
	private MongoDatabase datastore;
	
	
	
	public CoreMongoDBConnection(CorePlugin plugin,String host,int port,String authenticationDatabase, String username,String password, String database) {
		this.log = plugin.getLog();
		this.log.info("Data set to MongoDB");
		this.plugin = plugin;
		if((host==null)||(host.equals(""))){
			this.log.alert("DMYSQL() - host nulo");
		}
		this.database = database;
		if((this.database==null)||(this.database.equals(""))){
			this.log.alert("DMYSQL() - database nulo");
		}

		if((username==null)||(username.equals(""))){
			this.log.alert("DMYSQL() - username nulo");
		}
		if((password==null)||(password.equals(""))){
			this.log.alert("DMYSQL() - password nulo");
		}
		System.setProperty("jdk.tls.trustNameService", "true");
		client = getClient(host,port,authenticationDatabase,username,password);	
	}
	
	public CoreMongoDBConnection(CorePlugin plugin,String host,int port, String database) {
		this.log = plugin.getLog();
		this.log.info("Data set to MongoDB");
		this.plugin = plugin;
		if((host==null)||(host.equals(""))){
			this.log.alert("DMYSQL() - host nulo");
		}
		this.database = database;
		if((this.database==null)||(this.database.equals(""))){
			this.log.alert("DMYSQL() - database nulo");
		}
		System.setProperty("jdk.tls.trustNameService", "true");
		client = getClient(host,port);
	}
	
	public CoreMongoDBConnection(CorePlugin plugin,String connectionString, String database) {
		this.log = plugin.getLog();
		this.log.info("Data set to MongoDB");
		this.plugin = plugin;
		this.database = database;
		if((this.database==null)||(this.database.equals(""))){
			this.log.alert("DMYSQL() - database nulo");
		}
		System.setProperty("jdk.tls.trustNameService", "true");
		client = getClient(connectionString);
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
	
	private MongoClient getClient(String host,int port,String authenticationDatabase, String username,String password) {
		if(client!=null){
            return client;
        }
		String uri = "mongodb://";
        uri += username+":"+password+"@";
        uri += host+":";
        uri += port+"/";
        uri += authenticationDatabase;
        uri += "?ssl=false&connectTimeoutMS=10000&socketTimeoutMS=10000";
        this.log.println(uri);
		MongoClientURI connectionString = new MongoClientURI(uri);
		MongoClient localClient = new MongoClient(connectionString);
		/*
		 * ServerAddress addr = new ServerAddress(host, port); MongoCredential
		 * credentials = MongoCredential.createCredential(username,
		 * authenticationDatabase, password.toCharArray()); MongoClient localClient =
		 * new MongoClient(addr, Arrays.asList(credentials));
		 */
		return localClient;
	}
	private MongoClient getClient(String host,int port) {
		if(client!=null){
            return client;
        }
		String uri = "mongodb://";
        uri += host+":";
        uri += port+"/";
        uri += "?ssl=false&connectTimeoutMS=500&socketTimeoutMS=500";
        this.log.println(uri);
		MongoClientURI connectionString = new MongoClientURI(uri);
		MongoClient localClient = new MongoClient(connectionString);
		return localClient;
	} 
	
	private MongoClient getClient(String connectionString) {
		if(client!=null){
            return client;
        }
		MongoClient localClient = new MongoClient(new MongoClientURI(connectionString));
		return localClient;
	}
	
	
	
}
