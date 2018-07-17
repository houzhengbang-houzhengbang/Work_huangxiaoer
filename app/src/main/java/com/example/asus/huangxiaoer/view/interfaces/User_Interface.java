package com.example.asus.huangxiaoer.view.interfaces;


import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.base.mvp.IBaseView;

public interface User_Interface extends IBaseView {
    void onUserSuccess(UserBean userBean);
}
