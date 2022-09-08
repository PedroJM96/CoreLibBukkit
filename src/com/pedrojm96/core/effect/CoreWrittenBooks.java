package com.pedrojm96.core.effect;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreReflection;
import com.pedrojm96.core.CoreVariables;
import com.pedrojm96.core.CoreVersion;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Contiene los metodos para enviar libros escritos  a los jugadores en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.2 05-09-2022
 *
 */
public class CoreWrittenBooks {
	/**
	 * 
	 * @param player El Jugador al que se enviaran el libro.
	 * @param pages paginas del libro 
	 */
	public static void sendBook(Player player, List<String> pages, String title, String name, boolean give)
	{
		if(CoreVersion.getVersion().esMenorIgual(CoreVersion.v1_12_x)) {
			send_1_12_2(player,pages,title,name,give);
		}else if(CoreVersion.getVersion().esMenorIgual(CoreVersion.v1_13_x) ){
			send_1_13(player,pages,title,name,give);
		}else if(CoreVersion.getVersion().esMenorIgual(CoreVersion.v1_14_2) ){
			send_1_14(player,pages,title,name,give);
		}else {
			send_1_14_4(player,pages,title,name,give);
		}	
	}

	private static void send_1_12_2(Player player, List<String> pages, String title, String name, boolean give)
	{
		int slot = player.getInventory().getHeldItemSlot();
	    ItemStack old = player.getInventory().getItem(slot);
	    player.getInventory().setItem(slot, getBook(player, pages, title, name));
	    try {
	    	ByteBuf buf = Unpooled.buffer(256);
	        buf.setByte(0, (byte)0);
	        buf.writerIndex(1);
	        Class<?> PacketDataSerializerClass = CoreReflection.getNMSClass("PacketDataSerializer");
	        Constructor<?> PacketDataSerializerConstructor = PacketDataSerializerClass.getConstructor(ByteBuf.class);
	        Object PacketDataSerializerObj = PacketDataSerializerConstructor.newInstance(buf);
	        Class<?> PacketPlayOutCustomPayloadClass = CoreReflection.getNMSClass("PacketPlayOutCustomPayload");
	        Constructor<?> PacketPlayOutCustomPayloadConstructor = PacketPlayOutCustomPayloadClass.getConstructor(String.class,PacketDataSerializerClass);
	        Object packet = PacketPlayOutCustomPayloadConstructor.newInstance("MC|BOpen",PacketDataSerializerObj);
	        CoreReflection.sendPacket(player, packet);
	        
	    } catch (Exception e) {
	      System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
	      e.printStackTrace();
	    } 
	    
	    if(give) {
	    	if(old!=null) {
	    		 player.getInventory().addItem(old);
	    	}
	    }else {
	    	if(old==null) {
	    		player.getInventory().setItem(slot, new ItemStack(Material.AIR));
	    	}else {
	    		player.getInventory().setItem(slot, old);
	    	}	
	    }
	}
	
	private static void send_1_14_4(Player player, List<String> pages, String title, String name, boolean give)
	{
		int slot = player.getInventory().getHeldItemSlot();
	    ItemStack old = player.getInventory().getItem(slot);
	    player.getInventory().setItem(slot, getBook(player, pages, title, name));
	    player.openBook(getBook(player, pages, title, name));
	    if(give) {
	    	if(old!=null) {
	    		 player.getInventory().addItem(old);
	    	}
	    }else {
	    	if(old==null) {
	    		player.getInventory().setItem(slot, new ItemStack(Material.AIR));
	    	}else {
	    		player.getInventory().setItem(slot, old);
	    	}
	    }
	}
	
	private static void send_1_13(Player player, List<String> pages, String title, String name, boolean give)
	{
		int slot = player.getInventory().getHeldItemSlot();
	    ItemStack old = player.getInventory().getItem(slot);
	    player.getInventory().setItem(slot, getBook(player, pages, title, name));
	    try {
	    	ByteBuf buf = Unpooled.buffer(256);
	        buf.setByte(0, (byte)0);
	        buf.writerIndex(1);
	        Class<?> MinecraftKeyClass = CoreReflection.getNMSClass("MinecraftKey");
	        Constructor<?> MinecraftKeyConstructor = MinecraftKeyClass.getConstructor(String.class);
	        Object MinecraftKeyObj = MinecraftKeyConstructor.newInstance("minecraft:book_open");
	        Class<?> PacketDataSerializerClass = CoreReflection.getNMSClass("PacketDataSerializer");
	        Constructor<?> PacketDataSerializerConstructor = PacketDataSerializerClass.getConstructor(ByteBuf.class);
	        Object PacketDataSerializerObj = PacketDataSerializerConstructor.newInstance(buf);
	        Class<?> PacketPlayOutCustomPayloadClass = CoreReflection.getNMSClass("PacketPlayOutCustomPayload");
	        Constructor<?> PacketPlayOutCustomPayloadConstructor = PacketPlayOutCustomPayloadClass.getConstructor(MinecraftKeyClass,PacketDataSerializerClass);
	        Object packet = PacketPlayOutCustomPayloadConstructor.newInstance(MinecraftKeyObj,PacketDataSerializerObj);
	        CoreReflection.sendPacket(player, packet);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    if(give) {
	    	if(old!=null) {
	    		 player.getInventory().addItem(old);
	    	}
	    }else {
	    	if(old==null) {
	    		player.getInventory().setItem(slot, new ItemStack(Material.AIR));
	    	}else {
	    		player.getInventory().setItem(slot, old);
	    	}
	    }
	}
	
	
	private static void send_1_14(Player player, List<String> pages, String title, String name, boolean give)
	{
		int slot = player.getInventory().getHeldItemSlot();
	    ItemStack old = player.getInventory().getItem(slot);
	    player.getInventory().setItem(slot, getBook(player, pages, title, name));
	    try {
	    	ByteBuf buf = Unpooled.buffer(256);
	        buf.setByte(0, (byte)0);
	        buf.writerIndex(1);
	        Class<?> EnumHandClass = CoreReflection.getNMSClass("EnumHand");
	        Class<?> PacketPlayOutOpenBookClass = CoreReflection.getNMSClass("PacketPlayOutOpenBook");
	        Constructor<?> PacketPlayOutOpenBookConstructor = PacketPlayOutOpenBookClass.getConstructor(EnumHandClass);
	        Object packet = PacketPlayOutOpenBookConstructor.newInstance(EnumHandClass.getField("MAIN_HAND").get(null));
	        CoreReflection.sendPacket(player, packet);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    if(give) {
	    	if(old!=null) {
	    		 player.getInventory().addItem(old);
	    	}
	    }else {
	    	if(old==null) {
	    		player.getInventory().setItem(slot, new ItemStack(Material.AIR));
	    	}else {
	    		player.getInventory().setItem(slot, old);
	    	}
	    }
	}
	
	
	
	private static ItemStack getBook(Player player,List<String> pages, String title, String name) {
	    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
	    BookMeta meta = (BookMeta)book.getItemMeta();
	    if (meta == null) {
	    	return null; 
	    }
	    List<String> coloredPages = new ArrayList<>();
	    for (String page : pages) {
	    	page = CoreVariables.replace(page, player);
	    	coloredPages.add(page);
	    } 
	    meta.setPages(coloredPages);
	    meta.setTitle(CoreColor.colorCodes(title));
	    meta.setDisplayName(CoreColor.colorCodes(name));
	    meta.setAuthor(player.getName());
	    book.setItemMeta(meta);
	    return book;
	  }
}
