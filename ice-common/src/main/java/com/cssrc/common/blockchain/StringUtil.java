package com.cssrc.common.blockchain;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;

import com.google.gson.GsonBuilder;

public class StringUtil {
	
	//Applies Sha256 to a string and returns the result. 
	public static String applySha256(String input){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//Short hand helper to turn Object into a json string
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	//Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"  
	public static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
	
	private static String PUBLIC_KEY_PATH="/Users/bingwu/downloads/reciept.pubkey";                                              
	private static String PRIVATE_KEY_PATH="/Users/bingwu/downloads/reciept.prikey";                                             
	                                                                                                                             
	public static void geration(){                                                                                               
		KeyPairGenerator keyPairGenerator;                                                                                       
		try {                                                                                                                    
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");                                                              
			SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes());                                      
			keyPairGenerator.initialize(1024, secureRandom);                                                                     
			KeyPair keyPair = keyPairGenerator.genKeyPair();                                                                     
			byte[] publicKeyBytes = keyPair.getPublic().getEncoded();                                                            
			FileOutputStream fos = new FileOutputStream(PUBLIC_KEY_PATH);                                                        
			fos.write(publicKeyBytes);                                                                                           
			fos.close();                                                                                                         
			byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();                                                          
			fos = new FileOutputStream(PRIVATE_KEY_PATH);                                                                        
			fos.write(privateKeyBytes);                                                                                          
			fos.close();                                                                                                         
		} catch (Exception e) {                                                                                                  
			// TODO Auto-generated catch block                                                                                   
			e.printStackTrace();                                                                                                 
		}                                                                                                                        
	}                                                                                                                            
	                                                                                                                             
	/**                                                                                                                          
	 * 获取公钥                                                                                                                      
	 * @param filename                                                                                                           
	 * @return                                                                                                                   
	 * @throws Exception                                                                                                         
	 */                                                                                                                          
	public static PublicKey getPublicKey(String filename) throws Exception {                                                     
		File f = new File(filename);                                                                                             
		FileInputStream fis = new FileInputStream(f);                                                                            
		DataInputStream dis = new DataInputStream(fis);                                                                          
		byte[] keyBytes = new byte[(int)f.length()];                                                                             
		dis.readFully(keyBytes);                                                                                                 
		dis.close();                                                                                                             
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);                                                              
		KeyFactory kf = KeyFactory.getInstance("RSA");                                                                           
		return kf.generatePublic(spec);                                                                                          
	}                                                                                                                            
	                                                                                                                             
	                                                                                                                             
	/**                                                                                                                          
	 * 获取私钥                                                                                                                      
	 * @param filename                                                                                                           
	 * @return                                                                                                                   
	 * @throws Exception                                                                                                         
	 */                                                                                                                          
	public static PrivateKey getPrivateKey(String filename)throws Exception {                                                    
		File f = new File(filename);                                                                                             
		FileInputStream fis = new FileInputStream(f);                                                                            
		DataInputStream dis = new DataInputStream(fis);                                                                          
		byte[] keyBytes = new byte[(int)f.length()];                                                                             
		dis.readFully(keyBytes);                                                                                                 
		dis.close();                                                                                                             
		PKCS8EncodedKeySpec spec =new PKCS8EncodedKeySpec(keyBytes);                                                             
		KeyFactory kf = KeyFactory.getInstance("RSA");                                                                           
		return kf.generatePrivate(spec);                                                                                         
	}                                                                                                                            
	      Cipher cipher;                                                                                                         
	/**                                                                                                                          
	 * 私钥加密                                                                                                                      
	 * @param                                                                                                                    
	 * @return                                                                                                                   
	 * @throws Exception                                                                                                         
	 */                                                                                                                          
	public static byte[] encypt(String message,String public_key_path)throws Exception {                                        
		byte[] cipherText;                                                                                                       
		Cipher cipher = Cipher.getInstance("RSA");                                                                               
		RSAPublicKey pubKey = (RSAPublicKey) getPublicKey(public_key_path);                                                      
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);                                                                                
		cipherText = cipher.doFinal(message.toString().getBytes());                                                              
		//加密后的东西                                                                                                                 
		//System.out.println("cipher: " + new String(cipherText));                                                               
		return cipherText;                                                                                                       
	}                                                                                                                            
	                                                                                                                             
	/**                                                                                                                          
	 * 公钥解密                                                                                                                      
	 * @param cipherText                                                                                                         
	 * @return                                                                                                                   
	 * @throws Exception                                                                                                         
	 */                                                                                                                          
	public static boolean decrypt(byte[] cipherText,String private_key_path)throws Exception {                                   
		try {                                                                                                                    
			Cipher cipher = Cipher.getInstance("RSA");                                                                           
			RSAPrivateKey priKey = (RSAPrivateKey) getPrivateKey(private_key_path);                                              
			cipher.init(Cipher.DECRYPT_MODE, priKey);                                                                            
			byte[] plainText = cipher.doFinal(cipherText);                                                                       
			//System.out.println("publickey: " + Base64.getEncoder().encode(cipherText));                                        
			//System.out.println("plain : " + new String(plainText));                                                            
			return true;                                                                                                         
		}catch(Exception e){                                                                                                     
			return false;                                                                                                        
		}                                                                                                                        
	}                                 
	
	
}