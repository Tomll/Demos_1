package transage.com.aidl_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import transage.com.aidl_client.presenter.UserPresenter;
import transage.com.aidl_client.view.UserView;

/**
 * 这里将MainActivity被视为View， 仅负责数据的展示，并且将用户的操作事件路由给P去做处理
 */
public class MainActivity extends AppCompatActivity implements UserView {
    private EditText edit1;
    private EditText edit2;
    private Button loginButton;
    private UserPresenter userPresenter = new UserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = edit1.getText().toString();
                String password = edit2.getText().toString();
                // View将用户的点击事件直接路由给Presenter区处理
                userPresenter.login(userName, password);
            }
        });
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
