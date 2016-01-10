package com.myshops.shops.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.myshops.shops.myshops.R;

//@ContentView(R.layout.fragment_my_goods)
public class GoodsFragment extends BaseFragment implements View.OnClickListener {
    private FragmentManager mFragmentManager;
    RadioButton rb_chushou,rb_xiajia,rb_fenlei;
//    @ViewInject(R.id.radiobutton_chushou)
//    private RadioButton rb_chushou;
//    @ViewInject(R.id.radiobutton_xiajia)
//    private RadioButton rb_xiajia;
//    @ViewInject(R.id.radiobutton_fenlei)
//    private RadioButton rb_fenlei;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_goods, container, false);
        rb_chushou = (RadioButton) v.findViewById(R.id.radiobutton_chushou);
        rb_xiajia = (RadioButton) v.findViewById(R.id.radiobutton_xiajia);
        rb_fenlei = (RadioButton) v.findViewById(R.id.radiobutton_fenlei);
        //第一个默认选中的
        rb_chushou.setChecked(true);
        rb_chushou.setBackgroundResource(R.color.white);
        rb_chushou.setTextColor(getResources().getColor(R.color.style_color));
        //添加监听事件
        rb_chushou.setOnClickListener(this);
        rb_xiajia.setOnClickListener(this);
        rb_fenlei.setOnClickListener(this);
        // 获取
        mFragmentManager = getChildFragmentManager();
        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            Goods_chushou_Fragment gf = new Goods_chushou_Fragment();
            fragmentTransaction.add(R.id.framelayout_dingdan,gf,"chushou");
            fragmentTransaction.commit();
        }
        return v;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mFragmentManager.findFragmentByTag("chushou")!= null){
            fragmentTransaction.hide(mFragmentManager.findFragmentByTag("chushou"));
        }
        if (mFragmentManager.findFragmentByTag("xiajia")!= null){
            fragmentTransaction.hide(mFragmentManager.findFragmentByTag("xiajia"));
        }
        if (mFragmentManager.findFragmentByTag("fenlei")!= null){
            fragmentTransaction.hide(mFragmentManager.findFragmentByTag("fenlei"));
        }
        int id = v.getId();
        if(id == R.id.radiobutton_chushou){
            if (mFragmentManager.findFragmentByTag("chushou") != null) {
                fragmentTransaction.show(mFragmentManager.findFragmentByTag("chushou"));
                rb_chushou.setBackgroundResource(R.color.white);
                rb_xiajia.setBackground(null);
                rb_fenlei.setBackground(null);
                rb_chushou.setTextColor(getResources().getColor(R.color.style_color));
                rb_xiajia.setTextColor(getResources().getColor(R.color.white));
                rb_fenlei.setTextColor(getResources().getColor(R.color.white));
            } else {
                Goods_chushou_Fragment gf = new Goods_chushou_Fragment();
                fragmentTransaction.add(R.id.framelayout_dingdan, gf, "chushou");
                rb_xiajia.setBackground(null);
                rb_fenlei.setBackground(null);
                rb_chushou.setTextColor(getResources().getColor(R.color.style_color));
                rb_xiajia.setTextColor(getResources().getColor(R.color.white));
                rb_fenlei.setTextColor(getResources().getColor(R.color.white));
            }
        }else if(id == R.id.radiobutton_xiajia){
            if (mFragmentManager.findFragmentByTag("xiajia") != null) {
                fragmentTransaction.show(mFragmentManager.findFragmentByTag("xiajia"));
                rb_chushou.setBackground(null);
                rb_xiajia.setBackgroundResource(R.color.white);
                rb_fenlei.setBackground(null);
                rb_chushou.setTextColor(getResources().getColor(R.color.white));
                rb_xiajia.setTextColor(getResources().getColor(R.color.style_color));
                rb_fenlei.setTextColor(getResources().getColor(R.color.white));
            } else {
                Goods_xiajia_Fragment xf = new Goods_xiajia_Fragment();
                fragmentTransaction.add(R.id.framelayout_dingdan, xf, "xiajia");
                rb_chushou.setBackground(null);
                rb_xiajia.setBackgroundResource(R.color.white);
                rb_fenlei.setBackground(null);
                rb_chushou.setTextColor(getResources().getColor(R.color.white));
                rb_xiajia.setTextColor(getResources().getColor(R.color.style_color));
                rb_fenlei.setTextColor(getResources().getColor(R.color.white));
            }
        }else if(id == R.id.radiobutton_fenlei){
            if (mFragmentManager.findFragmentByTag("fenlei") != null) {
                fragmentTransaction.show(mFragmentManager.findFragmentByTag("fenlei"));
                rb_chushou.setBackground(null);
                rb_xiajia.setBackground(null);
                rb_fenlei.setBackgroundResource(R.color.white);
                rb_chushou.setTextColor(getResources().getColor(R.color.white));
                rb_xiajia.setTextColor(getResources().getColor(R.color.white));
                rb_fenlei.setTextColor(getResources().getColor(R.color.style_color));
            } else {
                Goods_fenlei_Fragment ff = new Goods_fenlei_Fragment();
                fragmentTransaction.add(R.id.framelayout_dingdan, ff, "fenlei");
                rb_chushou.setBackground(null);
                rb_xiajia.setBackground(null);
                rb_fenlei.setBackgroundResource(R.color.white);
                rb_chushou.setTextColor(getResources().getColor(R.color.white));
                rb_xiajia.setTextColor(getResources().getColor(R.color.white));
                rb_fenlei.setTextColor(getResources().getColor(R.color.style_color));
            }
        }
        fragmentTransaction.commit();
    }
}
