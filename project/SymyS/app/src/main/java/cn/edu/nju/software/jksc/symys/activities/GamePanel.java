package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;

import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.nju.software.jksc.symys.GamePanelController;
import cn.edu.nju.software.jksc.symys.R;
import cn.edu.nju.software.jksc.symys.algorithm.MapGenerator;
import cn.edu.nju.software.jksc.symys.common.Bobble;


public class GamePanel extends Activity {



    GamePanelController gc;

    int showid = 23332;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_panel);

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.main_panel);
        Bundle extras = getIntent().getExtras();
        int size = (Integer) extras.get("boardSize");
        int axises = (Integer) extras.get("axisSum");
        int colorSum = (Integer) extras.get("colorSum");
        Bobble[][] colors = MapGenerator.generate(size,axises,0,40,colorSum);


        gc = new GamePanelController(colors,axises,frameLayout,this);
        gc.init();


        ArrayList<Integer> dlist = new ArrayList<>();
        dlist.add(R.drawable.user_guide1);
        dlist.add(R.drawable.user_guide2);
        dlist.add(R.drawable.user_guide3);

        s1(dlist.iterator());


    }

    private void s1(Iterator<Integer> iter){
        int source = iter.next();
        final boolean next = iter.hasNext();
        final Iterator<Integer> fiter = iter;
        showid++;

        final long fshowid = showid;
        ShowcaseView showcaseView = new ShowcaseView.Builder(this)
                .setStyle(R.style.user_guide)
                .hideOnTouchOutside()
                .setShowcaseEventListener(new OnShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewHide(ShowcaseView showcaseView) {
                        if (next) {
                            s1(fiter);
                        }
                    }

                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {

                    }

                    @Override
                    public void onShowcaseViewShow(ShowcaseView showcaseView) {

                    }
                })
                .build();
        showcaseView.setBackgroundDrawable(getResources().getDrawable(source));
        showcaseView.hideButton();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_game_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
