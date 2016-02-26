package com.kingdee.internet.entity;

import com.kingdee.internet.util.IBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "T_BD_SYNC_PROGRESS")
public class TaskProgress {
    @Id
    private String taskId;
    private String progress;
    private Date currentDate;
    private String percentage;

    public TaskProgress() {}

    private TaskProgress(Builder builder) {
        this.taskId = builder.taskId;
        this.progress = builder.progress;
        this.currentDate = builder.currentDate;
        this.percentage = builder.percentage;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public static class Builder implements IBuilder<TaskProgress> {
        private final String taskId;
        private String progress;
        private Date currentDate;
        private String percentage;

        public Builder(String taskId) {
            this.taskId = taskId;
        }

        public Builder progress(String progress) {
            this.progress = progress;
            return this;
        }

        public Builder currentDate(Date currentDate) {
            this.currentDate = currentDate;
            return this;
        }

        public Builder percentage(String percentage) {
            this.percentage = percentage;
            return this;
        }

        public Builder percentage(Double percentage) {
            this.percentage = Double.toString(percentage);
            return this;
        }

        @Override
        public TaskProgress build() {
            return new TaskProgress(this);
        }
    }
}
