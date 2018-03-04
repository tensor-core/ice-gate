package com.cssrc.common.blockchain;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {
	private static int port;
	private static String type;
	public static String product_pub_key_path="/Users/bingwu/downloads/product.pubkey";                 
	public static String product_pri_key_path="/Users/bingwu/downloads/product.prikey";                 
	public static String warehouse_pub_key_path="/Users/bingwu/downloads/warehouse.pubkey";             
	public static String warehouse_pri_key_path="/Users/bingwu/downloads/warehouse.prikey";             
	public static String logistics_pub_key_path="/Users/bingwu/downloads/logistics.pubkey";             
	public static String logistics_pri_key_path="/Users/bingwu/downloads/logistics.prikey";             
	public static String reciept_pub_key_path="/Users/bingwu/downloads/reciept.pubkey";                 
	public static String reciept_pri_key_path="/Users/bingwu/downloads/reciept.prikey";  
	
	public SocketClient(int port,String type) throws IOException{
		try {
            Socket socket =new Socket("127.0.0.1",2013);
            socket.setSoTimeout(60000);
            //socket输入流
            DataInputStream input = new DataInputStream(socket.getInputStream());
            //socket输出流
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             
            String result ="";
            
            if(!result.equals(Commands.CLOSE.getCommand())){
            	
            		byte[] cipherText = null;
            		Message message = null;
            		if("product".equals(type)) {
            			try {                                                                                                                           
            				System.out.println("Trying to Mine block 1... ");                                                                           
            				message = new Message(1,"iphone",10,"生产消息","product","warehouse");
            				cipherText = StringUtil.encypt(message.toString(), product_pub_key_path);                                                                                                                                                               
            			}catch (Exception e){                                                                                                           
	        	                                                                                                                                 
            			} 
            		}
            		if("warehouse".equals(type)) {
            			try {                                                                                                                           
            				System.out.println("Trying to Mine block 2... ");                                                                           
            				message = new Message(1, "iphone", 10, "仓储消息","warehouse","logistics");
            				cipherText = StringUtil.encypt(message.toString(), product_pub_key_path);                                                                                                                                                               
            			}catch (Exception e){                                                                                                           
	        	                                                                                                                                 
            			} 
            		}
            		if("logistics".equals(type)) {
            			try {                                                                                                                           
            				System.out.println("Trying to Mine block 3... ");                                                                           
            				message = new Message(1, "iphone", 1, "物流消息","logistics","reciept");
            				cipherText = StringUtil.encypt(message.toString(), product_pub_key_path);                                                                                                                                                               
            			}catch (Exception e){                                                                                                           
	        	                                                                                                                                 
            			} 
            		}
            		if("reciept".equals(type)) {
            			try {                                                                                                                           
            				System.out.println("Trying to Mine block 4... ");                                                                           
            				message = new Message(1, "iphone", 1, "收货消息","reciept","next");
            				cipherText = StringUtil.encypt(message.toString(), product_pub_key_path);                                                                                                                                                               
            			}catch (Exception e){                                                                                                           
	        	                                                                                                                                 
            			} 
            		}
	            	//System.out.println(new String(cipherText));
                output.writeUTF(message.toString());
                output.flush();
                output.writeUTF(new String(cipherText));
                //output.write(cipherText);
                output.flush();
                result = input.readUTF();
                System.out.println("Server say : " + result);
            }
            	output.close();
            	input.close();
            	socket.close();
        }catch (Exception e) {
            System.out.println("Exception:" + e);
        }
	}
	public static void main(String[] args) throws IOException{
        new SocketClient(port,type);
    }
}
