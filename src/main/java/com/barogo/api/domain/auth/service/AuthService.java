package com.barogo.api.domain.auth.service;

import com.barogo.api.domain.auth.dto.AuthRequestDto;
import com.barogo.api.domain.auth.dto.TokenRequestDto;
import com.barogo.api.domain.auth.dto.TokenResponseDto;
import com.barogo.api.domain.auth.entity.AuthRefreshToken;
import com.barogo.api.domain.auth.repository.AuthRefreshTokenRepository;
import com.barogo.api.domain.user.dto.UserInfoResponseDto;
import com.barogo.api.domain.user.dto.UserLoginResponseDto;
import com.barogo.api.domain.user.entity.User;
import com.barogo.api.domain.user.service.UserService;
import com.barogo.api.global.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthRefreshTokenRepository authRefreshTokenRepository;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * JWT 토큰을 이용한 로그인 처리
     * @param request
     * @param requestDto
     * @return
     */
    public UserLoginResponseDto login(HttpServletRequest request, AuthRequestDto requestDto) {

        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();

        // 로그인 검증
        User user = userService.getUser(userId, password);
        user.setModDate(LocalDateTime.now());
        user.setModWorker(user.getUserId());

        TokenResponseDto tokenResponseDto = this.getAccessToken(userId);

        AuthRefreshToken authRefreshToken = authRefreshTokenRepository.findByUserId(userId);

        if (ObjectUtils.isEmpty(authRefreshToken)) {
            authRefreshToken = new AuthRefreshToken();
            authRefreshToken.setUserId(userId);
            authRefreshToken.setRefreshToken(tokenResponseDto.getRefreshToken());
            authRefreshToken.setRegWorker(userId);
        } else {
            authRefreshToken.setRefreshToken(tokenResponseDto.getRefreshToken());
            authRefreshToken.setModDate(LocalDateTime.now());
            authRefreshToken.setModWorker(userId);
        }

        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        BeanUtils.copyProperties(userLoginResponseDto, userInfoResponseDto);

        userLoginResponseDto.setAccessToken(tokenResponseDto.getAccessToken());
        userLoginResponseDto.setRefreshToken(tokenResponseDto.getRefreshToken());
        userLoginResponseDto.setUserInfo(userInfoResponseDto);

        return userLoginResponseDto;
    }

    public TokenResponseDto refresh(TokenRequestDto tokenRequestDto) {

        String userId = tokenRequestDto.getId();

        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            log.info("USER_INVALID_REFRESH_TOKEN");
        }

        // 2. Access Token 에서 manager ID 가져오기
        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        if (ObjectUtils.isEmpty(authentication)) {
            log.info("USER_INCONSISTENCY_ACCESS_TOKEN");
        }

        if (!authentication.getName().equals(userId)) {
            log.info("USER_REQUEST_INCONSISTENCY_REFRESH_TOKEN");
        }

        // 3. 저장소에서 user ID 를 기반으로 Refresh Token 값 가져옴
        AuthRefreshToken refreshToken = this.getAuthRefreshToken(authentication.getName());

        if (ObjectUtils.isEmpty(refreshToken)) {
            log.info("USER_ALREADY_LOGOUT");
        }

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getRefreshToken().equals(tokenRequestDto.getRefreshToken())) {
            log.info("USER_INCONSISTENCY_REFRESH_TOKEN");
        }

        // 5. 새로운 토큰 생성
        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateToken(authentication);

        // 6. 저장소 정보 업데이트
        refreshToken.setRefreshToken(tokenResponseDto.getRefreshToken());

        // 토큰 발급
        return tokenResponseDto;
    }

    @Override
    public UserDetails loadUserByUsername(String id) {
        return userService.getAuthByUserId(id);
    }

    private TokenResponseDto getAccessToken(String userId) {

        UserDetails userDetails = this.loadUserByUsername(userId);
        UserDetails principal
                = new org.springframework.security.core.userdetails.User(
                userDetails.getUsername(), "", userDetails.getAuthorities()
        );
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(principal, "", userDetails.getAuthorities());

        return jwtTokenProvider.generateToken(authenticationToken);

    }

    public AuthRefreshToken getAuthRefreshToken(String userId) {
        return authRefreshTokenRepository.findByUserId(userId);
    }
}
