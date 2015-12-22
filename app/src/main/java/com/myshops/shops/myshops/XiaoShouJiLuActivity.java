package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.myshops.shops.bean.Shouruyuzhichu;


import java.util.ArrayList;
import java.util.List;

public class XiaoShouJiLuActivity extends AppCompatActivity {
    List<Shouruyuzhichu> list = new ArrayList<>();
    private  ListView listView;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_shou_ji_lu);

        listView = (ListView) findViewById(R.id.xiaoshou_listView);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XiaoShouJiLuActivity.this.finish();
            }
        });

        list.add(new Shouruyuzhichu(R.drawable.shangpin1,"毛衣","灰色","L","23","45","34","进货"));
    }

}
