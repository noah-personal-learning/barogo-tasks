package com.barogo.api.domain.delivery.controller;

import com.barogo.api.domain.delivery.entity.Delivery;
import com.barogo.api.domain.delivery.repository.DeliveryRepository;
import com.barogo.api.domain.order.entity.Order;
import com.barogo.api.domain.order.repository.OrderRepository;
import com.barogo.api.global.util.code.DeliveryStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WebAppConfiguration
public class DeliveryControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private OrderRepository orderRepository;
    @Autowired private DeliveryRepository deliveryRepository;


    @BeforeEach
    void beforeEach() {
        Order order = orderRepository.save(
                Order.builder()
                    .productName("탕수육 대")
                    .productCount(1)
                    .productUnitPrice(new BigDecimal("25000"))
                    .address("인천 서구")
                .build());

        deliveryRepository.save(
                Delivery.builder()
                        .order(order)
                        .address(order.getAddress())
                        .status(DeliveryStatus.BEFORE_DELIVERY)
                        .build());
    }

    @Test
    @DisplayName("3일전 배송 기록을 가져온다.")
    @WithMockUser
    void getDeliveryHistory() throws Exception {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        Map<String,String> requestData = new HashMap<>();
        requestData.put("order_id", "1");
        requestData.put("before_day", "3");

        // when & then00
        ResultActions result = this.mockMvc.perform(get("/delivery/history")
                        .headers(headers)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andExpect(status().isOk())
                .andDo(print());
    }

}