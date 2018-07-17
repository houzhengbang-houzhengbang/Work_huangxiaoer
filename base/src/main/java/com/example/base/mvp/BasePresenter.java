package com.example.base.mvp;

public class BasePresenter<M extends BaseModel,V extends IBaseView> implements  IBasePresenter {


    public M model;
    public V view;

    public void attch(M m,V v){
         this.model = m;
         this.view = v;
    }
    public void detach(){
        this.view = null;
        this.model = null;
    }

}
