package com.myshops.shops.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.myshops.shops.adapter.TianjiaShangpinAdapter;
import com.myshops.shops.bean.Tianjiashangpin;
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

public class Goods_xiajia_Fragment extends Fragment {
    ListView Lv_xiajia;
    PullToRefreshLayout layout_data;
    SharedPreferences spf;
    int list_shu = 10,shangti=0,shopId;
    private ProgressDialog mprogresssdialog;
    TianjiaShangpinAdapter tianjiaShangpinAdapter;
    List<Tianjiashangpin> xiajia_list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_goods_xiajia_, container, false) ;
        spf = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        ((PullToRefreshLayout) v.findViewById(R.id.rotate_header_web_view_frame_xiajia))
                .setOnRefreshListener(new MyListener());
        tianjiaShangpinAdapter = new TianjiaShangpinAdapter(getActivity(),xiajia_list);
        Lv_xiajia = (ListView) v.findViewById(R.id.listView_xiajia);
        getXiajiaShuju(null);
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("aa","dd");
        super.onSaveInstanceState(outState);
    }
    //下拉和上提
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
                    list_shu = 10;
                    shangti = 0;
                    xiajia_list.clear();
                    getXiajiaShuju(pullToRefreshLayout);
//                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
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
                        getXiajiaShuju(pullToRefreshLayout);
//                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
    //获取数据
    public void getXiajiaShuju(final PullToRefreshLayout pullToRefreshLayout){
        HashMap<String,String> hashMap_xiajia = new HashMap<>();
        hashMap_xiajia.put("token",spf.getString("token",""));
        hashMap_xiajia.remove("sign");
        String shopId = spf.getString("shopId","");
        hashMap_xiajia.put("sql","select * from wst_goods where goodsStatus = 1 and shopId = "+shopId+"");
        mprogresssdialog = ProgressDialog.show(getActivity(),"","正在加载...");
        HttpUtils.httputilsGet("/Api/extQueryByToken", hashMap_xiajia, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","成功"+result);
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
                            if (i >= shangti){
                                xiajia_list.add(new Tianjiashangpin(goodsImg, goodsName, marketPrice, shopPrice, saleCount, isBook, goodsStock, createTime));
                            }
                        }
                        shangti = list_shu;
                        if ( pullToRefreshLayout == null){
                            Lv_xiajia.setAdapter(new TianjiaShangpinAdapter(getActivity(),xiajia_list));
                            mprogresssdialog.dismiss();
                        }else {
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                            tianjiaShangpinAdapter.notifyDataSetChanged();
                            mprogresssdialog.dismiss();
                        }

                    }else{
                        mprogresssdialog.dismiss();
                        Toast.makeText(getActivity(), "服务器错误", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mprogresssdialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("GG","错误"+ex);
                mprogresssdialog.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.i("GG","结束");
                mprogresssdialog.dismiss();
            }
        });
    }
}
