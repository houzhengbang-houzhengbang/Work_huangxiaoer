package com.example.asus.huangxiaoer.model.api;


import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.bean.SubscribeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitApi2 {
    /**
     * http://www.wanandroid.com/tools/mockapi/6523/restaurants_offset_0_limit_4
     */

    @GET("tools/mockapi/6523/restaurants_offset_0_limit_4")
    Observable<SubscribeBean> getSubscribeBean();

    /**
     * http://www.wanandroid.com/tools/mockapi/6523/restaurant-list
     */
    @GET("tools/mockapi/6523/restaurant-list")
    Observable<Home_ReXiaoBean> getHomeReXiaoBean();


}
