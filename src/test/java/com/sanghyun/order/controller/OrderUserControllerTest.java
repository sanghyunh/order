package com.sanghyun.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.CommonTest;
import com.sanghyun.order.dto.user.UserDto.UserJoinRequestDto;
import com.sanghyun.order.util.ConverterUtil;
import com.sanghyun.order.util.RsaUtil;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderUserControllerTest extends CommonTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ConverterUtil converterUtil;
    @Autowired
    private RsaUtil rsaUtil;

    @Test
    // @Transactional
    public void joinTest() throws Exception {

        UserJoinRequestDto requestDto = new UserJoinRequestDto();
        requestDto.setSignId("test22");
        requestDto.setPassword(this.rsaUtil.encrypt("Password1234@"));
        requestDto.setName("name4321");
        String requestJson = this.converterUtil.toJsonString(requestDto);

        this.mockMvc.perform(post("/user/v1/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

}
