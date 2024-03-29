package com.ibm.demo.shoppingcartorder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cart")
public class Cart{
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	 private int id;	
	@Column(name = "customer_id", nullable = false, unique = true)
	 private String customerId;
	 
	@ElementCollection
	 private List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
