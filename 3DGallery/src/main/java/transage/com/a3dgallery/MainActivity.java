package transage.com.a3dgallery;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> listImage = new ArrayList<>();
    @Bind(R.id.viewPager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BUtterKnife绑定
        ButterKnife.bind(this);
        //1.初始化数据
        initData();

        //2.实现3D画廊效果需要的配置
        viewPager.setOffscreenPageLimit(3);
        int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        viewPager.setLayoutParams(lp);
        viewPager.setPageMargin(-50);
        //解决触摸滑动ViewPager左右两边的页面无反应的问题：将父控件的触摸事件传递给viewPager处理
        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });
        //给viewPager设置MyPagerTransformer
        viewPager.setPageTransformer(true, new MyPagerTransformer());

        //3.绑定适配器
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listImage.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = listImage.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(listImage.get(position));
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initData() {
        ImageView image1 = new ImageView(this);
//        image1.setImageBitmap(ImageUtil.getReverseBitmapById(R.mipmap.pg1, this));
        image1.setImageResource(R.mipmap.pg1);
        listImage.add(image1);
        ImageView image2 = new ImageView(this);
//        image2.setImageBitmap(ImageUtil.getReverseBitmapById(R.mipmap.pg2, this));
        image2.setImageResource(R.mipmap.pg2);
        listImage.add(image2);
        ImageView image3 = new ImageView(this);
//        image3.setImageBitmap(ImageUtil.getReverseBitmapById(R.mipmap.pg3, this));
        image3.setImageResource(R.mipmap.pg3);
        listImage.add(image3);
        ImageView image4 = new ImageView(this);
//        image4.setImageBitmap(ImageUtil.getReverseBitmapById(R.mipmap.pg4, this));
        image4.setImageResource(R.mipmap.pg4);
        listImage.add(image4);
        ImageView image5 = new ImageView(this);
//        image5.setImageBitmap(ImageUtil.getReverseBitmapById(R.mipmap.pg5, this));
        image5.setImageResource(R.mipmap.pg5);
        listImage.add(image5);
    }


}
