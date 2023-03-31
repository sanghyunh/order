package com.sanghyun.order.constant;

import lombok.Getter;

@Getter
public enum Errors {

    GENERAL_UNKNOWN(-10, "에러", "Unknown Error"),
    RSA_DECRYPT_ERROR(-5000, "에러", "RSA DECRYPT ERROR"),
    COMMON_ENCRYPT_ERROR(-5001, "에러", "암호화 오류"),
    UNKNOWN_USER(-5002, "에러", "존재하지 않는 사용자 입니다. 아이디 혹은 비밀번호를 확인해 주세요"),
    JWT_DECRYPT_ERROR(-5003, "에러", "JWT DECRYPT ERROR"),
    NOT_FOUND_TOKEN(-5004, "에러", "NOT FOUND TOKEN"),
    EXPIRATION_REFRESH_TOKEN(-5005, "에러", "EXPIRATION REFRESH TOKEN"),
    OAUTH_TOKEN_NOT_FOUND(-5006, "에러", "TOKEN 정보가 없습니다."),
    OAUTH_TOKEN_TIMEOUT(-5007, "에러", "TOKEN 만료 (refresh)"),
    OAUTH_TOKEN_ERROR(-5008, "에러", "잘못된 TOKEN 입니다"),
    NOT_POSSIBLE_UPDATE_ORDER(-5009, "에러", "수정할 수 없는 주문 건 입니다."),
    TEST(-9999, "에러", "임시 에러");

    private final int code;
    private final String title;
    private final String message;

    Errors(int code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }
}
