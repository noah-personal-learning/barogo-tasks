package com.barogo.api.domain.auth.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String accessToken;
    private String refreshToken;

}
