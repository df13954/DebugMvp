package com.android.debugmvp.test

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.debugmvp.bean.SobLoop
import com.android.debugmvp.databinding.ActivityBlogLoopBinding
import com.android.debugmvp.mvp.MVPActivityImpl
import com.android.debugmvp.test.adapter.LoopAdapter
import com.android.debugmvp.test.contract.SobLoopContract
import com.android.debugmvp.test.presenter.SobLoopPresenter
import com.android.lib.common.utils.dp
import com.debug.base.utils.AppDeviceUtils
import com.hi.dhl.binding.viewbind

class BlogLoopActivity : MVPActivityImpl<SobLoopPresenter>(), SobLoopContract.View {

    private val binding: ActivityBlogLoopBinding by viewbind()
    private lateinit var loopAdapter: LoopAdapter

    override fun createPresenter(): SobLoopPresenter {
        return SobLoopPresenter()
    }

    override fun initView() {
        binding.tvTitle.width = AppDeviceUtils.dip2px(this, 150f)
        binding.tvSubTitle.width = 150.dp.toInt()
        //adapter
        loopAdapter = LoopAdapter()
        binding.rvLoop.layoutManager = LinearLayoutManager(this)
        binding.rvLoop.adapter = loopAdapter
        loopAdapter.setOnItemClickListener { _, _, position ->
            val item = loopAdapter.data[position]
            showToast("${item.title},url:${item.targetUrl}")
        }
        mPresenter?.httpGetSobLoop()
    }

    override fun displayLoop(loops: List<SobLoop>) {
        loopAdapter.setList(loops)
    }

}