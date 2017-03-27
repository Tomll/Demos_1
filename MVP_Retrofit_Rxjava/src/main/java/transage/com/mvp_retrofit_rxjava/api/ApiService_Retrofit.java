package transage.com.mvp_retrofit_rxjava.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import transage.com.mvp_retrofit_rxjava.bean.MovieBean;

/**
 * Created by dongrp on 2016/10/12.
 * Retrofit服务接口（该接口包含项目中的所有服务接口）
 * 这样在这个过程中：Retrofit完成网络请求、RxJava完成异步回调
 */
public interface ApiService_Retrofit {

    //用于获取豆瓣电影Top250的数据
    //结合RxJava将Retrofit返回的Call对象 更换为Observable（便于异步回调），
    @GET("top250")
    Observable<MovieBean> getTopMovie(@Query("start") int start, @Query("count") int count);

}
