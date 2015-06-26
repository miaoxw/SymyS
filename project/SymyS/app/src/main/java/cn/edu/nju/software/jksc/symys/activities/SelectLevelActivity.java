package cn.edu.nju.software.jksc.symys.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;

import cn.edu.nju.software.jksc.symys.R;
import cn.edu.nju.software.jksc.symys.model.Pass;


public class SelectLevelActivity extends Activity {
    private static final int BOARD_SUM=4;
    private static final int DEGREE_SUM=3;
    private Button levelButtons[][]=new Button[BOARD_SUM][DEGREE_SUM];
    private int i_listener=0;
    private int j_listener=0;
    private Pass pass;
    private int currentSelectPass[]={0,0};
    private Button startButton;
    private int boardSize=3;
    private int axisSum=1;
    private int colorSum=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_level);
        pass=new Pass(this);
        initButtons();
        setButtonListeners();
        startButton=(Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectLevelActivity.this,GamePanel.class);
                Log.d("boardSize",""+boardSize);
                Log.d("axisSum",""+axisSum);
                Log.d("colorSum",""+colorSum);
                intent.putExtra("boardSize",boardSize);
                intent.putExtra("axisSum",axisSum);
                intent.putExtra("colorSum",colorSum);
                startActivity(intent);
            }
        });
    }

    private void initButtons(){
        // board:size=3;
        levelButtons[0][0]=(Button)findViewById(R.id.button1);
        levelButtons[0][1]=(Button)findViewById(R.id.button2);
        levelButtons[0][2]=(Button)findViewById(R.id.button3);
        // board:size=4;
        levelButtons[1][0]=(Button)findViewById(R.id.button4);
        levelButtons[1][1]=(Button)findViewById(R.id.button5);
        levelButtons[1][2]=(Button)findViewById(R.id.button6);
        // board:size=5;
        levelButtons[2][0]=(Button)findViewById(R.id.button7);
        levelButtons[2][1]=(Button)findViewById(R.id.button8);
        levelButtons[2][2]=(Button)findViewById(R.id.button9);
        // board:size=6;
        levelButtons[3][0]=(Button)findViewById(R.id.button10);
        levelButtons[3][1]=(Button)findViewById(R.id.button11);
        levelButtons[3][2]=(Button)findViewById(R.id.button12);


        for(int i=0;i<BOARD_SUM;i++){
            for(int j=0;j<DEGREE_SUM;j++){
                if(pass.getPassStatus(i,j)==1){
                    levelButtons[i][j].setBackgroundResource(R.drawable.pass_done);
                }else{
                    levelButtons[i][j].setBackgroundResource(R.drawable.clr_normal);
                }
            }
        }
    }

    private void setButtonListeners(){
        for(i_listener =0; i_listener <BOARD_SUM; i_listener++){
            for(j_listener =0; j_listener <DEGREE_SUM; j_listener++){
                final Button curButton=levelButtons[i_listener][j_listener];
                curButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Integer> indexes=findButtonIndex(curButton);
                        int i=indexes.get(0);
                        int j=indexes.get(1);
                        curButton.setBackgroundResource(R.drawable.clr_pressed);
                        if(j==0){
                            int boardSizeArray[]={3,4,5,6};
                            boardSize=boardSizeArray[i];
                        }if(j==1){
                            int axisSumArray[]={1,2,4};
                            axisSum=axisSumArray[i];
                        }if(j==2){
                            int colorSumArray[]={3,4};
                            colorSum=colorSumArray[i-1];
                        }
                    }
                });
            }
        }
    }

    private ArrayList<Integer> findButtonIndex(Button bt){
        ArrayList<Integer> indexs=new ArrayList<>();
        for(int i=0;i<BOARD_SUM;i++){
            for(int j=0;j<DEGREE_SUM;j++){
                if(levelButtons[i][j].equals(bt)){
                    indexs.add(i);
                    indexs.add(j);
                }
            }
        }
        return indexs;
    }

}
