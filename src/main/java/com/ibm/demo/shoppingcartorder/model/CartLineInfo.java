package com.ibm.demo.shoppingcartorder.model;

import javax.persistence.Embeddable;

@Embeddable
public class CartLineInfo {
	
	private Long productID;
	private int quanity;
	
	
	
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	
	
	
	
}
