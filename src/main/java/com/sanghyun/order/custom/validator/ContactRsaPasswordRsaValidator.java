package com.sanghyun.order.custom.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.ObjectUtils;

import com.sanghyun.order.annotaion.RsaPasswordField;
import com.sanghyun.order.util.RsaUtil;
import com.sanghyun.order.util.TextUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContactRsaPasswordRsaValidator implements ConstraintValidator<RsaPasswordField, String> {

    private final RsaUtil rsaUtil;
    private final TextUtil textUtil;

    @Override
    public void initialize(RsaPasswordField contactNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        if (ObjectUtils.isEmpty(contactField)) {
            return false;
        }
        String decField = this.rsaUtil.decrypt(contactField);
        return this.textUtil.passwordAllowedString(decField);
    }

}
