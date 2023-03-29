package com.sanghyun.order.repository.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sanghyun.order.entity.auth.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);
}
