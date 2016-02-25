package com.kingdee.internet.service;

import com.kingdee.internet.entity.Task;
import org.openqa.selenium.WebDriver;

public interface Context {
    WebDriver webDriver();
    Task task();
    State state();
    void state(State state);
}
