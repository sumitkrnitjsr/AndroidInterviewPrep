package com.maskedgeek.advancedinterviewprep.retrofit.data.remote;

import com.maskedgeek.androidinterviewprep.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Networking {
        public static final String HEADER_API_KEY = "x-api-key";
        public static final String HEADER_ACCESS_TOKEN = "x-access-token";
        public static final String HEADER_USER_ID = "x-user-id";

        public static int  NETWORK_CALL_TIMEOUT = 60;
        public static String API_KEY;

        public static NetworkService2 create(String apiKey, String baseURL, File cacheDIR,Long cacheSize) {
                API_KEY = apiKey;
                HttpLoggingInterceptor.Level loggingLevel = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                                                                                : HttpLoggingInterceptor.Level.NONE;

                return new Retrofit.Builder().baseUrl(baseURL)
                                .client(
                                        new OkHttpClient.Builder().cache(new Cache(cacheDIR, cacheSize))
                                        .addInterceptor(new HttpLoggingInterceptor().setLevel(loggingLevel))
                                        .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                                                .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                                        .build()
                                ).addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                .build()
                                .create(NetworkService2.class);

        }

}
