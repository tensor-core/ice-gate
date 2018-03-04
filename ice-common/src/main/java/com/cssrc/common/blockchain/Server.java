package com.cssrc.common.blockchain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import net.sf.json.JSONObject;

public class Server extends ServerSocket {
    private static final int SERVER_PORT =2013;
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



    public Server() throws IOException {
        super(SERVER_PORT);

        try {
            while (true) {
            		System.out.println("创建一个socket线程");
                Socket socket = accept();
                new CreateServerThread(socket);//当有请求时，启一个线程处理
                System.out.println("socket线程启动");
            }
        }catch (IOException e) {
        }finally {
            close();
        }
    }

    //线程类
    class CreateServerThread extends Thread {
        private Socket client;
        private DataInputStream inputStreamClient;
      	private DataOutputStream outputStreamClient;

        public CreateServerThread(Socket s)throws IOException {
            client = s;
             //socket输入流
            inputStreamClient = new DataInputStream(client.getInputStream());
            //socket输出流
            outputStreamClient = new DataOutputStream(client.getOutputStream());

            System.out.println("Client(" + getName() +") come in...");
            start();
        }

        public void run() {
            try {
                String line1 = inputStreamClient.readUTF();
                System.out.println("readline=="+line1);


                while (!line1.equals("close")) {

                	    		outputStreamClient.writeUTF("continue, Client(" + getName() +")!");
                        String line = inputStreamClient.readUTF();
                        try {
                        		byte cipherText[] = line.getBytes();
                        		boolean b = StringUtil.decrypt(cipherText, product_pri_key_path);
                        		System.out.println(b);
                        		b=true;
                        		if(b) {
									JSONObject obj = JSONObject.fromObject(line1);
                        			Message mess = (Message) JSONObject.toBean(obj,Message.class);
                        			if(blockchain.size()==0) {
                        				addBlock(new Block(mess, "0"));
                        			}
                        			else {
                        				addBlock(new Block(mess, blockchain.get(blockchain.size() - 1).hash));
                        			}
                        			System.out.println("\nBlockchain is Valid: " + isChainValid());
                        			String blockchainJson = StringUtil.getJson(blockchain);
                        			System.out.println("\nThe block chain: ");
                        			System.out.println(blockchainJson);
                        		}
                        			//

                        }catch(Exception e) {
	                        	line1 = line1.substring(7);
	                        	JSONObject obj = JSONObject.fromObject(line1);
	                			Message mess = new Message();
	                			mess = (Message) JSONObject.toBean(obj,Message.class);
	                			if(blockchain.size()==0) {
                    				addBlock(new Block(mess, "0"));
                    			}
                    			else {
                    				addBlock(new Block(mess, blockchain.get(blockchain.size() - 1).hash));
                    			}
	                			System.out.println("\nBlockchain is Valid: " + isChainValid());
	                			String blockchainJson = StringUtil.getJson(blockchain);
	                			System.out.println("\nThe block chain: ");
	                			System.out.println(blockchainJson);
                        }

                        System.out.println("Client(" + getName() +") say: " + line);
                    }



                outputStreamClient.writeUTF("bye, Client(" + getName() +")!");

                System.out.println("Client(" + getName() +") exit!");
                outputStreamClient.close();
                inputStreamClient.close();
                client.close();
            }catch (IOException e) {
            }
        }
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

    public static void main(String[] args)throws IOException {
        new Server();
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
}

