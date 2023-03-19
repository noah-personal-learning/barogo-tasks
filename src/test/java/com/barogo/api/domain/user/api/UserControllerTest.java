package com.barogo.api.domain.user.api;

import com.barogo.api.domain.user.dto.UserRegisterRequestDto;
import com.barogo.api.domain.user.dto.UserRegisterResponseDto;
import com.barogo.api.domain.user.exception.AlreadyUserIdException;
import com.barogo.api.domain.user.repository.UserRepository;
import com.barogo.api.domain.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입을 성공한다.")
    public void successRegister() throws Exception {
        // given
        UserRegisterRequestDto requestDto = UserRegisterRequestDto.builder()
                .loginId("NoahTest001")
                .password("NoahTest001")
                .name("박노아")
                .address("인천 서구")
                .build();
        // when
        UserRegisterResponseDto result = userService.join(requestDto);

        // then
        Assertions.assertThat(result.getLoginId()).isEqualTo(requestDto.getUserId());
    }

    @Test
    @DisplayName("회원가입을 실패한다.")
    public void failRegister() {

        // given
        UserRegisterRequestDto requestDto = UserRegisterRequestDto.builder()
                .loginId("NoahTest001")
                .password("NoahTest001")
                .name("박노아")
                .address("인천 서구")
                .build();

        // when & then
        Assert.assertThrows(AlreadyUserIdException.class, () -> userService.join(requestDto));

    }


}