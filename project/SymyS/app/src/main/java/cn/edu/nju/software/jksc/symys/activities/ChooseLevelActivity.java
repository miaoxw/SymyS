package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.software.jksc.symys.R;

public class ChooseLevelActivity extends Activity{
    private ViewPager mViewPager;
    List<View> viewList;
    private static final int SIZE = 3;
    MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        LayoutInflater mInflater = getLayoutInflater().from(this);

        View v1 = mInflater.inflate(R.layout.layout1, null);
        View v2 = mInflater.inflate(R.layout.layout1, null);
        View v3 = mInflater.inflate(R.layout.layout1, null);

        //添加页面数据
        viewList = new ArrayList<View>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);
        //实例化适配器
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter=new MyPagerAdapter(viewList);
        adapter.setContext(this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0); //设置默认当前页

        View view = viewList.get(0);
        Button leftBtn = (Button) findViewById(R.id.chooseBtLeft);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curItem = mViewPager.getCurrentItem();
                if (curItem > 0) {
                    mViewPager.setCurrentItem(curItem - 1);
                }
            }
        });
        Button rightBtn = (Button) findViewById(R.id.chooseBtRight);
        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int curItem = mViewPager.getCurrentItem();
                if (curItem < viewList.size()) {
                    mViewPager.setCurrentItem(curItem + 1);
                }
            }
        });
        //ChooseButtonFactory.setCurrentLevelStatus(this, 10);
        //Log.d("levelStatusUpdate:",ChooseButtonFactory.getStatusString(this)+":::end");
    }



    @Override
    protected void onResume(){
        super.onResume();
        Log.v("resume", "");
        adapter.updateView(viewList.get(0), 0);
        adapter.updateView(viewList.get(1),1);
        adapter.updateView(viewList.get(2),2);
    }


}
