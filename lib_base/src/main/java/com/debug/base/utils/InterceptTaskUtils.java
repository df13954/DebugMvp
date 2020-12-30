package com.debug.base.utils;

public class InterceptTaskUtils {
    private static long lastClickTime = 0;

    public static boolean isFastEvent(long interceptionTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < interceptionTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
