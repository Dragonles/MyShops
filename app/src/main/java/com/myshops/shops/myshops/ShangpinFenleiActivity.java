package com.myshops.shops.myshops;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myshops.shops.adapter.ItemAdapter;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class ShangpinFenleiActivity extends AppCompatActivity {
    List<String> items = new ArrayList<>();
    @ViewInject(R.id.fenlei_listView)
    private  ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin_fenlei);
        items.add("热门推荐");
        items.add("热门推荐");
        items.add("热门推荐");
        listView.setAdapter(new ItemAdapter(getApplicationContext(),items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShangpinFenleiActivity.this,GouwucheActivity.class);
                startActivity(intent);
            }
        });
    }

}
