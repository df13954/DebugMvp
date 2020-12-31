package com.android.debugmvp.test.contract;

import com.android.debugmvp.bean.BaseResponse;
import com.android.debugmvp.bean.SobLoop;
import com.debug.framework.mvp.IBaseModel;
import com.debug.framework.mvp.IBasePresenter;
import com.debug.framework.mvp.IBaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: 123
 * @date: 2020/12/31
 * @description $
 */
public interface SobLoopContract {

    interface Model extends IBaseModel {
        Observable<BaseResponse<List<SobLoop>>> getSobLoop();
    }

    interface View extends IBaseView {
        void displayLoop(List<SobLoop> loops);
    }

    interface Presenter extends IBasePresenter {
        void httpGetSobLoop();
    }
}
