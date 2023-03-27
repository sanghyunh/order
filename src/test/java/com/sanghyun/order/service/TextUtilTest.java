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

}
