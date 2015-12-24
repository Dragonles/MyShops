package com.myshops.shops.myshops;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    ProgressDialog pd ;
    Button btn_register_phone;
    RegisterPage registerPage;
    public static String register_userphonenum;
    public static String register_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setMessage("正在注册");

        et_register_phonenum.setEnabled(false);

        SMSSDK.initSDK(this, "da2a5ff6d920", "ab8b1fb4b484e66a99c2275e908df7e5");

        //打开注册页面
        registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    // 提交用户信息
                    registerUser(country, phone);
                }
            }

        });

        btn_register_phone = (Button) findViewById(R.id.btn_register_phonenum);
        final AlertDialog.Builder builder = new AlertDialog.Builder(x.app());
        btn_register_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerPage.show(x.app());

            }
        });

    }

    private void registerUser(String country, String phone) {

        et_register_phonenum.setText(phone);
        Log.i("aaaaa","country-----"+country+"-----phone-----"+phone);
    }

    @ViewInject(R.id.et_register_phonenum)
    private EditText et_register_phonenum;

    @ViewInject(R.id.et_register_pwd)
    private EditText et_register_pwd;

    @Event(R.id.ib_register_back)
    private void RegisterBackEvent(View view){
        Intent intent = new Intent(RegisterActivity.this,LeadActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Event(R.id.btn_register_submit)
    private void RegisterSubmitEvent(View view){

        String pa = "/Api/register";
        register_userphonenum = et_register_phonenum.getText().toString();
        register_password = et_register_pwd.getText().toString();
        HashMap<String, String> map = new HashMap<>();
        map.put("userPhone", register_userphonenum);
        map.put("loginPwd", register_password);
        map.put("isTure", "1");

        pd.show();

        HttpUtils.httputilsPost(pa,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
            //    Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("aaaa", result + "");
                pd.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    if ("200".equals(code)){

                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        RegisterActivity.this.finish();
                        Toast.makeText(x.app(), "注册成功", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(x.app(), "注册失败,"+message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
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
