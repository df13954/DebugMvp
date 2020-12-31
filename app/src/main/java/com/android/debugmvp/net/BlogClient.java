package com.android.debugmvp.net;

import android.text.TextUtils;


import com.android.debugmvp.BuildConfig;
import com.android.debugmvp.DebugApplication;
import com.debug.base.utils.RomUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sj.mblog.LL;

/**
 * @author dr
 * @date 2020-05-22
 * @description 针对TestClient的请求
 */
public class BlogClient implements ICreateRetrofitClient {

    private static boolean showLog = BuildConfig.DEBUG;
    private static final String TAG = "BlogClient->>";

    @Override
    public Retrofit createClient(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        if (showLog) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(
                    new BlogClient.LoggingInterceptor2());
            logInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logInterceptor);
//            builder.addInterceptor(
//                    new LoggingInterceptor.Builder()
//                            .setLevel(Level.BASIC)
//                            .tag("->>")
//                            .addHeader("token", SharedPreferencesUtils.init(KidsApplication.getContext())
//                                    .getString(AppConstant.APP_TOKEN))
//                            .addHeader("versionCode", BuildConfig.VERSION_CODE + "")
//                            .addHeader("versionName", BuildConfig.VERSION_NAME)
//                            .addHeader("packageName", BuildConfig.APPLICATION_ID)
//                            .addHeader("brand", RomUtil.getName())
//                            .addHeader("os", RomUtil.getSysVersion() + "")
//                            .addHeader("device", PadUtils.getPlatform())
//                            .addHeader("DeviceType", Build.MODEL)
//                            .log(Platform.INFO)
//                            .build()
//            );
        }
        builder.addInterceptor(new BlogClient.TokenInterceptor());
        builder.retryOnConnectionFailure(true);
        // 缓存设置为 100Mb
        builder.cache(new Cache(new File(DebugApplication.getContext().getCacheDir(), ""),
                1024 * 1024 * 100));
        OkHttpClient mApiClient = builder.build();
        return new Retrofit.Builder()
                .client(mApiClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    private static class LoggingInterceptor2 implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            if (TextUtils.isEmpty(message)) {
                return;
            }
            LL.d(message);
        }
    }

    public static class TokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            // String token = SharedPreferencesUtils.init(getContext())
            //         .getString(AppConstant.APP_TOKEN);
            //LL.e("token : " + token);
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder()
                    //.header("token", token)
                    .addHeader("versionCode", BuildConfig.VERSION_CODE + "")
                    .addHeader("versionName", BuildConfig.VERSION_NAME)
                    .addHeader("packageName", BuildConfig.APPLICATION_ID)
                    .addHeader("brand", RomUtil.getName())
                    .addHeader("os", RomUtil.getSysVersion() + "");
            //.addHeader("device", PadUtils.getPlatform());
            return chain.proceed(builder.build());
        }
    }
}
