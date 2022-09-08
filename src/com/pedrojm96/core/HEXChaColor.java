package com.pedrojm96.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;



/**
 * Contiene los metodos para codificar string con colores hexadecimales en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.0 08-09-2022
 *
 */
public class HEXChaColor {
	
	
	
	
	
	private static Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f0-9]){6}>");
	
	
	
	
	public static String colorCodes(String nonColoredText) {
		if(nonColoredText == null){
        	return nonColoredText;
        }
		if(nonColoredText.isEmpty()){
        	return nonColoredText;
        }
		if(nonColoredText.length() <= 0){
        	return nonColoredText;
        }
		if(nonColoredText == ""){
        	return nonColoredText;
        }
        if(nonColoredText == " "){
        	return nonColoredText;
        }
        String coloredText = color(nonColoredText);
        return coloredText;
    }
	

	private static String color(String coloredText) {
		 Matcher matcher = HEX_PATTERN.matcher(coloredText);
		 while (matcher.find()) {
			 ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));	
			 String before = coloredText.substring(0, matcher.start());
			 String after = coloredText.substring(matcher.end());
			 coloredText = String.valueOf(before) + hexColor + after;
			 matcher = HEX_PATTERN.matcher(coloredText);
			
		 }
		 return coloredText;
	}
}
