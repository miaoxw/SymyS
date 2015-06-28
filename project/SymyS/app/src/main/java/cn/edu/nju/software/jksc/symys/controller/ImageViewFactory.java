package cn.edu.nju.software.jksc.symys.controller;

/**
 * Created by Xc on 2015/6/21.
 */

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;

import cn.edu.nju.software.jksc.symys.common.Bobble;


/**
 * Created by Xc on 2015/6/6.
 */
public class ImageViewFactory {

    public static ImageView getImageView(Bobble color, Context context, int count,int i ,int j) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(color.getPicID());


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // always use the portrait dimensions to do the scaling calculations so we always get a portrait shaped
        // web dialog
        int width = metrics.widthPixels < metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels;
        double totalWidth = width * 0.8;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) (width * 0.8 / count),(int) (width * 0.8 / count));



        imageView.setX((float) (j * width * 0.8 / count));
        imageView.setY((float) (i * width * 0.8 / count));

        int pad = (int) (width * 0.8 * 0.06 / count);
        imageView.setLayoutParams(params);
        imageView.setPadding(pad, pad, pad, pad);
        return imageView;
    }

    public static ImageView getBigImageView(Context context){
        ImageView imageView = new ImageView(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // always use the portrait dimensions to do the scaling calculations so we always get a portrait shaped
        // web dialog
        int width = metrics.widthPixels < metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)(width*0.8),(int)(width*0.8));



        imageView.setLayoutParams(params);
        return imageView;
    }


}
