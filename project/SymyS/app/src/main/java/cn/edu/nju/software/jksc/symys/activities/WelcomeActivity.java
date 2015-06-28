package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import cn.edu.nju.software.jksc.symys.R;

/**
 * Created by norvins on 15/6/29.
 */
public class WelcomeActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        welcomeUI();
    }

    public void welcomeUI(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2500);
                    Message message = new Message();
                    welHandler.sendMessage(message);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler welHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            welcomeFunction();
        }
    };

    public void welcomeFunction(){
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this,MainMenuActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();
    }
}
