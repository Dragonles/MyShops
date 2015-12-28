package com.myshops.shops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myshops.shops.myshops.MainActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.myshops.ShopInfoActivity;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_shop)
public class ShopFragment extends BaseFragment {

    Button buttons;
    @Event(value = R.id.shop_info)
    private void shop_infoClick(View v){
        Intent intent =new Intent(getActivity(), ShopInfoActivity.class);
        startActivity(intent);
    }
//    ImageButton mgoinfo;

    @ViewInject(R.id.tv_shop_username)
    private TextView tv_shop_username;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_shop_username.setText(MainActivity.usernamefromlogin);
    }

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_shop,container,false);
//        Toolbar toolbar = (Toolbar)v.findViewById(R.id.toolbar);
//        tv_shop_username.setText(MainActivity.usernamefromlogin);
//        mgoinfo=(ImageButton)v.findViewById(R.id.shop_info);
//        mgoinfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getActivity(), ShopInfoActivity.class);
//                startActivity(intent);
//            }
//        });
//       // Activity.setSupportActionBar(toolbar);
//        return v;
//    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getContext());       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getContext());
    }

}
