package com.myshops.shops.myshops;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ShanActivity extends AppCompatActivity {

    public static String strName,hasShops;
    public static boolean isFirstLead;
    private Intent intent;
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
                    } else{
                        if ("".equals(strName)){
                            intent =new Intent(ShanActivity.this,LoginActivity.class);
                        } else{
                            if ("".equals(hasShops)){
                                intent =new Intent(ShanActivity.this,OpenActivity.class);
                            } else{
                                intent =new Intent(ShanActivity.this,MainActivity.class);
                            }
                        }
                    }
                    ShanActivity.this.startActivity(intent);
                    ShanActivity.this.finish();
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
    }

    void WriteSharedPreferences(boolean isFirstLead){
        SharedPreferences user = getSharedPreferences("user_info",0);
        SharedPreferences.Editor edit = user.edit();
        edit.putBoolean("isFirstLead", isFirstLead);
        Log.i("isfirst", String.valueOf(isFirstLead));
        edit.commit();
    }
}
