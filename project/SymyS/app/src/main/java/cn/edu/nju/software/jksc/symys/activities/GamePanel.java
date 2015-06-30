package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import cn.edu.nju.software.jksc.symys.R;
import cn.edu.nju.software.jksc.symys.algorithm.MapGenerator;
import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.controller.GamePanelController;
import cn.edu.nju.software.jksc.symys.utils.MyShowCase;

/**
 * needs gameData ,default for 积分模式
 */
public class GamePanel extends Activity {


    GamePanelController gc;
    HashMap<String, Object> gameData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_panel);
        Bobble[][] colors = null;

        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "font/FZXY.ttf");
        if (isLevel()) {
            int level = (Integer) gameData.get("level");
            int size = level/9 +4;
            int axises = 1 << (level%9 ) / 3  ;
            int colorSum = (size -1 ) >=4 ? 4:(size-1);
            int step = 40 - level;
            getIntent().putExtra("step",step);
            getIntent().putExtra("axises",axises);
            colors = MapGenerator.generate(size, axises, 0, step, colorSum);
            MyShowCase.show(this, R.layout.level_helping, "level-help");

            ViewGroup header = (ViewGroup) findViewById(R.id.header);
            View view = LayoutInflater.from(this).inflate(R.layout.level_header, null);
            header.addView(view);


            TextView tx = (TextView) findViewById(R.id.axis_lb);
            tx.setTypeface(custom_font);
            tx = (TextView) findViewById(R.id.axis);
            tx.setTypeface(custom_font);
            tx = (TextView) findViewById(R.id.step);
            tx.setTypeface(custom_font);
            tx = (TextView) findViewById(R.id.step_lb);
            tx.setTypeface(custom_font);
        } else {
            colors = MapGenerator.generatePointingMode();
            MyShowCase.show(this, R.layout.point_helping, "point-help");


            ViewGroup header = (ViewGroup) findViewById(R.id.header);
            View view = LayoutInflater.from(this).inflate(R.layout.point_header, null);
            header.addView(view);

            TextView tx = (TextView) findViewById(R.id.score_lb);
            tx.setTypeface(custom_font);
            tx = (TextView) findViewById(R.id.score);
            tx.setTypeface(custom_font);
            tx = (TextView) findViewById(R.id.step);
            tx.setTypeface(custom_font);
            tx = (TextView) findViewById(R.id.step_lb);
            tx.setTypeface(custom_font);

        }
        gc = new GamePanelController(colors, gameData, this);
        gc.init();
    }

    private boolean isLevel() {
        if (getIntent().hasExtra("gameData")) {
            gameData = (HashMap<String, Object>) getIntent().getSerializableExtra("gameData");
            if (gameData.get("type").equals("level")) {
                return true;
            }
        } else {
            gameData = new HashMap<>();
            if (getIntent().getStringExtra("type").equals("level")) {
                gameData = new HashMap<>();
                gameData.put("type", "level");
                gameData.put("level", getIntent().getIntExtra("level", 1));
            } else {
                gameData.put("type", "point");
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
