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
 * Created by zyh on 2015/12/17.
 */
public class CommentsAdapter extends BaseAdapter {
    Context context;
    List<Conmments> conmmentsList;
    public CommentsAdapter(Context context, List<Conmments> conmmentsList) {
        this.context = context;
        this.conmmentsList = conmmentsList;
    }

    @Override
    public int getCount() {
        return conmmentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return conmmentsList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_comments, null);
            vh.iv_usericon = (ImageView) convertView.findViewById(R.id.iv_usericon);
            vh.txt_username = (TextView) convertView.findViewById(R.id.txt_username);
            vh.txt_userlevel = (TextView) convertView.findViewById(R.id.txt_userlevel);
            vh.txt_com = (TextView) convertView.findViewById(R.id.txt_com);
            vh.txt_product = (TextView) convertView.findViewById(R.id.txt_product);
            vh.txt_setmeal = (TextView) convertView.findViewById(R.id.txt_setmeal);
            vh.txt_date = (TextView) convertView.findViewById(R.id.txt_date);
            convertView.setTag(vh);
        }else {
            Log.i("aaaa","*******11111*********");
            vh = (ViewHolder) convertView.getTag();
        }
        Log.i("aaaa","********2222********");

        vh.txt_username.setText(conmmentsList.get(position).getUsername());
        vh.txt_userlevel.setText(conmmentsList.get(position).getUserlevel());
        vh.txt_com.setText(conmmentsList.get(position).getUsercomments());
        vh.txt_product.setText(conmmentsList.get(position).getBuyproduct());
        vh.txt_setmeal.setText(conmmentsList.get(position).getSetmeal());
        vh.txt_date.setText(conmmentsList.get(position).getDate());
        Log.i("aaaa","********33333********"+conmmentsList.size());
        return convertView;
    }
    class ViewHolder {
        ImageView iv_usericon;
        TextView txt_username, txt_userlevel, txt_com, txt_product, txt_setmeal, txt_date;
    }
}
