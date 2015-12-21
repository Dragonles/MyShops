package com.myshops.shops.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myshops.shops.myshops.R;
import com.myshops.shops.myshops.ShopInfoActivity;

public class GoodsFragment extends Fragment {

    Button btn_shopinfo_exit;

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
        View v = inflater.inflate(R.layout.fragment_my_goods, container, false);

        btn_shopinfo_exit = (Button) v.findViewById(R.id.btn_shopinfo_exit);



        return v;
    }


}
