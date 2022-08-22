package com.pedrojm96.core.effect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import com.pedrojm96.core.CoreReflection;
import com.pedrojm96.core.CoreUtils;


/**
 * Contiene los metodos para enviar el header y el footer de la lista de jugadores.
 * 
 * @author PedroJM96
 * @version 1.3 18-08-2022
 *
 */
public class CorePlayerListHeaderFooter {
	/**
	 * Envia al jugador el header de la lista de jugadores.
	 * 
	 * @param player El jugador a enviar el header.
	 * @param header El Header a enviar al jugador.
	 */
	public static  void send(Player player, String header) {
		sendHeaderFooter(player,header," ");
	}
	/**
	 * Envia al jugador el header y footer de la lista de jugadores.
	 * 
	 * @param player El jugador a enviar el header y el footer.
	 * @param header El Header a enviar al jugador.
	 * @param footer El Footer a enviar al jugador.
	 */
	public static void sendHeaderFooter(Player player, String header, String footer) {
		try
		{
	    	if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_13) ) {
	    		sendPre1_13_1(player,header,footer);
	    	}else if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_16_x) ){
	    		sendPos1_13_1(player,header,footer);
	    	}else {
	    		player.setPlayerListHeaderFooter(header, footer);
	    		
	    		
	    	}
		}
		catch (Exception ex)
		{
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			ex.printStackTrace();
		}
		
		
	}
	/*
	 * Si se encuentra en una version de minecraft superior o igual  a la 1.17
	 
	private static void sendPos1_17(Player player, String header, String footer) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		Class<?> chat = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
    	Object tabheader = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}" );
    	Object tabfooter = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}" );
    	Constructor<?> constructor = CoreReflection.getClass("net.minecraft.network.protocol.game.PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[0]);
    	Object packet = constructor.newInstance(new Object[0]);
    	CoreReflection.getClass("net.minecraft.network.protocol.game.PacketPlayOutPlayerListHeaderFooter");
    	Field aField = packet.getClass().getDeclaredField("a");
        aField.setAccessible(true);
        aField.set(packet, tabheader);
        Field bField = packet.getClass().getDeclaredField("b");
        bField.setAccessible(true);
        bField.set(packet, tabfooter);;
        CoreReflection.sendPacket(player, packet);
	}*/
	/*
	 * Si se encuentra en una version de minecraft inferior a la 1.13.1.
	 */
	private static void sendPre1_13_1(Player player, String header, String footer) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		Class<?> chat = CoreReflection.getNMSClass("IChatBaseComponent");
    	Object tabheader = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}" );
    	Object tabfooter = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}" );
    	Constructor<?> constructor = CoreReflection.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[0]);
    	Object packet = constructor.newInstance(new Object[0]);
    	
    	Field aField = packet.getClass().getDeclaredField("a");
        aField.setAccessible(true);
        aField.set(packet, tabheader);
        Field bField = packet.getClass().getDeclaredField("b");
        bField.setAccessible(true);
        bField.set(packet, tabfooter);
        
        CoreReflection.sendPacket(player, packet);
	}
	
	
	/*
	 * Si se encuentra en una version de minecraft superior o igual  a la 1.13.1.
	 */
	private static void sendPos1_13_1(Player player, String header, String footer) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		Class<?> chat = CoreReflection.getNMSClass("IChatBaseComponent");
    	Object tabheader = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}" );
    	Object tabfooter = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}" );
    	Constructor<?> constructor = CoreReflection.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[0]);
    	Object packet = constructor.newInstance(new Object[0]);
    	
		Field aField = packet.getClass().getDeclaredField("header");
        aField.setAccessible(true);
        aField.set(packet, tabheader);
        Field bField = packet.getClass().getDeclaredField("footer");
        bField.setAccessible(true);
        bField.set(packet, tabfooter);
        
        CoreReflection.sendPacket(player, packet);
	}
	
}