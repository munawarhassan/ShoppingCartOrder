package com.ibm.demo.shoppingcartorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.demo.shoppingcartorder.model.FinalOrder;

@Repository
public interface ShoppingCartOrderRepository extends JpaRepository<FinalOrder, Integer> {

}
