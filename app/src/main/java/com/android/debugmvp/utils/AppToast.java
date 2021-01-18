package com.android.debugmvp.utils;

import android.view.Gravity;

import com.hjq.toast.ToastUtils;

/**
 * @author: dr
 * @date: 2021/1/18
 * @description toast 工具
 */
public class AppToast {

    public static void toast(int msg) {
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 150);
        ToastUtils.show(msg);
    }

    public static void toast(String msg) {
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 150);
        ToastUtils.show(msg);
    }
}
