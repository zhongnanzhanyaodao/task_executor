package com.kingdee.internet.statemachine;

public interface ContextListener {
    /**
     * context current state changed
     * @param context
     */
    void stateChanged(Context context);

    /**
     * encounter error
     * @param context
     * @param e
     */
    void onError(Context context, Throwable e);

    /**
     * update progress
     * @param context
     * @param percentage
     */
    void updateProgress(Context context, double percentage);
}
