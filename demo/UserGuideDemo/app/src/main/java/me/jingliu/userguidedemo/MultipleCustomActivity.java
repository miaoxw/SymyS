package me.jingliu.userguidedemo;

import android.app.Activity;
import android.os.Bundle;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;


public class MultipleCustomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_custom);

        ShowcaseView showcaseView = new ShowcaseView.Builder(this)
                .setStyle(R.style.Custom_semi_transparent_demo)//setStyle instead of setTarget!
                .hideOnTouchOutside()
                .setShowcaseEventListener(new OnShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewHide(ShowcaseView showcaseView) {//add another showcaseview
                    }

                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {//add another showcaseview
                        showAnotherSCV();
                    }

                    @Override
                    public void onShowcaseViewShow(ShowcaseView showcaseView) {
                    }
                })
                .build();

        //showcaseView.setBackground(getResources().getDrawable(R.drawable.swipe_back_en));//minAPI=16
        showcaseView.setBackgroundDrawable(getResources().getDrawable(R.drawable.swipe_back_en));//deprecated.

    }

    public void showAnotherSCV() {
        ShowcaseView showcaseView = new ShowcaseView.Builder(this)
                .setStyle(R.style.Custom_semi_transparent_demo)//setStyle instead of setTarget!
                .hideOnTouchOutside()
                .build();

        //showcaseView.setBackground(getResources().getDrawable(R.drawable.swipe_back_en));//minAPI=16
        showcaseView.setBackgroundDrawable(getResources().getDrawable(R.drawable.swipe_up_and_down_en));//deprecated.
    }

}
