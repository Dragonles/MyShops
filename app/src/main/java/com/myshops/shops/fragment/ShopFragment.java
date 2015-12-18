package com.myshops.shops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myshops.shops.myshops.R;
public class ShopFragment extends Fragment {
    Button buttons;
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
       // Activity.setSupportActionBar(toolbar);
        return v;
    }




}
