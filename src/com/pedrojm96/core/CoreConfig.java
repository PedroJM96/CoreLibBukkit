package com.pedrojm96.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.CharStreams;


/**
 * Facilita la creacion de configuracion en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.5 14-06-2020
 *
 */
public class CoreConfig {
	private FileConfiguration config;
	private File file;
	private String configFileName;
	private String header;
	
	private CoreLog log;
	
	public CoreConfig(JavaPlugin plugin,String configfile,CoreLog log,InputStream dafaultData,boolean update){
		try {
			Validate.notNull(plugin);
			Validate.notNull(configfile);
			Validate.notNull(log);
			Validate.notNull(dafaultData);
		}catch (IllegalArgumentException e) {
			log.fatalError("Error on create Object CoreConfig");
			e.printStackTrace();
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
		this.config = new YamlConfiguration();
		this.file = new File(plugin.getDataFolder(),configfile + ".yml");
		this.configFileName = configfile;
		this.log = log;
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
		String inpu = getInputStreamToString(dafaultData);
		
	
		YamlConfiguration defaultConfig = new YamlConfiguration();
		byte[] bytes = StringUtils.getBytesUtf8(inpu);
		
		
		
			String utf8EncodedString = StringUtils.newStringUtf8(bytes);
			try {
				defaultConfig.loadFromString(utf8EncodedString);
			} catch (InvalidConfigurationException e) {
				log.fatalError("Error on loaded default config for "+this.configFileName+".yml.");
				e.printStackTrace();
				plugin.getServer().getPluginManager().disablePlugin(plugin);
			}
		
		
		
		
		
		

		String[] inpuArray = inpu.split("\n");
		String header = "";
		for(int i = 0 ; i<inpuArray.length ; i++) {
			if(inpuArray[i].trim().startsWith("#")) {
				header = header +inpuArray[i]+"\n";
			}else {
				break;
			}
		}
		this.header = header;
		
		
		
		if (this.exists()){
			this.load();
			if(update) {
				for(String nodo : defaultConfig.getKeys(true)) {
					this.add(nodo, defaultConfig.get(nodo));
				}
				//this.header(defaultConfig.options().header());
				this.silenSave();
			}
		}else{
			
				for(String nodo : defaultConfig.getKeys(true)) {
					this.add(nodo, defaultConfig.get(nodo));
				}
			
			//this.header(defaultConfig.options().header());
			this.create();
		}
	}

	private String getInputStreamToString(InputStream dataIn) {
		String result ="";
		try {
			result = CharStreams.toString(new InputStreamReader(
				     dataIn, Charset.forName("UTF-8")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean exists(){
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
	public String getYMLtoString(){
		return config.saveToString();
	}
	public void save(){
		
        try{
        	
            String filestring = config.saveToString();
             
            String[] inpuArray = filestring.split("\n");
     		String configStringLimpia = "";
     		for(int i = 0 ; i<inpuArray.length ; i++) {
     			
     			if(inpuArray[i].trim().startsWith("#")) {
     				
     				continue;
     			}else {
     				
     				configStringLimpia = configStringLimpia+"\n" +inpuArray[i];
     			}
     		}
             
     		Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
     		fileWriter.write(this.header+configStringLimpia);
            fileWriter.close();
            log.alert(this.configFileName+".yml  save.");
        }
        catch(IOException e){
        	log.fatalError("Error on save "+this.configFileName+".yml.");
        	e.printStackTrace();
        }
	}
	public void silenSave(){
		
        try{
        	String filestring = config.saveToString();
            
            String[] inpuArray = filestring.split("\n");
     		String configStringLimpia = "";
     		
     		for(int i = 0 ; i<inpuArray.length ; i++) {
     			
     			if(inpuArray[i].trim().startsWith("#")) {
     				
     				continue;
     			}else {
     				
     				configStringLimpia = configStringLimpia+"\n" +inpuArray[i];
     			}
     		}
             
     		Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
     		fileWriter.write(this.header+configStringLimpia);
            fileWriter.close();
        }
        catch(IOException e){
        	log.fatalError("Error on save "+this.configFileName+".yml.");
        	e.printStackTrace();
        }
    }
	public void load(){
		log.info("Load "+this.configFileName+".yml");
		try {
			FileInputStream fileinputstream = new FileInputStream(this.file);
			config.load(new InputStreamReader(fileinputstream, Charset.forName("UTF-8")));
			log.alert(this.configFileName+".yml loaded.");
		} catch (IOException | InvalidConfigurationException e) {
			log.fatalError("Error on loaded "+this.configFileName+".yml.");
			e.printStackTrace();
		}
	}
	public CoreConfig loadFromString(String text){
		try {
			config.loadFromString(text);
			log.alert("String to "+this.configFileName+" loaded.");
			return this;
		} catch (InvalidConfigurationException e) {
			log.fatalError("Error on loaded Master "+this.configFileName);
			e.printStackTrace();
			return null;
		}
	}
	public void create(){
		
		
		log.alert("The "+this.configFileName+".yml file does not exist yet.");
		log.info("Creating and loading file "+this.configFileName+".yml.");
        try{
        	String filestring = config.saveToString();
            
            String[] inpuArray = filestring.split("\n");
     		String configStringLimpia = "";

     		for(int i = 0 ; i<inpuArray.length ; i++) {
     			
     			if(inpuArray[i].trim().startsWith("#")) {
     				
     				continue;
     			}else {
     				
     				configStringLimpia = configStringLimpia+"\n" +inpuArray[i];
     			}
     		}
             
     		Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
     		fileWriter.write(this.header+configStringLimpia);
            fileWriter.close();
            log.alert(this.configFileName+".yml  create.");
        }
        catch(IOException e){
        	log.fatalError("Error on create "+this.configFileName+".yml.");
            e.printStackTrace();
        }
    }
	@SuppressWarnings("unused")
	private void create(String data){
		String configTex = data;
		
		log.alert("The "+this.configFileName+".yml file does not exist yet.");
		log.info("Creating and loading file "+this.configFileName+".yml.");
        try{
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
            fileWriter.write(configTex);
            
            fileWriter.close();
            log.alert(this.configFileName+".yml  create.");
        }
        catch(IOException e){
        	log.fatalError("Error on create "+this.configFileName+".yml."); 
        	e.printStackTrace();
        }
    }
	public void add(String path,String value){
		if(!config.isSet(path)) {
			config.set(path,value);
		}
	}
	public void add(String path,long value){
		if(!config.isSet(path)) {
			config.set(path,value);
		}
	}
	public void add(String path,boolean value){
		if(!config.isSet(path)) {
			config.set(path,value);
		}
	}
	public void add(String path,List<String> value){
		if(!config.isSet(path)) {
			config.set(path,value);
		}
	}
	public void add(String path,int value){
		if(!config.isSet(path)) {
			config.set(path,value);
		}
	}
	public void add(String path, double value) {
		if(!config.isSet(path)) {
			config.set(path,value);
		}
	}
	public void add(String path, Object value) {
		if(!config.isSet(path)) {
			config.set(path, value);
		}
	}
	public boolean getBoolean(String path){
		return config.getBoolean(path);
	}
	public String getString(String path){
		return config.getString(path);
	}
	public int getInt(String path){
		return config.getInt(path);
	}
	public long getLong(String path){
		return config.getLong(path);
	}
	public List<String> getStringList(String path){
		return config.getStringList(path);
	}
	public ConfigurationSection getConfigurationSection(String path){
		return config.getConfigurationSection(path);
	}
	public Double getDouble(String path){
		return config.getDouble(path);
	}
	
	public float getFloat(String path){
		return (float) config.getDouble(path);
	}
	
	public Set<String> getKeys(Boolean bo){
		return config.getKeys(bo);
	}
	public void set(String path,String value){
		 config.set(path, value);
	}
	public void setNull(String path){
		 config.set(path, null);
	}
	public void set(String path,double value){
		 config.set(path, value);
	}
	
	public void set(String path,float value){
		 config.set(path, value);
	}
	
	public void set(String path, List<String> value) {
		config.set(path, value);
	}
	public void set(String path,int value){
		 config.set(path, value);
	}
	public void set(String path,boolean value){
		 config.set(path, value);
	}
	public boolean isSet(String path){
		return config.isSet(path);
	}
	
	public boolean isList(String path) {
		return config.isList(path);
	}
	
	public boolean contains(String path){
		 return config.contains(path);
	}
	@SuppressWarnings("deprecation")
	public void header(String h) {
		config.options().header(h);
	}
}
