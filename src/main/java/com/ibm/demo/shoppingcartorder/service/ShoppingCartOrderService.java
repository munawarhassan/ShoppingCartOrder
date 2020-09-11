package com.ibm.demo.shoppingcartorder.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibm.demo.shoppingcartorder.model.Cart;
import com.ibm.demo.shoppingcartorder.model.FinalOrder;
import com.ibm.demo.shoppingcartorder.repo.ShoppingCartOrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Component
@RibbonClient(name = "currencyconversionfactorservice")
@RequiredArgsConstructor
@Log
@Service
public class ShoppingCartOrderService {
	
	final private ShoppingCartOrderRepository  orderrepo;
		
	public FinalOrder confirmorder(Cart cart) {
		log.info("in side repo of ShoppingCartOrderService");
		FinalOrder finalOrder = new FinalOrder();
		orderrepo.save(finalOrder);
		return null;
	}

}
