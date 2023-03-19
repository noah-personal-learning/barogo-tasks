package com.barogo.api.domain.user.repository;

import com.barogo.api.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndPassword(String userId, String password);
    Optional<User> findByUserId(String userId);

    Optional<User> findAuthByUserId(String userId);
}
