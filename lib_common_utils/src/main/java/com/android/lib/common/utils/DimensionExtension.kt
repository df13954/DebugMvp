package com.android.lib.common.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Paint
import android.util.TypedValue

//获取屏幕宽像素
fun Context.getWidthPixels() = resources.displayMetrics.widthPixels
//获取屏幕高像素
fun Context.getHeightPixels() = resources.displayMetrics.heightPixels

//dp转pixel
val Float.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

val Int.dp
    get() = this.toFloat().dp

//sp转pixel
val Float.sp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)

val Int.sp: Int
    get() = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics) + 0.5f).toInt()

val Float.textHeight: Float
    get() {
        val paint = Paint()
        paint.textSize = this.sp
        return paint.fontMetrics.descent - paint.fontMetrics.ascent
    }

val Int.textHeight: Int
    get() {
        val paint = Paint()
        paint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)
        return (paint.fontMetrics.descent - paint.fontMetrics.ascent + 0.5f).toInt()
    }
