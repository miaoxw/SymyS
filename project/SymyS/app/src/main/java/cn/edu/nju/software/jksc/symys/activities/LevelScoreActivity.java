package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import cn.edu.nju.software.jksc.symys.R;

public class LevelScoreActivity extends Activity {


    HashMap<String,Object> gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_score);
        TextView tx = (TextView)findViewById(R.id.header);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "font/HarringtON.TTF");
        tx.setTypeface(custom_font);


        tx = (TextView)findViewById(R.id.score_tv);
        tx.setTypeface(custom_font);

        tx.setText("" + getIntent().getLongExtra("score", -1L));

        gameData = (HashMap<String,Object>) getIntent().getSerializableExtra("gameData");


        ImageView replay = (ImageView) findViewById(R.id.score_replay);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replay();
            }
        });

        ImageView next = (ImageView) findViewById(R.id.score_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        ImageView home = (ImageView) findViewById(R.id.score_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
    }


    private void home(){
        finish();
    }

    private void next(){

    }

    private void replay(){
        Intent intent = new Intent(LevelScoreActivity.this,GamePanel.class);
        intent.putExtra("gameData", gameData);
        finish();
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_score, menu);
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
