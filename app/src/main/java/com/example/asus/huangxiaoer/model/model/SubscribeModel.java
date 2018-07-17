package com.example.asus.huangxiaoer.model.model;


import android.util.Log;

import com.example.asus.huangxiaoer.model.api.RetrofitApi2;
import com.example.asus.huangxiaoer.model.bean.SubscribeBean;
import com.example.asus.huangxiaoer.model.util.RetrofitUtil2;
import com.example.base.mvp.BaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubscribeModel extends BaseModel {


    public void getMSubscribe(final getMISubscribe getMISubscribe) {
        RetrofitApi2 retrofitInterface = (RetrofitApi2) RetrofitUtil2.getInstance().getRetrofitInterface();
        final Observable<SubscribeBean> subscribe = retrofitInterface.getSubscribeBean();
        subscribe.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SubscribeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SubscribeBean subscribeBean) {

                if (subscribeBean != null) {
                    getMISubscribe.onMISubscribesuccess(subscribeBean);
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

    public interface getMISubscribe {
        void onMISubscribesuccess(SubscribeBean subscribeBean);
    }

}
