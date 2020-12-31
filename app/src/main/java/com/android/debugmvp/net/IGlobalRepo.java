package com.android.debugmvp.net;


import com.android.debugmvp.service.ISobBlogService;
import com.debug.framework.mvp.IBaseModel;

/**
 * @author dr
 * @date 2020-05-22
 * @description 不同URL的接口, 统一管理在这个位置
 */
public interface IGlobalRepo extends IBaseModel {
    //sob blog
    ISobBlogService getSobBlogService();

}
