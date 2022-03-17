package com.ibm.demo.shoppingcartorder.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;


@Component
public class CoreResponseModel{
	
 @Transient
	private int statusCode;
	
 @Transient
	private boolean success = true;


 @Transient
	private Object responseBody;
 
 @Transient
 private String message;
 


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}



	


	public Object getResponseBody() {
		return responseBody;
	}


	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

   
     
}
