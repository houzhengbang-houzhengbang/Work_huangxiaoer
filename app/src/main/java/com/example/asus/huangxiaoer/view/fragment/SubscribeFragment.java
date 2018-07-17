package com.example.asus.huangxiaoer.view.fragment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.SubscribeBean;
import com.example.asus.huangxiaoer.model.model.SubscribeModel;
import com.example.asus.huangxiaoer.presenter.SubscribePresenter;
import com.example.asus.huangxiaoer.view.activity.SearchActivity;
import com.example.asus.huangxiaoer.view.adapter.Subscribe_Recy_list_Adapter;
import com.example.asus.huangxiaoer.view.interfaces.Subscribe_Interface;
import com.example.base.BaseFragment;
import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;

public class SubscribeFragment extends BaseFragment<SubscribePresenter> implements Subscribe_Interface {

    private RecyclerView subscribe_recy;
    private Subscribe_Recy_list_Adapter adapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.subscribefragment;
    }


    @Override
    protected BaseModel initModel() {
        return new SubscribeModel();
    }

    @Override
    protected SubscribePresenter initPresenter() {
        return new SubscribePresenter();
    }

    @Override
    protected void initView(View view) {
        EditText a = view.findViewById(R.id.a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        subscribe_recy = view.findViewById(R.id.Subscribe_recy);
    }


    @Override
    protected void initData() {
        fragment.getPSubscribe();

        adapter = new Subscribe_Recy_list_Adapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        subscribe_recy.setAdapter(adapter);
        subscribe_recy.setLayoutManager(linearLayoutManager);


    }

    @Override
    public void onSubscribeSuccess(SubscribeBean subscribeBean) {
        adapter.setData(subscribeBean.getData());
    }
}
