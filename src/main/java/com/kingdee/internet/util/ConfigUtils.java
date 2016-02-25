package com.kingdee.internet.util;

import com.kingdee.internet.security.Encodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtils {
    @Value("${aes.key}")
    private String aesKey;

    @Value("${aes.iv}")
    private String aesIV;

    public byte[] aesKey() {
        return Encodes.decodeBase64(aesKey);
    }

    public byte[] aesIV() {
        return Encodes.decodeBase64(aesIV);
    }
}
