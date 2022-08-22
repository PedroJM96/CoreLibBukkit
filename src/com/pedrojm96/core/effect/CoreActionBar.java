package com.pedrojm96.core.effect;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreReflection;
import com.pedrojm96.core.CoreUtils;


/**
 * Contiene los metodos para enviar mensajes ActionBar a los jugadores en multiples versiones en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.7 22-08-2022
 *
 */
public class CoreActionBar {

	public static Map<UUID, Integer> timers = new HashMap<UUID, Integer>();
	
	
	private static class SendRun extends BukkitRunnable{

		private int duration = 0;
		private Player player;
		private String message;
		
		public SendRun(int duration,Player player,String message) {
			this.duration = duration;
			this.player = player;
			this.message = message;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(this.duration>0) {
				sendActionBar(player, CoreColor.colorCodes(message));
				duration = duration-20;
			}else {
				this.cancel();
				timers.remove(player.getUniqueId());
			}
		}
		
	}
	
	
	private static void cancelTimer(Player player)
	  {
	    Integer timerID = (Integer)timers.remove(player.getUniqueId());
	    if (timerID != null) {
	      Bukkit.getScheduler().cancelTask(timerID.intValue());
	    }
	  }
	
	
	
	public static void sendActionBar(Player player, String message, int duration,Plugin plugin) {
		if(timers.containsKey(player.getUniqueId())) {
			cancelTimer(player);
		}
		timers.put(player.getUniqueId(), Integer.valueOf(new SendRun(duration,player,message).runTaskTimer(plugin, 0L, 20L).getTaskId()));
	}
	
	public static void sendActionBar(Player player, String message) {
		if (!player.isOnline()) {
			return; // Player may have logged out
		}
		if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_11_x)) {
			sendPre_1_12(player,CoreColor.colorCodes(message));
		}else if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_15_x) ){
			sendPos_1_12_Pre_1_16(player,CoreColor.colorCodes(message));
		}else if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_16_x) ){
			sendPos_1_16_Pre_1_17(player,CoreColor.colorCodes(message));
		}else if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_17_x) ){
			sendPos_1_17(player,CoreColor.colorCodes(message));
		}else if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_18_x) ){
			sendPos_1_18(player,CoreColor.colorCodes(message));
		}else if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_19) ){
			sendPos_1_19(player,CoreColor.colorCodes(message));
		}else {
			sendPos_1_19_2(player,CoreColor.colorCodes(message));
		}
		
	}
	
	
	private static void sendPos_1_18(Player player, String message)
	  {
	    try
	    
	    {
	    	Class<?> chatClass = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
	    	
	    	Object ationmesage = CoreReflection.getClass("net.minecraft.network.chat.ChatComponentText").getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });

	    	Class<?> chatMessageTypeClass = CoreReflection.getClass("net.minecraft.network.chat.ChatMessageType");
	    	
	    	Constructor<?> constructor = CoreReflection.getClass("net.minecraft.network.protocol.game.PacketPlayOutChat").getConstructor(chatClass,chatMessageTypeClass,Class.forName("java.util.UUID"));
	    	// c es igual a GAME_INFO
	    	Object packet = constructor.newInstance(ationmesage,chatMessageTypeClass.getField("c").get(null),player.getUniqueId()  );

	    	CoreReflection.sendPacketPos_1_18(player, packet);
	    }
	    catch (Exception var11)
	    {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      var11.printStackTrace();
	    }
	  }
	
	private static void sendPos_1_19(Player player, String message)
	  {
	    try
	    
	    {
	    	Class<?> chatClass = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
	    	//Class<?> chatSerialiceClass = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent$ChatSerializer");
	    	
	    	//Object ationmesage = chatSerialiceClass.getMethod("a",String.class).invoke(new Object[] { "{\"text\": \"" + message + "\"}" });
	    	Object ationmesage = chatClass.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}" );

	    	Constructor<?> constructor = CoreReflection.getClass("net.minecraft.network.protocol.game.ClientboundSystemChatPacket").getConstructor(chatClass,int.class);
	    	// c es igual a GAME_INFO
	    	Object packet = constructor.newInstance(ationmesage,2);

	    	CoreReflection.sendPacketPos_1_19(player, packet);
	    }
	    catch (Exception var11)
	    {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      var11.printStackTrace();
	    }
	  }
	
	
	private static void sendPos_1_19_2(Player player, String message)
	  {
	    try
	    
	    {
	    	Class<?> chatClass = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
	    	//Class<?> chatSerialiceClass = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent$ChatSerializer");
	    	
	    	//Object ationmesage = chatSerialiceClass.getMethod("a",String.class).invoke(new Object[] { "{\"text\": \"" + message + "\"}" });
	    	Object ationmesage = chatClass.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}" );

	    	Constructor<?> constructor = CoreReflection.getClass("net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket").getConstructor(chatClass);
	    	// c es igual a GAME_INFO
	    	Object packet = constructor.newInstance(ationmesage);

	    	CoreReflection.sendPacketPos_1_19(player, packet);
	    }
	    catch (Exception var11)
	    {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      var11.printStackTrace();
	    }
	  }
	
	
	private static void sendPos_1_17(Player player, String message)
	  {
	    try
	    
	    {
	    	Class<?> chatClass = CoreReflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
	    	
	    	Object ationmesage = CoreReflection.getClass("net.minecraft.network.chat.ChatComponentText").getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });

	    	Class<?> chatMessageTypeClass = CoreReflection.getClass("net.minecraft.network.chat.ChatMessageType");
	    	
	    	Constructor<?> constructor = CoreReflection.getClass("net.minecraft.network.protocol.game.PacketPlayOutChat").getConstructor(chatClass,chatMessageTypeClass,Class.forName("java.util.UUID"));
	    	// c es igual a GAME_INFO
	    	Object packet = constructor.newInstance(ationmesage,chatMessageTypeClass.getField("c").get(null),player.getUniqueId()  );

	    	CoreReflection.sendPacketPos_1_17(player, packet);
	    }
	    catch (Exception var11)
	    {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      var11.printStackTrace();
	    }
	  }
	
	
	
	private static void sendPos_1_16_Pre_1_17(Player player, String message)
	  {
	    try
	    
	    {
	    	Class<?> chat = CoreReflection.getNMSClass("IChatBaseComponent");
	    	
	    	Object ationmesage = CoreReflection.getNMSClass("ChatComponentText").getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });

	    	Class<?> chatMessageTypeClass = CoreReflection.getNMSClass("ChatMessageType");
	    	
	    	Constructor<?> constructor = CoreReflection.getNMSClass("PacketPlayOutChat").getConstructor(chat,chatMessageTypeClass,Class.forName("java.util.UUID"));
	    	
	    	Object packet = constructor.newInstance(ationmesage,CoreReflection.getNMSClass("ChatMessageType").getField("GAME_INFO").get(null),player.getUniqueId()  );

	    	CoreReflection.sendPacket(player, packet);
	    }
	    catch (Exception var11)
	    {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      var11.printStackTrace();
	    }
	  }
	
	
	
	private static void sendPos_1_12_Pre_1_16(Player player, String message)
	  {
	    try
	    
	    {
	    	Class<?> chat = CoreReflection.getNMSClass("IChatBaseComponent");
	    	
	    	Object ationmesage = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}" );
	    	
	    	Class<?> chatMessageTypeClass = CoreReflection.getNMSClass("ChatMessageType");
	    	
	    	Constructor<?> constructor = CoreReflection.getNMSClass("PacketPlayOutChat").getConstructor(chat,chatMessageTypeClass);
	    	
	    	Object packet = constructor.newInstance(ationmesage,CoreReflection.getNMSClass("ChatMessageType").getField("GAME_INFO").get(null));

	    	CoreReflection.sendPacket(player, packet);
	    }
	    catch (Exception var11)
	    {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      var11.printStackTrace();
	    }
	  }
	
	private static void sendPre_1_12(Player player, String message)
	  {
	    try
	    
	    {
	    	Class<?> chat = CoreReflection.getNMSClass("IChatBaseComponent");
	    	
	    	Object ationmesage = chat.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}" );
	    	
	    	
	    	Constructor<?> constructor = CoreReflection.getNMSClass("PacketPlayOutChat").getConstructor(chat,byte.class);
	    	
	    	Object packet = constructor.newInstance(ationmesage,(byte) 2);

	    	CoreReflection.sendPacket(player, packet);
	    }
	    catch (Exception var11)
	    {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      var11.printStackTrace();
	    }
	  }
}
