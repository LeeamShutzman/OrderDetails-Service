package com.example.demo.controllers;

import java.util.List;
import java.util.Properties;

import com.example.demo.models.OrderDetailsPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.OrderDetails;
import com.example.demo.producers.KafkaProducer;
import com.example.demo.services.OrderDetailsService;

import javax.ws.rs.Path;

@RestController
@RequestMapping("orderDetails") //localhost:portNum/categories
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;

	public OrderDetailsController(OrderDetailsService orderDetailsService) {
		super();
		this.orderDetailsService = orderDetailsService;
		
	}

	public OrderDetailsService getOrderDetailsService() {
		return orderDetailsService;
	}

	public void setOrderDetailsService(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}
	


	@PostMapping("add")
	public OrderDetails addItem(@RequestBody OrderDetails orderDetails) {
		return orderDetailsService.addItem(orderDetails);
	}
	
	@GetMapping("all")
	public List<OrderDetails> getAllProductsOrdered(){
		return orderDetailsService.findAll();
	}
	
	@GetMapping()
	public List<OrderDetails> getProductsByOrderID(@RequestParam long orderID) {
		return orderDetailsService.findByOrderID(orderID);
	}

	@DeleteMapping("/deleteOrderDetails") //localhost:portNum/orders/deleteProduct
	public void deleteById(@RequestParam(name = "orderID") long orderID, @RequestParam(name = "productID") long productID){
		OrderDetailsPK orderDetailsPK = new OrderDetailsPK(orderID, productID);
		orderDetailsService.deleteById(orderDetailsPK);
	}
	/*
	@GetMapping("order{id}")
	public List<OrderDetails> getProductByID(@PathVariable("id") long orderID) {
		return orderDetailsService.findByProductID(orderID);
	}
	*/
}
