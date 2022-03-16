package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;

/*class orderDetailsPK{
	private long orderID;
	private long productID;
	public orderDetailsPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public orderDetailsPK(long orderID, long productID) {
		super();
		this.orderID = orderID;
		this.productID = productID;
	}
	
}*/
@Entity
@IdClass(OrderDetailsPK.class)
@Table(name="ORDERDETAILS")
public class OrderDetails{

	@Id
	//@JoinColumn(name="order_id", referencedColumnName="ORDERID")
	private long orderID;
	
	@Id
	//@JoinColumn(name="product_id", referencedColumnName="PRODUCTID")
	private long productID;
	
	/*
	@JoinColumns({
		@JoinColumn(name="order_id", referencedColumnName="ORDERID"),
		@JoinColumn(name="product_id", referencedColumnName="PRODUCTID")
	})
	*/
	
	@Column(name="QUANTITY")
	private int quantity;

	
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderDetails(long orderID, long productID, int quantity) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
	}

	
	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + "]";
	}

	
	
	

}
