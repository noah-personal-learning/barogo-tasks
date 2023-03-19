package com.barogo.api.domain.auth.entity;

import com.barogo.api.global.util.DateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "autoRefreshToken")
public class AuthRefreshToken extends DateEntity {

    @Id
    private String userId;
    private String refreshToken;

}
