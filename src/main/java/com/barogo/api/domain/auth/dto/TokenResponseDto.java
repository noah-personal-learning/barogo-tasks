package com.barogo.api.domain.auth.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long accessTokenExpiresIn;

    @Builder
    public TokenResponseDto(String accessToken, String refreshToken, String grantType, Long accessTokenExpiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.grantType = grantType;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
