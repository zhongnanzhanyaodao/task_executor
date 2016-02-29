package com.kingdee.internet.util;

import com.kingdee.internet.security.Cryptos;
import com.kingdee.internet.security.Encodes;
import com.kingdee.internet.security.Exceptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Map;
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

    public static String getClassPathFileContent(String filePath) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:" + filePath);
        try {
            return FileUtils.readFileToString(resource.getFile());
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String map2urlParams(Map<String, String> params) {
        StringBuffer buffer = new StringBuffer();
        if(params != null) {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return buffer.toString();
    }
}
