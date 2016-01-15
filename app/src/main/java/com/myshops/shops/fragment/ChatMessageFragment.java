package com.myshops.shops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myshops.shops.myshops.MaijiaxiaoxiActivity;
import com.myshops.shops.myshops.R;

/**
 * 卖家 ———— 聊天消息框架
 * */
public class ChatMessageFragment extends Fragment {


    public Button xiaoxi,dingdan; // 消息 订单
    public TextView shu1,shu2; // 消息--个数   订单消息---个数



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_chat_message, container, false);
        View v = inflater.inflate(R.layout.fragment_chat_message, container, false);

        xiaoxi = (Button) v.findViewById(R.id.xiaoxi_message);
        dingdan = (Button) v.findViewById(R.id.dingdan_message);
        shu1 = (TextView) v.findViewById(R.id.shuzi1);
        shu2 = (TextView) v.findViewById(R.id.shuzi2);
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiaoxi.setClickable(false);
                shu1.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), MaijiaxiaoxiActivity.class);
                startActivity(intent);
                xiaoxi.setClickable(true);
            }
        });
        return v;
    }


}
