package com.android.debugmvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.debug.framework.mvp.AbsMVPCompatFragment;
import com.debug.framework.mvp.IBasePresenter;
import org.greenrobot.eventbus.EventBus;

public abstract class MvpFragmentImp<P extends IBasePresenter> extends AbsMVPCompatFragment<P> {

    protected String TAG;

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
