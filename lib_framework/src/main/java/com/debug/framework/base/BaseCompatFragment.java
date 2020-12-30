package com.debug.framework.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.debug.framework.R;
import com.ns.yc.ycstatelib.StateLayoutManager;



/**
 * @author dr
 * @date 2020-05-22
 * @description fragment待扩展, 扩展和act同理
 */
public abstract class BaseCompatFragment extends Fragment {
    //方便在父类控制状态.子类中可能会调用管理器的方法.
    protected StateLayoutManager mStatusLayoutManager;
    protected View mRootView ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_base_view, container, false);
        mRootView = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //子类需要构造一个管理器
        mStatusLayoutManager = initStatusLayout();
        initBaseView(view);
    }

    private void initBaseView(View view) {
        if (mStatusLayoutManager != null) {
            FrameLayout ll_main = (FrameLayout) view.findViewById(R.id.ll_main);
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

    protected abstract StateLayoutManager initStatusLayout();

    protected abstract void initView();

    protected abstract void onRefreshClick();

    protected abstract void onNetworkClick();


}
