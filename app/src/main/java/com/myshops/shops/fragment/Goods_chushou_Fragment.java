package com.myshops.shops.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.myshops.shops.adapter.TianjiaShangpinAdapter;
import com.myshops.shops.bean.Tianjiashangpin;
import com.myshops.shops.myshops.AddShangpinActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.pulltorefresh.PullToRefreshLayout;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//package com.

public class Goods_chushou_Fragment extends Fragment {
    LinearLayout Ln_data,Ln_xiaoliang,Ln_kucun;
    ListView Lv_data;
    TextView Tv_data,Tv_xiaoliang,Tv_kucun;
    PullToRefreshLayout layout_data;
    Button btn_add;
    private ProgressDialog mprogresssdialog;
    int aa =0,bb =0,cc= 0;
    SharedPreferences spf;
    int list_shu = 10,shangti =0;
    TianjiaShangpinAdapter tianjiaShangpinAdapter;
    List<Tianjiashangpin> add_list = new ArrayList<>();
    public String tokens;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goods_chushou_, container, false);
        ((PullToRefreshLayout) v.findViewById(R.id.rotate_header_web_view_frame))
                .setOnRefreshListener(new MyListener());
        spf = getActivity().getSharedPreferences("user_info", 0);
        Ln_data = (LinearLayout) v.findViewById(R.id.linearout_data);
        Ln_xiaoliang = (LinearLayout) v.findViewById(R.id.linearout_xiaoliang);
        Ln_kucun = (LinearLayout) v.findViewById(R.id.linearout_kucun);
        Lv_data = (ListView) v.findViewById(R.id.listView_data);
        layout_data = (PullToRefreshLayout) v.findViewById(R.id.rotate_header_web_view_frame);
        Tv_data = (TextView) v.findViewById(R.id.text_data);
        Tv_xiaoliang = (TextView) v.findViewById(R.id.text_xiaoliang);
        Tv_kucun = (TextView) v.findViewById(R.id.text_kucun);
        btn_add = (Button) v.findViewById(R.id.btn_add_shangpins);
        tianjiaShangpinAdapter = new TianjiaShangpinAdapter(getActivity(),add_list);
        getShuju(null,1);
        Tv_data.setTextColor(getResources().getColorStateList(R.color.style_color));
      //添加事件点击事件
        Ln_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa = 1;
                list_shu = 10;
                shangti = 0;
                add_list.clear();
                getShuju(null,1);
                Tv_data.setTextColor(getResources().getColorStateList(R.color.style_color));
                Tv_kucun.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_xiaoliang.setTextColor(getResources().getColorStateList(R.color.fontcolor));
            }
        });
        Ln_xiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bb=2;
                list_shu = 10;
                shangti = 0;
                add_list.clear();
                getShuju(null,2);
                Tv_data.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_kucun.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_xiaoliang.setTextColor(getResources().getColorStateList(R.color.style_color));
            }
        });
         Ln_kucun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cc=3;
                shangti = 0;
                list_shu = 10;
                add_list.clear();
                getShuju(null,3);
                Tv_data.setTextColor(getResources().getColorStateList(R.color.fontcolor));
                Tv_kucun.setTextColor(getResources().getColorStateList(R.color.style_color));
                Tv_xiaoliang.setTextColor(getResources().getColorStateList(R.color.fontcolor));
            }
        });
        //添加商品的点击事件
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddShangpinActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("asda","dddf");
        super.onSaveInstanceState(outState);
    }
    /***
     * 下拉刷新 上提加载
     */
    public class MyListener implements PullToRefreshLayout.OnRefreshListener
    {
        //刷新
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
        {
            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    add_list.clear();
                    shangti = 0;
                    if(aa ==1){
                        getShuju(null,1);
                        mprogresssdialog.dismiss();
                    }
                    if(bb ==2){
                        getShuju(null,2);
                        mprogresssdialog.dismiss();
                    }
                    if(cc ==3){
                        getShuju(null,3);
                        mprogresssdialog.dismiss();
                    }
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
        //加载
        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
        {
            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    list_shu+=5;
                    if(aa ==1){
                        getShuju(pullToRefreshLayout,1);
                        mprogresssdialog.dismiss();
                    }
                    if(bb ==2){
                        getShuju(pullToRefreshLayout,2);
                        mprogresssdialog.dismiss();
                    }
                    if(cc ==3){
                        getShuju(pullToRefreshLayout,3);
                        mprogresssdialog.dismiss();
                    }
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
    //得到数据
    public void getShuju(final PullToRefreshLayout pullToRefreshLayout,int w){
        HashMap<String,String> hashMap_data = new HashMap<>();
        Log.i("GG","出售页面的TOKEN"+spf.getString("token",""));
        hashMap_data.put("token",spf.getString("token",""));
        hashMap_data.remove("sign");
        String shopIds = spf.getString("shopId","");
        if(w == 1){
            hashMap_data.put("sql","select * from wst_goods where shopId = "+ shopIds +" order by createTime");
        }
        if(w == 2){
            hashMap_data.put("sql","select * from wst_goods where shopId = "+ shopIds+" order by saleCount");
        }
        if(w == 3){
            hashMap_data.put("sql","select * from wst_goods where shopId = "+ shopIds +" order by goodsStock");
        }
        mprogresssdialog = ProgressDialog.show(getActivity(),"","正在加载...");
        HttpUtils.httputilsGet("/Api/extQueryByToken", hashMap_data, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","结果"+result);
                try {
                    JSONObject res = new JSONObject(result);
                    String code = res.getString("code");
                    if("200".equals(code)){
                        JSONArray list = res.getJSONArray("data");
                        if(list_shu > list.length()){
                            list_shu = list.length();
                            Toast.makeText(getActivity(), "数据已到最后一条", Toast.LENGTH_SHORT).show();
                        }
                        for(int i=0;i<list_shu;i++) {
                            JSONObject res_list = list.getJSONObject(i);
                            String goodsImg = res_list.getString("goodsImg");
                            String goodsName = res_list.getString("goodsName");
                            String marketPrice = res_list.getString("marketPrice");
                            String shopPrice = res_list.getString("shopPrice");
                            String saleCount = res_list.getString("saleCount");
                            String isBook = res_list.getString("isBook");
                            String goodsStock = res_list.getString("goodsStock");
                            String createTime = res_list.getString("createTime");
//                            //图片外链地址（网络地址）
//                            String url2 = QiNiuConfig.externalLinks + goodsImg;
//                            //加载（下载）图片  iv_add4为ImageView
//                            Glide.with(getActivity()).load(url2).into(iv_shopinfo_shopheader);
                            if (i >= shangti){
                                add_list.add(new Tianjiashangpin(goodsImg, goodsName, marketPrice, shopPrice, saleCount, isBook, goodsStock, createTime));
                            }
                        }
                        shangti = list_shu;
                        if ( pullToRefreshLayout == null){
                            Lv_data.setAdapter(new TianjiaShangpinAdapter(getActivity(),add_list));
                        }else {
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                            tianjiaShangpinAdapter.notifyDataSetChanged();
                        }

                    }else{
                        mprogresssdialog.dismiss();
                        Toast.makeText(getActivity(), "服务器错误", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mprogresssdialog.dismiss();
                Log.i("GG","错误"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                mprogresssdialog.dismiss();
                Log.i("GG","结束");
            }
        });

    }

}
