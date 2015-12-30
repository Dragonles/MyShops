package com.myshops.shops.myshops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.myshops.shops.untils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

import me.nereo.imagechoose.MultiImageSelectorActivity;

@ContentView(R.layout.activity_add_shangpin)
public class AddShangpinActivity extends BaseActivity {
    private  static  final int REQUEST_IMAGE=100;
    Button btn_submit;
    SharedPreferences spf;
    EditText et_goodsName,et_goodsId,et_miaoshu,et_nowPrice,et_oldPrice,et_kucun,et_danwei,et_xiaoshouCount,et_yudingCount,et_createTime,et_xiaoshouTime;
    private String mStoreFilePath = "",mUserFilePath = "";//图片路径
@ViewInject(R.id.imageChose)
private ImageView images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spf = getSharedPreferences("muser", Context.MODE_PRIVATE);
        et_goodsName = (EditText) findViewById(R.id.add_goodsName);
        et_goodsId = (EditText) findViewById(R.id.add_goodId);
        et_miaoshu = (EditText) findViewById(R.id.add_shangoinmiaoshu);
        et_nowPrice = (EditText) findViewById(R.id.add_nowPrice);
        et_oldPrice = (EditText) findViewById(R.id.add_oldPrice);
        et_kucun = (EditText) findViewById(R.id.add_kucun);
        et_danwei = (EditText) findViewById(R.id.add_danwei);
        et_xiaoshouCount = (EditText) findViewById(R.id.add_xiaoshouCount);
        et_yudingCount = (EditText) findViewById(R.id.add_yudingCount);
        et_createTime = (EditText) findViewById(R.id.add_createTime);
        et_xiaoshouTime  = (EditText) findViewById(R.id.add_xiaoshouTime);
        btn_submit = (Button) findViewById(R.id.add_commit);
        //选择图片
        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseStorePicture();
            }
        });
        //点击完成
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> maps = new HashMap<>();
                maps.put("token",LoginActivity.token);
                maps.put("goodname",et_goodsName.getText().toString());
                maps.put("goodsId",et_goodsId.getText().toString());
                maps.put("goodsSpec",et_miaoshu.getText().toString());
                maps.put("Markeprice",et_nowPrice.getText().toString());
                maps.put("Shopprice",et_oldPrice.getText().toString());
                maps.put("Goodstock",et_kucun.getText().toString());
                maps.put("goodsUnit",et_danwei.getText().toString());
                maps.put("saleCount",et_xiaoshouCount.getText().toString());
                maps.put("bookQuantity",et_yudingCount.getText().toString());
                maps.put("createTime",et_createTime.getText().toString());
                maps.put("saleTime",et_xiaoshouTime.getText().toString());
                //添加商品  上传
                HttpUtils.httputilsPost("/AllOrders/addgoods", maps, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("GG","成功 "+result.toString());
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i("GG","失败"+ex);
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

    }
    //选择图片
    public void ChooseStorePicture(){
        Intent intent = new Intent(AddShangpinActivity.this,MultiImageSelectorActivity.class);
// whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        startActivityForResult(intent,REQUEST_IMAGE);
    }
    //返回图片路径方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                for (String s:path){

                }
                    mStoreFilePath = path.get(0).toString();
                    images.setImageDrawable(null);
                    images.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
            }
        }
    }

}
