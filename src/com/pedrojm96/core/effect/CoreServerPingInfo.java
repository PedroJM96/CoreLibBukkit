package com.pedrojm96.core.effect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.mojang.authlib.GameProfile;
import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreReflection;
import com.pedrojm96.core.CoreVersion;





/**
 * Contiene los metodos para cambiar el motd servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.3 05-10-2022
 *
 */

public class CoreServerPingInfo {

	/*
	 * private static final VarHandle MODIFIERS;
	 * 
	 * static { try { Lookup lookup = MethodHandles.privateLookupIn(Field.class,
	 * MethodHandles.lookup()); MODIFIERS = lookup.findVarHandle(Field.class,
	 * "modifiers", int.class); } catch (IllegalAccessException |
	 * NoSuchFieldException ex) { throw new RuntimeException(ex); } }
	 */
	
	
	public static class PingMotd{
		
		private String motd;
		
		public PingMotd(String motd) {
			this.motd = motd;
		}
		
		public String getMotd() {
			return this.motd;
		}
	}
	
	public static class PingHover{
		
		private String hover;
		
		public PingHover(String hover) {
			this.hover = hover;
		}
		
		public String getHover() {
			return this.hover;
		}
	}
	
	public static class PingPlayerCount{
		
		private String playercount;
		
		public PingPlayerCount(String playercount) {
			this.playercount = playercount;
		}
		
		public String getPlayerCount() {
			return this.playercount;
		}
	}
	
	private static Object oldjson = null;
	private static boolean change;

	public static void changePingInfo(PingMotd motd) {
		
		change(motd.getMotd(),null,null);
	}
	
	public static void changePingInfo(PingHover hover) {
		change(null,hover.getHover(),null);
	}
	
	public static void changePingInfo(PingPlayerCount playercount) {
		change(null,null,playercount.getPlayerCount());
	}
	
	
	public static void changePingInfo(PingMotd motd,PingHover hover) {
		change(motd.getMotd(),hover.getHover(),null);
	}

	public static void changePingInfo(PingMotd motd,PingHover hover,PingPlayerCount playercount) {
		change(motd.getMotd(),hover.getHover(),playercount.getPlayerCount());
	}
	
	public static void changePingInfo(PingHover hover,PingPlayerCount playercount) {
		change(null,hover.getHover(),playercount.getPlayerCount());
	}
	
	public static void changePingInfo(PingMotd motd,PingPlayerCount playercount) {
		change(motd.getMotd(),null,playercount.getPlayerCount());
	}
	
	public static void clear() {
		if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
			Class<?> Class_PacketStatusOutServerInfo = CoreReflection.getClass("net.minecraft.network.protocol.status.PacketStatusOutServerInfo");
			CoreReflection.setStaticField(Class_PacketStatusOutServerInfo, "a", oldjson);
		}else {
			Class<?> Class_PacketStatusOutServerInfo = CoreReflection.getNMSClass("PacketStatusOutServerInfo");
			CoreReflection.setStaticField(Class_PacketStatusOutServerInfo, "a", oldjson);
		}
		change = false;
	}
	
	
	private static void change(String motd,String hover,String playercount) {
		if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
			changePos1_17(motd,hover,playercount);
		}else {
			changePre1_17(motd,hover,playercount);
		}
	}
	
	
	
	private static void changePre1_17(String motd,String hover,String playercount) {
		if(!change) {
			try{
				/**
				 * ServerData Serialiser *****************************************************************
				 */
				Class<?> Class_ServerPing_ServerData_Serializer = CoreReflection.getNMSClass("ServerPing$ServerData$Serializer");
				Constructor<?> Constructor_ServerPing_ServerData_Serializer = Class_ServerPing_ServerData_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_ServerData_Serializer = Constructor_ServerPing_ServerData_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ServerPingPlayerSample Serialiser *****************************************************************
				 */
				Class<?> Class_ServerPing_ServerPingPlayerSample_Serializer = CoreReflection.getNMSClass("ServerPing$ServerPingPlayerSample$Serializer");
				Constructor<?> Constructor_ServerPing_ServerPingPlayerSample_Serializer = Class_ServerPing_ServerPingPlayerSample_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_ServerPingPlayerSample_Serializer = Constructor_ServerPing_ServerPingPlayerSample_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ServerPing Serialiser *****************************************************************
				 */
				Class<?> Class_ServerPing_Serializer = CoreReflection.getNMSClass("ServerPing$Serializer");
				Constructor<?> Constructor_ServerPing_Serializer = Class_ServerPing_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_Serializer = Constructor_ServerPing_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * IChatBaseComponent ChatSerialiser *****************************************************************
				 */
				Class<?> Class_IChatBaseComponent_ChatSerializer = CoreReflection.getNMSClass("IChatBaseComponent$ChatSerializer");
				Constructor<?> Constructor_IChatBaseComponent_ChatSerializer = Class_IChatBaseComponent_ChatSerializer.getConstructor(new Class[0]);
				Object new_IChatBaseComponent_ChatSerializer = Constructor_IChatBaseComponent_ChatSerializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ChatModifier ChatModifierSerializer *****************************************************************
				 */
				Class<?> Class_ChatModifier_ChatModifierSerializer = CoreReflection.getNMSClass("ChatModifier$ChatModifierSerializer");
				Constructor<?> Constructor_ChatModifier_ChatModifierSerializer = Class_ChatModifier_ChatModifierSerializer.getConstructor(new Class[0]);
				Object new_ChatModifier_ChatModifierSerializer = Constructor_ChatModifier_ChatModifierSerializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ChatTypeAdapterFactory *****************************************************************
				 */
				Class<?> Class_ChatTypeAdapterFactory = CoreReflection.getNMSClass("ChatTypeAdapterFactory");
				Constructor<?> Constructor_ChatTypeAdapterFactory = Class_ChatTypeAdapterFactory.getConstructor(new Class[0]);
				Object new_ChatTypeAdapterFactory = Constructor_ChatTypeAdapterFactory.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				Class<?> Class_IChatBaseComponent = CoreReflection.getNMSClass("IChatBaseComponent");
				Class<?> Class_ServerPing_ServerData = CoreReflection.getNMSClass("ServerPing$ServerData");
				Class<?> Class_ServerPing_ServerPingPlayerSample = CoreReflection.getNMSClass("ServerPing$ServerPingPlayerSample");
				Class<?> Class_ServerPing = CoreReflection.getNMSClass("ServerPing");
				Class<?> Class_ChatModifier = CoreReflection.getNMSClass("ChatModifier");
				Class<?> Class_PacketStatusOutServerInfo = CoreReflection.getNMSClass("PacketStatusOutServerInfo");
				oldjson = getStaticField(Class_PacketStatusOutServerInfo, "a");
				Gson a = new GsonBuilder().registerTypeAdapter(Class_ServerPing_ServerData, new_ServerPing_ServerData_Serializer)
		                .registerTypeAdapter(Class_ServerPing_ServerPingPlayerSample,new_ServerPing_ServerPingPlayerSample_Serializer)
		                .registerTypeAdapter(Class_ServerPing, new PingSerializer(motd,hover,playercount,new_ServerPing_Serializer))
		                .registerTypeHierarchyAdapter(Class_IChatBaseComponent, new_IChatBaseComponent_ChatSerializer)
		                .registerTypeHierarchyAdapter(Class_ChatModifier, new_ChatModifier_ChatModifierSerializer)
		                .registerTypeAdapterFactory((TypeAdapterFactory) new_ChatTypeAdapterFactory).create();
				setStaticField(Class_PacketStatusOutServerInfo, "a", a);
				change = true;
				}catch (Exception var11){
					System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			    	var11.printStackTrace();
			    }
		  }	
	}
	
	
	private static void changePos1_17(String motd,String hover,String playercount) {
		if(!change) {
			try{
				/**
				 * ServerData Serialiser *****************************************************************
				 */
				Class<?> Class_ServerPing_ServerData_Serializer = CoreReflection.getClass("net.minecraft.network.protocol.status.ServerPing$ServerData$Serializer");
				Constructor<?> Constructor_ServerPing_ServerData_Serializer = Class_ServerPing_ServerData_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_ServerData_Serializer = Constructor_ServerPing_ServerData_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ServerPingPlayerSample Serialiser *****************************************************************
				 */
				Class<?> Class_ServerPing_ServerPingPlayerSample_Serializer = CoreReflection.getClass("net.minecraft.network.protocol.status.ServerPing$ServerPingPlayerSample$Serializer");
				Constructor<?> Constructor_ServerPing_ServerPingPlayerSample_Serializer = Class_ServerPing_ServerPingPlayerSample_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_ServerPingPlayerSample_Serializer = Constructor_ServerPing_ServerPingPlayerSample_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ServerPing Serialiser *****************************************************************
				 */
				Class<?> Class_ServerPing_Serializer = CoreReflection.getClass("net.minecraft.network.protocol.status.ServerPing$Serializer");
				Constructor<?> Constructor_ServerPing_Serializer = Class_ServerPing_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_Serializer = Constructor_ServerPing_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * IChatBaseComponent ChatSerialiser *****************************************************************
				 */
				Class<?> Class_IChatBaseComponent_ChatSerializer = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent$ChatSerializer");
				Constructor<?> Constructor_IChatBaseComponent_ChatSerializer = Class_IChatBaseComponent_ChatSerializer.getConstructor(new Class[0]);
				Object new_IChatBaseComponent_ChatSerializer = Constructor_IChatBaseComponent_ChatSerializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ChatModifier ChatModifierSerializer *****************************************************************
				 */
				Class<?> Class_ChatModifier_ChatModifierSerializer = CoreReflection.getClass("net.minecraft.network.chat.ChatModifier$ChatModifierSerializer");
				Constructor<?> Constructor_ChatModifier_ChatModifierSerializer = Class_ChatModifier_ChatModifierSerializer.getConstructor(new Class[0]);
				Object new_ChatModifier_ChatModifierSerializer = Constructor_ChatModifier_ChatModifierSerializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				/**
				 * ChatTypeAdapterFactory *****************************************************************
				 */
				Class<?> Class_ChatTypeAdapterFactory = CoreReflection.getClass("net.minecraft.util.ChatTypeAdapterFactory");
				Constructor<?> Constructor_ChatTypeAdapterFactory = Class_ChatTypeAdapterFactory.getConstructor(new Class[0]);
				Object new_ChatTypeAdapterFactory = Constructor_ChatTypeAdapterFactory.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				Class<?> Class_PacketStatusOutServerInfo = CoreReflection.getClass("net.minecraft.network.protocol.status.PacketStatusOutServerInfo");
				Class<?> Class_IChatBaseComponent = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
				Class<?> Class_ServerPing_ServerData = CoreReflection.getClass("net.minecraft.network.protocol.status.ServerPing$ServerData");
				Class<?> Class_ServerPing_ServerPingPlayerSample = CoreReflection.getClass("net.minecraft.network.protocol.status.ServerPing$ServerPingPlayerSample");
				Class<?> Class_ServerPing = CoreReflection.getClass("net.minecraft.network.protocol.status.ServerPing");
				Class<?> Class_ChatModifier = CoreReflection.getClass("net.minecraft.network.chat.ChatModifier");
				oldjson = getStaticField(Class_PacketStatusOutServerInfo, "a");
				Gson a = new GsonBuilder().registerTypeAdapter(Class_ServerPing_ServerData, new_ServerPing_ServerData_Serializer)
		                .registerTypeAdapter(Class_ServerPing_ServerPingPlayerSample,new_ServerPing_ServerPingPlayerSample_Serializer)
		                .registerTypeAdapter(Class_ServerPing, new PingSerializer(motd,hover,playercount,new_ServerPing_Serializer))
		                .registerTypeHierarchyAdapter(Class_IChatBaseComponent, new_IChatBaseComponent_ChatSerializer)
		                .registerTypeHierarchyAdapter(Class_ChatModifier, new_ChatModifier_ChatModifierSerializer)
		                .registerTypeAdapterFactory((TypeAdapterFactory) new_ChatTypeAdapterFactory).create();
				setStaticField(Class_PacketStatusOutServerInfo, "a", a);
				change = true;
				}catch (Exception var11){
					System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			    	var11.printStackTrace();
			    }
		  }	
	}
	
	public static void setStaticField(Class<?> c, String name, Object set) {
		Field field = null;
		try{
			field = c.getDeclaredField(name);
        } catch(NoSuchFieldException e){
        	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
            e.printStackTrace();
        } catch(SecurityException e){
        	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
            e.printStackTrace();
        }
		// make field non-final
	    //MODIFIERS.set(field, field.getModifiers() & ~Modifier.FINAL);
	    // set field to new value
	    field.setAccessible(true);
	    
	    try {
	    	field.set(null, set);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
	
	public static Object getStaticField(Class<?> c, String name){
		Object retorno = null;
		Field field = null;
        try{
        	field = c.getDeclaredField(name);
        } catch(NoSuchFieldException e){
        	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
            e.printStackTrace();
        } catch(SecurityException e){
        	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
            e.printStackTrace();
        }
        if(field == null){
            throw new IllegalArgumentException("Error while getting the field '" + name + "'");
        }
        // make field non-final
	    //MODIFIERS.set(field, field.getModifiers() & ~Modifier.FINAL);
	    // set field to new value
	    field.setAccessible(true);
	    try {
        	retorno = field.get(null);
        } catch (IllegalArgumentException | IllegalAccessException e) {
        	System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
            e.printStackTrace();
        }
        return retorno;
    }
	
	
	@SuppressWarnings("rawtypes")
	public static class PingSerializer implements JsonSerializer {

		private String motd;
		private String hover;
		private String playercount;
		private Object new_ServerPing_Serializer;
		private Class<?> Class_IChatBaseComponent;
		public PingSerializer(String motd,String hover,String playercount,Object new_ServerPing_Serializer){
			this.motd =  motd;
			this.hover =  hover;
			this.playercount =  playercount;
			this.new_ServerPing_Serializer = new_ServerPing_Serializer;
			if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
				Class_IChatBaseComponent = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
			}else {
				Class_IChatBaseComponent = CoreReflection.getNMSClass("IChatBaseComponent");
			}
		}
		
		@Override
		public JsonElement serialize(Object new_ServerPing, Type arg1, JsonSerializationContext arg2) {		
			JsonElement json = null;
			try {
				if(this.motd!=null) {
					if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
						CoreReflection.setFieldValue(new_ServerPing, "c", getIChatBaseComponent(CoreColor.colorCodes(this.motd)));
					}else {
						new_ServerPing.getClass().getMethod("setMOTD", Class_IChatBaseComponent ).invoke(new_ServerPing, getIChatBaseComponent(CoreColor.colorCodes(this.motd)));
					}	
				}
				if(this.hover!=null) {
					if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
						Object getServerPingPlayerSample = CoreReflection.getFieldValue(new_ServerPing, "d");
						CoreReflection.setFieldValue(getServerPingPlayerSample, "c", new GameProfile[] {new GameProfile(new UUID(0,0), CoreColor.colorCodes(this.hover))});
					}else {
						Object getServerPingPlayerSample = CoreReflection.getFieldValue(new_ServerPing, "b");
						CoreReflection.setFieldValue(getServerPingPlayerSample, "c", new GameProfile[] {new GameProfile(new UUID(0,0), CoreColor.colorCodes(this.hover))});
					}
					
					
				}
				if(this.playercount!=null) {
					if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
						Object getServerData = CoreReflection.getFieldValue(new_ServerPing, "e");
						CoreReflection.setFieldValue(getServerData, "b", 1);
						CoreReflection.setFieldValue(getServerData, "a", CoreColor.colorCodes(this.playercount));
					}else {
						Object getServerData = CoreReflection.getFieldValue(new_ServerPing, "c");
						CoreReflection.setFieldValue(getServerData, "b", 1);
						CoreReflection.setFieldValue(getServerData, "a", CoreColor.colorCodes(this.playercount));
					}
					
					
				}
				json = (JsonElement) this.new_ServerPing_Serializer.getClass().getMethod("a", new_ServerPing.getClass(),Type.class,JsonSerializationContext.class).invoke(new_ServerPing_Serializer, new_ServerPing,arg1,arg2);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
				e.printStackTrace();
			}
			return json;
		}
	}
	
	 private static Object getIChatBaseComponent(String message) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		 Class<?> Class_IChatBaseComponent;
		 if(CoreVersion.getVersion().esMayorIgual(CoreVersion.v1_17)) {
				Class_IChatBaseComponent = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
		  }else {
				Class_IChatBaseComponent = CoreReflection.getNMSClass("IChatBaseComponent");
		  }
		 return Class_IChatBaseComponent.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}" );
	 }	
}
