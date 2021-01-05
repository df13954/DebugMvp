package com.android.debugmvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.debugmvp.mvp.MVPActivityImpl;
import com.android.debugmvp.test.BlogLoopActivity;
import com.debug.framework.mvp.IBasePresenter;

public class MainActivity extends MVPActivityImpl {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, BlogLoopActivity.class));
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }
}