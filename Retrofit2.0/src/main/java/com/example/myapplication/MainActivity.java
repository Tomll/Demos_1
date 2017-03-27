package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    OkHttpClient client;
    Retrofit retrofit;
    GitHubAPI gitHubAPI;
    Call<User> callUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //访问按钮button
        button = (Button) findViewById(R.id.button);
        //展示访问结果的textView
        textView = (TextView) findViewById(R.id.textView);

        /**
         * 1.初始化OkHttpClient
         */
        client = new OkHttpClient();
        /**
         * 2.创建Retrofit
         */
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(client)
                //设置baseUrl,注意，baseUrl必须后缀"/"
                .baseUrl("https://api.github.com/")
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        /**
         * 2.创建定义好的Interface的实现类：GitHubAPI，采用动态代理的方式 创建接口的实现类
         */
        gitHubAPI = retrofit.create(GitHubAPI.class);
        /**
         * 3.通过调用gitHubAPI接口中的userInfo()方法,获得一个Call<User>）
         */
        callUser = gitHubAPI.userInfo("baiiu");

        //点击button开始异步调用
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 4.通过Call<User> callUser 发起异步请求
                 */
                callUser.enqueue(new Callback<User>() {
                    //请求成功
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User body = response.body(); //直接返回User JavaBean对象
                        textView.setText(body.toString());
                    }

                    //请求失败
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        if (call.isCanceled()) { // 判断是否被cancel掉了
                            Toast.makeText(MainActivity.this, "被Cancel了", Toast.LENGTH_LONG).show();
                        } else {
                            // LogUtil.e(t.toString());
                        }
                    }
                });
            }
        });

    }

}
