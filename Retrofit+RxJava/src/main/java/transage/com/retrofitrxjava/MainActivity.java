package transage.com.retrofitrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);

        //创建 观察者 对象
        Subscriber<MovieBean> subscriber = new Subscriber<MovieBean>() {
            @Override
            public void onCompleted() {
                //提示加载完成
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MovieBean movieBean) {
                textView.setText(movieBean.toString());
            }
        };

        //获取豆瓣电影Top250的数据
        HttpMethods.getInstance().getTopMovie(subscriber,0,10);

    }
}
