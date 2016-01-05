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
 * Created by zyh on 2015/12/21.
 * 消息 ---  客户管理  ———  ListVew -- Adapter
 */
public class ClientFragmentAdapter extends BaseAdapter {
    Context context;
    List<Conmments> conmmentsList;
    public ClientFragmentAdapter(Context context, List<Conmments> conmmentsList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_fragment_client, null);
            vh.img_shangpin = (ImageView) convertView.findViewById(R.id.img_shangpin);
            vh.txt_username = (TextView) convertView.findViewById(R.id.dianpu_name);
            vh.txt_pingjia = (TextView) convertView.findViewById(R.id.txt_pingjia);
            vh.shangpin_name_left = (TextView) convertView.findViewById(R.id.shangpin_name_left);
            vh.shangpin_danjia = (TextView) convertView.findViewById(R.id.shangpin_danjia);
            vh.shangpin_shuliang = (TextView) convertView.findViewById(R.id.shangpin_shuliang);
            convertView.setTag(vh);
        }else {
            Log.i("aaaa","*******11111*********");
            vh = (ViewHolder) convertView.getTag();
        }
        Log.i("aaaa","********2222********");vh.txt_pingjia.setText(conmmentsList.get(position).getUserlevel());
        vh.txt_pingjia.setText(conmmentsList.get(position).getComment());
        vh.txt_username.setText(conmmentsList.get(position).getUsername());
     //   Picasso.with(context).load(conmmentsList.get(position).getUsericon()).into(vh.img_shangpin);
//        vh.img_shangpin.setImageResource(conmmentsList.get(position).getUsericon());
        vh.shangpin_name_left.setText(conmmentsList.get(position).getBuyproduct());
        vh.shangpin_danjia.setText(conmmentsList.get(position).getDanjia());
        vh.shangpin_shuliang.setText(conmmentsList.get(position).getShuliang()+"");
        Log.i("aaaa","********33333********"+conmmentsList.size());
        return convertView;
    }
    class ViewHolder {
        //商品头像
        ImageView img_shangpin;
        //买家名称  评价类型  商品名称   商品单价  商品数量  商品订单状态   买家备注
        TextView txt_username, txt_pingjia, shangpin_name_left, shangpin_danjia, shangpin_shuliang, txt_ddzhuangtai, txt_beizhus;
    }
}
