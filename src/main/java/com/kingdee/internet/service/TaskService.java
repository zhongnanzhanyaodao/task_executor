package com.kingdee.internet.service;

import com.kingdee.internet.entity.TaskProgress;
import com.kingdee.internet.repository.TaskDao;
import com.kingdee.internet.repository.TaskProgressDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class TaskService {
    public static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskProgressDao taskProgressDao;

    @Transactional(readOnly = false)
    public void updateProgress(TaskProgress taskProgress) {
        taskProgressDao.insert(taskProgress);
    }

    @Transactional(readOnly = false)
    public void updateTaskStatus(String taskId, String errCode, String errMsg) {
        taskDao.upateStatus(taskId, errCode, errMsg, null);
    }
}
