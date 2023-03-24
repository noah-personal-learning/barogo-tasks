package com.barogo.api.domain.order.controller;

import com.barogo.api.domain.order.dto.request.OrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class OrderControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("주문 요청이 성공한다.")
    @WithMockUser
    public void successOfOrderRequest () throws Exception {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        Map<String, String> orderRequestData = new HashMap<>();
        orderRequestData.put("address", "인천 서구");
        orderRequestData.put("product_name", "짜장면");
        orderRequestData.put("product_count", "2");
        orderRequestData.put("product_unit_price", "6000");

        // when & then
        ResultActions result = this.mockMvc.perform(post("/order/request")
                        .headers(headers)
                        .content(objectMapper.writeValueAsString(orderRequestData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..amount").value(12000))
                .andDo(print());

    }

    @Test
    @DisplayName("주문 요청이 실패한다.")
    @WithMockUser
    public void failOfOrderRequest() throws Exception {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        Map<String, String> orderRequestData = new HashMap<>();
        orderRequestData.put("address", "인천 서구");
        orderRequestData.put("productName", "짜장면");
        orderRequestData.put("productCount", "2");
        orderRequestData.put("productUnitPrice", "6000");

        // when & then
        ResultActions result = this.mockMvc.perform(post("/order/request")
                        .headers(headers)
                        .content(objectMapper.writeValueAsString(orderRequestData)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$..code").value("잘못된 요청"))
                .andDo(print());

    }

}