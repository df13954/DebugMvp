package com.android.lib.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * @author: 123
 * @date: 2020/8/31
 * @description ext$
 */

object ExtUtils {
    @JvmStatic
    fun internalStartActivity(
            ctx: Context,
            activity: Class<out Activity>,
            params: Array<out Pair<String, Any?>>
    ) {
        ctx.startActivity(createIntent(ctx, activity, params))
    }
    @JvmStatic
    fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent {
        return Intent(ctx, clazz)
    }

}