package com.myshops.shops.myshops;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.myshops.shops.untils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.nereo.imagechoose.MultiImageSelectorActivity;

@ContentView(R.layout.activity_open)
public class OpenActivity extends AppCompatActivity {

    String storepicture = "",userpicture = "",username = "",useridcard = "",userphonenum = "",userpwd = "",userstore = "";
    private  static  final int REQUEST_IMAGE=2;
    private String mStoreFilePath = "",mUserFilePath = "";//图片路径
    static int where = 0; // 区分店铺图片和用户图片
    static int is_submit = 0; // 判断是否可以提交信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
    // 店铺照片  用户手持身份证照片
    @ViewInject(R.id.iv_open_storepicture)
    private ImageView iv_open_storepicture;
    @ViewInject(R.id.iv_open_userpicture)
    private ImageView iv_open_userpicture;
    // 用户真实姓名  用户身份证号  用户手机号 验证码  用户登录密码  店铺介绍
    @ViewInject(R.id.et_open_username)
    private EditText et_open_username;

    @ViewInject(R.id.et_open_useridcard)
    private EditText et_open_useridcard;

    @ViewInject(R.id.et_open_userphonenum)
    private EditText et_open_userphonenum;

    @ViewInject(R.id.et_open_verifycode)
    private EditText et_open_verifycode;

    @ViewInject(R.id.et_open_userpwd)
    private EditText et_open_userpwd;

    @ViewInject(R.id.et_open_userstore)
    private EditText et_open_userstore;

    @Event(R.id.btn_open_submit)
    private void LoginSubmitEvent(View view){

        storepicture = mStoreFilePath;
        userpicture = mUserFilePath;
        username = et_open_username.getText().toString();
        useridcard = et_open_useridcard.getText().toString();
        userphonenum = et_open_userphonenum.getText().toString();
        userpwd = et_open_userpwd.getText().toString();
        userstore = et_open_userstore.getText().toString();

        if (!("".equals(storepicture))){
            is_submit += 1;
        }
        if (!("".equals(userpicture))){
            is_submit += 1;
        }
        if (!("".equals(username))){
            is_submit += 1;
        }
        if (isIdCard(useridcard)){
            is_submit += 1;
        }
        if (isMobileNO(userphonenum)){
            is_submit += 1;
        }
        if (isPassWord(userpwd)){
            is_submit += 1;
        }

        if (is_submit == 6){
            // 内容填写完毕  提交注册
            String sql = null;
            sql = "select userName from wst_users";
            String types = "/Api/exeQuery";
            HashMap<String,String> map = new HashMap<>();
            map.put("sql",sql);
            HttpUtils.httpPost(types,map, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    System.out.println("onSuccess"+s.toString());
                }

                @Override
                public void onError(Throwable throwable, boolean b) {
                    System.out.println("onError"+throwable.toString());
                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });

        } else {
            Toast.makeText(OpenActivity.this,"请填写完整信息",Toast.LENGTH_SHORT).show();
        }



    }



    // 正则判断是否为正确的手机号格式
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
    // 正则判断是否为正确的身份证格式
    public boolean isIdCard(String mobiles) {
        Pattern p = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
    // 判断密码格式是否正确
    public boolean isPassWord(String pwd){

        int pwdlength = pwd.length();

        if (pwdlength < 6 && pwdlength >= 20){
            // 密码长度为 6-20 位
            return true;
        }
        return false;

    }

    public void ChooseStorePicture(View view){
        Intent intent = new Intent(OpenActivity.this,MultiImageSelectorActivity.class);
// whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        startActivityForResult(intent,REQUEST_IMAGE);
        where = 1;
    }

    public void ChooseUserPicture(View view){
        Intent intent = new Intent(OpenActivity.this,MultiImageSelectorActivity.class);
// whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        startActivityForResult(intent,REQUEST_IMAGE);
        where = 2;
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

                if (where == 1){
                    mStoreFilePath = path.get(0).toString();
                    iv_open_storepicture.setImageDrawable(null);
                    iv_open_storepicture.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                } else if (where == 2){
                    mUserFilePath = path.get(0).toString();
                    iv_open_userpicture.setImageBitmap(null);
                    iv_open_userpicture.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }

            }
        }
    }

}
