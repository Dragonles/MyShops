package com.myshops.shops.myshops;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myshops.shops.myshops.R;
import com.myshops.shops.untils.HttpUtils;
import com.myshops.shops.untils.ToastUtil;

import org.xutils.common.Callback;

import java.util.HashMap;

public class OrderFormActivity extends AppCompatActivity {
    private String GoodsThums; //商品缩略图
    private String GoodsName; //商品名称
    private String GoodsNums; //商品数量
    private String Username;  //客户名称
    private String TotalMoney; //购买金额
    private String OrderStatus;  //付款状态
    private String CreateTime; //订单创建时间
    private String IsRefund; //是否退款   未退款：0   已退款：1
    private String OrderId;  //订单ID

    private TextView tv_orderform_username,tv_orderform_goodsname,tv_orderform_howmany,
            tv_orderform_data,tv_orderform_howmuch,tv_orderform_paystate,
            tv_orderfrom_goodsstate,tv_orderform_isreimburse;
    private Button btn_shopinfo_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        tv_orderform_username = (TextView) findViewById(R.id.tv_orderform_username);
        tv_orderform_goodsname = (TextView) findViewById(R.id.tv_orderform_goodsname);
        tv_orderform_howmany = (TextView) findViewById(R.id.tv_orderform_howmany);
        tv_orderform_data = (TextView) findViewById(R.id.tv_orderform_data);
        tv_orderform_howmuch = (TextView) findViewById(R.id.tv_orderform_howmuch);
        tv_orderform_paystate = (TextView) findViewById(R.id.tv_orderform_paystate);
        tv_orderfrom_goodsstate = (TextView) findViewById(R.id.tv_orderfrom_goodsstate);
        tv_orderform_isreimburse = (TextView) findViewById(R.id.tv_orderform_isreimburse);
        btn_shopinfo_exit = (Button) findViewById(R.id.btn_shopinfo_exit);

        btn_shopinfo_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiugai();
            }
        });

        intentc();
    }
    /**
     * 接收数据
     * */
    public void intentc() {
        Intent intent = getIntent();
        GoodsThums = intent.getStringExtra("inGoodsThums");//商品缩略图
        GoodsName = intent.getStringExtra("inGoodsName");//商品名称
        GoodsNums = intent.getStringExtra("inGoodsNums");//商品数量
        Username = intent.getStringExtra("inUsername");//客户名称
        TotalMoney = intent.getStringExtra("inTotalMoney");//购买金额
        OrderStatus = intent.getStringExtra("inOrderStatus");//商品状态
        CreateTime = intent.getStringExtra("inCreateTime");//订单创建时间
        IsRefund = intent.getStringExtra("inIsRefund");//是否退款
        OrderId = intent.getStringExtra("inorderId");//订单ID
//        OrderStatus = intent.getStringExtra("OrderStatus"); //付款状态

        tv_orderform_username.setText(Username); //客户名称
        tv_orderform_goodsname.setText(GoodsName); //商品名称
        tv_orderform_howmany.setText(GoodsNums);  //商品数量
        tv_orderform_data.setText(CreateTime);  //时间
        tv_orderform_howmuch.setText(TotalMoney);  //金额
        tv_orderfrom_goodsstate.setText(OrderStatus); //商品状态
        tv_orderform_isreimburse.setText(IsRefund); //是否退款、
        Log.d("actOrderStatus", "OrderStatus: "+OrderStatus);
        if ("待发货".equals(OrderStatus)) {
            btn_shopinfo_exit.setText("点击发货");
        } else if ("已发货".equals(OrderStatus)) {
            btn_shopinfo_exit.setText("已发货");
        }  else if ("退款中".equals(OrderStatus)) {
            btn_shopinfo_exit.setText("点击退款");
        } else if ("已退款".equals(OrderStatus)) {
            btn_shopinfo_exit.setText("退款成功");
        }
    }
    /**
     * 修改数据
     * */
    public void xiugai() {
        if ("点击发货".equals(OrderStatus)) {
            alertdialog("发货", 3);
//            btn_shopinfo_exit.setText("已发货");
//            upDateHttp(3,OrderId);
        } else if ("已发货".equals(OrderStatus)) {
            ToastUtil.ToastLongs(getApplicationContext(),"此订单已发货");
//            btn_shopinfo_exit.setText("已发货");
//            upDateHttp(3,OrderId);
        } else if ("已完成".equals(OrderStatus)) {
            ToastUtil.ToastLongs(getApplicationContext(),"此订单已完成");
            btn_shopinfo_exit.setText("此订单已完成");
//            upDateHttp(3,OrderId);
        } else if ("点击退款".equals(OrderStatus)) {

            alertdialog("退款", -4);
        } else if ("退款成功".equals(OrderStatus)) {
            ToastUtil.ToastLongs(getApplicationContext(),"此订单已退款");
            btn_shopinfo_exit.setText("此订单已退款");
//            upDateHttp(3,OrderId);
        }
    }

    /**
     * 选择框
     * */
    public void alertdialog(final String mesg, final int os) {
        final AlertDialog.Builder ad = new AlertDialog.Builder(OrderFormActivity.this);
        ad.setTitle("是否"+mesg);
        ad.setMessage("是否确定"+mesg);
        ad.setCancelable(true);
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtil.ToastLongs(getApplicationContext(),"此订单已"+mesg);
                btn_shopinfo_exit.setText("已"+mesg);
                upDateHttp(os,OrderId);
            }
        });
        ad.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = ad.create();
        alert.show();
    }

    /**
     * 执行http
     * */
    public void upDateHttp(int os, String id) {
        String types = "/Api/exeQuery";
        String spls = "update wst_orders set orderStatus = "+os+" where orderId = "+id;
        HashMap<String, String> map = new HashMap<>();
        map.put("sqls", spls);
        HttpUtils.httputilsGet(types, map, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ToastUtil.ToastLongs(getApplicationContext(), "修改成功");
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    /**
     * back
     * */
    public void goBacksClick(View v) {
        OrderFormActivity.this.finish();
    }
}
