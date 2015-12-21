package com.myshops.shops.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myshops.shops.myshops.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class ItemAdapter extends BaseAdapter{
    Context context;
    List<String> list = new ArrayList<>();

    public ItemAdapter(Context context, List<String> list) {
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
            v= new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.itemadapter,null);
            v.text = (TextView) convertView.findViewById(R.id.texts);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();
        return convertView;
    }
    class ViewHolder{
        TextView text;
    }
}
