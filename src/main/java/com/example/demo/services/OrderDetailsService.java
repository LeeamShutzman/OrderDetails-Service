package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.models.Order;
import com.example.demo.models.OrderDetailsPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.OrderDetails;
import com.example.demo.models.Product;
import com.example.demo.producers.KafkaProducer;
import com.example.demo.repositories.OrderDetailsRepository;

@Service
public class OrderDetailsService {
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private RestTemplate restTemplate;

	private final KafkaProducer kafkaProducer;

	/***************************************************************/
	//Constructors, Getters, and Setters

	public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, KafkaProducer kafkaProducer) {
		super();
		this.orderDetailsRepository = orderDetailsRepository;
		this.kafkaProducer = kafkaProducer;
	}

	public OrderDetailsRepository getOrderDetailsRepository() {
		return orderDetailsRepository;
	}

	public void setOrderDetailsRepository(OrderDetailsRepository orderDetailsRepository) {
		this.orderDetailsRepository = orderDetailsRepository;
	}

	public KafkaProducer getKafkaProducer() {
		return kafkaProducer;
	}

	/***************************************************************/
	//Repository Method Calls

	//Add an OrderDetails
	public OrderDetails addOrderDetails(OrderDetails orderDetails) {
		try {
			Product product = restTemplate.getForObject(
					"http://localhost:8070/products/getProductByID?productID=" + orderDetails.getProductID(), Product.class);
			String productName = product.getProductName();
			OrderDetails temp = orderDetailsRepository.save(orderDetails);
			this.kafkaProducer.sendMessage("Added " + orderDetails.getQuantity() + " " + productName
			+ "s to order " + orderDetails.getOrderID());
			return temp;
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			this.kafkaProducer.sendMessage("Order doesn't exist");
			return new OrderDetails();
		} catch (java.lang.NullPointerException e) {
			this.kafkaProducer.sendMessage("Product doesn't exist");
			return new OrderDetails();
		}

	}

	//View all OrderDetails
	public List<OrderDetails> findAll() {
		return orderDetailsRepository.findAll();
	}

	//View an OrderDetails object by composite key ID
	public Optional<OrderDetails> findByOrderDetailsID(OrderDetailsPK orderDetailsPK) {
		return orderDetailsRepository.findById(orderDetailsPK);
	}

	//View all OrderDetails in the same order
	public List<OrderDetails> findOrderDetailsByOrderID(long orderID) {
		return orderDetailsRepository.findByOrderID(orderID);
	}

	//View all OrderDetails for the same Product
	public List<OrderDetails> findOrderDetailsByProductID(long productID) {
		return orderDetailsRepository.findByProductID(productID);
	}

	//Delete an OrderDetails
	public void deleteOrderDetails(OrderDetailsPK orderDetailsPK) {
		orderDetailsRepository.deleteById(orderDetailsPK);
	}

	//Update an OrderDetails
	public OrderDetails updateOrderDetails(OrderDetailsPK orderDetailsID, OrderDetails orderDetails) {
		try {
			OrderDetails temp = orderDetailsRepository.findById(orderDetailsID).get();
			if(Objects.nonNull(orderDetails.getQuantity()) && orderDetails.getQuantity() != 0){
				temp.setQuantity(orderDetails.getQuantity());
			}
			return orderDetailsRepository.save(temp);
		}
		catch (NoSuchElementException e){
			System.out.println("No order detail for that order and product was found");
			return null;
		}
	}

}
