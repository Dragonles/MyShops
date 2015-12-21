package com.myshops.shops.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.Gouwuche;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class GouwucheAdapter extends BaseAdapter {
    Context context;
    List<Gouwuche> list;
    public GouwucheAdapter(Context context, List list){
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
        ViewHolder v = null;
        if(convertView == null){
            v = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_gouwuche,null);
            v.dianppu_name = (TextView) convertView.findViewById(R.id.dianpu_name);
            v.shangpin_name = (TextView) convertView.findViewById(R.id.shangpin_name);
            v.price = (TextView) convertView.findViewById(R.id.shangpin_danjia);
            v.count = (TextView) convertView.findViewById(R.id.shangpin_shuliang);
            v.shangpin_img = (ImageView) convertView.findViewById(R.id.img_shangpin);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();
        v.dianppu_name.setText(list.get(position).getDianppu_name());
        v.shangpin_name.setText(list.get(position).getShangping_name());
        v.price.setText(list.get(position).getPrice());
        v.count.setText(list.get(position).getCount());
        v.shangpin_img.setBackgroundResource(list.get(position).getImg());
        return convertView;
    }
    class ViewHolder{
        TextView dianppu_name,shangpin_name,price,count;
        ImageView shangpin_img;
    }
}
