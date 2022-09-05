package com.pedrojm96.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.plugin.java.JavaPlugin;
/**
 * Permite comprobar si ay actualizaciones para el plugin en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.2 05-09-2022
 *
 */
public class CoreSpigotUpdater {
	private int project = 0;
    private URL checkURL;
    private String newVersion = "";
    private String pluginVersion;
 
    public CoreSpigotUpdater(JavaPlugin plugin, int projectID) {
        this.pluginVersion = plugin.getDescription().getVersion();
        this.project = projectID;
        try {
            this.checkURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + projectID);
        } catch (MalformedURLException e) {
        }
    }

    public int getProjectID() {
        return project;
    }

    public String getLatestVersion() {
        return newVersion;
    }
 
    public String getResourceURL() {
        return "https://www.spigotmc.org/resources/" + project;
    }
 
    public boolean checkForUpdates() throws Exception {
        URLConnection con = checkURL.openConnection();
        this.newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        return !pluginVersion.equals(newVersion);
    }
}
