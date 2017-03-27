package transage.com.mvp_retrofit_rxjava.model;

/**
 * Created by dongrp on 2016/10/9.
 */
public interface UserModel {

    void login(String userName, String password, LoginListener loginListener);

    void top250(int start, int count, Top250Listener top250Listener);

}

