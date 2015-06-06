package me.jingliu.userguidedemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;


public class DefaultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        Button get_src_bn = (Button)findViewById(R.id.get_source_bn);
        get_src_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/Beeder/ShowcaseViewDemo");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(get_src_bn))
                .setContentTitle("Default ShowcaseView")
                .setContentText("This is highlighting the button view.\nIn Default ShowcaseView, you must set the Target you want to highlight!")
                .hideOnTouchOutside()
                .build();
    }
}
