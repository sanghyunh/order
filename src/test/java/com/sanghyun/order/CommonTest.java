package com.sanghyun.order;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.sanghyun.order.dto.auth.AuthDto.TokenDto;
import com.sanghyun.order.dto.user.UserDto.UserBaseDto;
import com.sanghyun.order.util.ConverterUtil;
import com.sanghyun.order.util.DateUtil;
import com.sanghyun.order.util.JwtUtil;
import com.sanghyun.order.util.RsaUtil;

@SpringBootTest
@ActiveProfiles(profiles = "local")
@AutoConfigureMockMvc
public class CommonTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected RsaUtil rsaUtil;
    @Autowired
    protected ConverterUtil converterUtil;
    @Autowired
    protected DateUtil dateUtil;
    @Autowired
    protected JwtUtil jwtUtil;

    protected UserBaseDto getLoginDto() {
        UserBaseDto requestDto = new UserBaseDto();
        requestDto.setSignId("test22");
        requestDto.setPassword(this.rsaUtil.encrypt("Password1234@"));
        return requestDto;
    }

    protected TokenDto parseToken(ResultActions resultAction) throws UnsupportedEncodingException {
        MvcResult result = resultAction.andReturn();
        String content = result.getResponse().getContentAsString();
        return this.converterUtil.toObject(content, TokenDto.class);
    }

}
