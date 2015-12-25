package com.myshops.shops.myshops;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LeadActivity extends AppCompatActivity {

    Button login_btn,regist_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        login_btn=(Button)findViewById(R.id.login_btn);
        regist_btn=(Button)findViewById(R.id.regist_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LeadActivity.this,LoginActivity.class);
                startActivity(intent);
                LeadActivity.this.finish();
            }
        });
        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LeadActivity.this,RegisterActivity.class);
                startActivity(intent);
                LeadActivity.this.finish();
            }
        });
    }

}
