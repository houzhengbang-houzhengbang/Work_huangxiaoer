package com.example.asus.huangxiaoer.model.model;


import com.example.asus.huangxiaoer.model.api.RetrofitApi2;
import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.util.RetrofitUtil2;
import com.example.base.mvp.BaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Home_StoreModel extends BaseModel {

    private RetrofitApi2 retrofitInterface;


    public void getMHomeStore( final getMIHomeStore getMIHomeStore) {
        retrofitInterface = RetrofitUtil2.getInstance().getRetrofitInterface();
        final Observable<Home_ReXiaoBean> user = retrofitInterface.getHomeReXiaoBean();
        user.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Home_ReXiaoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Home_ReXiaoBean home_reXiaoBean) {
                if (home_reXiaoBean != null) {
                    getMIHomeStore.onMIHomeStoresuccess(home_reXiaoBean);
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

    public interface getMIHomeStore {
        void onMIHomeStoresuccess(Home_ReXiaoBean home_reXiaoBean);
    }
}
