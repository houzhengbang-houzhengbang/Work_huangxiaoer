package com.example.asus.huangxiaoer.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.model.UserModel;
import com.example.asus.huangxiaoer.presenter.UserPresenter;
import com.example.asus.huangxiaoer.view.fragment.HomeFragment;
import com.example.asus.huangxiaoer.view.interfaces.User_Interface;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public class MainExcessiceActivity extends BaseActivity<UserPresenter> implements User_Interface {


    private String token;
    private String logintype;
    private int uid;
    /*
        private Intent intent;
    */
    private SharedPreferences ssp;
    private ImageView guoduye2;
    private ImageView guoduye;
    long mLastTime = 0;
    long mCurTime = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    // Toast.makeText(MainActivity.this,"这是单击事件",Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(MainExcessiceActivity.this, MainActivity.class);
                    intent1.putExtra("a", "a");
                    startActivity(intent1);
                    break;
                case 2:
                    //Toast.makeText(MainActivity.this,"这是双击事件",Toast.LENGTH_LONG).show();
                    Intent it = new Intent(MainExcessiceActivity.this, ErWeiMaActivity.class);
                    startActivity(it);
                    break;
            }
        }
    };

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main_excessice;
    }

    @Override
    protected BaseModel initModel() {
        return new UserModel();
    }

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
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
        title.setText("扫码");
        guoduye = findViewById(R.id.mine_guoduye);
        guoduye2 = findViewById(R.id.mine_guoduye2);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String sao = intent.getStringExtra("sao");
        String s = "sao";
        Log.e("123", "initView: " + sao);
        if (s.equals(sao)) {
            Log.e("123", "initView: " + sao);
            guoduye2.setVisibility(View.GONE);
            guoduye.setVisibility(View.VISIBLE);
            guoduye2.setSelected(false);
            /*双击事件*/
            guoduye2.setOnClickListener(new View.OnClickListener() {

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

        }
        ssp = MainExcessiceActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
        logintype = ssp.getString("logintype", "0");

        if (logintype.equals("qq")) {
            guoduye.setVisibility(View.GONE);
            guoduye2.setVisibility(View.VISIBLE);
            guoduye.setSelected(false);

            /*双击事件*/
            guoduye2.setOnClickListener(new View.OnClickListener() {

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

        } else if (logintype.equals("yzm")) {
            guoduye.setVisibility(View.GONE);
            guoduye2.setVisibility(View.VISIBLE);
            guoduye.setSelected(false);

            /*双击事件*/
            guoduye2.setOnClickListener(new View.OnClickListener() {

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
        } else if (logintype.equals("local")) {
            uid = ssp.getInt("uid", 0);
            token = ssp.getString("token", "0");

            guoduye.setVisibility(View.GONE);
            guoduye2.setVisibility(View.VISIBLE);
            guoduye.setSelected(false);

            HashMap<String, String> map = new HashMap<>();
            map.put("uid", "" + "" + uid);
            map.put("token", token);
            // Log.e("123", "init998View: " + map);
            presenter.getPuser(map);
            /*双击事件*/
            guoduye2.setOnClickListener(new View.OnClickListener() {

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

    /*        guoduye2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent(MainExcessiceActivity.this, MainActivity.class);
                    intent3.putExtra("a", "a");
                    startActivity(intent3);

                }
            });*/

            //presenter.detach();
        }


    }

    @Override
    public void onUserSuccess(UserBean userBean) {

        SharedPreferences sp = MainExcessiceActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("mobile", userBean.getData().getMobile());
        edit.putString("username", userBean.getData().getUsername());
        edit.putString("icon", userBean.getData().getIcon());
        edit.putString("nickname", userBean.getData().getNickname() + "");
        edit.commit();


      /*  ImageView guoduye = findViewById(R.id.mine_guoduye);
        guoduye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainExcessiceActivity.this, MainActivity.class);
                intent.putExtra("a", "a");
                startActivity(intent);
                presenter.detach();

            }
        });*/
    }
}
