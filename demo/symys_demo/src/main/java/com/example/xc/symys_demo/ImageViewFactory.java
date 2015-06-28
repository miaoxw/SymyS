package com.example.xc.symys_demo;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.xc.symys_demo.R;

/**
 * Created by Xc on 2015/6/6.
 */
public class ImageViewFactory {


    public static final int red = 1;
    public static final int yellow = 2;
    public static final int blue = 4;

    public static final int orange = 3;
    public static final int purple = 5;
    public static final int green = 6;

    public static ImageView getImageView(int color ,Context context){
        ImageView imageView = new ImageView(context);
        switch (color){
            case red:
                imageView.setImageResource(R.drawable.red);
                break;
            case yellow:
                imageView.setImageResource(R.drawable.yellow);
                break;
            case blue:
                imageView.setImageResource(R.drawable.blue);
                break;
            case orange:
                imageView.setImageResource(R.drawable.orange);
                break;
            case purple:
                imageView.setImageResource(R.drawable.purple);
                break;
            case green:
                imageView.setImageResource(R.drawable.green);
                break;
        }
        DisplayMetrics dm = new DisplayMetrics();
        int dpi = dm.densityDpi;

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.height= 100;
        params.width= params.height;
        imageView.setLayoutParams(params);
        imageView.setTag(color);
        imageView.setPadding(5,5,5,5);
        return imageView;
    }


}
