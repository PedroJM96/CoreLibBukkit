package com.pedrojm96.core;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Contiene los metodos para enviar mensajes en consola en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.4 5-9-2022
 *
 */
public class CoreLog {

	public enum Color
	{
		AQUA("§b"), BLACK("§0"), BLUE("§9"), DARK_AQUA("§3"), DARK_BLUE("§1"),
		DARK_GRAY("§8"), DARK_GREEN("§2"), DARK_PURPLE("§5"), DARK_RED("§4"),
		GOLD("§6"), GRAY("§7"), GREEN("§a"), LIGHT_PURPLE("§d"), RED("§c"), 
		WHITE("§f"), YELLOW("§e");
		private String color;
		private Color(final String color) {
			this.color = color;
		}
		public String get() {
			return this.color;
		}
	}
	
	private JavaPlugin plugin;
	private Color color;
	private String prefix;
	private boolean debug = false;
	
	public CoreLog(JavaPlugin plugin,Color color,boolean debug) {
		this.plugin = plugin;
		this.color = color;
		this.prefix = this.color.get()+ "["+Color.GRAY.get()+this.plugin.getName()+this.color.get()+"]";
		this.debug = debug;
	}
	
	public CoreLog(JavaPlugin plugin,Color color) {
		this.plugin = plugin;
		this.color = color;
		this.prefix = this.color.get()+ "["+Color.GRAY.get()+this.plugin.getName()+this.color.get()+"]";
	}
	
	public void seDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void info(String info)
	{
		this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&7 " + info));
	}
	
	public void alert(String info)
	{
		this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&8 " + info));
	}
	
	public void fatalError(String info)
	{
		this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&4Fatal-Error:&c " + info));
	}

	public void fatalError(String info, Exception e)
	{
		this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&4Fatal-Error:&c " + info));
		e.printStackTrace();
	}
	
	public void error(String info)
	{
		this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&c " + info));
	}
	
	public void error(String info,Exception e)
	{
		this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&c " + info));
		e.printStackTrace();
	}
	
	public void debug(String info)
	{
		if(debug) {
			this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&8Debug:&c " + info));
		}	
	}

	public void line()
	{
		this.plugin.getServer().getConsoleSender().sendMessage(this.color.get()+"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
	public void println(String string)
	{
		System.out.println(string);
	}
	
	public void print(String string)
	{
		System.out.print(string);
	}
	
	public void debug(String info, Exception e) {
		if(debug) {
			this.plugin.getServer().getConsoleSender().sendMessage(prefix+CoreColor.colorCodes("&8Debug:&c " + info));
			e.printStackTrace();
		}
	}
}
