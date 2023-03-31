package com.sanghyun.order.entity.user;

import java.io.Serializable;
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
public class OrderUser extends CommonEntity implements Serializable {

    @Column(unique = true, nullable = false)
    private String userId;
    @Column(unique = true, nullable = false)
    private String signId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

}