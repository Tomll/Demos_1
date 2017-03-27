package transage.com.retrofitrxjava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dongrp on 2016/10/12.
 */
public class HttpMethods {

    //BaseUrl：在此baseUrl后面拼接“top250”得到电影列表的访问接口
    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    //超时时间
    private static final int DEFAULT_TIMEOUT = 5;
    //Retrofit对象
    private Retrofit retrofit;
    //ApiService_Retrofit接口的实现类
    private ApiService_Retrofit apiService_retrofit;

    //私有构造
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//1参：超时时间数 2参：单位秒

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //Retrofit通过动态代理的方式创建 服务接口 的实现类
        apiService_retrofit = retrofit.create(ApiService_Retrofit.class);
    }

    //私有静态 创建单例 方法
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //对外提供：获取单例 方法
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param start      起始位置
     * @param count      获取长度
     */
    public void getTopMovie(Subscriber<MovieBean> subscriber, int start, int count) {
        apiService_retrofit.getTopMovie(start, count)  //apiService_retrofit服务接口返回的是一个Observable对象
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
