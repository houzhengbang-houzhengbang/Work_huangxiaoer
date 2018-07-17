package com.example.asus.huangxiaoer.model.util;


import android.util.Log;

import com.example.asus.huangxiaoer.model.api.RetrofitApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private Retrofit retrofit;
    private static RetrofitUtil retrofitUtil;

    private RetrofitUtil() {
    }

    private RetrofitUtil(String baseUrl) {
        //第三方的日志拦截器
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OKhttp3  设置拦截器打印日志
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(logInterceptor)

                .build();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava2平台
                .client(okHttpClient)//OKhttp3添加到Retrofit
                .build();
    }

    //可指定baseUrl
    public static RetrofitUtil getInstance(String baseUrl) {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (null == retrofitUtil) {
                    retrofitUtil = new RetrofitUtil(baseUrl);
                }
            }
        }
        return retrofitUtil;
    }

    //默认的baseUrl
    public static RetrofitUtil getInstance() {
        if (null == retrofitUtil) {
            return getInstance("https://www.zhaoapi.cn/");
        }
        return retrofitUtil;
    }

    //获得Retrofit
    public Retrofit getRetrofit() {
        return retrofit;
    }

    //直接获得RetrofitInterface
    public RetrofitApi getRetrofitInterface() {
        RetrofitApi apiService = retrofit.create(RetrofitApi.class);
        return apiService;
    }

    //日志信息
//    public static class HttpLogger implements HttpLoggingInterceptor.Logger {
//        @Override
//        public void log(String message) {
//            Log.e("myMessage", message);
//        }
//    }
}
