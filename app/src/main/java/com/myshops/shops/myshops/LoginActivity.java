package com.myshops.shops.myshops;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.myshops.shops.untils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

@ContentView(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    /**
     * 软键盘的控制
     */
    private InputMethodManager mSoftManager;
    public static String token,shopId;
    ProgressDialog pd;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    //用户头像
    String image="";
    String TEMPS = "登陆异常，请重试！";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setMessage("正在登录");
        preferences = getSharedPreferences("user_info", 0);
        editor = preferences.edit();
    }

    @Event(R.id.ib_login_back)
    private void LoginBackEvent(View view){
        Intent intent = new Intent(LoginActivity.this,LeadActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Event(R.id.tv_login_toregister)
    private void LoginToRegisterEvent(View view){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }

    @ViewInject(R.id.et_login_phonenum)
    private EditText et_login_phonenum;

    @ViewInject(R.id.et_login_pwd)
    private EditText et_login_pwd;

    @Event(R.id.btn_login_submit)
    private void LoginSubmitEvent(View view){

        String userloginname = et_login_phonenum.getText().toString();
        String userloginpwd = et_login_pwd.getText().toString();

        if ("".equals(userloginname) || "".equals(userloginpwd)){
            Toast.makeText(LoginActivity.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
        } else {

            String pa = "/Api/login";
            String loginPhone = et_login_phonenum.getText().toString();
            String loginPassword = et_login_pwd.getText().toString();
            HashMap<String, String> map = new HashMap<>();
            map.put("loginName", loginPhone);
            map.put("loginPwd", loginPassword);
            map.put("clientType", "android");

            pd.show();

            HttpUtils.httputilsPost(pa,map, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    //  Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                    Log.i("aaaa", result + "");

                    String code = null;
                    String message = null;
                    JSONObject data;
                    String username = null;
                    String userType = null;
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        code = jsonObject.getString("code");
                        message = jsonObject.getString("message");
                        data = jsonObject.getJSONObject("data");
                        username = data.getString("username");
                        token = data.getString("token");
                        image = data.getString("userPhoto");
                        userType = data.getString("userType");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if ("200".equals(code)){
                        //存入数据
                        editor.putString("NAME",username );
                        editor.putString("userType",userType);
                        editor.putString("token",token);
                        rongyun();

                    } else{
                        pd.dismiss();
                        Toast.makeText(x.app(), ""+message, Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    Log.i("aa","onerror"+ex.getMessage() + "");
                    pd.dismiss();
                    Toast.makeText(x.app(), TEMPS, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    //    Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    Log.i("aa", x.app() + "");
                }
                @Override
                public void onFinished() {
                    //    Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    Log.i("aa",x.app()+"");
                }
            });
        }
    }


    public void isShopNull(){
        String pa = "/AllOrders/shopisnull";
        HashMap<String, String> map = new HashMap<>();
        Log.i("aaaa","token"+token);
        map.put("token", token);

        Log.i("aaaa","走着步1");
        HttpUtils.httputilsPost(pa,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("aaaa","走着步2"+result+"------------");
             //   Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("aaaa", result + "");
                pd.dismiss();
                Intent intent;
                String code = null;
                String date = null;
                String message = null;

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    code = jsonObject.getString("code");
                    date = jsonObject.getString("data");
                    message = jsonObject.getString("message");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("logindata",date);
                if ("200".equals(code)){

                    if ("1".equals(date)){
                        //存在店铺信息跳转主界面
                        //存入数据
                        editor.putString("hasShops",date);
                        getShops();
                        intent = new Intent(LoginActivity.this,MainActivity.class);
                        Toast.makeText(x.app(), "登陆成功", Toast.LENGTH_SHORT).show();
                    } else {
                        //未开通店铺，跳转开通店铺界面
                        //存入数据
                        editor.putString("hasShops","");

                        intent = new Intent(LoginActivity.this,OpenActivity.class);

                    }
                    //提交
                    editor.commit();
                    startActivity(intent);
                    LoginActivity.this.finish();

                } else{
                    Toast.makeText(x.app(), ""+message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("aa","onerror"+ex.getMessage() + "");
                Log.i("aaaa","走着步3----------"+ex.getMessage()+"------------");
                pd.dismiss();
                Toast.makeText(x.app(),"系统出现异常，请稍后再试！",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                //    Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                Log.i("aa","这里"+ x.app() + "");
            }
            @Override
            public void onFinished() {
                //    Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                Log.i("aa",x.app()+"");
            }
        });
    }
    //获取用户的ShopId
    public void getShops(){
        HashMap<String,String> hashMap_shopid = new HashMap<>();
        Log.i("GG","TOKEN"+token);
        hashMap_shopid.put("token",token);
        hashMap_shopid.remove("sign");
        HttpUtils.httputilsGet("/Long/returnshopid", hashMap_shopid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","成功de数据"+result);
                try {
                    JSONObject res = new JSONObject(result);
                    String code = res.getString("code");
                    if("200".equals(code)){
                        String data = res.getString("data");
                        shopId = data;
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("shopId",data);
                        editor.commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("GG","错误"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    // 获取融云 Token
    public void rongyun(){
        String params = "/AllOrders/getRongyunToken";
        String loginName = et_login_phonenum.getText().toString();
        HashMap<String ,String> map = new HashMap<String,String>();
        map.put("token",token);
        map.put("name",loginName);
        map.put("userImage",image);
        map.put("appKey","p5tvi9dstzq54");
        map.put("AppSecret","3wrHOYn2n3MV");
        HttpUtils.httputilsPost(params, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("androids","onSuccess————"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject data = jsonObject.getJSONObject("data");
                    String rongyunid = data.getString("userId");
                    String rongyunToken = data.getString("token");
                    editor.putString("rongyuntoken",rongyunToken);
                    editor.putString("userId",rongyunid);
                    rong(rongyunToken);
                    Log.i("rongyun","----rongyunid----"+rongyunid+"----rongyunToken----"+rongyunToken);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("onSuccess","onError————"+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    protected void onPause() {
        super.onPause();
        if (mSoftManager == null) {
            mSoftManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (getCurrentFocus() != null) {
            mSoftManager.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), 0);// 隐藏软键盘
        }
    }
    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void rong(String token) {
        /**
         * IMKit SDK调用第二步,建立与服务器的连接
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
             */
            @Override
            public void onTokenIncorrect() {

                Log.d("LoginActivitys", "--onTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(String userid) {

                Log.d("LoginActivitys", "--onSuccess" + userid);
                isShopNull();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             *                  http://www.rongcloud.cn/docs/android.html#常见错误码
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

                Log.d("LoginActivitys", "--onError" + errorCode);
            }
        });
    }
}
