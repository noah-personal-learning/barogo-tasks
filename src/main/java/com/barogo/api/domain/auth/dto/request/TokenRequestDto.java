package com.barogo.api.domain.auth.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenRequestDto {

    @NotNull
    private String id;

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

}
