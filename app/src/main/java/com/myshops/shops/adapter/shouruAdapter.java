package com.myshops.shops.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.Shouruyuzhichu;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
public class shouruAdapter extends BaseAdapter {
    Context context;
    List<Shouruyuzhichu> list;
    public shouruAdapter(Context context, List list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_shouru,null);
            v.shangpin_img = (ImageView) convertView.findViewById(R.id.shouru_img);
            v.shangpin_title = (TextView) convertView.findViewById(R.id.shouru_title);
            v.shangpin_color = (TextView) convertView.findViewById(R.id.shouru_color);
            v.shangpin_size = (TextView) convertView.findViewById(R.id.shouru_size);
            v.shangpin_price_new = (TextView) convertView.findViewById(R.id.shouru_nowPrice);
            v.shangpin_price_old = (TextView) convertView.findViewById(R.id.shouru_oldPrice);
            v.shangpin_count = (TextView) convertView.findViewById(R.id.shouru_count);
            v.shangpin_type = (TextView) convertView.findViewById(R.id.shouru_flag);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();
//        v.shangpin_img.setBackgroundResource(Integer.parseInt(list.get(position).getImg()));
        v.shangpin_title.setText(list.get(position).getShangpin_title());
        v.shangpin_color.setText(list.get(position).getShangpin_color());
        v.shangpin_size.setText(list.get(position).getShagpin_size());
        v.shangpin_price_new.setText(list.get(position).getShangpin_price_first());
        v.shangpin_price_old.setText(list.get(position).getShangpin_price_two());
        v.shangpin_count.setText("×"+list.get(position).getShangpin_count());
        v.shangpin_type.setText(list.get(position).getType());
        return convertView;
    }
    class ViewHolder{
        TextView shangpin_title,shangpin_price_new,shangpin_price_old,shangpin_color,shangpin_size,shangpin_count,shangpin_type;
        ImageView shangpin_img;
    }
}
