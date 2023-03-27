package com.sanghyun.order.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class InitProperties {

    @Value("${rsa.public-key}")
    private String publicRsaKey;
    @Value("${rsa.private-key}")
    private String privateRsaKey;

}
