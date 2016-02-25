package com.kingdee.internet.captcha;

import com.kingdee.internet.entity.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:/applicationContext.xml",
        "classpath:/applicationContext-redis.xml"
})
public class CaptchaRecognitionTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private CaptchaRecognition captchaRecognition;

    @Value("${captcha.api.jsdati.test.image:aaaa}")
    private String testImage;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    public void testRecognition() {
        captchaRecognition.recognition(testImage, 5);
    }

    @Test
    public void testTaskExecutor() {
        Task task = new Task();
        task.setBankType("ccb_p");
        taskExecutor.execute((Runnable)applicationContext.getBean(task.getBankType(), task));
    }
}
