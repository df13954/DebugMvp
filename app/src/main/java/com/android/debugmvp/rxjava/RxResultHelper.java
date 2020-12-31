package com.android.debugmvp.rxjava;


import com.android.debugmvp.bean.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @author dr
 * @date 2020-05-22
 * @description rx过滤异常
 */
public class RxResultHelper {

    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return upstream -> upstream
                .flatMap((Function<BaseResponse<T>, ObservableSource<T>>) response -> {
                    //根据自己的业务来定,
                    //如果是成功,就返回数据,当然,如果你自己的业务的字段不一样,可以根据自己的来修改
                    if (response.isSuccess()) {
                        return Observable.just(response.getData());
                    }
                    return Observable.error(new Throwable());
                    // else if (response.getCode() == ExceptionCode.LOGIN_ERROR) {
                    //     //登录问题
                    //     return Observable.error(new ReloginException());
                    // } else if (response.getCode() == ExceptionCode.SERVER_ERROR) {
                    //     //服务器异常
                    //     return Observable.error(new ServerException());
                    // } else {
                    //     //必须把code，等信息往下传递，在下一层observer的时候，再拆开处理，释放code，和msg
                    //     //做业务具体判断
                    //     ApiCommonException e = new ApiCommonException(
                    //             response.getErrorCode(),
                    //             response.getErrorMessage(),
                    //             response.getRemark()
                    //     );
                    //     return Observable.error(e);
                    // }
                    //........可以增加其他异常
                });
    }

    public static <T> ObservableTransformer<BaseResponse, BaseResponse> handleResultEmpty() {
        return upstream -> upstream
                .flatMap((Function<BaseResponse, ObservableSource<BaseResponse>>) response -> {
                    //根据自己的业务来定,
                    //如果是成功,就返回数据,当然,如果你自己的业务的字段不一样,可以根据自己的来修改
                    if (response.isSuccess()) {
                        return Observable.just(response);
                    }
                    return Observable.error(new Throwable());
                    // else if (response.getCode() == ExceptionCode.LOGIN_ERROR) {
                    //     //登录问题
                    //     return Observable.error(new ReloginException());
                    // } else if (response.getCode() == ExceptionCode.SERVER_ERROR) {
                    //     //服务器异常
                    //     return Observable.error(new ServerException());
                    // } else {
                    //     //必须把code，等信息往下传递，在下一层observer的时候，再拆开处理，释放code，和msg
                    //     //做业务具体判断
                    //     ApiCommonException e = new ApiCommonException(
                    //             response.getErrorCode(),
                    //             response.getErrorMessage(),
                    //             response.getRemark()
                    //     );
                    //     return Observable.error(e);
                    // }
                    //........可以增加其他异常
                });
    }

}
