package com.pedrojm96.core.effect;

import java.lang.reflect.Constructor;
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



/**
 * Contiene los metodos para cambiar el motd servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.1 19-08-2022
 *
 */
public class CoreServerPingInfo {

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
	
	private static Gson oldjson = null;

	private static boolean change;
	
	public static Class<?> Class_IChatBaseComponent = CoreReflection.getNMSClass("IChatBaseComponent");
	public static Class<?> Class_ServerPing_ServerData = CoreReflection.getNMSClass("ServerPing$ServerData");
	public static Class<?> Class_ServerPing_ServerPingPlayerSample = CoreReflection.getNMSClass("ServerPing$ServerPingPlayerSample");
	public static Class<?> Class_ServerPing = CoreReflection.getNMSClass("ServerPing");
	public static Class<?> Class_ChatModifier = CoreReflection.getNMSClass("ChatModifier");
	public static Class<?> Class_ServerPing_ServerData_Serializer = CoreReflection.getNMSClass("ServerPing$ServerData$Serializer");
	public static Class<?> Class_ServerPing_ServerPingPlayerSample_Serializer = CoreReflection.getNMSClass("ServerPing$ServerPingPlayerSample$Serializer");
	public static Class<?> Class_ServerPing_Serializer = CoreReflection.getNMSClass("ServerPing$Serializer");
	public static Class<?> Class_IChatBaseComponent_ChatSerializer = CoreReflection.getNMSClass("IChatBaseComponent$ChatSerializer");
	public static Class<?> Class_ChatModifier_ChatModifierSerializer = CoreReflection.getNMSClass("ChatModifier$ChatModifierSerializer");
	public static Class<?> Class_ChatTypeAdapterFactory = CoreReflection.getNMSClass("ChatTypeAdapterFactory");
	public static Class<?> Class_PacketStatusOutServerInfo = CoreReflection.getNMSClass("PacketStatusOutServerInfo");
	
	
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
		CoreReflection.setStaticField(Class_PacketStatusOutServerInfo, "a", oldjson);
		change = false;
	}
	

	private static void change(String motd,String hover,String playercount) {
		
		if(!change) {
			try{
				
				/**
				 * ServerData Serialiser *****************************************************************
				 */
				
				Constructor<?> Constructor_ServerPing_ServerData_Serializer = Class_ServerPing_ServerData_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_ServerData_Serializer = Constructor_ServerPing_ServerData_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				
				/**
				 * ServerPingPlayerSample Serialiser *****************************************************************
				 */
				
				Constructor<?> Constructor_ServerPing_ServerPingPlayerSample_Serializer = Class_ServerPing_ServerPingPlayerSample_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_ServerPingPlayerSample_Serializer = Constructor_ServerPing_ServerPingPlayerSample_Serializer.newInstance(new Object[0]);
				
				/**
				 * *****************************************************************************************
				 */
				
				/**
				 * ServerPing Serialiser *****************************************************************
				 */
				
				Constructor<?> Constructor_ServerPing_Serializer = Class_ServerPing_Serializer.getConstructor(new Class[0]);
				Object new_ServerPing_Serializer = Constructor_ServerPing_Serializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				
				/**
				 * IChatBaseComponent ChatSerialiser *****************************************************************
				 */
				
				Constructor<?> Constructor_IChatBaseComponent_ChatSerializer = Class_IChatBaseComponent_ChatSerializer.getConstructor(new Class[0]);
				Object new_IChatBaseComponent_ChatSerializer = Constructor_IChatBaseComponent_ChatSerializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				
				/**
				 * ChatModifier ChatModifierSerializer *****************************************************************
				 */
				
				Constructor<?> Constructor_ChatModifier_ChatModifierSerializer = Class_ChatModifier_ChatModifierSerializer.getConstructor(new Class[0]);
				Object new_ChatModifier_ChatModifierSerializer = Constructor_ChatModifier_ChatModifierSerializer.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				
				/**
				 * ChatTypeAdapterFactory *****************************************************************
				 */
				
				Constructor<?> Constructor_ChatTypeAdapterFactory = Class_ChatTypeAdapterFactory.getConstructor(new Class[0]);
				Object new_ChatTypeAdapterFactory = Constructor_ChatTypeAdapterFactory.newInstance(new Object[0]);
				/**
				 * *****************************************************************************************
				 */
				
				
				
				oldjson = (Gson) CoreReflection.getStaticField(Class_PacketStatusOutServerInfo, "a");

				Gson a = new GsonBuilder().registerTypeAdapter(Class_ServerPing_ServerData, new_ServerPing_ServerData_Serializer)
		                .registerTypeAdapter(Class_ServerPing_ServerPingPlayerSample,new_ServerPing_ServerPingPlayerSample_Serializer)
		                .registerTypeAdapter(Class_ServerPing, new PingSerializer(motd,hover,playercount,new_ServerPing_Serializer))
		                .registerTypeHierarchyAdapter(Class_IChatBaseComponent, new_IChatBaseComponent_ChatSerializer)
		                .registerTypeHierarchyAdapter(Class_ChatModifier, new_ChatModifier_ChatModifierSerializer)
		                .registerTypeAdapterFactory((TypeAdapterFactory) new_ChatTypeAdapterFactory).create();
			
				
				
				CoreReflection.setStaticField(Class_PacketStatusOutServerInfo, "a", a);
				change = true;

				}catch (Exception var11){
					System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			    	var11.printStackTrace();
			    }
		}
		
		
		
		
	}
	
	
	@SuppressWarnings("rawtypes")
	public static class PingSerializer implements JsonSerializer {

		private String motd;
		private String hover;
		private String playercount;
		private Object new_ServerPing_Serializer;
		public PingSerializer(String motd,String hover,String playercount,Object new_ServerPing_Serializer){
			this.motd =  motd;
			this.hover =  hover;
			this.playercount =  playercount;
			this.new_ServerPing_Serializer = new_ServerPing_Serializer;
		}
		
		
		@Override
		public JsonElement serialize(Object new_ServerPing, Type arg1, JsonSerializationContext arg2) {
			
			
			
			// TODO Auto-generated method stub
			JsonElement json = null;
			try {
				
				 
				
				if(this.motd!=null) {
					new_ServerPing.getClass().getMethod("setMOTD", Class_IChatBaseComponent ).invoke(new_ServerPing, getIChatBaseComponent(CoreColor.colorCodes(this.motd)));
				}
				
				if(this.hover!=null) {
					Object getServerPingPlayerSample = CoreReflection.getFieldValue(new_ServerPing, "b");
					CoreReflection.setFieldValue(getServerPingPlayerSample, "c", new GameProfile[] {new GameProfile(new UUID(0,0), CoreColor.colorCodes(this.hover))});
				
				}
				
				if(this.playercount!=null) {
					Object getServerData = CoreReflection.getFieldValue(new_ServerPing, "c");
					CoreReflection.setFieldValue(getServerData, "b", 1);
					CoreReflection.setFieldValue(getServerData, "a", CoreColor.colorCodes(this.playercount));

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
		 
		 return Class_IChatBaseComponent.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}" );
	 }
	
	
	
	 
	 
	 
	 
	 
	 
	 
	
}
