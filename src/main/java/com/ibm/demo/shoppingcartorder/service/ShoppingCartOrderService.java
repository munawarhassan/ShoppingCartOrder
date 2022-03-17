package com.ibm.demo.shoppingcartorder.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.demo.shoppingcartorder.model.Cart;
import com.ibm.demo.shoppingcartorder.model.CartDTO;
import com.ibm.demo.shoppingcartorder.model.CartLineInfo;
import com.ibm.demo.shoppingcartorder.model.CoreResponseModel;
import com.ibm.demo.shoppingcartorder.model.FinalOrder;
import com.ibm.demo.shoppingcartorder.model.OrderDTO;
import com.ibm.demo.shoppingcartorder.model.Product;
import com.ibm.demo.shoppingcartorder.model.ProductDefaultPrice;
import com.ibm.demo.shoppingcartorder.model.Product;
import com.ibm.demo.shoppingcartorder.repo.ShoppingCartDefaultProductPriceRepository;
import com.ibm.demo.shoppingcartorder.repo.ShoppingCartOrderRepository;
import com.ibm.demo.shoppingcartorder.repo.ShoppingCartRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.RequiredArgsConstructor;


@Component
@RibbonClient(name = "shoppingcartproductinventory")
@RequiredArgsConstructor
@Service
public class ShoppingCartOrderService {
	
	@Autowired
	private ShoppingCartOrderRepository  orderrepo;
	
	@Autowired
	private ShoppingCartRepository cartrepo;
	
	@Autowired
	private ShoppingCartDefaultProductPriceRepository defaultPriceRepo;
	
	@Autowired
	private CoreResponseModel respModel;	
	
	private ResponseEntity<?>  respEntity;
	
	@Bean
	@LoadBalanced
	RestTemplate createRestTemplate() {
		RestTemplateBuilder b = new RestTemplateBuilder();
		return b.build();
	}

	@Autowired
	@Lazy
	RestTemplate lbrestTemplate;
	
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(ShoppingCartOrderService.class);
		
	@HystrixCommand(fallbackMethod = "getDefaultPrice")
	public FinalOrder confirmorder(String customerId) {
		Cart myCart = cartrepo.findByCustomerId(customerId);
		FinalOrder finalOrder = new FinalOrder();
		finalOrder.setUserid(customerId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		finalOrder.setDate(formatter.format(new Date()));
		
		String baseUrl = "http://shoppingcartproductinventory/inventory/findproduct/{productid}";
		
		Product product = null;
		Double price = null;
		String description = "";
		for(CartLineInfo cartLineInfo : myCart.getCartLines()) {
			Map<String, Long> params = new HashMap<String, Long>();
		    params.put("productid", cartLineInfo.getProductID());	
		    product = lbrestTemplate.getForObject(baseUrl, Product.class, params);
		    price =  price + ( cartLineInfo.getQuanity() * product.getPrice());
		    description = description + product.getDescription();
			
		}		
		
		finalOrder.setTotalprice(price);
		finalOrder.setDescription(description);
		orderrepo.save(finalOrder);
		return finalOrder;
	}
	
	public FinalOrder getDefaultPrice(String customerId) {
		
		Cart myCart = cartrepo.findByCustomerId(customerId);
		FinalOrder finalOrder = new FinalOrder();
		finalOrder.setUserid(customerId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		finalOrder.setDate(formatter.format(new Date()));
		
		List<ProductDefaultPrice> productDefaultPriceList =  defaultPriceRepo.findAll();		
	
		Double price = null;
		String description = "";
		for(CartLineInfo cartLineInfo : myCart.getCartLines()) {
			ProductDefaultPrice productDefaultPrice = defaultPriceRepo.findByProductId(cartLineInfo.getProductID());
	       price =  price + ( cartLineInfo.getQuanity() * productDefaultPrice.getPrice());
		    description = description + productDefaultPrice.getDescription();
			
		}
		finalOrder.setTotalprice(price);
		finalOrder.setDescription(description);
		orderrepo.save(finalOrder);
		return finalOrder;
	}
	
	
	public ResponseEntity<?> addtocart(List<CartLineInfo> cartLines , String customerId) {
		try {
			Cart myCart = new Cart();
			myCart.setCustomerId(customerId);
			myCart.setCartLines(cartLines);
			cartrepo.save(myCart);
		} catch (Exception ex) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PromoEngineException(ex.getMessage()));
		}

		return ResponseEntity.ok("successfully added in DB");
	}
	
	
	public ResponseEntity<?> updatetocart(List<CartLineInfo> cartLines , String customerId) {
		try {
			Cart myCart = cartrepo.findByCustomerId(customerId);
			if(null == myCart) {
				Cart myCartL = new Cart();
				myCartL.setCustomerId(customerId);
				myCartL.setCartLines(cartLines);
				myCart=cartrepo.save(myCartL);
			}else {
				
				for(CartLineInfo cartLine : cartLines) {
					
					boolean existInCart = false;
					
					for(CartLineInfo myCartLine : myCart.getCartLines()) {
						if(myCartLine.getProductID().equals(cartLine.getProductID())) {
							myCartLine.setQuanity(cartLine.getQuanity()+myCartLine.getQuanity());
							existInCart = true;
							break;
						}
						
					}
					if(!existInCart) {
						myCart.getCartLines().add(cartLine);
					}
					
				}
				
				cartrepo.save(myCart);
				
			}
			log.info(" Record Updated Successfully");
			return populateSuccessResponseWithResult(myCart, "Successfully Update records to database");
		} catch (Exception ex) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PromoEngineException(ex.getMessage()));
		}

		return ResponseEntity.ok("successfully added in DB");
	}
	
	public ResponseEntity<?>   populateSuccessResponseWithResult(Object result, String message){		
		
		respModel = new CoreResponseModel();
		respModel.setStatusCode(200);
		respModel.setMessage(message);
		respModel.setResponseBody(result);
		respEntity = new ResponseEntity<Object>(respModel,HttpStatus.OK);
		return respEntity;
	}
	
	public ResponseEntity<?>  populateFailureResponse( String message){	
		respModel = new CoreResponseModel();
		respModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
		respModel.setMessage(message);
		respModel.setSuccess(false);		
		respEntity = new ResponseEntity<Object>(respModel,HttpStatus.BAD_REQUEST);		
		return respEntity;
	}

}
