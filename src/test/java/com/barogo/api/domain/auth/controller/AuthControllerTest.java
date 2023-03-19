package com.barogo.api.domain.auth.controller;

import com.barogo.api.domain.auth.dto.AuthRequestDto;
import com.barogo.api.domain.user.dto.UserRegisterRequestDto;
import com.barogo.api.domain.user.dto.UserRegisterResponseDto;
import com.barogo.api.domain.user.entity.User;
import com.barogo.api.domain.user.repository.UserRepository;
import com.barogo.api.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository.save(
                User.builder()
                    .userId("NoahTest001")
                    .password("NoahTest001")
                    .username("박노아")
                    .address("인천 서구")
                .build());
    }

    @Test
    @DisplayName("로그인 성공")
    public void successLogin() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        AuthRequestDto loginReqDto
                = new AuthRequestDto("NoahTest001", "NoahTest001");

        ResultActions result = this.mockMvc.perform(get("/auth/login")
                        .headers(headers)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginReqDto)))
                .andExpect(status().isOk())
                .andDo(print());

        System.out.println(result.andReturn().getResponse().getContentAsString());

    }
}