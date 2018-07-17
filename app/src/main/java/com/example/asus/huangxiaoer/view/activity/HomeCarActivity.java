package com.example.asus.huangxiaoer.view.activity;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.view.fragment.HomeFragment;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

public class HomeCarActivity extends BaseActivity {


    @Override
    protected int bindLayoutId() {
        return R.layout.activity_home_car;
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
    protected void initData() {
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("购物车");
    }

    @Override
    protected void initView() {

    }

    public void home_back(View view) {
/*
        startActivity(new Intent(HomeCarActivity.this, HomeFragment.class));

*/
finish();
    }
}
