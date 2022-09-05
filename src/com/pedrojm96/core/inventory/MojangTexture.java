package com.pedrojm96.core.inventory;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.pedrojm96.core.CoreLog;
import com.pedrojm96.core.CoreUtils;


/**
 * Para manejar las texturas minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.4 05-09-2022
 *
 */
public class MojangTexture extends BukkitRunnable {
	 
	 private SkullMeta skullMeta;
	 private ItemStack itemStack;
	 private CoreLog log;
	 private String textureplayername;
	 private int slot;
	 private boolean glow;
	 private Player player;
	 
	 public MojangTexture(ItemStack itemStack,int slot,Player player,String textureplayername,boolean glow,CoreLog log) {
		 this.skullMeta = (SkullMeta) itemStack.getItemMeta();
		 this.itemStack = itemStack;
		 this.log = log;
		 this.textureplayername = textureplayername;
		 this.slot = slot;
		 this.player = player;
		 this.glow = glow;
	 }
	 
	 @Override
	 public void run() {
		 String textures = textureplayername;
		 String[] mojangtextures = CoreUtils.getTextureFromMojang(textures, log);
		 if(mojangtextures!=null) {
			 GameProfile localprofile = new GameProfile(UUID.randomUUID(), this.textureplayername);
			 localprofile.getProperties().put("textures", new Property("textures", mojangtextures[0], mojangtextures[1]));
			 Field profileField = null;
			 try {
				 profileField = this.skullMeta.getClass().getDeclaredField("profile");
				 profileField.setAccessible(true);
				 profileField.set(this.skullMeta, localprofile);
				 this.itemStack.setItemMeta(skullMeta);
				 
				 if(this.glow) {
					 this.itemStack = CoreNBTAttribute.addGlow(this.itemStack);
				 }else {
					 this.itemStack = CoreNBTAttribute.removeAllAttributes(this.itemStack);
				 }
				 this.player.getOpenInventory().setItem(slot, this.itemStack);
			 } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
				 this.log.error("Error al dar formato al gameprofile con texturas", e);
			 }
		 }
	 }
}