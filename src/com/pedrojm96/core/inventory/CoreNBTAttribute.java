package com.pedrojm96.core.inventory;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


/**
 * Contiene los metodos staticos para usar los NBTAtributes en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.3 05-09-2022
 *
 */
public class CoreNBTAttribute {
	 public static ItemStack removePotionAttributes(ItemStack item){
		 	ItemMeta meta = item.getItemMeta();
		 	meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_POTION_EFFECTS);
		 	item.setItemMeta(meta);
			return item;
	 }

	 public static ItemStack removeAllAttributes(ItemStack item){
		 ItemMeta meta = item.getItemMeta();
		 for(org.bukkit.inventory.ItemFlag flag : org.bukkit.inventory.ItemFlag.values()) {
			 meta.addItemFlags(flag);
		 }
		 item.setItemMeta(meta);
		 return item;
	  }
	 
	public static ItemStack addGlow(ItemStack item){
		 ItemMeta meta = item.getItemMeta();
		 meta.addEnchant(Enchantment.DURABILITY, 1,true);
		 item.setItemMeta(meta);
		 return CoreNBTAttribute.removeAllAttributes(item);
    }	
}
