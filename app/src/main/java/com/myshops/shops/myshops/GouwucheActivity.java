package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.myshops.shops.adapter.GouwucheAdapter;
import com.myshops.shops.bean.Gouwuche;

import java.util.ArrayList;
import java.util.List;

public class GouwucheActivity extends AppCompatActivity {
    ListView listView;
    ImageView iv_back;
    List<Gouwuche> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_gouwuche);

        iv_back = (ImageView) findViewById(R.id.goBacks);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GouwucheActivity.this.finish();
            }
        });

        listView = (ListView) findViewById(R.id.gongwuche_ListView);
        list.add(new Gouwuche(R.drawable.shangpin1,"专卖店","奶瓶","12","32"));
        listView.setAdapter(new GouwucheAdapter(getApplicationContext(),list));
    }
}
