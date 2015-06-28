package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.nju.software.jksc.symys.R;

/**
 * Created by norvins on 15/6/29.
 */
public class MainMenuActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Button levelButton = (Button) findViewById(R.id.levelButton);
        levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainMenuActivity.this,ChooseLevelActivity.class);
                startActivity(intent);
            }
        });

        Button creditButton = (Button) findViewById(R.id.creditButton);
        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainMenuActivity.this,GamePanel.class);
                startActivity(intent);
            }
        });
    }

}
