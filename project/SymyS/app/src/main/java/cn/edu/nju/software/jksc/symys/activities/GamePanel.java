package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.HashMap;

import cn.edu.nju.software.jksc.symys.R;
import cn.edu.nju.software.jksc.symys.algorithm.MapGenerator;
import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.controller.GamePanelController;
import cn.edu.nju.software.jksc.symys.utils.MyShowCase;

/**
 * needs gameData ,default for 积分模式
 *
 *
 */
public class GamePanel extends Activity {


    GamePanelController gc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_panel);
        HashMap<String,Object> gameData;
        Bobble[][] colors = null;
        if(isLevel()){
            gameData = (HashMap<String,Object>)getIntent().getSerializableExtra("gameData");
            Bundle extras = getIntent().getExtras();
            int size = (Integer) extras.get("boardSize");
            int axises = (Integer) extras.get("axisSum");
            int colorSum = (Integer) extras.get("colorSum");
            colors = MapGenerator.generate(size, axises, 0, 40, colorSum);
            MyShowCase.show(this, R.layout.level_helping, "level-help");
        }else{
            gameData = new HashMap<>();
            gameData.put("type","point");
            colors = MapGenerator.generatePointingMode();
            MyShowCase.show(this, R.layout.point_helping, "point-help");
        }

        gc = new GamePanelController(colors, gameData,this);
        gc.init();

        TextView tx = (TextView)findViewById(R.id.axis_lb);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "font/FZXY.ttf");
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.axis);
        tx.setTypeface(custom_font);
        tx = (TextView)findViewById(R.id.step);
        tx.setTypeface(custom_font);
        tx = (TextView)findViewById(R.id.step_lb);
        tx.setTypeface(custom_font);


    }

    private boolean isLevel() {
        if(getIntent().hasExtra("gameData")){
            HashMap<String,Object> data = (HashMap<String,Object>)getIntent().getSerializableExtra("gameData");
            if(data.get("type").equals("level")){
                return true;
            }
        }

        return false;
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
