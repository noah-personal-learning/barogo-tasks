package com.barogo.api.domain.user.dto;

import com.barogo.api.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Getter
@ToString
@NoArgsConstructor
public class UserRegisterRequestDto {

    @NotNull
    @JsonProperty("user_id")
    String userId;
    @NotNull
    @JsonProperty("password")
    String password;
    @NotNull
    @JsonProperty("name")
    String name;
    @JsonProperty("address")
    String address;

    String regIp;

    @Builder
    public UserRegisterRequestDto(String loginId, String password, String name, String address) {
        this.userId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.regIp = getRemoteIp();
    }

    // todo. User toEntity
    public User toEntity(UserRegisterRequestDto requestDto) {
        return User.builder()
                .userId(requestDto.getUserId())
                .address(requestDto.getAddress())
                .name(requestDto.getName())
                .password(requestDto.getPassword())
                .regIp(requestDto.getRegIp())
                .build();
    }

    public String getRemoteIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
