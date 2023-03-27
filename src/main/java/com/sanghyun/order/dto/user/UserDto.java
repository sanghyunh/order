package com.sanghyun.order.dto.user;

import javax.validation.constraints.NotEmpty;

import com.sanghyun.order.annotaion.RsaPasswordField;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class UserDto {

    @Data
    public static class UserJoinRequestDto {

        @NotEmpty
        private String id;
        @RsaPasswordField
        private String password;
        @NotEmpty
        private String name;

    }

    @Getter
    @Builder
    public static class UserJoinResponseDto {
        private String token;
    }

}
