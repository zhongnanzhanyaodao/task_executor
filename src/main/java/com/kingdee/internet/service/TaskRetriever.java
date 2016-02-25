package com.kingdee.internet.service;

import com.alibaba.fastjson.JSON;
import com.kingdee.internet.entity.Task;
import com.kingdee.internet.util.ApplicationContextHolder;
import com.kingdee.internet.util.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class TaskRetriever {
    public static final Logger logger = LoggerFactory.getLogger(TaskRetriever.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ConfigUtils configUtils;

    /**
     * 每5秒执行一次
     */
    @Scheduled(fixedDelay = 5000)
    public void retrieveTask() throws InterruptedException {
        if (taskExecutor.getActiveCount() < configUtils.taskPoolSize()) {
            logger.info("begin to retrieve task from: {}", configUtils.taskRetrieveApi());
            try {
                String response = Request.Get(configUtils.taskRetrieveApi())
                        .socketTimeout(1000)
                        .execute()
                        .returnContent()
                        .asString(Charset.forName("utf-8"));
                if (StringUtils.isNotBlank(response)) {
                    logger.debug("task retrieved: {}", response);
                    Task task = JSON.parseObject(response, Task.class);
                    WebDriver webDriver = ApplicationContextHolder.getBean("ieWebDriver", WebDriver.class);
                }
            } catch (IOException e) {
                logger.error("retrieve task failed.", e);
            }
        }
    }
}
