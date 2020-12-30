package com.iandcode.framework.mvp;

import android.app.Activity;
import android.content.Intent;

/**
 * p层会持有view接口.
 * 在p层可对view层操作的公共行为
 * 父类包含了子类集合的常见的方法,也就是子类常见方法都可以抽象到父类中去
 */
public interface IBaseView {


    //以下几个view配置状态布局操作的.

    //显示无网络状态
    void showNoNetworkView();

    //错误
    void showErrorView();

    //我们业务的内容
    void showContentView();

    //加载中页面
    void showLoadingView();


    Activity getAct();

    void startToActivity(Intent intent);

    void showToast(String msg);

    void showToast(int msg);

    void showToastLong(String msg);

    void showToastLong(int msg);

}
