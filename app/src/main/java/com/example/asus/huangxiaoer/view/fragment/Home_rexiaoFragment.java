package com.example.asus.huangxiaoer.view.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.model.Home_ReXiaoModel;
import com.example.asus.huangxiaoer.presenter.Home_ReXiaoPresenter;
import com.example.asus.huangxiaoer.view.adapter.Home_ReXiao_Recy_list_Adapter;
import com.example.asus.huangxiaoer.view.adapter.Subscribe_Recy_list_Adapter;
import com.example.asus.huangxiaoer.view.interfaces.Home_ReXiao_Interface;
import com.example.base.BaseFragment;

public class Home_rexiaoFragment extends BaseFragment<Home_ReXiaoPresenter> implements Home_ReXiao_Interface {

    private RecyclerView home_rexiao_recy;
    private Home_ReXiao_Recy_list_Adapter adapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.home_rexiaofragment;
    }


    @Override
    protected Home_ReXiaoModel initModel() {
        return new Home_ReXiaoModel();
    }

    @Override
    protected Home_ReXiaoPresenter initPresenter() {
        return new Home_ReXiaoPresenter();
    }

    @Override
    protected void initView(View view) {
        home_rexiao_recy = view.findViewById(R.id.home_rexiao_recy);
    }


    @Override
    protected void initData() {
        fragment.getReXiaoModel();


        adapter = new Home_ReXiao_Recy_list_Adapter(getActivity());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        home_rexiao_recy.setAdapter(adapter);
        home_rexiao_recy.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onhomeReXiaoSuccess(Home_ReXiaoBean home_reXiaoBean) {
        adapter.setData(home_reXiaoBean.getData().get(1).getSpus());
    }
}
