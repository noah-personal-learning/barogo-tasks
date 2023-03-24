package com.barogo.api.domain.auth.controller;

import com.barogo.api.domain.auth.dto.request.AuthRequestDto;
import com.barogo.api.domain.user.entity.User;
import com.barogo.api.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WebAppConfiguration
public class AuthControllerTest {

    /// Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    /// Method
    @BeforeEach
    void beforeEach() {
        userRepository.save(
                User.builder()
                    .userId("NoahTest001")
                    .password("qkrshdkQ!W@")
                    .username("박노아")
                    .address("인천 서구")
                .build());
    }

    @Test
    @DisplayName("로그인 성공")
    public void successLogin() throws Exception {

        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        AuthRequestDto loginReqDto
                = new AuthRequestDto("NoahTest001", "qkrshdkQ!W@");

        // when & then
        ResultActions result = this.mockMvc.perform(get("/auth/login")
                        .headers(headers)
                        .content(objectMapper.writeValueAsString(loginReqDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userId").value("NoahTest001"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 실패")
    public void failLogin() throws Exception {

        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        AuthRequestDto loginReqDto
                = new AuthRequestDto("NoahTest001", "qkrshdkQ!W2");

        // when & then
        ResultActions result = this.mockMvc.perform(get("/auth/login")
                        .headers(headers)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginReqDto)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$..code").value("존재하지 않는 사용자 정보"))
                .andExpect(jsonPath("$..message").value("다른 아이디와 비밀번호로 다시 시도해주세요."))
                .andDo(print());
    }

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }
}