package com.kingdee.internet.util;

public class TaskException extends RuntimeException {
    private final String errMsg;
    private final String errCode;

    public TaskException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public static TaskException create(String code, String message) {
        return new TaskException(code, message);
    }

    public static TaskException create(TaskStatus status) {
        return new TaskException(status.code(), status.message());
    }
}
