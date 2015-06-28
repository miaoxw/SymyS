package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import cn.edu.nju.software.jksc.symys.R;

public class LevelScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_score);
        TextView tx = (TextView)findViewById(R.id.score_label);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "font/HarringtON.TTF");
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.score_tv);
        tx.setTypeface(custom_font);

        tx.setText(""+getIntent().getLongExtra("score",-1L));
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
