package com.barogo.api.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserRegisterResponseDto {

    @JsonProperty("login_id")
    private String loginId;

    @Builder
    public UserRegisterResponseDto(String loginId) {
        this.loginId = loginId;
    }
}
