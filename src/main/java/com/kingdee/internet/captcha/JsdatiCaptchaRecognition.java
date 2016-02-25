package com.kingdee.internet.captcha;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.internet.security.Encodes;
import com.kingdee.internet.security.Exceptions;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsdatiCaptchaRecognition implements CaptchaRecognition {
    private static final Logger logger = LoggerFactory.getLogger(JsdatiCaptchaRecognition.class);

    @Value("${captcha.api.jsdati.url}")
    private String url;

    @Value("${captcha.api.jsdati.username}")
    private String username;

    @Value("${captcha.api.jsdati.password}")
    private String password;

    @Value("${captcha.api.jsdati.token}")
    private String token;

    /**
     * api return sample: {"result":true,"data":{"id":9748720207,"val":"RK2F2"}}
     * @param imgBase64
     * @param length
     * @return
     */
    @Override
    public String recognition(String imgBase64, int length) {
        try {
            String result = Request.Post(url)
                    .body(buildHttpEntity(imgBase64, length))
                    .socketTimeout(10000) // set socket timeout 10 seconds
                    .execute()
                    .returnContent()
                    .asString();
            logger.info("jsdati api returned: {}", result);
            JSONObject resp = JSON.parseObject(result);
            if(resp.getBooleanValue("result")) {
                return resp.getJSONObject("data").getString("val");
            }
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
        return null;
    }

    private HttpEntity buildHttpEntity(String imgBase64, int length) {
        String lenStr = "" + length;
        byte[] imgBytes = Encodes.decodeBase64(imgBase64);
        return MultipartEntityBuilder.create()
                .addTextBody("user_name", username)
                .addTextBody("user_pw", password)
                .addTextBody("yzm_minlen", lenStr)
                .addTextBody("yzm_maxlen", lenStr)
                .addTextBody("yzmtype_mark", "0")
                .addTextBody("zztool_token", token)
                .addBinaryBody("upload", imgBytes, ContentType.create("application/octet-stream"), "uploadimage")
                .build();
    }

}
