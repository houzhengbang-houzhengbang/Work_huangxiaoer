package com.example.asus.huangxiaoer.model.model;


import com.example.asus.huangxiaoer.model.api.RetrofitApi2;
import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.util.RetrofitUtil2;
import com.example.base.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Home_ReXiaoModel extends BaseModel {

    private RetrofitApi2 retrofitInterface;


    public void getMHomeReXiao( final getMIHomeReXiao getMIHomeReXiao) {
        retrofitInterface = RetrofitUtil2.getInstance().getRetrofitInterface();
        final Observable<Home_ReXiaoBean> user = retrofitInterface.getHomeReXiaoBean();
        user.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Home_ReXiaoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Home_ReXiaoBean home_reXiaoBean) {
                if (home_reXiaoBean != null) {
                    getMIHomeReXiao.onMIHomeReXiaosuccess(home_reXiaoBean);
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

    public interface getMIHomeReXiao {
        void onMIHomeReXiaosuccess(Home_ReXiaoBean home_reXiaoBean);
    }
}
