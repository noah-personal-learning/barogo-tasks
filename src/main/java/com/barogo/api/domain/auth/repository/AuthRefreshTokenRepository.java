package com.barogo.api.domain.auth.repository;

import com.barogo.api.domain.auth.entity.AuthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRefreshTokenRepository extends JpaRepository<AuthRefreshToken, String> {

    AuthRefreshToken findByUserId(String userId);
}
