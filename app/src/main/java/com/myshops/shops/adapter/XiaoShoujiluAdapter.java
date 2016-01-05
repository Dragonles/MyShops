package com.myshops.shops.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.Xiaoshoujilu;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
public class XiaoShoujiluAdapter extends BaseAdapter {
    Context context;
    List<Xiaoshoujilu> list;
    public XiaoShoujiluAdapter(Context context, List<Xiaoshoujilu> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if(convertView == null){
            v = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_xiaohsoujilu,null);
            v.img = (ImageView) convertView.findViewById(R.id.shangpin_img);
            v.name = (TextView) convertView.findViewById(R.id.xiaoshou_name);
            v.price = (TextView) convertView.findViewById(R.id.xiaoshou_pirce);
            v.data = (TextView) convertView.findViewById(R.id.xiaoshou_data);
            v.count = (TextView) convertView.findViewById(R.id.xiaoshou_count);
        }
        return convertView;
    }
    class ViewHolder{
        TextView name,price,count,data;
        ImageView img;
    }
}
