package cn.edu.nju.software.jksc.symys.controller;


import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.jksc.symys.R;
import cn.edu.nju.software.jksc.symys.activities.ChooseButtonFactory;
import cn.edu.nju.software.jksc.symys.activities.LevelScoreActivity;
import cn.edu.nju.software.jksc.symys.activities.ScoreActivity;
import cn.edu.nju.software.jksc.symys.algorithm.AxisChecker;
import cn.edu.nju.software.jksc.symys.algorithm.ScoreCalculator;
import cn.edu.nju.software.jksc.symys.common.Bobble;

import static android.util.Log.v;

/**
 * Created by Xc on 2015/6/23.
 */
public class GamePanelController {
    Bobble[][] bobbles;
    Activity activity;
    int col_size;
    ImageView[][] imageViews;

    ViewGroup layout;
    final int NORMAL = 0;
    final int TO_MIX = 1;

    final int LEFT = 0;
    final int RIGHT = 1;
    final int UP = 2;
    final int DOWN = 3;

    final int NO_SWAP = 0;

    private long mix_time = 100;
    private long swap_time = 150;

    private int step = 0;
    private int max_step = 30;
    private int axises_target;


    HashMap<String, Object> gameData;

    public GamePanelController(Bobble[][] bobbles, HashMap<String, Object> gameData, Activity activity) {
        this.bobbles = bobbles;
        col_size = bobbles.length;
        imageViews = new ImageView[col_size][col_size];
        this.activity = activity;
        layout = (ViewGroup) activity.findViewById(R.id.main_panel);
        this.gameData = gameData;
        this.axises_target = 1;
        this.max_step = 30;
    }

    public void init() {
        for (int i = 0; i < col_size; ++i) {
            for (int j = 0; j < col_size; ++j) {
                final ImageView imageView = ImageViewFactory.getImageView(bobbles[i][j], activity, col_size, i, j);

                imageViews[i][j] = imageView;
                layout.addView(imageView);
            }
        }

        int width = ImageViewFactory.getScreenWidth(activity);

        ViewGroup.LayoutParams p = layout.getLayoutParams();
        p.width = p.height = width;
        layout.setLayoutParams(p);


        reset();
        ImageButton imageButton = (ImageButton) activity.findViewById(R.id.done_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               done();
            }
        });
        judge();
    }

    private void done(){
        Intent intent;
        if (isPoint()) {
            intent = new Intent(activity, ScoreActivity.class);
        } else {
            intent = new Intent(activity, LevelScoreActivity.class);
        }
        intent.putExtra("score", getScore());
        intent.putExtra("gameData", gameData);

        if(!isPoint()){
            int level =(Integer)gameData.get("level");

            int currentMax = (Integer) gameData.get("currentMax");
            gameData.put("currentMax", currentMax >= level ? currentMax : level);
            ChooseButtonFactory.setCurrentLevelStatus(activity, currentMax >= (level) ? currentMax : level);
        }
        activity.startActivity(intent);
        activity.finish();
    }

    //把所有的ImageView根据Bobble给初始化
    private void reset() {
        for (int i = 0; i < col_size; ++i) {
            for (int j = 0; j < col_size; ++j) {
                final int x = i;
                final int y = j;
                imageViews[i][j].setImageResource(bobbles[i][j].getPicID());
                resetImageViewSize(x, y);


                //有关混合的监听
                if (bobbles[i][j].isPrimary() && getMixNeighbour(i, j).size() > 0) {
                    imageViews[x][y].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toMix(x, y);
                        }
                    });
                } else {
                    imageViews[x][y].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reset();
                        }
                    });
                }

                //有关交换的监听
                if (bobbles[i][j].getColorID() > 0) {
                    imageViews[i][j].setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            float touchX = event.getX();
                            float touchY = event.getY();

                            float deltaX = touchX - v.getWidth() / 2;
                            float deltaY = touchY - v.getHeight() / 2;
                            switch (event.getActionMasked()) {
                                case MotionEvent.ACTION_DOWN://按下的时候
                                    break;
                                case MotionEvent.ACTION_MOVE://移动
                                    break;
                                case MotionEvent.ACTION_UP://开始移动或者取消

                                    if (Math.abs(deltaX) > Math.abs(deltaY)) {  //左右
                                        if (deltaX > v.getHeight()) {  //右
                                            swap(x, y, RIGHT);
                                        } else if (deltaX < 0 - v.getHeight()) {  //左
                                            swap(x, y, LEFT);
                                        }
                                    } else {  //上下
                                        if (deltaY > v.getHeight()) {  //下
                                            swap(x, y, DOWN);
                                        } else if (deltaY < 0 - v.getHeight()) {  //上
                                            swap(x, y, UP);
                                        }
                                    }
                                    break;
                                case MotionEvent.ACTION_OUTSIDE:
                            }
                            return false;
                        }
                    });
                }


            }
        }
    }


    private boolean isPoint() {
        return gameData.get("type").equals("point");
    }

    private void before_swap(int x, int y, int position) {
        int x2 = x;
        int y2 = y;

        int[] x_delta = new int[]{0, 0, -1, 1};
        int[] y_delta = new int[]{-1, 1, 0, 0};


        for (int i = 0; i < 4; i++) {
            if (i != position) {
                x2 = x + x_delta[i];
                y2 = y + y_delta[i];
                resetImageViewSize(x2, y2);
            }
        }

        x2 = x + x_delta[position];
        y2 = y + y_delta[position];
        if (x2 >= 0 && y2 >= 0 && x2 < col_size && y2 < col_size) {
            if (bobbles[x][y].getColorID() != bobbles[x2][y2].getColorID() && bobbles[x2][y2].getColorID() > 0) {
                zoom2(x2, y2, 1.05f);
            }
        }
    }

    private void swap(int x, int y, int position) {
        int x2 = x;
        int y2 = y;
        float animationX = 0;
        float animationY = 0;

        switch (position) {
            case LEFT:
                animationX = 0 - imageViews[x][y].getWidth();
                y2--;
                break;
            case RIGHT:
                animationX = imageViews[x][y].getWidth();
                y2++;
                break;
            case UP:
                animationY = 0 - imageViews[x][y].getHeight();
                x2--;
                break;
            case DOWN:
                animationY = imageViews[x][y].getHeight();
                x2++;
                break;
        }


        final int xx = x;
        final int yy = y;
        final int xx2 = x2;
        final int yy2 = y2;

        if (x2 >= 0 && y2 >= 0 && x2 < col_size && y2 < col_size) {
            if (bobbles[x][y].getColorID() != bobbles[x2][y2].getColorID() && bobbles[x2][y2].getColorID() > 0) {
                final Animation translateAnimation1 = new TranslateAnimation(0, animationX, 0, animationY);
                translateAnimation1.setDuration(200);
                final Animation translateAnimation2 = new TranslateAnimation(0, 0 - animationX, 0, 0 - animationY);
                translateAnimation2.setDuration(200);
                zoom2(x, y, 1.05f);
                zoom2(x2, y2, 1.05f);
                layout.bringChildToFront(imageViews[x][y]);

                final ImageView fim1 = imageViews[x][y];
                final ImageView fim2 = imageViews[x2][y2];


                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 0x2333) {
                            fim1.startAnimation(translateAnimation1);
                            fim2.startAnimation(translateAnimation2);
                        }
                    }
                };
                handler.sendEmptyMessage(0x2333);


//                imageViews[x][y].startAnimation(translateAnimation1);
//                imageViews[x2][y2].startAnimation(translateAnimation2);

                translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imageViews[xx][yy].clearAnimation();
                        imageViews[xx2][yy2].clearAnimation();

                        resetImageViewSize(xx, yy);
                        resetImageViewSize(xx2, yy2);

                        Bobble temp = bobbles[xx][yy];
                        bobbles[xx][yy] = bobbles[xx2][yy2];
                        bobbles[xx2][yy2] = temp;
                        step();
                        reset();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }


    }

    public void mix(int x1, int y1, final int x2, final int y2) {
        if (bobbles[x1][y1].mixWith(bobbles[x2][y2], isPoint())) {
//            float animationY = (x1 - x2) * imageViews[x1][y1].getHeight();
//            float animationX = (y1 - y2) * imageViews[x1][y1].getHeight();
//
//            Animation translateAnimation1 = new TranslateAnimation(0, animationX, 0, animationY);
            float animationY = (x1 - x2) * 1.0f;
            float animationX = (y1 - y2) * 1.0f;

            final Animation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, animationX,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, animationY);
            translateAnimation1.setDuration(mix_time);

            translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    step();
                    reset();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            layout.bringChildToFront(imageViews[x2][y2]);
            final ImageView fim = imageViews[x2][y2];

            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x2333) {
                        fim.startAnimation(translateAnimation1);
                    }
                }
            };
            handler.sendEmptyMessage(0x2333);

        }
    }

    private void step() {
        step++;
        judge();
    }

    public void judge() {
        ImageButton imageButton = (ImageButton) activity.findViewById(R.id.done_button);

        if(isPoint()){
            imageButton.setImageResource(R.drawable.done_active);
            imageButton.setClickable(true);
            TextView tv = (TextView) activity.findViewById(R.id.step);
            tv.setText("" + step);
            tv = (TextView) activity.findViewById(R.id.score);
            tv.setText(String.format(""+getScore()));

        }else{
            TextView tv = (TextView) activity.findViewById(R.id.step);
            tv.setText("" + (max_step-step));
            tv = (TextView) activity.findViewById(R.id.axis);
            tv.setText(String.format("%d/%d", axises(), axises_target));
            if (axises() >= axises_target) {
                imageButton.setImageResource(R.drawable.done_active);
                imageButton.setClickable(true);
            } else {
                imageButton.setImageResource(R.drawable.done);
                imageButton.setClickable(false);
            }
        }

    }


    public int axises() {
        return AxisChecker.countAxis(bobbles);
    }


    public long getScore() {
        if(isPoint()){
            return ScoreCalculator.calculatePointingModeScore(bobbles);
        }else{
            return ScoreCalculator.calculateNormalModeScore(bobbles,max_step-step, 999);
        }
    }

    //初始化


    private ArrayList<Pos> getMixNeighbour(int x, int y) {
        ArrayList<Pos> poses = new ArrayList<>();
        Pos[] poss = new Pos[4];
        poss[0] = new Pos(x - 1, y);
        poss[1] = new Pos(x + 1, y);
        poss[2] = new Pos(x, y - 1);
        poss[3] = new Pos(x, y + 1);

        for (Pos pos : poss) {
            if (pos.x >= 0 && pos.x < col_size && pos.y >= 0 && pos.y < col_size) {
                if (bobbles[pos.x][pos.y].isPrimary())
                    if (bobbles[pos.x][pos.y].getColorID() != bobbles[x][y].getColorID())
                        poses.add(pos);
            }
        }
        return poses;
    }


    //准备Mix i，j坐标
    private void toMix(int i, int j) {
        final int x = i;
        final int y = j;


        for (int ii = 0; ii < col_size; ++ii) {
            for (int jj = 0; jj < col_size; ++jj) {
                imageViews[ii][jj].setOnTouchListener(null);
                imageViews[ii][jj].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reset();
                    }
                });


            }
        }


        zoom(i, j);
        imageViews[x][y].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });


        ArrayList<Pos> poses = getMixNeighbour(x, y);


        for (final Pos pos : poses) {
            v("pos:", pos.x + " " + pos.y);
            zoom(pos.x, pos.y);
            imageViews[pos.x][pos.y].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mix(pos.x, pos.y, x, y);
                }
            });
        }
    }

    private void zoom(int x, int y) {
        imageViews[x][y].setScaleX(1.1f);
        imageViews[x][y].setScaleY(1.1f);
    }

    private void zoom2(int x, int y, float s) {
        imageViews[x][y].setScaleX(s);
        imageViews[x][y].setScaleY(s);
    }

    private void resetImageViewSize(int x, int y) {
        imageViews[x][y].setScaleX(1.0f);
        imageViews[x][y].setScaleY(1.0f);

    }

    static class Pos {
        public int x;
        public int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
