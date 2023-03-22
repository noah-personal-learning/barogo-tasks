package com.barogo.api.domain.user.dto.response;

import com.barogo.api.domain.auth.dto.response.AuthResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginResponseDto extends AuthResponseDto {

    private UserInfoResponseDto UserInfo;

}
