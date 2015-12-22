package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.myshops.shops.adapter.shouruAdapter;
import com.myshops.shops.bean.Shouruyuzhichu;

import java.util.ArrayList;
import java.util.List;

    public class ShouRuZhiChuActivity extends AppCompatActivity {
        List<Shouruyuzhichu> list = new ArrayList<>();
        ListView listView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_shou_ru_zhi_chu);
            listView = (ListView) findViewById(R.id.shouru_listView);
            list.add(new Shouruyuzhichu(R.drawable.shangpin1,"进口羊毛衫","白色","LL","54","23","3","进货"));
            listView.setAdapter(new shouruAdapter(getApplicationContext(),list));
        }

    }
