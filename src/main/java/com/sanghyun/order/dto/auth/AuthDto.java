package com.sanghyun.order.dto.auth;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class AuthDto {

    @Builder
    @Getter
    public static class TokenDto {

        private String token;
        private String refreshToken;
    }

    @Data
    public static class TokenRequestDto {

        @NotEmpty
        private String token;
        @NotEmpty
        private String refreshToken;
    }

    @Builder
    @Getter
    public static class TokenClaimDto {
        private String signId;
        private String name;
    }

    @Builder
    @Getter
    public static class TokenPayloadDto {
         private String sub;
         private Long exp;
         private Long iat;
         private TokenClaimDto claim;
    }


}
