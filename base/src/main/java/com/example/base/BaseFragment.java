package com.example.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;
import com.example.base.mvp.IBaseView;

public abstract class BaseFragment <F extends BasePresenter> extends Fragment implements IBaseView {
public F fragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(bindLayoutId(),container,false);
       initView(view);
        return view;
    }


   @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       fragment = initPresenter();
       if(fragment != null){
           fragment.attch(initModel(),this);
       }
       initData();
    }

    protected abstract void initData();

    protected abstract BaseModel initModel();

    protected abstract F initPresenter();

    protected abstract void initView(View view);

    protected abstract int bindLayoutId();

    public void onSuccess(String s){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(fragment != null){
            fragment.detach();
        }
    }
}
