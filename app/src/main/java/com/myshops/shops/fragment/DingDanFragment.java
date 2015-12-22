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

import com.myshops.shops.adapter.JinhuoAdapter;
import com.myshops.shops.myshops.JinHuoActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.myshops.ShopInfoActivity;

public class DingDanFragment extends Fragment {

    Button btn_tiaozhuan,btn_tiaozhuan2;

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
        View v = inflater.inflate(R.layout.fragment_ding_dan, container, false);

        btn_tiaozhuan = (Button) v.findViewById(R.id.btn_tiaozhuan);


        btn_tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShopInfoActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
