package com.barogo.api.domain.user.service;

import com.barogo.api.domain.user.dto.request.UserRegisterRequestDto;
import com.barogo.api.domain.user.exception.AlreadyUserIdException;
import com.barogo.api.domain.user.exception.InvalidPasswordException;
import com.barogo.api.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserValidationService {

    private final UserRepository userRepository;

    public void join(UserRegisterRequestDto requestDto) {
        // 이미 가입되어 있는 경우 예외 처리
        if (userRepository.findByUserId(requestDto.getUserId()).isPresent()) {
            throw new AlreadyUserIdException();
        }
        // 규칙을 따르지 않는 비밀번호 예외 처리
        if (!isValidPassword(requestDto.getPassword())) {
            throw new InvalidPasswordException();
        }
    }

    /**
     * 비밀번호 유효성 검증
     * 조건. 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로
     * 		 12자리 이상의 문자열로 생성해야 합니다.
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        // Password must be at least 12 characters long
        if (password.length() < 12) {
            return false;
        }

        // Password must contain at least three of the following: uppercase letters, lowercase letters, numbers, and special characters
        int numCharClasses = 0;
        if (password.matches(".*[A-Z].*")) {
            numCharClasses++;
        }
        if (password.matches(".*[a-z].*")) {
            numCharClasses++;
        }
        if (password.matches(".*\\d.*")) {
            numCharClasses++;
        }
        if (password.matches(".*[^A-Za-z0-9].*")) {
            numCharClasses++;
        }
        return numCharClasses >= 3;
    }
}
