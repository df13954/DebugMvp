package com.android.debugmvp.test

import com.android.debugmvp.databinding.ActivityPermissionTestBinding
import com.android.debugmvp.mvp.MVPActivityImpl
import com.android.debugmvp.test.presenter.EmptyPresenter
import com.android.debugmvp.utils.AppToast.toast
import com.android.lib.common.utils.singleClick
import com.hi.dhl.binding.viewbind
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions


class PermissionTestActivity : MVPActivityImpl<EmptyPresenter>() {

    private val vb: ActivityPermissionTestBinding by viewbind()

    override fun createPresenter(): EmptyPresenter {
        return EmptyPresenter()
    }

    override fun initView() {
        vb.btnExtStore.singleClick {
            XXPermissions.with(this)
                    // 不适配 Android 11 可以这样写
                    //.permission(Permission.Group.STORAGE)
                    // 适配 Android 11 需要这样写，这里无需再写 Permission.Group.STORAGE
                    .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                    .permission(Permission.RECORD_AUDIO)
                    .request(object : OnPermissionCallback {
                        override fun onGranted(permissions: List<String>, all: Boolean) {
                            if (all) {
                                toast("获取存储权限成功")
                            }
                        }

                        override fun onDenied(permissions: List<String>, never: Boolean) {
                            if (never) {
                                toast("被永久拒绝授权，请手动授予存储权限")
                                // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                XXPermissions.startPermissionActivity(this@PermissionTestActivity, permissions)
                            } else {
                                toast("获取存储权限失败")
                            }
                        }
                    })
        }
    }

}