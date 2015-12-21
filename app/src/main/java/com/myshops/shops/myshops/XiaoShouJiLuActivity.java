package com.myshops.shops.myshops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.myshops.shops.bean.Shouruyuzhichu;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class XiaoShouJiLuActivity extends AppCompatActivity {
    List<Shouruyuzhichu> list = new ArrayList<>();
    @ViewInject(R.id.listView)
    private  ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_shou_ji_lu);
        list.add(new Shouruyuzhichu(R.drawable.shangpin1,"毛衣","灰色","L","23","45","34","进货"));
    }

}
