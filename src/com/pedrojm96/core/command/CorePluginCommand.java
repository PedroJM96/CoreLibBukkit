package com.pedrojm96.core.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import com.pedrojm96.core.CoreColor;







/**
 * Facilita la creacion de comandos en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.4 05-09-2022
 *
 */
public abstract class CorePluginCommand implements CommandExecutor,TabExecutor{

	public abstract boolean onCommand(CommandSender sender,String command,String[] args);
	public abstract List<String> onCustomTabComplete(CommandSender sender, List<String> list, String[] args);
	public abstract String getErrorNoPermission();
	public abstract String getPerm();
	public abstract String getName();
	public abstract List<String> getAliases();
	public abstract String getUsage();
	public abstract String getDescription();
	
	public boolean hasPerm(CommandSender sender){
		if(getPerm() == null){
			return true;
		}
		if(sender.hasPermission(getPerm())){
			return true;
		}
		return false;
	}
	
	private HashMap<List<String>, CoreSubCommand> subcommands = new HashMap<List<String>,CoreSubCommand>();
	public void addSubCommand(List<String> subcmds,CoreSubCommand s){
    	subcommands.put(subcmds, s);
    }
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if (command.getName().equalsIgnoreCase(getName())) {
			if(!hasPerm(sender)) {
				return null;
			}
			List<String> result = new ArrayList<String>();
			if(arg3.length == 1 ) {
				for(List<String> subcommand : subcommands.keySet()) {
					for(String subcommandAlliases : subcommand) {
						if(!arg3[0].isEmpty() || arg3[0] != "") {
							if(subcommandAlliases.startsWith(arg3[0])) {
								if(subcommands.get(subcommand).hasPerm(sender)) {
									result.add(subcommandAlliases);
								}else {
									continue;
								}
							}else {
								continue;
							}
						}else {
							if(subcommands.get(subcommand).hasPerm(sender)) {
								result.add(subcommandAlliases);
							}else {
								continue;
							}
						}
					}
				}
				return  onCustomTabComplete(sender,result,arg3);
			}else {
				for(List<String> subcommand : subcommands.keySet()) {
					if(subcommand.contains(arg3[0].toLowerCase())) {
						if(subcommands.get(subcommand).hasPerm(sender)) {
							
							String[] subargs = new String[arg3.length-1]; 
			    			for(int i = 1; i<arg3.length;i++) {
			    				subargs[i-1] = arg3[i];
			    			}
							result = subcommands.get(subcommand).onCustomTabComplete(sender, result, subargs);
						}
						break;
					}
				}
				if(result!=null && !result.isEmpty()) {
					return result;
				}
			}
			return null;
		}
		return null;
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(!hasPerm(sender)) {
			CoreColor.message(sender, getErrorNoPermission());	
			return true;
		}
		boolean retorno = false;
		if (arg3.length == 0) {
			return onCommand(sender,command.getName(),arg3);
		}
    	//se verifica si el comando coincide un un comando almacenado(Incluye el alias del comando)
    	for(List<String> s : subcommands.keySet()){
    		//si el comando coincide
    		if(s.contains(arg3[0].toLowerCase())){
    			//se executa el comando
    			String[] args = new String[arg3.length-1]; 
    			for(int i = 1; i<arg3.length;i++) {
    				args[i-1] = arg3[i];
    			}
    			retorno =  subcommands.get(s).rum(sender,arg3[0].toLowerCase(),args);
    			break;
    		}
    	}
    	//si es comando no es correto
    	if(!retorno){
    		return onCommand(sender,command.getName(),arg3);
    	}
		return retorno;
	}
}
