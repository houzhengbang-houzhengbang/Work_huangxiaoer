package com.example.asus.huangxiaoer.view.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.LoginBean;
import com.example.asus.huangxiaoer.model.model.LoginModel;
import com.example.asus.huangxiaoer.model.util.Regular_ExpressionUtil;
import com.example.asus.huangxiaoer.presenter.LoginPresenter;
import com.example.asus.huangxiaoer.view.interfaces.Login_Interface;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity<LoginPresenter> implements Login_Interface, View.OnTouchListener {
    private LinearLayout b;
    private LinearLayout a;
    private String pass;
    private String name;

    private EditText login_c_model;
    private EditText login_c_pass;
    private ImageView login_c_look;

    private EditText login_yz_yzm;
    private EditText login_yz_model;
    private Button login_yz_qingqiu_yzm;
    private int i =30;


    private String qqicon;
    private String qqname;
    private String phone;


    private int arg1;
    private int arg2;
    private Object obj;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BaseModel initModel() {
        return new LoginModel();
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);

        /*短信验证*/
        login_yz_qingqiu_yzm = findViewById(R.id.login_yz_qingqiu_yzm);
        login_yz_model = findViewById(R.id.login_yz_model);
        login_yz_yzm = findViewById(R.id.login_yz_yzm);


        /*常规登录*/
        login_c_model = findViewById(R.id.login_c_model);
        login_c_pass = findViewById(R.id.login_c_pass);
        login_c_look = findViewById(R.id.login_c_look);
        login_c_look.setOnTouchListener(this);


        // .registerEventHandler是用来往SMSSDK中注册一个事件接收器。
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                arg1 = event;
                arg2 = result;
                obj = data;
               /* if(arg2 == -1){
                    Toast.makeText(LoginActivity.this, "没有获取到验证码", Toast.LENGTH_SHORT).show();
                }else{*/
                    handler.sendEmptyMessage(-3);
                //}
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void initData() {

    }

    /*切换 登录方式*/
    public void loginverchange(View view) {

        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.GONE);
    }

    public void logincon_change(View view) {

        b.setVisibility(View.VISIBLE);
        a.setVisibility(View.GONE);
    }



    /*请求验证码*/
    public void login_yz_qingqiu_yzm(View view) {

        phone = login_yz_model.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SMSSDK.getVerificationCode("86", phone);
        login_yz_qingqiu_yzm.setClickable(false);
        //开始倒计时
        handler.sendEmptyMessageDelayed(-1, 1000);


      /*
      16619835976
         if (Regular_ExpressionUtil.isMobileNO(name)) {

      }else{
            Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }*/

    }
    /*短信登录*/
    public void login_btnimg(View view) {

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(login_yz_yzm.getText().toString())) {
            Toast.makeText(getApplicationContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SMSSDK.submitVerificationCode("86", phone, login_yz_yzm.getText().toString());


    }


    /*常规登录*/
//    @SuppressLint("ClickableViewAccessibility")
    public void login_c_btnimg(View view) {

        /*获取手机号和密码*/
        name = login_c_model.getText().toString();
        pass = login_c_pass.getText().toString();

        if (Regular_ExpressionUtil.isMobileNO(name)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("mobile", name);
            map.put("password", pass);
            presenter.getPlogin(map);
        } else {
            Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }

    }

    /*显示或隐藏密码*/
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.login_c_look) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    login_c_look.setImageResource(R.drawable.show_pass);
                    login_c_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//密码显示
                    login_c_pass.setSelection(login_c_pass.length());//但是密码显示后，文本光标会跑到开头去，重新在文本末获取一下光标
                    break;
                case MotionEvent.ACTION_UP:
                    login_c_look.setImageResource(R.drawable.hide_pass);
                    login_c_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());//密码隐藏
                    login_c_pass.setSelection(login_c_pass.length());
                    break;
            }
        }
        return true;//这里要返回true，不然抬起事件会不响应，应该是事件分发机制的原因

    }

    /*返回的数据*/
    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        /*判断是否登录成功*/
        if (loginBean.getCode() != null) {
            if (loginBean.getCode().equals("0")) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                SharedPreferences sp = LoginActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                //通过editor对象写入数据
                edit.putString("logintype", "local");
                edit.putInt("uid", loginBean.getData().getUid());
                edit.putString("token", loginBean.getData().getToken());
                //提交数据存入到xml文件中
                edit.commit();


                Intent intent = new Intent(LoginActivity.this, MainExcessiceActivity.class);
                //intent.putExtra("a", "a");
                startActivity(intent);


                presenter.detach();
            } else {
                Toast.makeText(LoginActivity.this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                login_c_model.setText("");
                login_c_pass.setText("");
            }
        }

    }


    /*友盟 QQ 登录  */
    public void QQlogin(View view) {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(LoginActivity.this).onActivityResult(requestCode, resultCode, data);


    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

            Set<String> set = data.keySet();
            for (String string : set) {
                if (string.trim().equals("iconurl")) {
                    //Log.e("123", "onComplete: " + data.get(string).toString());
                    qqicon = data.get(string).toString();
                } else if (string.trim().equals("name")) {
                    //Log.e("123", "onComplete: " + data.get(string).toString());
                    qqname = data.get(string).toString();
                }
            }
            SharedPreferences sppp = LoginActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
            SharedPreferences.Editor edit = sppp.edit();
            edit.putString("logintype", "qq");
            edit.putString("qqname", qqname);
            edit.putString("qqicon", qqicon);
            edit.commit();

            Intent intent = new Intent(LoginActivity.this, MainExcessiceActivity.class);
            startActivity(intent);

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    //在完成短信验证之后，需要销毁回调监听接口。
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                i--;
                login_yz_qingqiu_yzm.setText("重新发送(" + i + ")");
                login_yz_qingqiu_yzm.setBackgroundResource(R.color.hui);
                if(i==1){
                    i=30;
                    handler.sendEmptyMessageDelayed(-2, 1000);

                }else {
                    handler.sendEmptyMessageDelayed(-1, 1000);
                }
            } else if (msg.what == -2) {
                login_yz_qingqiu_yzm.setText("重新获取");
                login_yz_qingqiu_yzm.setClickable(true);
                login_yz_qingqiu_yzm.setBackgroundResource(R.color.hui);

            } else if(msg.what == -3) {
                int event = arg1;
                int result = arg2;
                Object data = obj;
                //Log.e("123", "handleMessage: "+event+"***"+result+"***"+data );
                // 短信注册成功后，返回MainActivity,然后提示
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(LoginActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                        SharedPreferences sppp = LoginActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sppp.edit();
                        edit.putString("logintype", "yzm");
                        edit.putString("yammodel", phone);
                        edit.commit();
                        Intent intent = new Intent(LoginActivity.this, MainExcessiceActivity.class);
                        startActivity(intent);

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(LoginActivity.this, "正在获取验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }

                }/*else{
                    Toast.makeText(LoginActivity.this, "没有获取到验证码", Toast.LENGTH_SHORT).show();

                }*/

            }


        }


    };

    public void WeiXinlogin(View view) {
        Toast.makeText(LoginActivity.this, "正在研发中....", Toast.LENGTH_SHORT).show();

    }
}
