package com.example.asus.huangxiaoer.presenter;


import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.model.Home_ReXiaoModel;
import com.example.asus.huangxiaoer.model.model.UserModel;
import com.example.asus.huangxiaoer.view.interfaces.Home_ReXiao_Interface;
import com.example.base.mvp.BasePresenter;

import java.util.HashMap;

public class Home_ReXiaoPresenter extends BasePresenter<Home_ReXiaoModel, Home_ReXiao_Interface> {

/*用户信息*/
    public void getReXiaoModel() {
        model.getMHomeReXiao(new Home_ReXiaoModel.getMIHomeReXiao() {
            @Override
            public void onMIHomeReXiaosuccess(Home_ReXiaoBean home_reXiaoBean) {
                view.onhomeReXiaoSuccess(home_reXiaoBean);

            }
        });
    }
}
