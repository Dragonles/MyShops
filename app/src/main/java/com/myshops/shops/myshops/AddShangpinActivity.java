package com.myshops.shops.myshops;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myshops.shops.bean.Goods_classify;
import com.myshops.shops.untils.ActionSheetDialog;
import com.myshops.shops.untils.HttpUtils;
import com.myshops.shops.untils.QiNiuConfig;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.util.Auth;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.WheelPicker;

@ContentView(R.layout.activity_add_shangpin)
public class AddShangpinActivity extends BaseActivity implements View.OnClickListener {
    int c = 0;
    static int where = 0; // 区分商品图片和缩略图片
    StringBuffer province = new StringBuffer();
    final ArrayList<String> dataset = new ArrayList<String>();
    String first = "",sFile,sFiles;
    SharedPreferences spf;
    private final int PIC_FROM_CAMERA = 1;
    private final int PIC_FROM＿LOCALPHOTO = 0;
    private final int PIC_FROM_CAMERA_SUO = 5;
    private final int PIC_FROM＿LOCALPHOTO_SUO = 6;
    private ProgressDialog mProgressDialog;
    public static String choosename = "";
    private Uri photoUri;
    private ArrayList<Goods_classify> classifyList = new ArrayList<>();
    private static final int REQUEST_IMAGE =2;
    String mFilePath,zFilePath;
    static File picFile,files;
    HashMap<String,String> map = new HashMap<>();
    String id;
    List<Goods_classify> fenlei_list = new ArrayList<>();

    @ViewInject(R.id.shangpinleimu_txt)
    private TextView shangpinleimu_txt;

    // 添加图片
    @ViewInject(R.id.qu_suolueimg1)
    private ImageView qu_suolueimg1;

    @ViewInject(R.id.qu_suolueimg2)
    private ImageView qu_suolueimg2;

    @ViewInject(R.id.qu_suolueimg3)
    private ImageView qu_suolueimg3;

    @ViewInject(R.id.suolueimg1)
    private ImageView suolueimg1;

    @ViewInject(R.id.suolueimg2)
    private ImageView suolueimg2;

    @ViewInject(R.id.suolueimg3)
    private ImageView suolueimg3;

    @ViewInject(R.id.layout_suolueimg1)
    private RelativeLayout layout_suolueimg1;

    @ViewInject(R.id.layout_suolueimg2)
    private RelativeLayout layout_suolueimg2;

    @ViewInject(R.id.layout_suolueimg3)
    private RelativeLayout layout_suolueimg3;

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

    @ViewInject(R.id.shangpinLeimu_btn)
    private Button shangpinLeimu;

//    @ViewInject(R.id.shangpinDialog)
//    private Button shangpinDialog;

    @ViewInject(R.id.add_img3)
    private RelativeLayout add_img3;

    @ViewInject(R.id.imgs3)
    private ImageView imgs3;


    @ViewInject(R.id.shangpinname)
    private EditText shangpinname;

    @ViewInject(R.id.shangpinjieshao)
    private EditText shangpinjieshao;
    @ViewInject(R.id.shangpin_text)
    private TextView shangpin_text;
    @ViewInject(R.id.goods_number)
    private EditText goods_number;
    @ViewInject(R.id.goods_Id)
    private EditText goods_Id;

    private String[] FanWei ;
    private String[] GuigeName;
    int shuju,shuju2;


    List<String> path ;

    @ViewInject(R.id.tianjia0)
    private Button tianjia0;

    @ViewInject(R.id.linearLayout)
    private LinearLayout linearLayout;

    @ViewInject(R.id.xuanzhong)
    private LinearLayout xuanzhong;

    @ViewInject(R.id.queding)
    private Button queding;

    @ViewInject(R.id.shichangjiage)
    private EditText jiage;

    @ViewInject(R.id.dianpujiage)
    private EditText dianpujiage;

    @ViewInject(R.id.kucun)
    private EditText kucun;

    @ViewInject(R.id.shangpindanwei)
    private EditText shangpindanwei;

    int a = 0;
    int dian;
    int dijipai;
    //设置属性规格
    HashMap<String ,List> m = new HashMap<String, List>();
    HashMap<String ,List> ma = new HashMap<>();
    List<View> list_view = new ArrayList<>();
    private TagFlowLayout mFlowLayout,myFlowLayout;
    List<TagFlowLayout> mF_list = new ArrayList<>();

    HashMap<String,Integer> hashMap = new HashMap<>();
    List<EditText> shuxingming_list = new ArrayList<>();
    int shu;
    int diji;
    static String[] shuxing_leixing;
    LayoutInflater mInflater;

    JSONObject list_dahu;
    JSONObject jsonObject2;
    String shuxingming;
    JSONArray list_dahu2,jsonObject3,list_dahu3,list_dahu4;


    public Handler handler_Classify = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //处理消息
            fenlei_list = (List<Goods_classify>) msg.obj;
            if (msg.what==1){
                for (int j = 0 ; j<shuju;j++ ){
                    FanWei[j] = fenlei_list.get(j).getClassify_name();
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spf = getSharedPreferences("user_info",Context.MODE_PRIVATE);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        if ("".equals(id)){

        }else {
            setShangPin();
//            addFenLei();
            setShuxing();
        }

        qu_img.setOnClickListener(this);
        qu_img2.setOnClickListener(this);
        qu_img3.setOnClickListener(this);
        qu_suolueimg1.setOnClickListener(this);
        qu_suolueimg2.setOnClickListener(this);
        qu_suolueimg3.setOnClickListener(this);
        //点击确定的点击事件
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cishu = 0;
                List<String> li = new ArrayList<String>();
                m.put("li" + cishu, li);
                for ( int a = 0; a<=ma.size()-1;a++){
                    li.add(shuxingming_list.get(a).getText()+"-"+(String) ma.get("list"+a).get(hashMap.get("dian" + a)));
                }
                //  添加市场价格 库存 单位 店铺价格
                Log.i("GG","市场价格"+jiage.getText().toString()+"店铺价格"+dianpujiage.getText().toString()+"库存"+kucun.getText().toString()+"单位"+shangpindanwei.getText().toString());
                li.add(dianpujiage.getText().toString());
                li.add(jiage.getText().toString());
                li.add(kucun.getText().toString());
                li.add(shangpindanwei.getText().toString());
                Log.i("GG","jiae  "+jiage.getText().toString());
                if ("".equals(jiage.getText().toString()) || "".equals(kucun.getText().toString())){
                    Toast.makeText(AddShangpinActivity.this,"价格或者库存不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    addzhi(xuanzhong, cishu);
                }
                cishu++;
            }
        });

        //添加的点击事件
        tianjia0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int abc = list_view.size();
                if (ma.get("list" + abc) == null && ma.size() < list_view.size()) {
                    Toast.makeText(AddShangpinActivity.this, "请先输入上一条", Toast.LENGTH_SHORT).show();
                } else {
                    addOtherLayout(a, linearLayout);
                }
            }
        });
    }

    //添加选出来的值：
    public void addzhi(final LinearLayout xuanzhong,int a){
        final View otherView = View.inflate(this, R.layout.flayout, null);
        xuanzhong.addView(otherView);

        myFlowLayout = (TagFlowLayout) otherView.findViewById(R.id.id_flowlayout);
        myFlowLayout.setMaxSelectCount(1);
        final LayoutInflater myInflater = LayoutInflater.from(otherView.getContext());

        Log.i("GG","mmmmmmmm"+m.get("li"+a));

        final TagAdapter tagAdapter = new TagAdapter<String>(m.get("li"+a)) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) myInflater.inflate(R.layout.tv,
                        myFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        myFlowLayout.setAdapter(tagAdapter);

    }


    //添加属性名那一条
    public void addOtherLayout(int b, final LinearLayout otherLayout) {
        final View otherView = View.inflate(this, R.layout.activity_main2, null);
        otherLayout.addView(otherView);
        list_view.add(otherView);
        otherView.setTag(otherView);

        EditText shuxingming = (EditText) otherView.findViewById(R.id.shuxingming);
        shuxingming_list.add(shuxingming);
        final Button qu_tu = (Button) otherView.findViewById(R.id.qu_tu);
        Button add = (Button) otherView.findViewById(R.id.add);

        //删除的点击事件
        qu_tu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int g = 0 ; g<ma.size();g++){
                    if (otherView.equals(list_view.get(g))){
                        ma.remove("list"+g);
                    }
                }
                otherLayout.removeView(otherView);
                list_view.remove(otherView);

            }
        });

        //属性名长按事件
//        shuxingming.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                qu_tu.setVisibility(View.VISIBLE);
//                return true;
//            }
//        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLog(otherView);
            }
        });
        mInflater = LayoutInflater.from(otherView.getContext());

        mFlowLayout = (TagFlowLayout) otherView.findViewById(R.id.id_flowlayout);
        mF_list.add(mFlowLayout);
        mFlowLayout.setMaxSelectCount(1);

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                diji = position;
                return true;
            }
        });

        mFlowLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                queding(otherView);
                return true;
            }
        });
        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                dian = selectPosSet.iterator().next();
                for (int s = 0; s<list_view.size();s++){
                    if ( otherView.equals(list_view.get(s)) ){
                        dijipai = s;
                        hashMap.put("dian" + dijipai,dian);
                    }
                }
            }
        });

    }
    //添加值得弹出框
    public void diaLog(final View list_view0){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.alertext_form, null);
        dialog.setView(layout);
        final EditText editText = (EditText)layout.findViewById(R.id.etName);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String zhi = editText.getText().toString();
                if ("".equals(zhi)){
                    Toast.makeText(AddShangpinActivity.this,"值为空,添加失败",Toast.LENGTH_SHORT).show();
                }else {
                    if (list_view.size() == 1 && ma.size() == 0){
                        a = list_view.size()-1;
                        List<String> list = new ArrayList<String>();
                        list.add(zhi);
                        ma.put("list" + a, list);
                        tianjia();
                    }else {
                        for (int i= 0; i<list_view.size();i++){
                            if (list_view.get(i).equals(list_view0) && ma.size()==list_view.size()){
                                Log.i("GG","list:"+ma.get("list"+a));
                                a = i;
                                ma.get("list"+a).add(zhi);
                            }
                        }if (ma.size()<list_view.size()) {
                            a = list_view.size() - 1;
                            List<String> list0 = new ArrayList<String>();
                            list0.add(zhi);
                            ma.put("list" + a , list0);
                            tianjia();
                        }
                    }
                    Set<Map.Entry<String, List>> set = ma.entrySet();
                    Iterator<Map.Entry<String, List>> it = set.iterator();
                    while(it.hasNext()) {
                        Map.Entry<String, List> entry = it.next();
                        Log.i("GG",entry.getKey() +"-->" + entry.getValue());
                    }
                    mF_list.get(a).onChanged();

                }
            }
        });

        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();

    }

    //确定按钮
    public void queding(View view){

        for (int s = 0; s<list_view.size();s++){
            if ( view.equals(list_view.get(s)) ){
                shu = s;
            }
        }

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("是否删除");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ma.get("list"+shu).remove(diji);
                mF_list.get(shu).onChanged();
                myFlowLayout.onChanged();
            }
        });


        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //FlowLayout赋值
    public void tianjia(){
        final TagAdapter tagAdapter = new TagAdapter<String>(ma.get("list"+a)) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        mF_list.get(a), false);
                tv.setText(s);
                return tv;
            }
        };
        mF_list.get(a).setAdapter(tagAdapter);
    }

    //属性规格模板
    public void setShuxing(){

        HashMap<String ,String> mp = new HashMap<>();
        String types = "/Api/getSpecInfo";
        HttpUtils.httputilsGet(types, mp , new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("aaa","规格+成功"+result.toString());
                try {
                    //第一步
                    JSONObject json1 = new JSONObject(result);
                    JSONArray json2 = json1.getJSONArray("data");
                    //创建JSONObject集合 保存所有属性数据
                    List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
                    for(int i = 0;i<json2.length();i++){
                        JSONObject json3 = json2.getJSONObject(i);
                        jsonObjectList.add(json3);
                    }
                    //开始正式解析 第二层 属性
                    for(int j = 0;j<jsonObjectList.size();j++){
                        JSONObject json4 = jsonObjectList.get(j);
                        Log.i("GG","  json4="+json4.toString());
                        //本层属性：specId,createTime,specName,specContent
                        //返回第一层属性数据*************************************！！！！！--ss
                        String specName = json4.getString("specName");
                        Log.i("GG"," 第一层数据："+specName);
                        //specContent  数组 详细属性解析
                        //判断数组是否为空
                        if(json4.optJSONArray("specContent") != null){
                            //第二层详细属性数组
                            JSONArray json5 = json4.optJSONArray("specContent");
                            //Map<String,Object> map = new HashMap<String,Object>();
                            //递归解析JSONArray-------(不使用)----再添加属性层后使用
                            //jsonArrayDiGui(json5);-------(不使用)----再添加属性层后使用
                            for(int m = 0;m<json5.length();m++){
                                JSONArray jsonArray1 = json5.getJSONArray(m);
                                //返回第二层属性数据*************************************！！！！！--ss
                                String ss = ""+jsonArray1.get(0);
                                Log.i("GG"," 第二层数据："+ss);
                                for(int n = 1;n<jsonArray1.length();n++){
                                    //第三层属性数据*************************************！！！！！--s6
                                    JSONArray json6 = jsonArray1.getJSONArray(n);
                                    for(int x = 0;x<json6.length();x++){
                                        String s6 = ""+json6.get(x);
                                        Log.i("aaa","  第三层数据："+s6);
                                    }
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("GG","-----onError"+ex + "            "+isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
            //  商品类型
//    @Event(R.id.shangpinDialog)
//    private void addDia(View view) {
//
//        OptionPicker picker = new OptionPicker(AddShangpinActivity.this);
//        picker.setOptions(shuxing_leixing);
//        picker.setSelectedOption(2);
//        picker.setOnWheelListener(new WheelPicker.OnWheelListener<int[]>() {
//            @Override
//            public void onSubmit(int[] result) {
//                shangpin_text.setText(shuxing_leixing[result[0]]);
//            }
//        });
//        picker.showAtBottom();
//    }

    //   完成的点击事件
    @Event(R.id.tianjiasp)
    private void tjspClick(View view){
        if(picFile.length() == 0 && files.length() == 0){
            Log.i("GG","图片没上传完全！！！！");
        }else{
            upLoadImage(picFile,files);
        }
        Log.i("GG","上传的方法执行完毕");

    }
// 运费 的点击事件
//    @Event(R.id.shezhiyunfei)
//    private void szyfClick(View view){
//        startActivity(new Intent(AddShangpinActivity.this,YunfeiActivity.class));
//    }

    //  商品类目的点击事件  下拉
    @Event(R.id.shangpinLeimu_btn)
    private void fenlei(View view){
        Log.i("GG","GGFanWei"+FanWei);
//        OptionPicker picker = new OptionPicker(AddShangpinActivity.this);
//        picker.setOptions(FanWei);
//        picker.setSelectedOption(2);
//        picker.setOnWheelListener(new WheelPicker.OnWheelListener<int[]>() {
//            @Override
//            public void onSubmit(int[] result) {
//                shangpinleimu_txt.setText(FanWei[result[0]]);
//            }
//        });
//        picker.showAtBottom();
//        addPick();
        addFenLei();
    }

//          添加商品图片
    @Event(R.id.tjimg)
    private void tjimgClick(View view){
        new ActionSheetDialog(AddShangpinActivity.this)
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
//      添加商品缩略图
    @Event(R.id.tj_suolueImg)
    private void tj_suolueImg(View view){
        new ActionSheetDialog(AddShangpinActivity.this)
                .builder()
                .setTitle("选项")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍摄照片", ActionSheetDialog.SheetItemColor.Red,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                doHandlerPhoto_Suo(PIC_FROM_CAMERA_SUO);// 用户点击了从照相机获取
                                // onActivityResult(PIC_FROM_CAMERA,0,null);
                            }
                        })
                .addSheetItem("选取本地", ActionSheetDialog.SheetItemColor.Red,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                doHandlerPhoto_Suo(PIC_FROM＿LOCALPHOTO_SUO);
                            }
                        }).show();
        where = 2;
    }
    //   返回按钮
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
        }else if(i == R.id.qu_suolueimg1){
            layout_suolueimg1.setVisibility(View.GONE);
        }else if(i == R.id.qu_suolueimg2){
            layout_suolueimg1.setVisibility(View.GONE);
        }else if(i == R.id.qu_suolueimg3){
            layout_suolueimg1.setVisibility(View.GONE);
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

        HttpUtils.httputilsGet(types,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("GG","onSuccess:::商品修改信息:::"+s.toString());

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


    //获取商品的分类
    public void addFenLei(){
        //属性
        String types = "/Api/getGoodsCats";
        map.remove("sign");
        HttpUtils.httputilsGet(types,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("GG","管罗苍获取商城的分类"+s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String code  = jsonObject.getString("code");
                    if("200".equals(code)){
                        JSONArray list = jsonObject.getJSONArray("data");
                        FanWei = new String[list.length()];
                        Log.i("GG","管罗苍分类的长度"+list.length());
                        shuju = list.length();
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject list_dahu = list.getJSONObject(i);
                            String catId = list_dahu.getString("catId");
                            String catName = list_dahu.getString("catName");
                            Goods_classify shangpinFenLei = new Goods_classify(catName,catId);
                            fenlei_list.add(shangpinFenLei);
                            Log.i("GG","分类执行完成");
                        }
                        for (int i =0 ; i< fenlei_list.size(); i ++){
                            dataset.add(fenlei_list.get(i).getClassify_name());
                        }
                        addPick();
                        Message message = new Message();
                        message.what = 1;
                        message.obj = fenlei_list;
                        handler_Classify.sendMessage(message);
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

        //  图片
    private void doHandlerPhoto(int type) {
        try {
            // 保存裁剪后的图片文件
            File pictureFileDir = new File(
                    Environment.getExternalStorageDirectory(), "/maimai");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            int rand=(int)(Math.random()*100000);
            picFile = new File(pictureFileDir, rand+".jpeg");
            if (!picFile.exists()) {
                picFile.createNewFile();
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
    //  产品缩略图
    private void doHandlerPhoto_Suo(int type) {
        try {
            // 保存裁剪后的图片文件
            File pictureFileDir = new File(
                    Environment.getExternalStorageDirectory(), "/maimai");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            int rand=(int)(Math.random()*100000);
            files = new File(pictureFileDir, rand+".jpeg");
            if (!files.exists()) {
                files.createNewFile();
            }
            photoUri = Uri.fromFile(files);
            if (type == PIC_FROM＿LOCALPHOTO_SUO) {
                Intent intent = getCropImageIntent();
                startActivityForResult(intent, PIC_FROM＿LOCALPHOTO_SUO);
            } else {
                Intent cameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, PIC_FROM_CAMERA_SUO);
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
     * 启动裁剪缩略图
     */
    private void cropImageUriByTakePhoto_Suo() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        setIntentParams(intent);
        startActivityForResult(intent, PIC_FROM＿LOCALPHOTO_SUO);
    }
    /**
     * 设置公用参数
     */
    private void setIntentParams(Intent intent)
    {
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
    //  返回数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
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
            case PIC_FROM_CAMERA_SUO: // 拍照
                try
                {
                    cropImageUriByTakePhoto_Suo();
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
                        if(add_img.getVisibility() == View.GONE){
                            add_img.setVisibility(View.VISIBLE);
                            imgs.setImageBitmap(bitmap);
                        }else if(add_img2.getVisibility() == View.GONE){
                            add_img2.setVisibility(View.VISIBLE);
                            imgs2.setImageBitmap(bitmap);
                        }else if(add_img3.getVisibility() == View.GONE){
                            add_img3.setVisibility(View.VISIBLE);
                            imgs3.setImageBitmap(bitmap);
                        }
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case PIC_FROM＿LOCALPHOTO_SUO:
                try
                {
                    if (photoUri != null)
                    {
                        Bitmap bitmap = decodeUriAsBitmap(photoUri);
                        if(layout_suolueimg1.getVisibility() == View.GONE){
                            layout_suolueimg1.setVisibility(View.VISIBLE);
                            suolueimg1.setImageBitmap(bitmap);
                        }else if(layout_suolueimg2.getVisibility() == View.GONE){
                            layout_suolueimg2.setVisibility(View.VISIBLE);
                            suolueimg2.setImageBitmap(bitmap);
                        }else if(layout_suolueimg3.getVisibility() == View.GONE){
                            layout_suolueimg3.setVisibility(View.VISIBLE);
                            suolueimg3.setImageBitmap(bitmap);
                        }
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                break;
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri)
    {
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

    //  添加商品  的数据  的数据  的数据  的数据  的数据  的数据
    public void PutData(String s1,String s2){
        mProgressDialog = ProgressDialog.show(AddShangpinActivity.this, "", "正在加载...");
        String types = "/Api/addGoodsByToken";
        HashMap<String,String> map_finish = new HashMap<>();
        map_finish.put("token", spf.getString("tokn",""));
        map_finish.put("shopId",spf.getString("shopId",""));
        map_finish.put("goodsSn",goods_number.getText().toString());
        map_finish.put("goodsName",shangpinname.getText().toString());
        map_finish.put("goodsImg",s1+"");
        map_finish.put("goodsThumbs",s2+"");
        map_finish.put("marketPrice",jiage.getText().toString());
        map_finish.put("shopPrice",dianpujiage.getText().toString());
        map_finish.put("goodsStock",kucun.getText().toString());
        map_finish.put("goodsUnit",shangpindanwei.getText().toString());
        map_finish.put("isSale","1");
//        map_finish.put("goodsCatId1");
//        map_finish.put("goodsCatId2");
//        map_finish.put("goodsCatId3");
//        map_finish.put("shopCatId1");
//        map_finish.put("shopCatId2");
        map_finish.put("goodsDesc","四季的风思考的合法化速度速度");
//        map_finish.put("defaultSpec");
//        map_finish.put("selectSpec");
//        map_finish.put("sameSpec");
//        map_finish.put("gallery");
        String sguige = "{'"+types+"'}";
        HttpUtils.httputilsGet(types, map_finish, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","管罗苍添加商品返回的数据"+result);
                mProgressDialog.dismiss();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("GG","-----onError");
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
            //  分类的三级联动
    //开始
    // 弹出选择窗口
    public void addPick(){
        OptionPicker picker = new OptionPicker(AddShangpinActivity.this);
        picker.setOptions(dataset);
        picker.setOnWheelListener(new WheelPicker.OnWheelListener<int[]>() {
            @Override
            public void onSubmit(int[] result) {
                first = dataset.get(result[0]);
                province.append(first);
                Jsonjiexi("/Api/exeQuery",fenlei_list.get(result[0]).getCatId());
                fenlei_list.clear();
                dataset.clear();
                shangpinleimu_txt.setText(province);
            }
        });
        picker.showAtBottom();
    }
//    下一级城市查询
    private void Jsonjiexi(String d,String zhi){
        Log.i("GG","0000"+zhi);
        String sql = "select catId,catName from wst_goods_cats where parentId = "+zhi;
        Log.i("GG","9999");
        HashMap<String,String> map = new HashMap<>();
        Log.i("GG","8888");
        if("/Api/exeQuery".equals(d)){
            map.put("sql",sql);
        }
        HttpUtils.httputilsGet(d, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("GG","管罗苍二级的结果"+result);
                JSONObject jsonObject = null;
                String code = null;
                String message = null;
                JSONArray sheng = null;
                try {
                    jsonObject = new JSONObject(result);
                    code = jsonObject.getString("code");
                    message = jsonObject.getString("message");
                    sheng = jsonObject.getJSONArray("data");
                    Log.i("GG","^^^^^");
                    if ("200".equals(code)){
                        if(sheng.length() != 0){
                        Log.i("GG","if---"+province);
                        fenlei_list.clear();
                        for (int i = 0; i < sheng.length(); i++) {
                            JSONObject shengs = null;
                            try {
                                shengs = sheng.getJSONObject(i);
                                String areaId = shengs.getString("catId");
//                            String parentId = shengs.getString("parentId");
                                String areaName = shengs.getString("catName");
                                Log.i("GG","aaaaa"+areaName);
                                fenlei_list.add(new Goods_classify(areaName,areaId));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        dataset.clear();
                        for (int i = 0; i < sheng.length(); i++) {
                            Log.i("GG","ID:"+fenlei_list.get(i).getCatId()+"-----名称："+fenlei_list.get(i).getClassify_name());
                            dataset.add(fenlei_list.get(i).getClassify_name());
                        }
                        addPick();

                    } else {
                            choosename = String.valueOf(province);
                            Log.i("GG", "choosename-----" + choosename);
                            province.setLength(0);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
    //结束
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
    //  七牛
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
                            Log.i("GG", "111"+key + " " + info + " " + response);
                                sFile = key;
                            UploadManager uploadManager = new UploadManager();
                            uploadManager.put(picFile2, suiJiName(), qiNiuUpToken(), new UpCompletionHandler() {
                                @Override
                                public void complete(String key, ResponseInfo info, JSONObject response) {
                                    Log.i("GG", "2222"+key + " " + info + " " + response);
                                    sFiles = key;
                                    PutData(sFile,sFiles);
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
