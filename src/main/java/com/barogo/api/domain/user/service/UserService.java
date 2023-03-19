package com.barogo.api.domain.user.service;

import com.barogo.api.domain.user.dto.UserRequestDto;
import com.barogo.api.domain.user.dto.UserResponseDto;
import com.barogo.api.domain.user.entity.User;
import com.barogo.api.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    public UserResponseDto join(UserRequestDto requestDto) {

        User user = requestDto.toEntity();

        userRepository.save(user);

        return new UserResponseDto();
    }

}
