package com.example.asus.huangxiaoer.presenter;


import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.model.Home_ReXiaoModel;
import com.example.asus.huangxiaoer.model.model.Home_StoreModel;
import com.example.asus.huangxiaoer.view.interfaces.Home_ReXiao_Interface;
import com.example.asus.huangxiaoer.view.interfaces.Home_Store_Interface;
import com.example.base.mvp.BasePresenter;

public class Home_StorePresenter extends BasePresenter<Home_StoreModel, Home_Store_Interface> {

/*用户信息*/
    public void getReXiaoModel() {
        model.getMHomeStore(new Home_StoreModel.getMIHomeStore() {
            @Override
            public void onMIHomeStoresuccess(Home_ReXiaoBean home_reXiaoBean) {
                view.onhomeStoreSuccess(home_reXiaoBean);
            }
        });
    }
}
