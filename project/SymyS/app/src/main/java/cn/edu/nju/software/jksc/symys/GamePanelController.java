package cn.edu.nju.software.jksc.symys;


import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import cn.edu.nju.software.jksc.symys.common.Bobble;

/**
 * Created by Xc on 2015/6/23.
 */
public class GamePanelController {
    Bobble[][] bobbles;
    TableLayout layout;
    Activity activity;
    int col_size;
    ImageView[][] imageViews;

    final int NORMAL = 0;
    final int TO_MIX = 1;

    final int LEFT = -1;
    final int RIGHT = 1;
    final int UP = -2;
    final int DOWN = 2;

    final int NO_SWAP =0;

    public GamePanelController(Bobble[][] bobbles, TableLayout layout, Activity activity) {
        this.bobbles = bobbles;
        col_size = bobbles.length;
        imageViews = new ImageView[col_size][col_size];
        this.layout = layout;
        this.activity = activity;
    }


    public void swap(int x, int y, int position) {
        int x2 = x;
        int y2 = y;
        switch (position) {
            case LEFT:
                y2--;
                break;
            case RIGHT:
                y2++;
                break;
            case UP:
                x2--;
                break;
            case DOWN:
                x2++;
                break;
        }


        if (x2 >= 0 && y2 >= 0 && x2 < col_size && y2 < col_size) {
            if(bobbles[x][y].getColorID()!=bobbles[x2][y2].getColorID()){
                Bobble temp = bobbles[x][y];
                bobbles[x][y] = bobbles[x2][y2];
                bobbles[x2][y2] = temp;
                reset();
            }
        }


    }

    public void mix(int x1, int y1, int x2, int y2) {

        if (bobbles[x1][y1].mixWith(bobbles[x2][y2])) {
            reset();
        }
    }


    public Bobble[][] getBobbles() {
        return bobbles;
    }


    //初始化
    public void init() {
        for (int i = 0; i < col_size; ++i) {
            TableRow tableRow = new TableRow(activity);
            for (int j = 0; j < col_size; ++j) {
                final ImageView imageView = ImageViewFactory.getImageView(bobbles[i][j], activity);


                tableRow.addView(imageView);
                imageViews[i][j] = imageView;
            }
            layout.addView(tableRow);
        }
        reset();
    }


    //把所有的ImageView根据Bobble给初始化
    private void reset() {
        for (int i = 0; i < col_size; ++i) {
            for (int j = 0; j < col_size; ++j) {
                final int x = i;
                final int y = j;
                imageViews[i][j].setImageResource(bobbles[i][j].getPicID());
                resetImageViewSize(x, y);

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

                imageViews[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getActionMasked()) {
                            case MotionEvent.ACTION_DOWN://按下的时候
                                break;
                            case MotionEvent.ACTION_MOVE://移动
                                break;
                            case MotionEvent.ACTION_UP://开始移动或者取消
                                float touchX = event.getX();
                                float touchY = event.getY();

                                float deltaX = touchX - v.getWidth() / 2;
                                float deltaY = touchY - v.getHeight() / 2;
                                if (Math.abs(deltaX) > Math.abs(deltaY)) {  //左右
                                    if (deltaX > v.getHeight()) {  //右
                                        swap(x, y, RIGHT);
                                    }else if(deltaX < 0 - v.getHeight()) {  //左
                                        swap(x, y, LEFT);
                                    }
                                } else {  //上下
                                    if (deltaY > v.getHeight()) {  //下
                                        swap(x, y, DOWN);
                                    }else if (deltaY < 0 - v.getHeight()) {  //上
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


    private ArrayList<Pos> getMixNeighbour(int x, int y) {
        ArrayList<Pos> poses = new ArrayList<>();
        Pos[] poss = new Pos[col_size];
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

    private Pos[] getSwapNeighbour(int x, int y) {
        return null;
    }


    private Pos getNeighbour(int x, int y, int position) {
        Pos ret = new Pos(x + position % col_size, y + position / col_size);
        if (ret.x >= 0 && ret.y >= 0 && ret.x < col_size && ret.y < col_size) {
            return ret;
        } else {
            return null;
        }
    }


    //准备Mix i，j坐标
    private void toMix(int i, int j) {
        final int x = i;
        final int y = j;


        for (int ii = 0; ii < col_size; ++ii) {
            for (int jj = 0; jj < col_size; ++jj) {
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
            Log.v("pos:", pos.x + " " + pos.y);
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
