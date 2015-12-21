package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.myshops.shops.adapter.JinhuoAdapter;
import com.myshops.shops.bean.JinHuoJiLu;

import java.util.ArrayList;
import java.util.List;

public class JinHuoActivity extends AppCompatActivity {
    ListView jinhuo_listView;
    List<JinHuoJiLu> list = new ArrayList<>();
    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jin_huo);
        jinhuo_listView = (ListView) findViewById(R.id.jinhuojilu_listView);
        iv_back = (ImageView) findViewById(R.id.goBacks);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JinHuoActivity.this.finish();
            }
        });
        list.add(new JinHuoJiLu(R.drawable.home_baby_img,"严浩办羊毛衫","绿色","ML","2015-12-18","12","42","1245元"));
        jinhuo_listView.setAdapter(new JinhuoAdapter(getApplicationContext(),list));
    }

}
