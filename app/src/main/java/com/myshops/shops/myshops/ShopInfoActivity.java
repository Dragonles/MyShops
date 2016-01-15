package com.myshops.shops.myshops;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myshops.shops.untils.ActionSheetDialog;
import com.myshops.shops.untils.HttpUtils;
import com.myshops.shops.untils.QiNiuConfig;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.util.Auth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;


public class ShopInfoActivity extends AppCompatActivity {

    private RelativeLayout rly_mima;
    private TextView tv_name, tv_phone, tv_pwd, tv_tijiao;
    private ImageView iv_shopinfo_shopheader;
    ImageButton ib_shopinfo_back;
    Button btn_shopinfo_exit;
    String smima = "", sname = "", sphone = "";
    static String id, os, ns, nsa, userName, userPhone, userPwd, userPhoto;
    private Uri photoUri;
    private final int PIC_FROM_CAMERA = 1;
    private final int PIC_FROM＿LOCALPHOTO = 0;
    static File picFile;

    EditText et_oldpwd, et_newpwd, et_newpwd_algin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);


        rly_mima = (RelativeLayout) findViewById(R.id.rly_mima);
        tv_name = (TextView) findViewById(R.id.et_shopinfo_shopname_go);
        tv_phone = (TextView) findViewById(R.id.ib_settle_invoice_go);
        tv_pwd = (TextView) findViewById(R.id.tv_pwd);
        iv_shopinfo_shopheader = (ImageView) findViewById(R.id.iv_shopinfo_shopheader);
        ib_shopinfo_back = (ImageButton) findViewById(R.id.ib_shopinfo_back);
        btn_shopinfo_exit = (Button) findViewById(R.id.btn_shopinfo_exit);
        tv_tijiao = (TextView) findViewById(R.id.tv_tijiao);

        xiaZai();

        ib_shopinfo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopInfoActivity.this.finish();
            }
        });

        iv_shopinfo_shopheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(ShopInfoActivity.this)
                        .builder()
                        .setTitle("选项")
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍摄照片", ActionSheetDialog.SheetItemColor.Red,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        doHandlerPhoto(PIC_FROM_CAMERA);// 用户点击了从照相机获取
                                        // onActivityResult(PIC_FROM_CAMERA,0,null);
                                    }
                                })
                        .addSheetItem("选取本地", ActionSheetDialog.SheetItemColor.Red,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
                                    }
                                }).show();
            }
        });

        rly_mima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ShopInfoActivity.this);
                LayoutInflater inflater = (LayoutInflater) ShopInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.dialogview2, null);
                dialog.setView(layout);
                et_oldpwd = (EditText) layout.findViewById(R.id.et_oldpwd);
                et_newpwd = (EditText) layout.findViewById(R.id.et_newpwd);
                et_newpwd_algin = (EditText) layout.findViewById(R.id.et_newpwd_algin);
                dialog.setTitle("输入更改的密码");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        os = et_oldpwd.getText().toString();
                        ns = et_newpwd.getText().toString();
                        nsa = et_newpwd_algin.getText().toString();
                        if (ns != "") {

                            if (ns.equals(nsa)) {
                                Log.i("mima", et_newpwd.getText().toString() + "  " + et_newpwd_algin.getText().toString());
                                tv_pwd.setText(ns);
                                smima = ns;
                            } else {

                                Log.i("mima", et_newpwd.getText().toString() + " a " + et_newpwd_algin.getText().toString());
                                Toast.makeText(ShopInfoActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ShopInfoActivity.this, "密码不可为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });


        final SharedPreferences token = getSharedPreferences("user_info", 0);
        final String t = token.getString("token", "");
        Log.i("ShopToken", t);

        String sqll = "select * from wst_user_token where token = '" + t + "'";
        String types = "/Api/exeQuery";
        HashMap<String, String> map = new HashMap<>();
        map.put("sql", sqll);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("onSuccess", s.toString());
                try {

                    JSONObject jsonobj = new JSONObject(s);
                    String code = jsonobj.getString("code");
                    String message = jsonobj.getString("message");
                    JSONArray data = jsonobj.getJSONArray("data");
                    JSONObject info = data.getJSONObject(0);
                    Log.i("aaa", "aaa");
                    String userId = info.getString("userId");
                    Log.i("onSuccess", "userId = " + userId);
                    id = userId;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                String sql = "select * from wst_shops where userId = '" + id + "'";
                String type = "/Api/exeQuery";
                HashMap<String, String> maps = new HashMap<>();
                maps.put("sql", sql);
                HttpUtils.httputilsGet(type, maps, new CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        Log.i("userphoto", s.toString());

                        try {
                            JSONObject jsonobject = new JSONObject(s);
                            String code = jsonobject.getString("code");
                            String message = jsonobject.getString("message");
                            JSONArray data = jsonobject.getJSONArray("data");
                            userName = jsonobject.getString("shopName");
                            userPhone = jsonobject.getString("shopTel");
                            userPhoto = jsonobject.getString("shopImg");
                            tv_name.setText(userName);
                            tv_phone.setText(userPhone);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Log.i("onError", throwable.toString());
                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("onError", throwable.toString());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });

        showImage();

        tv_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sname = tv_name.getText().toString();
                sphone = tv_phone.getText().toString();

                if (sname != null && sphone != null && smima != null) {

                    upLoadImage();

                    String type = "/Api/eidtUserPwd";
                    HashMap<String, String> maps = new HashMap<>();
                    maps.put("token", t);
                    maps.put("loginPwd", os);
                    maps.put("newLoginPwd", ns);
                    HttpUtils.httputilsGet(type, maps, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {

                            Log.i("onSuccess", os + ns + result.toString());
                            Log.i("onSuccesss", "走方法");
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Log.i("onErrors", ex.toString() + isOnCallback);


                            Log.i("onSuccesss", "走方法");
                        }


                        @Override
                        public void onCancelled(CancelledException cex) {


                            Log.i("onSuccesss", "走方法");
                        }

                        @Override
                        public void onFinished() {


                            Log.i("onSuccesss", "走方法");
                        }
                    });


                    Toast.makeText(ShopInfoActivity.this, "操作成功", Toast.LENGTH_SHORT).show();

                    ShopInfoActivity.this.finish();
                } else {
                    Toast.makeText(ShopInfoActivity.this, "请输入合法信息", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_shopinfo_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences user = getSharedPreferences("user_info",0);
                SharedPreferences.Editor users = user.edit();
                users.putString("NAME","");
                users.commit();

                ShopInfoActivity.this.finish();
                Intent intent = new Intent(ShopInfoActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }


    public void shopInfoExit(View view){
        Intent intent = new Intent(ShopInfoActivity.this,LoginActivity.class);
        SharedPreferences user = getSharedPreferences("user_info",0);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("NAME", "");
        edit.commit();
        startActivity(intent);
    }

    private void doHandlerPhoto(int type) {
        try {
            // 保存裁剪后的图片文件
            File pictureFileDir = new File(
                    Environment.getExternalStorageDirectory(), "/maimai");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            int rand=(int)(Math.random()*100000);
            Log.i("ran",rand+"");
            picFile = new File(pictureFileDir, rand+".jpeg");
            if (!picFile.exists()) {
                picFile.createNewFile();
                Log.i("imgs",picFile.toString());
            }
            photoUri = Uri.fromFile(picFile);
            Log.i("img",photoUri.toString());
            if (type == PIC_FROM＿LOCALPHOTO) {
                Intent intent = getCropImageIntent();
                startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
            } else {
                Intent cameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, PIC_FROM_CAMERA);
            }

        } catch (Exception e) {
            Log.i("HandlerPicError", "处理图片出现错误");
        }
    }

    /**
     * 调用图片剪辑程序
     */
    public Intent getCropImageIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        setIntentParams(intent);
        return intent;
    }

    /**
     * 启动裁剪
     */
    private void cropImageUriByTakePhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        setIntentParams(intent);
        startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
    }

    /**
     * 设置公用参数
     */
    private void setIntentParams(Intent intent)
    {
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case PIC_FROM_CAMERA: // 拍照
                try
                {
                    cropImageUriByTakePhoto();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case PIC_FROM＿LOCALPHOTO:
                try
                {
                    if (photoUri != null)
                    {
                        Bitmap bitmap = decodeUriAsBitmap(photoUri);
                        iv_shopinfo_shopheader.setImageBitmap(bitmap);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri)
    {
        Bitmap bitmap = null;
        try
        {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 生成七牛上传token
     * */
    public String qiNiuUpToken(){
        //七牛key
        Auth auth = Auth.create(QiNiuConfig.ak,QiNiuConfig.sk);
        //七牛空间名称
        String bucketName = QiNiuConfig.bucketName;
        //生成上传token
        String token = auth.uploadToken(bucketName);
        return token;
    }

    private void upLoadImage(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                //开始上传文件
                try {

                    UploadManager uploadManager = new UploadManager();
                    uploadManager.put(picFile, suiJiName(), qiNiuUpToken(), new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info, JSONObject response) {

                            Log.i("qiniu", key + " " + info + " " + response);
                            userPhoto = key;

                            if (sname.equals(null))
                                sname = tv_name.getText().toString();
                            if (sphone.equals(null))
                                sphone = tv_phone.toString();
                            String sql = "update wst_shops set  shopName = '" + sname + "', shopTel = '" + sphone + "',  shopImg = '" + userPhoto + "' where userId = " + id;
                            String types = "/Api/exeQuery";
                            HashMap<String, String> map = new HashMap<>();
                            map.put("sql", sql);
                            HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Log.i("onSuccesses", s.toString());

                                }

                                @Override
                                public void onError(Throwable throwable, boolean b) {
                                    Log.i("onErrores", throwable.toString());
                                }

                                @Override
                                public void onCancelled(CancelledException e) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                        }
                    }, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public String suiJiName(){
        //随机数
        String s = "";
        Random ran =new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            s = s + ran.nextInt(100);
        }
        //上传的文件名
        String keyname = "wst_"+s+".jpg";
        return  keyname;
    }

    public void showImage(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                String url = "http://7xpmv7.com1.z0.glb.clouddn.com/Fj3g6jWLrUlRvm3TvcZviHbeM0YZ";
                iv_shopinfo_shopheader.setImageBitmap(BitmapFactory.decodeFile(url));
                Log.i("showImage","走方法");
            }
        }).start();
    }

    public void xiaZai(){

        String sql = "select shopImg, shopName, shopTel from wst_shops where userId = '" + id + "'";
        String type = "/Api/exeQuery";
        HashMap<String, String> maps = new HashMap<>();
        maps.put("sql", sql);
        HttpUtils.httputilsGet(type, maps, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("userphoto", s.toString());

                try {
                    JSONObject jsonobj = new JSONObject(s);
                    String code = jsonobj.getString("code");
                    String message = jsonobj.getString("message");
                    JSONArray data = jsonobj.getJSONArray("data");
                    JSONObject info = data.getJSONObject(0);
                    String images = info.getString("shopImg");
                    String userName = info.getString("shopName");
                    String userPhone = info.getString("shopTel");
                    tv_name.setText(userName);
                    tv_phone.setText(userPhone);
                    //图片外链地址（网络地址）
                    String url2 = QiNiuConfig.externalLinks + images;
                    //加载（下载）图片  iv_add4为ImageView
                    Log.i("url2", url2);
                    Glide.with(ShopInfoActivity.this).load(url2).into(iv_shopinfo_shopheader);

                }catch (Exception e){

                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("onError", throwable.toString());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
