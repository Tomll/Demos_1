package transage.com.aidl_client.model;

/**
 * Created by dongrp on 2016/10/9.
 */
public interface UserModel {

    void login(String userName,String password,LoginListener loginListener);
}

