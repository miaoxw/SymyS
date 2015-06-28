package cn.edu.nju.software.jksc.symys.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.nju.software.jksc.symys.R;

public class MyPagerAdapter extends PagerAdapter {

    private List<View> mListView;


    public MyPagerAdapter(List<View> mListView) {
        super();
        this.mListView = mListView;
    }


    //销毁position位置的界面
    public void destroyItem(View arg0, int arg1, Object arg2) {
        // TODO Auto-generated method stub
        ((ViewGroup)arg0).removeView(mListView.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

    ////获取当前窗体界面数
    public int getCount() {
        // TODO Auto-generated method stub
        return mListView.size();
    }
    //初始化position位置的界面
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        // TODO Auto-generated method stub
        ((ViewGroup)arg0).addView(mListView.get(arg1), 0);
        ArrayList<Button> buttons=ChooseButtonFactory.getButtonList(arg0);
        for(int i=0;i<buttons.size();i++){
            final Button btn=buttons.get(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("click",""+btn.getId());
                }
            });
        }
        if(arg1==0){
            Button bt=(Button)arg0.findViewById(R.id.button1);
            bt.setBackgroundResource(R.drawable.chooselevelbtn_unlocked_blue_1);
            Log.d("0:",""+bt.getId());
        }
        if(arg1==1){
            Button bt=(Button)arg0.findViewById(R.id.button1);
            bt.setBackgroundResource(R.drawable.chooselevelbtn_select_blue_2);
            Log.d("1:",""+bt.getId());
        }
        if(arg1==2){
            Button bt=(Button)arg0.findViewById(R.id.button1);
            bt.setBackgroundResource(R.drawable.chooselevelbtn_locked_blue);
            Log.d("2:",""+bt.getId());
        }
        return mListView.get(arg1);
    }

    // 判断是否由对象生成界面
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0==(arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

}