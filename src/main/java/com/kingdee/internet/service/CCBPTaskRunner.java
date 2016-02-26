package com.kingdee.internet.service;

import com.kingdee.internet.entity.Task;
import com.kingdee.internet.security.Encodes;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 中国建设银行-个人 登陆、数据抓取、数据保存处理
 */
@Component("ccb_p")
@Scope("prototype")
public class CCBPTaskRunner extends AbstractTaskRunner {
    public static final Logger logger = LoggerFactory.getLogger(CCBPTaskRunner.class);

    @Value("${ccbp.login.url}")
    private String url;

    public CCBPTaskRunner(Task task) {
        super(task);
    }

    @Override
    public void login() {
        logger.info("---------------- begin to process {} login", task.getBankType());
        webDriver.get(url);
        webDriver.switchTo().frame("fclogin");
        webDriver.findElement(By.id("USERID")).sendKeys(task.getCardNum());
        webDriver.findElement(By.id("LOGPASS")).sendKeys(task.getPasswd());
        updateProgress(0.5d); // 设置登陆进度为50%
        boolean bypass = false;
        do {
            try {
                WebElement imgElem;
                try {
                    imgElem = webDriver.findElement(By.id("fujiama"));
                } catch (NoSuchElementException e) {
                    logger.debug("no need to input captcha.");
                    break;
                }
                String imgBase64 = Encodes.encodeBase64(Request.Get(imgElem.getAttribute("src")).execute().returnContent().asBytes());
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
        updateProgress(1.0d); // 设置登陆进度为100%
        logger.info("---------------- {} login finished", task.getBankType());
    }

    @Override
    public void fetchData() {
        Set<Cookie> cookies = webDriver.manage().getCookies();
    }

    @Override
    public void saveData() {
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
