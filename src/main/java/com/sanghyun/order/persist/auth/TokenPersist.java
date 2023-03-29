package com.sanghyun.order.persist.auth;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.sanghyun.order.entity.auth.Token;
import com.sanghyun.order.repository.auth.TokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenPersist {

    private final TokenRepository tokenRepository;

    public Token save(Token token) {
        return this.tokenRepository.save(token);
    }

    public Token createToken(String token, String refreshToken, String userId) {
        return Token.builder()
                .userId(userId)
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    public Optional<Token> findByToken(String token) {
        return this.tokenRepository.findByToken(token);
    }

    public void delete(Token token) {
        this.tokenRepository.delete(token);
    }
}
