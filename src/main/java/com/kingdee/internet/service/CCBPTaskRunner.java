package com.kingdee.internet.service;

import com.kingdee.internet.captcha.CaptchaRecognition;
import com.kingdee.internet.entity.Task;
import com.kingdee.internet.security.Encodes;
import com.kingdee.internet.util.ApplicationContextHolder;
import com.kingdee.internet.util.CommonUtils;
import com.kingdee.internet.util.ConfigUtils;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("ccb_p")
@Scope("prototype")
public class CCBPTaskRunner extends AbstractTaskRunner {
    public static final Logger logger = LoggerFactory.getLogger(CCBPTaskRunner.class);

    private State state;

    public CCBPTaskRunner(Task task) {
        super(task);
        this.state = States.LOGIN;
    }

    @Override
    public void run() {
        while (state.process(this));
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public void state(State state) {
        this.state = state;
    }

    enum States implements State {
        INIT { // 初始化运行环境
            @Override
            public boolean process(Context context) {
                task = context.task();
                task.setPasswd(CommonUtils.aesDecrypt(task.getPasswd(), configUtils, 16));
                webDriver = context.webDriver();
                context.state(LOGIN);
                return true;
            }
        }, LOGIN { // 登陆
            @Override
            public boolean process(Context context) {
                webDriver.get("https://ibsbjstar.ccb.com.cn/app/V6/CN/STY1/login.jsp");
                webDriver.switchTo().frame("fclogin");
                webDriver.findElement(By.id("USERID")).sendKeys("kdpfx");
                webDriver.findElement(By.id("LOGPASS")).sendKeys("pfx147258");
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
                return true;
            }
        };

        static final ConfigUtils configUtils = ApplicationContextHolder.getBean(ConfigUtils.class);
        static final CaptchaRecognition captchaRecognition = ApplicationContextHolder.getBean(CaptchaRecognition.class);
        static WebDriver webDriver;
        static Task task;
    }
}
