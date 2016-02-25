package com.kingdee.internet.service;

import com.kingdee.internet.entity.Task;
import com.kingdee.internet.util.ApplicationContextHolder;
import org.openqa.selenium.WebDriver;

public abstract class AbstractTaskRunner implements Runnable, Context {
    protected final Task task;
    protected final WebDriver webDriver = ApplicationContextHolder.getBean("ieWebDriver", WebDriver.class);

    public AbstractTaskRunner(Task task) {
        this.task = task;
    }

    @Override
    public WebDriver webDriver() {
        return webDriver;
    }

    @Override
    public Task task() {
        return task;
    }
}