package com.debug.framework.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.debug.framework.base.BaseActivity;


public abstract class AbsMVPActivity<P extends IBasePresenter> extends BaseActivity
        implements IBaseView {

    public P mPresenter;

    @Override
    public Activity getAct() {
        return this;
    }

    @Override
    public void startToActivity(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent 不能为空");
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onViewAttached(this);
            //设置owner
            mPresenter.setLifecycleOwner(this);
            //p实现了owner接口
            getLifecycle().addObserver(mPresenter);
        }

    }

    @Override
    protected boolean isFullScreen() {
        //默认不是全屏,如果有需要,子类复写
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onViewDetached();
        }
    }

    /**
     * 子类必须实现该方法获取Presenter
     *
     * @return 返回该子类所需的Presenter
     */
    protected abstract P createPresenter();


    protected void initView(Bundle savedInstanceState) {

    }

    protected void initData(Intent intent) {

    }

    @Override
    public void showNoNetworkView() {
        showNetWorkError();
    }

    @Override
    public void showErrorView() {
        showError();
    }

    @Override
    public void showContentView() {
        showContent();
    }

    @Override
    public void showLoadingView() {
        showLoading();
    }

    @Override
    protected void onRefreshClick() {

    }

    @Override
    protected void onNetworkClick() {

    }
}
