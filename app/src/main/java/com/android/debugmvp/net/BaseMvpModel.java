package com.android.debugmvp.net;

import com.android.debugmvp.BuildConfig;
import com.android.debugmvp.service.ISobBlogService;
import com.debug.framework.mvp.AbsModel;

/**
 * @author dr
 * @date 2020-05-22
 * @description 提供各种http服务.子类继承此mode, 获得获取数据的功能
 */
public class BaseMvpModel extends AbsModel implements IGlobalRepo {

    @Override
    public ISobBlogService getSobBlogService() {
        return HttpManager.getService(
                ISobBlogService.class,
                BuildConfig.APP_BASE_URL,
                BlogClient.class
        );
    }

    //还可以增加其他服务,只需要扩展IGlobalRepo
}
