package com.sanghyun.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.dto.auth.AuthDto.TokenPayloadDto;
import com.sanghyun.order.entity.auth.Token;
import com.sanghyun.order.exception.AuthorizedException;
import com.sanghyun.order.service.auth.TokenService;
import com.sanghyun.order.util.ConverterUtil;
import com.sanghyun.order.util.DateUtil;
import com.sanghyun.order.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final TokenService tokenService;
    private final DateUtil dateUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.flushBuffer();
            return false;
        }

        this.validateToken(request);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void validateToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        TokenPayloadDto tokenPayload = this.jwtUtil.getPayload(accessToken);
        if (!this.dateUtil.expirationCheck(tokenPayload.getIat())) {
            throw new AuthorizedException(Errors.OAUTH_TOKEN_TIMEOUT);
        }
        Token token = this.tokenService.findByToken(accessToken)
                .orElseThrow(() -> new AuthorizedException(Errors.OAUTH_TOKEN_NOT_FOUND));
        this.jwtUtil.decData(token.getToken(), token.getUserId());
        request.setAttribute("userId", token.getUserId());
    }
}
