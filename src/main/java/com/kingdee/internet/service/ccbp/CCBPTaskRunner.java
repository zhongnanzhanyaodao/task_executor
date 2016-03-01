package com.kingdee.internet.service.ccbp;

import com.kingdee.internet.entity.Task;
import com.kingdee.internet.security.Encodes;
import com.kingdee.internet.service.AbstractTaskRunner;
import com.kingdee.internet.service.BankService;
import com.kingdee.internet.util.ApplicationContextHolder;
import com.kingdee.internet.util.CommonUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 中国建设银行-个人 登陆、数据抓取、数据保存处理
 *
 * {     "cardNum": "kdpfx",     "params": {         "respData": {             "bankLoginNo": "kdpfx",             "bankType": "ccb_p",             "bankPassword": "sMvpnaEMqchq0Qg+nUdbwQ==",             "startDate": "20150901",             "userId": "188888",             "isUpdate": "no",             "isAgreeProtocol": "yes"         }     },     "passwd": "sMvpnaEMqchq0Qg+nUdbwQ==",     "bankType": "ccb_p",     "userId": "188888" }
 */
@Component("ccbpTaskRunner")
@Scope("prototype")
public class CCBPTaskRunner extends AbstractTaskRunner {
    public static final Logger logger = LoggerFactory.getLogger(CCBPTaskRunner.class);

    @Value("${ccbp.login.url}")
    private String url;

    @Autowired
    @Qualifier("ccbpBankService")
    private BankService ccbpBankService;

    private String bankDataJson;

    public CCBPTaskRunner(Task task) {
        super(ApplicationContextHolder.getBean("chromeWebDriver", WebDriver.class), task);
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
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
        jsExecutor.executeScript(CommonUtils.getClassPathFileContent("js/bridge.js"));
        jsExecutor.executeScript("_X.setParams('" +
                CommonUtils.map2urlParams(task.getParams().get("respData")) + "')");
        webDriver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS); // 设置超时
        bankDataJson = (String) jsExecutor.executeAsyncScript(
                "var callbackJava = arguments[arguments.length - 1];\n"
                + CommonUtils.getClassPathFileContent("js/ccb-p.js")
        ); // 执行异步JavaScript
        updateProgress(0.5d); // 更新进度为50%

        // 获取浏览器 Cookie
        Set<Cookie> cookies = webDriver.manage().getCookies();
        CookieStore cookieStore = new BasicCookieStore();
        for(Cookie cookie : cookies) {
            cookieStore.addCookie(new BasicClientCookie(cookie.getName(), cookie.getValue()));
        }
        extra.put("cookieStore", cookieStore);
    }

    @Override
    public void saveData() {
        ccbpBankService.saveBankData(bankDataJson, this);
    }
}
