package com.myshops.shops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.myshops.shops.myshops.JinHuoActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.myshops.ShouRuZhiChuActivity;

public class ShopFragment extends Fragment {
    Button buttons;
    LinearLayout lly_jinhuojilu,lly_shouru;
    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop,container,false);
        Toolbar toolbar = (Toolbar)v.findViewById(R.id.toolbar);

        lly_jinhuojilu = (LinearLayout) v.findViewById(R.id.lly_jinhupjilu);
        lly_shouru = (LinearLayout) v.findViewById(R.id.lly_shouru);

        lly_jinhuojilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JinHuoActivity.class);
                startActivity(intent);
            }
        });

        lly_shouru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShouRuZhiChuActivity.class);
                startActivity(intent);
            }
        });

        // Activity.setSupportActionBar(toolbar);
        return v;
    }




}
