package com.android.debugmvp

import android.view.View
import com.android.debugmvp.databinding.ActivityMainBinding
import com.android.debugmvp.mvp.MVPActivityImpl
import com.android.debugmvp.test.BlankFragment
import com.android.debugmvp.test.BlogLoopActivity
import com.android.debugmvp.test.PermissionTestActivity
import com.android.debugmvp.test.presenter.EmptyPresenter
import com.android.debugmvp.utils.AppToast
import com.android.lib.common.utils.navActivity
import com.android.lib.common.utils.singleClick
import com.hi.dhl.binding.viewbind

class MainActivity : MVPActivityImpl<EmptyPresenter>() {

    private val binding: ActivityMainBinding by viewbind()

    override fun createPresenter(): EmptyPresenter {
        return EmptyPresenter()
    }

    override fun initView() {
        binding.tvMsg.text = "binding 默认2个例子"

        //进入权限申请页面
        binding.btnPermission.singleClick {
            navActivity<PermissionTestActivity>()
        }
    }

    fun openAct(view: View?) {
        startAct(BlogLoopActivity::class.java)
    }

    fun openFra(view: View?) {
        AppToast.toast("打开fragment")
        val tran = supportFragmentManager.beginTransaction()
        tran.replace(R.id.fl_container, BlankFragment.newInstance("", ""))
        tran.commitNow()
    }

}