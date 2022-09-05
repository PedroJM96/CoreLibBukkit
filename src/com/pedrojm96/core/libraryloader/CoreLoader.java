package com.pedrojm96.core.libraryloader;


/**
 * Para cargar las clases clas loader del plugins.
 *
 * @author PedroJM96
 * @version 1.1 05-09-2022
 * 
 */
public interface CoreLoader {
    public void onLoad();
    public default void onEnable() {}
    public default void onDisable() {}
}
