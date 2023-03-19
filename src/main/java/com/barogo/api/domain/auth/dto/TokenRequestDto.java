package com.barogo.api.domain.auth.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class TokenRequestDto {

    @NotNull
    private String id;

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

}
