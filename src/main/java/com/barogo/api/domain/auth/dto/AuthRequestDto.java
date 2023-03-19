package com.barogo.api.domain.auth.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthRequestDto {

    @NotNull
    private String userId;
    @NotNull
    private String password;

}
