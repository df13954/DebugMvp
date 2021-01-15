package com.android.lib.common.utils

import android.view.View
import android.widget.Checkable

/***
 * 带延迟过滤的点击事件View扩展
 * @param delay Long 延迟时间，默认600毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
inline fun <T : View> T.singleClick(time: Long = 800, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val curTime = System.currentTimeMillis()
        if (curTime - lastClickTime > time || this is Checkable) {
            lastClickTime = curTime
            block(this)
        }
    }
}

//兼容点击事件设置为this的情况
fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 800) {
    setOnClickListener {
        val curTime = System.currentTimeMillis()
        if (curTime - lastClickTime > time || this is Checkable) {
            lastClickTime = curTime
            onClickListener.onClick(this)
        }
    }
}

var <T : View> T.lastClickTime: Long
    set(value) = setTag(R.id.single_click_tag, value)
    get() = getTag(R.id.single_click_tag) as? Long ?: 0