package com.kingdee.internet.util;

public enum TaskStatus {
    NORMAL("0", "正常"),
    INVALID_PIN("2", "密码输入错误"),
    PROCESS_TIMEOUT("3", "处理超时，请重试"),
    INVALID_USER("5", "用户输入错误"),
    FETCH_FAILED("6", "数据抓取失败，请重试"),
    INVALID_USER_OR_PIN("7", "用户或密码输入不正确"),
    FROZEN_ACCOUNT("9", "账户被冻结，请联系银行处理");

    final String code;
    final String message;

    TaskStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
