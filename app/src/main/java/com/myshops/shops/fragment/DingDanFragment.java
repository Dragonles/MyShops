package com.myshops.shops.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.myshops.shops.adapter.OderAdpter;
import com.myshops.shops.bean.Order;
import com.myshops.shops.bean.Orders;
import com.myshops.shops.myshops.LoginActivity;
import com.myshops.shops.myshops.MainActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.fragment_ding_dan)
public class DingDanFragment extends BaseFragment {

    @ViewInject(R.id.listViews)
    private ListView listView;
    private void onclike(View v){
        
    }
    OderAdpter orderadpter; 
    private List<Order> lists=new ArrayList<>();

    @Event(value = R.id.btn_alldingdan)
    private void allClick(View v){
        String types = "/AllOrders/allorder";
        //String sql = "select * from wst_orders  join wst_order_goods  on wst_orders.orderId=wst_order_goods.orderId where userId in(select userId from wst_user_token where token='"+LoginActivity.token+"')";
        HashMap<String, String> map = new HashMap<>();
        map.put("token", LoginActivity.token);
        Log.i("token",LoginActivity.token);
        HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess" + s.toString());
                Log.i("sss",s.toString());
                try {
                    JSONObject data = new JSONObject(s);
                    String code = data.get("code").toString();
                    String nessage = data.get("message").toString();
                    JSONArray datas = (JSONArray) data.get("data");
                    List<Order> usersList = new ArrayList<Order>();
                    for (int i = 0; i < datas.length(); i++) {
                        JSONObject info = datas.getJSONObject(i);
                        Order order = new Order();
                        order.setGoodsThums(info.get("goodsThums").toString());
                        Log.i("img",info.get("goodsThums").toString());
                        order.setGoodsName(info.get("goodsName").toString());
                        order.setGoodsNums(info.get("goodsNums").toString());
                        order.setUsername(info.get("userName")    .toString());
                        order.setTotalMoney(info.get("totalMoney").toString());
                        order.setOrderStatus(info.get("orderStatus").toString());
                        lists.add(order);
                    }
                    listView.setAdapter(new OderAdpter(getActivity(), lists));

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError" + throwable.toString());
                Log.i("sser",throwable.getMessage());
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("CancelledException",e.toString());
            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(value = R.id.btn_daifahuo)
    private void dafahuoClick(View v){
        String types = "/AllOrders/addgoodtest";
        HashMap<String, String> map = new HashMap<>();
        map.put("token", LoginActivity.token);
        HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess" + s.toString());
                Log.i("sss",s.toString());
                try {
                    JSONObject data = new JSONObject(s);
                    String code = data.get("code").toString();
                    String nessage = data.get("message").toString();
                    JSONArray datas = (JSONArray) data.get("data");
                    List<Order> usersList = new ArrayList<Order>();
                    for (int i = 0; i < datas.length(); i++) {
                        JSONObject info = datas.getJSONObject(i);
                        Order order = new Order();
                        order.setGoodsThums(info.get("goodsThums").toString());
                        Log.i("img",info.get("goodsThums").toString());
                        order.setGoodsName(info.get("goodsName").toString());
                        order.setGoodsNums(info.get("goodsNums").toString());
                        order.setUsername(info.get("userName").toString());
                        order.setTotalMoney(info.get("totalMoney").toString());
                       // order.setOrderStatus(Integer.parseInt(info.get("orderStatus").toString()));
                        lists.add(order);
                    }
                    orderadpter.notifyDataSetChanged();
                    //listView.setAdapter(new OderAdpter(getActivity(), lists));

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError" + throwable.toString());
                Log.i("sser",throwable.getMessage());
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("CancelledException",e.toString());
            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    }