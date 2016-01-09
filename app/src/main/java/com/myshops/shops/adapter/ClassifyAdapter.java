package com.myshops.shops.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myshops.shops.bean.Goods_classify;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/7.
 */
public class ClassifyAdapter extends BaseAdapter {
    Context context;
    List<Goods_classify> list;
    public ClassifyAdapter(Context context,List<Goods_classify> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_layout,null);
            v.classify_name = (TextView) convertView.findViewById(R.id.classify_name);
            v.classify_count = (TextView) convertView.findViewById(R.id.classify_count);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();
        v.classify_name.setText(list.get(position).getClassify_name());
        v.classify_count.setText(list.get(position).getClassify_count());
        return convertView;
    }
    class ViewHolder{
        TextView classify_name,classify_count;

    }
}
