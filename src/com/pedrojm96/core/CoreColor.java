package com.pedrojm96.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



/**
 * Contiene los metodos para codificar string con colores y enviar mensajes a los jugadores en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.5 05-09-2022
 *
 */
public class CoreColor {
	/**
	 * Colorea un string.
	 * 
	 * @param nonColoredText El string a colorear.
	 * @return Devuelve un string coloreado. 
	 */
	public static String colorCodes(String nonColoredText) {
		if(nonColoredText == null){
        	return nonColoredText;
        }
		if(nonColoredText.isEmpty()){
        	return nonColoredText;
        }
		if(nonColoredText.length() <= 0){
        	return nonColoredText;
        }
		if(nonColoredText == ""){
        	return nonColoredText;
        }
        if(nonColoredText == " "){
        	return nonColoredText;
        }
		String coloredText = ChatColor.translateAlternateColorCodes('&', nonColoredText);
        return coloredText;
    }
	/**
	 * Envia una linea al jugador.
	 * 
	 * @param player El jugador a enviar la linea coloreada.
	 * @param paramString El color de la linea a enviar al jugador.
	 */
	public static void line(Player player,String color) {
		
		player.sendMessage(color.length()>0? CoreColor.colorCodes(color+"---------------------------------------------------"):color);
		
	}
	/**
	 * Envia mensajes al jugador.
	 * 
	 * @param player El jugador a enviar el mensaje coloreado.
	 * @param paramString El mensaje a enviar al jugador.
	 */
	public static void message(Player player,String paramString) {
		
		player.sendMessage(CoreColor.colorCodes(paramString));
		
	}
	/**
	 * Envia mensajes al jugador o la consola.
	 * 
	 * @param player El jugador o consola  a enviar el mensaje coloreado.
	 * @param paramString El mensaje a enviar al jugador o consola.
	 */
	public static void message(CommandSender player,String paramString) {
		player.sendMessage(CoreColor.colorCodes(paramString));
	}
	
	/**
	 * Colorea el string con un formato que permite usarlo TextComponent sin perder color en saltos de linea.
	 * 
	 * @param string El texto a colorear con soporte para TextComponent.
	 * @return Devuelve un string coloreado para usar en un TextComponent sin perder formato.
	 */
	public static String coloriseTextComponentString(String string) {
		if (string == null || string.length() == 0) return " ";
		String localString = CoreColor.colorCodes(string.trim());
		String newString = "";
		String last = "§7";
		if(localString.contains(" ")) {
			String[] frases = localString.split(" ");
			
			for(int i = 0; i <frases.length; i++) {
				String frase = frases[i].trim();
				if(frase.startsWith("§")) {
					newString = newString + " " + frase ;
					
				}else {
					newString = newString + " " +last+ frase;
				}
				
			
				for (int j = 0; j < frase.length(); j++)
		        {
		            char c = frase.charAt(j);
		            char m = frase.length() > 2 ? frase.charAt(j+2) : ' ';
		            if (c == '§' && m == '§') {
		            	last =  "§" + frase.charAt(j + 1) + "§" + frase.charAt(j + 3);
		            	j = 3;
		            }else if(c == '§'){
		            	last =  "§" + frase.charAt(j + 1);
		            	j = 1;
		            }
		        }
			}
			return newString.trim();	
		}else {
			return localString;
		}
	}
	
	/**
	 * Colorea lista de string.
	 * 
	 * @param paramList La lista de string a colorear.
	 * @return Devuelve una lista de string coloreada.
	 */
	public static List<String> rColorList(List<String> paramList) {
		List<String> s = new ArrayList<String>();
		s.addAll(paramList);
		for (int i = 0; i < s.size(); i++) {    
             String p =colorCodes(s.get(i));
             s.set(i, p);
        }
        return s;
	}
	/**
	 * Retorna todos los colores disponibles en una lista de string.
	 * 
	 * @return Devuelve la lista de colores disponibles en string.
	 */
	public static List<String> getAlternateColorList(){
		List<String> c = new ArrayList<String>();
		c.add("&0");
		c.add("&1");
		c.add("&2");
		c.add("&3");
		c.add("&4");
		c.add("&5");
		c.add("&6");
		c.add("&7");
		c.add("&8");
		c.add("&9");
		
		c.add("&a");
		c.add("&b");
		c.add("&c");
		c.add("&d");
		c.add("&e");
		c.add("&f");
		c.add("&k");
		c.add("&l");
		c.add("&m");
		c.add("&n");
		c.add("&o");
		c.add("&r");
		
		c.add("&A");
		c.add("&B");
		c.add("&C");
		c.add("&D");
		c.add("&E");
		c.add("&F");
		c.add("&K");
		c.add("&L");
		c.add("&M");
		c.add("&N");
		c.add("&O");
		c.add("&R");
		return c;
	}
	
	public static List<String> getNativeColorList(){
		List<String> c = new ArrayList<String>();
		c.add("§0");
		c.add("§1");
		c.add("§2");
		c.add("§3");
		c.add("§4");
		c.add("§5");
		c.add("§6");
		c.add("§7");
		c.add("§8");
		c.add("§9");
		
		c.add("§a");
		c.add("§b");
		c.add("§c");
		c.add("§d");
		c.add("§e");
		c.add("§f");
		c.add("§k");
		c.add("§l");
		c.add("§m");
		c.add("§n");
		c.add("§o");
		c.add("§r");
		
		c.add("§A");
		c.add("§B");
		c.add("§C");
		c.add("§D");
		c.add("§E");
		c.add("§F");
		c.add("§K");
		c.add("§L");
		c.add("§M");
		c.add("§N");
		c.add("§O");
		c.add("§R");
		return c;
	}
	public static String clearColor(String coloredText) {
		String nonColoredText = coloredText;
		
		for(String color : CoreColor.getAlternateColorList()) {
			nonColoredText = nonColoredText.replaceAll(color, "");
		}
		for(String color : CoreColor.getNativeColorList()) {
			nonColoredText = nonColoredText.replaceAll(color, "");
		}
		return nonColoredText.trim();
	}
	
}
