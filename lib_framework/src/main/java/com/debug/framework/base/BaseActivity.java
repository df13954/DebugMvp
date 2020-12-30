package com.debug.framework.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.debug.framework.R;
import com.ns.yc.ycstatelib.StateLayoutManager;


public abstract class BaseActivity extends AppCompatActivity {

    //方便在父类控制状态.子类中可能会调用管理器的方法.
    protected StateLayoutManager mStatusLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //所有act的父布局
        setContentView(R.layout.activity_base_view);
        mStatusLayoutManager = initStatusLayout();//子类需要构造一个管理器
        initBaseView();

    }

    protected abstract boolean isFullScreen();

    protected abstract StateLayoutManager initStatusLayout();



    protected abstract void onRefreshClick();

    protected abstract void onNetworkClick();

    private void initBaseView() {
        if (mStatusLayoutManager != null) {
            FrameLayout ll_main = (FrameLayout) findViewById(R.id.ll_main);
            ll_main.addView(mStatusLayoutManager.getRootLayout());
        }
    }

    protected void showContent() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager.showContent();
        }
    }

    protected void showEmptyData() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager.showEmptyData();
        }
    }

    protected void showError() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager.showError();
        }
    }

    protected void showNetWorkError() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager.showNetWorkError();
        }
    }

    protected void showLoading() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager.showLoading();
        }
    }


}
