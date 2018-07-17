package com.example.asus.huangxiaoer.view.interfaces;

import com.example.asus.huangxiaoer.model.bean.LoginBean;
import com.example.base.mvp.IBaseView;

public interface Login_Interface extends IBaseView {
    void onLoginSuccess(LoginBean loginBean);
}
