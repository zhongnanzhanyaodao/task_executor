package com.kingdee.internet.statemachine;

/**
 * 抓取服务状态枚举
 */
public enum States implements State {
    LOGIN("1", "网银登陆") {
        @Override
        public boolean process(Context context) {
            context.login();
            context.state(FETCH);
            return true;
        }
    }, FETCH("7", "数据抓取") {
        @Override
        public boolean process(Context context) {
            context.fetchData();
            context.state(SAVE);
            return true;
        }
    }, SAVE("8", "数据保存") {
        @Override
        public boolean process(Context context) {
            context.saveData();
            context.state(DONE);
            return true;
        }
    }, DONE("100", "完成") {
        @Override
        public boolean process(Context context) {
            return false;
        }
    };

    public final String progressId;
    public final String describe;

    States(String progressId, String describe) {
        this.progressId = progressId;
        this.describe = describe;
    }
}
