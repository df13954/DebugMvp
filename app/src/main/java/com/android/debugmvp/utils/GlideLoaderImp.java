package com.android.debugmvp.utils;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.android.debugmvp.DebugApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.debug.imageload.ILoaderStrategy;
import com.debug.imageload.LoaderOptions;


/**
 * @author: dr
 * @date: 2021/1/20
 * @description glide的具体实现
 */
public class GlideLoaderImp implements ILoaderStrategy {

    @Override
    public void loadImage(LoaderOptions options) {
        //加载图片的具体实现

        if (options == null) {
            Log.e("glide ->>", "options is null");
            return;
        }
        if (options.targetView == null) {
            Log.e("glide ->>", "image view is null");
            return;
        }

        //处理options
        Object url = null;
        if (!TextUtils.isEmpty(options.url)) {
            url = options.url;
        }

        if (url == null && options.file != null) {
            url = options.file;
        }

        if (url == null && options.uri != null) {
            url = options.uri;
        }

        if (url == null && options.targetBitmap != null) {
            url = options.targetBitmap;
        }

        if (url == null && options.targetByte != null) {
            url = options.targetByte;
        }
        //drawableResId 本地drawable
        if (url == null && options.drawableResId != 0) {
            url = options.drawableResId;
        }

        if (url == null) {
            Log.e("glide ->>", "image url is null");
            return;
        }

        if (options.loadGif) {
            //glide加载gif需要特别配置
            loadGif(options);
            return;
        }

        /*默认的策略是DiskCacheStrategy.AUTOMATIC
        DiskCacheStrategy有五个常量：
        DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
        DiskCacheStrategy.NONE 不使用磁盘缓存
        DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
        DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
        DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
        */
        //处理默认缓存策略
        DiskCacheStrategy cacheStrategy = DiskCacheStrategy.AUTOMATIC;
        if (options.skipNetCache) {
            cacheStrategy = DiskCacheStrategy.NONE;
        }
        Log.i("glide ->>", "options.skipNetCache(disk):" + options.skipNetCache);
        Log.i("glide ->>", "options.skipLocalCache (memory):" + options.skipLocalCache);
        Glide.with(options.targetView.getContext())
                .load(url)
                .placeholder(options.placeholderResId)
                .error(options.errorResId)
                .skipMemoryCache(options.skipLocalCache)
                .diskCacheStrategy(cacheStrategy)
                .into((ImageView) options.targetView);
    }

    /**
     * 加载gif配置和普通的不同
     *
     * @param options options
     */
    private void loadGif(LoaderOptions options) {
        Glide.with(options.targetView.getContext())
                .asGif()
                .load(options.drawableResId)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into((ImageView) options.targetView);
    }

    @Override
    public void clearMemoryCache() {
        // 清理内存缓存
        if (DebugApplication.getContext() != null) {
            Glide.get(DebugApplication.getContext()).clearMemory();
        }
    }

    @Override
    public void clearDiskCache() {
        // 清理磁盘缓存
        if (DebugApplication.getContext() != null) {
            //此方法需要在子线程操作
            Glide.get(DebugApplication.getContext()).clearDiskCache();
        }
    }
}