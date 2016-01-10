package com.myshops.shops.myshops;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myshops.shops.untils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_add_shangpin)
public class AddShangpinActivity extends BaseActivity implements View.OnClickListener {
    private ProgressDialog mProgressDialog;
    private static final int REQUEST_IMAGE =2;
    String mFilePath,zFilePath;
    SharedPreferences spf;
    HashMap<String,String> map = new HashMap<>();
    String id;
    @ViewInject(R.id.shangpinleimu)
    private TextView shangpinleimu;

    @ViewInject(R.id.qu_img)
    private ImageView qu_img;

    @ViewInject(R.id.qu_img2)
    private ImageView qu_img2;

    @ViewInject(R.id.qu_img3)
    private ImageView qu_img3;

    @ViewInject(R.id.add_img)
    private RelativeLayout add_img;

    @ViewInject(R.id.imgs)
    private ImageView imgs;

    @ViewInject(R.id.add_img2)
    private RelativeLayout add_img2;

    @ViewInject(R.id.imgs2)
    private ImageView imgs2;

    @ViewInject(R.id.add_img3)
    private RelativeLayout add_img3;

    @ViewInject(R.id.imgs3)
    private ImageView imgs3;


    @ViewInject(R.id.guige_1)
    private LinearLayout guige_1;

    @ViewInject(R.id.guige_2)
    private LinearLayout guige_2;

    @ViewInject(R.id.guige_3)
    private LinearLayout guige_3;

    @ViewInject(R.id.delete_guige1)
    private ImageView delete_guige1;

    @ViewInject(R.id.delete_guige2)
    private ImageView delete_guige2;

    @ViewInject(R.id.delete_guige3)
    private ImageView delete_guige3;

    @ViewInject(R.id.shangpinname)
    private EditText shangpinname;

    @ViewInject(R.id.shangpinjieshao)
    private EditText shangpinjieshao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spf = getSharedPreferences("user_info", Context.MODE_PRIVATE);
//            setShangPin();

        qu_img.setOnClickListener(this);
        qu_img2.setOnClickListener(this);
        qu_img3.setOnClickListener(this);
        delete_guige1.setOnClickListener(this);
        delete_guige2.setOnClickListener(this);
        delete_guige3.setOnClickListener(this);
    }

    @Event(R.id.tianjiasp)
    private void tjspClick(View view){
        mProgressDialog = ProgressDialog.show(AddShangpinActivity.this, "", "正在加载...");
        String types = "/AllOrders/addgoods";
        String spjs = String.valueOf(shangpinjieshao.getText());
        String spname = String.valueOf(shangpinname.getText());
        HashMap<String,String> map = new HashMap<>();
        map.put("goodsName",spname);
        map.put("goodsDesc",spjs);
        map.put("token",spf.getString("token",""));
//        HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.i("GG","结果"+result);
//                mProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.i("GG","错误"+ex);
//                mProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//                Log.i("GG","结束");
//            }
//        });
    }

    //添加规格块
    @Event(R.id.add_guige)
    private void add_guigeClick(View view){
        if(guige_1.getVisibility() == View.GONE){
            guige_1.setVisibility(View.VISIBLE);
        }else if(guige_2.getVisibility() == View.GONE){
            guige_2.setVisibility(View.VISIBLE);
        }else if(guige_3.getVisibility() == View.GONE){
            guige_3.setVisibility(View.VISIBLE);
        }
    }
    //获取分类
    @Event(R.id.shangpinleimu)
    private void spClick(View view){
        Log.i("GG","!!!!!!!!!!!!!");
        HashMap<String ,String> hashMap = new HashMap<>();
//        hashMap.put("token",spf.getString("token",""));
        hashMap.remove("sign");
        HttpUtils.httputilsGet("/Api/getGoodsCats", hashMap, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","分类结果"+result.toString());
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
//        final String[] leimu = {"鼠标","电脑","服饰","运动鞋"};
//        final int[] i = new int[1];
//        AlertDialog.Builder builder = new AlertDialog.Builder(AddShangpinActivity.this);
//        builder.setTitle("类目");
//        builder.setCancelable(true);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if(i[0] == 1){
//
//                }else{
//                    shangpinleimu.setText(leimu[0]);
//                }
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.setSingleChoiceItems(leimu, 0,new  DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                shangpinleimu.setText(leimu[which]);
//                i[0] = 1;
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();
    }

    @Event(R.id.tjspfh)
    private void tjspfhClick(View view){
        finish();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if( i == R.id.qu_img){
            add_img.setVisibility(View.GONE);
        }else if(i == R.id.qu_img2){
            add_img2.setVisibility(View.GONE);
        }else if(i == R.id.qu_img3){
            add_img3.setVisibility(View.GONE);
        }else if(i == R.id.delete_guige1){
            guige_1.setVisibility(View.GONE);
        }else if(i == R.id.delete_guige2){
            guige_2.setVisibility(View.GONE);
        }else if(i == R.id.delete_guige3){
            guige_3.setVisibility(View.GONE);
        }
    }

//    //获取商品信息
//    public void setShangPin(){
//        //属性
//        String types = "/Api/extQueryByToken";
//        Log.i("GG","TOKEN的值"+spf.getString("token",""));
//        map.put("token",spf.getString("token",""));
//        map.put("sql","select * from wst_goods where goodsId ='"+id+"'");
//        map.remove("sign");
//
//        HttpUtils.httputilsPost(types,map, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                Log.i("GG","成功"+s.toString());
//
//                String ct = null;
//                try {
//                    JSONObject jsonObject = new JSONObject(s);
//                    ct = jsonObject.getString("code");
//
//                    if(ct.equals("200")){
//                        JSONArray list = jsonObject.getJSONArray("data");
//                        for (int i = 0; i < list.length(); i++) {
//                            JSONObject list_dahu = list.getJSONObject(i);
//                            String goodsId = list_dahu.getString("goodsId");
//                            String goodsSn = list_dahu.getString("goodsSn");
//                            String goodsName = list_dahu.getString("goodsName");
//                            String goodsImg = list_dahu.getString("goodsImg");
//                            String goodsThums = list_dahu.getString("goodsThums");
//                            String marketPrice = list_dahu.getString("marketPrice");
//                            String goodsStock = list_dahu.getString("goodsStock");
//
//                        }
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//                System.out.println("错误"+throwable.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException e) {
//
//            }
//
//            @Override
//            public void onFinished() {
//                Log.i("GG","结束");
//            }
//        });
//    }


}
