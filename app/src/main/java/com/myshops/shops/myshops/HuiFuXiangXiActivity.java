package com.myshops.shops.myshops;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.myshops.shops.adapter.HuiFuXiangXiAdapter;
import com.myshops.shops.myshops.R;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 回复评论
 * */
//@ContentView(R.layout.activity_hui_fu_xiang_xi)
public class HuiFuXiangXiActivity extends AppCompatActivity {

    ImageView user_img;
    TextView username, txt_pinjia, txt_datas,txt_hufu;
    EditText edit_pinglun;
    Button btn_submit;
    ListView listview_pinlun;
    List<String> pinglunList = new ArrayList<>();
    String pingluns;
    HuiFuXiangXiAdapter hfxxAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_fu_xiang_xi);

        user_img = (ImageView) findViewById(R.id.user_img);  //评论者头像
        username = (TextView) findViewById(R.id.username);  //评论者名称
        txt_pinjia = (TextView) findViewById(R.id.txt_pinjia);  //评价内容
        txt_datas = (TextView) findViewById(R.id.txt_datas);  //评论时间
        txt_hufu = (TextView) findViewById(R.id.txt_hufu);  //回复TextView
        edit_pinglun = (EditText) findViewById(R.id.edit_pinglun);  //评论输入框
        btn_submit = (Button) findViewById(R.id.btn_submit);  //提交按钮
        listview_pinlun = (ListView) findViewById(R.id.listview_pinlun);  //回复评论列表


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pingluns = edit_pinglun.getText().toString();
                if (pingluns.length() != 0){

                    Log.i("AAAA","**"+pingluns);
                    pinglunList.add(pingluns);
                    btnsubmit();
                    Log.i("AAAA","*///***////*"+pinglunList.size()+"////"+pinglunList.get(0));
                }else {
                    Log.i("AAAA","777777777777777");
                }

            }
        });

    }

    public void btnsubmit(){
        Log.i("AAAA","**"+pingluns+"****88888");
        hfxxAdapter = new HuiFuXiangXiAdapter(getApplicationContext(), pinglunList);
        listview_pinlun.setAdapter(hfxxAdapter);
        edit_pinglun.getText().clear();

        //失去焦点
        edit_pinglun.setFocusable(true);
        edit_pinglun.setFocusableInTouchMode(true);
        edit_pinglun.requestFocus();
        edit_pinglun.requestFocusFromTouch();
        //收起软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_pinglun.getWindowToken(), 0) ;
    }


}
