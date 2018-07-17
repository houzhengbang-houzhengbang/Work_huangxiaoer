package com.example.asus.huangxiaoer.model.model;


import android.util.Log;

import com.example.asus.huangxiaoer.model.api.RetrofitApi;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.bean.UserPhotoBean;
import com.example.asus.huangxiaoer.model.bean.UsernameBean;
import com.example.asus.huangxiaoer.model.util.RetrofitUtil;
import com.example.base.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class MineZiLiaoModel extends BaseModel {


    /*修改昵称*/
    public void getMUsername(HashMap<String, String> map, final getMIUsername getMIUsername) {
        RetrofitApi retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        Observable<UsernameBean> usera = retrofitInterface.getUsernameBean(map);

        usera.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UsernameBean>() {
            @Override
            public void onSubscribe(Disposable d) {
               // Log.e("123", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(UsernameBean usernameBean) {
                //Log.e("123", "onNext:nc " + usernameBean.getMsg());
                if (usernameBean != null) {
                    getMIUsername.onMIUsernamesuccess(usernameBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                //Log.e("123", "onError: nc" + e);
            }

            @Override
            public void onComplete() {
                //Log.e("123", "onError: nc");
            }
        });
    }

    public interface getMIUsername {
        void onMIUsernamesuccess(UsernameBean usernameBean);
    }


    /*上传图片*/
    public void getMUserphoto(int uid, MultipartBody.Part photouri, final getMIUserphoto getMIUserphoto) {
        RetrofitApi retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        Observable<UserPhotoBean> user = retrofitInterface.getUserPhotoBean(uid, photouri);
        user.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserPhotoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserPhotoBean userPhotoBean) {
                //Log.e("123", "onNext: " + "照片");
            }

            @Override
            public void onError(Throwable e) {
                //Log.e("123", "onError: "+"zp" );
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public interface getMIUserphoto {
        void onMIUserphotosuccess(UserPhotoBean userphotoBean);
    }


    /*用户信息,又反悔*/
    public void getMUser(HashMap<String, String> map3, final getMIUsers getMIUsers) {

        RetrofitApi retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();

        Observable<UserBean> users = retrofitInterface.getUserBean(map3);
        //Log.e("123", "onNext03:user ");

        users.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserBean userBean) {
               // Log.e("123", "onNext1: user" + userBean.getData().getNickname());
                if (userBean != null) {
                   // Log.e("123", "onNext2: user" + userBean.getData().getNickname());
                    getMIUsers.onMIUserssuccess(userBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                //Log.e("123", "onError: user" + e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public interface getMIUsers {
        void onMIUserssuccess(UserBean userBean);
    }
}
