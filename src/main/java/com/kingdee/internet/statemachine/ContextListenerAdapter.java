package com.kingdee.internet.statemachine;

/**
 * Context Listener Adapter
 */
public abstract class ContextListenerAdapter implements ContextListener {

    @Override
    public void stateChanged(Context context) {

    }

    @Override
    public void onError(Context context, Throwable e) {

    }

    @Override
    public void updateProgress(Context context, double percentage) {

    }
}
