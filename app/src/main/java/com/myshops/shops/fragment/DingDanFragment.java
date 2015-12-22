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

import com.myshops.shops.adapter.OderAdpter;
import com.myshops.shops.bean.Order;
import com.myshops.shops.bean.Orders;
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

    private List<Order> lists=new ArrayList<>();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lists.add(new Order("傻福小苍", "未发货", "3000", "20", "云南龙井", "已付款", R.drawable.img_head_three));
        listView.setAdapter(new OderAdpter(getActivity(), lists));
        String sql = "select * from wst_users";
        String types = "/Api/exeQuery";
        HashMap<String, String> map = new HashMap<>();
        map.put("sql", sql);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess" + s.toString());
                Log.i("sss",s.toString());
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError" + throwable.toString());
                Log.i("sser",throwable.toString());
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("CancelledException",e.toString());
            }

            @Override
            public void onFinished() {

            }
        });
//        try {
//            JSONObject data = new JSONObject(s);
//            String code = data.get("code").toString();
//            String nessage = data.get("message").toString();
//            JSONArray datas = (JSONArray) data.get("data");
//            List<Users> usersList = new ArrayList<Users>();
//            for (int i = 0; i < datas.length(); i++) {
//                JSONObject info = datas.getJSONObject(i);
//                Users users = new Users();
//                users.setUserName(info.get("username").toString());
//                users.setToken(info.get("token").toString());
//                users.setClientType(info.get("userType").toString());
//                usersList.add(users);
//            }
//
//        }

        //    ListView listView;
//    public DingDanFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_ding_dan,container,false);
//        listView=(ListView)v.findViewById(R.id.listViews);
//        lists.add(new Order("傻福小苍","未发货","3000","20","云南龙井","已付款",R.drawable.img_head_three));
//        listView.setAdapter(new OderAdpter(getActivity(),lists));
//        return v;
//}
    }
    }