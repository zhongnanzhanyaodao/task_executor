package com.kingdee.internet.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class CryptosTest {
    public static final Logger logger = LoggerFactory.getLogger(Cryptos.class);

    @Test
    public void aesTest() {
        String iv = "wmKWvDAKPPZcDgNh";
        String password = "af25414ea7ef616ghce2bf0bc811895c";

        logger.info(Encodes.encodeBase64(iv.getBytes(Charset.forName("utf-8"))));
        logger.info(Encodes.encodeBase64(password.getBytes(Charset.forName("utf-8"))));
    }
}