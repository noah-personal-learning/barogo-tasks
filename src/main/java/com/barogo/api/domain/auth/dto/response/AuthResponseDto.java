package com.barogo.api.domain.auth.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponseDto {

    private String accessToken;
    private String refreshToken;

}
