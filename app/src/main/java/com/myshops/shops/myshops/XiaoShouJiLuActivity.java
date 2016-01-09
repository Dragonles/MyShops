package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.myshops.shops.adapter.XiaoShoujiluAdapter;
import com.myshops.shops.bean.Xiaoshoujilu;
import com.myshops.shops.untils.HttpUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XiaoShouJiLuActivity extends AppCompatActivity {
    List<Xiaoshoujilu> list = new ArrayList<>();
    private  ListView listView;
    private ImageView iv_back;
    private ImageView shangpin_img;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_shou_ji_lu);

        listView = (ListView) findViewById(R.id.xiaoshou_listView);
        shangpin_img = (ImageView) findViewById(R.id.shangpin_img);
        iv_back = (ImageView) findViewById(R.id.goBacks);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XiaoShouJiLuActivity.this.finish();
            }
        });

        String sql = "select * from wst_goods where isSale = 1";
        String types = "/Api/exeQuery";
        HashMap<String, String> map = new HashMap<>();
        map.put("sql", sql);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("onSuccess", s.toString());
                try {
                    JSONObject jsonobj = new JSONObject(s);
                    String code = jsonobj.getString("code");
                    String message = jsonobj.getString("message");
                    JSONArray data = jsonobj.getJSONArray("data");
                    JSONObject info = data.getJSONObject(0);
                    String goodsName = info.getString("goodsName");
                    String goodsImg = info.getString("goodsImg");
                    String createTime = info.getString("createTime");
                    String shopPrice = info.getString("shopPrice");
                    String bookQuantity = info.getString("bookQuantity");

                    list.add(new Xiaoshoujilu(goodsImg,goodsName,shopPrice,createTime,bookQuantity));
                    listView.setAdapter(new XiaoShoujiluAdapter(getApplicationContext(),list));


                } catch (JSONException e) {
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

    }

}
