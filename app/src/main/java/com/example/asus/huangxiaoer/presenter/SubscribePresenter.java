package com.example.asus.huangxiaoer.presenter;


import com.example.asus.huangxiaoer.model.bean.SubscribeBean;
import com.example.asus.huangxiaoer.model.model.SubscribeModel;
import com.example.asus.huangxiaoer.view.interfaces.Subscribe_Interface;
import com.example.base.mvp.BasePresenter;

public class SubscribePresenter extends BasePresenter<SubscribeModel, Subscribe_Interface> {

/*用户信息*/
    public void getPSubscribe() {
        model.getMSubscribe(new SubscribeModel.getMISubscribe() {
            @Override
            public void onMISubscribesuccess(SubscribeBean subscribeBean) {
                view.onSubscribeSuccess(subscribeBean);
            }
        });
    }
}
