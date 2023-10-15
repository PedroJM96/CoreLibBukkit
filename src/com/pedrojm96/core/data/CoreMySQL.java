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
 * Facilita la creacion de base de datos mysql.
 * 
 * @author PedroJM96
 * @version 1.4 05-09-2022
 *
 */
public class CoreMySQL implements CoreSQL {

	private CoreLog log;
	private String tabla;
	private CoreMySQLConnection coreconnection;
	public Map<String, String> columns = new HashMap<String, String>();

	public CoreMySQL(CoreMySQLConnection connection,String tabla) {
		this.log = connection.getLog();
		this.tabla = tabla;
		this.coreconnection = connection;
	}
	
	@Override
	public boolean checkStorage() {
		boolean existe;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = coreconnection.getConnection();
			String query = "SELECT * FROM "+this.tabla;
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			existe = true;
			this.log.info("Loaded database");
			String listcolumns = "";
			ResultSetMetaData metaData = result.getMetaData();
			int rowCount = metaData.getColumnCount();
			for (int i = 1; i <= rowCount; i++) {
				this.columns.put(metaData.getColumnName(i).toLowerCase(), metaData.getColumnTypeName(i));
				listcolumns = listcolumns +"["+metaData.getColumnName(i).toLowerCase()+","+metaData.getColumnTypeName(i)+"] ";
			}
			this.log.info(listcolumns);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			existe = false;
			this.log.alert("The table "+this.tabla+" does not exist");
		}finally{
			cleanup(connection,statement,result);
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
			cleanup(connection,statement,null);
		}
	}
	
	@Override
	public void changeColumnType(String columnname,String columntype) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = coreconnection.getConnection();
			String query = "ALTER TABLE "+this.tabla+" MODIFY "+columnname+" "+columntype;
			statement = connection.createStatement();
			statement.executeUpdate(query);
			this.log.info("Change column Type of "+columnname+" for: "+columntype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.log.alert("The table "+this.tabla+" does not exist");
		}finally{
			cleanup(connection,statement,null);
		}
	}
	
	protected void cleanup(Connection connection, Statement statement,ResultSet result){
		if (connection != null) {
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
		    	  this.log.error("SQLException on cleanup [connection].");  
			}
		}
		
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
	public void build(String ...paramsString) {
		// TODO Auto-generated method stub
		this.log.info("Creating table " + this.tabla);
		Connection connection = null;
		Statement statement = null;
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
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+this.tabla+" (id int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY, " + query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cleanup(connection,statement,null);
		}
	}
	
	@Override
	public boolean checkData(CoreWHERE paranWhere,String paranString) {
		Connection connection = null;
		boolean existe = false;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = coreconnection.getConnection();
			String query = "SELECT '"+paranString+"' FROM "+this.tabla+" WHERE "+paranWhere.get()+";";
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			existe = result.next();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			existe = true;
		}finally{
			cleanup(connection,statement,result);
		}
		return existe;
		
	}

	@Override
	public void insert(String... args) {
		Connection connection = null;
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
		Statement statement = null;
	    ResultSet result = null;
	    String query = "INSERT INTO "+this.tabla+" ("+data+") VALUES ("+value+");";
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
			cleanup(connection,statement,result);
		}
		
	}
	
	@Override
	public String get(CoreWHERE paranWhere,String paranString) {
		Connection connection = null;
		// TODO Auto-generated method stub
		String value = "";
		String query = "SELECT "+paranString+" FROM "+this.tabla+" WHERE "+paranWhere.get()+";";
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
			this.log.fatalError("Could not create getter statement on get: "+query,e);
			e.printStackTrace();
		}finally{
			cleanup(connection,statement,result);
		}
		return value;
	}
	
	@Override
	public HashMap<String, String> get(CoreWHERE paranWhere,String... paranString) {
		Connection connection = null;
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
			cleanup(connection,statement,result);
		}
		return value;
	}
	
	@Override
	public List<HashMap<String, String>> getAll(CoreWHERE paranWhere, String criterio, String... paranString) {
		Connection connection = null;
		String getData = "";
		for(int i =0 ; i<paranString.length ; i++) {
			if(i==(paranString.length - 1)) {
				getData = getData +paranString[i].trim();
			}else {
				getData = getData +paranString[i].trim()+",";
			}
		}
		List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		String query = "";
		if(criterio != null && !criterio.isEmpty() && criterio != " ") {
			query = "SELECT "+getData+" FROM "+this.tabla+" WHERE "+paranWhere.get()+" "+criterio+";";
		}else {
			query = "SELECT "+getData+" FROM "+this.tabla+" WHERE "+paranWhere.get()+";";
		}
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
			cleanup(connection,statement,result);
		}
		return lista;
	}
	
	@Override
	public List<HashMap<String, String>> getAll(String criterio, String... paranString) {
		Connection connection = null;
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
			cleanup(connection,statement,result);
		}
		return lista;
	}
	
	@Override
	public void update(CoreWHERE paranWhere,String... args) {
		// TODO Auto-generated method stub
		Connection connection = null;
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
		Statement statement = null;
	    ResultSet result = null;
	    String query = "UPDATE "+this.tabla+" SET "+data+" WHERE "+paranWhere.get() +";";
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			this.log.fatalError("Could not create getter statement on update: "+query,e);
		}
		finally{
			cleanup(connection,statement,result);
		}
	}
	
	@Override
	public void update(String... args) {
		// TODO Auto-generated method stub
		Connection connection = null;
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
		Statement statement = null;
	    ResultSet result = null;
	    String query = "UPDATE "+this.tabla+" SET "+data+";";
		try {
			connection = coreconnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			this.log.fatalError("Could not create getter statement on update: "+query,e);
		}
		finally{
			cleanup(connection,statement,result);
		}
	}
	
	@Override
	public void delete(CoreWHERE paranWhere) {
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
			cleanup(connection,statement,result);
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
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
			cleanup(connection,statement,result);
		}
		
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return coreconnection.getConnection();
	}
}
