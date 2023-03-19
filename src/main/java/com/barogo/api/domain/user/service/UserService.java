package com.barogo.api.domain.user.service;

import com.barogo.api.domain.user.dto.UserRegisterRequestDto;
import com.barogo.api.domain.user.dto.UserRegisterResponseDto;
import com.barogo.api.domain.user.entity.User;
import com.barogo.api.domain.user.exception.AlreadyUserIdException;
import com.barogo.api.domain.user.exception.NotFoundUserException;
import com.barogo.api.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    UserValidationService validationService;

    public UserRegisterResponseDto join(UserRegisterRequestDto requestDto) {

        validationService.join(requestDto);

        User user = requestDto.toEntity(requestDto);

        User completedUser = userRepository.save(user);

        return UserRegisterResponseDto.builder()
                .loginId(completedUser.getUserId())
                .build();
    }

    public User getUser(String userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password).orElseThrow(NotFoundUserException::new);
    }

    public User getAuthByUserId(String userId) {
        return userRepository.findAuthByUserId(userId).orElseThrow(NotFoundUserException::new);
    }
}
