package cn.edu.nju.software.jksc.symys.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.nju.software.jksc.symys.R;

public class MyPagerAdapter extends PagerAdapter {

    private List<View> mListView;
    private Context context;
    private int currentSelectLevel=0;
    private int currentAccessLevel=0;
    private int currentMaxLevel=-1;

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
        Log.v("inistantiateIem","");
        ((ViewGroup)arg0).addView(mListView.get(arg1), 0);
        ArrayList<Button> buttons=ChooseButtonFactory.getButtonList(arg0);
        final int curArg1=arg1;
        for(int i=0;i<buttons.size();i++){
            final Button btn=buttons.get(i);
            final int currentIndex=i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentSelectLevel=currentIndex+curArg1*9;
                    if(currentSelectLevel<=currentAccessLevel){
                        Intent intent=new Intent(context,GamePanel.class);
                        HashMap<String,Object> gameData = new HashMap<>();
                        gameData.put("type","level");
                        gameData.put("level",currentSelectLevel);
                        gameData.put("currentMax",currentMaxLevel);
                        intent.putExtra("gameData",gameData);
                        context.startActivity(intent);
                        Log.v("click",""+currentSelectLevel);
                    }
                    Log.v("click:","currentSelectLevel:"+currentSelectLevel+" access level:"+currentAccessLevel);
                    Log.v("currentMaxLevel",""+currentMaxLevel);

                }
            });
        }
        boolean levelStatus[][]=ChooseButtonFactory.getCurrentLevelStatus(context, arg1);
        Integer lockedRes=ChooseButtonFactory.getButtonImgResLocked(arg1);
        ArrayList<Integer> selectRes=ChooseButtonFactory.getButtonImgRes(arg1,ChooseButtonType.SELECT);
        ArrayList<Integer> unlockedRes=ChooseButtonFactory.getButtonImgRes(arg1,ChooseButtonType.UNLOCKED);

        int lastestIndex=-1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(levelStatus[i][j]){
                    lastestIndex=arg1+i*3+j;
                    currentAccessLevel=lastestIndex+1;
                    currentMaxLevel=lastestIndex;
                    buttons.get(i*3+j).setBackgroundResource(unlockedRes.get(i*3+j));
                }else{
                    if(arg1+i*3+j-1==lastestIndex){
                        buttons.get(i*3+j).setBackgroundResource(selectRes.get(i*3+j));
                    }else{
                        buttons.get(i*3+j).setBackgroundResource(lockedRes);
                    }

                }
            }
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
//    @Override
//    public int getItemPosition(Object object){
//
//        return POSITION_NONE;
//    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub
        super.startUpdate(arg0);
        Log.v("startUPdate","");
//        for(int i=0;i<mListView.size();i++){
//            if(arg0.equals(mListView.get(i))){
//                updateView(arg0,i);
//            }
//        }

    }
    public void setContext(Context context){
        this.context=context;
    }

    public void updateView(View arg0, int arg1){
        Log.v("updateView","");
        ArrayList<Button> buttons=ChooseButtonFactory.getButtonList(arg0);
        boolean levelStatus[][]=ChooseButtonFactory.getCurrentLevelStatus(context, arg1);
        Integer lockedRes=ChooseButtonFactory.getButtonImgResLocked(arg1);
        ArrayList<Integer> selectRes=ChooseButtonFactory.getButtonImgRes(arg1,ChooseButtonType.SELECT);
        ArrayList<Integer> unlockedRes=ChooseButtonFactory.getButtonImgRes(arg1,ChooseButtonType.UNLOCKED);
        int lastestIndex=-1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(levelStatus[i][j]){
                    lastestIndex=arg1+i*3+j;
                    currentAccessLevel=lastestIndex+1;
                    currentMaxLevel=lastestIndex;
                    buttons.get(i*3+j).setBackgroundResource(unlockedRes.get(i*3+j));
                }else{
                    if(arg1+i*3+j-1==lastestIndex){
                        buttons.get(i*3+j).setBackgroundResource(selectRes.get(i*3+j));
                    }else{
                        buttons.get(i*3+j).setBackgroundResource(lockedRes);
                    }

                }
            }
        }
    }


}