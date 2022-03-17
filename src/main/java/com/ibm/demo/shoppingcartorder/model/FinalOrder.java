package com.ibm.demo.shoppingcartorder.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
public class FinalOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id") 
    private long id;
    
    @Column(name = "userid")  
    private String userid;
    
    @Column(name = "date")  
    private String date;
    
    @Column(name = "description")  
    private String description;
    
    @Column(name = "total_price") 
    private Double totalprice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
    
    
}
