package com.android.debugmvp.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.debugmvp.bean.SobLoop
import com.android.debugmvp.databinding.FragmentBlankBinding
import com.android.debugmvp.test.adapter.LoopAdapter
import com.android.debugmvp.test.contract.SobLoopContract
import com.android.debugmvp.test.presenter.SobLoopPresenter
import com.android.debugmvp.mvp.MvpFragmentImp
import com.hi.dhl.binding.viewbind

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BlankFragment : MvpFragmentImp<SobLoopPresenter>(), SobLoopContract.View {
    private var param1: String? = null
    private var param2: String? = null

    private val binding: FragmentBlankBinding by viewbind()

    private lateinit var loopAdapter: LoopAdapter

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BlankFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun initView() {
        loopAdapter = LoopAdapter()
        binding.rvLoop.layoutManager = LinearLayoutManager(context)
        binding.rvLoop.adapter = loopAdapter
        loopAdapter.setOnItemClickListener { _, _, position ->
            val item = loopAdapter.data[position]
            showToast("${item.title},url:${item.targetUrl}")
        }
        mPresenter?.httpGetSobLoop()
    }


    override fun createPresenter(): SobLoopPresenter {
        return SobLoopPresenter()
    }

    override fun displayLoop(loops: MutableList<SobLoop>?) {
        loopAdapter.setList(loops)
    }
}