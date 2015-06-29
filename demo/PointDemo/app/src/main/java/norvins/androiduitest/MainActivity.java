package norvins.androiduitest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import widget.RiseNumberTextView;


public class MainActivity extends Activity {
    private ImageView verticalAxis = null;
    private ImageView horizontalAxis = null;
    private ImageButton doneButton = null;
    private RiseNumberTextView pointText = null;
    private Animation horizontalAppear;
    private Animation verticalAppear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uitest);

        doneButton = (ImageButton) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new doneListener());

        verticalAxis = (ImageView) findViewById(R.id.verticalAxis);
        verticalAxis.setVisibility(View.GONE);
        horizontalAxis = (ImageView) findViewById(R.id.horizontalAxis);
        horizontalAxis.setVisibility(View.GONE);

        pointText = (RiseNumberTextView) findViewById(R.id.pointText);

    }


    class doneListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            Animation disappear = AnimationUtils.loadAnimation(MainActivity.this,R.anim.disappear);
            horizontalAppear = AnimationUtils.loadAnimation(MainActivity.this,R.anim.appear);

            disappear.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    doneButton.setVisibility(View.INVISIBLE);
                    verticalAppear();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            horizontalAppear.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    horizontalAxis.setVisibility(View.VISIBLE);
                    countPoint();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            horizontalAxis.startAnimation(horizontalAppear);

            verticalAppear = AnimationUtils.loadAnimation(MainActivity.this,R.anim.appear);
            verticalAppear.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    verticalAxis.setVisibility(View.VISIBLE);
                    horizontalAppear();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            verticalAxis.startAnimation(verticalAppear);
            doneButton.startAnimation(disappear);
        }
    }

    public void verticalAppear(){

    }

    public void horizontalAppear(){

    }

    public void countPoint(){
        pointText.withNumber(2666);
        pointText.setDuration(5000);
        pointText.start();
        pointText.setOnEnd(new RiseNumberTextView.EndListener() {
            @Override
            public void onEndFinish() {
//                            Toast.makeText(MainActivity.this, "增长完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
