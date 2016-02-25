package com.kingdee.internet.captcha;

public interface CaptchaRecognition {
    String recognition(String imgBase64, int length);
}
