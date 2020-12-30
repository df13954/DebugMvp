package com.debug.base;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;

import com.debug.base.crash.CrashHandler;
import com.debug.base.crash.CrashUtil;
import com.debug.base.utils.AppDeviceUtil;

import java.io.File;

/**
 * @author dr
 * @date 2020-05-22
 * @description 中间层, 需要增加工具, 或者增加公用功能可以在这module增加
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (FrameworkConfig.getCrashConfig().crashCatch) {
            if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof CrashHandler)) {
                String processName = AppDeviceUtil.getProcessName(Process.myPid());
                if (!TextUtils.isEmpty(processName)) {
                    int i = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (i == PackageManager.PERMISSION_GRANTED) {
                        File file = new File(CrashUtil.getCrashFilePath(processName));
                        if (file.exists() && file.length() > FrameworkConfig.getCrashConfig().crashFileMaxSize) {
                            file.delete();
                        }
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(processName));
                }
            }
        }
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
