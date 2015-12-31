package com.myshops.shops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myshops.shops.myshops.R;
public class Goods_xiajia_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_goods_xiajia_, container, false) ;

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("aa","dd");
        super.onSaveInstanceState(outState);
    }
}
