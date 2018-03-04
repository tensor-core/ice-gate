package com.cssrc.common.blockchain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Peer2Peer {

	private Socket socket;
	private ServerSocket server;
	private int port = 8888;
	private LinkedList<Peer> peers = new LinkedList<Peer>();
	private DataInputStream inputStreamClient;
	private DataOutputStream outputStreamClient;
	public Peer2Peer(int port,String type,String type2){
		this.port = port;
		if("server".equals(type)) {
			try {
				new Server();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("client".equals(type)) {
			try {
				new SocketClient(port,type2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void connect(String host, int port){
		try {
            socket =new Socket("127.0.0.1",2013);
            socket.setSoTimeout(60000);
            outputStreamClient = new DataOutputStream(socket.getOutputStream());
			inputStreamClient = new DataInputStream(socket.getInputStream());
        }catch (Exception e) {
            System.out.println("Exception:" + e);
        }
	}
	
	public String receive(){
		String data = null;
		try {
			data = inputStreamClient.readUTF();
			//inputStreamClient.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return data;
		}
	}
	
	public void send(String data){
		try {
			outputStreamClient.writeUTF(data);
			outputStreamClient.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Peer> getPeers() {
		return peers;
	}

	public void setPeers(LinkedList<Peer> peers) {
		this.peers = peers;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
