package com.example.asus.huangxiaoer.view.interfaces;


import com.example.asus.huangxiaoer.model.bean.SubscribeBean;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.base.mvp.IBaseView;

public interface Subscribe_Interface extends IBaseView {
    void onSubscribeSuccess(SubscribeBean subscribeBean);
}
