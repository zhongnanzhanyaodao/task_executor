package com.kingdee.internet.service;

import com.alibaba.fastjson.JSON;
import com.kingdee.internet.entity.Task;
import com.kingdee.internet.util.ApplicationContextHolder;
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
import java.util.Map;
import java.util.concurrent.Semaphore;

import static com.kingdee.internet.util.ApplicationContextHolder.applicationContext;

@Component
public class TaskRetriever {
    public static final Logger logger = LoggerFactory.getLogger(TaskRetriever.class);

    private static final String TASK_RUNNER_SUFFIX = "TaskRunner";
    private static final String BANK_SERVICE_SUFFIX = "BankService";

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ConfigUtils configUtils;

    @Autowired
    @Qualifier("taskSemaphore")
    private Semaphore taskSemaphore;

    /**
     * 同步任务获取定时任务，每5秒执行一次
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
                    taskExecutor.execute(getTaskRunner(task));
                }
            } catch (IOException e) {
                logger.error("retrieve task failed.", e);
            }
        }
    }

    /**
     * 定制同步任务参数
     * @param bankService
     * @param task
     */
    private static void customiseTaskParams(BankService bankService, Task task) {
        // 1. customise task start date
        Map<String, String> respData = task.getParams().get("respData");
        respData.put("startDate", bankService.getStartDate(respData));
    }

    /**
     * 实例化抓取任务类，获取失败时，执行空任务执行器，释放信号量
     * @param task
     * @return
     */
    private static Runnable getTaskRunner(Task task) {
        String bankType = task.getBankType();
        if(StringUtils.isNotBlank(bankType)) {
            String prefix = StringUtils.replaceChars(bankType, "_", "").toLowerCase();
            try {
                customiseTaskParams(ApplicationContextHolder.getBean(prefix + BANK_SERVICE_SUFFIX, BankService.class), task);
                return (Runnable) applicationContext().getBean(prefix + TASK_RUNNER_SUFFIX, task);
            } catch (Exception e) {
                logger.warn("get task runner with name: {} failed!", prefix);
            }
        }
        return applicationContext().getBean(EmptyTaskRunner.class);
    }
}
