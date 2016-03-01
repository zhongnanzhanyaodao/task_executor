package com.kingdee.internet.service;

import com.kingdee.internet.entity.TaskProgress;
import com.kingdee.internet.statemachine.Context;
import com.kingdee.internet.statemachine.ContextListenerAdapter;
import com.kingdee.internet.statemachine.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskRunnerListener extends ContextListenerAdapter {
    public static final Logger logger = LoggerFactory.getLogger(TaskRunnerListener.class);

    @Autowired
    private TaskService taskService;

    @Override
    public void stateChanged(Context context) {
        States currentState = (States)context.state();
        // update task progress
        logger.debug("update task progress, current state is: {}", currentState);
        if(currentState == States.LOGIN) {
            // TODO: 更改任务状态？
        }
        TaskProgress taskProgress = buildProgress(context);
        taskService.updateProgress(taskProgress);
    }

    /**
     * 任务执行异常处理
     * @param context
     * @param e
     */
    @Override
    public void onError(Context context, Throwable e) {
        taskService.updateTaskStatus(context.getTaskId(), e.getMessage(), e.getMessage());
    }

    @Override
    public void updateProgress(Context context, double percentage) {
        taskService.updateProgress(buildProgress(context, percentage));
    }

    private TaskProgress buildProgress(Context context) {
        return buildProgress(context, 0.0d);
    }

    private TaskProgress buildProgress(Context context, double percentage) {
        if(context.state() instanceof States) {
            States state = (States) context.state();
            return new TaskProgress.Builder(context.getTaskId())
                    .currentDate(new Date())
                    .progress(state.progressId)
                    .percentage(percentage)
                    .build();
        }
        return null;
    }
}
