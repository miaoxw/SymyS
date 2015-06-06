package me.jingliu.userguidedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button show_default_SCV_bn=(Button)findViewById(R.id.default_showcase_view_bn);
        Button show_custom_SCV_bn=(Button)findViewById(R.id.custom_showcase_view_bn);
        Button show_multiple_default_SCV_bn=(Button)findViewById(R.id.multiple_default_showcase_view_bn);
        Button show_multiple_custom_SCV_bn=(Button)findViewById(R.id.multiple_custom_showcase_view_bn);

        show_default_SCV_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DefaultActivity.class);
                startActivity(intent);
            }
        });

        show_custom_SCV_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CustomActivity.class);
                startActivity(intent);
            }
        });

        show_multiple_default_SCV_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MultipleDefaultActivity.class);
                startActivity(intent);
            }
        });

        show_multiple_custom_SCV_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MultipleCustomActivity.class);
                startActivity(intent);
            }
        });
    }
}

