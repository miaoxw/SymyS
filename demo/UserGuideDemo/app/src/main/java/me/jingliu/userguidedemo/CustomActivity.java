package me.jingliu.userguidedemo;


import android.app.Activity;
import android.os.Bundle;

import com.github.amlcurran.showcaseview.ShowcaseView;


public class CustomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        ShowcaseView showcaseView = new ShowcaseView.Builder(this)
                .setStyle(R.style.Custom_semi_transparent_demo)//setStyle instead of setTarget!
                .hideOnTouchOutside()
                .build();

        //showcaseView.setBackground(getResources().getDrawable(R.drawable.swipe_back_en));//minAPI=16
        showcaseView.setBackgroundDrawable(getResources().getDrawable(R.drawable.swipe_back_en));//deprecated.

    }
}

