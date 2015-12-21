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

import org.xutils.view.annotation.Event;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends Fragment {


    public GoodsFragment() {
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
        View v = inflater.inflate(R.layout.fragment_my_goods,container,false);

        return v;
    }


}
