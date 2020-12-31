package com.android.debugmvp.net;


import android.util.Log;


import com.android.debugmvp.BuildConfig;
import com.android.debugmvp.DebugApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author dr
 * @date 2020-05-22
 * @description OkHttp管理类
 */
public class HttpManager {

    private static boolean showLog = BuildConfig.DEBUG;
    private static boolean useUnsafeHttps = !BuildConfig.DEBUG;
    private static final HashMap<String, Object> SERVICE_MAP = new HashMap<>();
    public static String[] sslCertPath;
    private static Converter.Factory gsonJsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private static final int DEFAULT_TIMEOUT = 10;

    private static final int DEFAULT_UPLOAD_TIMEOUT = 60;

    private static <T> T createService(Class<T> serviceClass, String baseUrl) {
        Retrofit mRetrofitClient = generateRetrofitClient(baseUrl);
        return mRetrofitClient.create(serviceClass);
    }

    public synchronized static <T> T getService(Class<T> serviceClass, String baseUrl) {
        //缓存
        if (SERVICE_MAP.containsKey(serviceClass.getName())) {
            return (T) SERVICE_MAP.get(serviceClass.getName());
        } else {
            Object obj = createService(serviceClass, baseUrl);
            SERVICE_MAP.put(serviceClass.getName(), obj);
            return (T) obj;
        }
    }

    public synchronized static <T> T getService(Class<T> serviceClass, String baseUrl, Class<? extends ICreateRetrofitClient> createRetrofitClientClass) {
        if (createRetrofitClientClass == null) {
            return getService(serviceClass, baseUrl);
        }
        if (SERVICE_MAP.containsKey(serviceClass.getName())) {
            return (T) SERVICE_MAP.get(serviceClass.getName());
        } else {
            try {
                ICreateRetrofitClient iCreateRetrofitClient = createRetrofitClientClass.newInstance();
                Object obj = iCreateRetrofitClient.createClient(baseUrl).create(serviceClass);
                SERVICE_MAP.put(serviceClass.getName(), obj);
                return (T) obj;
            } catch (InstantiationException e) {
                e.printStackTrace();
                return getService(serviceClass, baseUrl);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return getService(serviceClass, baseUrl);
            }
        }
    }

    public static Retrofit generateRetrofitClient(String baseUrl) {
        //Client配置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (useUnsafeHttps) {
            builder.sslSocketFactory(getSSLSocketFactory());
            builder.hostnameVerifier(getHostnameVerifier());
        } else if (sslCertPath != null) {
            builder.sslSocketFactory(getCertificateFactory(sslCertPath));
        }
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_UPLOAD_TIMEOUT, TimeUnit.SECONDS);
        if (showLog) {
//            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new LoggingInterceptor());
//            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(logInterceptor);
        }
        builder.retryOnConnectionFailure(true);
        builder.cache(new Cache(new File(DebugApplication.getContext().getCacheDir(), ""),
                1024 * 1024 * 100));
        OkHttpClient mApiClient = builder.build();

        return new Retrofit.Builder()
                .client(mApiClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonJsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static TrustManager[] getTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
        return trustAllCerts;
    }

    private static HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        return hostnameVerifier;
    }

    private static KeyManager[] prepareKeyManager(InputStream bksFile, String password) {
        try {
            if (bksFile == null || password == null) {
                return null;
            }

            KeyStore clientKeyStore = KeyStore.getInstance("BKS");
            clientKeyStore.load(bksFile, password.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, password.toCharArray());
            return keyManagerFactory.getKeyManagers();

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SSLSocketFactory getCertificateFactory(String... certificatePaths) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            for (int i = 0; i < certificatePaths.length; i++) {
                String certificateAlias = Integer.toString(i + 1);
                String certificatePath = certificatePaths[i];
                InputStream certificate = DebugApplication.getContext().getAssets().open(certificatePath);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null) {
                        certificate.close();
                    }
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null,
                    trustManagerFactory.getTrustManagers(), new SecureRandom());
            return (sslContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
