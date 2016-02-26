package com.kingdee.internet.util;

import com.kingdee.internet.captcha.CaptchaRecognition;
import com.kingdee.internet.security.Cryptos;
import com.kingdee.internet.security.Encodes;
import org.apache.http.client.fluent.Request;
import org.junit.Test;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(locations = {
        "classpath:/applicationContext.xml",
        "classpath:/applicationContext-redis.xml"
})
public class SeleniumConfigTest extends AbstractJUnit4SpringContextTests {
    public static final Logger logger = LoggerFactory.getLogger(SeleniumConfigTest.class);

//    @Autowired
//    @Qualifier("ieWebDriver")
//    private WebDriver webDriver;

    @Autowired
    private CaptchaRecognition captchaRecognition;

    @Autowired
    private ConfigUtils configUtils;

    /*@Test
    public void CCBPTest() throws Exception {
        webDriver.get("https://ibsbjstar.ccb.com.cn/app/V5/CN/STY1/login.jsp");
        webDriver.switchTo().frame("fclogin");
        webDriver.findElement(By.id("USERID")).sendKeys("kdpfx");
        webDriver.findElement(By.id("LOGPASS")).sendKeys("pfx147258");
        boolean bypass = false;
        do {
            try {
                String imgUrl = webDriver.findElement(By.id("fujiama")).getAttribute("src");
                String imgBase64 = Encodes.encodeBase64(Request.Get(imgUrl).execute().returnContent().asBytes());
                String captcha = captchaRecognition.recognition(imgBase64, 5);
                webDriver.findElement(By.id("PT_CONFIRM_PWD")).clear();
                webDriver.findElement(By.id("PT_CONFIRM_PWD")).sendKeys(captcha);
                TimeUnit.SECONDS.sleep(1);
                webDriver.findElement(By.id("fujiamacorrect"));
                bypass = true;
            } catch (Exception e) {
                logger.warn("cannot find correct element.");
                webDriver.findElement(By.id("getyan")).click();
            }
        } while (!bypass);

        webDriver.findElement(By.id("loginButton")).sendKeys(Keys.ENTER);
        webDriver.quit();
    }*/

    @Test
    public void testDecrypt() {
        String source = Encodes.encodeBase64(Cryptos.aesEncrypt("pfx147258".getBytes(), configUtils.aesKey(), configUtils.aesIV(), 16));
        logger.info(source);
        logger.info(CommonUtils.aesDecrypt(source, configUtils, 16));
    }
}
