package com.pedrojm96.core.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.pedrojm96.core.CoreColor;

/**
 * Facilita la creacion de subcomandos en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.0 22-09-2018
 *
 */
public abstract class CoreSubCommand {
	public abstract boolean onSubCommand(CommandSender sender,String[] args);
	
	public abstract String getErrorNoPermission();

	public abstract List<String> onCustomTabComplete(CommandSender sender, List<String> list, String[] args);
	
	public abstract String getPerm();
	
	public boolean hasPerm(CommandSender sender){
		if(getPerm() == null){
			return true;
		}
		if(sender.hasPermission(getPerm())){
			return true;
		}
		return false;
	}
	
	
	
	
	
	public boolean rum(CommandSender sender,String cmd,String[] args){
		if (!hasPerm(sender)){
			CoreColor.message(sender, getErrorNoPermission());
			return true;
    	}

		return onSubCommand(sender,args);
	}
} 
