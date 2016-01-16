package com.myshops.shops.myshops;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class ShanActivity extends AppCompatActivity {

    public static String strName,hasShops,userid;
    public static boolean isFirstLead;
    private Intent intent;
    private static String rongyuntoken,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shan);
        new Thread (new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*
                    *  延迟3秒后跳转引导页（LeadActivity）
                    * */
                    ReadSharedPreferences();
                    Log.i("isfirst",isFirstLead+"");
                    if (isFirstLead==false){
                        isFirstLead = true;
                        WriteSharedPreferences(isFirstLead);
                        intent =new Intent(ShanActivity.this,LeadActivity.class);
                        ShanActivity.this.startActivity(intent);
                        ShanActivity.this.finish();
                    } else{
                        if (("".equals(strName)) || ("".equals(rongyuntoken))){
                            intent =new Intent(ShanActivity.this,LoginActivity.class);
                            ShanActivity.this.startActivity(intent);
                            ShanActivity.this.finish();
                        } else{
                            getNewToken();

                        }
                    }
                    break;
                }
            }
        }).start();
    }

    void ReadSharedPreferences(){
        SharedPreferences user = getSharedPreferences("user_info",0);
        isFirstLead = user.getBoolean("isFirstLead",false);
        strName = user.getString("NAME","");
        hasShops = user.getString("hasShops","");
        rongyuntoken = user.getString("rongyuntoken","");
        userid = user.getString("userId","");
        token = user.getString("token","");
    }

    void WriteSharedPreferences(boolean isFirstLead){
        SharedPreferences user = getSharedPreferences("user_info",0);
        SharedPreferences.Editor edit = user.edit();
        edit.putBoolean("isFirstLead", isFirstLead);
        Log.i("isfirst", String.valueOf(isFirstLead));
        edit.commit();
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

    // 获取最新 Token

    public void getNewToken(){
        String types = "/Api/exeQuery";
        String sql = "select token from wst_user_token where userId = '"+userid+"'";
        HashMap<String ,String> map = new HashMap<String,String>();
        map.put("sql",sql);
        HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("shanget",result+"");
                String newToken;
                String code;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    code = jsonObject.getString("code");
                    JSONArray date = jsonObject.getJSONArray("data");
                    JSONObject datajson = date.getJSONObject(0);
                    newToken = datajson.getString("token");
                    if ("200".equals(code)){
                        //请求成功
                        if (token.equals(newToken)){
                            // 与上次登录 token 一致 无须重新登陆
                            // 与融云建立连接
                            rong(rongyuntoken);
                            if ("".equals(hasShops)){
                                intent =new Intent(ShanActivity.this,OpenActivity.class);
                            } else{
                                intent =new Intent(ShanActivity.this,MainActivity.class);
                            }
                        } else {
                            // 账号已在其他设备登陆，重新登陆
                            Toast.makeText(ShanActivity.this,"登陆已过期，请重新登陆！",Toast.LENGTH_SHORT).show();
                            intent = new Intent(ShanActivity.this,LoginActivity.class);
                        }
                        startActivity(intent);
                        ShanActivity.this.finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    }

}
