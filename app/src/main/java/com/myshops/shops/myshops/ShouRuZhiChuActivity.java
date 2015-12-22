package com.myshops.shops.myshops;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.myshops.shops.adapter.shouruAdapter;
import com.myshops.shops.bean.Shouruyuzhichu;

import java.util.ArrayList;
import java.util.List;

public class ShouRuZhiChuActivity extends AppCompatActivity {
    List<Shouruyuzhichu> list = new ArrayList<>();
    ListView listView;
    CheckBox cb_kaiguan;
    TextView tv_shouru, tv_zhichu;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ru_zhi_chu);
        listView = (ListView) findViewById(R.id.shouru_listView);
        cb_kaiguan = (CheckBox) findViewById(R.id.shouru_flag);
        tv_shouru = (TextView) findViewById(R.id.tv_shouru);
        tv_zhichu = (TextView) findViewById(R.id.tv_zhichu);

        cb_kaiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_kaiguan.isChecked()) {
                    tv_shouru.setText("***");
                    tv_zhichu.setText("***");
                } else {
                    tv_shouru.setText("123");
                    tv_zhichu.setText("123");
                }
            }
        });


        list.add(new Shouruyuzhichu(R.drawable.shangpin1, "进口羊毛衫", "白色", "LL", "54", "23", "3", "进货"));
        listView.setAdapter(new shouruAdapter(getApplicationContext(), list));
    }
}
