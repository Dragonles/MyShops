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

import java.net.HttpURLConnection;
import java.util.HashMap;

@ContentView(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    public static String token;
    ProgressDialog pd;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
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

                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        JSONObject data = jsonObject.getJSONObject("data");
                        String username = data.getString("username");
                        token = data.getString("token");
                        String userType = data.getString("userType");

                        if ("200".equals(code)){
                            //存入数据
                            editor.putString("NAME",username );
                            editor.putString("userType",userType);
                            editor.putString("token",token);
                            isShopNull();

                        } else{
                            Toast.makeText(x.app(), "登陆失败，"+message, Toast.LENGTH_SHORT).show();
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
    }


    public void isShopNull(){
        String pa = "/AllOrders/shopisnull";
        HashMap<String, String> map = new HashMap<>();
        Log.i("aaaa",token);
        map.put("token", token);

        Log.i("aaaa","走着步1");
        HttpUtils.httputilsGet(pa,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("aaaa","走着步2"+result+"------------");
             //   Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("aaaa", result + "");
                pd.dismiss();
                Intent intent;


                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    String date = jsonObject.getString("data");

                    if ("200".equals(code)){

                        if ("1".equals(date)){
                            //存在店铺信息跳转主界面
                            //存入数据
                            editor.putString("hasShops",date);

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
                        Toast.makeText(x.app(), "信息错误，请重新登陆", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("aa","onerror"+ex.getMessage() + "");
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


}
