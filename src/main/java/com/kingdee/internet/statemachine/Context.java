package com.kingdee.internet.statemachine;

import com.kingdee.internet.entity.Task;

public interface Context {
    Task task();
    void state(State state);
    State state();

    void login();
    void fetchData();
    void saveData();

    String getTaskId();
}
