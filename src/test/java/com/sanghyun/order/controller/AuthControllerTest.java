package com.sanghyun.order.controller;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.CommonTest;
import com.sanghyun.order.dto.auth.AuthDto.TokenDto;
import com.sanghyun.order.dto.auth.AuthDto.TokenPayloadDto;
import com.sanghyun.order.dto.auth.AuthDto.TokenRequestDto;
import com.sanghyun.order.dto.user.UserDto.UserBaseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest extends CommonTest {

    @Test
    @Transactional
    public void loginTest() throws Exception {

        UserBaseDto failRequestDto = new UserBaseDto();
        failRequestDto.setSignId("test");
        failRequestDto.setPassword(this.rsaUtil.encrypt("Password1234@"));
        String requestJson = this.converterUtil.toJsonString(failRequestDto);

        this.mockMvc.perform(post("/auth/v1/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is5xxServerError());

        this.login();
    }

    @Test
    @Transactional
    public void refreshTokenTest() throws Exception {
        String userId = "2B10485F-74C1-4624-81B9-AFA5816F0618";
        TokenDto tokenDto = this.login();
        String payload = this.jwtUtil.decData(tokenDto.getToken(), userId);
        TokenPayloadDto tokenPayloadDto = this.converterUtil.toObject(payload, TokenPayloadDto.class);
        System.out.println(tokenPayloadDto.getSub());
        System.out.println(tokenPayloadDto.getExp());
        System.out.println(tokenPayloadDto.getIat());
        System.out.println(tokenPayloadDto.getClaim());
        Assertions.assertTrue(this.dateUtil.expirationCheck(tokenPayloadDto.getExp()));
        Assertions.assertTrue(this.dateUtil.expirationCheck(tokenPayloadDto.getIat()));

        TokenRequestDto tokenRequestDto = new TokenRequestDto();
        tokenRequestDto.setToken(tokenDto.getToken());
        tokenRequestDto.setRefreshToken(tokenDto.getRefreshToken());
        String requestJson = this.converterUtil.toJsonString(tokenRequestDto);

        this.mockMvc.perform(put("/auth/v1/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

}
