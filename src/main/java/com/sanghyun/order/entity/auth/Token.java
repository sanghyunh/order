package com.sanghyun.order.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sanghyun.order.entity.CommonEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Token extends CommonEntity {

    @Column(nullable = false)
    private String userId;
    @Column(unique = true, nullable = false)
    private String token;
    @Column(unique = true, nullable = false)
    private String refreshToken;

}
