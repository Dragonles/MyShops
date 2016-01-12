package com.myshops.shops.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.myshops.shops.fragment.MoreFragment;

import com.myshops.shops.myshops.R;
import com.shizhefei.view.indicator.FragmentListPageAdapter;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
/**
 * 我的订单
 * */
public class DingDansFragment extends Fragment {
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	private String[] names = { "全部订单","待发货","已发货","已完成","退款中","已退款"};
	private ScrollIndicatorView indicator;
	private int size = 6;


	@Override
	public View  onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
		View v =inflater.inflate(R.layout.activity_moretab, container, false);
//		setContentView(R.layout.activity_moretab);
		ViewPager viewPager = (ViewPager) v.findViewById(R.id.moretab_viewPager);
		indicator = (ScrollIndicatorView) v.findViewById(R.id.moretab_indicator);
		indicator.setScrollBar(new ColorBar(getActivity(), Color.RED, 5));

		// 设置滚动监听
		int selectColorId = R.color.tab_top_text_2;
		int unSelectColorId = R.color.tab_top_text_1;
		indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(getActivity(), selectColorId, unSelectColorId));

		viewPager.setOffscreenPageLimit(2);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getContext());
		indicatorViewPager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));

		// 默认true ，自动布局
		indicatorViewPager.getAdapter().notifyDataSetChanged();
		return v;
	}



	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// 设置是否自动布局
			indicator.setSplitAuto(isChecked);
		}
	};



//	public void on3(View view) {
//		size = 3;
//		indicatorViewPager.getAdapter().notifyDataSetChanged();
//	}
//
//	public void on4(View view) {
//		size = 4;
//		indicatorViewPager.getAdapter().notifyDataSetChanged();
//	}
//
//	public void on5(View view) {
//		size = 5;
//		indicatorViewPager.getAdapter().notifyDataSetChanged();
//	}
//
//	public void on12(View view) {
//		size = 12;
//		indicatorViewPager.getAdapter().notifyDataSetChanged();
//	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return size;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(names[position % names.length]);
			textView.setPadding(20, 0, 20, 0);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			MoreFragment fragment = new MoreFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(MoreFragment.INTENT_INT_INDEX, position);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getItemPosition(Object object) {
			return FragmentListPageAdapter.POSITION_NONE;
		}

	};

}
