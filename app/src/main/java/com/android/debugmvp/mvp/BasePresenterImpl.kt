package com.android.debugmvp.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.android.debugmvp.rxjava.RxSchedulersHelper
import com.debug.framework.mvp.IBaseModel
import com.debug.framework.mvp.IBasePresenter
import com.debug.framework.mvp.IBaseView
import com.uber.autodispose.AutoDisposeConverter
import sj.mblog.LL
import java.lang.ref.WeakReference
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

abstract class BasePresenterImpl<V : IBaseView?, M : IBaseModel> : IBasePresenter<IBaseView> {
    protected var TAG = ""
    private var lifecycleOwner: LifecycleOwner? = null
    protected lateinit var mModel: M
    private var viewProxy: V? = null
    protected var mViewRef: WeakReference<V?>? = null
    protected abstract fun createModel(): M
    override fun getView(): V? {
        return viewProxy
    }

    override fun setLifecycleOwner(owner: LifecycleOwner) {
        lifecycleOwner = owner
    }

    protected fun <T> bindLifecycle(): AutoDisposeConverter<T> {
        if (null == lifecycleOwner) throw NullPointerException("lifecycleOwner == null")
        return RxSchedulersHelper.bindLifecycle(lifecycleOwner)
    }

    override fun onViewAttached(view: IBaseView) {
        mViewRef = WeakReference(view as V)
        var interfaces = view.javaClass.interfaces
        var found = false
        for (anInterface in interfaces) {
            if (anInterface == IBaseView::class.java) {
                found = true
            }
        }
        //如果没找到,增加base
        if (!found) {
            val infs = Arrays.copyOf(interfaces, interfaces.size + 1)
            infs[interfaces.size] = IBaseView::class.java
            interfaces = infs
        }
        val proxyInstance = Proxy.newProxyInstance(view.javaClass.classLoader, interfaces) { proxy: Any?, method: Method, args: Array<Any?> ->
            if (mViewRef == null || mViewRef?.get() == null) {
                return@newProxyInstance null
            } else {
                mViewRef?.apply {
                    return@newProxyInstance method.invoke(get(), *args)
                }
            }
        }
        viewProxy = proxyInstance as V
        mModel = createModel()
        TAG = this.javaClass.simpleName
    }

    override fun onViewDetached() {
        if (isAttach) {
            mViewRef?.clear()
            mViewRef = null
        }
    }

    protected val isAttach: Boolean
        protected get() = mViewRef != null &&
                mViewRef?.get() != null

    override fun onCreate(owner: LifecycleOwner) {
        LL.e(TAG, "P -> onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        LL.e(TAG, "P -> onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        LL.e(TAG, "P -> onResume")
    }

    override fun onStop(owner: LifecycleOwner) {
        LL.e(TAG, "P -> onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycleOwner = null
        LL.e(TAG, "P -> onDestroy")
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {}
    override fun onPause(owner: LifecycleOwner) {
        LL.e(TAG, "P -> onPause")
    }
}