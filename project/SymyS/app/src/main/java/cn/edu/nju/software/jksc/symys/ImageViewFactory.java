package cn.edu.nju.software.jksc.symys;

/**
 * Created by Xc on 2015/6/21.
 */

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableRow;

import cn.edu.nju.software.jksc.symys.common.Bobble;


/**
 * Created by Xc on 2015/6/6.
 */
public class ImageViewFactory {

    public static ImageView getImageView(Bobble color, Context context, int count) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(color.getPicID());

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        DisplayMetrics dm = new DisplayMetrics();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // always use the portrait dimensions to do the scaling calculations so we always get a portrait shaped
        // web dialog
        int width = metrics.widthPixels < metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels;

        params.height = (int) (width * 0.9 / count);
        params.width = (int) (width * 0.9 / count);

        imageView.setLayoutParams(params);
        imageView.setPadding(5, 5, 5, 5);
        return imageView;
    }


}
