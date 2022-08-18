package com.pedrojm96.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Contiene los metodos estaticos para acceder y manejar  la reflesion de clases de minecraft.net y craftbukkit.
 * 
 * @author PedroJM96
 * @version 1.2 10-07-2021
 *
 */
public class CoreReflection {
	/**
	 * Busca la clase indicada en net.minecraft.server.
	 * 
	 * @param name Nombre de la clase a buscar.
	 * @return Devuelve la clase buscada con el nombre en net.minecraft.server.
	 */
	public static Class<?> getNMSClass(String name)
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		
	    return getClass("net.minecraft.server." + version + "." + name);
	  
	}
	/**
	 * Busca la clase indicada con sus clases internas en net.minecraft.server.
	 * 
	 * @param name Nombre de las clase a buscar.
	 * @return Devuelve todas las clases internas buscadas con el nombre en net.minecraft.server.
	 */
	public static Class<?> getNMSClassArray(String name)
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	   
	    return getClass("[Lnet.minecraft.server." + version + "." + name+";");
	   
	}
	/**
	 * Busca la clase indicada en org.bukkit.craftbukkit.
	 * 
	 * @param name Nombre de las clase a buscar.
	 * @return Devuelve la clase buscada con el nombre en org.bukkit.craftbukkit.
	 */
	public static Class<?> getCraftClass(String name)
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	    return getClass("org.bukkit.craftbukkit." + version + "." + name);
	}
	/**
	 * Retorna una clase en el diretorio org.bukkit.
	 * 
	 * @param name Nombre de la clase a buscar,
	 * @return Devuelve la clase buscada con el nombre en org.bukkit.
	 */
	public static Class<?> getBukkitClass(String name)
	{
	    return getClass("org.bukkit." + name);
	}
	
	
	/**
	 *  Envia un paquete al jugador indigado.
	 *  
	 * @param player El jugador al que se le va a enviar el paquete.
	 * @param packet El paquete que se va a enviar.
	 */
	public static void sendPacket(Player player, Object packet)
	{
	    try
	    {
	    
	    	
	      Object handle = player.getClass().getMethod("getHandle").invoke(player);

	      Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
	      playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet") ).invoke(playerConnection, packet );
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	}
	

	/**
	 *  Envia un paquete al jugador indigado.
	 *  
	 * @param player El jugador al que se le va a enviar el paquete.
	 * @param packet El paquete que se va a enviar.
	 */
	public static void sendPacketPos_1_17(Player player, Object packet)
	{
	    try
	    {
	    
	      Object handle = player.getClass().getMethod("getHandle").invoke(player);

	      Object playerConnection = handle.getClass().getField("b").get(handle);
	      
	      Object networkManager = playerConnection.getClass().getField("a").get(playerConnection);
	      
	      
	      networkManager.getClass().getMethod("sendPacket", getClass("net.minecraft.network.protocol.Packet") ).invoke(networkManager, packet );
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	}
	
	/**
	 *  Envia un paquete al jugador indigado.
	 *  
	 * @param player El jugador al que se le va a enviar el paquete.
	 * @param packet El paquete que se va a enviar.
	 */
	public static void sendPacketPos_1_18(Player player, Object packet)
	{
	    try
	    {
	    
	    	
	      Object handle = player.getClass().getMethod("getHandle").invoke(player);

	      Object playerConnection = handle.getClass().getField("b").get(handle);
	      
	      Object networkManager = playerConnection.getClass().getField("a").get(playerConnection);
	      
	      
	      networkManager.getClass().getMethod("a", getClass("net.minecraft.network.protocol.Packet") ).invoke(networkManager, packet );
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	}
	
	
	public static Object  getStaticField(Class<?> c, String name){
		Object retorno = null;
		
		Field f = null;
        try{
            f = c.getDeclaredField(name);
        } catch(NoSuchFieldException e){
            e.printStackTrace();
        } catch(SecurityException e){
            e.printStackTrace();
        }
        if(f == null){
            throw new IllegalArgumentException("Error while getting the field '" + name + "'");
        }
        f.setAccessible(true);
       
        try{
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, modifiersField.getInt(f) - Modifier.FINAL);
        } catch(SecurityException e){
            System.out.println("A security manager may be preventing you from setting this field.");
            e.printStackTrace();
        } catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException e){
            e.printStackTrace();
        }
       
        try {
        	retorno = f.get(null);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return retorno;
    }
	
	
	/**
	 *  Obtiene el valor del field buscado.
	 *  
	 * @param obj El objeto dende se va a buscar el field.
	 * @param name El nombre del field a buscar.
	 * @return Devuelve el valor del field buscado en el objeto con el nombre.
	 */
	public static Object getFieldValue(Object obj,String name)
	{
         try
         {
        	 Field field = obj.getClass().getDeclaredField(name);
        	 field.setAccessible(true);
        	 return field.get(obj);
         }catch(Exception e){}
         return null;
	}
	/**
	 * Establece el valor del field buscado.
	 * 
	 * @param obj El objeto dende se va a buscar el field.
	 * @param name El nombre del field a buscar.
	 * @param value El valor que se asignara al field buscado en el objeto con el nombre.
	 */
	public static void setFieldValue(Object obj,String name,Object value)
	{
         try
         {
        	 Field field = obj.getClass().getDeclaredField(name);
        	 field.setAccessible(true);
        	 field.set(obj,value);
         }
         catch(Exception e){}
	}
	
	
	public static void setStaticField(Class<?> c, String name, Object set){
        Field f = null;
        try{
            f = c.getDeclaredField(name);
        } catch(NoSuchFieldException e){
            e.printStackTrace();
        } catch(SecurityException e){
            e.printStackTrace();
        }
        if(f == null){
            throw new IllegalArgumentException("Error while getting the field '" + name + "'");
        }
        f.setAccessible(true);
       
        try{
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, modifiersField.getInt(f) - Modifier.FINAL);
        } catch(SecurityException e){
            System.out.println("A security manager may be preventing you from setting this field.");
            e.printStackTrace();
        } catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException e){
            e.printStackTrace();
        }
       
        try {
            f.set(null, set);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Enum<?> getEnum(String enumFullName)
	  {
	    String[] x = enumFullName.split("\\.(?=[^\\.]+$)");
	    if (x.length == 2)
	    {
	      String enumClassName = x[0];
	      String enumName = x[1];
	      
	      
		  Class<Enum> cl = (Class<Enum>) getClass(enumClassName);
	      
	      return Enum.valueOf(cl, enumName);
	    }
	    return null;
	  }
	
	 public static Class<?> getClass(String name)
	  {
	    try
	    {
	      return Class.forName(name);
	    }
	    catch (ClassNotFoundException e) {
	    	 e.printStackTrace();
	    }
	    return null;
	  }	 	
	 
}
