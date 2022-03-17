package com.ibm.demo.shoppingcartorder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.shoppingcartorder.model.Cart;
import com.ibm.demo.shoppingcartorder.model.CartLineInfo;
import com.ibm.demo.shoppingcartorder.model.FinalOrder;
import com.ibm.demo.shoppingcartorder.model.OrderDTO;
import com.ibm.demo.shoppingcartorder.service.ShoppingCartOrderService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class ShoppingCartOrderController {
	
	@Autowired
	private ShoppingCartOrderService orderservice;
	
	
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(ShoppingCartOrderController.class);
	
	@RequestMapping(path = "/default", method = RequestMethod.GET)
	public String getDefaultMessage() {		
		
		return	"Hello , I am ready for Productinventory";
	}
	
	@RequestMapping(path = "/confirmorder", method = RequestMethod.POST)
	public FinalOrder confirmorder(@RequestBody String customerId){

		log.info("*Inside confirmorder**");
		return orderservice.confirmorder(customerId);

	}
	
	@RequestMapping(value ="/addtocart", method=RequestMethod.POST)
	public ResponseEntity<?> addtocart(@RequestBody List<CartLineInfo> cartLines , String customerId) {
		
		
		return orderservice.addtocart(cartLines,customerId);
	}

	
	@RequestMapping(value ="/updatetocart", method=RequestMethod.PUT)
	public ResponseEntity<?> updatetocart(@RequestBody List<CartLineInfo> cartLines , String customerId) {
		
		return orderservice.updatetocart(cartLines,customerId);
	}
		
}
