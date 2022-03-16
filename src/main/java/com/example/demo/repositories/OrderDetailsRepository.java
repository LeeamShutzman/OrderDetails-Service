package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.OrderDetails;
import com.example.demo.models.OrderDetailsPK;
import com.example.demo.models.Product;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsPK> {

	List<OrderDetails> findByProductID(long productID);

	List<OrderDetails> findByOrderID(long orderID);
}
