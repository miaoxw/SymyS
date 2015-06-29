package com.example.xc.symys_demo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {

    public static final int red = 1;
    public static final int yellow = 2;
    public static final int blue = 4;
    public static final int orange = 3;
    public static final int purple = 5;
    public static final int green = 6;


    int col_size = 4;
    int circle_count = col_size * col_size;
    ImageView[] imageViews = new ImageView[circle_count];
    OnClickListener[] onClickListeners = new OnClickListener[circle_count];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_layout);
        for (int i = 0; i < col_size; ++i) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < col_size; ++j) {
                ImageView imageView = ImageViewFactory.getImageView((int) (Math.random() * 4 + 1), this);
                tableRow.addView(imageView);
                imageViews[col_size * i + j] = imageView;
            }
            tableLayout.addView(tableRow);
        }
        for (int i = 0; i < col_size; ++i) {
            for (int j = 0; j < col_size; ++j) {

                final int index = i * col_size + j;
                final ImageView imageView = imageViews[index];
                if (null == imageView) {
                    int[] aa = new int[3];
                    aa[4] = 5;
                }
                if (isPure(imageView)) {
                    onClickListeners[index] = new OnClickListener() {
                        public void onClick(View v) {  //按下后使得周围的按钮变大 ,并且添加监听
                            Log.i("pressed_id_ori",index+"");
                            int[] candidates = new int[]{index - 1, index + 1, index + col_size, index - col_size};
                            final ArrayList<Integer> nids = new ArrayList<>();  //相邻的操作的view
                            int x_index = index % col_size;
                            for (int candidate : candidates) {
                                if ((candidate >= 0) && (candidate < circle_count)) { //筛选是否出界
                                    if ((x_index > 0) && (x_index < col_size - 1)) { //筛选是否相邻
                                        if (isPure(imageViews[candidate])) {               //筛选是否纯色
                                            if (((int) imageViews[candidate].getTag())
                                                    != ((int) imageView.getTag())) {       //yan se xiang tong
                                                nids.add(candidate);
                                            }
                                        }
                                    }
                                }
                            }

                            for (final int nid: nids) {
                                final ImageView neighbourImageView = imageViews[nid];
                                neighbourImageView.setScaleX(1.1f);
                                neighbourImageView.setScaleY(1.1f);
                                OnClickListener ol = new OnClickListener() {
                                    public void onClick(View v) {
                                        Log.i("pressed_id_inner",nid+"");
                                        int color1 = (Integer) imageView.getTag();
                                        int color2 = (Integer) neighbourImageView.getTag();
                                        neighbourImageView.setImageResource(getPicID(color1 + color2));
                                        neighbourImageView.setTag(color1+color2);
                                        neighbourImageView.setScaleX(1.0f);
                                        neighbourImageView.setScaleY(1.0f);
                                        neighbourImageView.setOnClickListener(null);
                                        imageView.setVisibility(View.INVISIBLE);
                                        //还原隔壁的
                                        for(int otherNid : nids) {
                                            if(otherNid != nid){
                                                imageViews[otherNid].setScaleX(1.0f);
                                                imageViews[otherNid].setScaleY(1.0f);
                                                imageViews[otherNid].setOnClickListener(onClickListeners[otherNid]);
                                            }

                                        }

                                    }
                                };
                                neighbourImageView.setOnClickListener(ol);
                            }

                            for(int i = 0 ; i < col_size ; i ++){
                                if(! nids.contains(i) ){
                                    imageViews[i].setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            for(int otherNid : nids) {
                                                imageViews[otherNid].setScaleX(1.0f);
                                                imageViews[otherNid].setScaleY(1.0f);
                                                imageViews[otherNid].setOnClickListener(onClickListeners[otherNid]);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    };
                    imageView.setOnClickListener(onClickListeners[index]);
                }
            }


        }


//        final ImageView circle4 = (ImageView) findViewById(R.id.circle4);
//        circle4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int id : ids) {
//                    final ImageView iv = (ImageView) findViewById(id);
//                    Log.i("", "pad");
//                    iv.setScaleX(1.1f);
//                    iv.setScaleY(1.1f);
//                    iv.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            circle4.setVisibility(View.INVISIBLE);
//                            iv.setImageResource(R.drawable.purple);
//
//                        }
//                    });
//                }
//            }
//        });
    }

    boolean isPure(ImageView imageView) {
        int color = (Integer) imageView.getTag();
        switch (color) {
            case 1:
            case 2:
            case 4:
                return true;
            default:
                return false;
        }

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

    public int getPicID(int color) {
        switch (color) {
            case red:
                return R.drawable.red;
            case yellow:
                return R.drawable.yellow;
            case blue:
                return R.drawable.blue;
            case orange:
                return R.drawable.orange;
            case purple:
                return R.drawable.purple;
            case green:
                return R.drawable.green;
            default:
                return -1;
        }

    }

    public int mix(int color1, int color2) {
        int col = R.drawable.blue + R.drawable.green + R.drawable.red - color1 - color2;
        switch (col) {
            case R.drawable.green:
                return R.drawable.purple;
            case R.drawable.blue:
                return R.drawable.yellow;
            case R.drawable.red:
                return R.drawable.orange;
            default:
                return -1;
        }

    }

}
