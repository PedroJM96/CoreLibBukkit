package com.pedrojm96.core.inventory.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CorePlugin;
import com.pedrojm96.core.CoreVariables;
import com.pedrojm96.core.command.CoreExecuteComands;
import com.pedrojm96.core.inventory.CoreItemMaker;
import com.pedrojm96.core.inventory.CoreNBTAttribute;




/**
 * Contiene los metodos para codificar item de menu en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.6 05-09-2022
 *
 */
public class CoreMenuItem {
	 private Material material;
	 private Short data = null;
	 private String no_permision_message = "You no have permision to use this item";
	 private String name = null;
	 private String permission = null;
	 private boolean permission_to_view = false;
	 private Integer price;
	 private Boolean kopen;
	 private int maxrows;
	 private Integer slot = null;
	 private List<String> lore;
	 private List<String> commands;
	 private String skull = null;
	 private  boolean enchant_glow;
	 private  boolean version_check;
	 private List<String> version_list;
	 private String no_version_message = "It is only available in the <version> versions.";
	 
	 public CoreMenuItem(Material material)
	 {
		 this.material = material;
	 }

	 public void setMaxrows(Integer s){
		 if ((s == null) || (s.intValue() == 0))
		 {
		      this.maxrows = 6;
		      return;
		 }   
		 this.maxrows = s;
	}

	 public String getName() {
		 return this.name;
	 }
	 
	 public void setName(String name)
	 {
		 if ((name == null) || (name.length() == 0))
		 {
			 this.name = null;
			 return;
		 }
		 this.name = name;
	 }
	 
	 public void setVersionList(List<String> version) 
	 {
		 this.version_list = version;
	 }
		 
	 public void setVersionCheck(boolean version_check)
	 {
		 this.version_check = version_check;
	 }
	 
	 public boolean getVersionCheck()
	 {
		 return this.version_check;
	 }
	 
	 public void setNoVersionMessage(String no_version_message)
	 {
		 if(no_version_message!=null) {
			 if(!no_version_message.isEmpty()) {
				 this.no_version_message = no_version_message;   
			 }
		 }   
	 }
	 
	 public void setNoPermisionMessage(String no_permision_message)
	 {
		 if(no_permision_message!=null) {
			 if(!no_permision_message.isEmpty()) {
				 this.no_permision_message = no_permision_message;   
			 }
		 } 
	 }
	 
	 
	 public String getNoVersionMessage()
	 {
		 return this.no_version_message;  
	 }
	 
	 public List<String> getVersionList() 
	 {
		 return this.version_list;
	 }

	 public void setLore(List<String> lore)
	 {
		 if ((lore == null) || (lore.size() == 0))
		 {
			 this.lore = null;
			 return;
		 }
		 this.lore = lore;
	 }

	 public void setPrice(Integer s)
	 {
		 if ((s == null) || (s.intValue() == 0))
		 {
			 this.price = null;
			 return;
		 }   
		 this.price = s;
	 }
	 
	 public int getPrice()
	 {
		 if (this.price == null) {
			 return 0;
		 }
		 return this.price;
	 }

	 public void setSlot(Integer s)
	 {
		 if ((s == null) || (s.intValue() == 0))
		 {
			 this.slot = null;
			 return;
		 }
		 if (s.intValue() < 1) {
			 s = Integer.valueOf(1);
		 }
		 if (s.intValue() > getMaxSlot(maxrows)) {
			 s = Integer.valueOf(getMaxSlot(maxrows));
		 }
		 s = Integer.valueOf(s.intValue() - 1);
		 this.slot = s;
	 }
	 
	 public void setPermissionToView(boolean permission_to_view)
	 {
		 this.permission_to_view = permission_to_view;
	 }
	 
	 public boolean getPermissionToView()
	 {
	     return this.permission_to_view; 
	 }
	 
	 public void setkOpen(Boolean s)
	 {
		 if (s == null) {
			 this.kopen = null;
		 } else {
			 this.kopen = s;
		 }
	 }
	 
	 public void setSkull(String skull)
	 {
		 if ((skull == null) || (skull.length() == 0)) {
			 this.skull = null;
		 } else {
			 this.skull = skull;
		 }
	 }
	
	 public void setEnchantGlow(boolean enchant_glow)
	 {
		 this.enchant_glow = enchant_glow;
	 }
	
	 public boolean getEnchantGlow()
	 {
		 return enchant_glow;
	 }
	
	 public void setPerm(String permission)
	 {
		 if ((permission == null) || (permission.length() == 0)) {
			 this.permission = null;
		 } else {
			 this.permission = permission;
		 }
	 }
	 
	@SuppressWarnings("deprecation")
	public boolean like(ItemStack s,int sl,Player player)
	 {
		 if (s == null) {
			 return false;
		 }
		 if(this.slot!=sl) {
			 return false;
		 }
		 if (s.getType() != this.material) {
			 return false;
		 }
		 ItemMeta meta = s.getItemMeta();
		 if (this.name == null && meta.hasDisplayName()){
			 return false; 
		 }  
		 if (this.name != null && !meta.hasDisplayName()) { 
			 return false;
		 } 
		
		 if (!meta.getDisplayName().equalsIgnoreCase(CoreVariables.replace(this.name.replaceAll("<price>", String.valueOf(getPrice())), player))) {
			 return false;
		 }
		
		 if ((this.data != null) && (this.data.shortValue() != s.getDurability())) {
			 return false;
		 }
		 return true;
	 }
	  
	public ItemStack create(Player player,Inventory menu,CorePlugin plugin)
	 {
		 CoreItemMaker itemmaker = new CoreItemMaker(this.material,slot,enchant_glow,player,plugin);
		 itemmaker.setSkull(skull);
		 itemmaker.setData(this.data);
		 if (this.name != null) {
			 String localname = this.name;
			 itemmaker.setDisplayName(CoreVariables.replace(localname.replaceAll("<price>", String.valueOf(getPrice())), player));
		 }
		 if (this.lore != null) {
			 List<String> locallore = new ArrayList<String>(); 
			 for(String l: this.lore) {
				 locallore.add(l.replaceAll("<price>", String.valueOf(getPrice())));
			 }
			 if(!hasPerm(player)){
				 locallore.add(" ");
				 locallore.add(CoreColor.colorCodes(this.no_permision_message));
				 itemmaker.setLore(CoreVariables.replaceList(locallore, player));
			 }else{
				 itemmaker.setLore(CoreVariables.replaceList(locallore, player));
			 }
		 }else{
			 if(!hasPerm(player)){
				 List<String> locallore = new ArrayList<String>(); 
				 locallore.add(" ");
				 locallore.add(CoreColor.colorCodes(this.no_permision_message));
				 itemmaker.setLore(CoreVariables.replaceList(locallore, player));
			 }
		 }
		 ItemStack item = itemmaker.getItemStack();
		 this.material = item.getType();
		 if(enchant_glow) {
			 return  CoreNBTAttribute.addGlow(item);		 
		 }
		 return CoreNBTAttribute.removeAllAttributes(item);
	 }
	
	
	public ItemStack create(Player player,OfflinePlayer fromplayer,Inventory menu,CorePlugin plugin)
	 {
		 CoreItemMaker itemmaker = new CoreItemMaker(this.material,slot,enchant_glow,player,plugin);
		 itemmaker.setSkull(skull);
		 itemmaker.setData(this.data);
		 if (this.name != null) {
			 String localname = this.name;
			 itemmaker.setDisplayName(CoreVariables.replace(localname.replaceAll("<price>", String.valueOf(getPrice())), fromplayer));
		 }
		 if (this.lore != null) {
			 List<String> locallore = new ArrayList<String>(); 
			 for(String l: this.lore) {
				 locallore.add(l.replaceAll("<price>", String.valueOf(getPrice())));
			 }
			 if(!hasPerm(player)){
				 locallore.add(" ");
				 locallore.add(CoreColor.colorCodes(this.no_permision_message));
				 itemmaker.setLore(CoreVariables.replaceList(locallore, fromplayer));
			 }else{
				 itemmaker.setLore(CoreVariables.replaceList(locallore, fromplayer));
			 }
		 }else{
			 if(!hasPerm(player)){
				 List<String> locallore = new ArrayList<String>(); 
				 locallore.add(" ");
				 locallore.add(CoreColor.colorCodes(this.no_permision_message));
				 itemmaker.setLore(CoreVariables.replaceList(locallore, fromplayer));
			 }
		 }
		 ItemStack item = itemmaker.getItemStack(fromplayer);
		 this.material = item.getType();
		 if(enchant_glow) {
			 return  CoreNBTAttribute.addGlow(item);		 
		 }
		 return CoreNBTAttribute.removeAllAttributes(item);
	 }
	
	

	 public void executeCommands(Player p, JavaPlugin plugin,String prefix)
	 {
		 if ((this.commands != null) && (this.commands.size() > 0))
		 {
			 for(String s : this.commands){
				 CoreExecuteComands c = new CoreExecuteComands(p,s,plugin,prefix);
				 c.execute();
			 }
		 }
	 }
	 
	 public boolean hasPerm(Player s)
	 {
		 if (this.permission == null) {
			 return true;
		 }
		 return s.hasPermission(this.permission);
	 }
	  
	 public void setData(Short s)
	 {
		 if ((s == null) || (s.shortValue() == 0))
		 {
			 this.data = null;
			 return;
		 }
		 this.data = s;
	  }
	 
	  public void setCommands(List<String> c)
	  {
		  if ((c == null) || (c.size() == 0))
		  {
			  this.commands = null;
		      return;
		  }
		  this.commands = new ArrayList<String>();
		  for (String s : c)
		  {
		      this.commands.add(s);
		  }
	  }
	  
	  public boolean isCommand()
	  {
		  if (this.commands == null) {
			  return false;
		  }
		  return true;
	  }
	  
	  public boolean kOpen()
	  {
		  if (this.kopen == null) {
			  return false;
		  }
		  return this.kopen;
	  }
	  public int getSlot()
	  {
		  if (this.slot == null) {
			  return 0;
		  }
		  return this.slot;
	  }
	  
	  private int getMaxSlot(int rows){
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
}
