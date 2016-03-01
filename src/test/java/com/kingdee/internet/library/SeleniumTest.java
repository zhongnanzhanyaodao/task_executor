package com.kingdee.internet.library;

import com.alibaba.fastjson.JSON;
import com.kingdee.internet.entity.Task;
import com.kingdee.internet.security.Encodes;
import com.kingdee.internet.util.CommonUtils;
import org.apache.http.client.fluent.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class SeleniumTest {
    public static final Logger logger = LoggerFactory.getLogger(SeleniumTest.class);

    private static final String TASK_JSON = "{     \"cardNum\": \"kdpfx\",     \"params\": {         \"respData\": {             \"bankLoginNo\": \"kdpfx\",             \"bankType\": \"ccb_p\",             \"bankPassword\": \"sMvpnaEMqchq0Qg+nUdbwQ==\",             \"startDate\": \"20150901\",             \"userId\": \"188888\",             \"isUpdate\": \"no\",             \"isAgreeProtocol\": \"yes\"         }     },     \"passwd\": \"sMvpnaEMqchq0Qg+nUdbwQ==\",     \"bankType\": \"ccb_p\",     \"userId\": \"188888\" }";

    private WebDriver webDriver;

    @Before
    public void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "D:/DevTools/selenium/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @After
    public void destory() {
        webDriver.quit();
    }

    @Test
    public void test() throws InterruptedException {
        Task task = JSON.parseObject(TASK_JSON, Task.class);

        webDriver.get("https://ibsbjstar.ccb.com.cn/CCBIS/V6/STY1/CN/login.jsp");
        webDriver.switchTo().frame("fclogin");
        webDriver.findElement(By.id("USERID")).sendKeys("kdpfx");
        webDriver.findElement(By.id("LOGPASS")).sendKeys("pfx147258");
        webDriver.findElement(By.id("loginButton")).sendKeys(Keys.ENTER);

        JavascriptExecutor jsExector = (JavascriptExecutor)webDriver;
        jsExector.executeScript(CommonUtils.getClassPathFileContent("js/bridge.js"));
        jsExector.executeScript("_X.setParams('" +
                CommonUtils.map2urlParams(task.getParams().get("respData")) + "')");
        webDriver.manage().timeouts().setScriptTimeout(1, TimeUnit.MINUTES);
        String result = (String) jsExector.executeAsyncScript(CommonUtils.getClassPathFileContent("js/ccb-p.js"));
        logger.info("return message: {}", result);
        TimeUnit.SECONDS.sleep(20);
    }
}
