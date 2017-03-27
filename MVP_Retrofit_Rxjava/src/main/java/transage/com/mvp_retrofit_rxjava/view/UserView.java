package transage.com.mvp_retrofit_rxjava.view;

import transage.com.mvp_retrofit_rxjava.bean.MovieBean;

/**
 * Created by dongrp on 2016/10/9.
 */
public interface UserView {

    void showLoginSuccess();

    void showLoginFail();

    void top250Success(MovieBean movieBean);

    void top250Fail();
}
