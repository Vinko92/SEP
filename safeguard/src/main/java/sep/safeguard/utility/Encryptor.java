package sep.safeguard.utility;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

	public static String MD5(String value) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		byte[] bytes = value.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		byte[] encrypted = md.digest(bytes);
		StringBuffer sb = new StringBuffer();
		
	    for (int i = 0; i < encrypted.length; i++) {
	         sb.append(Integer.toString((encrypted[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
		return sb.toString();
	}
	
}
