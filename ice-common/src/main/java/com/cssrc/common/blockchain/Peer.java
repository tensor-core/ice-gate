package com.cssrc.common.blockchain;

public class Peer {
	private String ip;
	private int serverPort;
	
	public Peer(String ip, int serverPort){
		this.ip = ip;
		this.serverPort = serverPort;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int port) {
		this.serverPort = port;
	}

	@Override
	public String toString() {
		return String.format("Peer [ip=%s, serverPort=%s]", ip, serverPort);
	}
}
