package com.myshops.shops.myshops;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.myshops.shops.bean.Shouruyuzhichu;
import com.myshops.shops.untils.HttpUtils;
import com.myshops.shops.untils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 收入总额
 * */
public class ShouRuZhiChuActivity extends AppCompatActivity {
    List<Shouruyuzhichu> list = new ArrayList<>();
    private String tokens;
    ListView listView;
    Shouruyuzhichu srAdapter;
    private TextView txt_shouru, txt_zhichu, txt_shourus; //收入总额   支出总额  隐藏收入总额
    private CheckBox check_xy; //显示隐藏按钮
    private List<Float> prices = new ArrayList<>();
    private float a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ru_zhi_chu);
        txt_shouru = (TextView) findViewById(R.id.txt_shouru); //收入总额
        txt_zhichu = (TextView) findViewById(R.id.txt_zhichu); //支出总额
        check_xy = (CheckBox) findViewById(R.id.check_xy);  //显示隐藏按钮
        txt_shourus = (TextView) findViewById(R.id.txt_shourus); //隐藏总额
        listView = (ListView) findViewById(R.id.shouru_listView);

        check_xy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_xy.isChecked()) {
                    ToastUtil.ToastLongs(ShouRuZhiChuActivity.this, "已隐藏金额");
                    txt_shouru.setVisibility(View.GONE);
                    txt_shourus.setVisibility(View.VISIBLE);
                } else {
                    ToastUtil.ToastLongs(ShouRuZhiChuActivity.this, "显示金额");
                    txt_shouru.setVisibility(View.VISIBLE);
                    txt_shourus.setVisibility(View.GONE);
                }
            }
        });
        /*
        * 获取token
        * */
        chatoken();

//        list.add(new Shouruyuzhichu(R.drawable.shangpin1,"进口羊毛衫","白色","LL","54","23","3","进货"));
//        listView.setAdapter(new ShouruAdapter(getApplicationContext(),list));
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

        Log.i("srzclog","tokens:"+tokens);
        String types = "/Api/extQueryByToken";
        String sqls = "select * from wst_goods";
        HashMap<String, String> map = new HashMap<>();
        map.put("token", tokens);
        map.put("sql",sqls);
        Log.i("token",tokens);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("srzclog","Json数据: "+s);
                System.out.println("onSuccess" + s.toString());
                Log.i("sss",s.toString());
                //解析JSON数据
                try {
                    JSONObject jo = new JSONObject(s);
                    String code = jo.get("code").toString();
                    if ("200".equals(code)) {
                        JSONArray datas = jo.getJSONArray("data");

                        for (int i = 0; i < 10; i++) {
                            Shouruyuzhichu sr = new Shouruyuzhichu();
                            JSONObject info = datas.getJSONObject(i);
                            sr.setImg(info.getString("goodsThums").toString());//商品图片
                            sr.setShangpin_price_two(info.getString("markeprice").toString());//商品原价
                            sr.setShangpin_price_first(info.getString("shopPrice").toString());//商品现价
                            sr.setShangpin_title(info.getString("goodsName").toString());//商品名称
                            sr.setShangpin_count(info.getString("saleCount").toString());//销售个数
                            sr.setShangpin_count(info.getString("saleCount").toString());//销售颜色
                            sr.setShangpin_count(info.getString("saleCount").toString());//销售尺码

                            a= a + Float.valueOf(info.get("shopPrice").toString());
//
//
//                            list.add(sr);
                        }
//
//                        float p = 0;
//                        for (int i = 0; i < prices.size(); i++) {
//                            p+= prices.get(i);
//                        }
                        Log.i("pricess","结束总价格："+a);
                        txt_shouru.setText(a+"/元");
//                        Log.i("srzclog","Json数据: "+a);
//                        srAdapter = new ShouruAdapter(getApplicationContext(),list);
//                        listView.setAdapter(srAdapter);

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

    public void gobacksClick(View V) {
        ShouRuZhiChuActivity.this.finish();
    }

}
