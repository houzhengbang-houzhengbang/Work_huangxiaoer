package com.example.asus.huangxiaoer.model.model;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.example.asus.huangxiaoer.model.api.RetrofitApi;
import com.example.asus.huangxiaoer.model.bean.LoginBean;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.util.RetrofitUtil;
import com.example.base.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel {

    private RetrofitApi retrofitInterface;
    private SharedPreferences sp;

    /*登录*/
    public void getMlogin(HashMap<String, String> map, final getMIlogin getMIlogin) {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        Observable<LoginBean> login = retrofitInterface.getLoginBean(map);
        login.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean loginBean) {
                if (loginBean != null) {
                    getMIlogin.onMILoginsuccess(loginBean);
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

    public interface getMIlogin {
        void onMILoginsuccess(LoginBean loginBean);
    }


}
