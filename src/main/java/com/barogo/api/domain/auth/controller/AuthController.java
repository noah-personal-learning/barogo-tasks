package com.barogo.api.domain.auth.controller;

import com.barogo.api.domain.auth.dto.AuthRequestDto;
import com.barogo.api.domain.auth.dto.TokenRequestDto;
import com.barogo.api.domain.auth.dto.TokenResponseDto;
import com.barogo.api.domain.auth.service.AuthService;
import com.barogo.api.domain.user.dto.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> login(HttpServletRequest request, @RequestBody AuthRequestDto requestDto) {
        UserLoginResponseDto loginResponseDto = authService.login(request, requestDto);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    @ResponseBody
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody TokenRequestDto requestDto) {
        TokenResponseDto tokenResponseDto = authService.refresh(requestDto);
        return new ResponseEntity<>(tokenResponseDto, HttpStatus.OK);
    }

}
