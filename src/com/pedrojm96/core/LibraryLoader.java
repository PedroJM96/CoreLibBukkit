package com.pedrojm96.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;

import org.bukkit.plugin.java.JavaPlugin;

public class LibraryLoader {
	  private static final Method ADD_URL_METHOD;
	  
	  private JavaPlugin plugin;
	  
	  private CoreLog log;
	  
	  static {
	    try {
	      ADD_URL_METHOD = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
	      ADD_URL_METHOD.setAccessible(true);
	    } catch (NoSuchMethodException e) {
	      throw new RuntimeException(e);
	    } 
	  }
	  
	  public LibraryLoader(JavaPlugin plugin, CoreLog log) {
	    this.log = log;
	    this.plugin = plugin;
	  }
	  
	  public void loadLib(File jarsDir, String groupId, String artifactId, String version) throws IOException {
	    loadLib(jarsDir, groupId, artifactId, version, "https://repo1.maven.org/maven2");
	  }
	  
	  public void loadLib(File jarsDir, String groupId, String artifactId, String version, String url) throws IOException {
	    loadLib(jarsDir, new MavenArtifact(groupId, artifactId, version, url));
	  }
	  
	  public void loadLib(File jarsDir, MavenArtifact maven) throws IOException {
	    if (jarsDir.exists() && !jarsDir.isDirectory()) {
	      Files.delete(jarsDir.toPath());
	      if (!jarsDir.exists() && !jarsDir.mkdirs())
	        throw new IOException("Could not create parent directory structure."); 
	    } else if (!jarsDir.exists() && 
	      !jarsDir.mkdirs()) {
	      throw new IOException("Could not create parent directory structure.");
	    } 
	    this.log.info(String.format("Loading lib %s:%s:%s from %s", new Object[] { maven.getGroupId(), maven.getArtifactId(), maven.getVersion(), maven.getRepo() }));
	    String name = String.valueOf(maven.getGroupId()) + "-" + maven.getArtifactId() + "-" + maven.getVersion();
	    File saveLocation = new File(jarsDir, String.valueOf(name) + ".jar");
	    if (!saveLocation.exists()) {
	      try {
	        Exception exception2;
	        this.log.info("Downloading librarie '" + name + "'...");
	        URL url = maven.getUrl();
	        Exception exception1 = null;
	      } catch (Exception e) {
	        e.printStackTrace();
	      } 
	      this.log.info("Librarie '" + name + "' successfully downloaded.");
	    } 
	    if (!saveLocation.exists())
	      throw new RuntimeException("Unable to download lib: " + name); 
	    URLClassLoader classLoader = (URLClassLoader)this.plugin.getClass().getClassLoader();
	    try {
	      ADD_URL_METHOD.invoke(classLoader, new Object[] { saveLocation.toURI().toURL() });
	    } catch (Exception e) {
	      throw new RuntimeException("Unable to load lib: " + saveLocation.toString(), e);
	    } 
	    this.log.info("Loaded Librarie '" + name + "' successfully.");
	  }
	}

