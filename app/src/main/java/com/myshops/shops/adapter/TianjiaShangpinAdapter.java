package com.myshops.shops.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.Tianjiashangpin;
import com.myshops.shops.myshops.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class TianjiaShangpinAdapter extends BaseAdapter {
    Context context;
    List<Tianjiashangpin> list ;
    public TianjiaShangpinAdapter(Context context,List list){
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
        Tianjiashangpin tj = list.get(position);
        ViewHolder v;
        if(convertView == null){
            v = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_tianjiashangpin,null);
            v.add_img = (ImageView) convertView.findViewById(R.id.add_img);
            v.add_name = (TextView) convertView.findViewById(R.id.add_name);
            v.add_marketPrice = (TextView) convertView.findViewById(R.id.add_marketPrice);
            v.add_shopPrice = (TextView) convertView.findViewById(R.id.add_shopPrice);
//            v.add_shopPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            v.add_saleCount = (TextView) convertView.findViewById(R.id.add_saleCount);
            v.add_isBook = (TextView) convertView.findViewById(R.id.add_isBook);
            v.add_goodsStock = (TextView) convertView.findViewById(R.id.add_goodsStock);
            v.add_createTime = (TextView) convertView.findViewById(R.id.add_createTime);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();
        //图片外链地址（网络地址）
//        String url2 = QiNiuConfig.externalLinks + goodsImg;
        //加载（下载）图片  iv_add4为ImageView
//        Glide.with(context).load(url2).into(v.add_img);
//        Picasso.with(context).load(tj.getAdd_img()).into(v.add_img);
        v.add_name.setText(tj.getAdd_name());
        v.add_marketPrice.setText(tj.getAdd_marketPrice());
        v.add_shopPrice.setText(tj.getAdd_shopPrice());
        v.add_shopPrice.setTextColor(Paint.STRIKE_THRU_TEXT_FLAG);
        v.add_saleCount.setText(tj.getSaleCount());
        v.add_goodsStock.setText(tj.getGoodsStock());
        v.add_createTime.setText(tj.getCreateTime());
        return convertView;
    }
    class ViewHolder{
        ImageView add_img;
        TextView add_name,add_marketPrice,add_shopPrice,add_saleCount,add_isBook,add_goodsStock,add_createTime;
    }
}
