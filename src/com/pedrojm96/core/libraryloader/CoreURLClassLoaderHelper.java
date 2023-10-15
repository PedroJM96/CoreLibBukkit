package com.pedrojm96.core.libraryloader;

import java.nio.file.Path;

/**
 * Para cargar las clases clas loader del plugins.
 *
 * @author PedroJM96
 * @version 1.1 05-09-2022
 * 
 */
public interface CoreURLClassLoaderHelper extends AutoCloseable {
	void addJarToClasspath(Path file);
	
    @Override
    default void close() {

    }
}
