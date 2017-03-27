package transage.com.mvp_retrofit_rxjava.model;

import rx.Subscriber;
import transage.com.mvp_retrofit_rxjava.util.HttpMethods;
import transage.com.mvp_retrofit_rxjava.bean.MovieBean;

/**
 * Created by dongrp on 2016/10/9.
 *
 * UserModel接口的实现类：登录逻辑在此类执行
 */
public class UserModelImpl implements UserModel {

    //实际的登录逻辑在此执行
    @Override
    public void login(final String userName, final String password, final LoginListener loginListener) {
        //模拟登录
        if ("123".equals(userName) && "123".equals(password)) {
            loginListener.loginSuccess();
        } else {
            loginListener.loginFail();
        }
    }

    @Override
    public void top250(int start, int count, final Top250Listener top250Listener) {
        //创建 观察者 对象
        Subscriber<MovieBean> subscriber = new Subscriber<MovieBean>() {
            @Override
            public void onCompleted() {
                //提示加载完成
                //Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                top250Listener.top250Fail();
            }

            @Override
            public void onNext(MovieBean movieBean) {
                top250Listener.top250Success(movieBean);
            }
        };

        //获取豆瓣电影Top250的数据
        HttpMethods.getInstance().getTopMovie(subscriber,start,count);
    }

}
