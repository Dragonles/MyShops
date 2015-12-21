package com.myshops.shops.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.myshops.shops.myshops.R;


public class MessageFragment extends Fragment implements View.OnClickListener {

    public MessageFragment() {
        // Required empty public constructor
    }

    FragmentManager fm;
    RadioButton rb_mesg, rb_circle, rb_evaluate, rb_client; //聊天消息  微店商圈  评价管理 客户管理
    ColorStateList dian, nodian;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_message, container, false);
        View v = inflater.inflate(R.layout.fragment_message, container, false);

        rb_mesg = (RadioButton) v.findViewById(R.id.rb_mesg);     //聊天消息
        rb_circle = (RadioButton) v.findViewById(R.id.rb_circle);   //微店商圈
        rb_evaluate = (RadioButton) v.findViewById(R.id.rb_evaluate);  //评价管理
        rb_client = (RadioButton) v.findViewById(R.id.rb_client);  //客户管理

        //默认聊天消息
        rb_mesg.setChecked(true);
        rb_mesg.setOnClickListener(this);
        rb_circle.setOnClickListener(this);
        rb_evaluate.setOnClickListener(this);
        rb_client.setOnClickListener(this);

        dian = getResources().getColorStateList(R.color.dian);
        nodian = getResources().getColorStateList(R.color.nodian);

        fm = getChildFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction ftt = fm.beginTransaction();
            ChatMessageFragment cmf = new ChatMessageFragment();
            ftt.add(R.id.fragment_parent, cmf, "rb_mesg");
            ftt.commit();
            rb_mesg.setTextColor(dian);

        }


        return v;
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction ftt = fm.beginTransaction();

        if (fm.findFragmentByTag("rb_mesg") != null) {
            ftt.hide(fm.findFragmentByTag("rb_mesg"));
        }
        if (fm.findFragmentByTag("rb_circle") != null) {
            ftt.hide(fm.findFragmentByTag("rb_circle"));
        }
        if (fm.findFragmentByTag("rb_evaluate") != null) {
            ftt.hide(fm.findFragmentByTag("rb_evaluate"));
        }
        if (fm.findFragmentByTag("rb_client") != null) {
            ftt.hide(fm.findFragmentByTag("rb_client"));
        }

        int id = v.getId();
        if (id == R.id.rb_mesg) {
            // 聊天消息
            rb_mesg.setTextColor(dian);
            rb_circle.setTextColor(nodian);
            rb_evaluate.setTextColor(nodian);
            rb_client.setTextColor(nodian);
            if (fm.findFragmentByTag("rb_mesg")!=null){
                ftt.show(fm.findFragmentByTag("rb_mesg"));
            }else{
                ChatMessageFragment cmf = new ChatMessageFragment();
                Log.i("sdddddd","333333333");
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent, cmf, "rb_mesg");
            }
        } else if (id == R.id.rb_circle) {
            //微店商圈
            rb_mesg.setTextColor(nodian);
            rb_circle.setTextColor(dian);
            rb_evaluate.setTextColor(nodian);
            rb_client.setTextColor(nodian);
            if (fm.findFragmentByTag("rb_circle")!=null){
                ftt.show(fm.findFragmentByTag("rb_circle"));
            }else{
                CircleFragment cf = new CircleFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent, cf, "rb_circle");
                Log.i("sellmssage","weidina");
            }
        } else if (id == R.id.rb_evaluate) {
            //评价管理
            rb_mesg.setTextColor(nodian);
            rb_circle.setTextColor(nodian);
            rb_evaluate.setTextColor(dian);
            rb_client.setTextColor(nodian);
            if (fm.findFragmentByTag("rb_evaluate")!=null){
                ftt.show(fm.findFragmentByTag("rb_evaluate"));
            }else{
                EvaluateFragment ef = new EvaluateFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent, ef, "rb_evaluate");
            }
        } else if (id == R.id.rb_client) {
            //客户管理
            rb_mesg.setTextColor(nodian);
            rb_circle.setTextColor(nodian);
            rb_evaluate.setTextColor(nodian);
            rb_client.setTextColor(dian);
            if (fm.findFragmentByTag("rb_client")!=null){
                ftt.show(fm.findFragmentByTag("rb_client"));
            }else{
                ClientFragment clf = new ClientFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent, clf, "rb_client");
            }
        }

        ftt.commit();
    }
}
