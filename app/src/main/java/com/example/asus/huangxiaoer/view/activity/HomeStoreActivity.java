package com.example.asus.huangxiaoer.view.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.model.Home_StoreModel;
import com.example.asus.huangxiaoer.presenter.Home_StorePresenter;
import com.example.asus.huangxiaoer.view.adapter.Home_Store_Recy_list_you_Adapter;
import com.example.asus.huangxiaoer.view.adapter.Home_Store_Recy_list_zuo_Adapter;
import com.example.asus.huangxiaoer.view.adapter.Subscribe_Recy_list_Adapter;
import com.example.asus.huangxiaoer.view.interfaces.Home_Store_Interface;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

import java.util.List;

public class HomeStoreActivity extends BaseActivity<Home_StorePresenter> implements Home_Store_Interface {


    private RecyclerView home_store_recy_zuo;
    private RecyclerView home_store_recy_you;
    private Home_Store_Recy_list_zuo_Adapter adapter;
    private Home_Store_Recy_list_you_Adapter adapter2;
    private Home_ReXiaoBean home;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_home_store;
    }

    @Override
    protected BaseModel initModel() {
        return new Home_StoreModel();
    }

    @Override
    protected Home_StorePresenter initPresenter() {
        return new Home_StorePresenter();
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
        title.setText("麦当当的店");

        home_store_recy_zuo = findViewById(R.id.home_store_recy_zuo);
        home_store_recy_you = findViewById(R.id.home_store_recy_you);
    }

    @Override
    protected void initData() {
        presenter.getReXiaoModel();

        adapter = new Home_Store_Recy_list_zuo_Adapter(HomeStoreActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeStoreActivity.this);

        home_store_recy_zuo.setAdapter(adapter);
        home_store_recy_zuo.setLayoutManager(linearLayoutManager);




    }

    @Override
    public void onhomeStoreSuccess(Home_ReXiaoBean home_reXiaoBean) {
        home = home_reXiaoBean;
        adapter.setData(home_reXiaoBean.getData());
        adapter2 = new Home_Store_Recy_list_you_Adapter(HomeStoreActivity.this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomeStoreActivity.this);

        home_store_recy_you.setAdapter(adapter2);
        home_store_recy_you.setLayoutManager(linearLayoutManager2);


        adapter.setOnItemClick(new Home_Store_Recy_list_zuo_Adapter.OnItemClick() {
            @Override
            public void setOnCl0ick(int pos, List<Home_ReXiaoBean.DataBean> list) {

                adapter2.setData(list.get(pos).getSpus());
            }
        });
    }

    @Override
    public void onhomeStoreyouSuccess(Home_ReXiaoBean home_reXiaoBean) {

    }
}
