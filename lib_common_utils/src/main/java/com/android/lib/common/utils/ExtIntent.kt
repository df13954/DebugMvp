package com.android.lib.common.utils

import android.app.Activity
import android.content.Context

/**
 * @author: 123
 * @date: 2020/8/31
 * @description ext$
 */
inline fun <reified T: Activity> Context.navActivity(vararg params: Pair<String, Any?>) =
        ExtUtils.internalStartActivity(this, T::class.java, params)

