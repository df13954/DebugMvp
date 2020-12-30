package com.debug.base.utils;

import java.math.BigDecimal;

/**
 * @author: 123
 * @date: 2020/9/5
 * @description 处理数字工具$
 */
public class AppMath {
    public static float getBigDecimalScale(float num,int scale) {
        BigDecimal bigDecimal = new BigDecimal(num);
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            //异常 说明包含非数字。
            return false;
        }
        return true;
    }
}
