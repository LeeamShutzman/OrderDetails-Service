package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="SUPPLIER")
public class Supplier {

	@Id
	@JoinColumn(name="supplier_id", referencedColumnName="SUPPLIERID")
	private long supplierID;
	@Column(name="COMPANYNAME")
	private String companyName;
	@Column(name="CONTACTNAME")
	private String contactName;

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(long supplierID, String companyName, String contactName) {
		super();
		this.supplierID = supplierID;
		this.companyName = companyName;
		this.contactName = contactName;
	}
	
	
	public long getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Override
	public String toString() {
		return "Supplier [supplierID=" + supplierID + ", companyName=" + companyName + ", contactName=" + contactName
				+ "]";
	}

}
