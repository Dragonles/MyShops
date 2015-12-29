package com.myshops.shops.myshops;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.myshops.shops.untils.HttpUtils;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

@ContentView(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    public static String token;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setMessage("正在登录");
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
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("aaaa", result + "");
                pd.dismiss();
                SharedPreferences preferences = getSharedPreferences("user_info", 0);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    JSONObject data = jsonObject.getJSONObject("data");
                    String username = data.getString("username");
                    token = data.getString("token");
                    String userType = data.getString("userType");
                    SharedPreferences.Editor editor = preferences.edit();

                    if ("200".equals(code)){
                        //存入数据
                        editor.putString("phone",username );
                        editor.putString("userType",userType);
                        editor.putString("token",token);
                        //提交
                        editor.commit();

                        isShopNull();
//
//                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                        intent.putExtra("username",username);
//                        startActivity(intent);
//                        LoginActivity.this.finish();
//                        Toast.makeText(x.app(), "登陆成功", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(x.app(), "登陆信息错误", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("aa","onerror"+ex.getMessage() + "");
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


    public void isShopNull(){
        String pa = "/AllOrders/shopisnull ";

        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);

        pd.show();

        HttpUtils.httputilsGet(pa,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("aaaa", result + "");
                pd.dismiss();
                SharedPreferences preferences = getSharedPreferences("user_info", 0);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
//                    String message = jsonObject.getString("message");
//                    JSONObject data = jsonObject.getJSONObject("data");
//                    String username = data.getString("username");
//                    token = data.getString("token");
//                    String userType = data.getString("userType");
//                    SharedPreferences.Editor editor = preferences.edit();
//
//                    if ("200".equals(code)){
//                        //存入数据
//                        editor.putString("NAME",username );
//                        editor.putString("userType",userType);
//                        //提交
//                        editor.commit();
//                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                        intent.putExtra("username",username);
//                        startActivity(intent);
//                        LoginActivity.this.finish();
//                        Toast.makeText(x.app(), "登陆成功", Toast.LENGTH_SHORT).show();
//                    } else{
//                        Toast.makeText(x.app(), "登陆信息错误", Toast.LENGTH_SHORT).show();
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("aa","onerror"+ex.getMessage() + "");
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
