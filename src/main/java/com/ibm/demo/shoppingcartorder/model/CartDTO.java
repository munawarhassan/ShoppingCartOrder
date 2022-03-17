package com.ibm.demo.shoppingcartorder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor

public class CartDTO{
	
	
	 private String customerId;
	 
	 private List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<CartLineInfo> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLineInfo> cartLines) {
		this.cartLines = cartLines;
	}
	 
	 
	
}
