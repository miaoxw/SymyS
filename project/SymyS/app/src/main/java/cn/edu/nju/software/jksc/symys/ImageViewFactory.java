package cn.edu.nju.software.jksc.symys;

/**
 * Created by Xc on 2015/6/21.
 */

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TableRow;

import cn.edu.nju.software.jksc.symys.common.Bobble;


/**
 * Created by Xc on 2015/6/6.
 */
public class ImageViewFactory {

    public static ImageView getImageView(Bobble color, Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(color.getPicID());

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.height = 100;
        params.width = params.height;
        imageView.setLayoutParams(params);
        imageView.setTag(color);
        imageView.setPadding(5, 5, 5, 5);
        return imageView;
    }


}
