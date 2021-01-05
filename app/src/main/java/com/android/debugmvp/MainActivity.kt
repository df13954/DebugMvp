package com.android.debugmvp

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.debugmvp.databinding.ActivityMainBinding
import com.android.debugmvp.mvp.MVPActivityImpl
import com.android.debugmvp.test.BlankFragment
import com.android.debugmvp.test.BlogLoopActivity
import com.android.debugmvp.test.presenter.EmptyPresenter
import com.debug.framework.mvp.IBasePresenter
import com.hi.dhl.binding.viewbind

class MainActivity : MVPActivityImpl<EmptyPresenter>() {

    private val binding: ActivityMainBinding by viewbind()

    override fun createPresenter(): EmptyPresenter {
        return EmptyPresenter()
    }

    override fun initView() {
        binding.tvMsg.text = "binding 默认2个例子"

    }

    fun openAct(view: View?) {
        startAct(BlogLoopActivity::class.java)
    }

    fun openFra(view: View?) {
        val tran = supportFragmentManager.beginTransaction()
        tran.replace(R.id.fl_container, BlankFragment.newInstance("", ""))
        tran.commitNow()
    }

}