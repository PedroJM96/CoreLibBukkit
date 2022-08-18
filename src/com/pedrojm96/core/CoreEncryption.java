package com.pedrojm96.core;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
/**
 * Contiene los metodos estaticos para encritar string.
 * 
 * @author PedroJM96
 * @version 1.0 22-09-2018
 *
 */
public enum CoreEncryption {
	SHA512("SHA512"),  
	MD5("MD5"),  
	SHA256("SHA256");  
	
	private String type;
	
	private CoreEncryption(String type) {
		this.type = type;
	}
	
	
	public String encrypt(String base) {
		String retorno;
		switch(this.type) {
		case "SHA512":
			retorno = sha512(base);
			break;
		case "MD5":
			retorno = md5(base);
			break;
		case "SHA256":
			retorno = sha256(base);
			break;
		default:
			retorno = md5(base);
			break;
		}
		return retorno;
	}
	
	
	private String sha512(String base)
	{
		try
	    {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++)
			{
				String hex = Integer.toHexString(0xFF & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
	    }
	    catch (Exception ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	private String md5(String base)
	{
		try
	    {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++)
			{
				String hex = Integer.toHexString(0xFF & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
	    }
	    catch (Exception ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	private String sha256(String base)
	{
	    try
	    {
	    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    	byte[] hash = digest.digest(base.getBytes("UTF-8"));
	    	StringBuffer hexString = new StringBuffer();
	    	for (int i = 0; i < hash.length; i++)
	    	{
	    		String hex = Integer.toHexString(0xFF & hash[i]);
	    		if (hex.length() == 1) {
	    			hexString.append('0');
	    		}
	    		hexString.append(hex);
	    	}
	    	return hexString.toString();
	    }
	    catch (Exception ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	public static String generateRandomText(int size) {
		 SecureRandom random = new SecureRandom();
		 String text = new BigInteger(size, random).toString(32);
		 return text;
	}
}
