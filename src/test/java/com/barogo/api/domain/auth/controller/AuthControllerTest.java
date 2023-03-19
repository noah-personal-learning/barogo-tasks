package com.barogo.api.domain.auth.controller;

import com.barogo.api.domain.auth.dto.AuthRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WebAppConfiguration
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("가입되어 있지 않은 사용자 예외 처리")
    public void notFountUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        AuthRequestDto loginReqDto
                = new AuthRequestDto("NoahTest001", "NoahTest001");

        ResultActions result = this.mockMvc.perform(get("/auth/login")
                        .headers(headers)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginReqDto)))
                .andExpect(status().is4xxClientError())
                .andDo(print());

        System.out.println(result.andReturn().getResponse().getContentAsString());

    }
}