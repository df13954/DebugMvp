package com.debug.base.crash;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.debug.base.R;
import com.debug.base.utils.AppDateUtils;
import com.debug.base.utils.AppDeviceUtil;
import com.debug.base.BaseApplication;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CrashReportActivity extends AppCompatActivity {

    TextView mToolBar;
    TextView tvException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_report);
        mToolBar = (TextView) findViewById(R.id.toolBar);
        mToolBar.setText("崩溃日志");
        tvException = (TextView) findViewById(R.id.tv_exception);
        tvException.setMovementMethod(new ScrollingMovementMethod());
        Throwable exception = (Throwable) getIntent().getSerializableExtra("exception");
        final String packageName = getIntent().getStringExtra("packageName");
        if (exception != null) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            }
            stringBuilder
                    .append("=======Crash=======\n")
                    .append(exception.toString()).append("\n")
                    .append("详细信息\n");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            pw.flush();
            stringBuilder.append(sw.toString()).append("\n");
            tvException.setText(stringBuilder);
        }
        findViewById(R.id.btn_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashUtil.launchAppByPackageName(BaseApplication.getContext(), packageName);
                finish();
            }
        });
    }
}
