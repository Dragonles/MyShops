package com.myshops.shops.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.mob.tools.gui.ViewPagerAdapter;
import com.myshops.shops.adapter.OderAdpter;
import com.myshops.shops.bean.Order;
import com.myshops.shops.bean.Orders;
import com.myshops.shops.myshops.LoginActivity;
import com.myshops.shops.myshops.MainActivity;
import com.myshops.shops.myshops.OrderFormActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.untils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.xiaopan.psts.PagerSlidingTabStrip;

/**
 * 卖家 —————  订单框架
 * */
//@ContentView(R.layout.fragment_ding_dan)
public class DingDanFragment extends Fragment {

    //    @ViewInject(R.id.ddtabs)
//    private PagerSlidingTabStrip tabs;
//    @ViewInject(R.id.viewpager_dd)
//    private ViewPager pager;
//    private DDMyPagerAdapter adapter;
    int i = 1;


    ListView all_lv,daifa_lv,daishou_lv,daiping_lv, tuikuanz_lv, tuikuan_lv;
    List<Order> list = new ArrayList<>();
    private String GoodsThums; //商品缩略图
    private String GoodsName; //商品名称
    private String GoodsNums; //商品数量
    private String Username;  //客户名称
    private String TotalMoney; //购买金额
    private String OrderStatus;  //付款状态
    private String CreateTime; //订单创建时间
    private String IsRefund; //是否退款   未退款：0   已退款：1
    //    @ViewInject(R.id.slidingTabStrip)
//    private PagerSlidingTabStrip pagerSlidingTabStrip1;
//    @ViewInject(R.id.viewPager)
//    private ViewPager viewPager1;
    private String tokens;  //用户token

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ding_dan, container, false);

//        pd = ProgressDialog.show(getActivity(),"","正在加载中");

        PagerSlidingTabStrip pagerSlidingTabStrip1 = (PagerSlidingTabStrip) v.findViewById(R.id.slidingTabStrip);
        ViewPager viewPager1 = (ViewPager) v.findViewById(R.id.viewPager);
        init(0, pagerSlidingTabStrip1, viewPager1);

        //listview 点击事件
        all_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //调用具体方法
                intents(position);
            }
        });
        daifa_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //调用具体方法
                intents(position);
            }
        });
        daishou_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //调用具体方法
                intents(position);
            }
        });
        daiping_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //调用具体方法
                intents(position);
            }
        });
        tuikuanz_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //调用具体方法
                intents(position);
            }
        });
        tuikuan_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //调用具体方法
                intents(position);
            }
        });



        return v;
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        pd = ProgressDialog.show(getActivity(),"","正在加载中");
//        PagerSlidingTabStrip pagerSlidingTabStrip1 = (PagerSlidingTabStrip) findViewById(R.id.slidingTabStrip);
//        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager);
//
//        init(0, pagerSlidingTabStrip1, viewPager1);

//        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////                Log.i("onpagepos","onPageScrolled"+position);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                i = position;
//                Log.i("onpagepos","onPageSelected"+position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
////                Log.i("onpagepos","onPageScrollStateChanged"+state);
//            }
//        });
//        pager.setOffscreenPageLimit(1);
//        adapter = new DDMyPagerAdapter(getActivity().getSupportFragmentManager());
//        pager.setAdapter(adapter);
//        tabs.setViewPager(pager);

//    }

    /**
     * 点击订单列表进入详情页
     * */
    public void intents(int position) {

        IsRefund = list.get(position).getIsRefund();//是否退款
        CreateTime = list.get(position).getCreateTime();//订单创建时间
        GoodsThums = list.get(position).getGoodsThums();//商品缩略图
        GoodsName = list.get(position).getGoodsName();//商品名称
        Username = list.get(position).getUsername();//客户名称
        GoodsNums = list.get(position).getGoodsNums();//商品数量
        TotalMoney = list.get(position).getTotalMoney();//购买金额
        OrderStatus = list.get(position).getOrderStatus();//商品状态
        //Intent 传递数据
        Intent intent = new Intent(getActivity(), OrderFormActivity.class);
        intent.putExtra("inGoodsThums",GoodsThums); //商品缩略图
        intent.putExtra("inGoodsName",GoodsName); //商品名称
        intent.putExtra("inGoodsNums",GoodsNums); //商品数量
        intent.putExtra("inUsername",Username); //客户名称
        intent.putExtra("inTotalMoney",TotalMoney); //购买金额
        intent.putExtra("inOrderStatus",OrderStatus); //商品状态
        intent.putExtra("inCreateTime",CreateTime); //订单创建时间
        intent.putExtra("inIsRefund",IsRefund);  //是否退款
        startActivity(intent);
        Log.i("intentstring","商品缩略图"+GoodsThums+"商品名称"+GoodsName+"商品数量"+GoodsNums+"客户名称"+Username+"购买金额"+TotalMoney+"付款状态"+OrderStatus);
    }


    private void init(int index, PagerSlidingTabStrip pagerSlidingTabStrip, ViewPager viewPager) {
        int length = pagerSlidingTabStrip.getTabCount();
        final List<View> views = new ArrayList<View>(length);
        View all = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ddsuperawesomecardragment, null);
        View daifa = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ddsuperawesomecardragmenta, null);
        View daishou = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ddsuperawesomecardragment, null);
        View daiping = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ddsuperawesomecardragment, null);
        View tuikuanz = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ddsuperawesomecardragment, null);
        View tuikuan = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ddsuperawesomecardragment, null);
        views.add(all);
        views.add(daifa);
        views.add(daishou);
        views.add(daiping);
        views.add(tuikuanz);
        views.add(tuikuan);
        all_lv = (ListView) all.findViewById(R.id.list_view);
        daifa_lv = (ListView) all.findViewById(R.id.list_view);
        daishou_lv = (ListView) all.findViewById(R.id.list_view);
        daiping_lv = (ListView) all.findViewById(R.id.list_view);
        tuikuanz_lv = (ListView) all.findViewById(R.id.list_view);
        tuikuan_lv = (ListView) all.findViewById(R.id.list_view);

        if (LoginActivity.token == null) {
            SharedPreferences user = getActivity().getSharedPreferences("user_info",0);
            tokens = user.getString("token","");
            Log.i("SharedPreferencesToken","SharedPreferences 本地保存"+tokens);
        } else {
            tokens = LoginActivity.token;
            Log.i("SharedPreferencesToken","登录获得："+tokens);
        }

        String types = "/AllOrders/allorder";
        //String sql = "select * from wst_orders  join wst_order_goods  on wst_orders.orderId=wst_order_goods.orderId where userId in(select userId from wst_user_token where token='"+LoginActivity.token+"')";
        HashMap<String, String> map = new HashMap<>();
        map.put("token", tokens);
        Log.i("token",tokens);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess" + s.toString());
                Log.i("sss",s.toString());
                //解析JSON数据
                try {
                    JSONObject data = new JSONObject(s);
                    String code = data.get("code").toString();
                    if ("200".equals(code)) {
                        String nessage = data.get("message").toString();
                        JSONArray datas = (JSONArray) data.get("data");
                        List<Order> usersList = new ArrayList<Order>();
                        for (int i = 0; i < 10; i++) {
                            JSONObject info = datas.getJSONObject(i);
                            Order order = new Order();
                            //是否退款
                            order.setIsRefund(info.get("isRefund").toString());
                            //订单创建时间
                            order.setCreateTime(info.get("createTime").toString());
                            //商品缩略图
                            order.setGoodsThums(info.get("goodsThums").toString());
                            Log.i("img", info.get("goodsThums").toString());
                            //商品名称
                            order.setGoodsName(info.get("goodsName").toString());
                            //商品数量
                            order.setGoodsNums(info.get("goodsNums").toString());
                            //客户名称
                            order.setUsername(info.get("userName").toString());
                            //购买金额
                            order.setTotalMoney(info.get("totalMoney").toString());
//                        order.setGoodsThums(info.get("goodsThums").toString());
                            //付款状态
                            String os = info.get("orderStatus").toString();
                            Log.i("ostatus", "付款状态" + os);
                            if ("-1".equals(os)) {
                                order.setOrderStatus("待发货");
                            } else if ("0".equals(os)) {
                                order.setOrderStatus("已发货");
                            } else if ("1".equals(os)) {
                                order.setOrderStatus("已完成");
                            } else if ("2".equals(os)) {
                                order.setOrderStatus("退款中");
                            } else if ("3".equals(os)) {
                                order.setOrderStatus("已退款");
                            }

//                            Log.i("intentstring", "****商品缩略图" + GoodsThums + "商品名称" + GoodsName + "商品名称" + GoodsNums + "客户名称" + Username + "购买金额" + TotalMoney + "付款状态" + OrderStatus);

                            Log.i("orderstatus_ss", info.get("orderStatus").toString());
//                            Log.i("Logpos", "http: "+positions+" os:" + os);
                            list.add(order);
//                            if (positions == 1) {
//                                Log.i("Logpos","position = 1");
//                                lists.add(order);
//                            } else if (positions == 2 && "-1".equals(os)){
//                                Log.i("Logpos","position = 2 os = -1");
//                                lists.add(order);
//                            } else if (positions == 3 && "0".equals(os)){
//                                Log.i("Logpos","position = 2 os = -1");
//                                lists.add(order);
//                            } else if (positions == 4 && "1".equals(os)){
//                                Log.i("Logpos","position = 2 os = -1");
//                                lists.add(order);
//                            } else if (positions == 5 && "2".equals(os)){
//                                Log.i("Logpos","position = 2 os = -1");
//                                lists.add(order);
//                            } else if (positions == 6 && "3".equals(os)){
//                                Log.i("Logpos","position = 2 os = -1");
//                                lists.add(order);
//                            }

                        }
//                        a += 10;
//                        Log.i("listssize",lists.size()+"");
                        Log.i("ddadapter","list的长度"+list.size());
                        all_lv.setAdapter(new OderAdpter(getActivity(), list));
                        daifa_lv.setAdapter(new OderAdpter(getActivity(), list));
                        daishou_lv.setAdapter(new OderAdpter(getActivity(), list));
                        daiping_lv.setAdapter(new OderAdpter(getActivity(), list));
                        tuikuanz_lv.setAdapter(new OderAdpter(getActivity(), list));
                        tuikuan_lv.setAdapter(new OderAdpter(getActivity(), list));

//                        mHasLoadedOnce = true;
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError" + throwable.toString());
                Log.i("sser",throwable.getMessage());
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("CancelledException",e.toString());
            }

            @Override
            public void onFinished() {

            }
        });

        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setCurrentItem(index < length ? index : length);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private List<View> viewList;

        public ViewPagerAdapter(List<View> viewList){
            Log.i("ddadapter","ViewPagerAdapter");
            setViewList(viewList);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("ddadapter","destroyItem");
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i("ddadapter",position+"adapter");
            container.addView(viewList.get(position), 0);
            return viewList.get(position);
        }

        @Override
        public int getCount() {
            Log.i("ddadapter","getCount");
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            Log.i("ddadapter","isViewFromObject");
            return arg0 == arg1;
        }

        public List<View> getViewList() {
            Log.i("ddadapter","getViewList");
            return viewList;
        }

        public void setViewList(List<View> viewList) {
            Log.i("ddadapter","getViewList");
            this.viewList = viewList;
        }
    }



//
//    @Event(value = R.id.btn_daifahuo)
//    private void dafahuoClick(View v){
//        String types = "/AllOrders/addgoodtest";
//        HashMap<String, String> map = new HashMap<>();
//        map.put("token", LoginActivity.token);
//        HttpUtils.httputilsPost(types, map, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                System.out.println("onSuccess" + s.toString());
//                Log.i("sss",s.toString());
//                try {
//                    JSONObject data = new JSONObject(s);
//                    String code = data.get("code").toString();
//                    String nessage = data.get("message").toString();
//                    JSONArray datas = (JSONArray) data.get("data");
//                    List<Order> usersList = new ArrayList<Order>();
//                    for (int i = 0; i < datas.length(); i++) {
//                        JSONObject info = datas.getJSONObject(i);
//                        Order order = new Order();
//                        order.setGoodsThums(info.get("goodsThums").toString());
//                        Log.i("img",info.get("goodsThums").toString());
//                        order.setGoodsName(info.get("goodsName").toString());
//                        order.setGoodsNums(info.get("goodsNums").toString());
//                        order.setUsername(info.get("userName").toString());
//                        order.setTotalMoney(info.get("totalMoney").toString());
//                       // order.setOrderStatus(Integer.parseInt(info.get("orderStatus").toString()));
//                        lists.add(order);
//                    }
//                    orderadpter.notifyDataSetChanged();
//                    //listView.setAdapter(new OderAdpter(getActivity(), lists));
//
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//                System.out.println("onError" + throwable.toString());
//                Log.i("sser",throwable.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException e) {
//                Log.i("CancelledException",e.toString());
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }

}