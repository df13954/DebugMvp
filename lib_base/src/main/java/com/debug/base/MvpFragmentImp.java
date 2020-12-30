package com.debug.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.debug.framework.mvp.AbsMVPCompatFragment;
import com.debug.framework.mvp.IBasePresenter;
import com.ns.yc.ycstatelib.OnNetworkListener;
import com.ns.yc.ycstatelib.OnRetryListener;
import com.ns.yc.ycstatelib.StateLayoutManager;

import org.greenrobot.eventbus.EventBus;

public abstract class MvpFragmentImp<P extends IBasePresenter> extends AbsMVPCompatFragment<P> {

    protected String TAG;
    //private Unbinder unbinder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TAG = this.getClass().getSimpleName();
        if (registerEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
        initView();
        initData(getArguments());
    }

    protected boolean registerEventBus() {
        return false;
    }


    @Override
    protected StateLayoutManager initStatusLayout() {
        //这个配置,可以直接在base中构建.默认配置.当有需要的时候,子类可以重新new config覆盖
        mStatusLayoutManager = StateLayoutManager.newBuilder(getContext())
                .contentView(getLayoutId())
                .emptyDataView(getEmptyLayout())
                //也可以是app模块自己定制
                .errorView(getErrorLayout())
                .loadingView(getLoadingLayout())
                .netWorkErrorView(getNetWorkLayout())
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        onRefreshClick();
                    }
                })
                .onNetworkListener(new OnNetworkListener() {
                    @Override
                    public void onNetwork() {
                        //这里,可以设置网络,也可以重新请求数据等业务
                        onNetworkClick();
                    }
                })
                .build();
        mStatusLayoutManager.showContent();
        return mStatusLayoutManager;
    }

    /**
     * 子类如果需要更改空页面布局,直接复写方法,下面几个方法同理
     *
     * @return getEmptyLayout
     */
    protected int getEmptyLayout() {
        return R.layout.activity_emptydata;
    }

    protected int getErrorLayout() {
        return R.layout.activity_error;
    }

    protected int getLoadingLayout() {
        return R.layout.activity_loading;
    }

    protected int getNetWorkLayout() {
        return R.layout.activity_networkerror;
    }

    /**
     * 我们页面布局
     *
     * @return
     */
    public abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }

    @Override
    public void showToast(String msg) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showToastLong(int msg) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, "" + getString(msg), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToast(int msg) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, "" + getString(msg), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToastLong(String msg) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
        }
    }
}
