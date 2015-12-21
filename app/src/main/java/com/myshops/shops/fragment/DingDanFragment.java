package com.myshops.shops.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.myshops.shops.adapter.OderAdpter;
import com.myshops.shops.bean.Order;
import com.myshops.shops.myshops.R;

import java.util.ArrayList;
import java.util.List;

public class DingDanFragment extends Fragment {

    List<Order> lists=new ArrayList<>();
    ListView listView;
    public DingDanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ding_dan,container,false);
        listView=(ListView)v.findViewById(R.id.listViews);
        lists.add(new Order("傻福小苍","未发货","3000","20","云南龙井","已付款",R.drawable.img_head_three));
        listView.setAdapter(new OderAdpter(getActivity(),lists));
        return v;
    }
}
