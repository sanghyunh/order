package com.sanghyun.order.util;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Component;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

@Slf4j
@Component
public class TextUtil {

    public boolean passwordAllowedString(String target) {
        return this.permitText(
                target,
                "^(?=.*[A-Za-z])"
                        + "(?=.*[A-Za-z])"
                        + "(?=.*\\d)"
                        + "(?=.*[~`!@#$%^&*\\(\\)_-[+]=\\{\\}\\[\\];:\"\\\\'[|]<>,.?/])"
                        + "[A-Za-z\\d~`!@#$%^&*\\(\\)_-[+]=\\{\\}\\[\\];:\"\\\\'[|]<>,.?/]{1,}$");
    }

    public boolean permitText(String target, String pattern) {

        Pattern compilePattern = Pattern.compile(pattern);
        Matcher matcher = compilePattern.matcher(target);
        return matcher.find();
    }

    public String createHash(String salt, String password) {
        try {
            int keyLength = 512;
            int iteration = 100000;
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iteration, keyLength);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            SecretKey key = secretKeyFactory.generateSecret(pbeKeySpec);
            return Hex.encodeHexString(key.getEncoded());
        } catch (Exception e) {
            log.error("createHash Exception : {}", e.getMessage(), e);
            throw new CommonException(Errors.COMMON_ENCRYPT_ERROR);
        }
    }

}
