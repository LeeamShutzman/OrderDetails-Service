package com.example.demo.services;

import java.util.List;
import java.util.Optional;

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

	/*********************************************************/
	public OrderDetails addItem(OrderDetails orderDetails) {
		try {
			Product product = restTemplate.getForObject(
					"http://localhost:8070/products/product" + orderDetails.getProductID(), Product.class);
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

	public List<OrderDetails> findAll() {
		return orderDetailsRepository.findAll();
	}

	public List<OrderDetails> findByOrderID(long orderID) {
		return orderDetailsRepository.findByOrderID(orderID);
	}

	public void deleteById(OrderDetailsPK orderDetailsPK) {
		orderDetailsRepository.deleteById(orderDetailsPK);
	}
	
	public Optional<OrderDetails> findByID(OrderDetailsPK orderDetailsPK) {
		return orderDetailsRepository.findById(orderDetailsPK);
	}

	
}
