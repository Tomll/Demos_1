package transage.com.aidl_client.model;

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
        if ("张三".equals(userName) && "123456".equals(password)) {
            loginListener.loginSuccess();
        } else {
            loginListener.loginFail();
        }
    }

}
