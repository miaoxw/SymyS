package me.jingliu.userguidedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;


public class MultipleDefaultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_default);

        displayShowcaseViewOneTwo(this, R.id.img_view, R.id.like_bn);
    }

    private void displayShowcaseViewOneTwo(final Activity activity,final int id1,final int id2) {
        new ShowcaseView.Builder(activity)
                .setContentTitle("Look how cute it is!")
                .setContentText("This is a lovely kitten.")
                .setTarget(new ViewTarget(activity.findViewById(id1)))
                .setShowcaseEventListener(new OnShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewHide(ShowcaseView showcaseView) {//add another showcaseview
                    }

                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {//add another showcaseview
                        showcaseView.setVisibility(View.GONE);
                        showOverlayTutorialTwo(activity, id2);
                    }

                    @Override
                    public void onShowcaseViewShow(ShowcaseView showcaseView) {
                    }
                })
                .build();
    }

    public void showOverlayTutorialTwo(Activity activity, int id) {
        new ShowcaseView.Builder(activity)
                .setContentTitle("Like button")
                .setContentText("Click the button if you like kittens.")
                .setTarget(new ViewTarget(activity.findViewById(id)))
                .setShowcaseEventListener(new OnShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewHide(ShowcaseView showcaseView) {
                    }

                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
                        showcaseView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onShowcaseViewShow(ShowcaseView showcaseView) {
                    }
                })
                .build();
    }
}

