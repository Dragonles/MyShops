package com.myshops.shops.myshops;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.myshops.shops.bean.Areas;
import com.myshops.shops.bean.ProvinceBean;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
    public static String placeName = "选择地址";


    private ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    OptionsPickerView pvOptions;


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

    // 地区选择
    @ViewInject(R.id.btn_open_usershopaddress)
    private Button btn_open_usershopaddress;
    @Event(R.id.btn_open_usershopaddress)
    private void userShopAddEvent(View view){
        //areaType = "4";
        //parentId = "370102001000";
        //地区选择接口

        addPick();
        pvOptions.show();

        String types = "/KittyApi/areas";
        HashMap<String,String> map = new HashMap<>();
        map.put("parentId","0");
        map.put("areaType","0");
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("citys",s.toString());
                try {
                    JSONObject data = new JSONObject(s);
                    String code = data.get("code").toString();
                    String message = data.get("message").toString();
                    JSONArray datas = data.getJSONArray("data");

                    List<Areas> areasList = new ArrayList<Areas>();
                    for (int i = 0; i < datas.length(); i++) {
                        org.json.JSONObject datas2 = datas.getJSONObject(i);

//
//                        Areas areas = new Areas();
//                        areas.setAreaId(datas2.get("areaId").toString());
//                        areas.setAreaName(datas2.get("areaName").toString());
//                        areas.setParentId(datas2.get("parentId").toString());
//                        areas.setAreaType(datas2.get("areaType").toString());

//                        areasList.add(areas);

                        options1Items.add(new ProvinceBean((Long) datas2.get("areaId"),datas2.get("areaName").toString()));

                    }

                    final ArrayList<String> dataset = new ArrayList<String>();
                    for (Areas areas : areasList) {
                        String areaName = areas.getAreaName();
                        dataset.add(areaName);
                    }

                    //选项选择器
                    pvOptions = new OptionsPickerView(OpenActivity.this);
                    //选项1
//                    options1Items.add(new ProvinceBean(0,"广东"));
//                    options1Items.add(new ProvinceBean(1,"湖南"));
//                    options1Items.add(new ProvinceBean(3,"广西"));

                    //选项2
                    ArrayList<String> options2Items_01=new ArrayList<String>();
                    options2Items_01.add("广州");
                    options2Items_01.add("佛山");
                    options2Items_01.add("东莞");
                    options2Items_01.add("阳江");
                    options2Items_01.add("珠海");
                    ArrayList<String> options2Items_02=new ArrayList<String>();
                    options2Items_02.add("长沙");
                    options2Items_02.add("岳阳");
                    ArrayList<String> options2Items_03=new ArrayList<String>();
                    options2Items_03.add("桂林");
                    options2Items.add(options2Items_01);
                    options2Items.add(options2Items_02);
                    options2Items.add(options2Items_03);

                    //选项3
                    ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
                    ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
                    ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();
                    ArrayList<String> options3Items_01_01=new ArrayList<String>();
                    options3Items_01_01.add("白云");
                    options3Items_01_01.add("天河");
                    options3Items_01_01.add("海珠");
                    options3Items_01_01.add("越秀");
                    options3Items_01.add(options3Items_01_01);
                    ArrayList<String> options3Items_01_02=new ArrayList<String>();
                    options3Items_01_02.add("南海");
                    options3Items_01_02.add("高明");
                    options3Items_01_02.add("顺德");
                    options3Items_01_02.add("禅城");
                    options3Items_01.add(options3Items_01_02);
                    ArrayList<String> options3Items_01_03=new ArrayList<String>();
                    options3Items_01_03.add("其他");
                    options3Items_01_03.add("常平");
                    options3Items_01_03.add("虎门");
                    options3Items_01.add(options3Items_01_03);
                    ArrayList<String> options3Items_01_04=new ArrayList<String>();
                    options3Items_01_04.add("其他1");
                    options3Items_01_04.add("其他2");
                    options3Items_01_04.add("其他3");
                    options3Items_01.add(options3Items_01_04);
                    ArrayList<String> options3Items_01_05=new ArrayList<String>();
                    options3Items_01_05.add("其他1");
                    options3Items_01_05.add("其他2");
                    options3Items_01_05.add("其他3");
                    options3Items_01.add(options3Items_01_05);

                    ArrayList<String> options3Items_02_01=new ArrayList<String>();
                    options3Items_02_01.add("长沙长沙长沙长沙长沙长沙长沙长沙长沙1111111111");
                    options3Items_02_01.add("长沙2");
                    options3Items_02_01.add("长沙3");
                    options3Items_02_01.add("长沙4");
                    options3Items_02_01.add("长沙5");
                    options3Items_02_01.add("长沙6");
                    options3Items_02_01.add("长沙7");
                    options3Items_02_01.add("长沙8");
                    options3Items_02.add(options3Items_02_01);
                    ArrayList<String> options3Items_02_02=new ArrayList<String>();
                    options3Items_02_02.add("岳1");
                    options3Items_02_02.add("岳2");
                    options3Items_02_02.add("岳3");
                    options3Items_02_02.add("岳4");
                    options3Items_02_02.add("岳5");
                    options3Items_02_02.add("岳6");
                    options3Items_02_02.add("岳7");
                    options3Items_02_02.add("岳8");
                    options3Items_02_02.add("岳9");
                    options3Items_02.add(options3Items_02_02);
                    ArrayList<String> options3Items_03_01=new ArrayList<String>();
                    options3Items_03_01.add("好山水");
                    options3Items_03.add(options3Items_03_01);

                    options3Items.add(options3Items_01);
                    options3Items.add(options3Items_02);
                    options3Items.add(options3Items_03);

                    //三级联动效果
                    pvOptions.setPicker(options1Items, options2Items, options3Items, true);
                    //设置选择的三级单位
                    //  pvOptions.setLabels("省", "市", "区");
                    pvOptions.setTitle("选择城市");
                    pvOptions.setCyclic(false, true, true);
                    //设置默认选中的三级项目
                    //监听确定选择按钮
                    pvOptions.setSelectOptions(1, 1, 1);
                    pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3) {
                            //返回的分别是三个级别的选中位置
                            String tx = options1Items.get(options1).getPickerViewText()
                                    + options2Items.get(options1).get(option2)
                                    + options3Items.get(options1).get(option2).get(options3);
                            btn_open_usershopaddress.setText(tx);
                            //    vMasker.setVisibility(View.GONE);
                        }
                    });





//

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("aa", "2+fanhui");
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

    public void addPick(){

        //选项选择器
        pvOptions = new OptionsPickerView(this);
        //选项1
        options1Items.add(new ProvinceBean(0,"广东"));
        options1Items.add(new ProvinceBean(1,"湖南"));
        options1Items.add(new ProvinceBean(3,"广西"));

        //选项2
        ArrayList<String> options2Items_01=new ArrayList<String>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("阳江");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02=new ArrayList<String>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        ArrayList<String> options2Items_03=new ArrayList<String>();
        options2Items_03.add("桂林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();
        ArrayList<String> options3Items_01_01=new ArrayList<String>();
        options3Items_01_01.add("白云");
        options3Items_01_01.add("天河");
        options3Items_01_01.add("海珠");
        options3Items_01_01.add("越秀");
        options3Items_01.add(options3Items_01_01);
        ArrayList<String> options3Items_01_02=new ArrayList<String>();
        options3Items_01_02.add("南海");
        options3Items_01_02.add("高明");
        options3Items_01_02.add("顺德");
        options3Items_01_02.add("禅城");
        options3Items_01.add(options3Items_01_02);
        ArrayList<String> options3Items_01_03=new ArrayList<String>();
        options3Items_01_03.add("其他");
        options3Items_01_03.add("常平");
        options3Items_01_03.add("虎门");
        options3Items_01.add(options3Items_01_03);
        ArrayList<String> options3Items_01_04=new ArrayList<String>();
        options3Items_01_04.add("其他1");
        options3Items_01_04.add("其他2");
        options3Items_01_04.add("其他3");
        options3Items_01.add(options3Items_01_04);
        ArrayList<String> options3Items_01_05=new ArrayList<String>();
        options3Items_01_05.add("其他1");
        options3Items_01_05.add("其他2");
        options3Items_01_05.add("其他3");
        options3Items_01.add(options3Items_01_05);

        ArrayList<String> options3Items_02_01=new ArrayList<String>();
        options3Items_02_01.add("长沙长沙长沙长沙长沙长沙长沙长沙长沙1111111111");
        options3Items_02_01.add("长沙2");
        options3Items_02_01.add("长沙3");
        options3Items_02_01.add("长沙4");
        options3Items_02_01.add("长沙5");
        options3Items_02_01.add("长沙6");
        options3Items_02_01.add("长沙7");
        options3Items_02_01.add("长沙8");
        options3Items_02.add(options3Items_02_01);
        ArrayList<String> options3Items_02_02=new ArrayList<String>();
        options3Items_02_02.add("岳1");
        options3Items_02_02.add("岳2");
        options3Items_02_02.add("岳3");
        options3Items_02_02.add("岳4");
        options3Items_02_02.add("岳5");
        options3Items_02_02.add("岳6");
        options3Items_02_02.add("岳7");
        options3Items_02_02.add("岳8");
        options3Items_02_02.add("岳9");
        options3Items_02.add(options3Items_02_02);
        ArrayList<String> options3Items_03_01=new ArrayList<String>();
        options3Items_03_01.add("好山水");
        options3Items_03.add(options3Items_03_01);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
        //  pvOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);
                btn_open_usershopaddress.setText(tx);
                //    vMasker.setVisibility(View.GONE);
            }
        });


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

    //跳转页面回调方法
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
                btn_open_storeaddress.setText(placeName);
            }
        }
    }

}
