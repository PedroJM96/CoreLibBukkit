package com.pedrojm96.core.effect;

import java.lang.reflect.Constructor;

import org.bukkit.entity.Player;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreReflection;
import com.pedrojm96.core.CoreVersion;

/**
 * Contiene los metodos para enviar titulos y sutitulos a los jugadores en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.5 05-09-2022
 *
 */
public class CoreTitles {
	/**
	 * 
	 * @param player El Jugador al que se enviaran los titulos.
	 * @param title El Titulo que se va a enviar.
	 */
	public static void sendTitles(Player player, String title)
	{
		sendTitles(player, 20 , 50 ,10 , CoreColor.colorCodes(title), " ");
	}
	/**
	 * 
	 * @param player El Jugador al que se enviaran los titulos.
	 * @param title El Titulo que se va a enviar.
	 * @param subtitle El Sub-Titulo que se va a enviar.
	 */
	public static void sendTitles(Player player, String title, String subtitle)
	{
		sendTitles(player, 20 , 50 ,10 , CoreColor.colorCodes(title), CoreColor.colorCodes(subtitle));
	}
	/**
	 * 
	 * @param player El Jugador al que se enviaran los titulos.
	 * @param stay La duracion del titulo/sub-titulo. (El tiempo que durara mostrandose).
	 * @param title El Titulo que se va a enviar.
	 * @param subtitle El Sub-Titulo que se va a enviar.
	 */
	public static void sendTitles(Player player, Integer stay, String title, String subtitle)
	{
		sendTitles(player, 20 , stay <= 0 ? 50 : stay ,10 , CoreColor.colorCodes(title), CoreColor.colorCodes(subtitle));
	}
	/**
	 * 
	 * @param player El Jugador al que se enviaran los titulos.
	 * @param fadeIn La duracion que tardara en aparecer el titulo/sub-titulo.
	 * @param stay La duracion del titulo/sub-titulo. (El tiempo que durara mostrandose).
	 * @param fadeOut La duracion que tardara en desaparecer el titulo/sub-titulo.
	 * @param title El Titulo que se va a enviar.
	 * @param subtitle El Sub-Titulo que se va a enviar.
	 */
	public static void sendTitles(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
	{
		if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
			 player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
		}else {
			send(player, fadeIn <= 0 ? 20 : fadeIn , stay <= 0 ? 50 : stay ,fadeOut <= 0 ? 10 : fadeOut , CoreColor.colorCodes(title), CoreColor.colorCodes(subtitle));
		}	
	}
	
	private static void send(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
	{
		try{
			Object chat = CoreReflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
			Constructor<?> Constructor = CoreReflection.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { CoreReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], CoreReflection.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
			Object timePacket = Constructor.newInstance(new Object[] { CoreReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null), chat, fadeIn, stay, fadeOut });
			Constructor = CoreReflection.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { CoreReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], CoreReflection.getNMSClass("IChatBaseComponent") });
			Object titlePacket = Constructor.newInstance(new Object[] { CoreReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chat });
			chat = CoreReflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
			Object subtitlePacket = Constructor.newInstance(new Object[] { CoreReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chat });
			CoreReflection.sendPacket(player, timePacket);
			CoreReflection.sendPacket(player, titlePacket);
	      	CoreReflection.sendPacket(player, subtitlePacket);
	    }
	    catch (Exception var11){
	    	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	    	var11.printStackTrace();
	    }
	}
}
