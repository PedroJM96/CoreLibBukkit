package com.pedrojm96.core.effect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.pedrojm96.core.CoreReflection;

/**
 * Objeto que contiene los metodos del falso BossBar en las versionnes iguales o inferiores a 1.8.8 del servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.2 05-09-2022
 *
 */
public class FakeBossBarPre1_9 implements FakeBossBar{
	private int ID = new Random().nextInt();
	private String name;
	private float health = 0.0F;
	private final float maxHealth = 300F;
	private Object PacketPlayOutSpawnEntityLivin;
	private Player player;
	private boolean isProgress = false;
	private Class<?> EntityClass = CoreReflection.getNMSClass("Entity");
	private Class<?> DataWatcherClass = CoreReflection.getNMSClass("DataWatcher");
	private Class<?> PacketPlayOutSpawnEntityLivinClass = CoreReflection.getNMSClass("PacketPlayOutSpawnEntityLiving");
	private boolean send = false;
	
	public FakeBossBarPre1_9(Player player, String mensaje, float pro) {
		this.name = mensaje;
		this.player = player;
		if(pro>0) {
			isProgress = true;
			this.health = pro;
		}else {
			isProgress = false;
			this.health = this.maxHealth;
		}
		try {
			createDragon();
		} catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void createDragon() throws Exception{
		PacketPlayOutSpawnEntityLivin = PacketPlayOutSpawnEntityLivinClass.newInstance();
		Location loc = makeLocation(this.player.getLocation());
    	Field aField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("a");
        aField.setAccessible(true);
        aField.set(PacketPlayOutSpawnEntityLivin, Integer.valueOf(ID));
        Field bField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("b");
        bField.setAccessible(true);
        bField.set(PacketPlayOutSpawnEntityLivin, Integer.valueOf(64)); //64== winter//63==enderdragon
        Field cField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("c");
        cField.setAccessible(true);
        cField.set(PacketPlayOutSpawnEntityLivin, Integer.valueOf(loc.getBlockX() * 32));
        Field dField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("d");
        dField.setAccessible(true);
        dField.set(PacketPlayOutSpawnEntityLivin, Integer.valueOf(loc.getBlockY() * 32));
        Field eField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("e");
        eField.setAccessible(true);
        eField.set(PacketPlayOutSpawnEntityLivin, Integer.valueOf(loc.getBlockZ() * 32));
        Field iField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("i");
        iField.setAccessible(true);
        iField.set(PacketPlayOutSpawnEntityLivin, Byte.valueOf((byte)((int)loc.getYaw() * 256 / 360)));
        Field jField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("j");
        jField.setAccessible(true);
        jField.set(PacketPlayOutSpawnEntityLivin, Byte.valueOf((byte)((int)loc.getPitch() * 256 / 360)));
        Field kField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("k");
        kField.setAccessible(true);
        kField.set(PacketPlayOutSpawnEntityLivin, Byte.valueOf((byte)((int)loc.getPitch() * 256 / 360)));
        Field lField = PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("l");
        lField.setAccessible(true);
        lField.set(PacketPlayOutSpawnEntityLivin, getWatcher()); 
	}

	@Override
	public void setName(String paramString) {
		try {
			this.name = paramString;
		} catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void destroy() {
		try {
			Class<?> PacketPlayOutEntityDestroy = CoreReflection.getNMSClass("PacketPlayOutEntityDestroy"); 
			Object packet = PacketPlayOutEntityDestroy.newInstance();
			Field a = PacketPlayOutEntityDestroy.getDeclaredField("a");
			a.setAccessible(true);
			a.set(packet, new int[] { this.ID });
			CoreReflection.sendPacket(player, packet);
			this.send = false;
		}catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		} 
	}
	
	@Override
	public void send(){
		try {
			CoreReflection.sendPacket(player, PacketPlayOutSpawnEntityLivin);
			teleport();
			this.send = true;
		}catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
	}
	
	private void teleport() throws Exception{
		//Location loc = getDragonLocation(this.player.getLocation());
		Location loc = makeLocation(this.player.getLocation());
		Constructor<?> constructorPacketPlayOutEntityTeleport = CoreReflection.getNMSClass("PacketPlayOutEntityTeleport").getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Byte.TYPE, Byte.TYPE, Boolean.TYPE });
		Object packet = constructorPacketPlayOutEntityTeleport.newInstance(new Object[] { Integer.valueOf(this.ID), Integer.valueOf(loc.getBlockX() * 32), Integer.valueOf(loc.getBlockY() * 32), Integer.valueOf(loc.getBlockZ() * 32), Byte.valueOf((byte)((int)loc.getYaw() * 256 / 360)), Byte.valueOf((byte)((int)loc.getPitch() * 256 / 360)), Boolean.valueOf(false) });
		CoreReflection.sendPacket(player, packet);
	}
	
	private void sendMetaPacket() throws Exception{
	     Constructor<?> constructorMeta = CoreReflection.getNMSClass("PacketPlayOutEntityMetadata").getConstructor(new Class[] { Integer.TYPE, DataWatcherClass, Boolean.TYPE });
		 Object packet = constructorMeta.newInstance(new Object[] { Integer.valueOf(this.ID), getWatcher(), Boolean.valueOf(true) });
		 CoreReflection.sendPacket(player, packet);
	}
	
	@SuppressWarnings("removal")
	public Object getWatcher() {
		Object watcher = null;
		try {
			Object entyti = null;
			watcher = DataWatcherClass.getConstructor(new Class<?>[] { EntityClass }).newInstance(entyti);
			Method a = DataWatcherClass.getMethod("a", Integer.TYPE, Object.class);
			a.invoke(watcher, 0, (byte) 32 ); // 0 --- 32   (0=visible) (32=invisible)
			a.invoke(watcher, 6, (Float) (this.isProgress?health:this.maxHealth)); //barra de vida o progreso
			a.invoke(watcher, 7, (Integer) 0); // desconosido
			a.invoke(watcher, 8, (Byte) (byte) 0); //desconosido
			a.invoke(watcher, 10, name); //nombre
			a.invoke(watcher, 2, name);//nombre
			a.invoke(watcher, 11, (Byte) (byte) 1);//desconosido
			a.invoke(watcher, 17, new Integer(0));
			a.invoke(watcher, 18, new Integer(0));
			a.invoke(watcher, 19, new Integer(0));
			a.invoke(watcher, 20, new Integer(1000));
			
		}catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
		return watcher;
	}
	
	@SuppressWarnings("unused")
	private static Location getDragonLocation(Location loc)
	  {
	    float pitch = loc.getPitch();
	    if (pitch >= 55.0F) {
	      loc.add(0.0D, -300.0D, 0.0D);
	    } else if (pitch <= -55.0F) {
	      loc.add(0.0D, 300.0D, 0.0D);
	    } else {
	      loc = loc.getBlock().getRelative(getDirection(loc), Bukkit.getServer().getViewDistance() * 16).getLocation();
	    }
	    return loc;
	  }
	
	protected Location makeLocation(Location base)
	{
	    return base.getDirection().multiply(32).add(base.toVector()).toLocation(base.getWorld());
	}

	 private static BlockFace getDirection(Location loc)
	  {
	    float dir = Math.round(loc.getYaw() / 90.0F);
	    if ((dir == -4.0F) || (dir == 0.0F) || (dir == 4.0F)) {
	      return BlockFace.SOUTH;
	    }
	    if ((dir == -1.0F) || (dir == 3.0F)) {
	      return BlockFace.EAST;
	    }
	    if ((dir == -2.0F) || (dir == 2.0F)) {
	      return BlockFace.NORTH;
	    }
	    if ((dir == -3.0F) || (dir == 1.0F)) {
	      return BlockFace.WEST;
	    }
	    return null;
	  }

	private void update() {
		// TODO Auto-generated method stub
		try {
			if(isProgress) {
				sendMetaPacket();
			}
			teleport();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isSend() {
		// TODO Auto-generated method stub
		return this.send;
	}

	@Override
	public float getProgress() {
		// TODO Auto-generated method stub
		return this.health;
	}

	@Override
	public void setProgress(float progress) {
		// TODO Auto-generated method stub
		this.health = progress;
		update();
	}
}