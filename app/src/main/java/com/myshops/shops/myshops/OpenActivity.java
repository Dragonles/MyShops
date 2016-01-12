package com.myshops.shops.myshops;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.myshops.shops.bean.Areas;
import com.myshops.shops.untils.ActionSheetDialog;
import com.myshops.shops.untils.HttpUtils;
import com.myshops.shops.untils.QiNiuConfig;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.util.Auth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.WheelPicker;

@ContentView(R.layout.activity_open)
public class OpenActivity extends AppCompatActivity {

    String storepicture = "",userpicture = "",userstorename = "",userphonenum = "",storeplace = "",sendhowlong = "",userstore = "";
    static int where = 0; // 区分店铺图片和用户图片
    static boolean is_submit = false; // 判断是否可以提交信息
    private double WEIDU = 0 ,JINGDU = 0; // 经纬度
    int curryposition = 0;
    RadioGroup rg;//派送范围选项
    public static String placeName = "选择地址";

    private ArrayList<Areas> areasList = new ArrayList<>();
    final ArrayList<String> dataset = new ArrayList<String>();

    StringBuffer province = new StringBuffer();
    String first = "";
    public static String choosename = "";
    ProgressDialog progressDialog ;
    String userIds ="";

    private Uri photoUri;
    private final int PIC_FROM_CAMERA = 1;
    private final int PIC_FROM＿LOCALPHOTO = 0;
    static File stopicFile,userpicFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中……");

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
                sendhowlong = rbtext[curryposition];
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

    // 地区选择
    @ViewInject(R.id.btn_open_usershopaddress)
    private Button btn_open_usershopaddress;
    @Event(R.id.btn_open_usershopaddress)
    private void userShopAddEvent(View view){

        progressDialog.show();
        //地区选择接口
        String types = "/KittyApi/areas";
        HashMap<String,String> map = new HashMap<>();
        map.put("parentId","0");
        map.put("areaType","0");
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                progressDialog.dismiss();
                Log.i("citys",s.toString());
                areasList.clear();
                dataset.clear();
                province.setLength(0);
                try {
                    JSONObject data = new JSONObject(s);
                    String code = data.get("code").toString();
                    String message = data.get("message").toString();
                    JSONArray datas = data.getJSONArray("data");
                    for (int i = 0; i < datas.length(); i++) {
                        org.json.JSONObject datas2 = datas.getJSONObject(i);
                        String id = (String) datas2.get("areaId");
                        String name = (String) datas2.get("areaName");
                        Log.i("areas","ID:"+id+"-----名称："+name);
                        areasList.add(new Areas(id,name));
                    }
                    for (int i = 0; i < datas.length(); i++) {

                        Log.i("areascity","ID:"+areasList.get(i).getId()+"-----名称："+areasList.get(i).getName());
                        dataset.add(areasList.get(i).getName());
                    }
                    addPick();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("aa", "2+fanhui---"+throwable+"-----"+b);
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("aa", "3+fanhui");
            }

            @Override
            public void onFinished() {
                Log.i("aa", "4+fanhui");
            }
        });
    }
    // 弹出选择窗口
    public void addPick(){
        OptionPicker picker = new OptionPicker(OpenActivity.this);
        picker.setOptions(dataset);
        picker.setOnWheelListener(new WheelPicker.OnWheelListener<int[]>() {
            @Override
            public void onSubmit(int[] result) {


                first = dataset.get(result[0]);

                province.append(first);

                Jsonjiexi("/Api/exeQuery",areasList.get(result[0]).getId());
                areasList.clear();
                dataset.clear();
                btn_open_usershopaddress.setText(province);
            }
        });
        picker.showAtBottom();
    }
    //下一级城市查询
    private void Jsonjiexi(String d,String zhi){
        progressDialog.show();
        String sql = "select * from wst_areas where parentId = "+zhi;
        HashMap<String,String> map = new HashMap<>();
        if("/Api/exeQuery".equals(d)){
            map.put("sql",sql);
        }

        HttpUtils.httputilsPost(d, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                progressDialog.dismiss();
                Log.i("qing",result);
                JSONObject jsonObject = null;
                String code = null;
                String message = null;
                JSONArray sheng = null;
                try {
                    jsonObject = new JSONObject(result);
                    code = jsonObject.getString("code");
                    message = jsonObject.getString("message");
                    sheng = jsonObject.getJSONArray("data");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if ("200".equals(code)){
                    Log.i("qing","if---"+province+"-----date-----"+sheng);
                    Log.i("qing","choosename-----"+choosename);
                    if (sheng.length() != 0){
                        areasList.clear();
                        for (int i = 0; i < sheng.length(); i++) {
                            JSONObject shengs = null;
                            try {
                                shengs = sheng.getJSONObject(i);
                                String areaId = shengs.getString("areaId");
                                String parentId = shengs.getString("parentId");
                                String areaName = shengs.getString("areaName");
                                String areaType = shengs.getString("areaType");
                                Log.i("addresssheng","aaaaa"+areaName);
                                areasList.add(new Areas(areaId,areaName));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        dataset.clear();
                        for (int i = 0; i < sheng.length(); i++) {
                            Log.i("areascity","ID:"+areasList.get(i).getId()+"-----名称："+areasList.get(i).getName());
                            dataset.add(areasList.get(i).getName());
                        }
                        addPick();
                    } else {
                        Log.i("qing","------走这里------");
                        choosename = province.toString();
                        Log.i("qing","choosename-----"+choosename);
                        province.setLength(0);
                    }

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    //店铺介绍
    @ViewInject(R.id.et_open_userstore)
    private EditText et_open_userstore;

    @Event(R.id.btn_open_submit)
    private void OpenSubmitEvent(View view){
        progressDialog.setMessage("正在提交");
        progressDialog.show();
        SharedPreferences user = getSharedPreferences("user_info",0);
        String token = user.getString("token","");
        if ("".equals(token)){
            Toast.makeText(OpenActivity.this,"登陆信息错误，请重新登陆！",Toast.LENGTH_SHORT).show();
        } else {
            Log.i("token",token);
            String sql = "SELECT userId from wst_user_token where token ='"+token+"'";
            String types = "/Api/exeQuery";
            HashMap<String,String> map = new HashMap<>();
            map.put("sql",sql);
            //  map.put("sqlshops",sqlshops);
            HttpUtils.httputilsPost(types,map, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {

                    //   Toast.makeText(x.app(), s ,Toast.LENGTH_SHORT).show();
                    Log.i("onSuccess",s.toString());
                    String code = null;
                    try {
                        JSONObject js = new JSONObject(s);
                        code = js.getString("code");
                        String message = js.getString("message");
                        JSONArray data = js.getJSONArray("data");
                        JSONObject info = data.getJSONObject(0);
                        userIds = info.getString("userId");
                        Log.i("userid",userIds+"");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if ("200".equals(code)){
                        upLoadImage(stopicFile,userpicFile);

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(OpenActivity.this,"程序出现错误，请重新登陆！",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OpenActivity.this,LoginActivity.class);
                        SharedPreferences user = getSharedPreferences("user_info",0);
                        SharedPreferences.Editor edit = user.edit();
                        edit.putString("NAME", "");
                        edit.commit();
                        startActivity(intent);
                        OpenActivity.this.finish();
                    }

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
        }


    }

    //提交信息
    public void openShop(){
        is_submit = false;
        //店铺图片   storepicture
        //用户身份证   userpicture
        userstorename = et_open_userstorname.getText().toString();//店铺名称
        userphonenum = et_open_userphonenum.getText().toString();// 店铺手机号
        storeplace = choosename;//店铺地址
        //经纬度         WEIDU = 0 ,JINGDU
        //派送范围       sendhowlong
        userstore = et_open_userstore.getText().toString();// 店铺介绍

        if (!("".equals(storepicture))){
            is_submit = true;
            Log.i("issubmit","is_submit-----"+is_submit+"-----storepicture-----"+storepicture);
            //店铺图片通过
        } else {
            is_submit = false;

        }
        if (!("".equals(userpicture))){
            is_submit = true;
            Log.i("issubmit","is_submit-----"+is_submit+"-----userpicture-----"+userpicture);
            //用户图片通过
        } else {
            is_submit = false;

        }
        if (!("".equals(userstorename))){
            is_submit = true ;
            Log.i("issubmit","is_submit-----"+is_submit+"-----userstorename-----"+userstorename);
            //店铺名称通过
        } else {
            is_submit=false;
        }
        if (isMobileNO(userphonenum)){
            is_submit = true ;
            Log.i("issubmit","is_submit-----"+is_submit+"-----userphonenum-----"+userphonenum);
            //手机号码通过
        } else {
            is_submit=false;
        }
        if (!("".equals(storeplace))){
            is_submit = true ;
            Log.i("issubmit","is_submit-----"+is_submit+"-----storeplace-----"+storeplace);
            //店铺地址通过
        } else {
            is_submit=false;
        }
        if (JINGDU != 0 && WEIDU != 0){
            is_submit = true ;
            Log.i("issubmit","is_submit-----"+is_submit+"-----JINGDU-----"+JINGDU+"-----WEIDU-----"+WEIDU);
            //经纬度通过
        } else {
            is_submit=false;
        }
        if (!("".equals(sendhowlong))){
            is_submit = true ;
            Log.i("issubmit","is_submit-----"+is_submit+"-----sendhowlong-----"+sendhowlong);
            //派送范围通过
        } else {
            is_submit=false;
        }
        if (!("".equals(userstore))){
            is_submit = true ;
            Log.i("issubmit","is_submit-----"+is_submit+"-----userstore-----"+userstore);
            //店铺介绍通过
        } else {
            is_submit=false;
        }

        if (is_submit == true){
            progressDialog.setMessage("正在提交");
            progressDialog.show();
            Log.i("issubmit","内容填写完毕  提交注册");
            // 内容填写完毕  提交注册
            String sql = "INSERT INTO wst_shops (userId ,shopName ,shopImg ,shopTel ,shopAddress ,latitude ,longitude ,statusRemarks ,perimfo ,ranges) VALUES ("+userIds+",'"+userstorename+"','"+storepicture+"','"+userphonenum+"','"+storeplace+"','"+WEIDU+"','"+JINGDU+"','"+userstore+"','"+userpicture+"','"+sendhowlong+"')";
            String types = "/Api/exeInsertQuery";
            HashMap<String,String> map = new HashMap<>();
            map.put("sql",sql);
            HttpUtils.httputilsPost(types,map, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    progressDialog.dismiss();
                    // Toast.makeText(x.app(), s ,Toast.LENGTH_SHORT).show();
                    Log.i("submit",s.toString());
                    JSONObject jsonObject = null;
                    String code = null;
                    String istrue = null;
                    try {
                        jsonObject = new JSONObject(s);
                        code = jsonObject.getString("code");
                        istrue = jsonObject.getString("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if ("200".equals(code)){
                        if ("1".equals(istrue)){
                            Intent intent = new Intent(OpenActivity.this,MainActivity.class);
                            SharedPreferences user = getSharedPreferences("user_info",0);
                            SharedPreferences.Editor edit = user.edit();
                            edit.putString("hasShops", "1");
                            edit.commit();
                            startActivity(intent);
                            OpenActivity.this.finish();
                            Toast.makeText(x.app(),"开店成功，等待认证！",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(x.app(),"请求失败，请重新提交！",Toast.LENGTH_SHORT).show();
                        }

                    }
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

    public void ChooseStorePicture(View view){
        new ActionSheetDialog(OpenActivity.this)
                .builder()
                .setTitle("选项")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍摄照片", ActionSheetDialog.SheetItemColor.Red,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                doHandlerPhoto(PIC_FROM_CAMERA);// 用户点击了从照相机获取
                                // onActivityResult(PIC_FROM_CAMERA,0,null);
                            }
                        })
                .addSheetItem("选取本地", ActionSheetDialog.SheetItemColor.Red,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
                            }
                        }).show();
        where = 1;
    }

    public void ChooseUserPicture(View view){
        new ActionSheetDialog(OpenActivity.this)
                .builder()
                .setTitle("选项")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍摄照片", ActionSheetDialog.SheetItemColor.Red,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                doHandlerPhoto(PIC_FROM_CAMERA);// 用户点击了从照相机获取
                                // onActivityResult(PIC_FROM_CAMERA,0,null);
                            }
                        })
                .addSheetItem("选取本地", ActionSheetDialog.SheetItemColor.Red,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
                            }
                        }).show();
        where = 2;
    }

    private void doHandlerPhoto(int type) {
        try {
            // 保存裁剪后的图片文件
            File pictureFileDir = new File(
                    Environment.getExternalStorageDirectory(), "/maimai");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            int rand=(int)(Math.random()*100000);
            Log.i("ran",rand+"");
            File picFile = null;
            if (where == 1){
                stopicFile = new File(pictureFileDir, rand+".jpeg");
                picFile = stopicFile;
            } else if (where == 2){
                userpicFile = new File(pictureFileDir, rand+".jpeg");
                picFile = userpicFile;
            }

            if (!picFile.exists()) {
                picFile.createNewFile();
                Log.i("imgs",picFile.toString());
            }
            photoUri = Uri.fromFile(picFile);
            Log.i("img",photoUri.toString());
            if (type == PIC_FROM＿LOCALPHOTO) {
                Intent intent = getCropImageIntent();
                startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
            } else {
                Intent cameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, PIC_FROM_CAMERA);
            }

        } catch (Exception e) {
            Log.i("HandlerPicError", "处理图片出现错误");
        }
    }

    /**
     * 调用图片剪辑程序
     */
    public Intent getCropImageIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        setIntentParams(intent);
        return intent;
    }

    /**
     * 启动裁剪
     */
    private void cropImageUriByTakePhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        setIntentParams(intent);
        startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
    }

    /**
     * 设置公用参数
     */
    private void setIntentParams(Intent intent) {
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try
        {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
    //跳转页面回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case PIC_FROM_CAMERA: // 拍照
                try
                {
                    cropImageUriByTakePhoto();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case PIC_FROM＿LOCALPHOTO:
                try
                {
                    if (photoUri != null)
                    {

                        Bitmap bitmap = decodeUriAsBitmap(photoUri);
                        if (where == 1){
                            iv_open_storepicture.setImageBitmap(bitmap);
                        } else if (where == 2){
                            iv_open_userpicture.setImageBitmap(bitmap);
                        }
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            default:
                if (resultCode == 3){
                    WEIDU = data.getDoubleExtra("weidu",0);
                    JINGDU = data.getDoubleExtra("jingdu",0);
                    Log.i("sss","WEIDU"+WEIDU);
                    Log.i("sss","JINGDU"+JINGDU);
                    btn_open_storeaddress.setText(placeName);
                }
                break;
        }

    }

    // 上传图片
    /**
     * 生成七牛上传token
     * */
    public String qiNiuUpToken(){
        //七牛key
        Auth auth = Auth.create(QiNiuConfig.ak,QiNiuConfig.sk);
        //七牛空间名称
        String bucketName = QiNiuConfig.bucketName;
        //生成上传token
        String token = auth.uploadToken(bucketName);
        return token;
    }

    private void upLoadImage(final File picFile1, final File picFile2){

        new Thread(new Runnable() {
            @Override
            public void run() {

                //开始上传文件
                try {

                    UploadManager uploadManager = new UploadManager();
                    uploadManager.put(picFile1, suiJiName(), qiNiuUpToken(), new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info, JSONObject response) {

                            Log.i("qiniu", key + " " + info + " " + response);
                            storepicture = key;
                            UploadManager uploadManager = new UploadManager();
                            uploadManager.put(picFile2, suiJiName(), qiNiuUpToken(), new UpCompletionHandler() {
                                @Override
                                public void complete(String key, ResponseInfo info, JSONObject response) {

                                    Log.i("qiniu", key + " " + info + " " + response);
                                    userpicture = key;
                                    openShop();
                                }
                            }, null);
                        }
                    }, null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public String suiJiName(){
        //随机数
        String s = "";
        Random ran =new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            s = s + ran.nextInt(100);
        }
        //上传的文件名
        String keyname = "wst_"+s+".jpg";
        return  keyname;
    }

}
