package com.example.asus.huangxiaoer.model.api;


import com.example.asus.huangxiaoer.model.bean.LoginBean;
import com.example.asus.huangxiaoer.model.bean.RegBean;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.bean.UserPhotoBean;
import com.example.asus.huangxiaoer.model.bean.UsernameBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitApi {

    /**
     * 登录
     * https://www.zhaoapi.cn/user/login?mobile=12345678901&password=123456
     */

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> getLoginBean(@FieldMap HashMap<String, String> map);

    /**
     * 注册
     * https://www.zhaoapi.cn/user/reg?mobile=12345678901&password=123456
     */

    @POST("user/reg")
    @FormUrlEncoded
    Observable<RegBean> getRegBean(@FieldMap HashMap<String, String> map);

    /**
     * 用户信息
     * https://www.zhaoapi.cn/user/getUserInfo?uid=15005
     */

    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<UserBean> getUserBean(@FieldMap HashMap<String, String> map);

    /**
     * 上传头像
     * https://www.zhaoapi.cn/file/upload?uid=15005&file=?
     */
    @POST("file/upload")
    @Multipart
    Observable<UserPhotoBean> getUserPhotoBean(@Query("uid") int uid, @Part MultipartBody.Part file);

    /**
     * 修改昵称
     * https://www.zhaoapi.cn/user/updateNickName?uid=15005&nickname=?
     */
    @POST("user/updateNickName")
    @FormUrlEncoded
    Observable<UsernameBean> getUsernameBean(@FieldMap HashMap<String, String> map);

}
