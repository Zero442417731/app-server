package com.example.wzs.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


import com.nonecity.R;
import com.example.wzs.myapplication.adapter.GuideViewPagerAdapter;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 欢迎页
 */
public class WelcomeGuideActivity extends Activity implements OnClickListener {

	private ViewPager vp;
	private GuideViewPagerAdapter adapter;
	private List<View> views;
	private TextView startBtn;

	// 引导页图片资源
	private static final int[] pics = { R.layout.guid_view1,
			R.layout.guid_view2, R.layout.guid_view3, R.layout.guid_view4 };

	// 底部小点图片

	// 记录当前选中位置
	private int currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		views = new ArrayList<View>();

		// 初始化引导页视图列表
		for (int i = 0; i < pics.length; i++) {
			View view = LayoutInflater.from(this).inflate(pics[i], null);
			
			if (i == pics.length - 1) {
				startBtn = (TextView) view.findViewById(R.id.btn_login);
				startBtn.setTag("enter");
				startBtn.setOnClickListener(this);
			}
			
			views.add(view);

		}

		vp = (ViewPager) findViewById(R.id.vp_guide);
		// 初始化adapter
		adapter = new GuideViewPagerAdapter(views);
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(new PageChangeListener());


		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 如果切换到后台，就设置下次不进入功能引导页
		SharedPreferencesUtil.putBoolean(WelcomeGuideActivity.this, Constant.FIRST_OPEN, true);
		finish();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}



	/**
	 * 设置当前view
	 * 
	 * @param position
	 */
	private void setCurView(int position) {
		if (position < 0 || position >= pics.length) {
			return;
		}
		vp.setCurrentItem(position);
	}

	/**
	 * 设置当前指示点
	 *
	 * @param position
	 */
	private void setCurDot(int position) {
		if (position < 0 || position > pics.length || currentIndex == position) {
			return;
		}
		currentIndex = position;
	}

	@Override
	public void onClick(View v) {
		if (v.getTag().equals("enter")) {
			enterMainActivity();
			return;
		}
		
		int position = (Integer) v.getTag();
		setCurView(position);
		setCurDot(position);
	}
	
	
	private void enterMainActivity() {
		Intent intent = new Intent(WelcomeGuideActivity.this,
				SplashActivity.class);
		startActivity(intent);
		SharedPreferencesUtil.putBoolean(WelcomeGuideActivity.this, Constant.FIRST_OPEN, true);
		finish();
	}

	private class PageChangeListener implements OnPageChangeListener {
		// 当滑动状态改变时调用
		@Override
		public void onPageScrollStateChanged(int position) {
			// arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。

		}

		// 当前页面被滑动时调用
		@Override
		public void onPageScrolled(int position, float arg1, int arg2) {
			// arg0 :当前页面，及你点击滑动的页面
			// arg1:当前页面偏移的百分比
			// arg2:当前页面偏移的像素位置

		}

		// 当新的页面被选中时调用
		@Override
		public void onPageSelected(int position) {
			// 设置底部小点选中状态
			setCurDot(position);
		}

	}
}
