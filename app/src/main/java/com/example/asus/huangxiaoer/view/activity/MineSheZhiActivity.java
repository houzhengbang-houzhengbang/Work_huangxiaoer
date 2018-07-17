package com.example.asus.huangxiaoer.view.activity;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

public class MineSheZhiActivity extends BaseActivity {


    @Override
    protected int bindLayoutId() {
        return R.layout.activity_mine_she_zhi;
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

    }

    @Override
    protected void initView() {
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("设置");
    }

    public void mine_shezhi_guanyuhuangxiaoer(View view) {
        startActivity(new Intent(MineSheZhiActivity.this, GuanYuHuangXiaoErActivity.class));

    }
}
