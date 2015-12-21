package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class OpenActivity extends AppCompatActivity {

    // 店铺照片  用户手持身份证照片
    private ImageView iv_open_storepicture,iv_open_userpicture;
    // 用户真实姓名  用户身份证号  用户手机号  用户登录密码  店铺介绍
    private EditText et_open_username,et_open_useridcard,et_open_userphonenum,et_open_userpwd,et_open_userstore;
    private Button btn_open_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        iv_open_storepicture = (ImageView) findViewById(R.id.iv_open_storepicture);
        iv_open_userpicture = (ImageView) findViewById(R.id.iv_open_userpicture);
        et_open_username = (EditText) findViewById(R.id.et_open_username);
        et_open_useridcard = (EditText) findViewById(R.id.et_open_useridcard);
        et_open_userphonenum = (EditText) findViewById(R.id.et_login_phonenum);
        et_open_userpwd = (EditText) findViewById(R.id.et_login_pwd);
        et_open_userstore = (EditText) findViewById(R.id.et_open_userstore);
        btn_open_submit = (Button) findViewById(R.id.btn_open_submit);

    }

}
