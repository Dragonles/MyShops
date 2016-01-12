package com.myshops.shops.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myshops.shops.adapter.OderAdpter;
import com.myshops.shops.bean.Order;
import com.myshops.shops.myshops.LoginActivity;
import com.myshops.shops.myshops.OrderFormActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.untils.HttpUtils;
import com.shizhefei.fragment.LazyFragment;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MoreFragment extends LazyFragment {

	private String GoodsThums; //商品缩略图
	private String GoodsName; //商品名称
	private String GoodsNums; //商品数量
	private String Username;  //客户名称
	private String TotalMoney; //购买金额
	private String OrderStatus;  //付款状态
	private String CreateTime; //订单创建时间
	private String IsRefund; //是否退款   未退款：0   已退款：1
	private String OrderId; //订单ID


	private ProgressBar progressBar;
	private TextView textView;
	private int tabIndex;
	private ListView listview;
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private String tokens;  //用户token
	List<Order> list = new ArrayList<>();

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain_item);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		progressBar = (ProgressBar) findViewById(R.id.fragment_mainTab_item_progressBar);
		listview = (ListView) findViewById(R.id.listview);
//		textView = (TextView) findViewById(R.id.fragment_mainTab_item_textView);
//		textView.setText("界面" + " " + tabIndex + " 加载完毕");
		//获取token
		chatoken();
		//listview 点击事件
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				intents(position);
			}
		});
		handler.sendEmptyMessageDelayed(1, 2000);
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
		handler.removeMessages(1);
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			progressBar.setVisibility(View.GONE);
			listview.setVisibility(View.VISIBLE);
		}
	};

	/**
	 * 获取token
	 * */
	public void chatoken() {
		if (LoginActivity.token == null) {
			SharedPreferences user = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
			tokens = user.getString("token","");
			Log.i("SharedPreferencesToken","SharedPreferences 本地保存"+tokens);
		} else {
			tokens = LoginActivity.token;
			Log.i("SharedPreferencesToken","登录获得："+tokens);
		}
		chashuju(tabIndex);
	}
	/**
	 * 获取数据
	 * */
	public void chashuju(final int pos) {
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
						for (int i = 0; i < datas.length(); i++) {
							JSONObject info = datas.getJSONObject(i);
							Order order = new Order();
//							//是否退款
//							order.setIsRefund(info.get("isRefund").toString());
//							//订单创建时间
//							order.setCreateTime(info.get("createTime").toString());
//							//商品缩略图
//							order.setGoodsThums(info.get("goodsThums").toString());
//							Log.i("img", info.get("goodsThums").toString());
//							//商品名称
//							order.setGoodsName(info.get("goodsName").toString());
//							//商品数量
//							order.setGoodsNums(info.get("goodsNums").toString());
//							//客户名称
//							order.setUsername(info.get("userName").toString());
//							//购买金额
//							order.setTotalMoney(info.get("totalMoney").toString());
//                        order.setGoodsThums(info.get("goodsThums").toString());
							//订单ID
							String orderid = info.get("orderId").toString();
							//付款状态
							String os = info.get("orderStatus").toString();
							String ors = null;
							int aa = 0;
							Log.i("ostatus", "付款状态" + os);
							if ("0".equals(os)) {
								ors = "待发货";
//								order.setOrderStatus("待发货");
							} else if ("3".equals(os) || "1".equals(os) || "2".equals(os)) {
								ors = "已发货";
								aa = 1;
//								order.setOrderStatus("已发货");
							} else if ("4".equals(os)) {
								ors = "已完成";
//								order.setOrderStatus("已完成");
							} else if ("-6".equals(os)) {
								ors = "退款中";
//								order.setOrderStatus("退款中");
							} else if ("-4".equals(os)) {
								ors = "已退款";
//								order.setOrderStatus("已退款");
							}
							if (pos == 0) {
								list.add(new Order(info.get("userName").toString(),info.get("goodsName").toString(),info.get("goodsNums").toString(),info.get("totalMoney").toString(),ors,info.get("goodsThums").toString(),info.get("createTime").toString(),info.get("isRefund").toString(),orderid));
							} else if (pos == 1 && "0".equals(os) ) {
								//待发货
								list.add(new Order(info.get("userName").toString(),info.get("goodsName").toString(),info.get("goodsNums").toString(),info.get("totalMoney").toString(),ors,info.get("goodsThums").toString(),info.get("createTime").toString(),info.get("isRefund").toString(),orderid));
							} else if (pos == 2 && aa == 1 ) {
								//已发货
								list.add(new Order(info.get("userName").toString(),info.get("goodsName").toString(),info.get("goodsNums").toString(),info.get("totalMoney").toString(),ors,info.get("goodsThums").toString(),info.get("createTime").toString(),info.get("isRefund").toString(),orderid));
							} else if (pos == 3 && "4".equals(os) ) {
								//已完成
								list.add(new Order(info.get("userName").toString(),info.get("goodsName").toString(),info.get("goodsNums").toString(),info.get("totalMoney").toString(),ors,info.get("goodsThums").toString(),info.get("createTime").toString(),info.get("isRefund").toString(),orderid));
							} else if (pos == 4 && "-6".equals(os) ) {
								//退款中
								list.add(new Order(info.get("userName").toString(),info.get("goodsName").toString(),info.get("goodsNums").toString(),info.get("totalMoney").toString(),ors,info.get("goodsThums").toString(),info.get("createTime").toString(),info.get("isRefund").toString(),orderid));
							} else if (pos == 5 && "-4".equals(os) ) {
								//已退款
								list.add(new Order(info.get("userName").toString(),info.get("goodsName").toString(),info.get("goodsNums").toString(),info.get("totalMoney").toString(),ors,info.get("goodsThums").toString(),info.get("createTime").toString(),info.get("isRefund").toString(),orderid));
							}

//                            Log.i("intentstring", "****商品缩略图" + GoodsThums + "商品名称" + GoodsName + "商品名称" + GoodsNums + "客户名称" + Username + "购买金额" + TotalMoney + "付款状态" + OrderStatus);

							Log.i("orderstatus_ss", info.get("orderStatus").toString());
//                            Log.i("Logpos", "http: "+positions+" os:" + os);
//                            list.add(order);
//							if (pos == 0) {  //全部订单
//								Log.i("Logpos","position = 1");
//								list.add(order);
//							} else if (pos == 1 && "0".equals(os)){  //待发货
//								Log.i("Logpos","position = 2 os = -1");
//								list.add(order);
//							} else if (pos == 2 && "3".equals(os)){  //已发货
//								Log.i("Logpos","position = 2 os = -1");
//								list.add(order);
//							} else if (pos == 3 && "4".equals(os)){  //已完成
//								Log.i("Logpos","position = 2 os = -1");
//								list.add(order);
//							} else if (pos == 4 && "-6".equals(os)){  //退款中
//								Log.i("Logpos","position = 2 os = -1");
//								list.add(order);
//							} else if (pos == 5 && "-4".equals(os)){  //已退款
//								Log.i("Logpos","position = 2 os = -1");
//								list.add(order);
//							}

						}
						listview.setAdapter(new OderAdpter(getActivity(), list));

//                        a += 10;
//                        Log.i("listssize",lists.size()+"");
						Log.i("ddadapter","list的长度"+list.size());
//                        all_lv.setAdapter(new OderAdpter(getActivity(), list));
//                        daifa_lv.setAdapter(new OderAdpter(getActivity(), list));
//                        daishou_lv.setAdapter(new OderAdpter(getActivity(), list));
//                        daiping_lv.setAdapter(new OderAdpter(getActivity(), list));
//                        tuikuanz_lv.setAdapter(new OderAdpter(getActivity(), list));
//                        tuikuan_lv.setAdapter(new OderAdpter(getActivity(), list));

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
	}
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
		OrderId = list.get(position).getOrderId(); //  订单ID
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
		intent.putExtra("inorderId",OrderId); //商品ID
		startActivity(intent);
		Log.i("intentstring","商品缩略图"+GoodsThums+"商品名称"+GoodsName+"商品数量"+GoodsNums+"客户名称"+Username+"购买金额"+TotalMoney+"付款状态"+OrderStatus);
	}

}
