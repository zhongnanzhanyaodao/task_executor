package com.kingdee.internet.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.msgpack.annotation.Message;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "t_bd_sync_task")
@Message
public class Task {
    @Id
    private String taskId;
    private String userId;
    private String cardNum;
    private String passwd;
    private Date requestDate;
    private String processed;
    private String errorCode;
    private String errorMessage;
    private Date endDate;
    private String bankType;
    private String username;

    @Column(name = "params")
    private String paramsStr;

    @Transient
    private Map<String, Map<String, String>> params;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParamsStr() {
        paramsStr = JSON.toJSONString(params);
        return paramsStr;
    }

    public void setParamsStr(String paramsStr) {
        this.paramsStr = paramsStr;
        params = JSON.parseObject(paramsStr, new TypeReference<Map<String, Map<String, String>>>(){});
    }

    public Map<String, Map<String, String>> getParams() {
        return params;
    }

    public void setParams(Map<String, Map<String, String>> params) {
        this.params = params;
    }
}
