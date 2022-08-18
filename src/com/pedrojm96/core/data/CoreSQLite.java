package com.pedrojm96.core.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pedrojm96.core.CoreLog;


/**
 * Facilita la creacion de base de datos sqlite.
 * 
 * @author PedroJM96
 * @version 1.3 02-01-2020
 *
 */
public class CoreSQLite implements CoreSQL{

	private CoreLog log;
	private String tabla;

	
	private CoreSQLiteConnection coreconnection;
	
	public Map<String, String> columns = new HashMap<String, String>();
	
	public CoreSQLite(CoreSQLiteConnection connection, String tabla) {
		this.log = connection.getPlugin().getLog();
		this.tabla = tabla;
		coreconnection = connection;
	}
	
	
	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return coreconnection.getConnection();
	}
	
	protected void cleanup(Statement statement,ResultSet result){
		if (statement != null) {
			try
			{
		        statement.close();
			}
			catch (SQLException e)
			{
				this.log.error("SQLException on cleanup [statement].");
			}
		}
		
		if (result != null) {
			try
			{
		        result.close();
			}
			catch (SQLException e)
			{
		    	  this.log.error("SQLException on cleanup [result].");  
			}
		}
		
	}
	
	@Override
	public boolean checkStorage() {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		boolean existe;
		try {
			String tableExists = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+this.tabla+"';";        
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(tableExists);
			String exS = result.getString(1);       
			this.log.info("Table " + exS + " exists."); 
			this.log.info("Loaded database");
			existe = true;
			
				cleanup(statement,result);
				String get = "SELECT * FROM "+this.tabla+";";        
				connection = coreconnection.getConnection();
				statement = connection.createStatement();
				result = statement.executeQuery(get);
				
				String listcolumns = "";
				
				ResultSetMetaData metaData = result.getMetaData();
				int rowCount = metaData.getColumnCount();
				for (int i = 1; i <= rowCount; i++) {
					this.columns.put(metaData.getColumnName(i).toLowerCase(), metaData.getColumnTypeName(i));
					listcolumns = listcolumns +"["+metaData.getColumnName(i).toLowerCase()+","+metaData.getColumnTypeName(i)+"] ";
				}
				this.log.info(listcolumns);
			
			
		}catch (SQLException e){
			existe = false;
			this.log.alert("The table "+this.tabla+" does not exist");  
		}finally{
			cleanup(statement,result);
		}
		return existe;	
	}
	

	@Override
	public boolean columnExists(String columnname) {
		
		return this.columns.containsKey(columnname.toLowerCase());
	}
	
	@Override
	public String getColumnType(String columnname) {
		
		return this.columns.get(columnname.toLowerCase());
	}
	
	@Override
	public void addColumn(String columnname,String columntype) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			connection = coreconnection.getConnection();
			String query = "ALTER TABLE "+this.tabla+" ADD "+columnname+" "+columntype;
			statement = connection.createStatement();
			statement.executeUpdate(query);
			this.log.info("Added column: "+columnname+" "+columntype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.log.alert("The table "+this.tabla+" does not exist");
		}finally{
			cleanup(statement,result);
		}
	}
	
	@Override
	public void changeColumnType(String columnname,String columntype) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			connection = coreconnection.getConnection();
			String query = "ALTER TABLE "+this.tabla+" MODIFY "+columnname+" "+columntype;
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			this.log.info("Change column Type of "+columnname+" for: "+columntype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			this.log.alert("The table "+this.tabla+" does not exist");
		}finally{
			cleanup(statement,result);
		}
	}
	
	
	@Override
	public void build(String ...paramsString) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		this.log.info("Creating table " + this.tabla);
		String query = "";
		for (int i = 0; i<paramsString.length;i++) {
			if(i==(paramsString.length - 1)) {
				query = query + paramsString[i]+");";
			}else {
				query = query + paramsString[i]+", ";
			}
		}
		
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+this.tabla+" (id INTEGER PRIMARY KEY AUTOINCREMENT, " + query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cleanup(statement,null);
		}
	}
	
	@Override
	public boolean checkData(CoreWHERE paranWhere,String paranString) {
		boolean existe = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			String query = "SELECT '"+paranString+"' FROM "+this.tabla+" WHERE "+paranWhere.get()+";";
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			existe = result.next();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			existe = true;
		}finally{
			cleanup(statement,result);
		}
		return existe;
		
	}
	
	@Override
	public void insert(String... args) {
		// TODO Auto-generated method stub
		String data = "";
		String value = "";
		for(int i =0; i<args.length;i++) {
			if(!args[i].trim().contains(":")) {
				continue;
			}
			String[] local = args[i].trim().split(":");
			if(local.length<2) {
				continue;
			}
			String locaData = local[0].trim();
			String locaValue = local[1].trim();
			
			if(i==(args.length - 1)) {
				data = data + locaData;
				value = value + "'"+locaValue+"'";
			}else {
				data = data + locaData +",";
				value = value + "'"+locaValue +"',";
			}		
		}
		Connection connection = null;
		Statement statement = null;
		
	    ResultSet result = null;
	    String query = "INSERT INTO "+this.tabla+" ("+data+") VALUES ("+value+");";
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.log.error("Could not create getter statement on insert: "+query);
			e.printStackTrace();
		}
		finally{
			cleanup(statement,result);
		}
		
	}
	
	
	@Override
	public String get(CoreWHERE paranWhere,String paranString) {
		
		// TODO Auto-generated method stub
		String value = "";
		String query = "SELECT "+paranString+" FROM "+this.tabla+" WHERE "+paranWhere.get()+";";
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			if ((result != null) && (result.next())){
				value = result.getString(paranString);
			}
		}catch (SQLException e) {
			this.log.error("Could not create getter statement on get: "+query);
			e.printStackTrace();
		}finally{
			cleanup(statement,result);
		}
		return value;
	}
	
	
	@Override
	public HashMap<String, String> get(CoreWHERE paranWhere,String... paranString) {
		
		// TODO Auto-generated method stub
		HashMap<String, String> value = new HashMap<String, String>();
		String getData = "";
		
		for(int i =0 ; i<paranString.length ; i++) {
			if(i==(paranString.length - 1)) {
				getData = getData +paranString[i].trim();
			}else {
				getData = getData +paranString[i].trim()+",";
			}
		}
		
		String query = "SELECT "+getData+" FROM "+this.tabla+" WHERE "+paranWhere.get()+";";
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			if ((result != null) && (result.next())){
				
				for(int i =0 ; i<paranString.length ; i++) {
					value.put(paranString[i], result.getString(paranString[i]));
				}
			}
		}catch (SQLException e) {
			this.log.fatalError("Could not create getter statement on get: "+query,e);
			e.printStackTrace();
		}finally{
			cleanup(statement,result);
		}
		return value;
	}
	@Override
	public List<HashMap<String, String>> getAll(CoreWHERE paranWhere, String criterio, String... paranString) {
		
		// TODO Auto-generated method stub
		
		String getData = "";
		
		for(int i =0 ; i<paranString.length ; i++) {
			if(i==(paranString.length - 1)) {
				getData = getData +paranString[i].trim();
			}else {
				getData = getData +paranString[i].trim()+",";
			}
		}
		List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		
		String query;
		if(criterio != null && criterio.isEmpty() && criterio == " ") {
			query = "SELECT "+getData+" FROM "+this.tabla+" WHERE "+paranWhere.get()+" "+criterio+";";
		}else {
			query = "SELECT "+getData+" FROM "+this.tabla+" WHERE "+paranWhere.get()+";";
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while ((result != null) && (result.next())){
				HashMap<String, String> values = new HashMap<String, String>();
				
				
				for(int i =0 ; i<paranString.length ; i++) {
					values.put(paranString[i], result.getString(paranString[i]));
				}
				
				lista.add(values);
			}
		}catch (SQLException e) {
			this.log.fatalError("Could not create getter statement on get: "+query,e);
			e.printStackTrace();
		}finally{
			cleanup(statement,result);
		}
		return lista;
	}
	
	@Override
	public List<HashMap<String, String>> getAll(String criterio, String... paranString) {
		
		// TODO Auto-generated method stub
		
		String getData = "";
		
		for(int i =0 ; i<paranString.length ; i++) {
			if(i==(paranString.length - 1)) {
				getData = getData +paranString[i].trim();
			}else {
				getData = getData +paranString[i].trim()+",";
			}
		}
		List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		String query = "SELECT "+getData+" FROM "+this.tabla+" "+criterio+";";
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while ((result != null) && (result.next())){
				HashMap<String, String> values = new HashMap<String, String>();
				
				
				for(int i =0 ; i<paranString.length ; i++) {
					values.put(paranString[i], result.getString(paranString[i]));
				}
				
				lista.add(values);
			}
		}catch (SQLException e) {
			this.log.fatalError("Could not create getter statement on get: "+query,e);
			e.printStackTrace();
		}finally{
			cleanup(statement,result);
		}
		return lista;
	}
	@Override
	public void update(CoreWHERE paranWhere,String... args) {
		// TODO Auto-generated method stub
		
		String data = "";
		
		for(int i =0; i<args.length;i++) {
			if(!args[i].trim().contains(":")) {
				continue;
			}
			String[] local = args[i].trim().split(":");
			if(local.length<2) {
				continue;
			}
			String locaData = local[0].trim();
			String locaValue = local[1].trim();
			
			if(i==(args.length - 1)) {
				data = data + locaData +"='"+locaValue+"'";
				
			}else {
				data = data + locaData +"='"+locaValue+"',";
			}	
		}
		Connection connection = null;
		Statement statement = null;
		
	    ResultSet result = null;
	    String query = "UPDATE "+this.tabla+" SET "+data+" WHERE "+paranWhere.get() +";";
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.log.fatalError("Could not create getter statement on update: "+query,e);
		}
		finally{
			cleanup(statement,result);
		}
	}

	
	@Override
	public void update(String... args) {
		// TODO Auto-generated method stub
		
		String data = "";
		
		for(int i =0; i<args.length;i++) {
			if(!args[i].trim().contains(":")) {
				continue;
			}
			String[] local = args[i].trim().split(":");
			if(local.length<2) {
				continue;
			}
			String locaData = local[0].trim();
			String locaValue = local[1].trim();
			
			if(i==(args.length - 1)) {
				data = data + locaData +"='"+locaValue+"'";
				
			}else {
				data = data + locaData +"='"+locaValue+"',";
			}	
		}
		Connection connection = null;
		Statement statement = null;
		
	    ResultSet result = null;
	    String query = "UPDATE "+this.tabla+" SET "+data+";";
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.log.fatalError("Could not create getter statement on update: "+query,e);
		}
		finally{
			cleanup(statement,result);
		}
	}
	

	@Override
	public void delete(CoreWHERE paranWhere) {
		
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		
	    ResultSet result = null;
	    String query = "DELETE FROM "+this.tabla+" WHERE "+paranWhere.get() +";";
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.log.fatalError("Could not create getter statement on delete: "+query,e);
		}
		finally{
			cleanup(statement,result);
		}
	}
	
	public void close() {
			coreconnection.close();
	}
	
	@Override
	public void table(String table) {
		// TODO Auto-generated method stub
		this.tabla = table;
	}
	
	@Override
	public void executeUpdate(String query) {
		Connection connection = null;
		Statement statement = null;
	    ResultSet result = null;
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.log.fatalError("Could not create getter statement on insert: "+query,e);
			e.printStackTrace();
		}
		finally{
			cleanup(statement,result);
		}
		
	}

}
