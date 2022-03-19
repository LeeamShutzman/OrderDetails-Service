package com.example.demo.controllers;

import com.example.demo.models.OrderDetails;
import com.example.demo.repositories.OrderDetailsRepository;
import com.example.demo.services.OrderDetailsService;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class OrderDetailsControllerAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @MockBean
    OrderDetailsRepository orderDetailsRepository;


    @Test
    void getOrderDetailsService() {
    }

    @Test
    void setOrderDetailsService() {
    }

    @Test
    void addOrderDetails() {
    }

    @Test
    void getAllOrderDetails() throws Exception {
        List<OrderDetails> testList = new ArrayList<>();
        testList.add(new OrderDetails(1,10,5));
        testList.add(new OrderDetails(1,11,5));
        testList.add(new OrderDetails(2,10,5));
        testList.add(new OrderDetails(2,11,5));
        String expected = "[{\"orderID\":1,\"productID\":10,\"quantity\":5},{\"orderID\":1,\"productID\":11,\"quantity\":5},{\"orderID\":2,\"productID\":10,\"quantity\":5},{\"orderID\":2,\"productID\":11,\"quantity\":5}]";

        when(orderDetailsRepository.findAll()).thenReturn(testList);
        RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8091/orderDetails/all");

        mockMvc.perform(request).andExpect(content().json(expected));
        //assertEquals(testList.toString(),mockMvc.perform(request).andReturn().toString());
    }

    @Test
    void getOrderDetailsById() {
    }

    @Test
    void getOrderDetailsByOrderID() {
    }

    @Test
    void getOrderDetailsByProductID() {
    }

    @Test
    void deleteOrderDetails() {
    }
}