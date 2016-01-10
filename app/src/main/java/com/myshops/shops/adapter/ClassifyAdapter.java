package com.myshops.shops.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myshops.shops.bean.Goods_classify;
import com.myshops.shops.myshops.R;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/7.
 */
public class ClassifyAdapter extends BaseAdapter {
    Context context;
    List<Goods_classify> list;
    SharedPreferences spf;
    public ClassifyAdapter(Context context,List<Goods_classify> list){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if(convertView == null){
            v = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_layout,null);
            v.classify_name = (TextView) convertView.findViewById(R.id.classify_name);
            v.classify_count = (TextView) convertView.findViewById(R.id.classify_count);
            v.delete = (RelativeLayout) convertView.findViewById(R.id.delete);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();
        v.classify_name.setText(list.get(position).getClassify_name());
        v.classify_count.setText(list.get(position).getClassify_count());
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        v.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("删除");
                builder.setMessage("确定删除么");
                builder.setCancelable(true);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        spf = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("token",spf.getString("token",""));
                        hashMap.remove("sign");
                        hashMap.put("sql","delete from wst_goods_cats where `catId` = "+list.get(position).getCatId()+"");
                        HttpUtils.httputilsGet("/Api/extQueryByToken", hashMap, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Log.i("GG","成功"+result);
                                try {
                                    JSONObject res = new JSONObject(result);
                                    String code = res.getString("code");
                                    if("200".equals(code)){
                                        Toast.makeText(context,"删除成功",Toast.LENGTH_LONG).show();
                                        list.remove(position);
                                        notifyDataSetChanged();
                                    }else{
                                        Toast.makeText(context,"删除失败",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                Log.i("GG","错误"+ex);
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {
                                Log.i("GG","结束");
                            }
                        });
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return convertView;
    }
    class ViewHolder{
        RelativeLayout delete;
        TextView classify_name,classify_count;

    }
}
