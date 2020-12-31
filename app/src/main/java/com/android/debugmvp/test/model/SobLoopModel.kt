package com.android.debugmvp.test.model

import com.android.debugmvp.bean.BaseResponse
import com.android.debugmvp.bean.SobLoop
import com.android.debugmvp.net.BaseMvpModel
import com.android.debugmvp.test.contract.SobLoopContract
import io.reactivex.Observable

/**
 * @author: 123
 * @date: 2020/12/31
 * @description $
 */
class SobLoopModel : BaseMvpModel(), SobLoopContract.Model {
    override fun getSobLoop(): Observable<BaseResponse<List<SobLoop>>> {
        return sobBlogService.sobLoop
    }
}