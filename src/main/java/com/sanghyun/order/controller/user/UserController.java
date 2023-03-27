package com.sanghyun.order.controller.user;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanghyun.order.dto.user.UserDto.UserJoinRequestDto;
import com.sanghyun.order.exception.BindingException;
import com.sanghyun.order.service.user.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/join")
    public ResponseEntity<Void> joinUser(
            @Valid @RequestBody UserJoinRequestDto userJoinRequestDto
            , BindingResult result) {
        if (result.hasErrors()) {
            throw new BindingException(result.getFieldError());
        }
        this.userService.joinUser(userJoinRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
