package cn.edu.nju.software.jksc.symys.activities;

/**
 * Created by jingliu on 15/6/28.
 */

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.edu.nju.software.jksc.symys.R;

public class ChooseButtonFactory {
    public static ArrayList<Button> getButtonList(View view) {
        ArrayList<Button> buttons = new ArrayList<>();
        final ArrayList<Integer> buttonIds = getButtonIds();
        for (int i = 0; i < buttonIds.size(); i++) {
            Button btn = (Button) view.findViewById(buttonIds.get(i));
            buttons.add(btn);
        }
        return buttons;
    }

    public static ArrayList<Integer> getButtonIds() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(new Integer(R.id.button1));
        list.add(new Integer(R.id.button2));
        list.add(new Integer(R.id.button3));
        list.add(new Integer(R.id.button4));
        list.add(new Integer(R.id.button5));
        list.add(new Integer(R.id.button6));
        list.add(new Integer(R.id.button7));
        list.add(new Integer(R.id.button8));
        list.add(new Integer(R.id.button9));
        return list;
    }
}
