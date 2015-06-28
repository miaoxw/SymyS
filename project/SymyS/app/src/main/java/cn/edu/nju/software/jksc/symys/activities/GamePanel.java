package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.github.amlcurran.showcaseview.ShowcaseView;

import cn.edu.nju.software.jksc.symys.controller.GamePanelController;
import cn.edu.nju.software.jksc.symys.R;
import cn.edu.nju.software.jksc.symys.algorithm.MapGenerator;
import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.utils.MyShowCase;


public class GamePanel extends Activity {


    GamePanelController gc;


    int showid = 23332;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_panel);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.main_panel);
        Bundle extras = getIntent().getExtras();
        int size = (Integer) extras.get("boardSize");
        int axises = (Integer) extras.get("axisSum");
        int colorSum = (Integer) extras.get("colorSum");
        Bobble[][] colors = MapGenerator.generate(size, axises, 0, 40, colorSum);


        gc = new GamePanelController(colors, axises, frameLayout, this);
        gc.init();



        MyShowCase.show(this,R.layout.level_helping,"level-help");
//        MyShowCase.show(this,R.layout.level_helping,MyShowCase.NO_LIMIT);
    }




    @Override
    public void finish() {
        super.finish();
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
