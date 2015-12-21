package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.myshops.shops.adapter.GouwucheAdapter;
import com.myshops.shops.bean.Gouwuche;

import java.util.ArrayList;
import java.util.List;

public class GouwucheActivity extends AppCompatActivity {
    ListView listView;
    List<Gouwuche> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_gouwuche);
        listView = (ListView) findViewById(R.id.gongwuche_ListView);
        list.add(new Gouwuche(R.drawable.shangpin1,"专卖店","奶瓶","12","32"));
        listView.setAdapter(new GouwucheAdapter(getApplicationContext(),list));
    }
}
