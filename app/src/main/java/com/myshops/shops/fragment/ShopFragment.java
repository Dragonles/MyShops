package com.myshops.shops.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshops.shops.bean.CircleImageView;
import com.myshops.shops.myshops.LoginActivity;
import com.myshops.shops.myshops.R;
import com.myshops.shops.myshops.ShopInfoActivity;
import com.myshops.shops.untils.HttpUtils;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

public class ShopFragment extends Fragment {

    ImageButton mgoinfo;
    private ImageView iv_userIcon;
    public static String uid;
    public static String userPhoto;
    public static String userName;
    public static String userPhone;
    private TextView tv_shop_username;
    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop,container,false);
        Toolbar toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        mgoinfo=(ImageButton)v.findViewById(R.id.shop_info);
        iv_userIcon = (ImageView) v.findViewById(R.id.iv_userIcon);
        tv_shop_username = (TextView) v.findViewById(R.id.tv_shop_username);


        String t = LoginActivity.token;
        String sqll = "select * from wst_user_token where token = '" + t + "'";
        String types = "/Api/exeQuery";
        HashMap<String, String> map = new HashMap<>();
        map.put("sql", sqll);
        HttpUtils.httputilsGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                Log.i("onSuccess", s.toString());

                try {

                    JSONObject jsonobj = new JSONObject(s);
                    String code = jsonobj.getString("code");
                    String message = jsonobj.getString("message");
                    JSONArray data = jsonobj.getJSONArray("data");
                    JSONObject info = data.getJSONObject(0);
                    Log.i("aaa", "aaa");
                    String userId = info.getString("userId");
                    Log.i("onSuccess", "userId = " + userId);
                    uid = userId;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                String sql = "select userPhoto, userName, userPhone from wst_users where userId = '" + uid + "'";
                String type = "/Api/exeQuery";
                HashMap<String, String> maps = new HashMap<>();
                maps.put("sql", sql);
                HttpUtils.httputilsGet(type, maps, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        Log.i("userphoto", s.toString());

                        try {
                            JSONObject jsonobj = new JSONObject(s);
                            String code = jsonobj.getString("code");
                            String message = jsonobj.getString("message");
                            JSONArray data = jsonobj.getJSONArray("data");
                            JSONObject info = data.getJSONObject(0);
                            userPhoto = info.getString("userPhoto");
                            userName = info.getString("userName");
                            userPhone = info.getString("userPhone");
                            Log.i("userPhoto",userPhoto + " " + userName + " 1");
                            tv_shop_username.setText(userName);
                            Log.i("userPhoto",userPhoto + " " + userName + " 2");
                            iv_userIcon.setBackgroundResource(0);
                            Picasso.with(getContext()).load(userPhoto).into(iv_userIcon);

                        }catch (Exception e){
                        
                        }

//                        Log.i("userPhoto",userPhoto + " " + userName + " 1");
//                        tv_shop_username.setText(userName);
//                        //iv_userIcon.setImageBitmap(BitmapFactory.decodeFile(userPhoto));
//                        Log.i("userPhoto",userPhoto + " " + userName + " 2");
//                        iv_userIcon.setImageURI(Uri.parse(userPhoto));
//                        //Picasso.with(getContext()).load(userPhoto).into(iv_userIcon);
//                        Log.i("userPhoto",userPhoto + " " + userName + " ''3");


                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Log.i("onError", throwable.toString());
                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("onError", throwable.toString());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });


        mgoinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), ShopInfoActivity.class);
                startActivity(intent);
            }
        });
       // Activity.setSupportActionBar(toolbar);
        return v;
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getContext());       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getContext());
    }


}
