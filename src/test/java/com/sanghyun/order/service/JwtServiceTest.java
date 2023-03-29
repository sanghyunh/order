package com.sanghyun.order.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanghyun.order.CommonTest;
import com.sanghyun.order.dto.auth.AuthDto.TokenClaimDto;
import com.sanghyun.order.util.ConverterUtil;
import com.sanghyun.order.util.DateUtil;
import com.sanghyun.order.util.JwtUtil;
import org.junit.jupiter.api.Test;

public class JwtServiceTest extends CommonTest {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ConverterUtil converterUtil;
    @Autowired
    private DateUtil dateUtil;

    @Test
    public void jwtTest() {
        String secretKey = UUID.randomUUID().toString().toUpperCase();
        TokenClaimDto userResponseDto = TokenClaimDto.builder()
                .signId("test")
                .name("testName")
                .build();

        // 1시간 만료 토큰 생성
        String encData =
                this.jwtUtil.encData(userResponseDto, secretKey, this.dateUtil.makeExpirationDate((long) (60 * 60)));
        System.out.println(encData);
        String decData = this.jwtUtil.decData(encData, secretKey);
        System.out.println(decData);

    }

}
