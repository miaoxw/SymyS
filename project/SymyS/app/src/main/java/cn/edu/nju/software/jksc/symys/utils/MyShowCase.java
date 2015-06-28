package cn.edu.nju.software.jksc.symys.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import cn.edu.nju.software.jksc.symys.R;

/**
 * Created by Xc on 2015/6/28.
 */


public class MyShowCase {

    public static final String NO_LIMIT = "no_limit";
    public static void show(final Activity activity,int res_id ,String shot_id){
        SharedPreferences sp = activity.getSharedPreferences("shot", Context.MODE_PRIVATE);
        if(!sp.contains(shot_id+"")|shot_id.equals(NO_LIMIT)){
            View v = LayoutInflater.from(activity).inflate(res_id, null);
            final ViewGroup root = (ViewGroup)activity.getWindow().getDecorView();
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(activity, android.R.anim.fade_out));
                    v.setVisibility(View.INVISIBLE);
                }
            });
            root.addView(v);
            if(!shot_id.equals(NO_LIMIT)){
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(shot_id,1);
                editor.apply();
            }
        }




    }
}
