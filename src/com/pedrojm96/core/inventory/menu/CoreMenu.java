package com.pedrojm96.core.inventory.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreMaterial;
import com.pedrojm96.core.CorePlugin;
import com.pedrojm96.core.CoreUtils;





/**
 * Contiene los metodos para codificar un menu en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.4 19-09-2021
 *
 */
public class CoreMenu {
	private String name;
	private List<String> worlds =  new ArrayList<String>();;
	private String command;
	private String perm;
	private boolean glass_enable;
	private int glass_color = 0;
	private Sound sound;
	private int rows;
	public List<CoreMenuItem> items = new ArrayList<CoreMenuItem>();
	private ConfigurationSection sectionitems;
	private int slot;
	private CorePlugin plugin;
	
	
	public CoreMenu(String name, ConfigurationSection sectionitems,CorePlugin plugin) {
		this.name = name;
		this.sectionitems = sectionitems;
		this.plugin = plugin;
	}
	
	public void create(int Row,Player player){
		slot = getSlot(Row);
		if(CoreUtils.mc1_8 || CoreUtils.mc1_9 || CoreUtils.mc1_10 || CoreUtils.mc1_11 || CoreUtils.mc1_12) {
			this.name = CoreColor.colorCodes(this.name);
		}else {
			if(this.name.contains("&k") ) {
				this.name = this.name.replaceAll("&k", "");
			}
			if(this.name.contains("&K") ) {
				this.name = this.name.replaceAll("&K", "");
			}
			if(this.name.contains("§k") ) {
				this.name = this.name.replaceAll("§k", "");
			}
			if(this.name.contains("§K") ) {
				this.name = this.name.replaceAll("§K", "");
			}
			
			this.name = CoreColor.colorCodes(this.name);
		}
		Inventory m= Bukkit.createInventory(null,slot, this.name);
		if(this.getGlassEnable()) {
			int color = this.getGlasscolor();
			if(CoreUtils.mc1_8 || CoreUtils.mc1_9 || CoreUtils.mc1_10 || CoreUtils.mc1_11 || CoreUtils.mc1_12) {
				for (int i = 0; i < slot; i++)
				{
					ItemStack it = createItem(" ",160,color);
			        m.setItem(i, it);
				}
			}else {
				
				String material = this.getGlasscolor(color);
				
				for (int i = 0; i < slot; i++)
				{
					ItemStack it = createItem(" ",material);
			        m.setItem(i, it);
				}
			}
			
			
			
		}
		for (CoreMenuItem item : items) {
			if(item.getPermissionToView()){
				if(item.hasPerm(player)) {
					m.setItem(item.getSlot(), item.create(player,m,this.plugin));
				}
			}else{
				m.setItem(item.getSlot(), item.create(player,m,this.plugin));
			}
		}
		player.openInventory(m);
	}
	
	
	
	
	public String getGlasscolor(int color) {
		String retorno;
		switch(color) {
	    case 15:
	    	retorno = "BLACK_STAINED_GLASS_PANE";
	    	break;
	    case 14:
	    	retorno = "RED_STAINED_GLASS_PANE";
	    	break;
	    case 13:
	    	retorno = "GREEN_STAINED_GLASS_PAN";
	    	break;
	    case 12:
	    	retorno = "BROWN_STAINED_GLASS_PANE";
	    	break;
	    case 11:
	    	retorno = "BLUE_STAINED_GLASS_PANE";
	    	break;
	    case 10:
	    	retorno = "PURPLE_STAINED_GLASS_PANE";
	    	break;
	    case 9:
	    	retorno = "CYAN_STAINED_GLASS_PANE";
	    	break;
	    case 8:
	    	retorno = "LIGHT_GRAY_STAINED_GLASS_PANE";
	    	break;
	    case 7:
	    	retorno = "GRAY_STAINED_GLASS_PANE";
	    	break;
	    case 6:
	    	retorno = "PINK_STAINED_GLASS_PANE";
	    	break;
	    case 5:
	    	retorno = "LIME_STAINED_GLASS_PANE";
	    	break;
	    case 4:
	    	retorno = "YELLOW_STAINED_GLASS_PANE";
	    	break;
	    case 3:
	    	retorno = "LIGHT_BLUE_STAINED_GLASS_PAN";
	    	break;
	    case 2:
	    	retorno = "MAGENTA_STAINED_GLASS_PANE";
	    	break;
	    case 1:
	    	retorno = "ORANGE_STAINED_GLASS_PANE";
	    	break;
	    case 0:
	    	retorno = "WHITE_STAINED_GLASS_PANE";
	    	break;
	    default:
	    	retorno = "WHITE_STAINED_GLASS_PANE";
	    	break;
	    }
		return retorno;
	}
	
	public void createFromPlayer(int Row,Player player,OfflinePlayer fromplayer){
		slot = getSlot(Row);
		if(CoreUtils.mc1_8 || CoreUtils.mc1_9 || CoreUtils.mc1_10 || CoreUtils.mc1_11 || CoreUtils.mc1_12) {
			this.name = CoreColor.colorCodes(this.name);
		}else {
			for(String color : CoreColor.getAlternateColorList()) {
				if(this.name.contains(color)) {
					this.name = this.name.replaceAll(color, "");
				}
			}
			this.name = CoreColor.colorCodes(this.name);
		}
		Inventory m= Bukkit.createInventory(null,slot, this.name);
		if(this.getGlassEnable()) {
			int color = this.getGlasscolor();
			for (int i = 0; i < slot; i++)
			{
				ItemStack it = createItem(" ",160,color);
		        m.setItem(i, it);
			}
		}
		for (CoreMenuItem item : items) {
			if(item.getPermissionToView()){
				if(item.hasPerm(player)) {
					m.setItem(item.getSlot(), item.create(player,fromplayer,m,this.plugin));
				}
			}else{
				m.setItem(item.getSlot(), item.create(player,fromplayer,m,this.plugin));
			}
		}
		player.openInventory(m);
	}
	
	
	
	public String getPerm(){
		return this.perm;
	}
	
	public List<String>  getWorlds(){
		if(this.worlds.isEmpty()) {
			return null;
		}
		return this.worlds;
	}
	
	public Sound getSound(){
		return this.sound;
	}
	
	public void setPerm(String perm){
		this.perm = perm;
	}
	
	public void setWorld(String world){
		this.worlds.add(world);
	}
	
	public void setWorlds(List<String> worlds){
		this.worlds = worlds;
	}
	
	public void setGlasscolor(int color){
		this.glass_color = color;
	}
	public int getGlasscolor(){
		return this.glass_color;
	}
	public void setGlassEnable(boolean enable){
		this.glass_enable = enable;
	}
	public boolean getGlassEnable(){
		return this.glass_enable;
	}
	public void setSound(String sound){
		if(sound==null || sound.isEmpty()){
			this.sound = null;
		}else{
			try{
				this.sound = Sound.valueOf(sound);
			}catch(Exception ex){
				this.sound = null;
			}
			
		}
		
	}
	
	public String getCommands(){
		
		return this.command;
	}
	
	public void setCommands(String command){
		
		this.command = command;
	}
	
	public void setRows(int rows){
		this.rows = rows;
	}
	public String getName(){
		return this.name;
	}
	
	public void open(Player player){
		create(this.rows,player);
	}
	
	public void openFromPlayer(Player player, OfflinePlayer fromplayer){
		createFromPlayer(this.rows,player,fromplayer);
	}
	
	
	
	public int getRows(){
		return this.rows;
	}
	@SuppressWarnings("deprecation")
	private static ItemStack createItem(String name,int mate,int shrt) {
		ItemStack i = new ItemStack(CoreMaterial.getMaterial(mate) ,1,(short)shrt);
		ItemMeta im = i.getItemMeta();
		String n = CoreColor.colorCodes(name);
		im.setDisplayName(n);
		i.setItemMeta(im);
		return i;
	}

	private static ItemStack createItem(String name,String mate) {
		ItemStack i = new ItemStack(Material.getMaterial(mate) ,1);
		ItemMeta im = i.getItemMeta();
		String n = CoreColor.colorCodes(name);
		im.setDisplayName(n);
		i.setItemMeta(im);
		return i;
	}
	
	private int getSlot(int rows){
		if (rows <= 0) {
	        int s = 9;
	        return s;
	     }else if(rows > 6){
	    	 int s = 54;
	    	return s;
	     }else{
	    	 int s = rows * 9;
	    	 return s;
	     }
	}
	
	public void load(String wikiMenu){
		Set<String> keys = this.sectionitems.getKeys(false);
		for (String key : keys)
		{
			ConfigurationSection nodo = this.sectionitems.getConfigurationSection(key);
			if (!nodo.isSet("name"))
			{
				plugin.getLog().error("Menu-Item: The item " + key + " has no name!");
			}
			else if ((!nodo.isSet("material")) && (!nodo.isSet("material-old")))
			{
				plugin.getLog().error("Menu-Item: The item " + key + " does not have a defined material value, using id instead.!");
				if(!nodo.isSet("id")) {
					plugin.getLog().error("Menu-Item: The item " + key + " has no Material or ID!");
				}
				else if (  (nodo.getInt("id") == 0) || (CoreMaterial.getMaterial(nodo.getInt("id")) == null)    )
				{
					plugin.getLog().error("Menu-Item: The item " + key + " has an invalid item ID: " + nodo.getInt("id") + ".");
				}else {
					plugin.getLog().error("It is recommended to change the numeric id to a text value of the material. Check out the new settings here. "+wikiMenu);
					plugin.getLog().error("The material id is not supported in 1.16.x");
					CoreMenuItem item = new CoreMenuItem(CoreMaterial.getMaterial(nodo.getInt("id")));
					item.setPerm(nodo.getString("permission"));
					item.setName(nodo.getString("name"));
					item.setMaxrows(rows);
					item.setSlot(Integer.valueOf(nodo.getInt("slot")));
					item.setEnchantGlow(nodo.getBoolean("enchant-glow"));
					item.setData(Short.valueOf((short)nodo.getInt("data")));
					
					
					
					item.setPrice(Integer.valueOf(nodo.getInt("price")));
					item.setkOpen(nodo.getBoolean("keep-open"));
					item.setPermissionToView(nodo.getBoolean("permission-to-view"));
					item.setVersionCheck(nodo.getBoolean("version-check"));
					item.setVersionList(nodo.getStringList("version-list"));
					item.setNoVersionMessage(nodo.getString("no-version-message"));
					item.setNoPermisionMessage(nodo.getString("no-permision-message"));
					
					
					if ((nodo.isSet("lore")) && (nodo.isList("lore"))) {
			        	item.setLore(nodo.getStringList("lore"));
			        }
					if((nodo.contains("skull")) && (nodo.isSet("skull"))){
			        	 item.setSkull(nodo.getString("skull"));
			        }
			        if ((nodo.isSet("commands")) && (nodo.isList("commands"))) {
			        	item.setCommands(nodo.getStringList("commands"));
			        }
			        items.add(item);
					
				}
			}else {
				String mate_data;
				
				if(CoreUtils.isPre1_13()) {
					if(nodo.isSet("material-old")) {
						mate_data = nodo.getString("material-old");
					}else {
						mate_data = nodo.getString("material");
					}
				}else {
					if(nodo.isSet("material")) {
						mate_data = nodo.getString("material");
					}else {
						mate_data = nodo.getString("material-old");
					}
				}
				
				
				
				if(Material.getMaterial(mate_data.contains(":") ? mate_data.split(":")[0].trim().toUpperCase() : mate_data.toUpperCase()) == null) {
					plugin.getLog().error("Menu-Item: The item " + key + " has an invalid item Material: " + (mate_data.contains(":") ? mate_data.split(":")[0].trim().toUpperCase() : mate_data.toUpperCase()) + ".");
				}else {
					String mate;
					short data;
					if(mate_data.contains(":")) {
						mate = mate_data.split(":")[0].trim().toUpperCase();
						data = Short.valueOf(mate_data.split(":")[1].trim());
					}else {
						mate = mate_data.toUpperCase();
						data = Short.valueOf((short)nodo.getInt("data"));
					}
					
					CoreMenuItem item = new CoreMenuItem(Material.getMaterial(mate));
					item.setPerm(nodo.getString("permission"));
					item.setName(nodo.getString("name"));
					item.setMaxrows(rows);
					item.setSlot(Integer.valueOf(nodo.getInt("slot")));
					item.setEnchantGlow(nodo.getBoolean("enchant-glow"));
					item.setData(data);
					
					
					item.setPrice(Integer.valueOf(nodo.getInt("price")));
					item.setkOpen(nodo.getBoolean("keep-open"));
					item.setPermissionToView(nodo.getBoolean("permission-to-view"));
					item.setVersionCheck(nodo.getBoolean("version-check"));
					item.setVersionList(nodo.getStringList("version-list"));
					item.setNoVersionMessage(nodo.getString("no-version-message"));
					item.setNoPermisionMessage(nodo.getString("no-permision-message"));
					
					if ((nodo.isSet("lore")) && (nodo.isList("lore"))) {
			        	item.setLore(nodo.getStringList("lore"));
			        }
					if((nodo.contains("skull")) && (nodo.isSet("skull"))){
			        	 item.setSkull(nodo.getString("skull"));
			        }
			        if ((nodo.isSet("commands")) && (nodo.isList("commands"))) {
			        	item.setCommands(nodo.getStringList("commands"));
			        }
			        items.add(item);
				}
			}
		}
	}
	
	
}
