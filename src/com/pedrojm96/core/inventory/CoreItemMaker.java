package com.pedrojm96.core.inventory;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.pedrojm96.core.CorePlugin;
import com.pedrojm96.core.CoreUtils;
import com.pedrojm96.core.CoreVariables;

/**
 * Contiene los metodos para crear item en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.0 03-04-2020
 *
 */
public class CoreItemMaker {
	private List<String> lore;
	private String displayName;
	private Material material;
	private Short data;
	private String skull;
	private CorePlugin plugin;
	private Player player;
	private int slot;
	private boolean glow;
	public CoreItemMaker(Material mate, int slot,boolean glow, Player player , CorePlugin plugin) {
		this.material = mate;
		this.plugin = plugin;
		this.player = player;
		this.slot = slot;
		this.glow = glow;
	}
	
	
	@SuppressWarnings("deprecation")
	public ItemStack getItemStack() {
		
		 ItemStack item = new ItemStack(this.material);
		 
		 if (this.data != null) {
			 item.setDurability(this.data.shortValue());
		 }
		 
		 Material checkMaterial = null;

		 if(CoreUtils.Version.getVersion().esMayorIgual(CoreUtils.Version.v1_13)) {
			 checkMaterial = Material.getMaterial("PLAYER_HEAD");
			 this.data = 3;
		 }else {
			 checkMaterial = Material.getMaterial("SKULL_ITEM");
		 }
		 
		 if((this.material.equals(checkMaterial)) && ((this.data==null?0:this.data) == 3) &&  (this.skull!=null)) {
			 //SkullItem------------------------
			 SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
			 skullMeta.setDisplayName(displayName);
			 skullMeta.setLore(lore);
			 if(this.skull.contains("url:")){
				 this.URL(skullMeta);
			 }else if(this.skull.contains("textures:")) {
				 this.TEXTURES(skullMeta);
			 }else if(skull.contains("web:")) {
				 this.WEB(skullMeta,item);
			 }else{
				 this.PLAYER(skullMeta,item);
			 }
			 item.setItemMeta(skullMeta);  
		 }else {
			 //Iten mormal sin textura
			 ItemMeta meta = item.getItemMeta();
			 meta.setDisplayName(displayName);
			 meta.setLore(lore);
			 item.setItemMeta(meta);
		 }
		 return item;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getItemStack(OfflinePlayer fromplayer) {
		
		 ItemStack item = new ItemStack(this.material);
		 
		 if (this.data != null) {
			 item.setDurability(this.data.shortValue());
		 }
		 
		 Material checkMaterial = null;

		 if(CoreUtils.Version.getVersion().esMayorIgual(CoreUtils.Version.v1_13)) {
			 checkMaterial = Material.getMaterial("PLAYER_HEAD");
			 this.data = 3;
		 }else {
			 checkMaterial = Material.getMaterial("SKULL_ITEM");
		 }
		 
		 if((this.material.equals(checkMaterial)) && ((this.data==null?0:this.data) == 3) &&  (this.skull!=null)) {
			 //SkullItem------------------------
			 SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
			 skullMeta.setDisplayName(displayName);
			 skullMeta.setLore(lore);
			 if(this.skull.contains("url:")){
				 this.URL(skullMeta);
			 }else if(this.skull.contains("textures:")) {
				 this.TEXTURES(skullMeta);
			 }else if(skull.contains("web:")) {
				 this.WEB(skullMeta,item,fromplayer);
			 }else{
				 this.PLAYER(skullMeta,item,fromplayer);
			 }
			 item.setItemMeta(skullMeta);  
		 }else {
			 //Iten mormal sin textura
			 ItemMeta meta = item.getItemMeta();
			 meta.setDisplayName(displayName);
			 meta.setLore(lore);
			 item.setItemMeta(meta);
		 }
		 return item;
	}
	
	
	private void URL(SkullMeta skullMeta) {
		 String localString = this.skull;
		 String url = localString.split("url:")[1].trim();
		 GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		 byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		 profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		 Field profileField = null;
		 try {
			 profileField = skullMeta.getClass().getDeclaredField("profile");
			 profileField.setAccessible(true);
			 profileField.set(skullMeta, profile);
		 } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			 e1.printStackTrace();
		 }
	}
	
	
	private void TEXTURES(SkullMeta skullMeta) {
		String localString = this.skull;
		 String textures = localString.split("textures:")[1].trim();
		 GameProfile profile = new GameProfile(UUID.randomUUID(), null);
           
		 profile.getProperties().put("textures", new Property("textures", new String(textures)));
		 Field profileField = null;
		 try {
			 profileField = skullMeta.getClass().getDeclaredField("profile");
			 profileField.setAccessible(true);
			 profileField.set(skullMeta, profile);
		 } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
               e1.printStackTrace();
		 }
	}
	
	private void WEB(SkullMeta skullMeta,ItemStack item) {
		 String localString = skull;
		 String textureplayername = CoreVariables.replace(localString.split("web:")[1].trim(), player);
		 plugin.getLog().debug("web: obteniendo texturas para "+textureplayername+" desde mojan.");
		 if(CoreUtils.playerTexturesCache.containsKey(textureplayername)) {
			 this.CacheMojangTexture(skullMeta, textureplayername);
		 }else {
			 new MojangTexture(item,slot,player,textureplayername,this.glow,plugin.getLog()).runTaskAsynchronously(plugin.getInstance());
		 }
	}
	
	private void WEB(SkullMeta skullMeta,ItemStack item,OfflinePlayer fromplayer) {
		 String localString = skull;
		 String textureplayername = CoreVariables.replace(localString.split("web:")[1].trim(), fromplayer);
		 plugin.getLog().debug("web: obteniendo texturas para "+textureplayername+" desde mojan.");
		 if(CoreUtils.playerTexturesCache.containsKey(textureplayername)) {
			 this.CacheMojangTexture(skullMeta, textureplayername);
		 }else {
			 new MojangTexture(item,slot,player,textureplayername,this.glow,plugin.getLog()).runTaskAsynchronously(plugin.getInstance());
		 }
	}
	
	
	private void PLAYER(SkullMeta skullMeta,ItemStack item, OfflinePlayer fromplayer) {
		Player playerBukkit = Bukkit.getPlayerExact(CoreVariables.replace(skull, fromplayer));
		 if(playerBukkit!=null) {
			 plugin.getLog().debug("Jugador en linea intentando obtener texturas del profile local.");
			 String[] localtextures = CoreUtils.getPlayerTextureLocal(playerBukkit, plugin.getLog());
			 if(localtextures!=null) {
				 this.LocalTextures(skullMeta, localtextures);
			 }else {
				 plugin.getLog().debug("Intentando obtener texturas desde mojan.");
				 String textureplayername = CoreVariables.replace(skull, player);
				 if(CoreUtils.playerTexturesCache.containsKey(textureplayername)) {
					 this.CacheMojangTexture(skullMeta, textureplayername);
				 }else {
					 new MojangTexture(item,slot,player,textureplayername,this.glow,plugin.getLog()).runTaskAsynchronously(plugin.getInstance());
				 } 
			 }
		 }else {
			 plugin.getLog().debug("Jugador fuera de linea intentando obtener texturas desde mojan.");
			 String textureplayername = CoreVariables.replace(skull, fromplayer);
			 if(CoreUtils.playerTexturesCache.containsKey(textureplayername)) {
				  this.CacheMojangTexture(skullMeta, textureplayername);
			 }else {
				 new MojangTexture(item,slot,player,textureplayername,this.glow,plugin.getLog()).runTaskAsynchronously(plugin.getInstance());
			 } 
		 }
	}
	
	private void PLAYER(SkullMeta skullMeta,ItemStack item) {
		Player playerBukkit = Bukkit.getPlayerExact(CoreVariables.replace(skull, player));
		 if(playerBukkit!=null) {
			 plugin.getLog().debug("Jugador en linea intentando obtener texturas del profile local.");
			 String[] localtextures = CoreUtils.getPlayerTextureLocal(playerBukkit, plugin.getLog());
			 if(localtextures!=null) {
				 this.LocalTextures(skullMeta, localtextures);
			 }else {
				 plugin.getLog().debug("Intentando obtener texturas desde mojan.");
				 String textureplayername = CoreVariables.replace(skull, player);
				 if(CoreUtils.playerTexturesCache.containsKey(textureplayername)) {
					 this.CacheMojangTexture(skullMeta, textureplayername);
				 }else {
					 new MojangTexture(item,slot,player,textureplayername,this.glow,plugin.getLog()).runTaskAsynchronously(plugin.getInstance());
				 } 
			 }
		 }else {
			 plugin.getLog().debug("Jugador fuera de linea intentando obtener texturas desde mojan.");
			 String textureplayername = CoreVariables.replace(skull, player);
			 if(CoreUtils.playerTexturesCache.containsKey(textureplayername)) {
				  this.CacheMojangTexture(skullMeta, textureplayername);
			 }else {
				 new MojangTexture(item,slot,player,textureplayername,this.glow,plugin.getLog()).runTaskAsynchronously(plugin.getInstance());
			 } 
		 }
	}
	
	
	
	private void LocalTextures(SkullMeta skullMeta,String[] localtextures) {
		GameProfile localprofile = new GameProfile(UUID.randomUUID(), skull);
		 localprofile.getProperties().put("textures", new Property("textures", localtextures[0], localtextures[1]));
		 Field profileField = null;
		 try {
			 profileField = skullMeta.getClass().getDeclaredField("profile");
			 profileField.setAccessible(true);
			 profileField.set(skullMeta, localprofile);
		 } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			 plugin.getLog().error("Error al dar formato al gameprofile con texturas", e);
		 }
	}
	
	private void CacheMojangTexture(SkullMeta skullMeta,String textureplayername) {
		 plugin.getLog().debug("Textura obtenida desde el cache de mojan para: "+textureplayername);
		 String[] cachetextures = CoreUtils.playerTexturesCache.get(textureplayername);
		 GameProfile localprofile = new GameProfile(UUID.randomUUID(), skull);
		 localprofile.getProperties().put("textures", new Property("textures", cachetextures[0], cachetextures[1]));
		 Field profileField = null;
		 try {
			 profileField = skullMeta.getClass().getDeclaredField("profile");
			 profileField.setAccessible(true);
			 profileField.set(skullMeta, localprofile);
		 } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			 plugin.getLog().error("Error al dar formato al gameprofile con texturas", e);
		 }
	}
	
	
	/**
	 * @return the lore
	 */
	public List<String> getLore() {
		return lore;
	}
	/**
	 * @param lore the lore to set
	 */
	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}
	/**
	 * @param material the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}
	/**
	 * @return the data
	 */
	public Short getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Short data) {
		this.data = data;
	}
	/**
	 * @return the skull
	 */
	public String getSkull() {
		return skull;
	}
	/**
	 * @param skull the skull to set
	 */
	public void setSkull(String skull) {
		this.skull = skull;
	}
}
