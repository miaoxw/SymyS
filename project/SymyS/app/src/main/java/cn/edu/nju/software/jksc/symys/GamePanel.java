package cn.edu.nju.software.jksc.symys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import cn.edu.nju.software.jksc.symys.algorithm.MapGenerator;
import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;


public class GamePanel extends Activity {


    int col_size = 4;
    int circle_count = col_size * col_size;
    ImageView[] imageViews = new ImageView[circle_count];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_panel);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.main_panel);
        Bobble[][] colors = MapGenerator.generate(col_size,1,0,4);
        Log.i("length:", colors==null?"0":"1");
        for (int i = 0; i < col_size; ++i) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < col_size; ++j) {
                ImageView imageView = ImageViewFactory.getImageView(colors[i][j], this);
                tableRow.addView(imageView);
                imageViews[col_size * i + j] = imageView;
            }
            tableLayout.addView(tableRow);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_panel, menu);
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
