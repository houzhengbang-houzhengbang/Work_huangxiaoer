package com.example.asus.huangxiaoer.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

public class MineUserActivity extends BaseActivity {

    private Button mineuserloginbtn;
    private Button mineuserzhuxiao;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_mine_user;
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
        mineuserloginbtn = findViewById(R.id.mineuserloginbtn);
        mineuserzhuxiao = findViewById(R.id.mineuserzhuxiao);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("用户");


        SharedPreferences sp = MineUserActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);

        String token = sp.getString("token", "");
        String logintype = sp.getString("logintype", "");

        if (logintype.equals("qq")) {
            mineuserzhuxiao.setVisibility(View.VISIBLE);
            mineuserloginbtn.setVisibility(View.GONE);
        }else {

            if (token.equals("")) {
                mineuserzhuxiao.setVisibility(View.GONE);
                mineuserloginbtn.setVisibility(View.VISIBLE);
            } else {
                mineuserzhuxiao.setVisibility(View.VISIBLE);
                mineuserloginbtn.setVisibility(View.GONE);
            }
        }
    }


    public void mineuserzhuxiao(View view) {
        mineuserloginbtn.setVisibility(View.VISIBLE);
        mineuserzhuxiao.setVisibility(View.GONE);

        SharedPreferences sp = MineUserActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        //通过editor对象写入数据
        edit.putInt("lohintype", 0);
        edit.putInt("qqname", 0);
        edit.putInt("qqicon", 0);
        edit.putInt("uid", 0);
        edit.putString("token", "");
        edit.putString("mobile", "");
        edit.putString("username", "");
        edit.putString("icon", "");
        edit.putString("nickname", "");
        //提交数据存入到xml文件中
        edit.commit();
    }

    public void mineuserloginbtn(View view) {
        startActivity(new Intent(MineUserActivity.this, LoginActivity.class));

    }
}
