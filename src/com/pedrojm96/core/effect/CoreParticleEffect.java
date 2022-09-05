package com.pedrojm96.core.effect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import xyz.xenondevs.particle.ParticleEffect;


/**
 * Contiene los metodos estaticos para enviar efetos de particulas en el servidor.
 * 
 * @author PedroJM96
 * @version 1.2 05-09-2022
 *
 */
public enum CoreParticleEffect {
	
	//minecraft 1.8.8
	/**
	 * Un efecto de part�culas que se muestra al explotar tnt y creepers:
	 * <ul>
	 * <li>Parece una nube blanca.
	 * <li>El valor de velocidad influye en la velocidad a la que la part�cula se va volando.
	 * <li>DIRECTIONAL.
	 * </ul>
	 */
	EXPLOSION_NORMAL,
	/**
	 * Un efecto de part�culas que se muestra al explotar bolas de fuego de ghat y wither skulls:
	 * <ul>
	 * <li>Parece una bola gris que se est� desvaneciendo
	 * <li>El valor de velocidad influye ligeramente en el tama�o de este efecto de part�cula,
	 * </ul>
	 */
	EXPLOSION_HUGE, 
	/**
	 * Un efecto de part�culas que se muestra al explotar tnt y creepers:
	 * <ul>
	 * <li> Parece una multitud de bolas grises que se est�n desvaneciendo
	 * <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	 * </ul>
	 */
	EXPLOSION_LARGE,  
	/**
	 * Un efecto de part�cula que se muestra al lanzar fuegos artificiales:
	 * <ul>
	 * <li> Parece una estrella blanca que brilla
	 * <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	 * <li>DIRECTIONAL.
	 * </ul>
	 */
	FIREWORKS_SPARK,  
	/**
	* Un efecto de part�culas que se muestra por entidades de nataci�n y flechas en el agua:
	* <ul>
	* <li> Parece una burbuja
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	WATER_BUBBLE,  
	/**
	* Un efecto de part�culas que se muestra por entidades de nataci�n y lobos temblorosos:
	* <ul>
	* <li> Parece una gota azul
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* <li>DIRECTIONAL.
	* </ul>
	*/
	WATER_SPLASH, 
	/**
	* Un efecto de part�culas que se muestra en el agua cuando se pesca:
	* <ul>
	* <li> Parece una gota azul
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	WATER_WAKE,  
	/**
	* Un efecto de part�culas que se muestra en el agua:
	* <ul>
	* <li> Parece un peque�o cuadrado azul
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	SUSPENDED,  
	/**
	* Un efecto de part�culas que se muestra por aire cuando est� cerca del lecho de roca y en el vac�o:
	* <ul>
	* <li> Parece un peque�o cuadrado gris
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* <li>DIRECTIONAL.
	* </ul>
	*/
	SUSPENDED_DEPTH, 
	/**
	* Un efecto de part�culas que se muestra al aterrizar un golpe cr�tico y con flechas:
	* <ul>
	* <li> Parece una cruz marr�n claro
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	CRIT,  
	/**
	* Un efecto de part�cula que se muestra al aterrizar un golpe con un arma encantada:
	* <ul>
	* <li> Parece una estrella cian
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	CRIT_MAGIC, 
	/**
	* Un efecto de part�culas que se muestra por tnt imprimado, antorchas, goteros, dispensadores, portales de extremo, puestos de preparaci�n y monstruos reproductores:
	* <ul>
	* <li> Parece una peque�a nube gris
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	SMOKE_NORMAL,
	/**
	* Un efecto de part�culas que se muestra por fuego, minecarts con horno y llamas:
	* <ul>
	* <li> Parece una gran nube gris
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	SMOKE_LARGE, 
	/**
	* Un efecto de part�cula que se muestra cuando las pociones o las botellas de chapoteo o encantando golpean algo:
	* <ul>
	* <li> Parece un remolino blanco
	* <li> El valor de velocidad hace que la part�cula solo se mueva hacia arriba cuando se establece en 0
	* <li> Solo se puede controlar el movimiento en el eje Y, el movimiento en los ejes X e Z se multiplica por 0.1 al establecer los valores en 0
	* </ul>
	*/
	SPELL,  
	/**
	* Un efecto de part�culas que se muestra cuando las pociones de salpicaduras instant�neas golpean algo:
	* <ul>
	* <li> Parece una cruz blanca
	* <li> El valor de velocidad hace que la part�cula solo se mueva hacia arriba cuando se establece en 0
	* <li> Solo se puede controlar el movimiento en el eje Y, el movimiento en los ejes X e Z se multiplica por 0.1 al establecer los valores en 0
	* </ul>
	*/
	SPELL_INSTANT, 
	/**
	* Un efecto de part�cula que se muestra por entidades con efectos de poci�n activa:
	* <ul>
	* <li> Parece un remolino de color
	* <li> El valor de velocidad hace que la part�cula se coloree de negro cuando se establece en 0
	* <li> El color de la part�cula se vuelve m�s claro al aumentar la velocidad y m�s oscuro al disminuir la velocidad
	* <li>COLORABLE.
	* </ul>
	*/
	SPELL_MOB,
	/**
	* Un efecto de part�cula que se muestra por entidades con efectos de poci�n activa aplicados a trav�s de una baliza:
	* <ul>
	* <li> Parece un remolino de color transparente
	* <li> El valor de velocidad hace que la part�cula siempre se coloree en negro cuando se establece en 0
	* <li> El color de la part�cula se vuelve m�s claro al aumentar la velocidad y m�s oscuro al disminuir la velocidad
	* <li>COLORABLE.
	* </ul>
	*/
	SPELL_MOB_AMBIENT,
	/**
	* Un efecto de part�cula que se muestra por brujas:
	* <ul>
	* <li> Parece una cruz morada
	* <li> El valor de velocidad hace que la part�cula solo se mueva hacia arriba cuando se establece en 0
	* <li> Solo se puede controlar el movimiento en el eje Y, el movimiento en los ejes X e Z se multiplica por 0.1 al establecer los valores en 0
	* </ul>
	*/
	SPELL_WITCH, 
	/**
	* Un efecto de part�culas que se muestra por bloques debajo de una fuente de agua:
	* <ul>
	* <li> Parece un goteo azul
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	DRIP_WATER, 
	/**
	* Un efecto de part�culas que se muestra por bloques debajo de una fuente de lava:
	* <ul>
	* <li> Parece un goteo naranja
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	DRIP_LAVA,
	/**
	* Un efecto de part�culas que se muestra al atacar a un aldeano en un pueblo:
	* <ul>
	* <li> Parece un coraz�n gris agrietado
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	VILLAGER_ANGRY, 
	/**
	* Un efecto de part�culas que se muestra cuando se usa harina de huesos y se comercia con un aldeano de una aldea:
	* <ul>
	* <li> Parece una estrella verde
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* <li>DIRECTIONAL.
	* </ul>
	*/
	VILLAGER_HAPPY,
	/**
	* Un efecto de part�cula que se muestra por micelio:
	* <ul>
	* <li> Parece un peque�o cuadrado gris
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* <li>DIRECTIONAL.
	* </ul>
	*/
	TOWN_AURA,  
	/**
	* Un efecto de part�cula que se muestra con bloques de notas:
	* <ul>
	* <li> Parece una nota de color
	* <li> El valor de velocidad hace que la part�cula se coloree en verde cuando se establece en 0
	* <li>COLORABLE.
	* </ul>
	*/
	NOTE,  
	/**
	* Un efecto de part�culas que se muestra en portales del infierno, endermen, perlas de ender, ojos de ender, cofres de ender y huevos de drag�n:
	* <ul>
	* <li> Parece una nube p�rpura
	* <li> El valor de velocidad influye en la propagaci�n de este efecto de part�cula
	* <li>DIRECTIONAL.
	* </ul>
	*/
	PORTAL,  
	/**
	* Un efecto de part�culas que se muestra en las tablas de encantamiento que est�n cerca de las estanter�as:
	* <ul>
	* <li> Parece una carta blanca cr�ptica
	* <li> El valor de velocidad influye en la propagaci�n de este efecto de part�cula
	* <li>DIRECTIONAL.
	* </ul>
	*/
	ENCHANTMENT_TABLE, 
	/**
	* Un efecto de part�culas que se muestra con antorchas, hornos activos, cubos de magma y reproductores de monstruos:
	* <ul>
	* <li> Parece una peque�a llama
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	FLAME,
	/**
	* Un efecto de part�culas que se muestra por la lava:
	* <ul>
	* <li> Parece una chispa
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	LAVA,  
	/**
	* Un efecto de part�cula que no se utiliza actualmente:
	* <ul>
	* <li> Parece un cuadrado gris transparente
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	FOOTSTEP, 
	/**
	* Un efecto de part�culas que se muestra cuando muere un mob:
	* <ul>
	* <li> Parece una gran nube blanca
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	CLOUD, 
	/**
	* Un efecto de part�cula que se muestra con redstone ore, redstone activado, antorchas de redstone y repetidores de redstone:
	* <ul>
	* <li> Parece una peque�a nube de color
	* <li> El valor de velocidad hace que la part�cula se coloree de rojo cuando se establece en 0
	* <li>COLORABLE.
	* </ul>
	*/
	REDSTONE, 
	/**
	* Un efecto de part�culas que se muestra cuando las bolas de nieve golpean un bloque:
	* <ul>
	* <li> Parece una peque�a pieza con la textura de bola de nieve
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	SNOWBALL,  
	/**
	* Un efecto de part�cula que no se utiliza actualmente:
	* <ul>
	* <li> Parece una peque�a nube blanca
	* <li> El valor de velocidad influye en la velocidad a la que la part�cula sale volando
	* <li>DIRECTIONAL.
	* </ul>
	*/
	SNOW_SHOVEL, 
	/**
	* Un efecto de part�culas que se muestra por los slime:
	* <ul>
	* <li> Parece una peque�a parte del icono del slimeball
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	SLIME,  
	/**
	* Un efecto de part�culas que se muestra al criar y domesticar animales:
	* <ul>
	* <li> Parece un coraz�n rojo
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	HEART, 
	/**
	* Un efecto de part�culas que se muestra por barreras:
	* <ul>
	* <li> Parece una caja roja con una barra que lo atraviesa
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	BARRIER,  
	/**
	* Un efecto de part�culas que se muestra al romper una herramienta o los huevos golpean un bloque:
	* <ul>
	* <li> Parece una peque�a pieza con una textura de art�culo
	* <li>DIRECTIONAL.
	* </ul>
	*/
	ITEM_CRACK,  
	/**
	* Un efecto de part�cula que se muestra al romper bloques o esprintar:
	* <ul>
	* <li> Parece una peque�a pieza con una textura de bloque
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	BLOCK_CRACK, 
	/**
	* Un efecto de part�culas que se muestra al caer:
	* <ul>
	* <li> Parece una peque�a pieza con una textura de bloque
	* <li>DIRECTIONAL.
	* </ul>
	*/
	BLOCK_DUST,  
	/**
	* Un efecto de part�culas que se muestra cuando la lluvia golpea el suelo:
	* <ul>
	* <li> Parece una gota azul
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* </ul>
	*/
	WATER_DROP,
	/**
	* Un efecto de part�cula que no se utiliza actualmente:
	* <ul>
	* <li> No tiene efecto visual
	* </ul>
	*/
	ITEM_TAKE,  
	/**
	* Un efecto de part�cula que se muestra por los elder guardians:
	* <ul>
	* <li> Parece la forma del elder guardians
	* <li> El valor de velocidad no tiene influencia en este efecto de part�cula
	* <li> Los valores de desplazamiento no tienen influencia en este efecto de part�cula
	* </ul>
	*/
	MOB_APPEARANCE;
	
	public void playAll(Location loc, float xOffset, float yOffset, float zOffset, float speed, int count,int... extra) {
		ParticleEffect.valueOf(this.name()).display(loc,new Vector(xOffset, yOffset, zOffset),speed,count,null, Bukkit.getOnlinePlayers()  );
	}
	
	public void play(Player player,Location loc, float xOffset, float yOffset, float zOffset, float speed, int count,int... extra) {
		ParticleEffect.valueOf(this.name()).display(loc,new Vector(xOffset, yOffset, zOffset),speed,count,null, player );
	}
}
