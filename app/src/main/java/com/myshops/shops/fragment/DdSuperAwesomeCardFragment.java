package com.myshops.shops.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.myshops.shops.adapter.EvaluateAdapter;
import com.myshops.shops.adapter.OderAdpter;
import com.myshops.shops.bean.Conmments;
import com.myshops.shops.bean.Order;
import android.support.v4.app.Fragment;
import com.myshops.shops.myshops.HuiFuXiangXiActivity;
import com.myshops.shops.myshops.LoginActivity;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zyh on 2015/12/30.
 * 卖家 ———— 订单 框架内容
 */
public class DdSuperAwesomeCardFragment extends Fragment {

    int a = 0;
    private String tokens;  //用户token
    OderAdpter orderadpter;
    private List<Order> lists=new ArrayList<>();
    ListView listView;
    private static final String ARG_POSITION = "position";
    private int positions;

    public static DdSuperAwesomeCardFragment newInstance(int position) {
        DdSuperAwesomeCardFragment f = new DdSuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positions = getArguments().getInt(ARG_POSITION);
        Log.i("positions",positions+"*******");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_super_awesome_card, container, false);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        Log.i("positions",positions+"*******");
        listView = new ListView(getActivity());
        listView.setLayoutParams(params);

        Log.i("dindanss", "我的订单");
        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(params);
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                .getDisplayMetrics());



//        Log.i("brands", "成功");
//        Log.i("SuperonSuccess","* create的时候 **a的值：**"+a);
//        if (a != 0) {
//            Log.i("SuperonSuccess","***a的值：**"+a);
//        }else {
//            a = 1;
//            Log.i("SuperonSuccess","* 查询数据的时候的时候 **a的值：**"+a);
            //查询数据


//            Log.i("onSuccess","****///ss/**cha  xun  sql****  "+tokens);
//            String types = "/AllOrders/findMessage";
//            HashMap<String,String> map = new HashMap<>();
//            map.put("token",tokens);
//            Log.i("token",tokens);
//            HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
//                @Override
//                public void onSuccess(String s) {
//                    System.out.println("onSuccess" + s.toString());
//
//                }
//
//                @Override
//                public void onError(Throwable throwable, boolean b) {
//                    System.out.println("onError" + throwable.toString());
//                }
//
//                @Override
//                public void onCancelled(Callback.CancelledException e) {
//
//                }
//
//                @Override
//                public void onFinished() {
//
//                }
//            });


            //假数据
//            list.add(new Conmments("小苍","vip5","good！","无敌大井盖","非标配","2012-12-12","好评"));
//            list.add(new Conmments("小苍","vip5","good！","无敌大井盖","非标配","2012-12-12","差评"));

//        }

//        orderadpter = new DingDanFragment(lists,getContext());
//        evaAdapter.notifyDataSetChanged();
//        listView.setAdapter(orderadpter);
        allClicks();
        params.setMargins(margin, margin, margin, margin);
        fl.addView(listView);
        return fl;
    }

    /**
     * 读取本地方法
     * */
    private void allClicks(){
        Log.i("dindanss", "我的订单方法");
        //获取用户的token
        Log.i("SharedPreferencesToken","LoginActivity的："+ LoginActivity.token);

        if (LoginActivity.token == null) {
            SharedPreferences user = getActivity().getSharedPreferences("user_info",0);
            tokens = user.getString("token","");
            Log.i("SharedPreferencesToken","SharedPreferences 本地保存"+tokens);
        } else {
            tokens = LoginActivity.token;
            Log.i("SharedPreferencesToken","登录获得："+tokens);
        }

        String types = "/AllOrders/allorder";
        //String sql = "select * from wst_orders  join wst_order_goods  on wst_orders.orderId=wst_order_goods.orderId where userId in(select userId from wst_user_token where token='"+LoginActivity.token+"')";
        HashMap<String, String> map = new HashMap<>();
        map.put("token", tokens);
        Log.i("token",tokens);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
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

}
