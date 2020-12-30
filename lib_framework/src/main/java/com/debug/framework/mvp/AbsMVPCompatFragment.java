package com.debug.framework.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;

import com.debug.framework.base.BaseCompatFragment;


public abstract class AbsMVPCompatFragment<P extends IBasePresenter> extends BaseCompatFragment
        implements IBaseView {

    protected P mPresenter;

    @Override
    public Activity getAct() {
        return this.getActivity();
    }

    @Override
    public void startToActivity(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent 不能为空");
        }
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
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

    /**
     * 子类初始化页面方法
     */
    protected void initView() {

    }

    /**
     * 子类初始化数据方法
     *
     * @param bundle 传入的数据，可能为空
     */
    protected void initData(Bundle bundle) {

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
        //显示错误页面后,点击刷新会触发此方法,子类需要可以重写,实现刷新逻辑
    }

    @Override
    protected void onNetworkClick() {
        //无网络显示页面,点击会触发此方法,子类需要可以重写,实现无网络逻辑

    }
}
