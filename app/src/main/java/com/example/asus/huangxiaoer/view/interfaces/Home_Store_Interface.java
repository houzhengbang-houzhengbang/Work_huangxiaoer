package com.example.asus.huangxiaoer.view.interfaces;


import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.base.mvp.IBaseView;

public interface Home_Store_Interface extends IBaseView {
    void onhomeStoreSuccess(Home_ReXiaoBean home_reXiaoBean);
    void onhomeStoreyouSuccess(Home_ReXiaoBean home_reXiaoBean);
}
