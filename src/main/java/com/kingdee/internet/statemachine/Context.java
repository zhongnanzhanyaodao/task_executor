package com.kingdee.internet.statemachine;

import com.kingdee.internet.entity.Task;

import java.util.Map;

public interface Context {
    Task task();
    void state(State state);
    State state();

    void login();
    void fetchData();
    void saveData();

    String getTaskId();

    Map<String, Object> extra();
}
