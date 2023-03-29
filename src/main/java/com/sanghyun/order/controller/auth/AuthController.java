package com.sanghyun.order.controller.auth;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanghyun.order.dto.auth.AuthDto;
import com.sanghyun.order.dto.auth.AuthDto.TokenDto;
import com.sanghyun.order.dto.user.UserDto.UserBaseDto;
import com.sanghyun.order.exception.BindingException;
import com.sanghyun.order.service.auth.AuthService;
import com.sanghyun.order.service.auth.TokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/v1/token")
    public ResponseEntity<TokenDto> token(@Valid @RequestBody UserBaseDto userBaseDto
            , BindingResult result) {
        if (result.hasErrors()) {
            throw new BindingException(result.getFieldError());
        }

        return new ResponseEntity<>(this.authService.login(userBaseDto), HttpStatus.CREATED);
    }

    @PutMapping("/v1/token")
    public ResponseEntity<TokenDto> refreshToken(@Valid @RequestBody AuthDto.TokenRequestDto tokenDto
            , BindingResult result) {
        if (result.hasErrors()) {
            throw new BindingException(result.getFieldError());
        }

        return new ResponseEntity<>(this.tokenService.refreshToken(tokenDto), HttpStatus.OK);
    }

}
