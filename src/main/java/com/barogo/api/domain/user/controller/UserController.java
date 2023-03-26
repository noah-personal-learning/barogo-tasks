package com.barogo.api.domain.user.controller;

import com.barogo.api.domain.user.dto.request.UserRegisterRequestDto;
import com.barogo.api.domain.user.dto.response.UserRegisterResponseDto;
import com.barogo.api.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping ("/user/add")
    public ResponseEntity<Object> userJoin(@RequestBody @Valid UserRegisterRequestDto requestDto) {

        UserRegisterResponseDto responseDto = userService.join(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

}
