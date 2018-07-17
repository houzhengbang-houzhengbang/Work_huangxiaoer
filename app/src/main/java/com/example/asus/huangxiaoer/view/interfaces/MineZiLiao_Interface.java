package com.example.asus.huangxiaoer.view.interfaces;

import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.bean.UserPhotoBean;
import com.example.asus.huangxiaoer.model.bean.UsernameBean;
import com.example.base.mvp.IBaseView;

public interface MineZiLiao_Interface extends IBaseView {
    void onPhotoSuccess(UserPhotoBean userPhotoBean);

    void onUserNameSuccess(UsernameBean usernameBean);

    void onZiLiaoUserSuccess(UserBean userBean);

}
