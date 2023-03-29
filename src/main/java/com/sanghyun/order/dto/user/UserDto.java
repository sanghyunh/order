package com.sanghyun.order.dto.user;

import javax.validation.constraints.NotEmpty;

import com.sanghyun.order.annotaion.RsaPasswordField;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserJoinRequestDto extends UserBaseDto {

        @NotEmpty
        private String name;
    }

    @Data
    public static class UserBaseDto {

        @NotEmpty
        private String signId;
        @RsaPasswordField
        private String password;

    }

}
