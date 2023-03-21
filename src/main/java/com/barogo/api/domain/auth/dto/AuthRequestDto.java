package com.barogo.api.domain.auth.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthRequestDto {

    @NotNull
    private String userId;
    @NotNull
    private String password;

}
