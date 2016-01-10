package com.myshops.shops.myshops;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.myshops.shops.adapter.OderAdpter;
import com.myshops.shops.adapter.XiaoShoujiluAdapter;
import com.myshops.shops.bean.Order;
import com.myshops.shops.bean.Shouruyuzhichu;
import com.myshops.shops.bean.Xiaoshoujilu;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_xiao_shou_ji_lu)
public class XiaoShouJiLuActivity extends BaseActivity {

    private String Shopprice;
    private String goodsName;
    private String goodsThums;
    private String saleCount;
    private String saleTime;
    private String tokens;
    public List<Xiaoshoujilu> list = new ArrayList<>();
    public XiaoShoujiluAdapter xsAdapter;

    @Event(value = R.id.goBackss)
    private void goBackssClick(View v) {
        XiaoShouJiLuActivity.this.finish();
    }

    @ViewInject(R.id.xiaoshou_listView)
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取token
        chatoken();


//        list.add(new Shouruyuzhichu(R.drawable.shangpin1,"毛衣","灰色","L","23","45","34","进货"));
    }
    /**
     * 获取token方法
     * */
    public void chatoken() {
        if (LoginActivity.token == null) {
            SharedPreferences user = this.getSharedPreferences("user_info",0);
            tokens = user.getString("token","");
            Log.i("SharedPreferencesToken","SharedPreferences 本地保存"+tokens);
        } else {
            tokens = LoginActivity.token;
            Log.i("SharedPreferencesToken","登录获得："+tokens);
        }
        //获取Http请求方法
        datas(tokens);
    }

    /**
     * http请求方法
     * */
    public void datas(String tokens) {
        String types = "/Api/extQueryByToken";
        String sqls = "select * from wst_goods";
        HashMap<String, String> map = new HashMap<>();
        map.put("token", tokens);
        map.put("sql",sqls);
        Log.i("token",tokens);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess" + s.toString());
                Log.i("sss",s.toString());
                //解析JSON数据
                try {
                    JSONObject jo = new JSONObject(s);
                    String code = jo.get("code").toString();
                    if ("200".equals(code)) {
                        JSONArray datas = jo.getJSONArray("data");
                        for (int i = 0; i < 10; i++) {
                            JSONObject info = datas.getJSONObject(i);
                            Xiaoshoujilu xs = new Xiaoshoujilu();

                            xs.setImg(info.getString("goodsThums").toString());
                            Log.i("xiaoshouSuc", "商品名称" + info.getString("goodsThums").toString());

                            xs.setData(info.getString("saleTime").toString());
                            Log.i("xiaoshouSuc", "商品名称" + info.getString("saleTime").toString());

                            xs.setPrice(info.getString("shopPrice").toString());
                            Log.i("xiaoshouSuc", "商品名称" + info.getString("shopPrice").toString());

                            xs.setShangming_name(info.getString("goodsName").toString());
                            Log.i("xiaoshouSuc", "商品名称" + info.getString("goodsName").toString());

                            xs.setCount(info.getString("saleCount").toString());
                            Log.i("xiaoshouSuc", "商品名称"+ info.getString("saleCount").toString());
                            list.add(xs);
                        }
                        xsAdapter = new XiaoShoujiluAdapter(getApplicationContext(),list);
                        listView.setAdapter(xsAdapter);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError" + throwable.toString());
                Log.i("sser",throwable.getMessage());
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("CancelledException",e.toString());
            }

            @Override
            public void onFinished() {

            }
        });
    }


}
