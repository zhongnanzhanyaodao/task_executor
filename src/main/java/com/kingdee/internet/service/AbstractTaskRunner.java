package com.kingdee.internet.service;

import com.kingdee.internet.captcha.CaptchaRecognition;
import com.kingdee.internet.entity.Task;
import com.kingdee.internet.statemachine.Context;
import com.kingdee.internet.statemachine.ContextListener;
import com.kingdee.internet.statemachine.State;
import com.kingdee.internet.statemachine.States;
import com.kingdee.internet.util.ConfigUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.Semaphore;

public abstract class AbstractTaskRunner implements Runnable, Context {
    public static final Logger logger = LoggerFactory.getLogger(AbstractTaskRunner.class);
    protected final Task task;
    protected WebDriver webDriver;
    private State state;

    @Autowired
    @Qualifier("taskRunnerListener")
    private ContextListener listener;

    @Autowired
    @Qualifier("taskSemaphore")
    private Semaphore taskSemaphore;

    @Autowired
    protected ConfigUtils configUtils;

    @Autowired
    protected CaptchaRecognition captchaRecognition;

    public AbstractTaskRunner(WebDriver webDriver, Task task) {
        this.webDriver = webDriver;
        this.task = task;
        state(States.LOGIN);
    }

    @Override
    public void run() {
        try {
            while (state.process(this)) ;
        } catch (Exception e) {
            logger.error("process bank data encountered error.", e);
            if(listener != null) {
                listener.onError(this, e); // 异常处理
            }
        } finally {
            webDriver.quit(); // quit web driver
            taskSemaphore.release();
        }
    }

    @Override
    public void state(State state) {
        this.state = state;
        if(listener != null) {
            listener.stateChanged(this);
        }
    }

    protected void updateProgress(double percentage) {
        if(listener != null) {
            listener.updateProgress(this, percentage);
        }
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public Task task() {
        return task;
    }

    @Override
    public String getTaskId() {
        return task.getTaskId();
    }
}