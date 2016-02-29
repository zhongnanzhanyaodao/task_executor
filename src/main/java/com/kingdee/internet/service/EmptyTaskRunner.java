package com.kingdee.internet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component("emptyTaskRunner")
public class EmptyTaskRunner implements Runnable {
    @Autowired
    @Qualifier("taskSemaphore")
    private Semaphore taskSemaphore;

    @Override
    public void run() { // 空任务，直接释放资源
        taskSemaphore.release();
    }
}
