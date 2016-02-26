package com.kingdee.internet.service;

import com.alibaba.fastjson.JSON;
import com.kingdee.internet.entity.Task;
import com.kingdee.internet.util.CommonUtils;
import com.kingdee.internet.util.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Semaphore;

import static com.kingdee.internet.util.ApplicationContextHolder.applicationContext;

@Component
public class TaskRetriever {
    public static final Logger logger = LoggerFactory.getLogger(TaskRetriever.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ConfigUtils configUtils;

    @Autowired
    @Qualifier("taskSemaphore")
    private Semaphore taskSemaphore;

    /**
     * 每5秒执行一次
     */
    @Scheduled(fixedDelay = 5000)
    public void retrieveTask() throws InterruptedException {
        if(taskSemaphore.availablePermits() > 0) { // 信号量大于0
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
                    task.setPasswd(CommonUtils.aesDecrypt(task.getPasswd(), configUtils, 16)); // 密码解密
                    taskSemaphore.acquire();
                    taskExecutor.execute((Runnable) applicationContext().getBean(task.getBankType(), task));
                }
            } catch (IOException e) {
                logger.error("retrieve task failed.", e);
            }
        }
    }
}
