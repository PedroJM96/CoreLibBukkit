package com.pedrojm96.core;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Implementacion para facilitar la interacion de las librerias core.
 * 
 * @author PedroJM96
 * @version 1.0 13-10-2018
 *
 */
public interface CorePlugin {
	public abstract CoreLog getLog();
	public abstract JavaPlugin getInstance();
	
}
