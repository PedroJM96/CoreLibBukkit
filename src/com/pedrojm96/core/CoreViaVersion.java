package com.pedrojm96.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;





/**
 * Utilidades para el plugin ViaVersion.
 * 
 * @author PedroJM96
 * @version 1.6 08-06-2023
 *
 */
public class CoreViaVersion {

	@SuppressWarnings("rawtypes")
	private static ViaAPI viaApi = null;
	
	public static boolean Setup() {
		if(viaApi!=null) {
			return true;
		}
		if (Bukkit.getPluginManager().getPlugin("ViaVersion") == null) {
			return false;
	    }
	    viaApi = Via.getAPI();
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public static ViaAPI getApi() {
		return viaApi;
	}
	
	@SuppressWarnings("unchecked")
	public static int getPlayerClientVersion(Player p) {
		return viaApi.getPlayerVersion(p);
	}
	
	public static int getProtocolVersion(String version) {
		int retorno;
		switch(version) {
	    case "1.7.2":
	    	retorno = 4;
	    	break;
	    case "1.7.4":
	    	retorno = 4;
	    	break;
	    case "1.7.5":
	    	retorno = 4;
	    	break;
	    case "1.7.6":
	    	retorno = 5;
	    	break;
	    case "1.7.7":
	    	retorno = 5;
	    	break;
	    case "1.7.8":
	    	retorno = 5;
	    	break;
	    case "1.7.9":
	    	retorno = 5;
	    	break;
	    case "1.7.10":
	    	retorno = 5;
	    	break;
	    case "1.8":
	    	retorno = 47;
	    	break;
	    case "1.8.1":
	    	retorno = 47;
	    	break;
	    case "1.8.2":
	    	retorno = 47;
	    	break;
	    case "1.8.3":
	    	retorno = 47;
	    	break;
	    case "1.8.4":
	    	retorno = 47;
	    	break;
	    case "1.8.5":
	    	retorno = 47;
	    	break;
	    case "1.8.6":
	    	retorno = 47;
	    	break;
	    case "1.8.7":
	    	retorno = 47;
	    	break;
	    case "1.8.8":
	    	retorno = 47;
	    	break;
	    case "1.8.9":
	    	retorno = 47;
	    	break;
	    case "1.9":
	    	retorno = 107;
	    	break;
	    case "1.9.1":
	    	retorno = 108;
	    	break;
	    case "1.9.2":
	    	retorno = 109;
	    	break;
	    case "1.9.3":
	    	retorno = 110;
	    	break;
	    case "1.9.4":
	    	retorno = 110;
	    	break;
	    case "1.10":
	    	retorno = 210;
	    	break;
	    case "1.10.1":
	    	retorno = 210;
	    	break;
	    case "1.10.2":
	    	retorno = 210;
	    	break;
	    case "1.11":
	    	retorno = 315;
	    	break;
	    case "1.11.1":
	    	retorno = 316;
	    	break;
	    case "1.11.2":
	    	retorno = 316;
	    	break;
	    case "1.12":
	    	retorno = 335;
	    	break;
	    case "1.12.1":
	    	retorno = 338;
	    	break;
	    case "1.12.2":
	    	retorno = 340;
	    	break;
	    case "1.13":
	    	retorno = 393;
	    	break;
	    case "1.13.1":
	    	retorno = 401;
	    	break;
	    case "1.13.2":
	    	retorno = 404;
	    	break;
	    case "1.14":
	    	retorno = 477;
	    	break;
	    case "1.14.1":
	    	retorno = 480;
	    	break;
	    case "1.14.2":
	    	retorno = 485;
	    	break;
	    case "1.14.3":
	    	retorno = 490;
	    	break;
	    case "1.14.4":
	    	retorno = 498;
	    	break;
	    case "1.15":
	    	retorno = 573;
	    	break;
	    case "1.15.1":
	    	retorno = 575;
	    	break;
	    case "1.15.2":
	    	retorno = 578;
	    	break;
	    case "1.16":
	    	retorno = 735;
	    	break;
	    case "1.16.1":
	    	retorno = 736;
	    	break;
	    case "1.16.2":
	    	retorno = 751;
	    	break;
	    case "1.16.3":
	    	retorno = 753;
	    	break;
	    case "1.16.4":
	    	retorno = 754;
	    	break;
	    case "1.16.5":
	    	retorno = 754;
	    	break;
	    case "1.17":
	    	retorno = 755;
	    	break;
	    case "1.17.1":
	    	retorno = 756;
	    	break;
	    case "1.18":
	    	retorno = 757;
	    	break;
	    case "1.18.1":
	    	retorno = 757;
	    	break;
	    case "1.18.2":
	    	retorno = 758;
	    	break;
	    case "1.19":
	    	retorno = 759;
	    	break;
	    case "1.19.1":
	    	retorno = 760;
	    	break;
	    case "1.19.2":
	    	retorno = 760;
	    	break;
	    case "1.19.3":
	    	retorno = 761;
	    	break;
	    case "1.19.4":
	    	retorno = 762;
	    	break;
	    case "1.20":
	    	retorno = 763;
	    	break;
	    default:
	    	retorno = 0;
	    	break;
	    }
		return retorno;
	}
}
