package com.example.asus.huangxiaoer.view.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.UserBean;
import com.example.asus.huangxiaoer.model.bean.UserPhotoBean;
import com.example.asus.huangxiaoer.model.bean.UsernameBean;
import com.example.asus.huangxiaoer.model.model.MineZiLiaoModel;
import com.example.asus.huangxiaoer.model.util.FrescoUtil;
import com.example.asus.huangxiaoer.model.util.ImageUtil;
import com.example.asus.huangxiaoer.presenter.MineZiLiaoPresenter;
import com.example.asus.huangxiaoer.view.interfaces.MineZiLiao_Interface;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MineZiLiaoActivity extends BaseActivity<MineZiLiaoPresenter> implements View.OnClickListener, MineZiLiao_Interface {


    private SimpleDraweeView mine_zi_liao_userPhoto;
    private TextView mine_zi_liao_mobile;
    private TextView mine_zi_liao_username;


    private PopupWindow mPopupWindowDialog;

    private Button btn_take_photo;
    private Button btn_pick_photo;
    private Button btn_cancel;

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private String name;
    private String username;
    private MultipartBody.Part photouri;
    private File tempFile;
    private int uid;
    private String token;
    private String mobile;
    private String username2;
    private String icon;
    private String qqicon;
    private String qqname;
    private String logintype;
    private String yammodel;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_mine_zi_liao;
    }

    @Override
    protected BaseModel initModel() {
        return new MineZiLiaoModel();
    }

    @Override
    protected MineZiLiaoPresenter initPresenter() {
        return new MineZiLiaoPresenter();
    }


    @Override
    protected void initView() {
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("编辑资料");


        mine_zi_liao_username = findViewById(R.id.mine_zi_liao_username);
        mine_zi_liao_userPhoto = findViewById(R.id.mine_zi_liao_userPhoto);
        mine_zi_liao_mobile = findViewById(R.id.mine_zi_liao_mobile);

        Button btn_ziliao_login = findViewById(R.id.btn_ziliao_login);
        Button btn_ziliao_save = findViewById(R.id.btn_ziliao_save);

        /*赋值*/
        SharedPreferences sp = MineZiLiaoActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
        icon = sp.getString("icon", "0");
        username = sp.getString("username", "0");
        username2 = sp.getString("nickname", "黄小二");
        token = sp.getString("token", "");
        uid = sp.getInt("uid", 0);
        mobile = sp.getString("mobile", "0");

        logintype = sp.getString("logintype", "0");

        if (logintype.equals("qq")) {
            qqicon = sp.getString("qqicon", "0");
            qqname = sp.getString("qqname", "0");
            FrescoUtil.setYuanJiao(qqicon, mine_zi_liao_userPhoto, 50, R.color.white, 1);
            mine_zi_liao_username.setText(qqname);
            mine_zi_liao_username.setKeyListener(null);//不可编辑
            mine_zi_liao_username.setEnabled(true);//可编辑

            mine_zi_liao_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MineZiLiaoActivity.this, "非本地登录,不能修改", Toast.LENGTH_SHORT).show();
                }
            });
        } else if(logintype.equals("yzm")){
            yammodel = sp.getString("yammodel", "0");

            FrescoUtil.setYuanJiao(String.valueOf(R.drawable.youxiang), mine_zi_liao_userPhoto, 50, R.color.white, 1);
            mine_zi_liao_username.setText(yammodel);

            mine_zi_liao_username.setKeyListener(null);//不可编辑
            mine_zi_liao_username.setEnabled(true);//可编辑

            mine_zi_liao_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MineZiLiaoActivity.this, "非本地登录,不能修改", Toast.LENGTH_SHORT).show();
                }
            });
        }else {


            if (token.equals("")) {
                FrescoUtil.setYuanJiao(String.valueOf(R.drawable.youxiang), mine_zi_liao_userPhoto, 50, R.color.white, 1);
                btn_ziliao_save.setVisibility(View.GONE);
                btn_ziliao_login.setVisibility(View.VISIBLE);
                btn_ziliao_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MineZiLiaoActivity.this, LoginActivity.class));
                    }
                });

            } else {
                FrescoUtil.setYuanJiao(icon, mine_zi_liao_userPhoto, 50, R.color.white, 1);
                btn_ziliao_save.setVisibility(View.VISIBLE);
                btn_ziliao_login.setVisibility(View.GONE);
                if (username2.equals("")) {
                    mine_zi_liao_username.setText(username);
                } else {
                    mine_zi_liao_username.setText(username2);
                }
                mine_zi_liao_mobile.setText(mobile);

            }
        }


    }


    @Override
    protected void initData() {
    }

    /*点击提交*/
    public void mine_zi_liao_user_xiugai(View view) {
        if (logintype.equals("qq")) {
            Toast.makeText(MineZiLiaoActivity.this, "非本地登录,不能修改", Toast.LENGTH_SHORT).show();
        } else {

            name = mine_zi_liao_username.getText().toString();
            mine_zi_liao_username.setText(name);
            //*昵称*//*
            //Log.e("123", "mine_zi_liao_user_xiugai: "+name );
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid + "");
            map.put("nickname", name);
            presenter.getPusername(map);

        }
    }

    /*点击图片弹出菜单*/
    public void mine_zi_liao_userPhotos(View view) {
        if (logintype.equals("qq")) {
            Toast.makeText(MineZiLiaoActivity.this, "非本地登录,不能修改", Toast.LENGTH_SHORT).show();
        } else {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View vi = inflater.inflate(R.layout.userphotopopwindow, null);
            btn_take_photo = (Button) vi.findViewById(R.id.btn_take_photo);
            btn_pick_photo = (Button) vi.findViewById(R.id.btn_pick_photo);
            btn_cancel = (Button) vi.findViewById(R.id.btn_cancel);
            btn_take_photo.setOnClickListener(this);
            btn_pick_photo.setOnClickListener(this);
            btn_cancel.setOnClickListener(this);
            /*pop 设置*/
            mPopupWindowDialog = new PopupWindow(vi, ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            mPopupWindowDialog.setFocusable(true);
            mPopupWindowDialog.update();
            mPopupWindowDialog.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindowDialog.setOutsideTouchable(true);
            /*显示 pop 弹框*/
            mPopupWindowDialog.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }

    }

    /*选择弹框选项*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:// 拍照

                // 激活相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory(), "temp_photo.jpg");
                    // 从文件中创建uri
                    Uri uri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

                if (mPopupWindowDialog != null && mPopupWindowDialog.isShowing()) {
                    mPopupWindowDialog.dismiss();
                }
                break;
            case R.id.btn_pick_photo:// 相册


                // 激活系统图库，选择一张图片
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);

                if (mPopupWindowDialog != null && mPopupWindowDialog.isShowing()) {
                    mPopupWindowDialog.dismiss();
                }
                break;

            case R.id.btn_cancel: // 取消
                if (mPopupWindowDialog != null && mPopupWindowDialog.isShowing()) {
                    mPopupWindowDialog.dismiss();
                }
                break;

        }
    }


    /**
     * 返回结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            //            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(MineZiLiaoActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                /**
                 * 获得图片
                 */
                mine_zi_liao_userPhoto.setImageBitmap(bitmap);
                //保存到SharedPreferences
                saveBitmapToSharedPreferences(bitmap);

            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //保存图片到SharedPreferences
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));

        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("huangxiaoer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();

        //上传头像
        setImgByStr(bitmap);
    }

    /**
     * 上传头像
     */
    public void setImgByStr(Bitmap bitmap) {
        if (bitmap != null) {
            // 拿着imagePath上传了
        }
        String imagePath = ImageUtil.savePhoto(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));

        File file = new File(imagePath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
        photouri = MultipartBody.Part.createFormData("file", file.getName(), photoRequestBody);

        /*头像*/
        presenter.getPuserphoto(uid, photouri);
    }

    /*  //从SharedPreferences获取图片
     private void getBitmapFromSharedPreferences() {
          SharedPreferences sharedPreferences = getSharedPreferences("testSP", Context.MODE_PRIVATE);
          //第一步:取出字符串形式的Bitmap
          String imageString = sharedPreferences.getString("image", "");
          //第二步:利用Base64将字符串转换为ByteArrayInputStream
          byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
          if (byteArray.length == 0) {
              mine_zi_liao_userPhoto.setImageResource(R.mipmap.ic_launcher);
          } else {
              ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
              //第三步:利用ByteArrayInputStream生成Bitmap
              Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
              mine_zi_liao_userPhoto.setImageBitmap(bitmap);

          }

      }
  */
    /*返回结果*/
    @Override
    public void onPhotoSuccess(UserPhotoBean userPhotoBean) {
        //Log.e("123", "onPhotoSuccess: " + "图片");
    }

    @Override
    public void onUserNameSuccess(UsernameBean usernameBean) {


        if (usernameBean.getMsg().equals("token超时")) {
            Toast.makeText(MineZiLiaoActivity.this, "登录时间过长,请重新登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MineZiLiaoActivity.this, LoginActivity.class));

        } else {
            /*信息*/
            HashMap<String, String> map3 = new HashMap<>();
            map3.put("uid", uid + "");
            map3.put("token", token);
            presenter.getPuser(map3);
        }


    }

    @Override
    public void onZiLiaoUserSuccess(UserBean userBean) {

        SharedPreferences spp = MineZiLiaoActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
        SharedPreferences.Editor edit = spp.edit();
        //通过editor对象写入数据
        edit.putString("nickname", userBean.getData().getNickname() + "");
        edit.putString("icon", userBean.getData().getIcon());
        edit.commit();

        /*赋值*/
        //  SharedPreferences p = MineZiLiaoActivity.this.getSharedPreferences("huangxiaoer", MODE_PRIVATE);
        //  String icon2 = p.getString("icon", "0");
        // String name2= p.getString("nickname", "黄小二");

        //  mine_zi_liao_username.setText( name2);
        //  FrescoUtil.setYuanJiao(icon2, mine_zi_liao_userPhoto, 50, R.color.white, 1);
        Toast.makeText(MineZiLiaoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();


    }


}