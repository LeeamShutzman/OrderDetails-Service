package com.example.demo.models;

import java.io.Serializable;

public class OrderDetailsPK implements Serializable{
	private long orderID;
	private long productID;
	public OrderDetailsPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetailsPK(long orderID, long productID) {
		super();
		this.orderID = orderID;
		this.productID = productID;
	}
	
}