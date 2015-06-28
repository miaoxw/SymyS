package cn.edu.nju.software.jksc.symys.activities;

/**
 * Created by jingliu on 15/6/28.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cn.edu.nju.software.jksc.symys.R;

public class ChooseButtonFactory {
    public static final String saveFileName="levelstatus";
    public static ArrayList<Button> getButtonList(View view) {
        ArrayList<Button> buttons = new ArrayList<>();
        final ArrayList<Integer> buttonIds = getButtonIds();
        for (int i = 0; i < buttonIds.size(); i++) {
            Button btn = (Button) view.findViewById(buttonIds.get(i));
            buttons.add(btn);
        }
        return buttons;
    }

    public static ArrayList<Integer> getButtonIds() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(new Integer(R.id.button1));
        list.add(new Integer(R.id.button2));
        list.add(new Integer(R.id.button3));
        list.add(new Integer(R.id.button4));
        list.add(new Integer(R.id.button5));
        list.add(new Integer(R.id.button6));
        list.add(new Integer(R.id.button7));
        list.add(new Integer(R.id.button8));
        list.add(new Integer(R.id.button9));
        return list;
    }

    public static ArrayList<Integer> getButtonImgRes(int viewId,ChooseButtonType type){
        if(type==ChooseButtonType.SELECT){
            return getButtonImgResSelect(viewId);
        }else if(type==ChooseButtonType.LOCKED){
            return null;
        }else if(type==ChooseButtonType.UNLOCKED){
            return getButtonImgResUnlock(viewId);
        }
        return null;
    }

    private static ArrayList<Integer> getButtonImgResSelect(int viewId){
        ArrayList<Integer> res=new ArrayList<>();
        if(viewId==0){
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_1));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_2));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_3));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_4));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_5));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_6));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_7));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_8));
            res.add(new Integer(R.drawable.chooselevelbtn_select_blue_9));
        }else if(viewId==1){
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_1));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_2));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_3));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_4));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_5));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_6));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_7));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_8));
            res.add(new Integer(R.drawable.chooselevelbtn_select_red_9));
        }else if(viewId==2){
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_1));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_2));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_3));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_4));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_5));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_6));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_7));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_8));
            res.add(new Integer(R.drawable.chooselevelbtn_select_yellow_9));
        }
        return res;
    }
    public static Integer getButtonImgResLocked(int viewId){
        Integer integer=new Integer(0);
        if(viewId==0){
            integer=new Integer(R.drawable.chooselevelbtn_locked_blue);
        }else if(viewId==1){
            integer=new Integer(R.drawable.chooselevelbtn_locked_red);
        }else if(viewId==2){
            integer=new Integer(R.drawable.chooselevelbtn_locked_yellow);
        }
        return integer;
    }

    public static boolean[][] getCurrentLevelStatus(Context context,int viewId){
        int curBase=viewId*9;
        boolean[][] status=new boolean[3][3];
        SharedPreferences pref=context.getSharedPreferences(saveFileName,Context.MODE_PRIVATE);
        String statusStr=pref.getString("levelStatus",null);
        if(statusStr!=null){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    char c=statusStr.charAt(curBase+i*3+j);
                    if(c=='0'){
                        status[i][j]=false;
                    }else{
                        status[i][j]=true;
                    }
                }
            }
        }else{
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    status[i][j]=false;
                }
            }
        }
        return status;
    }

    private static ArrayList<Integer> getButtonImgResUnlock(int viewId){
        ArrayList<Integer> res=new ArrayList<>();
        if(viewId==0){
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_1));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_2));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_3));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_4));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_5));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_6));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_7));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_8));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_blue_9));
        }else if(viewId==1){
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_1));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_2));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_3));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_4));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_5));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_6));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_7));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_8));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_red_9));

        }else if(viewId==2){
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_1));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_2));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_3));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_4));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_5));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_6));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_7));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_8));
            res.add(new Integer(R.drawable.chooselevelbtn_unlocked_yellow_9));
        }
        return res;
    }
}
