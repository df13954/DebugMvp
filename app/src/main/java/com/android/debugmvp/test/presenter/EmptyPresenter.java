package com.android.debugmvp.test.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.debug.framework.mvp.IBasePresenter;
import com.debug.framework.mvp.IBaseView;


public class EmptyPresenter implements IBasePresenter {
    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onStart(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }

    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onStop(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {

    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onViewAttached(IBaseView view) {

    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public IBaseView getView() {
        return null;
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner owner) {

    }
}
