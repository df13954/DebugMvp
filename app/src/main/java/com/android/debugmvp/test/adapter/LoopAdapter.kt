package com.android.debugmvp.test.adapter

import com.android.debugmvp.R
import com.android.debugmvp.bean.SobLoop
import com.android.debugmvp.utils.ImageHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author: 123
 * @date: 2020/12/31
 * @description 轮播图适配器
 */
class LoopAdapter : BaseQuickAdapter<SobLoop, BaseViewHolder>(R.layout.item_loop_layout) {
    override fun convert(holder: BaseViewHolder, item: SobLoop) {
        holder.setText(R.id.tv_loop_title, item.title)
        holder.setText(R.id.tv_loop_url, item.targetUrl)
        // Glide.with(context)
        //         .load(item.imageUrl)
        //         .into((holder.getView<View>(R.id.iv_loop_cover) as ImageView))
        //替换为lib的使用方式。+ 中间层

        ImageHelper.load(
                holder.getView(R.id.iv_loop_cover),
                item.imageUrl,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        )
    }
}