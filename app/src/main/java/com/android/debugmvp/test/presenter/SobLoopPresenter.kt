package com.android.debugmvp.test.presenter

import com.android.debugmvp.mvp.BasePresenterImpl
import com.android.debugmvp.rxjava.RxResultHelper
import com.android.debugmvp.rxjava.RxSchedulersHelper
import com.android.debugmvp.test.contract.SobLoopContract
import com.android.debugmvp.test.contract.SobLoopContract.Presenter
import com.android.debugmvp.test.model.SobLoopModel

/**
 * @author: 123
 * @date: 2020/12/31
 * @description $
 */
class SobLoopPresenter : BasePresenterImpl<SobLoopContract.View?, SobLoopModel>(), Presenter {
    override fun createModel(): SobLoopModel {
        return SobLoopModel()
    }

    override fun httpGetSobLoop() {
        mModel.sobLoop
                .compose(RxSchedulersHelper.io2Main())
                .compose(RxResultHelper.handleResult())
                .`as`(bindLifecycle())
                .subscribe({ loops -> view?.displayLoop(loops) }) {
                    view?.showToast("request fail")
                }
    }
}