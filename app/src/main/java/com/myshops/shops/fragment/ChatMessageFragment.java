package com.myshops.shops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myshops.shops.myshops.R;

/**
 * 卖家 ———— 聊天消息框架
 * */
public class ChatMessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_chat_message, container, false);
        View view = inflater.inflate(R.layout.fragment_chat_message, container, false);




        return view;
    }


}
