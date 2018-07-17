package com.example.asus.huangxiaoer.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

public class MineXiHuanActivity extends BaseActivity {



    @Override
    protected int bindLayoutId() {
        return R.layout.activity_mine_xi_huan;
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
        title.setText("喜欢的店");
    }
}
