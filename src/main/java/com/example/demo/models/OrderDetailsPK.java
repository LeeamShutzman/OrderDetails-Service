package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailsPK implements Serializable{

	private long orderID;
	private long productID;

	public OrderDetailsPK() {
		super();
	}

	public OrderDetailsPK(long orderID, long productID) {
		super();
		this.orderID = orderID;
		this.productID = productID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderDetailsPK that = (OrderDetailsPK) o;
		return orderID == that.orderID && productID == that.productID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderID, productID);
	}
}