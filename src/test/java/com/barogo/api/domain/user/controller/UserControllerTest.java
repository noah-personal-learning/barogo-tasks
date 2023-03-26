package com.barogo.api.domain.user.controller;

import com.barogo.api.domain.user.entity.User;
import com.barogo.api.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserControllerTest {

    /// Fields
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserRepository userRepository;


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

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입을 성공한다.")
    @WithMockUser
    public void successRegister() throws Exception {

        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, String> requestDto = new HashMap<>();
        requestDto.put("user_id", "NoahTest003");
        requestDto.put("password", "qkrshdkQ!W@E#");
        requestDto.put("name", "박노아");
        requestDto.put("address", "인천 서구");

        // when & then
        ResultActions result = this.mockMvc.perform(post("/user/add")
                        .headers(headers)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                // .andExpect(jsonPath("$..userId").value("NoahTest001"))
                .andDo(print());
    }

    @Test
    @DisplayName("비밀번호 유효성 검증에 실패한다.")
    @WithMockUser
    public void invalidPassword() throws Exception {

        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, String> requestDto = new HashMap<>();
        requestDto.put("user_id", "NoahTest002");
        requestDto.put("password", "NoahTest001");
        requestDto.put("name", "박노아");
        requestDto.put("address", "인천 서구");

        // when & then
        ResultActions result = this.mockMvc.perform(post("/user/add")
                        .headers(headers)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$..code")
                        .value("잘못된 패스워드"))
                .andExpect(jsonPath("$..message")
                        .value("비밀번호는 영어 대문자, 소문자, 특수문자 중 3종류 이상으로 12자리 이상의 문자열로 생성해야합니다."))
                .andDo(print());

    }

    @Test
    @DisplayName("이미 가입되어 있는 아이디")
    @WithMockUser
    public void alreadyLoginId() throws Exception {

        // given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, String> requestDto = new HashMap<>();
        requestDto.put("user_id", "NoahTest001");
        requestDto.put("password", "qkrshdkQ!W@E#");
        requestDto.put("name", "박노아");
        requestDto.put("address", "인천 서구");

        // when & then
        ResultActions result = this.mockMvc.perform(post("/user/add")
                        .headers(headers)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$..code")
                        .value("이미 가입되어 있는 아이디"))
                .andExpect(jsonPath("$..message")
                        .value("다른 아이디로 다시 시도해주세요."))
                .andDo(print());

    }


}