package com.pedrojm96.core.effect;
/**
 * Inteface que contiene los metodos del falso BossBar en multiples versiones en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.1 05-09-2022
 *
 */
public abstract interface FakeBossBar {
	public abstract void setName(String paramString);
	public abstract void destroy();
	public abstract void send();
	public abstract float getProgress();
	public abstract boolean isSend();
	public abstract void setProgress(float progress);
	
}
