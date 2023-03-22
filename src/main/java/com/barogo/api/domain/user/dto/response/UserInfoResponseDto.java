package com.barogo.api.domain.user.dto.response;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserInfoResponseDto {

    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "reg_ip")
    private String regIp;

}
