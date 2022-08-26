package com.pedrojm96.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bukkit.Bukkit;



public class FileUtil {
	 
	 @SuppressWarnings({ "rawtypes", "resource" })
		public static void extractFolder(String zipFile,String extractFolder) 
		{
		    try
		    {
		        int BUFFER = 2048;
		        File file = new File(zipFile);

		        
				ZipFile zip = new ZipFile(file);
		        String newPath = extractFolder;

		        new File(newPath).mkdir();
		        Enumeration zipFileEntries = zip.entries();

		        // Process each entry
		        while (zipFileEntries.hasMoreElements())
		        {
		            // grab a zip file entry
		            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
		            String currentEntry = entry.getName();

		            File destFile = new File(newPath, currentEntry);
		            //destFile = new File(newPath, destFile.getName());
		            File destinationParent = destFile.getParentFile();

		            // create the parent directory structure if needed
		            destinationParent.mkdirs();

		            if (!entry.isDirectory())
		            {
		                BufferedInputStream is = new BufferedInputStream(zip
		                .getInputStream(entry));
		                int currentByte;
		                // establish buffer for writing file
		                byte data[] = new byte[BUFFER];

		                // write the current file to disk
		                FileOutputStream fos = new FileOutputStream(destFile);
		                BufferedOutputStream dest = new BufferedOutputStream(fos,
		                BUFFER);

		                // read and write until last byte is encountered
		                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
		                    dest.write(data, 0, currentByte);
		                }
		                dest.flush();
		                dest.close();
		                is.close();
		            }


		        }
		    }
		    catch (Exception e) 
		    {	Bukkit.getLogger().severe("ERROR: "+e.getMessage());
		        
		    }

		}
	 
	 public static void copy(InputStream in, File file) {
		    try {
		        OutputStream out = new FileOutputStream(file);
		        byte[] buf = new byte[1024];
		        int len;
		        while((len=in.read(buf))>0){
		            out.write(buf,0,len);
		        }
		        out.close();
		        in.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 
/*	 public static void zipFolder(final File folder, final File zipFile) throws IOException {
	        zipFolder(folder, new FileOutputStream(zipFile));
	    }*/

	   /* public static void zipFolder(final File folder, final OutputStream outputStream) throws IOException {
	        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
	            processFolder(folder, zipOutputStream, folder.getPath().length() + 1);
	        }
	    }*/

	   /* private static void processFolder(final File folder, final ZipOutputStream zipOutputStream, final int prefixLength)
	            throws IOException {
	        for (final File file : folder.listFiles()) {
	            if (file.isFile()) {
	                final ZipEntry zipEntry = new ZipEntry(file.getPath().substring(prefixLength));
	                zipOutputStream.putNextEntry(zipEntry);
	                try (FileInputStream inputStream = new FileInputStream(file)) {
	                    IOUtils.copy(inputStream, zipOutputStream);
	                }
	                zipOutputStream.closeEntry();
	            } else if (file.isDirectory()) {
	                processFolder(file, zipOutputStream, prefixLength);
	            }
	        }
	    }*/
}
