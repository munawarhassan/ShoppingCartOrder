package com.ibm.demo.shoppingcartorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ibm.demo.shoppingcartorder.model.Cart;
import com.ibm.demo.shoppingcartorder.model.ProductDefaultPrice;

@Repository
public interface ShoppingCartDefaultProductPriceRepository extends JpaRepository<ProductDefaultPrice, Integer> {
	
	ProductDefaultPrice findByProductId(Long productId);

}
