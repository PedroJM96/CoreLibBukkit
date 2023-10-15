package com.pedrojm96.core.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CorePlugin;
import com.pedrojm96.core.CoreVariables;
import com.pedrojm96.core.command.CoreExecuteComands;



/**
 * Contiene los metodos para codificar item en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.5 05-09-2022
 *
 */
public class CoreItem {
	 
	private Material material;
	 private Short data = null;
	 private String name = null;
	 private String permission = null;
	 private Integer slot = null;
	 private List<String> lore;
	 private List<String> commands;
	 private int delay;
	 private Map<String, Long> cooldown = new HashMap<String, Long>();
	 private String skull = null;
	 private  boolean enchant_glow;
	 private  boolean version_check;
	 private List<String> version_list;
	 private String no_version_message = "It is only available in the <version> versions.";
	 public Map<String, Long> getCooldown(){
		 return this.cooldown;
	 }
	 
	 public CoreItem(Material material)
	 {
		 this.material = material;
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
	 
	 public void setDelay(Integer delay)
	 {
		 if ((delay == null) || (delay.intValue() == 0))
		 {
			 this.delay = Integer.valueOf(1);
			 return;
		 }
		 if (delay.intValue() < 1) {
			 if(delay.intValue() != -1){
				 this.delay = Integer.valueOf(1);
				 return;
			 }
		 }
		 this.delay = delay;
	 }
	 
	 public void setSlot(Integer slot)
	 {
		 if ((slot == null) || (slot.intValue() == 0))
		 {
			 this.slot = null;
			 return;
		 }
		 if (slot.intValue() < 1) {
			 slot = Integer.valueOf(1);
		 }
		 if (slot.intValue() > 9) {
			 slot = Integer.valueOf(9);
		 }
		 slot = Integer.valueOf(slot.intValue() - 1);
		 this.slot = slot;
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
	public boolean like(ItemStack s,Player player)
	 {
		 if (s == null) {
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
		 if (!meta.getDisplayName().equals(CoreVariables.replace(this.name, player))) {
			 return false;
		 }
		 if ((this.data != null) && (this.data.shortValue() != s.getDurability())) {
			 return false;
		 }
		 return true;
	 }

	public void give(Player player,CorePlugin plugin)
	 {
		 CoreItemMaker itemmaker = new CoreItemMaker(this.material,slot,enchant_glow,player,plugin);
		 itemmaker.setSkull(skull);
		 itemmaker.setData(this.data);
		 if (this.name != null) {
			 itemmaker.setDisplayName(CoreVariables.replace(this.name, player));
		 }
		 if (this.lore != null) {
			 itemmaker.setLore(CoreVariables.replaceList(this.lore, player));
		 }
		 ItemStack item = itemmaker.getItemStack();
		 this.material = item.getType();
		 if(enchant_glow) {
			 item = CoreNBTAttribute.addGlow(item);
		 }else {
			 item = CoreNBTAttribute.removeAllAttributes(item);
		 }
		 player.getInventory().setItem(slot, item);
	 }

	 public void executeCommands(Player s,CorePlugin plugin, String click_wait,String prefix)
	  {
	    if ((this.commands != null) && (this.commands.size() > 0))
	    {
	    	if(this.delay == -1){
	    		for(String ss : this.commands){
	    			CoreExecuteComands c = new CoreExecuteComands(s,ss,plugin.getInstance(),prefix);
		    		c.execute();
	    		}
	    	}else{
	    		if(!cooldown.containsKey(s.getName())){
		    	  long t = System.currentTimeMillis();
		    	  cooldown.put(s.getName(), t);
		    	  for(String ss : this.commands){
		    		  CoreExecuteComands c = new CoreExecuteComands(s,ss,plugin.getInstance(),prefix);
		    		  c.execute();
			      }
		    	}else{
		    		int segundos = Integer.valueOf(this.delay);
		        	int mil = 1000;
		        	long dm = segundos*mil;
		        	long t = System.currentTimeMillis();
		        	long ptime = t-dm;
		        	long las = cooldown.get(s.getName());
		        	if(las<ptime){
		        		for(String ss : this.commands){
		        			CoreExecuteComands c = new CoreExecuteComands(s,ss,plugin.getInstance(),prefix);
				    		c.execute();
				        }
		        		cooldown.put(s.getName(), t);
		        	}else{
		        		long left = las-ptime;
		        		double leftSeng = Double.valueOf(left)/Double.valueOf(1000);
		        		String m = click_wait.replaceAll("<time>", String.valueOf(leftSeng));
		        		CoreColor.message(s, m);
		        	}
		    	}
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
}
