package com.pedrojm96.core.effect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

import org.bukkit.entity.Player;

import com.pedrojm96.core.CoreReflection;
/**
 * Contiene los metodos para ocultar y mostrar a los jugadores en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.1 19-08-2022
 *
 */
public class CoreShowHidePlayer {

	
	public static Class<?> Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction = CoreReflection.getNMSClass("PacketPlayOutPlayerInfo$EnumPlayerInfoAction");
	public static Class<?> Class_PacketPlayOutPlayerInfo = CoreReflection.getNMSClass("PacketPlayOutPlayerInfo");
	
	public static Class<?> Class_EntityPlayer = CoreReflection.getNMSClass("EntityPlayer");
	public static Class<?> Class_EntityPlayerArray = CoreReflection.getNMSClassArray("EntityPlayer");
	
	public static void hide(Player player,Player playertohide) {
		try {
			
			
			Constructor<?> Constructor_PacketPlayOutPlayerInfo = Class_PacketPlayOutPlayerInfo.getConstructor(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction,Class_EntityPlayerArray);
			
			Object[] get_EntityPlayerArray = (Object[]) Array.newInstance(Class_EntityPlayer, 1);
			
			
			Object get_EntityPlayer =  playertohide.getClass().getMethod("getHandle").invoke(playertohide);
			
			Array.set(get_EntityPlayerArray, 0, get_EntityPlayer);

			Object new_PacketPlayOutPlayerInfo = Constructor_PacketPlayOutPlayerInfo.newInstance(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction.getField("REMOVE_PLAYER").get(null), Class_EntityPlayerArray.cast(get_EntityPlayerArray));
			CoreReflection.sendPacket(player, new_PacketPlayOutPlayerInfo);
		}
	    catch (Exception var11)
	    {
	    	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	    	var11.printStackTrace();
	    }
	}
	
	
	public static void hide(Player player,Player... playertohide) {
		try {
			
		
			
			Constructor<?> Constructor_PacketPlayOutPlayerInfo = Class_PacketPlayOutPlayerInfo.getConstructor(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction,Class_EntityPlayerArray);
			
			
			Object[] get_EntityPlayerArray = (Object[]) Array.newInstance(Class_EntityPlayer, playertohide.length);
			
			for(int i =0; i<playertohide.length; i++) {
				Object get_EntityPlayer =  playertohide[i].getClass().getMethod("getHandle").invoke(playertohide[i]);
				
				Array.set(get_EntityPlayerArray, i, get_EntityPlayer);
			}
			Object new_PacketPlayOutPlayerInfo = Constructor_PacketPlayOutPlayerInfo.newInstance(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction.getField("REMOVE_PLAYER").get(null),Class_EntityPlayerArray.cast(get_EntityPlayerArray));
			CoreReflection.sendPacket(player, new_PacketPlayOutPlayerInfo);
		}
	    catch (Exception var11)
	    {
	    	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	    	var11.printStackTrace();
	    }
	}
	
	
	public static void show(Player player,Player... playertoshow) {
		try {
			
			Constructor<?> Constructor_PacketPlayOutPlayerInfo = Class_PacketPlayOutPlayerInfo.getConstructor(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction,Class_EntityPlayerArray);
			
			
			Object[] get_EntityPlayerArray = (Object[]) Array.newInstance(Class_EntityPlayer, playertoshow.length);
			
			for(int i =0; i<playertoshow.length; i++) {
				Object get_EntityPlayer =  playertoshow[i].getClass().getMethod("getHandle").invoke(playertoshow[i]);
				
				Array.set(get_EntityPlayerArray, i, get_EntityPlayer);
			}
			
			Object new_PacketPlayOutPlayerInfo = Constructor_PacketPlayOutPlayerInfo.newInstance(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction.getField("ADD_PLAYER").get(null),Class_EntityPlayerArray.cast(get_EntityPlayerArray));
			CoreReflection.sendPacket(player, new_PacketPlayOutPlayerInfo);
		}
	    catch (Exception var11)
	    {
	    	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	    	var11.printStackTrace();
	    }
	}
	
	
	public static void show(Player player,Player playertoshow) {
		try {
			Constructor<?> Constructor_PacketPlayOutPlayerInfo = Class_PacketPlayOutPlayerInfo.getConstructor(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction,Class_EntityPlayerArray);
			Object[] get_EntityPlayerArray = (Object[]) Array.newInstance(Class_EntityPlayer, 1);
			
			
			Object get_EntityPlayer =  playertoshow.getClass().getMethod("getHandle").invoke(playertoshow);
			
			Array.set(get_EntityPlayerArray, 0, get_EntityPlayer);
			
			Object new_PacketPlayOutPlayerInfo = Constructor_PacketPlayOutPlayerInfo.newInstance(Class_PacketPlayOutPlayerInfo_EnumPlayerInfoAction.getField("ADD_PLAYER").get(null),Class_EntityPlayerArray.cast(get_EntityPlayerArray));
			CoreReflection.sendPacket(player, new_PacketPlayOutPlayerInfo);
		}
	    catch (Exception var11)
	    {
	    	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	    	var11.printStackTrace();
	    }
	}
	
}
