package com.myshops.shops.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.myshops.shops.adapter.ClassifyAdapter;
import com.myshops.shops.bean.Goods_classify;
import com.myshops.shops.myshops.R;
import com.myshops.shops.pulltorefresh.PullToRefreshLayout;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Goods_fenlei_Fragment extends Fragment {
    TextView Tv_classify;
    EditText editText;
    ListView Lv_classify;
    SharedPreferences spf;
    ClassifyAdapter classifyAdapter;
    int list_shu = 10,shangti =0;
    List<Goods_classify> classify_list = new ArrayList<>();
    private ProgressDialog mprogresssdialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goods_fenlei_, container, false);
        ((PullToRefreshLayout) v.findViewById(R.id.rotate_header_web_view_frame_classify))
                .setOnRefreshListener(new MyListener());
        Tv_classify = (TextView) v.findViewById(R.id.textView_addClassify);
        spf = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Lv_classify = (ListView) v.findViewById(R.id.fenlei_listView);
        getClassify(null);
        //新建分类
        Tv_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态加载布局生成View对象
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View longinDialogView = layoutInflater.inflate(R.layout.logindialog_layout, null);
                //获取布局中的控件
                editText = (EditText)longinDialogView.findViewById(R.id.edit_dialog);
                //创建一个AlertDialog对话框
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("新建分类")
                        .setView(longinDialogView)                //加载自定义的对话框式样
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                classify_list.add(new Goods_classify(editText.getText().toString(),"0"));
                                Lv_classify.setAdapter(new ClassifyAdapter(getActivity(),classify_list));
                            }
                        })
                        .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();

                alertDialog.show();
            }
        });
        return v;
    }
    //获取数据
    public void getClassify(final PullToRefreshLayout pullToRefreshLayout){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("token",spf.getString("token",""));
        hashMap.remove("sign");
        mprogresssdialog = ProgressDialog.show(getActivity(),"","正在加载...");
        hashMap.put("sql","select * from wst_goods_cats order by catSort");
        HttpUtils.httputilsGet("/Api/extQueryByToken", hashMap, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","结果"+result);
                try {
                    JSONObject res = new JSONObject(result);
                    String code = res.getString("code");
                    if("200".equals(code)){
                        JSONArray list = res.getJSONArray("data");
                        for(int i =0;i<list_shu;i++){
                            JSONObject res_list = list.getJSONObject(i);
                            String classify_name = res_list.getString("catName");
                            String classsify_count = res_list.getString("catSort");
                            if (i >= shangti){
                                classify_list.add(new Goods_classify(classify_name,classsify_count));
                            }
                        }
                        shangti = list_shu;
                        if ( pullToRefreshLayout == null){
                            Lv_classify.setAdapter(new ClassifyAdapter(getActivity(),classify_list));
                        }else {
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                            classifyAdapter.notifyDataSetChanged();
                        }
                    }else{
                        Toast.makeText(getActivity(),"服务器错误",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mprogresssdialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("GG","错误"+ex);
                mprogresssdialog.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.i("GG","结束");
                mprogresssdialog.dismiss();
            }
        });

    }
    //下拉和上提
    /***
     * 下拉刷新 上提加载
     */
    public class MyListener implements PullToRefreshLayout.OnRefreshListener
    {
        //刷新
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
        {
            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    classify_list.clear();
                    shangti = 0;
                    getClassify(null);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
        //加载
        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
        {
            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    list_shu+=5;
                    getClassify(pullToRefreshLayout);
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("aas","dds");
        super.onSaveInstanceState(outState);
    }


}
