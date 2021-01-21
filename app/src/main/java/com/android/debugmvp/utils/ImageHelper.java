package com.android.debugmvp.utils;

import android.widget.ImageView;

import com.debug.imageload.ImageLoader;

/**
 * @author: dr
 * @date: 2021/1/20
 * @description 中间增加一层
 */
public class ImageHelper {
    /**
     * 基础加载，最简单
     *
     * @param iv
     * @param url
     */
    public static void load(ImageView iv, String url) {
        ImageLoader.getInstance()
                .load(url)
                .into(iv);
    }

    public static void loadGif(ImageView iv, int res) {
        ImageLoader.getInstance()
                .loadGif(res)
                .into(iv);
    }

    /**
     * 占位符
     *
     * @param iv
     * @param url
     * @param placeholder
     */
    public static void load(ImageView iv, String url, int placeholder) {
        ImageLoader.getInstance()
                .load(url)
                .placeholder(placeholder)
                .into(iv);
    }

    /**
     * 占位符+错误
     *
     * @param iv
     * @param url
     * @param placeholder
     * @param error
     */
    public static void load(ImageView iv, String url, int placeholder, int error) {
        ImageLoader.getInstance()
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(iv);
    }
}