package com.ibm.demo.shoppingcartorder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	String userID;
	String userName;
	String deliveryAddress;
	String orderDescription;
	Double totalPrice;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
