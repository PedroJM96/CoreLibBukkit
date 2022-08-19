package com.pedrojm96.core.effect;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.pedrojm96.core.CoreReflection;
import com.pedrojm96.core.effect.CoreBossBar.Color;
import com.pedrojm96.core.effect.CoreBossBar.Style;
/**
 * Objeto que contiene los metodos del falso BossBar en la versiones superiores a la 1.9 y menores a la 1.17 del servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.1 19-08-2022
 *
 */
public class FakeBossBarPos_1_9 implements FakeBossBar{
	private UUID uuid;
	private String name;
	
	private Player player;
	private boolean send = false;
	private Object PacketPlayOutBoss;
	
	private float progress;
	private final float maxProgress = 1.0F;
	private boolean isProgress = false;
	
	
	private Class<?> BossBattle_BarColorClass =  CoreReflection.getNMSClass("BossBattle$BarColor"); 
	private Class<?> BossBattle_BarStyleClass = CoreReflection.getNMSClass("BossBattle$BarStyle");
	
	private Class<?> PacketPlayOutBoss_ActionClass = CoreReflection.getNMSClass("PacketPlayOutBoss$Action");
	
	private Class<?> PacketPlayOutBossClass = CoreReflection.getNMSClass("PacketPlayOutBoss");
	
	Class<?> ChatSerializerClass = CoreReflection.getNMSClass("IChatBaseComponent$ChatSerializer");
	
	
	private Color color;
	private Style style;
	
	
	public FakeBossBarPos_1_9(Player player, String mensaje,Color color,Style style,float pro) {
		this.name = mensaje;
		this.uuid = UUID.randomUUID();
		this.player = player;
		this.color = color;
		this.style = style;
				    
		if(pro>0) {
			isProgress = true;
			this.progress = pro;
		}else {
			isProgress = false;
			this.progress = this.maxProgress;

		}
		
		try {
			createBossBattleServer();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void createBossBattleServer() throws Exception{
		
		PacketPlayOutBoss = PacketPlayOutBossClass.newInstance();

    	Field aField = PacketPlayOutBoss.getClass().getDeclaredField("a");
        aField.setAccessible(true);
        aField.set(PacketPlayOutBoss, this.uuid);
        
        Field bField = PacketPlayOutBoss.getClass().getDeclaredField("b");
        bField.setAccessible(true);
        bField.set(PacketPlayOutBoss, PacketPlayOutBoss_ActionClass.getEnumConstants()[0]);//Action.Add
        
        Field cField = PacketPlayOutBoss.getClass().getDeclaredField("c");
        cField.setAccessible(true);
        cField.set(PacketPlayOutBoss, ChatSerializerClass.getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + this.name + "\"}" }));
        
        Field dField = PacketPlayOutBoss.getClass().getDeclaredField("d");
        dField.setAccessible(true);
        dField.set(PacketPlayOutBoss, Float.valueOf(this.progress)); //Progreso
        
        Field eField = PacketPlayOutBoss.getClass().getDeclaredField("e");
        eField.setAccessible(true);
        eField.set(PacketPlayOutBoss, BossBattle_BarColorClass.getEnumConstants()[this.color.ordinal()]);
        
        Field fField = PacketPlayOutBoss.getClass().getDeclaredField("f");
        fField.setAccessible(true);
        fField.set(PacketPlayOutBoss, BossBattle_BarStyleClass.getEnumConstants()[this.style.ordinal()]);
        
        Field gField = PacketPlayOutBoss.getClass().getDeclaredField("g");
        gField.setAccessible(true);
        gField.set(PacketPlayOutBoss, Boolean.valueOf(false)); //Boolean.valueOf(this.darkenSky)
        
        Field hField = PacketPlayOutBoss.getClass().getDeclaredField("h");
        hField.setAccessible(true);
        hField.set(PacketPlayOutBoss, Boolean.valueOf(false)); //Boolean.valueOf(this.playMusic)
        
        Field iField = PacketPlayOutBoss.getClass().getDeclaredField("i");
        iField.setAccessible(true);
        iField.set(PacketPlayOutBoss, Boolean.valueOf(false)); //Boolean.valueOf(this.createFog)
	}
	
	
	
	@Override
	public void setName(String paramString) {
		// TODO Auto-generated method stub
		this.name = paramString;
		try {
			Field cField = PacketPlayOutBoss.getClass().getDeclaredField("c");
	        cField.setAccessible(true);
	        cField.set(PacketPlayOutBoss, ChatSerializerClass.getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + this.name + "\"}" }));
		}catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {
		try {
			Field bField = PacketPlayOutBoss.getClass().getDeclaredField("b");
	        bField.setAccessible(true);
	        bField.set(PacketPlayOutBoss, PacketPlayOutBoss_ActionClass.getEnumConstants()[1]);//Action.Remove
	        
	        
			CoreReflection.sendPacket(player, PacketPlayOutBoss);
			this.send = false;
		}catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
		
	}

	@Override
	public void send() {
		// TODO Auto-generated method stub
		try {
		
			
			Field bField = PacketPlayOutBoss.getClass().getDeclaredField("b");
	        bField.setAccessible(true);
	        bField.set(PacketPlayOutBoss, PacketPlayOutBoss_ActionClass.getEnumConstants()[0]);//Action.Add
	        
			CoreReflection.sendPacket(player, PacketPlayOutBoss);
			this.send = true;
		}catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
		
		
	}

	

	private void update() {
		// TODO Auto-generated method stub
		try {
			if(isProgress) {
				Field bField = PacketPlayOutBoss.getClass().getDeclaredField("b");
			    bField.setAccessible(true);
			    bField.set(PacketPlayOutBoss, PacketPlayOutBoss_ActionClass.getEnumConstants()[2]);//Action.PCT
			       		    
				CoreReflection.sendPacket(player, PacketPlayOutBoss);
			}
			
			
		}catch (Exception e) {
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
	}

	public void setProgress(float progress)
	  {
	    if (progress > 1.0F) {
	      progress /= 100.0F;
	    }
	    if (progress != this.progress)
	    {
	    	this.progress = progress;
	      	if(isProgress) {
	      		try {
		      		 Field dField = PacketPlayOutBoss.getClass().getDeclaredField("d");
				     dField.setAccessible(true);
				     dField.set(PacketPlayOutBoss, Float.valueOf(this.progress)); //Pro
		      	}catch (Exception e) {
		      		System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
					e.printStackTrace();
		      	}
	      	}
	    }
	    
	    update();
	    
	 }
	
	
	
	@Override
	public boolean isSend() {
		// TODO Auto-generated method stub
		return this.send;
	}

	@Override
	public float getProgress() {
		// TODO Auto-generated method stub
		return this.progress;
	}
	
}
