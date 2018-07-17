package com.example.asus.huangxiaoer.presenter;

import android.util.Log;

import com.example.asus.huangxiaoer.model.bean.LoginBean;
import com.example.asus.huangxiaoer.model.model.LoginModel;
import com.example.asus.huangxiaoer.model.model.SearchModel;
import com.example.asus.huangxiaoer.view.interfaces.Login_Interface;
import com.example.asus.huangxiaoer.view.interfaces.SearchInterface;
import com.example.base.mvp.BasePresenter;

import java.util.HashMap;

public class LoginPresenter extends BasePresenter<LoginModel, Login_Interface> {

/*登录*/
    public void getPlogin(HashMap<String, String> map) {
        model.getMlogin(map, new LoginModel.getMIlogin() {
            @Override
            public void onMILoginsuccess(LoginBean loginBean) {
                view.onLoginSuccess(loginBean);
            }
        });
    }

}
