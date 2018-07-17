package com.example.asus.huangxiaoer.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.view.adapter.ViewPagerAdapter;
import com.example.asus.huangxiaoer.view.fragment.HomeFragment;
import com.example.asus.huangxiaoer.view.fragment.MineFragment;
import com.example.asus.huangxiaoer.view.fragment.SubscribeFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus.huangxiaoer.R.drawable.mine2;
import static com.example.asus.huangxiaoer.R.drawable.subscribe2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener/*, ViewPager.OnPageChangeListener*/ {
    private SharedPreferences sharedPreferences;
    private ImageView home_subscribe;
    private ImageView home_mine;
    private Button home_btn_scan;
    private ViewPager home_viewpager;
    private ArrayList<Fragment> fragments;
    private ViewPagerAdapter adapter;
    private FrameLayout home_scan;
    private long firstClickTime;


    long mLastTime = 0;
    long mCurTime = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    // Toast.makeText(MainActivity.this,"这是单击事件",Toast.LENGTH_LONG).show();
                    home_viewpager.setVisibility(View.GONE);
                    home_scan.setVisibility(View.VISIBLE);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment fragment = new HomeFragment();
                    ft.replace(R.id.home_scan, fragment);
                    ft.commit();
                    break;
                case 2:
                    //Toast.makeText(MainActivity.this,"这是双击事件",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(MainActivity.this, MainExcessiceActivity.class);
                    in.putExtra("sao", "sao");
                    startActivity(in);

                    break;
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        home_viewpager = (ViewPager) findViewById(R.id.home_viewpager);
        home_scan = (FrameLayout) findViewById(R.id.home_scan);
        home_mine = (ImageView) findViewById(R.id.home_mine);
        home_subscribe = (ImageView) findViewById(R.id.home_subscribe);
        home_btn_scan = (Button) findViewById(R.id.home_btn_scan);

        Intent intent2 = getIntent();
        String ziliao = intent2.getStringExtra("ziliao");
        //Log.e("back", "onCreate: "+ziliao );
        if (ziliao == "back") {
            home_scan.setVisibility(View.GONE);
            home_viewpager.setVisibility(View.VISIBLE);
            home_viewpager.setCurrentItem(1);
            home_subscribe.setImageResource(R.drawable.subscribe1);
            home_mine.setImageResource(R.drawable.mine2);
        }


        /*双击事件*/
        home_btn_scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mLastTime = mCurTime;
                mCurTime = System.currentTimeMillis();
                if (mCurTime - mLastTime < 300) {//双击事件
                    mCurTime = 0;
                    mLastTime = 0;
                    handler.removeMessages(1);
                    handler.sendEmptyMessage(2);
                } else {//单击事件
                    handler.sendEmptyMessageDelayed(1, 310);
                }

            }
        });





        /*从过渡页面过来   进入了 Home 页面*/
        Intent intent1 = getIntent();
        String a = intent1.getStringExtra("a");
        if (a != null) {
            home_viewpager.setVisibility(View.GONE);
            home_scan.setVisibility(View.VISIBLE);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = new HomeFragment();
            ft.replace(R.id.home_scan, fragment);
            ft.commit();
        }




        /*
         * 判断APP  是否 第一次进入  使用引导页
         */
        sharedPreferences = getSharedPreferences("count", MODE_PRIVATE);
        int count = sharedPreferences.getInt("count", 0);
        //Log.d("print", String.valueOf(count));
        if (count == 0) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), GuideActivity.class);
            startActivity(intent);
            this.finish();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count", ++count);
        editor.commit();




        /*
         * 点击按钮  切换 Fragment  页面
         */

        home_viewpager.setOnClickListener(this);
        home_mine.setOnClickListener(this);
        home_subscribe.setOnClickListener(this);
        //  home_btn_scan.setOnClickListener(this);

        fragments = new ArrayList<Fragment>();
        fragments.add(new SubscribeFragment());
        fragments.add(new MineFragment());

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        home_viewpager.setAdapter(adapter);


        home_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               // Log.e("123", "onPageSelected: "+position);
                // home_subscribe.setSelected(false);
                // home_mine.setSelected(false);
                switch (position) {
                    case R.id.home_subscribe:
                        home_viewpager.setCurrentItem(0);
                    case 0:
                        home_subscribe.setImageResource(R.drawable.subscribe2);
                        home_mine.setImageResource(R.drawable.mine1);
                        break;
                    case R.id.home_mine:
                        home_viewpager.setCurrentItem(1);
                    case 1:
                        home_subscribe.setImageResource(R.drawable.subscribe1);
                        home_mine.setImageResource(R.drawable.mine2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    /*按钮点击*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_subscribe:
                home_scan.setVisibility(View.GONE);
                home_viewpager.setVisibility(View.VISIBLE);

                home_viewpager.setCurrentItem(0);
                home_subscribe.setImageResource(R.drawable.subscribe2);
                home_mine.setImageResource(R.drawable.mine1);
                break;

            case R.id.home_mine:
                home_scan.setVisibility(View.GONE);
                home_viewpager.setVisibility(View.VISIBLE);
                home_viewpager.setCurrentItem(1);
                home_subscribe.setImageResource(R.drawable.subscribe1);
                home_mine.setImageResource(R.drawable.mine2);
                break;


/*            case R.id.home_btn_scan:
                home_viewpager.setVisibility(View.GONE);
                home_scan.setVisibility(View.VISIBLE);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new HomeFragment();
                ft.replace(R.id.home_scan, fragment);
                ft.commit();
                break;*/


        }

    }


}
