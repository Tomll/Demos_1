package transage.com.android_interaction_javascrpipt;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Android应用中java 与 javaScript的交互
 */
public class MainActivity extends AppCompatActivity {
    private WebView myWebView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.myWebView);
        if (myWebView != null) {
            WebSettings settings = myWebView.getSettings();
            settings.setJavaScriptEnabled(true);//表示webview可以执行服务器端的js代码
        }
        //添加JS接口，“control”为注入接口的名称
        myWebView.addJavascriptInterface(new JsInteration(), "control");
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) { //在页面加载完成的时候调用js中的方法
                super.onPageFinished(view, url);
                testMethod(myWebView);
            }
        });
        //加载本地assets资源目录中的html页面
        myWebView.loadUrl("file:///android_asset/js_java_interaction.html");

    }


    /**
     * Java 调用 JavaScript中的方法
     */
    private void testMethod(WebView webView) {
        String call;
        //call = "javascript:sayHello()";
        //call = "javascript:alertMessage(\"" + "content" + "\")";
        //call = "javascript:toastMessage(\"" + "content" + "\")";
        call = "javascript:sumToJava(1,2)";
        webView.loadUrl(call);
    }

    /**
     * Java调用JavaScript方法的结果回调
     */
    public class JsInteration {

        @JavascriptInterface
        public void callUp(String message) {
            // 检查并申请CALL_PHONE权限
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);// 申请打电话权限
                return;
            }
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + message));
            startActivity(intent);
        }

        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            Toast.makeText(getApplicationContext(), "onSumResult result=" + result, Toast.LENGTH_LONG).show();
        }
    }


}
