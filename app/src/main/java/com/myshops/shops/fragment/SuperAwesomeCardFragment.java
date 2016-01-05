package com.myshops.shops.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;

import com.myshops.shops.adapter.EvaluateAdapter;
import com.myshops.shops.bean.Conmments;
import com.myshops.shops.myshops.HuiFuXiangXiActivity;
import com.myshops.shops.myshops.LoginActivity;
import com.myshops.shops.untils.HttpUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 消息种中心 -- 评价管理 -- 评价类型 （全部评价、好评、中评、差评）
 * */
public class SuperAwesomeCardFragment extends Fragment {

    int a = 0;
    private String tokens;  //用户token
    List<Conmments> list = new ArrayList<>();
    ListView listView;
    EvaluateAdapter evaAdapter;
    private static final String ARG_POSITION = "position";
    private int positions;
    public static SuperAwesomeCardFragment newInstance(int position) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
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
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        Log.i("positions",positions+"*******");
        listView = new ListView(getActivity());
        listView.setLayoutParams(params);

        Log.i("dindanss","消息中心 ----  评价管理");
        //获取用户的token
        Log.i("SharedPreferencesToken","LoginActivity的："+LoginActivity.token);

        if (LoginActivity.token == null) {
            SharedPreferences user = getActivity().getSharedPreferences("user_info",0);
            tokens = user.getString("token","");
            Log.i("SharedPreferencesToken","SharedPreferences 本地保存"+tokens);
        } else {
            tokens = LoginActivity.token;
            Log.i("SharedPreferencesToken","登录获得："+tokens);
        }



        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(params);
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                .getDisplayMetrics());

        Log.i("brands", "成功");
        Log.i("SuperonSuccess","* create的时候 **a的值：**"+a);
        if (a != 0) {
            Log.i("SuperonSuccess","***a的值：**"+a);
        }else {
            a = 1;
            Log.i("SuperonSuccess","* 查询数据的时候的时候 **a的值：**"+a);
            //查询数据


            Log.i("onSuccess","****///ss/**cha  xun  sql****  "+tokens);
            String types = "/AllOrders/findMessage";
            HashMap<String,String> map = new HashMap<>();
            map.put("token",tokens);
            Log.i("token",tokens);
            HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    System.out.println("onSuccess" + s.toString());

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


            //假数据
            list.add(new Conmments("小苍","vip5","good！","无敌大井盖","非标配","2012-12-12","好评"));
//            list.add(new Conmments("小苍","vip5","good！","无敌大井盖","非标配","2012-12-12","差评"));

        }

        evaAdapter = new EvaluateAdapter(list,getContext());
//        evaAdapter.notifyDataSetChanged();
        listView.setAdapter(evaAdapter);




        //点击listview列表 回复消息
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                huifu();
            }
        });
        params.setMargins(margin, margin, margin, margin);
        fl.addView(listView);
        return fl;
    }

    //回复消息
    public void huifu() {
        Intent intent = new Intent(getActivity(), HuiFuXiangXiActivity.class);
        startActivity(intent);
    }



}
