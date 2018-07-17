package com.example.asus.huangxiaoer.presenter;


import android.content.Intent;

import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.model.UserModel;
import com.example.asus.huangxiaoer.view.interfaces.User_Interface;
import com.example.base.mvp.BasePresenter;

import java.util.HashMap;

public class UserPresenter extends BasePresenter<UserModel, User_Interface> {

/*用户信息*/
    public void getPuser(HashMap<String, String> map) {
        model.getMUser(map, new UserModel.getMIUser() {
            @Override
            public void onMIUsersuccess(UserBean userBean) {
                view.onUserSuccess(userBean);
            }
        });
    }
}
