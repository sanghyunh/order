package com.sanghyun.order.service.auth;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.dto.auth.AuthDto.TokenClaimDto;
import com.sanghyun.order.dto.auth.AuthDto.TokenDto;
import com.sanghyun.order.dto.auth.AuthDto.TokenPayloadDto;
import com.sanghyun.order.dto.auth.AuthDto.TokenRequestDto;
import com.sanghyun.order.entity.auth.Token;
import com.sanghyun.order.entity.user.OrderUser;
import com.sanghyun.order.exception.CommonException;
import com.sanghyun.order.persist.auth.TokenPersist;
import com.sanghyun.order.service.user.UserService;
import com.sanghyun.order.util.ConverterUtil;
import com.sanghyun.order.util.DateUtil;
import com.sanghyun.order.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenPersist tokenPersist;
    private final JwtUtil jwtUtil;
    private final DateUtil dateUtil;
    private final ConverterUtil converterUtil;
    private final UserService userService;

    @Transactional
    public Token save(OrderUser orderUser) {
        Token token = this.createToken(orderUser);
        return this.tokenPersist.save(token);
    }

    public Token createToken(OrderUser orderUser) {
        TokenClaimDto claim = TokenClaimDto.builder()
                .signId(orderUser.getSignId())
                .name(orderUser.getName())
                .build();

        long oneHour = 60 * 60;
        long thirtyDays = oneHour * 24 * 30;

        String token = this.jwtUtil.encData(
                claim
                , orderUser.getUserId()
                , this.dateUtil.makeExpirationDate(oneHour));

        String refreshToken = this.jwtUtil.encData(
                null
                , orderUser.getUserId()
                , this.dateUtil.makeExpirationDate(thirtyDays));

        return this.tokenPersist.createToken(token, refreshToken, orderUser.getUserId());
    }

    @Transactional
    public TokenDto refreshToken(TokenRequestDto tokenDto) {
        Optional<Token> token2 = this.tokenPersist.findByToken(tokenDto.getToken());
        Token token = this.tokenPersist.findByToken(tokenDto.getToken())
                .filter(token1 -> token1.getRefreshToken().equals(tokenDto.getRefreshToken()))
                .orElseThrow(() -> new CommonException(Errors.NOT_FOUND_TOKEN));
        String payload = this.jwtUtil.decData(tokenDto.getToken(), token.getUserId());
        TokenPayloadDto tokenPayloadDto = this.converterUtil.toObject(payload, TokenPayloadDto.class);
        if (!this.dateUtil.expirationCheck(tokenPayloadDto.getExp())) {
            throw new CommonException(Errors.NOT_FOUND_TOKEN);
        }
        Token newToken = this.createToken(this.userService.findByUserIdRequired(token.getUserId()));
        // TODO : 새로운 토큰 발급 후 기존 토큰 삭제 처리 hard delete 수정 필요
        this.deleteToken(token);
        return TokenDto.builder()
                .token(newToken.getToken())
                .refreshToken(newToken.getRefreshToken())
                .build();
    }

    public void deleteToken(Token token) {
        this.tokenPersist.delete(token);
    }
}
