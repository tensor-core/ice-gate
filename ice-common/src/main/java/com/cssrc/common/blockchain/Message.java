package com.cssrc.common.blockchain;


public class Message {
    private int prouductId;
    private String productName;
    private int num;
    private String data;
    private String from;
    private String to;
    
    public  Message() {
    	
    }

    public Message(int prouductId, String productName, int num, String data,String from,String to) {
        this.prouductId = prouductId;
        this.productName = productName;
        this.num = num;
        this.data = data;
        this.from = from;
        this.to = to;
    }

    public int getProuductId() {
        return prouductId;
    }

    public void setProuductId(int prouductId) {
        this.prouductId = prouductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Message{" +
                "prouductId=" + prouductId +
                ", productName='" + productName + '\'' +
                ", num=" + num +
                ", data='" + data + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}

