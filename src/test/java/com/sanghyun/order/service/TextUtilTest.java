package com.sanghyun.order.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanghyun.order.CommonTest;
import com.sanghyun.order.util.TextUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextUtilTest extends CommonTest {

    @Autowired
    private TextUtil textUtil;

    @Test
    public void allowTextTest() {
        Assertions.assertFalse(this.textUtil.passwordAllowedString("1234"));
        Assertions.assertFalse(this.textUtil.passwordAllowedString("1234Saaaa333"));
        Assertions.assertFalse(this.textUtil.passwordAllowedString("1234aaaaa333"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333#"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333!"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333("));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333)"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333*"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333&"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333^"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333%"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333$"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333#"));
        Assertions.assertTrue(this.textUtil.passwordAllowedString("14aSa22aaa333@"));
    }

    @Test
    public void hashTest() {
        System.out.println(this.textUtil.createHash("EF40C708-CE6E-43D7-A193-17B5964BDCB9", "test22"));

    }

}
