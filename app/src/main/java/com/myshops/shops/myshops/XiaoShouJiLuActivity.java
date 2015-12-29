package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.myshops.shops.bean.Shouruyuzhichu;
import com.myshops.shops.untils.HttpUtils;


import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XiaoShouJiLuActivity extends AppCompatActivity {
    List<Shouruyuzhichu> list = new ArrayList<>();
    private  ListView listView;
    private ImageView iv_back;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_shou_ji_lu);

        listView = (ListView) findViewById(R.id.xiaoshou_listView);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XiaoShouJiLuActivity.this.finish();
            }
        });

        String t = LoginActivity.token;
        String sql = "select * from wst_user_token where token = '" + t + "'";
//        String sql = "select * from wst_users where userId = " + sqll;
        String types = "/Api/exeQuery";
        HashMap<String, String> map = new HashMap<>();
        map.put("sql", sql);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("onSuccess", s.toString());
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("onError", throwable.toString());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });


        list.add(new Shouruyuzhichu(R.drawable.shangpin1,"毛衣","灰色","L","23","45","34","进货"));
    }

}
