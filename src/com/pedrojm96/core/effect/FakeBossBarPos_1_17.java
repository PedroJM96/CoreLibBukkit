package com.pedrojm96.core.effect;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.pedrojm96.core.effect.CoreBossBar.Color;
import com.pedrojm96.core.effect.CoreBossBar.Style;
/**
 * Objeto que contiene los metodos del falso BossBar en las versiones iguales o superiores a 1.17 del servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.3 05-09-2022
 *
 */
public class FakeBossBarPos_1_17 implements FakeBossBar{
	private String name;
	private Player player;
	private boolean send = false;
	private float progress;
	private final float maxProgress = 1.0F;
	private boolean isProgress = false;
	private BossBar bar;
	private BarColor color;
	private BarStyle style;
	
	public FakeBossBarPos_1_17(Plugin plugin,Player player, String mensaje,Color color,Style style,float pro) {
		this.name = mensaje;
		this.player = player;
		this.color = BarColor.valueOf(color.name());
		this.style = BarStyle.valueOf(style.name().replaceAll("PROGRESS", "SOLID").replaceAll("NOTCHED", "SEGMENTED"));
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
			System.out.println("Please report the bug at: https://github.com/PedroJM96/CoreLibBukkit");
			e.printStackTrace();
		}
	}
	
	public void createBossBattleServer() throws Exception{
		this.bar = Bukkit.createBossBar(this.name, this.color, this.style);
	}
	
	@Override
	public void setName(String paramString) {
		this.name = paramString;
		bar.setTitle(paramString);	
	}

	@Override
	public void destroy() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			this.bar.removePlayer(player);
		}
		this.bar.removeAll();
	}

	@Override
	public void send() {
		this.bar.setProgress(this.progress);
		this.bar.addPlayer(player);
		this.bar.setVisible(true);

	}
	@Override
	public void setProgress(float progress)
	 {
		this.progress = progress;
	    this.bar.setProgress( (this.isProgress?this.progress:this.maxProgress) );
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
