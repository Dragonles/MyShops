package com.myshops.shops.myshops;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
import com.myshops.shops.bean.CircleImageView;
import com.myshops.shops.fragment.ShopFragment;
import com.myshops.shops.untils.Config;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.MD5;

import java.util.HashMap;


public class ShopInfoActivity extends AppCompatActivity {

    private RelativeLayout rly_name, rly_phone, rly_mima;
    private TextView tv_name, tv_phone, tv_pwd;
    private ImageView iv_shopinfo_shopheader;
    private static final int RESULT_LOAD_IMAGE = 1;
    String searchC;
    public String sURL;
    ImageButton ib_shopinfo_back;
    Button btn_shopinfo_exit;
    String smima = "", sname = "", sphone = "";
    static String id, os, ns, nsa, userName, userPhone, userPwd;

//    CircleImageView imageView;

    EditText et_search, et_oldpwd, et_newpwd,et_newpwd_algin;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        rly_name = (RelativeLayout) findViewById(R.id.rly_name);
        rly_phone = (RelativeLayout) findViewById(R.id.rly_phone);
        rly_mima = (RelativeLayout) findViewById(R.id.rly_mima);
        tv_name = (TextView) findViewById(R.id.ib_shopinfo_shopname_go);
        tv_phone = (TextView) findViewById(R.id.ib_settle_invoice_go);
        tv_pwd = (TextView) findViewById(R.id.tv_pwd);
        iv_shopinfo_shopheader = (ImageView) findViewById(R.id.iv_shopinfo_shopheader);
        ib_shopinfo_back = (ImageButton) findViewById(R.id.ib_shopinfo_back);
        btn_shopinfo_exit = (Button) findViewById(R.id.btn_shopinfo_exit);

        iv_shopinfo_shopheader.setImageBitmap(BitmapFactory.decodeFile(ShopFragment.userPhoto));
        tv_name.setText(ShopFragment.userName);
        tv_phone.setText(ShopFragment.userPhone);
        ib_shopinfo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopInfoActivity.this.finish();
            }
        });

        iv_shopinfo_shopheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到本地选择图片
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        rly_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(ShopInfoActivity.this);
                LayoutInflater inflater = (LayoutInflater) ShopInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialogview, null);
                dialog.setView(layout);
                et_search = (EditText) layout.findViewById(R.id.searchC);
                dialog.setTitle("输入更改店铺名");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        searchC = et_search.getText().toString();
                        tv_name.setText(searchC);
                        sname = tv_name.getText().toString();
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

        rly_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ShopInfoActivity.this);
                LayoutInflater inflater = (LayoutInflater) ShopInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialogview, null);
                dialog.setView(layout);
                et_search = (EditText) layout.findViewById(R.id.searchC);
                dialog.setTitle("输入更改的手机号码");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        searchC = et_search.getText().toString();
                        tv_phone.setText(searchC);
                        sphone = tv_phone.getText().toString();
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
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
                                Log.i("mima",et_newpwd.getText().toString() + "  " + et_newpwd_algin.getText().toString());
                                tv_pwd.setText(ns);
                                smima = ns;


                            } else {

                                Log.i("mima",et_newpwd.getText().toString() + " a " + et_newpwd_algin.getText().toString());
                                Toast.makeText(ShopInfoActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }else{
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
        final SharedPreferences token = getSharedPreferences("user_info",0);
        final String t = token.getString("token","");
        Log.i("ShopToken",t);

        String sqll = "select * from wst_user_token where token = '" + t + "'";
//        String sql = "select * from wst_users where userId = " + sqll;
        String types = "/Api/exeQuery";
        HashMap<String, String> map = new HashMap<>();
        map.put("sql", sqll);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("onSuccess", s.toString());
//                try {
//                    JSONObject jsonobject = new JSONObject(s);
//                    String code = jsonobject.getString("code");
//                    String message = jsonobject.getString("message");
//                    JSONArray data = jsonobject.getJSONArray("data");
//                    String userPhoto = jsonobject.getString("userPhoto");
//                    String userName = jsonobject.getString("userName");
//                    String userPhone = jsonobject.getString("userPhone");
//                    String loginPwd = jsonobject.getString("userPwd");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
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

                String sql = "select * from wst_users where userId = '" + id + "'";
                String type = "/Api/exeQuery";
                HashMap<String, String> maps = new HashMap<>();
                maps.put("sql", sql);
                HttpUtils.httputilsGet(type, maps, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        Log.i("userphoto", s.toString());

                        try {
                            JSONObject jsonobject = new JSONObject(s);
                            String code = jsonobject.getString("code");
                            String message = jsonobject.getString("message");
                            JSONArray data = jsonobject.getJSONArray("data");
                            userName = jsonobject.getString("userName");
                            userPhone = jsonobject.getString("userPhone");
                            userPwd = jsonobject.getString("userPwd");

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




        btn_shopinfo_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sname != null && sphone != null && smima != null) {



//                    Log.i("update", sname + " " + sphone + " " + smima + " " );
//
//                    String sql = "update wst_users set  userName = '" + sname + "', userPhone = '" + sphone + "', where userId = " + id  ;
//                    String types = "/Api/exeQuery";
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("sql", sql);
//                    HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
//                        @Override
//                        public void onSuccess(String s) {
//                            Log.i("onSuccess", s.toString());
//                        }
//
//                        @Override
//                        public void onError(Throwable throwable, boolean b) {
//                            Log.i("onError", throwable.toString());
//                        }
//
//                        @Override
//                        public void onCancelled(CancelledException e) {
//
//                        }
//
//                        @Override
//                        public void onFinished() {
//
//                        }
//                    });


                    //String typess = "/Api/uploadImage";


                    HttpUtils.httpPostImage(sURL, t, "/ApiUp/uploadImage", new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.i("uploadImage", result+ " "+ t + " " + sURL);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });








//
//
//                    String type = "/Api/eidtUserPwd";
//                    HashMap<String , String> maps = new HashMap<>();
//                    maps.put("token",t);
//                    maps.put("loginPwd", os);
//                    maps.put("newLoginPwd",ns);
//                    HttpUtils.httputilsGet(type, maps, new Callback.CommonCallback<String>(){
//                        @Override
//                        public void onSuccess(String result) {
//
//                            Log.i("onSuccess", os + ns + result.toString());
//                            Log.i("onSuccesss","走方法");
//                        }
//
//                        @Override
//                        public void onError(Throwable ex, boolean isOnCallback) {
//                            Log.i("onErrors", ex.toString() + isOnCallback);
//
//
//                            Log.i("onSuccesss","走方法");
//                        }
//
//
//                        @Override
//                        public void onCancelled(CancelledException cex) {
//
//
//                            Log.i("onSuccesss","走方法");
//                        }
//
//                        @Override
//                        public void onFinished() {
//
//
//                            Log.i("onSuccesss","走方法");
//                        }
//                    });

                    Toast.makeText(ShopInfoActivity.this, "操作成功", Toast.LENGTH_SHORT).show();

                    ShopInfoActivity.this.finish();
                } else {
                    Toast.makeText(ShopInfoActivity.this, "请输入合法信息", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            sURL = picturePath;
            iv_shopinfo_shopheader.setVisibility(View.VISIBLE);
            iv_shopinfo_shopheader.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        } else {
            //Toast.makeText(getApplicationContext(), "图片选择异常", Toast.LENGTH_LONG).show();
        }
    }
    
}
