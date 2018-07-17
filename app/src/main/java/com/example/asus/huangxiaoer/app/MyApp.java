package com.example.asus.huangxiaoer.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyApp extends Application {
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        UMConfigure.init(this,"5b3cc7168f4a9d57b8000298"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        MobSDK.init(this);
    }
}