package com.myshops.shops.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by zyh on 2015/12/21.
 */
public class HuiFuXiangXiAdapter extends BaseAdapter {
    Context context;
    List<String> pinglunList;
    public HuiFuXiangXiAdapter(Context context, List<String> pinglunList) {
        this.context = context;
        this.pinglunList = pinglunList;
    }

    @Override
    public int getCount() {
        return pinglunList.size();
    }

    @Override
    public Object getItem(int position) {
        return pinglunList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("AAAA",pinglunList.size()+""+pinglunList.get(0));
        ViewHolder vh = null;
        if (convertView == null) {
            vh= new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_huifupinglun, null);
            vh.txt_txt = (TextView) convertView.findViewById(R.id.txt_txt);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.txt_txt.setText(pinglunList.get(position).toString());
        return convertView;
    }
    class ViewHolder {
        TextView txt_txt;
    }
}
