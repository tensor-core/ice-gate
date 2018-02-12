package com.cssrc.common.blockchain;

import java.security.PublicKey;

public class TransactionOutput {

	public String id;
	public PublicKey reciepient; //also known as the new owner of these coins.
	public String companyId; //the amount of coins they own
	public float value; //the amount of coins they own
	public String parentTransactionId; //the id of the transaction this output was created in
	
	//Constructor
	public TransactionOutput(PublicKey reciepient,String companyId,float value, String parentTransactionId) {
		this.reciepient = reciepient;
		this.companyId = companyId;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient)+companyId+Float.toString(value)+parentTransactionId);
	}
	
	//Check if coin belongs to you
	public boolean isMine(PublicKey publicKey,String cId) {
		return (publicKey == reciepient)&&(companyId == cId);
	}
}
