package com.android.debugmvp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.android.debugmvp.utils.GlideLoaderImp;
import com.debug.base.BaseApplication;
import com.debug.imageload.ImageLoader;
import com.hjq.toast.ToastUtils;

/**
 * @author: 123
 * @date: 2020/12/31
 * @description
 */
public class DebugApplication extends BaseApplication {

    public static Handler getHandler() {
        return mHandler;
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Context getContext() {
        return context;
    }

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //toast
        ToastUtils.init(this);
        //初始化glide 如果需要换其他，自行实现ILoaderStrategy
        ImageLoader.getInstance().setGlobalImageLoader(new GlideLoaderImp());
    }
}
