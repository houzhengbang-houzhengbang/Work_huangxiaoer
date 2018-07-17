package com.example.asus.huangxiaoer.presenter;


import android.util.Log;

import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.bean.UserPhotoBean;
import com.example.asus.huangxiaoer.model.bean.UsernameBean;
import com.example.asus.huangxiaoer.model.model.MineZiLiaoModel;
import com.example.asus.huangxiaoer.view.interfaces.MineZiLiao_Interface;
import com.example.base.mvp.BasePresenter;

import java.util.HashMap;

import okhttp3.MultipartBody;

public class MineZiLiaoPresenter extends BasePresenter<MineZiLiaoModel, MineZiLiao_Interface> {

    /*修改昵称*/
    public void getPusername(HashMap<String, String> map) {
        model.getMUsername(map, new MineZiLiaoModel.getMIUsername() {
            @Override
            public void onMIUsernamesuccess(UsernameBean usernameBean) {
                //Log.e("123", "onMIUsernamesuccess: "+"添加名字"+usernameBean.getMsg() );
                view.onUserNameSuccess(usernameBean);
            }
        });
    }

    /*用户信息*/
    public void getPuser(HashMap<String, String> map3) {
       //Log.e("123", "getPuser: " + map3.size());
        model.getMUser(map3, new MineZiLiaoModel.getMIUsers() {
            @Override
            public void onMIUserssuccess(UserBean userBean) {
                //Log.e("123", "onMIUserssuccess: " + userBean.getData().getNickname());
                view.onZiLiaoUserSuccess(userBean);
            }
        });
    }

    /*上传头像*/
    public void getPuserphoto(int uid, MultipartBody.Part photouri) {
        // model.getMUserphoto(uid,photo,new);
        model.getMUserphoto(uid, photouri, new MineZiLiaoModel.getMIUserphoto() {
            @Override
            public void onMIUserphotosuccess(UserPhotoBean userphotoBean) {
            }

        });
    }
}
