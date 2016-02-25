package com.kingdee.internet.util;

import com.kingdee.internet.security.Cryptos;
import com.kingdee.internet.security.Encodes;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class CommonUtils {
    private CommonUtils() {
        throw new UnsupportedOperationException();
    }

    public static String uuid() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    public static String aesDecrypt(String source, ConfigUtils configUtils) {
        return Cryptos.aesDecrypt(Encodes.decodeBase64(source), configUtils.aesKey(), configUtils.aesIV());
    }

    public static String aesDecrypt(String source, ConfigUtils configUtils, int keyLength) {
        return Cryptos.aesDecrypt(Encodes.decodeBase64(source), configUtils.aesKey(), configUtils.aesIV(), keyLength);
    }
}
