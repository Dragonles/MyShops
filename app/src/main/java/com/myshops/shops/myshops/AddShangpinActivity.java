package com.myshops.shops.myshops;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import me.nereo.imagechoose.MultiImageSelectorActivity;

@ContentView(R.layout.activity_add_shangpin)
public class AddShangpinActivity extends AppCompatActivity {
    private  static  final int REQUEST_IMAGE=100;
    private String mStoreFilePath = "",mUserFilePath = "";//图片路径
@ViewInject(R.id.imageChose)
private ImageView images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void ChooseStorePicture(View view){
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
