package cn.edu.nju.software.jksc.symys.activities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.nju.software.jksc.symys.R;

public class ChooseLevelActivity extends Activity implements OnClickListener {
    private ViewPager mViewPager;
    List<View> viewList;
    private RelativeLayout chooseLevelLayout1;
    private RelativeLayout chooseLevelLayout2;
    private static final int SIZE = 3;

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
        mViewPager.setAdapter(new MyPagerAdapter(viewList));
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

    }


    @Override
    public void onClick(View v) {
        Log.d("click:", "" + v.getId() + " " + v.toString());
    }
}
