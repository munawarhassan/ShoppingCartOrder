package com.ibm.demo.shoppingcartorder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.shoppingcartorder.model.Cart;
import com.ibm.demo.shoppingcartorder.model.FinalOrder;
import com.ibm.demo.shoppingcartorder.service.ShoppingCartOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/finalizaorder")
public class ShoppingCartOrderController {
	
	private final ShoppingCartOrderService orderservice;
	
	@RequestMapping(path = "/default", method = RequestMethod.GET)
	public String getDefaultMessage() {		
		
		return	"Hello , I am ready for Productinventory";
	}
	
	@RequestMapping(path = "/confirmorder", method = RequestMethod.POST)
	public FinalOrder confirmorder(@RequestBody Cart cart){

		log.info("*Inside confirmorder**");
		return orderservice.confirmorder(cart);

	}
}
