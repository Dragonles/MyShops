package com.myshops.shops.myshops;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

import me.nereo.imagechoose.MultiImageSelectorActivity;

@ContentView(R.layout.activity_add_shangpin)
public class AddShangPinActivity extends BaseActivity implements View.OnClickListener {
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

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.i("GG","id:--"+id);
        if ("".equals(id)){

        }else {
            setShangPin();
        }

        qu_img.setOnClickListener(this);
        qu_img2.setOnClickListener(this);
        qu_img3.setOnClickListener(this);
        delete_guige1.setOnClickListener(this);
        delete_guige2.setOnClickListener(this);
        delete_guige3.setOnClickListener(this);
    }

    @Event(R.id.tianjiasp)
    private void tjspClick(View view){
        mProgressDialog = ProgressDialog.show(AddShangPinActivity.this, "", "正在加载...");
        String types = "/AllOrders/addgoods";
        String spjs = String.valueOf(shangpinjieshao.getText());
        String spname = String.valueOf(shangpinname.getText());
        HashMap<String,String> map = new HashMap<>();
        map.put("goodsName",spname);
        map.put("goodsDesc",spjs);
        map.put("token",spf.getString("token",""));
        HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("aaa","-----onSuccess"+result);
                mProgressDialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("aaa","-----onError");
                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

//    @Event(R.id.shezhiyunfei)
//    private void szyfClick(View view){
//        startActivity(new Intent(AddShangPinActivity.this,YunfeiActivity.class));
//    }

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

    @Event(R.id.tjimg)
    private void tjimgClick(View view){
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);

        // whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

        // max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                for (String s : path){
                    Log.i("branddd",s);
                }
                if(add_img.getVisibility() == View.GONE){
                    add_img.setVisibility(View.VISIBLE);
                    imgs.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }else if(add_img2.getVisibility() == View.GONE){
                    add_img2.setVisibility(View.VISIBLE);
                    imgs2.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }else if(add_img3.getVisibility() == View.GONE){
                    add_img3.setVisibility(View.VISIBLE);
                    imgs3.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }
            }
        }
    }


    @Event(R.id.shangpinleimu)
    private void spClick(View view){
        final String[] leimu = {"鼠标","电脑","服饰","运动鞋"};
        final int[] i = new int[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(AddShangPinActivity.this);
        builder.setTitle("类目");
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(i[0] == 1){

                }else{
                    shangpinleimu.setText(leimu[0]);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setSingleChoiceItems(leimu, 0,new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shangpinleimu.setText(leimu[which]);
                i[0] = 1;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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

    //获取商品信息
    public void setShangPin(){
        //属性
        String types = "/Api/extQueryByToken";
//        map.put("url","/Api/register");
        map.put("token",spf.getString("token",""));
        map.put("sql","select * from wst_goods where goodsId ='"+id+"'");
        map.remove("sign");

        HttpUtils.httputilsPost(types,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("GG","onSuccess::::::"+s.toString());

                String ct = null;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    ct = jsonObject.getString("code");

                    if(ct.equals("200")){
                        JSONArray list = jsonObject.getJSONArray("data");
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject list_dahu = list.getJSONObject(i);
                            String goodsId = list_dahu.getString("goodsId");
                            String goodsSn = list_dahu.getString("goodsSn");
                            String goodsName = list_dahu.getString("goodsName");
                            String goodsImg = list_dahu.getString("goodsImg");
                            String goodsThums = list_dahu.getString("goodsThums");
                            String marketPrice = list_dahu.getString("marketPrice");
                            String goodsStock = list_dahu.getString("goodsStock");

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError::::::"+throwable.getMessage());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}
