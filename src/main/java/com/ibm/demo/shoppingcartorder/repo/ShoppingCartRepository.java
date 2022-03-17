package com.ibm.demo.shoppingcartorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ibm.demo.shoppingcartorder.model.Cart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Cart, Integer> {
	
	Cart findByCustomerId(String currency);
	
	

}
