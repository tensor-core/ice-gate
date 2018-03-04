package com.cssrc.common.blockchain;

public enum Commands {
	GET_BLOCKCHAIN("getBlockChain"),
	POST_LAST_MINED_BLOCK("postLastMinedBlock"),
	GET_BLOCK_CHAIN_SIZE("getBlockChainSize"),
	UNKNOWN_COMMAND("command unknown"),
	REGISTERING("registering"),
	PING("ping"),
	PONG("pong"),
	CLOSE("close");
	
	private String command;
	
	private Commands(String commands){
		this.command = commands;
	}
	
	public String getCommand(){
		return this.command;
	}
	
	public String getCommand(String param){
		return String.format(this.command, param);
	}
}
