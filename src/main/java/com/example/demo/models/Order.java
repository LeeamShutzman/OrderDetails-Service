package com.example.demo.models;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {

	@Id
	@JoinColumn(name="order_id", referencedColumnName="ORDERID")
	private int orderID;
	@Column(name="CUSTOMERID")
	private String customerID;
	@Column(name="ORDERDATE")
	private Date date;
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Order(int orderID, String customerID, Date date) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.date = date;
	}

	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customerID=" + customerID + ", date=" + date + "]";
	}

	
	
	

}
