package transage.com.mvp_retrofit_rxjava.presenter;

import transage.com.mvp_retrofit_rxjava.bean.MovieBean;
import transage.com.mvp_retrofit_rxjava.model.LoginListener;
import transage.com.mvp_retrofit_rxjava.model.Top250Listener;
import transage.com.mvp_retrofit_rxjava.model.UserModelImpl;
import transage.com.mvp_retrofit_rxjava.view.UserView;

/**
 * Created by dongrp on 2016/10/9.
 * UserPresenter的作用：连接View层 与 Model层 的桥梁：持有View 和 Model的对象引用，调用Model中的方法执行主业务逻辑，并通过LoginListener接口
 * 将结果回调回来（这是与Model层的交互逻辑）；因为Activity已经实现了View接口，所以再通过View接口对象将结果回调给Activity（这是与View层的交互逻辑）；
 */
public class UserPresenter {

    private UserView userView;
    private UserModelImpl UserModelImpl;


    public UserPresenter(UserView userView) {
        this.UserModelImpl = new UserModelImpl();
        this.userView = userView;
    }


    public void login(String userName, String password) {
        //调用Model中的方法执行主业务逻辑，并通过LoginListener接口将结果回调回来（这是与Model层的交互逻辑）
        UserModelImpl.login(userName, password, new LoginListener() {
            @Override
            public void loginSuccess() {
                //因为Activity已经实现了View接口，所以再通过View接口对象将结果回调给Activity（这是与View层的交互逻辑）
                userView.showLoginSuccess();
            }

            @Override
            public void loginFail() {
                userView.showLoginFail();
            }
        });
    }

    public void top250(int start, int count) {
        UserModelImpl.top250(start, count, new Top250Listener() {
            @Override
            public void top250Success(MovieBean movieBean) {
                userView.top250Success(movieBean);
            }

            @Override
            public void top250Fail() {
                userView.top250Fail();
            }
        });
    }


}
