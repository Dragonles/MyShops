package com.myshops.shops.myshops;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.myshops.shops.adapter.shouruAdapter;
import com.myshops.shops.bean.Shouruyuzhichu;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShouRuZhiChuActivity extends AppCompatActivity {
    List<Shouruyuzhichu> list = new ArrayList<>();
    ListView listView;
    CheckBox cb_kaiguan;
    TextView tv_shouru, tv_zhichu;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ru_zhi_chu);
        listView = (ListView) findViewById(R.id.shouru_listView);
        cb_kaiguan = (CheckBox) findViewById(R.id.shouru_flag);
        tv_shouru = (TextView) findViewById(R.id.tv_shouru);
        tv_zhichu = (TextView) findViewById(R.id.tv_zhichu);

        cb_kaiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_kaiguan.isChecked()) {
                    tv_shouru.setText("***");
                    tv_zhichu.setText("***");
                } else {
                    tv_shouru.setText("123");
                    tv_zhichu.setText("123");
                }
            }
        });

        String sql = "select * from wst_goods where userId = 15";
        String types = "/Api/exeQuery";
        HashMap<String,String> map = new HashMap<>();
        map.put("sql",sql);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("onSuccess" , s.toString());
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
                    Log.i("aaa","aaa");
                    String userId = data.get(0).toString();
                    Log.i("onSuccess","userId = " + userId);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("onError" , throwable.toString());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });

        list.add(new Shouruyuzhichu(R.drawable.shangpin1, "进口羊毛衫", "白色", "LL", "54", "23", "3", "进货"));
        listView.setAdapter(new shouruAdapter(getApplicationContext(), list));
    }
}
