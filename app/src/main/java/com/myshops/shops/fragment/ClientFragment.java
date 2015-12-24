package com.myshops.shops.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.myshops.shops.adapter.ClientFragmentAdapter;
import com.myshops.shops.bean.Conmments;
import com.myshops.shops.myshops.LoginActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 卖家 — 消息中心 ——— 客户管理框架
 * */
@ContentView(R.layout.fragment_client)
public class ClientFragment extends BaseFragment {

    private String orderStatuss;
    private ClientFragmentAdapter cfAdapter;
    private List<Conmments> conmmentsList = new ArrayList<>();
    @ViewInject(R.id.listView_client_fragment)
    private ListView listView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("conmmente","88888");
        //查询数据
        String tokens = LoginActivity.token;
        Log.i("onSuccess","******cha  xun  sql****");
//        String sql = "select * from wst_goods_appraises Shopid in(select * from wst_log_orders)";
        String types = "/AllOrders/allorder";
        HashMap<String,String> map = new HashMap<>();
        map.put("token",tokens);
        HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess" + s.toString());

                try {
                    JSONObject jo = new JSONObject(s);
                    int code = jo.getInt("code");
                    if (code == 200){
                        JSONArray data = jo.getJSONArray("data");
                        JSONObject info = data.getJSONObject(0);
                        String goodsName = info.getString("goodsName");//商品名称
                        String userName = info.getString("userName");//购买商品用户名
                        String goodsPrice = info.getString("goodsPrice");//商品价格
                        String goodsNums = info.getString("goodsNums");//商品数量
                        int orderStatus = info.getInt("orderStatus");//订单状态
                        if (orderStatus == -2) {
                            orderStatuss = "未发货";
                        }
                        String goodsThums = "http://122.114.62.25:8686/"+info.getString("goodsThums");//商品图片
                        Log.i("imageUrl",goodsThums);
                        conmmentsList.add(new Conmments(userName,goodsPrice,goodsNums,"差评",goodsName,goodsThums));
                        Log.i("conmmente","88888");
                        cfAdapter = new ClientFragmentAdapter(getActivity().getApplicationContext(), conmmentsList);
                        listView.setAdapter(cfAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError" + throwable.toString());
            }

            @Override
            public void onCancelled(Callback.CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });

//        conmmentsList.add(new Conmments("用户名","单价",14,"差评","商品名称"));
//        Log.i("conmmente","88888");
//        cfAdapter = new ClientFragmentAdapter(getActivity().getApplicationContext(), conmmentsList);
//        listView.setAdapter(cfAdapter);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_client, container, false);
////        View view = inflater.inflate(R.layout.fragment_client, container, false);
//
//        listView = (ListView) view.findViewById(R.id.listView_client_fragment);
//
//        conmmentsList.add(new Conmments("用户名","单价",14,"差评","商品名称"));
//        cfAdapter = new ClientFragmentAdapter(getActivity().getApplicationContext(), conmmentsList);
//        listView.setAdapter(cfAdapter);
//
////        return view;
//    }

}
