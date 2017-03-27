package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dongrp on 2016/7/22.
 * Retrofit turns your HTTP API into a Java interface.
 * 创建接口，声明GitHub的API
 */

public interface GitHubAPI {

    /*
      请求该接口：https://api.github.com/users/baiiu
    */
    @GET("users/{user}")
    Call<User> userInfo(@Path("user") String user);

}
