package com.pedrojm96.core.effect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreUtils;

/**
 * Contiene los metodos para enviar mensajes BossBar a los jugadores en multiples versiones en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.3 05-09-2022
 *
 */
public class CoreBossBar {
	
	public static float bossBarProgre =  0.5f;
	private static Map<UUID, FakeBossBar> fakeBossbar = new ConcurrentHashMap<>();
	private static HashMap<UUID, Integer> timers = new HashMap<UUID, Integer>();
	public static enum Color
	{
	    PINK,  BLUE,  RED,  GREEN,  YELLOW,  PURPLE,  WHITE;
	}
	public static enum Style
	{
	    PROGRESS,  NOTCHED_6,  NOTCHED_10,  NOTCHED_12,  NOTCHED_20;
	}

	public static void sendBossBar(Player player, String mensaje, String color, String estilo,int seconds,Plugin plugin) {
		 Color c = Color.BLUE;
		 Style s = Style.PROGRESS; 
		 try {
			 c = Color.valueOf(color);
			 s = Style.valueOf(estilo);
		 }catch (IllegalArgumentException e) {
			 c = Color.BLUE;
			 s = Style.PROGRESS;
		 }
		 sendBossBar(player,mensaje, c, s,seconds,plugin);
	 }
	
	 public static void sendBossBar(Player player, String mensaje, Color color, Style estilo,int seconds,Plugin plugin) {
		 sendBossBar(player,CoreColor.colorCodes(mensaje), color, estilo,seconds,3,plugin);
	 }
	 
	private static void sendBossBar(final Player player, final String mensaje, Color color, Style estilo,int seconds,int interval,Plugin plugin)
	{
		if (hasSendBar(player)) {
			removeSendBar(player);
		}
		if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_8_8)) {
			sendPre1_9(player, mensaje, seconds, 0,interval,plugin);
		}else {
			sendPos1_9(player, mensaje, color,estilo,seconds,0, interval,plugin);	
		}
	 }

	private static void sendPos1_9(final Player player,final String mensaje, final Color color, final Style style,int seconds, final float pro,int interval,Plugin plugin) {
		FakeBossBar bar = getBossBar(plugin,player,cleanMessage(mensaje),color,style,pro);
		bar.send();
		final float progressMinus = (bar.getProgress() / (seconds*(20/interval)));
		cancelTimer(player);
		timers.put(player.getUniqueId(), Integer.valueOf(Bukkit.getScheduler().runTaskTimer(plugin, new Runnable()
	    {
	      public void run()
	      {
	    	  FakeBossBar bar = fakeBossbar.get(player.getUniqueId());
	    	float newProgress = bar.getProgress() - progressMinus;
	    	if (newProgress <= 0.0F) {
	    		canselBoss(player);
		        cancelTimer(player);
	    	}else {
	    		bar.setProgress(newProgress);
	    	}
	      }
	    }, interval, interval).getTaskId()));	
	}

	private static void sendPre1_9(final Player player, final String mensaje, int seconds, final float pro,int interval,Plugin plugin) {
		FakeBossBar bar = getBossBar(plugin,player,cleanMessage(mensaje),null,null,pro);
		bar.send();
		final float progressMinus = (bar.getProgress() / (seconds*(20/interval)));
		cancelTimer(player);
		timers.put(player.getUniqueId(), Integer.valueOf(Bukkit.getScheduler().runTaskTimer(plugin, new Runnable()
	    {
	      public void run()
	      {
	    	  FakeBossBar bar = fakeBossbar.get(player.getUniqueId());
	    	float newProgress = bar.getProgress() - progressMinus;
	        if (newProgress <= 1.0F)
	        {
	        	canselBoss(player);
	        	cancelTimer(player);
	        }
	        else
	        {
	          bar.setProgress(newProgress);
	        }
	      }
	    }, interval, interval).getTaskId()));
	}
	
	private static void canselBoss(Player player) {
		if(fakeBossbar.containsKey(player.getUniqueId())) {
			FakeBossBar bar = fakeBossbar.get(player.getUniqueId());
			bar.destroy();
			fakeBossbar.remove(player.getUniqueId());
		}
	}
	
	public static void canselAll() {
		for(UUID uuid : fakeBossbar.keySet()) {
			FakeBossBar bar = fakeBossbar.get(uuid);
			bar.destroy();
			fakeBossbar.remove(uuid);
			Integer timerID = (Integer)timers.remove(uuid);
		    if (timerID != null) {
		      Bukkit.getScheduler().cancelTask(timerID.intValue());
		    }
		}
	}

	
	private static FakeBossBar getBossBar(Plugin plugin, Player player,String name, Color color, Style style,float pro) {
		FakeBossBar bar = null;
			if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_8_8)) {
				bar = new FakeBossBarPre1_9(player,name,pro);
			}else if(CoreUtils.Version.getVersion().esMenorIgual(CoreUtils.Version.v1_16_x)){
				bar = new FakeBossBarPos_1_9(player,name,color,style,pro);
			}else {
				bar = new FakeBossBarPos_1_17(plugin, player,name,color,style,pro);
			}
			fakeBossbar.put(player.getUniqueId(), bar);
			return bar;
	}
	
	 private static String cleanMessage(String message)
	 {
	    if (message.length() > 64) {
	      message = message.substring(0, 63);
	    }
	    return message;
	 }
	
	 private static void cancelTimer(Player player)
	 {
	    Integer timerID = (Integer)timers.remove(player.getUniqueId());
	    if (timerID != null) {
	      Bukkit.getScheduler().cancelTask(timerID.intValue());
	    }
	 }
	 
	 private static void removeSendBar(Player player)
	  {
	    if (!hasSendBar(player)) {
	      return;
	    }
	    canselBoss(player);
	    cancelTimer(player);
	  }

	private static boolean hasSendBar(Player player)
	{
		return (fakeBossbar.get(player.getUniqueId()) != null );
	}
}
