package com.example.utils;
 
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
 
public class HttpUtils {
    private static HttpUtils httpUtils = new HttpUtils();
 
    private HttpUtils() {
    }
 
    public static HttpUtils getHttpUtils() {
        synchronized (httpUtils){
            if (httpUtils == null){
                httpUtils = new HttpUtils();
            }
        }
        return httpUtils;
    }
    public String get(String url){
 
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())//日志拦截器
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时
                .readTimeout(10,TimeUnit.SECONDS)//读取超时
                .writeTimeout(10,TimeUnit.SECONDS)//写入超时
                .build();
 
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
