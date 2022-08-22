package com.pedrojm96.core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
/**
 * Utilidades para el core.
 * 
 * @author PedroJM96
 * @version 1.7 22-08-2022
 *
 */
public class CoreUtils {
	
	
	public enum Version{
		
		v1_5(10500),
		v1_6(10600),
		v1_7(10700),
		v1_8_8(10808),
		v1_8_9(10809),
		v1_9(10900),
		v1_9_1(10901),
		v1_9_2(10902),
		v1_9_3(10903),
		v1_9_4(10904),
		v1_9_x(10910),
		v1_10(11000),
		v1_10_1(11001),
		v1_10_2(11002),
		v1_10_x(11010),
		v1_11(11100),
		v1_11_1(11101),
		v1_11_2(11102),
		v1_11_x(11110),
		v1_12(11200),
		v1_12_1(11201),
		v1_12_2(11202),
		v1_12_x(11210),
		v1_13(11300),
		v1_13_1(11301),
		v1_13_2(11302),
		v1_13_x(11310),
		v1_14(11400),
		v1_14_1(11401),
		v1_14_2(11402),
		v1_14_3(11403),
		v1_14_4(11404),
		v1_14_x(11410),
		v1_15(11500),
		v1_15_1(11501),
		v1_15_2(11502),
		v1_15_x(11510),
		v1_16(11600),
		v1_16_1(11601),
		v1_16_2(11602),
		v1_16_3(11603),
		v1_16_4(11604),
		v1_16_5(11605),
		v1_16_x(11610),
		v1_17(11700),
		v1_17_1(11701),
		v1_17_x(11710),
		v1_18(11800),
		v1_18_1(11801),
		v1_18_2(11802),
		v1_18_x(11810),
		v1_19(11900),
		v1_19_1(11901),
		v1_19_2(11902),
		v1_19_x(11910),
		vUnsupported(1000000);
		
		private int value;
		
		private boolean contains;
		private boolean equals;
		
		private Version(int n) {
			this.value = n;
			this.contains = Bukkit.getBukkitVersion().split("-")[0].contains(this.toString());
			this.equals = Bukkit.getBukkitVersion().split("-")[0].equalsIgnoreCase(this.toString());
		}
		
		private int getValue() {
			return value;
		}
		
		public boolean Is() {
			return this.contains;	
		}
		
		public boolean IsEquals() {
			return this.equals;	
		}
		
		public String toString() {
			return this.name().replaceAll("_", ".").split("v")[1];
		}
		
		public boolean esMayorr(Version v) {
			return (this.getValue() > v.getValue());
		}
		
		
		public boolean esMayorIgual(Version v) {
			return (this.getValue() >= v.getValue());
		}
		
		public boolean esMenor(Version v) {
			return (this.getValue() < v.getValue());
		}
		
		public boolean esMenorIgual(Version v) {
			return (this.getValue() <= v.getValue());
		}
		
		
		public static Version getVersion() {
			
			Version retorno = Version.vUnsupported;
			for(Version version : Version.values()) {
				if(version.IsEquals()) {
					retorno =  version;
					break;
				}
			}
			return retorno;
		}
	
	}
	
	
	
	
	public static boolean mc1_7 = Bukkit.getBukkitVersion().split("-")[0].contains("1.7");
	public static boolean mc1_8 = Bukkit.getBukkitVersion().split("-")[0].contains("1.8");
	public static boolean mc1_9 = Bukkit.getBukkitVersion().split("-")[0].contains("1.9");
	public static boolean mc1_10 = Bukkit.getBukkitVersion().split("-")[0].contains("1.10");
	public static boolean mc1_11 = Bukkit.getBukkitVersion().split("-")[0].contains("1.11");
	public static boolean mc1_12 = Bukkit.getBukkitVersion().split("-")[0].contains("1.12");
	public static boolean mc1_13 = Bukkit.getBukkitVersion().split("-")[0].contains("1.13");
	public static boolean mc1_13_0 = Bukkit.getBukkitVersion().split("-")[0].equalsIgnoreCase("1.13");
	public static boolean mc1_13_1 = Bukkit.getBukkitVersion().split("-")[0].contains("1.13.1");
	
	public static Map<String, String[]> playerTexturesCache =  new HashMap<String, String[]>();
	
	
	public static String[] getPlayerTextureLocal(Player playerBukkit , CoreLog log) {
		String[] a = null;
		Class<?> strClass = (Class<?>) CoreReflection.getCraftClass("entity.CraftPlayer");
		GameProfile profile;
		try {
			profile = (GameProfile) strClass.cast(playerBukkit).getClass().getMethod("getProfile").invoke(strClass.cast(playerBukkit));
				//(GameProfile) handle.getClass().getMethod("getProfile").invoke(handle);	
			Property colle = profile.getProperties().get("textures").iterator().next();
			String texture = colle.getValue();	
			String signature = colle.getSignature();
			a = new String[] {texture, signature};
			log.debug("Textura obtenida localmente para: "+playerBukkit.getName());
		} catch (Exception e) {
			log.debug("Error al intentar obtener texturas localmente para "+playerBukkit.getName(),e);
		}
		return a;
	}
	
	public static String[] getTextureFromMojang(String name,CoreLog log) {
        try {
        	if(playerTexturesCache.containsKey(name)) {
        		log.debug("Textura obtenida desde el cache de mojan para: "+name);
             	return playerTexturesCache.get(name);
        	}else {
        		URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
                String uuid = new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();
                URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
                InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());
                JsonObject textureProperty = new JsonParser().parse(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
                String texture = textureProperty.get("value").getAsString();
                String signature = textureProperty.get("signature").getAsString();
                log.debug("Textura obtenida desde la web de mojan para: "+name);
                String[] playerTexture = new String[] {texture, signature};
                playerTexturesCache.put(name, playerTexture);
                return playerTexture;
        	}
        } catch (IOException | IllegalStateException e) {
        	log.debug("Error al intentar obtener texturas desde la web mojan para "+name, e);
            return null;
        }
    }
	
	public static boolean isdouble(String s){
		try{
			@SuppressWarnings("unused")
			double i = Double.parseDouble(s);
			return true;
		}
		catch(NumberFormatException er){
			return false;
		}
		
	}
	
	public static boolean isint(String s){
		try{
			@SuppressWarnings("unused")
			int i = Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException er){
			return false;
		}
		
	}
	
	public static int toint(String s){
		try{
			int i = Integer.parseInt(s);
			return i;
		}
		catch(NumberFormatException er){
			return 0;
		}	
	}
	
	public static int toint(String s,int error){
		try{
			int i = Integer.parseInt(s);
			return i;
		}
		catch(NumberFormatException er){
			return error;
		}	
	}
	
	
	
	public static long toLong(String s){
		try{
			
			long i = Long.parseLong(s);
			return i;
		}
		catch(NumberFormatException er){
			return 0;
		}
		
	}
	
	public static boolean isEnum(Class<?> class1, String value ) {
		try {
			@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
			Object obj = Enum.valueOf((Class<Enum>)class1, value);
			return true;
		}catch(IllegalArgumentException ex){
			return false;
		}
		
	}
	
	
	public static boolean isinteger(String s){
		try{
			@SuppressWarnings("unused")
			Integer i = Integer.valueOf(s);
			return true;
		}
		catch(NumberFormatException er){
			return false;
		}
		
	}
	public static Integer integerValue(String s){
		try{
			Integer i = Integer.valueOf(s);
			return i;
		}
		catch(NumberFormatException er){
			return 0;
		}
		
	}
	
	public static Double doubleValue(String s){
		try{
			Double i = Double.valueOf(s);
			return i;
		}
		catch(NumberFormatException er){
			return 0.0;
		}
		
	}
	
	 public  static String formatime(long segundos){
		    String format = "<dd>d:<hh>h:<mm>m:<ss>s";
	    	int d =0;
	    	int h = 0;
	    	int m = 0;
	    	while(segundos>60){
	    		if(h>12){
	    			d++;
	    			h=0;
	    		}
	    		if(m>60){
	    			h++;
	    			m=0;
	    		}
	    		segundos = segundos - 60;
	    		m++;
	    	}
	    	if(d==0 && h==0 && m==0){
	    		String forma = format.replaceAll("<dd>", String.valueOf(d)).replaceAll("<hh>", String.valueOf(h)).replaceAll("<mm>", String.valueOf(m)).replaceAll("<ss>", String.valueOf(segundos));
	    		return forma;
	    	}
	    	
	    	if(d==0 && h==0){
	    		String forma = format.replaceAll("<dd>", String.valueOf(d)).replaceAll("<hh>", String.valueOf(h)).replaceAll("<mm>", String.valueOf(m)).replaceAll("<ss>", String.valueOf(segundos));
	    		return forma;
	    	}
	    	if(d==0){
	    		String forma = format.replaceAll("<dd>", String.valueOf(d)).replaceAll("<hh>", String.valueOf(h)).replaceAll("<mm>", String.valueOf(m)).replaceAll("<ss>", String.valueOf(segundos));
	    		return forma;
	    	}
	    	String forma = format.replaceAll("<dd>", String.valueOf(d)).replaceAll("<hh>", String.valueOf(h)).replaceAll("<mm>", String.valueOf(m)).replaceAll("<ss>", String.valueOf(segundos));
			return forma;
	    }
	
	 
	 public  static String formatimeClear(long segundos){
		 	int d =0;
	    	int h = 0;
	    	int m = 0;
	    	while(segundos>60){
	    		if(h>12){
	    			d++;
	    			h=0;
	    		}
	    		if(m>60){
	    			h++;
	    			m=0;
	    		}
	    		segundos = segundos - 60;
	    		m++;
	    	}
	    	if(d==0 && h==0 && m==0){
	    		String forma = segundos + "s";
	    		return forma;
	    	}
	    	
	    	if(d==0 && h==0){
	    		String forma = m+"m " + segundos + "s";
	    		return forma;
	    	}
	    	if(d==0){
	    		String forma = h+"h " + m+"m " + segundos + "s";
	    		return forma;
	    	}
	    	String forma = d + "d " + h+"h " + m+"m " + segundos + "s";
			return forma;
	    }
	 
	 
	 public static String timeLeft(int timeoutSeconds)
	 {
	     int days = (int) (timeoutSeconds / 86400);
	     int hours = (int) (timeoutSeconds / 3600) % 24;
	     int minutes = (int) (timeoutSeconds / 60) % 60;
	     int seconds = timeoutSeconds % 60;
	     return (days > 0 ? " " + days + " d" : "") + (hours > 0 ? " " + hours + " h"  : "")
	             + (minutes > 0 ? " " + minutes + " m"  : "") + (seconds > 0 ? " " + seconds + " s"  : "");
	 }
	 
	
	
	public static ArrayList<Location> getCircleLocation(Location center, double radius, int amount){
        World world = center.getWorld();
        double increment = (2*Math.PI)/amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for(int i = 0;i < amount; i++){
        double angle = i*increment;
        double x = center.getX() + (radius * Math.cos(angle));
        double z = center.getZ() + (radius * Math.sin(angle));
        locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }
	
	public static List<String> jsonArrayStringToList(String json) {
		JsonArray jsonArry = new JsonParser().parse(json).getAsJsonArray();
		Type listType = new TypeToken<List<String>>() {}.getType();
		List<String> yourList = new Gson().fromJson(jsonArry, listType);
		return yourList;
	}
	
	public static String listToJsonArrayString(List<String> list) {
		JsonArray ss = new JsonArray();
		for(String s : list){
			ss.add(new JsonPrimitive(s));
		}
		return ss.toString();
	}
	public static boolean isPre1_13() {
		
		if(mc1_8 || mc1_9 || mc1_10 || mc1_11 || mc1_12) {
			return true;
		}else {
			return  false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack createItem(Player player,String name,List<String> lore,int id,int shrt) {
		ItemStack item = new ItemStack(CoreMaterial.getMaterial(id),1,(short)shrt);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(CoreVariables.replace(name,player));
		itemMeta.setLore(CoreColor.rColorList(lore));
		item.setItemMeta(itemMeta);
		return item;
	}
	
	

	@SuppressWarnings("deprecation")
	public static ItemStack createItem(String name,List<String> lore,int id,int shrt) {
		ItemStack item = new ItemStack(CoreMaterial.getMaterial(id),1,(short)shrt);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(CoreColor.colorCodes(name));
		itemMeta.setLore(CoreColor.rColorList(lore));
		item.setItemMeta(itemMeta);
		return item;
	}
	
	
	@SuppressWarnings("deprecation")
	public static ItemStack createItem(String name,List<String> lore,Material mate,int shrt) {
		ItemStack item = new ItemStack(mate,1,(short)shrt);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(CoreColor.colorCodes(name));
		itemMeta.setLore(CoreColor.rColorList(lore));
		item.setItemMeta(itemMeta);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack createItem(Player player, String name,List<String> lore,Material mate,int shrt) {
		ItemStack item = new ItemStack(mate,1,(short)shrt);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(CoreColor.colorCodes(name));
		itemMeta.setLore(CoreColor.rColorList(lore));
		item.setItemMeta(itemMeta);
		return item;
	}
	

	@SuppressWarnings("deprecation")
	public static ItemStack createItem(String name,int mate,int shrt) {
		ItemStack item = new ItemStack(CoreMaterial.getMaterial(mate),1,(short)shrt);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(CoreColor.colorCodes(name));
		item.setItemMeta(itemMeta);
		return item;
	}
	
	
	/**
     * Attempt to translate a player name into a UUID.
     * @param name - Player name.
     * @return Player UUID. Null if no match found.
     */
    public static UUID translateNameToUUID(String name, CorePlugin plugin) {
        UUID id = null;
        plugin.getLog().debug("translateNameToUUID(" + name + ")");
        

        if(name == null) {
        	plugin.getLog().debug("translateNameToUUID() - bad ID");
        	return id;
        }

        plugin.getLog().debug("translateNameToUUID() - Looking through online players: " + Bukkit.getServer().getOnlinePlayers().size());
        

        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
        for(Player p : players) {
            if(p.getName().equalsIgnoreCase(name)) {
                id = p.getUniqueId();
                plugin.getLog().debug("translateNameToUUID() online player UUID found: " + id.toString());
                break;
            }
        }

        // Last resort, attempt bukkit api lookup
        if(id == null && Bukkit.getServer().getOnlineMode()) {
        	plugin.getLog().debug("translateNameToUUID() - Attempting online lookup");
        	
        	UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(name));
        	try {
				Map<String, UUID> map = fetcher.call();
				for(Map.Entry<String, UUID> entry : map.entrySet()) {
					if(name.equalsIgnoreCase(entry.getKey())) {
						id = entry.getValue();
						plugin.getLog().debug("translateNameToUUID() web player UUID found: " + ((id == null) ? id : id.toString()));
						break;
					}
				}
			} catch (Exception e) {
				plugin.getLog().error("Exception on online UUID fetch",e);
			}
        } else if(id == null && !Bukkit.getServer().getOnlineMode()) {
            //There's nothing we can do but attempt to get the UUID from old method.
            id = Bukkit.getServer().getOfflinePlayer(name).getUniqueId();
            plugin.getLog().debug("translateNameToUUID() offline player UUID found: " + ((id == null) ? id : id.toString()));
            
        }
        return id;
    }
	
	
	
}
