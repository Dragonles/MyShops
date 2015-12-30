package com.myshops.shops.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.Order;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/21.
 */
public class OderAdpter extends BaseAdapter {
    Context context;
    List<Order> list;
    public OderAdpter(Context context, List<Order> list) {
        this.context=context;
        this.list=list;
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
        ViewHolder holder = null;
        if(convertView == null)
        {
            holder= new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.order_item,null);
           holder.goodimg=(ImageView)convertView.findViewById(R.id.goods_img);
            holder.uname=(TextView)convertView.findViewById(R.id.uname);
            holder.flag=(TextView)convertView.findViewById(R.id.falg_fahuo);
            holder.goodsname=(TextView)convertView.findViewById(R.id.goods_name);
            holder.number=(TextView)convertView.findViewById(R.id.numbers);
            holder.money=(TextView)convertView.findViewById(R.id.moneytext);
            holder.flag_stad=(TextView)convertView.findViewById(R.id.flag_stad);
            convertView.setTag(holder);
        } else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.goodimg.setBackgroundResource(R.drawable.img_head_three);
        holder.uname.setText(list.get(position).getUsername());
     //  holder.flag.setText(list.get(position).getOrderStatus());
        holder.goodsname.setText(list.get(position).getGoodsName());
        holder.number.setText(list.get(position).getGoodsNums());
        holder.flag_stad.setText(list.get(position).getOrderStatus());
        holder.money.setText(list.get(position).getTotalMoney());
        Log.i("dindanss","订单adapter");
        return convertView;
    }
    class ViewHolder {

        public ImageView goodimg;//商品图片
        public TextView uname;//买家名称
        public TextView flag;//发货状态
        public TextView goodsname;//商品名称
        public TextView number;//购买数量
        public TextView money;//交易金额
        public TextView flag_stad;//交易状态
    }
}
