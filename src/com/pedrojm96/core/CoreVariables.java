package com.pedrojm96.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pedrojm96.superstats.SuperStatsAPI;

import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

/**
 * Reemplaza parametros dentro de corchetes validos {} por variables internas, vaul y placeholderapi servidor de minecraft implementando la api de bukkt/spigot.
 * Para dar soporte al chat, permisos y placeholderapi se deben iniciar sus respetivos metodos.
 * 
 * @author PedroJM96
 * @version 1.5 05-09-2022
 *
 */
public class CoreVariables {

	private static Permission permission = null;
	private static Chat chat = null;
	public static List<String> vu = new ArrayList<String>();
	private static boolean placeholderAPI = false;
	private static SuperStatsAPI superstatsAPI;
	public static void superstats(SuperStatsAPI sstats)
	{
	    superstatsAPI = sstats;
	}
	
	public static void permission(Permission per) {
		permission = per;
	}
	
	public static void chat(Chat cha) {
		chat = cha;
	}

	public static void placeholderAPI(boolean api) {
		placeholderAPI = api;
	}

	public static String replace(String string, Player p){
		if(string == null){
			return "";
		}
		String newString = string;
		if (newString.contains("<player>")){
			newString = newString.replaceAll("<player>", p.getName());
		}
		if (newString.contains("<server>")){
			newString = newString.replaceAll("<server>", p.getServer().getName());
		}
		if (newString.contains("<displayname>")){
			newString = newString.replaceAll("<displayname>", p.getDisplayName());
		}
		if (newString.contains("<world>")){
			newString = newString.replaceAll("<world>", p.getWorld().getName());
		}
		if (newString.contains("<online>")){
			newString = newString.replaceAll("<online>", String.valueOf(Bukkit.getOnlinePlayers().size()));
		}
		if (newString.contains("<player-x>")){
			newString = newString.replaceAll("<player-x>", String.valueOf(p.getLocation().getBlockX()));
		}
		if (newString.contains("<player-y>")){
			newString = newString.replaceAll("<player-y>", String.valueOf(p.getLocation().getBlockY()));
		}
		if (newString.contains("<player-z>")){
			newString = newString.replaceAll("<player-z>", String.valueOf(p.getLocation().getBlockZ()));
		}
		if (newString.contains("<rank>")){
			if(permission != null) {
				if(!permission.getName().equalsIgnoreCase("SuperPerms")) {
					if(p.isOnline()) {
						String g = permission.getPrimaryGroup(p);
						if(g!=null || g !="" ) {
							newString = newString.replaceAll("<rank>", g);
						}
					}
				}
			}	
		}
		if(chat != null) {
			if (newString.contains("<prefix>")){
				newString = newString.replaceAll("<prefix>", chat.getPlayerPrefix(p));
			}
			if (newString.contains("<suffix>")){
				newString = newString.replaceAll("<suffix>", chat.getPlayerSuffix(p));
			}
		}
		newString = replaceUcode(newString);
		if(placeholderAPI){
			try{
				newString = PlaceholderAPI.setPlaceholders(p, newString);
			}catch (Exception var11){
				var11.printStackTrace();
		    }
		}
		if (superstatsAPI != null)
	    {
	      newString = superstatsAPI.replaceStats(newString, p);
	      newString = superstatsAPI.replaceTopStats(newString);
	    }
		newString = CoreColor.colorCodes(newString);
		return newString;
	}
	
	public static String replace(String string, OfflinePlayer p){
		if(string == null){
			return "";
		}
		String newString = string;
		if (newString.contains("<player>")){
			newString = newString.replaceAll("<player>", p.getName());
		}
		newString = replaceUcode(newString);
		if(placeholderAPI){
			try{
				newString = PlaceholderAPI.setPlaceholders(p, newString);
				
			}catch (Exception var11){
		    	
		    }
		}
		if (superstatsAPI != null)
	    {
	      newString = superstatsAPI.replaceStats(newString, p.getUniqueId().toString());
	      newString = superstatsAPI.replaceTopStats(newString);
	    }
		newString = CoreColor.colorCodes(newString);
		return newString;
	}
	
	public static List<String> replaceList(List<String> list,Player p) {
		List<String> localList = new ArrayList<String>();
		localList.addAll(list);
		for (int i = 0; i < localList.size(); i++) {
			localList.set(i, replace(localList.get(i),p));
        }
        return localList;
	}
	
	public static List<String> replaceList(List<String> list,OfflinePlayer p) {
		List<String> localList = new ArrayList<String>();
		localList.addAll(list);
		for (int i = 0; i < localList.size(); i++) {
			localList.set(i, replace(localList.get(i),p));
        }
        return localList;
	}
	
	public static String replaceConsole(String string, CommandSender p){
		String newString = string;
		if (newString.contains("<player>")){
			newString = newString.replaceAll("<player>", p.getName());
		}
		newString = replaceUcode(newString);
		return newString;
	}
	 
	public static String replaceUcode(String string){
		String newString = string;
	    while (newString.contains("<ucode"))
	    {
	      String code = newString.split("<ucode")[1].split(">")[0];
	      newString = newString.replaceAll("<ucode" + code + ">", 
	        String.valueOf((char)Integer.parseInt(code, 16)));
	    }
	    if (newString.contains("<a>")) {
	    	newString = newString.replaceAll("<a>", "á");
	    }
	    if (newString.contains("<e>")) {
	    	newString = newString.replaceAll("<e>", "é");
	    }
	    if (newString.contains("<i>")) {
	    	newString = newString.replaceAll("<i>", "í");
	    }
	    if (newString.contains("<o>")) {
	    	newString = newString.replaceAll("<o>", "ó");
	    }
	    if (newString.contains("<u>")) {
	    	newString = newString.replaceAll("<u>", "ú");
	    }
	    int s = 1;
	    for(String v : vu){
	    	if (newString.contains("<" + s + ">")){
	    		 newString = newString.replaceAll("<" + s + ">",v);
	    	}
	    	s = s + 1;
	    }
	    return newString;
	}
	
	public static void iniUcode(){
		vu.add("✖"); //1
		vu.add("♥"); //2
		vu.add("★");//3
		vu.add("●");//4
		vu.add("♦");//5
		vu.add("☻");//6
		vu.add("►");//7
		vu.add("◄");//8
		vu.add("▬");//9
		vu.add("✪");//10
		vu.add("✔");//11
		vu.add("❉");//12
		vu.add("■");//13
		vu.add("▀");//14
		vu.add("▄");//15
		vu.add("☠");//16
		vu.add("☭");//17
		vu.add("™");//18
		vu.add("◢");//19
		vu.add("◣");//20
		vu.add("❣");//21
		vu.add("☀");//22
		vu.add("❀");//23
		vu.add("☪");//24
		vu.add("☣");//25
		vu.add("☒");//26
		vu.add("☎");//27
		vu.add("░");//28
		vu.add("☑");//29
		vu.add("»");//30
		vu.add("«");//31
		vu.add("¿");//32
	}
}
