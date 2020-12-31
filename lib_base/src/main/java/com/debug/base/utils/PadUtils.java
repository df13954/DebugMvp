package com.debug.base.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;


public class PadUtils {

    public static String getPlatform(Context context) {
        if (isPad(context)) {
            return "androidPad";
        }
        return "android";
    }

    public static boolean isPad(Context context) {
        //最新的华为M5平板JDN2-AL00
        if (Build.MODEL.equals("JDN2-AL00")) {
            return true;
        }
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static int commonDivisor(int m, int n) {
        int temp;
        if (n > m) {
            temp = n;
            n = m;
            m = temp;
        }
        if (m % n == 0) {
            return n;
        }
        return commonDivisor(m - n, n);
    }

    public static String getScreenScale(Context context) {
        int width = AppDeviceUtil.getWidth(context);
        int height = AppDeviceUtil.getHeight(context);
        int divisor = commonDivisor(width, height);
        int w = width / divisor;
        int h = height / divisor;
        return w + ":" + h;
    }
}
