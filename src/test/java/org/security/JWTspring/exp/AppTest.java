package org.security.JWTspring.exp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 * Unit test for simple App.
 */
public class AppTest 
    
{
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		
		/*Key key = MacProvider.generateKey(SignatureAlgorithm.HS256);

		//Get the key data
		byte keyData[]= key.getEncoded();
		//Store data in a file...

		//Build key
		Key key2 = new SecretKeySpec(keyData, SignatureAlgorithm.HS256.getJcaName());
		System.out.println(key2.getAlgorithm());*/
		
		/*MessageDigest md = MessageDigest.getInstance("SHA-256");
		String text = "This is some text";

		md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		byte[] digest = md.digest();
		System.out.println(digest);*/
		/*MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String text = "welcom";
		byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(hash);
		
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(text);   
		System.out.println(sha256hex);*/
		
		
		try{
		        MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        byte[] hash = digest.digest("Welcome".getBytes("UTF-8"));
		        StringBuffer hexString = new StringBuffer();
		        String encoded = Base64.getEncoder().encodeToString("Welcome".getBytes());
		        
		        System.out.println(encoded);
		        for (int i = 0; i < hash.length; i++) {
		            String hex = Integer.toHexString(0xff & hash[i]);
		            if(hex.length() == 1) hexString.append('0');
		            hexString.append(hex);
		        }

		        System.out.println(hexString.toString());
		    } catch(Exception ex){
		       throw new RuntimeException(ex);
		    }
	}
    
}
