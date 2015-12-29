package com.myshops.shops.myshops;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myshops.shops.untils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.nereo.imagechoose.MultiImageSelectorActivity;

@ContentView(R.layout.activity_open)
public class OpenActivity extends AppCompatActivity {

    String storepicture = "",userpicture = "",userstorename = "",userphonenum = "",useremail = "",userpwd = "",userstore = "";
    private  static  final int REQUEST_IMAGE=2;
    private String mStoreFilePath = "",mUserFilePath = "";//图片路径
    static int where = 0; // 区分店铺图片和用户图片
    static int is_submit = 0; // 判断是否可以提交信息
    private double WEIDU ,JINGDU; // 经纬度
    int curryposition = 0;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

    }
    // 店铺照片  用户手持身份证照片
    @ViewInject(R.id.iv_open_storepicture)
    private ImageView iv_open_storepicture;
    //手持身份证
    @ViewInject(R.id.iv_open_userpicture)
    private ImageView iv_open_userpicture;
    //店铺名称
    @ViewInject(R.id.et_open_userstorname)
    private EditText et_open_userstorname;
    //手机号
    @ViewInject(R.id.et_open_userphonenum)
    private EditText et_open_userphonenum;
    //邮箱地址
    @ViewInject(R.id.et_open_useremailaddress)
    private EditText et_open_useremailaddress;
    //店铺地址
    @ViewInject(R.id.btn_open_storeaddress)
    private Button btn_open_storeaddress;
    @Event(R.id.btn_open_storeaddress)
    private void ChoosePlaceEvent(View view){
        Intent intent = new Intent(OpenActivity.this,ChoosePlaceActivity.class);
        startActivityForResult(intent,3);
    }
    //派送范围
    @ViewInject(R.id.btn_open_send)
    private Button btn_open_send;

    @Event(R.id.btn_open_send)

    private void SendEvent(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(OpenActivity.this);

        rg = new RadioGroup(x.app());
        rg.clearDisappearingChildren();
        rg.setPadding(50 ,20 ,50 ,20);
        final String[] rbtext = {"<1km","1<5km","5<10km","10<20km"};

        rg.clearDisappearingChildren();
        for ( int i = 0 ; i < 4 ; i++ ){
            RadioButton rb = new RadioButton(x.app());
            rb.setText(rbtext[i]);
            rb.setTextColor(Color.BLACK);
            rb.setId(i);
            rg.addView(rb);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                curryposition = checkedId;
                Log.i("qqq",curryposition+"");
            }
        });

        dialog.setView(rg);
        dialog.setTitle("选择免费派送范围");
        dialog.setIcon(android.R.drawable.btn_radio);
        dialog.setCancelable(true);
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


              btn_open_send.setText(rbtext[curryposition]);

            }
        });

        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();
    }

    //店铺介绍
    @ViewInject(R.id.et_open_userstore)
    private EditText et_open_userstore;

    @Event(R.id.btn_open_submit)
    private void LoginSubmitEvent(View view){
        is_submit = 0;

        storepicture = mStoreFilePath;
        userpicture = mUserFilePath;
        userstorename = et_open_userstorname.getText().toString();
        userphonenum = et_open_userphonenum.getText().toString();
        useremail = et_open_useremailaddress.getText().toString();
        userstore = et_open_userstore.getText().toString();

        if (!("".equals(storepicture))){
            is_submit += 1;
        }
        if (!("".equals(userpicture))){
            is_submit += 1;
        }
        if (!("".equals(userstorename))){
            is_submit += 1;
        }
        if (isMobileNO(userphonenum)){
            is_submit += 1;
        }
        if (isEmail(useremail)){
            is_submit += 1;
        }
        if (isPassWord(userpwd)){
            is_submit += 1;
        }
        if (!("".equals(userstore))){
            is_submit += 1;
        }

        if (is_submit == 7){
            // 内容填写完毕  提交注册
            String sqlusers = null;
            String sqlshops = null;
            sqlusers = "select loginPwd,userName,userPhone from wst_users where userId = 82";
          //  sqlusers = "insert into wst_users (userPhone ,userEmail ,loginPwd ) values ('"+userphonenum+"', '"+useremail+"','"+userpwd+"')";
            sqlshops = "INSERT INTO wst_users (shopName ,shopImg ,statusRemarks) VALUES ('"+userstorename+"','"+storepicture+"','"+userstore+"')";
            String types = "/Api/exeQuery";
            HashMap<String,String> map = new HashMap<>();
            map.put("sql",sqlusers);
          //  map.put("sqlshops",sqlshops);
            HttpUtils.httputilsPost(types,map, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Toast.makeText(x.app(), s ,Toast.LENGTH_SHORT).show();
                    Log.i("onSuccess",s.toString());
                }

                @Override
                public void onError(Throwable throwable, boolean b) {
                    Log.i("onError",throwable.toString());
                }

                @Override
                public void onCancelled(CancelledException e) {
                    Log.i("onError","走这里1");
                }

                @Override
                public void onFinished() {
                    Log.i("onError","走这里2");
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

    // 正则判断是否为正确的邮箱格式
    public boolean isEmail(String mobiles) {
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    // 判断密码格式是否正确
    public boolean isPassWord(String pwd){
        int pwdlength = pwd.length();
        if (pwdlength > 5 && pwdlength <= 20){
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
        } else{
            if (resultCode == 3){
                WEIDU = data.getDoubleExtra("weidu",0);
                JINGDU = data.getDoubleExtra("jingdu",0);
                Log.i("sss","WEIDU"+WEIDU);
                Log.i("sss","JINGDU"+JINGDU);
                btn_open_storeaddress.setText("修改位置");
            }
        }
    }

}
