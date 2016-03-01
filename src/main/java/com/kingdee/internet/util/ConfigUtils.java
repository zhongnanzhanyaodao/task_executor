package com.kingdee.internet.util;

import com.kingdee.internet.security.Encodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtils {
    public static final String FLAG_C = "贷";
    public static final String FLAG_D = "借";

    @Value("${aes.key}")
    private String aesKey;

    @Value("${aes.iv}")
    private String aesIV;

    @Value("${task.retrieve.api}")
    private String taskRetrieveApi;

    @Value("${task.pool.size}")
    private int taskPoolSize;

    public byte[] aesKey() {
        return Encodes.decodeBase64(aesKey);
    }

    public byte[] aesIV() {
        return Encodes.decodeBase64(aesIV);
    }

    public String taskRetrieveApi() {
        return taskRetrieveApi;
    }

    public int taskPoolSize() {
        return taskPoolSize;
    }
}
