package com.sanghyun.order.util;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.exception.CommonException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.ClassInfo;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final ConverterUtil converterUtil;
    private final DateUtil dateUtil;

    public Claims makeClaims(String subject, Map<String, Object> map) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.putAll(map);
        return claims;
    }

    public String encData(Object claim, String key, Date expiration) {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());
        JwtBuilder builder = Jwts.builder();

        builder.setHeaderParam("typ", "JWT");
        builder.setSubject("token");
        builder.setExpiration(expiration);
        builder.setIssuedAt(new Date());
        if(!ObjectUtils.isEmpty(claim)) {
            builder.claim("claim", claim);
        }
        builder.signWith(secretKey);
        return builder.compact();
    }

    public String decData(String secureData, String secretKey) {
        SecretKey encodeSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        Claims body;
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(encodeSecretKey)
                    .build()
                    .parseClaimsJws(secureData);
            body = claims.getBody();
        } catch (JwtException e) {
            log.error("Validate jwtData error::{}", e.getMessage());
            throw new CommonException(Errors.JWT_DECRYPT_ERROR);
        }
        return this.converterUtil.toJsonString(body);
    }


}
