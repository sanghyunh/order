package com.sanghyun.order.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanghyun.order.CommonTest;
import com.sanghyun.order.util.RsaUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RsaUtilTest extends CommonTest {

    @Autowired
    private RsaUtil rsaUtil;

    @Test
    public void generateRsa() {
        this.rsaUtil.createRsaKey();
    }

    @Test
    public void rsaTest() {
        String encData = "one,two";
        String enc = this.rsaUtil.encrypt(encData);
        System.out.println(enc);
        String dec = this.rsaUtil.decrypt(enc);
        Assertions.assertEquals(encData, dec);
    }
}
