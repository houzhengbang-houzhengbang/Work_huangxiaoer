package com.example.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.base.mvp.BaseModel;
import com.example.base.mvp.BasePresenter;
import com.example.base.mvp.IBaseView;

public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity implements IBaseView {
  public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        initView();
        presenter = initPresenter();
        if(presenter != null){
            presenter.attch(initModel(),this);
        }
        initData();
    }

    protected abstract int bindLayoutId();

    protected abstract BaseModel initModel();

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    public void onSuccess(String s){}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.detach();
        }

    }
}
