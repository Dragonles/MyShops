package com.myshops.shops.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.JinHuoJiLu;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class JinhuoAdapter extends BaseAdapter {
    Context context;
    List<JinHuoJiLu> list;

    public JinhuoAdapter(Context context,List<JinHuoJiLu> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_jinhuojilu,null);
            v.jinhuo_img = (ImageView) convertView.findViewById(R.id.jinhuo_img);
            v.jinhuo_title = (TextView) convertView.findViewById(R.id.jinhuo_title);
            v.jinhuo_color = (TextView) convertView.findViewById(R.id.jinhuo_color);
            v.jinhuo_size = (TextView) convertView.findViewById(R.id.jinhuo_size);
            v.jinhuo_data = (TextView) convertView.findViewById(R.id.jinhuo_data);
            v.jinhuo_price = (TextView) convertView.findViewById(R.id.jinhuo_price);
            v.jinhuo_count = (TextView) convertView.findViewById(R.id.jinhuo_count);
            v.jinhuo_allPrice = (TextView) convertView.findViewById(R.id.jinhuo_allPrice);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();
        v.jinhuo_img.setBackgroundResource(list.get(position).getImg());
        v.jinhuo_title.setText(list.get(position).getTitle());
        v.jinhuo_color.setText(list.get(position).getColor());
        v.jinhuo_size.setText(list.get(position).getSize());
        v.jinhuo_data.setText(list.get(position).getData());
        v.jinhuo_price.setText(list.get(position).getPrice());
        v.jinhuo_count.setText(list.get(position).getCount());
        v.jinhuo_allPrice.setText(list.get(position).getAllPrice());
        return convertView;
    }
    class ViewHolder{
        TextView jinhuo_title,jinhuo_price,jinhuo_color,jinhuo_size,jinhuo_data,jinhuo_count,jinhuo_allPrice;
        ImageView jinhuo_img;
    }
}
