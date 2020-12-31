package com.android.debugmvp.net;

import retrofit2.Retrofit;

public interface ICreateRetrofitClient {
    Retrofit createClient(String baseUrl);
}
