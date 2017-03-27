package transage.com.mvp_retrofit_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import transage.com.mvp_retrofit_rxjava.bean.MovieBean;
import transage.com.mvp_retrofit_rxjava.presenter.UserPresenter;
import transage.com.mvp_retrofit_rxjava.view.UserView;

public class MainActivity extends AppCompatActivity implements UserView {

    @BindView(R.id.top250Button)
    Button top250Button;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.loginButton)
    Button loginButton;

    private UserPresenter userPresenter = new UserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //初始化butterKnife

    }

    @OnClick(R.id.loginButton)//将loginButton的点击事件绑定到登录方法上
    public void login() {
        userPresenter.login(editText1.getText().toString(),editText2.getText().toString());
    }

    @OnClick(R.id.top250Button) //将top250Button的点击事件绑定到这个方法上
    public void requestTop250() {
        userPresenter.top250(1, 10);
    }


    /////////////////////////以下都是UserView.java中的接口回调////////////////////////////////////////////////////////
    @Override
    public void top250Success(MovieBean movieBean) {
        Toast.makeText(this, movieBean.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void top250Fail() {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoginFail() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();

    }


}
