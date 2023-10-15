package com.pedrojm96.core;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


/**
 * Para comprobar de si actualizaciones del plugin en servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.1 5-09-2022
 *
 */
public class CheckForUpdatesPlayerRun extends BukkitRunnable{

	private JavaPlugin plugin;
	private Player player;
	private String prefix;
	private int project = 0;
	
	public CheckForUpdatesPlayerRun(Player player,String prefix ,JavaPlugin plugin, int projectID) {
		this.plugin = plugin;
		this.player = player;
		this.prefix = prefix;
		this.project = projectID;
	}

	@Override
	public void run() {
		if(player==null) {
			return;
		}
		if(!player.isOnline()) {
			return;
		}
		CoreSpigotUpdater updater = new CoreSpigotUpdater(this.plugin, this.project);
    	try {
            if (updater.checkForUpdates()) {
            	
            	  CoreColor.message(this.player, this.prefix + "&7Update avaliable for "+this.plugin.getName()+". Please update to recieve latest version "+updater.getLatestVersion()+".");
            	  CoreColor.message(this.player, this.prefix + "&7"+updater.getResourceURL());
            }	
        } catch (Exception e) {
	
        }
	}
}
