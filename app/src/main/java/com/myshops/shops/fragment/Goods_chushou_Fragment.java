package com.myshops.shops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myshops.shops.myshops.AddShangpinActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.HashMap;

//package com.

public class Goods_chushou_Fragment extends Fragment {
    LinearLayout Ln_data,Ln_xiaoliang,Ln_kucun;
    ListView Lv_data,Lv_xiaoliang,Lv_kucun;
    TextView Tv_data,Tv_xiaoliang,Tv_kucun;
    Button btn_add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goods_chushou_, container, false);
        Ln_data = (LinearLayout) v.findViewById(R.id.linearout_data);
        Ln_xiaoliang = (LinearLayout) v.findViewById(R.id.linearout_xiaoliang);
        Ln_kucun = (LinearLayout) v.findViewById(R.id.linearout_kucun);
        Lv_data = (ListView) v.findViewById(R.id.listView_data);
        Lv_xiaoliang = (ListView) v.findViewById(R.id.listView_xiaoliang);
        Lv_kucun = (ListView) v.findViewById(R.id.listView_kucun);
        Tv_data = (TextView) v.findViewById(R.id.text_data);
        Tv_xiaoliang = (TextView) v.findViewById(R.id.text_xiaoliang);
        Tv_kucun = (TextView) v.findViewById(R.id.text_kucun);
        btn_add = (Button) v.findViewById(R.id.btn_shopinfo_exit);
        Tv_data.setTextColor(getResources().getColorStateList(R.color.style_color));
        HashMap<String,String> maps = new HashMap<>();
        String selects = "select goodsId,goodsImg,goodsName,marketPrice,shopPrice,saleCount,isBook,goodsStock,createTime from wst_goods";
        maps.put("sql",selects);
        String url = "/Api/exeQuery";
        HttpUtils.httputilsPost(url, maps, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","结果"+result);
                try {
                    JSONObject res_chushou = new JSONObject(result);
                    String code = res_chushou.getString("code");
                    if("200".equals(code)){
                        JSONArray list = res_chushou.getJSONArray("data");
                        for(int i=0;i<list.length();i++){
                            JSONObject list_chushou = list.getJSONObject(i);
                            String goodsId = list_chushou.getString("goodsId");
                            String goodsImg = list_chushou.getString("img");
                            String goodsName = list_chushou.getString("goodsName");
                            String marketPrice = list_chushou.getString("marketPrice");
                            String shopPrice = list_chushou.getString("shopPrice");
                            String saleCount = list_chushou.getString("saleCount");
                            String isBook = list_chushou.getString("isBook");
                            String goodsStock = list_chushou.getString("goodsStock");
                            String creatTime = list_chushou.getString("creatTime");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
        }

            @Override
            public void onFinished() {
                Log.i("GG","结束");
            }
        });
        //添加事件点击事件
        Ln_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lv_data.setVisibility(View.VISIBLE);
                Lv_xiaoliang.setVisibility(View.GONE);
                Lv_kucun.setVisibility(View.GONE);
                Tv_data.setTextColor(getResources().getColorStateList(R.color.style_color));
                Tv_kucun.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_xiaoliang.setTextColor(getResources().getColorStateList(R.color.fontcolor));
            }
        });
        Ln_xiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lv_data.setVisibility(View.GONE);
                Lv_xiaoliang.setVisibility(View.VISIBLE);
                Lv_kucun.setVisibility(View.GONE);
                Tv_data.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_kucun.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_xiaoliang.setTextColor(getResources().getColorStateList(R.color.style_color));
            }
        });
        Ln_kucun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lv_data.setVisibility(View.GONE);
                Lv_xiaoliang.setVisibility(View.GONE);
                Lv_kucun.setVisibility(View.VISIBLE);
                Tv_data.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_kucun.setTextColor(getResources().getColorStateList(R.color.style_color));
                Tv_xiaoliang.setTextColor(getResources().getColorStateList(R.color.fontcolor));
            }
        });
        //添加商品的点击事件
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity()  ,AddShangpinActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
