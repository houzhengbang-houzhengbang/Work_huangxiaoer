package com.example.asus.huangxiaoer.view.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.util.FrescoUtil;
import com.example.asus.huangxiaoer.view.activity.MineSheZhiActivity;
import com.example.asus.huangxiaoer.view.activity.MineXiHuanActivity;
import com.example.asus.huangxiaoer.view.activity.MineYouHuiActivity;
import com.example.asus.huangxiaoer.view.activity.MineUserActivity;
import com.example.asus.huangxiaoer.view.activity.MineZiLiaoActivity;
import com.example.base.BaseFragment;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import static android.content.Context.MODE_PRIVATE;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout mine_shezhi;
    private LinearLayout mine_youhui;
    private LinearLayout mine_ziliao;
    private LinearLayout mine_xihuan;
    private RelativeLayout ming_yonghu;

    private TextView mine_username;
    private SimpleDraweeView mine_photo;

    private String username;
    private String token;
    private String username2;
    private String icon;

    private String logintype;
    private String qqicon;
    private String qqname;
    private String yammodel;


    @Override
    protected int bindLayoutId() {
        return R.layout.minefragment;
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
        ming_yonghu = view.findViewById(R.id.ming_yonghu);
        ming_yonghu.setOnClickListener(this);
        mine_shezhi = view.findViewById(R.id.mine_shezhi);
        mine_shezhi.setOnClickListener(this);
        mine_youhui = view.findViewById(R.id.mine_youhui);
        mine_youhui.setOnClickListener(this);
        mine_xihuan = view.findViewById(R.id.mine_xihuan);
        mine_xihuan.setOnClickListener(this);
        mine_ziliao = view.findViewById(R.id.mine_ziliao);
        mine_ziliao.setOnClickListener(this);


        mine_username = view.findViewById(R.id.mine_username);
        mine_photo = view.findViewById(R.id.mine_photo);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();


        SharedPreferences sp = getActivity().getSharedPreferences("huangxiaoer", MODE_PRIVATE);
        logintype = sp.getString("logintype", "0");


        if (logintype.equals("qq")) {
            qqicon = sp.getString("qqicon", "0");
            qqname = sp.getString("qqname", "0");

            FrescoUtil.setYuanJiao(qqicon, mine_photo, 50, R.color.white, 1);
            mine_username.setText(qqname);

        } else if (logintype.equals("yzm")) {
            yammodel = sp.getString("yammodel", "0");

            FrescoUtil.setYuanJiao(String.valueOf(R.drawable.youxiang), mine_photo, 50, R.color.white, 1);
            mine_username.setText(yammodel);

        } else {
            username = sp.getString("username", "0");
            token = sp.getString("token", "0");
            username2 = sp.getString("nickname", "0");
            icon = sp.getString("icon", "0");

            if (token.equals("")) {
                FrescoUtil.setYuanJiao(String.valueOf(R.drawable.youxiang), mine_photo, 50, R.color.white, 1);
                mine_username.setText("xxx");

            } else {
                if (username2.equals("")) {
                    mine_username.setText(username);
                } else {
                    mine_username.setText(username2);
                }
                FrescoUtil.setYuanJiao(icon, mine_photo, 50, R.color.white, 1);
            }

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_ziliao:
                startActivity(new Intent(getActivity(), MineZiLiaoActivity.class));
                break;
            case R.id.mine_xihuan:
                startActivity(new Intent(getActivity(), MineXiHuanActivity.class));
                break;
            case R.id.mine_youhui:
                startActivity(new Intent(getActivity(), MineYouHuiActivity.class));
                break;
            case R.id.mine_shezhi:
                startActivity(new Intent(getActivity(), MineSheZhiActivity.class));
                break;
            case R.id.ming_yonghu:
                startActivity(new Intent(getActivity(), MineUserActivity.class));
                break;

        }
    }

}
