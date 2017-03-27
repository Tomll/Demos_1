package transage.com.aidl_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AutoScrollTextView t1;
    private AutoScrollTextView t2;
    private AutoScrollTextView t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (AutoScrollTextView) findViewById(R.id.tv);
        t2 = (AutoScrollTextView) findViewById(R.id.tv2);
        t3 = (AutoScrollTextView) findViewById(R.id.tv3);
        // 设置滚动的速度
        t1.setScrollMode(AutoScrollTextView.SCROLL_SLOW);
        t2.setScrollMode(AutoScrollTextView.SCROLL_NORM);
        t3.setScrollMode(AutoScrollTextView.SCROLL_FAST);
//////////////////////////////////////////////////////////////////////////////////////


        /*//它弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false).setMessage("sdfs").setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.isShowing()

        //sp
        final SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        sp.getBoolean("firstRequest",true);
        editor.putBoolean("firstRequest",false);
        editor.commit();

        //获取起调Activity的信息
        String packageName = this.getCallingActivity().getPackageName();
        if(packageName.equals("")){
            finish();
        }

        //获取方向信息
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_SENSOR){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }*/

    }



}
