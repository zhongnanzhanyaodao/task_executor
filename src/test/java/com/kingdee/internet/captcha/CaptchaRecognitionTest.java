package com.kingdee.internet.captcha;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:/applicationContext.xml",
        "classpath:/applicationContext-redis.xml"
})
public class CaptchaRecognitionTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private CaptchaRecognition captchaRecognition;

    @Value("${captcha.api.jsdati.test.image}")
    private String testImage;

    @Test
    public void testRecognition() {
        captchaRecognition.recognition(testImage, 5);
    }
}
