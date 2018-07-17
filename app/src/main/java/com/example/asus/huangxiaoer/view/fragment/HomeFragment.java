package com.example.asus.huangxiaoer.view.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.view.activity.HomeCarActivity;
import com.example.asus.huangxiaoer.view.activity.HomeStoreActivity;
import com.example.asus.huangxiaoer.view.adapter.ViewPagerAdapter;
import com.example.asus.huangxiaoer.view.custom.CustomViewPager;
import com.example.base.BaseFragment;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private TextView home_tab_rexiao;
    private TextView home_tab_zhaopai;
    private TextView home_tab_zhushi;
    private TextView home_tab_xiaochi;
    private TextView home_tab_yinping;

    private Home_rexiaoFragment Home_rexiaoFragment;
    private Home_zhaopaiFragment Home_zhaopaiFragment;
    private Home_xiaochiFragment Home_xiaochiFragment;
    private Home_yinpingFragment Home_yinpingFragment;
    private Home_zhushiFragment Home_zhushiFragment;


    private View home_tab_rexiao_xiahua;
    private View home_tab_zhaopai_xiahua;
    private View home_tab_zhushi_xiahua;
    private View home_tab_xiaochi_xiahua;
    private View home_tab_yinping_xiahua;
    private ArrayList<Fragment> fragmen;
    private CustomViewPager home_tab_viewpager;
    private ViewPagerAdapter adapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.homefragment;
    }


    @Override
    protected BaseModel initModel() {
        return null;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        ImageView home_go_store = view.findViewById(R.id.home_go_store);
        /*进入商店*/
        home_go_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeStoreActivity.class));

            }
        });
        FloatingActionButton home_add_car = view.findViewById(R.id.home_add_car);
        /*点击进入购物车*/
        home_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeCarActivity.class));
            }
        });


        home_tab_rexiao = (TextView) view.findViewById(R.id.home_tab_rexiao);
        home_tab_zhaopai = (TextView) view.findViewById(R.id.home_tab_zhaopai);
        home_tab_zhushi = (TextView) view.findViewById(R.id.home_tab_zhushi);
        home_tab_xiaochi = (TextView) view.findViewById(R.id.home_tab_xiaochi);
        home_tab_yinping = (TextView) view.findViewById(R.id.home_tab_yinping);

        home_tab_rexiao_xiahua = view.findViewById(R.id.home_tab_rexiao_xiahua);
        home_tab_zhaopai_xiahua = view.findViewById(R.id.home_tab_zhaopai_xiahua);
        home_tab_zhushi_xiahua = view.findViewById(R.id.home_tab_zhushi_xiahua);
        home_tab_xiaochi_xiahua = view.findViewById(R.id.home_tab_xiaochi_xiahua);
        home_tab_yinping_xiahua = view.findViewById(R.id.home_tab_yinping_xiahua);

        home_tab_viewpager = view.findViewById(R.id.home_tab_viewpager);

        home_tab_rexiao.setOnClickListener(this);
        home_tab_zhaopai.setOnClickListener(this);
        home_tab_xiaochi.setOnClickListener(this);
        home_tab_zhushi.setOnClickListener(this);
        home_tab_yinping.setOnClickListener(this);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initData() {

        Home_rexiaoFragment = new Home_rexiaoFragment();
        Home_zhaopaiFragment = new Home_zhaopaiFragment();
        Home_zhushiFragment = new Home_zhushiFragment();
        Home_xiaochiFragment = new Home_xiaochiFragment();
        Home_yinpingFragment = new Home_yinpingFragment();

        fragmen = new ArrayList<Fragment>();
        fragmen.add(new Home_rexiaoFragment());
        fragmen.add(new Home_zhaopaiFragment());
        fragmen.add(new Home_zhushiFragment());
        fragmen.add(new Home_xiaochiFragment());
        fragmen.add(new Home_yinpingFragment());


        home_tab_rexiao.setSelected(true);
        home_tab_rexiao.setTextColor(R.color.yellow);
        home_tab_zhaopai.setTextColor(R.color.hui);
        home_tab_zhushi.setTextColor(R.color.hui);
        home_tab_xiaochi.setTextColor(R.color.hui);
        home_tab_yinping.setTextColor(R.color.hui);
        home_tab_rexiao_xiahua.setVisibility(View.VISIBLE);
        home_tab_zhaopai_xiahua.setVisibility(View.GONE);
        home_tab_zhushi_xiahua.setVisibility(View.GONE);
        home_tab_xiaochi_xiahua.setVisibility(View.GONE);
        home_tab_yinping_xiahua.setVisibility(View.GONE);

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),fragmen);
        home_tab_viewpager.setAdapter(adapter);
        home_tab_viewpager.setScanScroll(false);
        home_tab_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case R.id.home_tab_rexiao:
                        home_tab_viewpager.setCurrentItem(0);
                    case 0:
                        home_tab_rexiao.setTextColor(R.color.yellow);
                        home_tab_zhaopai.setTextColor(R.color.hei);
                        home_tab_zhushi.setTextColor(R.color.hei);
                        home_tab_xiaochi.setTextColor(R.color.hei);
                        home_tab_yinping.setTextColor(R.color.hei);
                        home_tab_rexiao_xiahua.setVisibility(View.VISIBLE);
                        home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                        home_tab_zhushi_xiahua.setVisibility(View.GONE);
                        home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                        home_tab_yinping_xiahua.setVisibility(View.GONE);
                        break;
                    case R.id.home_tab_zhaopai:
                        home_tab_viewpager.setCurrentItem(1);
                    case 1:
                        home_tab_viewpager.setCurrentItem(1);
                        home_tab_zhaopai.setTextColor(R.color.yellow);
                        home_tab_rexiao.setTextColor(R.color.hei);
                        home_tab_zhushi.setTextColor(R.color.hei);
                        home_tab_xiaochi.setTextColor(R.color.hei);
                        home_tab_yinping.setTextColor(R.color.hei);
                        home_tab_rexiao_xiahua.setVisibility(View.GONE);
                        home_tab_zhaopai_xiahua.setVisibility(View.VISIBLE);
                        home_tab_zhushi_xiahua.setVisibility(View.GONE);
                        home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                        home_tab_yinping_xiahua.setVisibility(View.GONE);
                        break;
                    case R.id.home_tab_zhushi:
                        home_tab_viewpager.setCurrentItem(2);
                    case 2:
                        home_tab_zhushi.setTextColor(R.color.yellow);
                        home_tab_rexiao.setTextColor(R.color.hei);
                        home_tab_zhaopai.setTextColor(R.color.hei);
                        home_tab_xiaochi.setTextColor(R.color.hei);
                        home_tab_yinping.setTextColor(R.color.hei);
                        home_tab_rexiao_xiahua.setVisibility(View.GONE);
                        home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                        home_tab_zhushi_xiahua.setVisibility(View.VISIBLE);
                        home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                        home_tab_yinping_xiahua.setVisibility(View.GONE);
                        break;
                    case R.id.home_tab_xiaochi:
                        home_tab_viewpager.setCurrentItem(3);
                    case 3:

                        home_tab_viewpager.setCurrentItem(3);
                        home_tab_xiaochi.setTextColor(R.color.yellow);
                        home_tab_rexiao.setTextColor(R.color.hei);
                        home_tab_zhushi.setTextColor(R.color.hei);
                        home_tab_zhaopai.setTextColor(R.color.hei);
                        home_tab_yinping.setTextColor(R.color.hei);
                        home_tab_rexiao_xiahua.setVisibility(View.GONE);
                        home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                        home_tab_zhushi_xiahua.setVisibility(View.GONE);
                        home_tab_xiaochi_xiahua.setVisibility(View.VISIBLE);
                        home_tab_yinping_xiahua.setVisibility(View.GONE);
                        break;
                    case R.id.home_tab_yinping:
                        home_tab_viewpager.setCurrentItem(4);
                    case 4:
                        home_tab_viewpager.setCurrentItem(4);
                        home_tab_yinping.setTextColor(R.color.yellow);
                        home_tab_rexiao.setTextColor(R.color.hei);
                        home_tab_zhushi.setTextColor(R.color.hei);
                        home_tab_xiaochi.setTextColor(R.color.hei);
                        home_tab_zhaopai.setTextColor(R.color.hei);
                        home_tab_rexiao_xiahua.setVisibility(View.GONE);
                        home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                        home_tab_zhushi_xiahua.setVisibility(View.GONE);
                        home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                        home_tab_yinping_xiahua.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_tab_rexiao:
                home_tab_viewpager.setCurrentItem(0);
                home_tab_rexiao.setTextColor(R.color.yellow);
                home_tab_zhaopai.setTextColor(R.color.hei);
                home_tab_zhushi.setTextColor(R.color.hei);
                home_tab_xiaochi.setTextColor(R.color.hei);
                home_tab_yinping.setTextColor(R.color.hei);
                home_tab_rexiao_xiahua.setVisibility(View.VISIBLE);
                home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                home_tab_zhushi_xiahua.setVisibility(View.GONE);
                home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                home_tab_yinping_xiahua.setVisibility(View.GONE);
                break;

            case R.id.home_tab_zhaopai:
                home_tab_viewpager.setCurrentItem(1);
                home_tab_zhaopai.setTextColor(R.color.yellow);
                home_tab_rexiao.setTextColor(R.color.hei);
                home_tab_zhushi.setTextColor(R.color.hei);
                home_tab_xiaochi.setTextColor(R.color.hei);
                home_tab_yinping.setTextColor(R.color.hei);
                home_tab_rexiao_xiahua.setVisibility(View.GONE);
                home_tab_zhaopai_xiahua.setVisibility(View.VISIBLE);
                home_tab_zhushi_xiahua.setVisibility(View.GONE);
                home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                home_tab_yinping_xiahua.setVisibility(View.GONE);
                break;

            case R.id.home_tab_zhushi:
                home_tab_viewpager.setCurrentItem(2);
                home_tab_zhushi.setTextColor(R.color.yellow);
                home_tab_rexiao.setTextColor(R.color.hei);
                home_tab_zhaopai.setTextColor(R.color.hei);
                home_tab_xiaochi.setTextColor(R.color.hei);
                home_tab_yinping.setTextColor(R.color.hei);
                home_tab_rexiao_xiahua.setVisibility(View.GONE);
                home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                home_tab_zhushi_xiahua.setVisibility(View.VISIBLE);
                home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                home_tab_yinping_xiahua.setVisibility(View.GONE);
                break;
            case R.id.home_tab_xiaochi:
                home_tab_viewpager.setCurrentItem(3);
                home_tab_xiaochi.setTextColor(R.color.yellow);
                home_tab_rexiao.setTextColor(R.color.hei);
                home_tab_zhushi.setTextColor(R.color.hei);
                home_tab_zhaopai.setTextColor(R.color.hei);
                home_tab_yinping.setTextColor(R.color.hei);
                home_tab_rexiao_xiahua.setVisibility(View.GONE);
                home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                home_tab_zhushi_xiahua.setVisibility(View.GONE);
                home_tab_xiaochi_xiahua.setVisibility(View.VISIBLE);
                home_tab_yinping_xiahua.setVisibility(View.GONE);

                break;
            case R.id.home_tab_yinping:
                home_tab_viewpager.setCurrentItem(4);
                home_tab_yinping.setTextColor(R.color.yellow);
                home_tab_rexiao.setTextColor(R.color.hei);
                home_tab_zhushi.setTextColor(R.color.hei);
                home_tab_xiaochi.setTextColor(R.color.hei);
                home_tab_zhaopai.setTextColor(R.color.hei);
                home_tab_rexiao_xiahua.setVisibility(View.GONE);
                home_tab_zhaopai_xiahua.setVisibility(View.GONE);
                home_tab_zhushi_xiahua.setVisibility(View.GONE);
                home_tab_xiaochi_xiahua.setVisibility(View.GONE);
                home_tab_yinping_xiahua.setVisibility(View.VISIBLE);
                break;
        }

    }


}
