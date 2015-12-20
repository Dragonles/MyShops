package com.myshops.shops.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.Conmments;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by zyh on 2015/12/18.
 */
public class EvaluateAdapter extends BaseAdapter {
    Context context;
    List<Conmments> list;
    public EvaluateAdapter(List<Conmments> list, Context context) {
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
        Log.i("aaaa","****************");
        ViewHolder vh = null;
        if (convertView == null) {
            Log.i("aaaa","**********00000******");
            vh= new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_evaluate, null);
            vh.iv_usericon = (ImageView) convertView.findViewById(R.id.iv_usericon);
            vh.txt_username = (TextView) convertView.findViewById(R.id.txt_username);
            vh.txt_userlevel = (TextView) convertView.findViewById(R.id.txt_userlevel);
            vh.txt_com = (TextView) convertView.findViewById(R.id.txt_com);
            vh.txt_product = (TextView) convertView.findViewById(R.id.txt_product);
            vh.txt_setmeal = (TextView) convertView.findViewById(R.id.txt_setmeal);
            vh.txt_date = (TextView) convertView.findViewById(R.id.txt_date);
            vh.txt_ping = (TextView) convertView.findViewById(R.id.txt_ping);
            convertView.setTag(vh);
        }else {
            Log.i("aaaa","*******11111*********");
            vh = (ViewHolder) convertView.getTag();
        }
        Log.i("aaaa","********2222********");

        vh.txt_username.setText(list.get(position).getUsername());
        vh.txt_userlevel.setText(list.get(position).getUserlevel());
        vh.txt_com.setText(list.get(position).getUsercomments());
        vh.txt_product.setText(list.get(position).getBuyproduct());
        vh.txt_setmeal.setText(list.get(position).getSetmeal());
        vh.txt_date.setText(list.get(position).getDate());
        vh.txt_ping.setText(list.get(position).getComment());
        Log.i("aaaa","********33333********"+list.size());
        return convertView;
    }
    class ViewHolder {
        ImageView iv_usericon;
        TextView txt_username, txt_userlevel, txt_com, txt_product, txt_setmeal, txt_date,txt_ping;
    }
}
