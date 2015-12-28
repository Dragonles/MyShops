package com.myshops.shops.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myshops.shops.myshops.BaseActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.myshops.ShopInfoActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.fragment_my_goods)
public class GoodsFragment extends BaseFragment {

    Button btn_shopinfo_exit;

    public GoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
    }
}
