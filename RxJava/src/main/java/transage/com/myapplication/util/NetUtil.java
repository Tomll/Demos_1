package transage.com.aidl_client.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dongrp on 2016/9/28.
 * 网络请求工具类
 */
public class NetUtil {

    //公有并静态的Gson实例，提供给 别的类解析jsonString的时候用
    public static Gson gson = new Gson();

    /**
     * 基于okHttp二次封装的：异步Get请求(调用subscribeOn(Schedulers.io())方法 在Rx内部实现异步)，支持RxJava，返回一个Observable对象
     *
     * @param url    网络请求路径
     * @param tClass 请求返回的json数据对应JavaBean对象
     * @return 返回的 Observable对象
     */
    public static Observable getAsyncOkHttp(final String url, final Class<?> tClass) {
        //返回Observable对象
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                //在call方法中执行网络请求
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).get().build();
                try {
                    Response response = client.newCall(request).execute(); //执行同步Get请求
                    if (response.isSuccessful()) {
                        final Object obj = gson.fromJson(response.body().string(), tClass);
                        subscriber.onNext(obj);
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()) // subscribe()在 IO 线程：子线程
                .observeOn(AndroidSchedulers.mainThread()); // 结果回调 在主线程
        // 通过设置以上这两步就可以使用同步网络请求了，RxJava会自动将同步的网络请求放到IO线程中执行
    }


    /**
     * 基于okHttp二次封装的：异步Get请求，支持RxJava，返回一个Observable对象
     *
     * @param url    网络请求路径
     * @param tClass 请求返回的json数据对应JavaBean对象
     * @return 返回的 Observable对象
     */
    /*public static Observable getAsyncOkHttp(final String url, final Class<?> tClass) {
        //返回Observable对象
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                //在call方法中执行网络请求
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).get().build();
                client.newCall(request).enqueue(new Callback() { //执行异步Get请求
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        //response是网络请求子线程中的变量，直接放在Handler(Looper.getMainLooper())中会报NetworkOnMainThreadException
                        final Object obj = gson.fromJson(response.body().string(), tClass);
                        //因为onResponse()非UI线程，所以需要创建一个主线程的Handler通过post()方法执行主线程中逻辑
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(obj);
                                subscriber.onCompleted();
                            }
                        });
                    }
                });
            }
        });
    }*/




    /**
     * 基于okHttp二次封装的：异步Post请求方法（无泛型）
     *
     * @param url         请求路径
     * @param json        请求参数（json格式）
     * @param handler     主线程中接收响应结果的handler
     * @param requestCode 用于区分主线程中的多个不同网络请求 的请求码
     */
    public static void okhttpAsyncPost(String url, String json, final Handler handler, final int requestCode) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call arg0, IOException arg1) {

            }

            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                if (handler != null) { // 有的网络请求 不需要处理响应结果，所以存在 handler == null的情况
                    Message message = Message.obtain();
                    message.arg1 = requestCode;
                    message.obj = response.body().string();
                    handler.sendMessage(message);
                }
            }
        });
    }

    /**
     * 基于okHttp二次封装的：异步Post请求方法（有泛型）
     *
     * @param <T>                           服务器返回的json对应的javaBean
     * @param url:请求路径
     * @param json:请求参数（json格式）
     * @param handler:主线程的handler
     * @param requestCode:用于区分主线程中的多个不同网络请求 的请求码
     */
    public static <T> void okhttpAsyncPost(String url, String json, final Class<T> tClass, final Handler handler, final int requestCode) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call arg0, IOException arg1) {

            }

            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                T t = new Gson().fromJson(response.body().string(), tClass);
                Message message = Message.obtain();
                message.arg1 = requestCode;
                message.obj = t;
                handler.sendMessage(message);
            }
        });
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
