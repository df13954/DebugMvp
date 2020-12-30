package com.debug.base.crash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.debug.base.utils.AppDateUtils;
import com.debug.base.utils.AppDeviceUtil;
import com.debug.base.utils.AppFileUtil;
import com.debug.base.BaseApplication;
import com.debug.base.FrameworkConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    String packageName = "packageName";

    public CrashHandler(@NonNull String packageName) {
        this.packageName = packageName;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        saveCrashLog(e);
        if (FrameworkConfig.getCrashConfig().showCrashUI) {
            jumpToCrashReportActivity(e);
        }
        Process.killProcess(Process.myPid());
    }

    private void saveCrashLog(Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("-------------------------------------------------------------->>>>\n")
                .append(AppDateUtils.getYMDHMSDate(System.currentTimeMillis())).append("\n")
                .append("=======系统信息=======\n")
                .append(AppDeviceUtil.getDeviceBrand()).append("(").append(AppDeviceUtil.getDeviceModel()).append(")\n")
                .append(AppDeviceUtil.getDeviceBuildVersion()).append("\n");
        String[] deviceAbis = AppDeviceUtil.getDeviceAbis();
        if (deviceAbis != null) {
            for (int i = 0; i < deviceAbis.length; i++) {
                stringBuilder.append("abi").append(i).append(": ").append(AppDeviceUtil.getDeviceAbi()).append("\n");
            }
        }
        stringBuilder
                .append("=======Crash=======\n")
                .append(e.toString()).append("\n")
                .append("详细信息\n");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        stringBuilder.append(sw.toString()).append("\n")
                .append("<<<<--------------------------------------------------------------\n\n\n");
        String dataDir = CrashUtil.getCrashFilePath(packageName);
        try {
            int i = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                i = ContextCompat.checkSelfPermission(BaseApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || i == PackageManager.PERMISSION_GRANTED) {
                AppFileUtil.writeFile(dataDir, stringBuilder.toString(), true);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void jumpToCrashReportActivity(Throwable e) {
        Intent intent = new Intent(BaseApplication.getContext(), CrashReportActivity.class);
        intent.putExtra("exception", e);
        intent.putExtra("packageName", packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        BaseApplication.getContext().startActivity(intent);
    }
}
