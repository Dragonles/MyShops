package com.myshops.shops.myshops;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static String token;

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

        HttpUtils.httputilsPost(pa,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("aaaa", result + "");


                SharedPreferences preferences = getSharedPreferences("muser", Context.MODE_PRIVATE);

                try {
                    Log.i("codessss","走1");
                    JSONObject jsonObject = new JSONObject(result);
                    Log.i("codessss","走2");
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
                        editor.putString("tokens",token);
                        //提交
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
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




}
