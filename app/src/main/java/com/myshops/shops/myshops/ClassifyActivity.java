package com.myshops.shops.myshops;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.myshops.shops.adapter.TianjiaShangpinAdapter;
import com.myshops.shops.bean.Tianjiashangpin;
import com.myshops.shops.pulltorefresh.PullToRefreshLayout;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassifyActivity extends AppCompatActivity {
    ListView classify_listView;
    SharedPreferences spf;
    int list_shu = 10,shangti =0;
    List<Tianjiashangpin> list_class = new ArrayList<>();
    private  ProgressDialog progressDialog;
    TianjiaShangpinAdapter tianjiaShangpinAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        ((PullToRefreshLayout) findViewById(R.id.rotate_header_web_view_frame_classifys))
                .setOnRefreshListener(new MyListener());
        spf = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        classify_listView = (ListView) findViewById(R.id.classify_listviews);
        tianjiaShangpinAdapter = new TianjiaShangpinAdapter(getApplicationContext(),list_class);
        getShuju(null);
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
                    list_class.clear();
                    shangti = 0;
                    getShuju(null);
//                        progressDialog.dismiss();
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
                    Log.i("GG","!!"+list_shu+"$$"+shangti);
                    getShuju(pullToRefreshLayout);
//                        progressDialog.dismiss();
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
    //  获取数据
    public void getShuju(final PullToRefreshLayout pullToRefreshLayout){
//        progressDialog = ProgressDialog.show(getApplicationContext(),"","正在加载...");
        HashMap<String,String> hashMap = new HashMap<>();
        String shopId = spf.getString("shopId","");
        hashMap.put("classname","水果");
        hashMap.put("shopid",shopId);
        hashMap.remove("sign");
        HttpUtils.httputilsGet("/Long/selectgoods", hashMap, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","具体分类页面"+result);
                try {
                    JSONObject res = new JSONObject(result);
                    String code = res.getString("code");
                    if("200".equals(code)){
                        JSONArray res_list = res.getJSONArray("data");
                        if(list_shu > res_list.length()){
                            list_shu = res_list.length();
                            Toast.makeText(getApplicationContext(), "数据已到最后一条", Toast.LENGTH_SHORT).show();
                        }
                        for(int i =0;i<res_list.length();i++){
                            JSONObject list = res_list.getJSONObject(i);
                            String goodsImg = list.getString("goodsImg");
                            String goodsName = list.getString("goodsName");
                            String marketPrice = list.getString("marketPrice");
                            String shopPrice = list.getString("shopPrice");
                            String saleCount = list.getString("saleCount");
                            String isBook = list.getString("isBook");
                            String goodsStock = list.getString("goodsStock");
                            String createTime = list.getString("createTime");
                            if (i >= shangti){
                                list_class.add(new Tianjiashangpin(goodsImg, goodsName, marketPrice, shopPrice, saleCount, isBook, goodsStock, createTime));
                            }
                        }
                        shangti = list_shu;
                        if ( pullToRefreshLayout == null){
                            classify_listView.setAdapter(new TianjiaShangpinAdapter(getApplicationContext(),list_class));
                        }else {
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                            tianjiaShangpinAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("GG","错误"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.i("GG","结束");
            }
        });

    }

}
