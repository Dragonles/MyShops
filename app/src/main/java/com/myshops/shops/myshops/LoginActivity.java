package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText et_login_phonenum,et_login_pwd;
    private Button btn_login_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_login_phonenum = (EditText) findViewById(R.id.et_login_phonenum);
        et_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        btn_login_submit = (Button) findViewById(R.id.btn_login_submit);
        btn_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenum = et_login_phonenum.getText().toString();
                String password = et_login_pwd.getText().toString();
                if (isMobileNO(phonenum)){

                }
            }
        });
    }

    // 正则判断是否为正确的手机号格式
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    // 判断密码格式是否正确
    public boolean isPassWord(String pwd){

        int pwdlength = pwd.length();

        if (pwdlength < 6 && pwdlength >= 20){
            // 密码长度为 6-20 位
        }

        return true;
    }

}
