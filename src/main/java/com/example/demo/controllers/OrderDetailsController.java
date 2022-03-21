package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.OrderDetailsPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.OrderDetails;
import com.example.demo.services.OrderDetailsService;

@RestController
@RequestMapping("orderDetails") //localhost:portNum/categories
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;

	/***************************************************************/
	//Constructors, Getters, and Setters

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

	/***************************************************************/
	//Service endpoint mapping

	//Add an OrderDetails
	@PostMapping("/add") //localhost:portNum/orderDetails/add
	public OrderDetails addOrderDetails(@RequestBody OrderDetails orderDetails) {
		return orderDetailsService.addOrderDetails(orderDetails);
	}

	//View all OrderDetails'
	@GetMapping("/all") //localhost:portNum/orderDetails/all
	public List<OrderDetails> getAllOrderDetails(){
		return orderDetailsService.findAll();
	}

	//View an OrderDetails object by composite key ID
	@GetMapping("/getOrderDetailsByID") //localhost:portNum/orderDetails/getOrderDetailsByID?orderID=#&productID=#
	public Optional<OrderDetails> getOrderDetailsById(@RequestParam(name = "orderID") long orderID, @RequestParam(name = "productID") long productID){
		OrderDetailsPK orderDetailsPK = new OrderDetailsPK(orderID, productID);
		return orderDetailsService.findByOrderDetailsID(orderDetailsPK);
	}

	//View all OrderDetails objects in the same Order
	@GetMapping("/getOrderDetailsByOrderID") //localhost:portNum/orderDetails/getOrderDetailsByOrderID?orderID=#
	public List<OrderDetails> getOrderDetailsByOrderID(@RequestParam long orderID) {
		return orderDetailsService.findOrderDetailsByOrderID(orderID);
	}

	//View all OrderDetails objects for the same Product
	@GetMapping("/getOrderDetailsByProductID")//localhost:portNum/orderDetails/getOrderDetailsByProductID?productID=#
	public List<OrderDetails> getOrderDetailsByProductID(@RequestParam long productID) {
		return orderDetailsService.findOrderDetailsByProductID(productID);
	}

	//Delete an OrderDetails
	@DeleteMapping("/delete") //localhost:portNum/orderDetails/delete?orderID=#&productID=#
	public void deleteOrderDetails(@RequestParam(name = "orderID") long orderID, @RequestParam(name = "productID") long productID){
		OrderDetailsPK orderDetailsPK = new OrderDetailsPK(orderID, productID);
		orderDetailsService.deleteOrderDetails(orderDetailsPK);
	}

	//Update an OrderDetails
	@PutMapping("/update") //localhost:portNum/orderDetails/update
	public OrderDetails updateOrderDetails(@RequestParam(name = "orderID") long orderID, @RequestParam(name = "productID") long productID, @RequestBody OrderDetails orderDetails) {
		OrderDetailsPK orderDetailsPK = new OrderDetailsPK(orderID, productID);
		return orderDetailsService.updateOrderDetails(orderDetailsPK, orderDetails);
	}
}
