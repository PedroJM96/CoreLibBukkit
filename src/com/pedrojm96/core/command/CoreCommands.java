package com.pedrojm96.core.command;



import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CorePlugin;

/**
 * Para administrar los commandos.
 * 
 * @author PedroJM96
 * @version 1.1 05-09-2022
 *
 */
public class CoreCommands {
	
	public static void registerCommand(CorePluginCommand command,CorePlugin plugin) {
		PluginCommand plugincommands = createPluginCommand(command.getName(),plugin);
		if (plugincommands != null) {
			plugincommands.setAliases( command.getAliases() )
		            .setUsage( command.getUsage() )
		            .setDescription( command.getDescription() )
		            .setPermissionMessage( CoreColor.colorCodes( command.getErrorNoPermission()))
		            .setPermission( command.getPerm() );
			try {
				CommandMap commandMap = getCommandMapInstance();
			    if (commandMap != null) {
			        commandMap.register( plugin.getInstance().getDescription().getName(), plugincommands ); 
			        plugin.getInstance().getCommand(command.getName()).setExecutor(command);   
			        plugin.getInstance().getCommand(command.getName()).setTabCompleter(command);   
			    }
			}catch(RuntimeException e) {
				plugin.getLog().fatalError("Error on register plugin command with reflection in the bukkit Commans map.", e);
			}  
		}
	}
	
	private static PluginCommand createPluginCommand(String name ,CorePlugin plugin) {
	    try {
	        Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor( String.class, Plugin.class );
	        constructor.setAccessible( true );
	        return constructor.newInstance( name, plugin.getInstance() );
	    } catch (Exception  e ) {
	    	plugin.getLog().fatalError("Error on create plugin command with reflection", e);
	    }
	    return null;
	}
	
	/**
	* Get the {@link CommandMap} instance of {@link org.bukkit.plugin.PluginManager}
	*
	* @return The plugin map instance or null
	*/
	private static CommandMap getCommandMapInstance() {
	    if ( Bukkit.getPluginManager() instanceof SimplePluginManager ) {
	        SimplePluginManager spm = (SimplePluginManager) Bukkit.getPluginManager();
	        
	        try {
	            Field field = FieldUtils.getDeclaredField( spm.getClass(), "commandMap", true );
	            return (CommandMap) field.get( spm );
	        } catch ( IllegalAccessException e ) {
	            throw new RuntimeException( "Can't get the Bukkit CommandMap instance." );
	        }
	    }
	    return null;
	}
}
