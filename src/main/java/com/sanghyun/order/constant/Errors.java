package com.sanghyun.order.constant;

import lombok.Getter;

@Getter
public enum Errors {

    GENERAL_UNKNOWN(-10, "에러", "Unknown Error"),
    RSA_DECRYPT_ERROR(-5000, "에러", "RSA DECRYPT ERROR"),
    COMMON_ENCRYPT_ERROR(-5001, "에러", "암호화 오류"),
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
