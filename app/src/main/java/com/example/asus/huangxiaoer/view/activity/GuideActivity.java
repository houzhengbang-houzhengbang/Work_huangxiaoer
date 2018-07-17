package com.example.asus.huangxiaoer.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.view.adapter.GuidePageAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ImageButton ib_start;
    private SimpleDraweeView img;


    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        initViewPager();
    }


    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_viewpager);

        imageIdArray = new int[]{R.drawable.guideone, R.drawable.guidetwo, R.drawable.guidethree};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView image = new ImageView(this);
            image.setLayoutParams(params);
            image.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(image);
        }

        View inflater=getLayoutInflater().inflate(R.layout.start,null);

        viewList.add(inflater);

        img = inflater.findViewById(R.id.guide_simple_img);

        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.welcome_gif))
                .build();

        img.setController(mDraweeController);


        ib_start = inflater.findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
            }
        });

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length) {
            img.setVisibility(View.VISIBLE);
            ib_start.setVisibility(View.VISIBLE);
        } else {
         ib_start.setVisibility(View.GONE);
         img.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}