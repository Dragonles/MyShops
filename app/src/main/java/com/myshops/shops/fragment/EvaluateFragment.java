package com.myshops.shops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.myshops.shops.myshops.R;


import java.util.ArrayList;
import java.util.List;

/**
 * 卖家 ———— 评价管理框架
 * */
public class EvaluateFragment extends Fragment {

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private List<String> mTITLES = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_evaluate, container, false);
        View v = inflater.inflate(R.layout.fragment_evaluate, container, false);

        tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        pager = (ViewPager)v. findViewById(R.id.viewpager);

        adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

        return v;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

//        private final String[] TITLES = new String[mTITLES.size()];

        private final String[] TITLES = { "全部评价","好评" ,"中评", "差评"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return mTITLES.get(position);
            return TITLES[position];
        }

        @Override
        public int getCount() {
//            return mTITLES.size();
            return TITLES.length;

        }

        @Override
        public Fragment getItem(int position) {
            Log.i("posid","Home_Fragment："+position);
            position++;
//            pos = position;
            return SuperAwesomeCardFragment.newInstance(position);
        }


    }
}
