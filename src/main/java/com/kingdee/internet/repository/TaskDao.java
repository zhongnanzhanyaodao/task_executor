package com.kingdee.internet.repository;

import com.kingdee.internet.entity.Task;

import java.util.Date;

public interface TaskDao {
    void insert(Task task);

    /**
     * 当密码错误、处理完成、处理超时 更新 请求同步任务记录
     * @Update 此方法不更新数据库EndDate, 任务完成时才更新
     */
    void upateStatus(String taskId,String errorCode,String message,Date endDate);

    /**
     * 更改任务完成时间
     * @param taskId
     * @param endDate
     * @return
     */
    int updateTaskCompleteDate(String taskId, Date endDate);
}
