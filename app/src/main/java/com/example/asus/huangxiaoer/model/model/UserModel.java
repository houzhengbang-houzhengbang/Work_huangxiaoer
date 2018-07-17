package com.example.asus.huangxiaoer.model.model;


import com.example.asus.huangxiaoer.model.api.RetrofitApi;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.util.RetrofitUtil;
import com.example.base.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserModel extends BaseModel {

    private RetrofitApi retrofitInterface;


    /*用户信息*/
    public void getMUser(HashMap<String, String> map, final getMIUser getMIUser) {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        final Observable<UserBean> user = retrofitInterface.getUserBean(map);
        user.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserBean userBean) {
                if (userBean != null) {
                    getMIUser.onMIUsersuccess(userBean);
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public interface getMIUser {
        void onMIUsersuccess(UserBean userBean);
    }

}
