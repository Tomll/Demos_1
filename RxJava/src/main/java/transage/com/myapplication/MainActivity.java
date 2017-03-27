package transage.com.aidl_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import transage.com.aidl_client.bean.CarBean;
import transage.com.aidl_client.util.NetUtil;

public class MainActivity extends AppCompatActivity {
    //网络请求接口example
    private static final String URL = "http://221.228.238.72:8888/fert_bbc/getSomeArea.htm?parentId=2222222";
    @Bind(R.id.textView1)
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //基于OkHttp 和 RxJava 封装的网络请求方法
        //通过NetUtil.getAsyncOkHttp()方法，返回 Observable 被观察者
        Observable myObservable = NetUtil.getAsyncOkHttp(URL, CarBean.class);
        //创建Action1类：不关心Subscriber的OnComplete和OnError，我们只需要在onNext的时候做一些处理，这时候就可以使用Action1类。
        Action1<Object> onNextAction = new Action1<Object>() {
            @Override
            public void call(Object object) {
                CarBean carBean = (CarBean) object;
                Toast.makeText(MainActivity.this, carBean.toString(), Toast.LENGTH_SHORT).show();
                textView1.setText(carBean.toString());
            }
        };
        //建立订阅关系
        myObservable.subscribe(onNextAction);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this); //ButterKnife解除绑定
    }


}







/*       //RxJava 基本使用方法
        //创建 Rx 被观察者
        Observable myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world !");
                subscriber.onCompleted();
            }
        });
        //创建 Rx 观察者
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        };
        //建立订阅关系
        myObservable.subscribe(mySubscriber);*/