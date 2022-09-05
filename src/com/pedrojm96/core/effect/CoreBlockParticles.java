package com.pedrojm96.core.effect;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.pedrojm96.core.CoreUtils;

import xyz.xenondevs.particle.ParticleEffect;
/**
 * Contiene los metodos para colocar particulas a los bloques en multiples versiones en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.2 05-09-2022
 *
 */
public class CoreBlockParticles {
	
	private static class PlayCircle extends BukkitRunnable{
		
		private ArrayList<Location> locations;
		private ParticleEffect effect;
		private Effect effectOLD;
		private World world;
		private int ronda = 0; 
		private boolean old = false;
		
		public PlayCircle(ArrayList<Location> locations,ParticleEffect effect) {
			this.locations = locations;
			this.effect = effect;
			old = false;
		}
		
		public PlayCircle(ArrayList<Location> locations, Effect effect)
	    {
	      this.locations = locations;
	      this.effectOLD = effect;
	      this.world = ((Location)locations.get(0)).getWorld();
	      old = true;
	    }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(old) {
				 this.world.playEffect((Location)this.locations.get(this.ronda), this.effectOLD, 1);
			}else {
				this.effect.display(this.locations.get(ronda),new Vector(0, 0, 0),2f,1,null, Bukkit.getOnlinePlayers()  );
			}	
			ronda++;
			if(ronda >= this.locations.size()) {
				ronda = 0;
			}
		}	
	}

	public static Integer playCircle(Location center, double radius, int amount,ParticleEffect effect,JavaPlugin plugin) {
		ArrayList<Location> locations = CoreUtils.getCircleLocation(center, radius, amount);
		return new PlayCircle(locations,effect).runTaskTimer(plugin, 0L, 3L).getTaskId();
	}

	public static Integer playCircle(Location center, double radius, int amount, Effect effect, JavaPlugin plugin)
	{
	    ArrayList<Location> locations = CoreUtils.getCircleLocation(center, radius, amount);
	    return Integer.valueOf(new PlayCircle(locations, effect).runTaskTimer(plugin, 0L, 3L).getTaskId());
	}
}
