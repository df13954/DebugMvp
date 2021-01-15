package com.android.lib.common.utils

import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2

/**
 * @author: 123
 * @date: 2021/1/15
 * @description 基础view的扩展类
 */

//文字加粗
fun TextView.isBold() = let {
    //加粗
    paint.isFakeBoldText = true
}

//移除v2的侧边动效。原来xml的配置中，源码没有对这个属性进行获取：bug
fun ViewPager2.changeToNeverScrollMode() = let {
    //直接清除滑动边缘的波纹效果
    overScrollMode = View.OVER_SCROLL_NEVER
}
fun ViewPager2.changeOverScrollMode(targetOverScrollMode: Int) = let {
    //View.OVER_SCROLL_ALWAYS
    overScrollMode = targetOverScrollMode
}
