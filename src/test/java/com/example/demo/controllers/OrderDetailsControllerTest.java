package com.example.demo.controllers;

import com.example.demo.models.OrderDetails;
import com.example.demo.models.Product;
import com.example.demo.producers.KafkaProducer;
import com.example.demo.repositories.OrderDetailsRepository;
import com.example.demo.services.OrderDetailsService;
import com.google.gson.Gson;
import kafka.testkit.KafkaClusterTestKit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderDetailsControllerTest {
/*
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    //@MockBean

    @MockBean
    private OrderDetailsService orderDetailsService;
    @MockBean
    private OrderDetailsRepository orderDetailsRepository;
    private KafkaProducer kafkaProducer;

    @BeforeEach
    public void setUp(){
        this.orderDetailsService = new OrderDetailsService(this.orderDetailsRepository, this.kafkaProducer);
    }
*/
    RestTemplate restTemplate = new RestTemplate();
    @Test
    void getOrderDetailsService() {
    }

    @Test
    void setOrderDetailsService() {
    }

    @Test
    void addItem() {

    }

    @Test
    void getAllProductsOrdered() throws Exception {
        //OrderDetailsController orderDetailsController = new OrderDetailsController();
        List<OrderDetails> testList = new ArrayList<>();
        testList.add(new OrderDetails(1,10,5));
        testList.add(new OrderDetails(1,11,5));
        testList.add(new OrderDetails(2,10,5));
        testList.add(new OrderDetails(2,11,5));
        //when(orderDetailsService.findAll()).thenReturn(testList);
        //RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8091/orderDetails/all");
        //MvcResult result = mvc.perform(request).andReturn();
        Gson gson = new Gson();
        List<OrderDetails> actualList = Arrays.asList(restTemplate.getForObject("http://localhost:8091/orderDetails/all", OrderDetails[].class));


        assertEquals(testList.toString(), actualList.toString());
        //assertEquals(testList, result.getResponse().getContentAsString());
    }

    @Test
    void getProductsByOrderID() {

    }
}