package com.sanghyun.order.util;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.stereotype.Component;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j(topic = "http")
@Component
@RequiredArgsConstructor
public class RsaUtil {

    private final InitProperties initProperties;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        this.initPrivateKey();
        this.initPublicKey();
    }

    public String decrypt(String data) {
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes())));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
                 | InvalidKeyException e) {
            log.error("decrypt error : {}", e.getMessage(), e);
            throw new CommonException(Errors.RSA_DECRYPT_ERROR);
        }
        return result;
    }


    public String encrypt(String data) {
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
            result = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                 | IllegalBlockSizeException | InvalidKeyException e) {
            log.error("encrypt error : {}", e.getMessage(), e);
        }
        return result;
    }

    private void initPublicKey() {
        try {
            X509EncodedKeySpec keySpec =
                    new X509EncodedKeySpec(Base64.getDecoder().decode(this.initProperties.getPublicRsaKey()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("getPublicKey : {}", e.getMessage(), e);
        }
    }

    private void initPrivateKey() {
        String base64PrivateKey = this.initProperties.getPrivateRsaKey();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("getPrivateKey : {}", e.getMessage(), e);
        }
    }

    public void createRsaKey() {

        // TODO : 일회용 키생성
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);

            KeyPair keyPair = generator.genKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            byte[] privateKeyEnc = privateKey.getEncoded();
            byte[] privateKeyPem = Base64.getEncoder().encode(privateKeyEnc);
            String privateKeyPemStr = new String(privateKeyPem);
            byte[] publicKeyEnc = publicKey.getEncoded();
            byte[] publicKeyPem = Base64.getEncoder().encode(publicKeyEnc);
            String publicKeyPemStr = new String(publicKeyPem);
            log.info("publicKey: [{}]", publicKeyPemStr);
            log.info("privateKey: [{}]", privateKeyPemStr);

        } catch (Exception e) {
            log.error("initRsa : {}", e.getMessage(), e);
        }
    }
}
