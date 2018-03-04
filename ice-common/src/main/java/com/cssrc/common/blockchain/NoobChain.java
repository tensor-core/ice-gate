package com.cssrc.common.blockchain;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.Cipher;

public class NoobChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 3;
	public static String product_pub_key_path="/Users/bingwu/downloads/product.pubkey";                 
	public static String product_pri_key_path="/Users/bingwu/downloads/product.prikey";                 
	public static String warehouse_pub_key_path="/Users/bingwu/downloads/warehouse.pubkey";             
	public static String warehouse_pri_key_path="/Users/bingwu/downloads/warehouse.prikey";             
	public static String logistics_pub_key_path="/Users/bingwu/downloads/logistics.pubkey";             
	public static String logistics_pri_key_path="/Users/bingwu/downloads/logistics.prikey";             
	public static String reciept_pub_key_path="/Users/bingwu/downloads/reciept.pubkey";                 
	public static String reciept_pri_key_path="/Users/bingwu/downloads/reciept.prikey";                 

	public static void main(String[] args) {	
		byte[] cipherText = null;                                                                                                       
		boolean validate = false;                                                                                                       
	                                                                                                                                 
	                                                                                                                                 
//		try {                                                                                                                           
//			System.out.println("Trying to Mine block 1... ");                                                                           
//			Message message1 = new Message(1,"a",10,"生产消息","product","warehouse");                                                      
//			cipherText = StringUtil.encypt(message1.toString(), product_pub_key_path);                                                                        
//			addBlock(new Block(message1, "0"));                                                                                         
//		}catch (Exception e){                                                                                                           
//	                                                                                                                                 
//		}                                                                                                                               
	                                                                                                                                 
//		validate = false;                                                                                                               
//		try {                                                                                                                           
//			validate = StringUtil.decrypt(cipherText, product_pri_key_path);                                                                       
//			if(validate) {                                                                                                              
//				Message message2 = new Message(2, "a", 10, "仓储消息","warehouse","logistics");                                             
//				cipherText = StringUtil.encypt(message2, warehouse_pub_key_path);                                                                  
//				System.out.println("Trying to Mine block 2... ");                                                                       
//				addBlock(new Block(message2, blockchain.get(blockchain.size() - 1).hash));                                              
//			}                                                                                                                           
//		}catch (Exception e){                                                                                                           
//		}                                                                                                                               
//	                                                                                                                                 
//		validate =false;                                                                                                                
//		try {                                                                                                                           
//			//cipherText = null;                                                                                                        
//			validate = StringUtil.decrypt(cipherText, warehouse_pri_key_path);                                                                     
//			if(validate) {                                                                                                              
//				Message message3 = new Message(1, "a", 1, "物流消息","logistics","reciept");                                                
//				cipherText = StringUtil.encypt(message3, logistics_pub_key_path);                                                                  
//				System.out.println("Trying to Mine block 3... ");                                                                       
//				addBlock(new Block(message3, blockchain.get(blockchain.size() - 1).hash));                                              
//			}                                                                                                                           
//		}catch (Exception e){                                                                                                           
//	                                                                                                                                 
//		}                                                                                                                               
//	                                                                                                                                 
//		validate = false;                                                                                                               
//		try {                                                                                                                           
//			validate = StringUtil.decrypt(cipherText, logistics_pri_key_path);                                                                     
//			if(validate) {                                                                                                              
//				Message message4 = new Message(1, "a", 1, "收货消息","reciept","next");                                                     
//				cipherText = StringUtil.encypt(message4, reciept_pub_key_path);                                                                    
//				System.out.println("Trying to Mine block 4... ");                                                                       
//				addBlock(new Block(message4, blockchain.get(blockchain.size() - 1).hash));                                              
//			}                                                                                                                           
//		}catch(Exception e){                                                                                                            
//	                                                                                                                                 
//		}                                                                                                                               
//	                                                                                                                                 
		System.out.println("\nBlockchain is Valid: " + isChainValid());                                                                 
		                                                                                                                                
		String blockchainJson = StringUtil.getJson(blockchain);                                                                         
		System.out.println("\nThe block chain: ");                                                                                      
		System.out.println(blockchainJson);                                                                                             
	}
	
	                                                                                           
	                                                                                                                             
	public static Boolean isChainValid() {                                                                                       
		Block currentBlock;                                                                                                      
		Block previousBlock;                                                                                                     
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');                                                 
		                                                                                                                         
		//loop through blockchain to check hashes:                                                                               
		for(int i=1; i < blockchain.size(); i++) {                                                                               
			currentBlock = blockchain.get(i);                                                                                    
			previousBlock = blockchain.get(i-1);                                                                                 
			//compare registered hash and calculated hash:                                                                       
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){                                                        
				System.out.println("Current Hashes not equal");			                                                         
				return false;                                                                                                    
			}                                                                                                                    
			//compare previous hash and registered previous hash                                                                 
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {                                                         
				System.out.println("Previous Hashes not equal");                                                                 
				return false;                                                                                                    
			}                                                                                                                    
			//check if hash is solved                                                                                            
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {                                                
				System.out.println("This block hasn't been mined");                                                              
				return false;                                                                                                    
			}                                                                                                                    
			                                                                                                                     
		}                                                                                                                        
		return true;                                                                                                             
	}                                                                                                                            
	                                                                                                                             
	public static boolean addBlock(Block newBlock) {                                                                             
		newBlock.mineBlock(difficulty);                                                                                          
		//加入区块链之前先要做判断，是否针对同一个productId                                                                                          
		if(blockchain.size()==0) {                                                                                               
			blockchain.add(newBlock);                                                                                            
			return true;                                                                                                         
		}                                                                                                                        
		else{                                                                                                                    
			Block lastBlock = getLastBlock();                                                                                    
			if (newBlock.getMessage().getProuductId() == lastBlock.getMessage().getProuductId()                                  
					&&newBlock.getMessage().getFrom().equals(lastBlock.getMessage().getTo())){                                   
				blockchain.add(newBlock);                                                                                        
				return true;                                                                                                     
			}                                                                                                                    
			else{                                                                                                                
				System.out.println("addBlock false");                                                                            
				return false;                                                                                                    
			}                                                                                                                    
		}                                                                                                                        
	}                                                                                                                            
	                                                                                                                             
	public static Block getLastBlock(){                                                                                          
		if(blockchain.size() == 0 || blockchain == null) return null;                                                            
		return blockchain.get(blockchain.size() -1);                                                                             
	}                                                                                                                            
	                                                                                                                                                                                                                                                          
}
