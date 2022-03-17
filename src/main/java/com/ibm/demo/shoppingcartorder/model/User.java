package com.ibm.demo.shoppingcartorder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

   
    private String id;  
    private String username;
    private String password; 
    private String role;
    private String deliveryAddress;

  
}
