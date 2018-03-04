package com.cssrc.common.blockchain;

public class ExecuteCommands {
	private Peer2Peer networking;


	public ExecuteCommands(){}

	public ExecuteCommands(int port,String type,String type2){
		networking = new Peer2Peer(port,type,type2);
	}

	/**
	 * Used for client mode
	 */
	public void connect(String host, int port){
		networking.connect(host, port);
	}

	public String getBlockChain(){
		networking.send(Commands.GET_BLOCKCHAIN.getCommand());
		return networking.receive();
	}

	public int getBlockChainSize(){
		networking.send(Commands.GET_BLOCK_CHAIN_SIZE.getCommand());
		return Integer.parseInt(networking.receive());
	}

	public String postLastMinedBlock(){
		networking.send(Commands.POST_LAST_MINED_BLOCK.getCommand());
		return networking.receive();
	}

	public String ping(Peer p){
		connect(p.getIp(), p.getServerPort());
		networking.send(Commands.PING.getCommand());
		return networking.receive();
	}

	public void pingAll(){
		for(Peer p : networking.getPeers()){
			System.out.println(ping(p));
		}
	}

	public String register(){
		networking.send(Commands.REGISTERING.getCommand());
		return networking.receive();
	}

	public String send(String str){
		networking.send(str);
		return networking.receive();
	}


	public String receive(){
		return networking.receive();
	}

}