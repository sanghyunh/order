package com.sanghyun.order.service.auth;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.dto.auth.AuthDto.TokenDto;
import com.sanghyun.order.dto.auth.AuthDto.TokenRequestDto;
import com.sanghyun.order.dto.user.UserDto.UserBaseDto;
import com.sanghyun.order.entity.auth.Token;
import com.sanghyun.order.entity.user.OrderUser;
import com.sanghyun.order.exception.CommonException;
import com.sanghyun.order.service.user.UserService;
import com.sanghyun.order.util.RsaUtil;
import com.sanghyun.order.util.TextUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final TextUtil textUtil;
    private final RsaUtil rsaUtil;
    private final TokenService tokenService;

    @Transactional
    public TokenDto login(UserBaseDto userBaseDto) {
        OrderUser orderUser = this.loginChecker(userBaseDto);

        Token token = this.tokenService.save(orderUser);

        return TokenDto.builder()
                .token(token.getToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    private OrderUser loginChecker(UserBaseDto userBaseDto) {
        Optional<OrderUser> optionalUser = this.userService.find(userBaseDto.getSignId());
        return optionalUser
                .filter(orderUser -> orderUser
                        .getPassword()
                        .equals(this.textUtil.createHash(orderUser.getUserId(), this.rsaUtil.decrypt(userBaseDto.getPassword()))))
                .orElseThrow(() -> new CommonException(Errors.UNKNOWN_USER));
    }

}
