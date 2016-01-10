package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.myshops.shops.adapter.JinhuoAdapter;
import com.myshops.shops.bean.JinHuoJiLu;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class  JinHuoActivity extends AppCompatActivity {
    ListView jinhuo_listView;
    List<JinHuoJiLu> list = new ArrayList<>();
    ImageView iv_back;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jin_huo);
        jinhuo_listView = (ListView) findViewById(R.id.jinhuojilu_listView);
        iv_back = (ImageView) findViewById(R.id.goBacks);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JinHuoActivity.this.finish();
            }
        });


        String t = LoginActivity.token;
        String sql = "select * from wst_user_token where token = '" + t + "'";
        String types = "/Api/exeQuery";
        HashMap<String, String> map = new HashMap<>();
        map.put("sql", sql);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("onSuccess", s.toString());
//                try {
//                    JSONObject jsonobject = new JSONObject(s);
//                    String code = jsonobject.getString("code");
//                    String message = jsonobject.getString("message");
//                    JSONArray data = jsonobject.getJSONArray("data");
//                    String userPhoto = jsonobject.getString("userPhoto");
//                    String userName = jsonobject.getString("userName");
//                    String userPhone = jsonobject.getString("userPhone");
//                    String loginPwd = jsonobject.getString("userPwd");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                try {

                    JSONObject jsonobj = new JSONObject(s);
                    String code = jsonobj.getString("code");
                    String message = jsonobj.getString("message");
                    JSONArray data = jsonobj.getJSONArray("data");
                    JSONObject info = data.getJSONObject(0);
                    Log.i("aaa", "aaa");
                    String userId = info.getString("userId");
                    Log.i("onSuccess", "userId = " + userId);
                    id = userId;

                } catch (Exception e) {
                    e.printStackTrace();
                }

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


        list.add(new JinHuoJiLu(R.drawable.home_baby_img,"严浩办羊毛衫","绿色","ML","2015-12-18","12","42","1245元"));
        jinhuo_listView.setAdapter(new JinhuoAdapter(getApplicationContext(),list));
    }

}
