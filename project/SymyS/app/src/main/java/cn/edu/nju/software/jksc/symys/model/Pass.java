package cn.edu.nju.software.jksc.symys.model;

import android.content.Context;

/**
 * Created by jingliu on 15/6/26.
 */
public class Pass {
    private static final int BOARD_SUM=4;
    private static final int DEGREE_SUM=3;
    private int isPass[][]=new int[BOARD_SUM][DEGREE_SUM];
    private char splitChar='$';
    private String fileName="pass";
    private FileHelper fileHelper;
    private Context ctx;
    private int currentMaxDone=-1;

    public Pass(Context ctx){
        this.ctx=ctx;
        fileHelper=new FileHelper(this.ctx,fileName);
        String passStr=fileHelper.load();
        initPass(passStr);
    }

    private void initPass(String fileStr){
        if(fileStr==null||fileStr.length()!=BOARD_SUM*DEGREE_SUM+BOARD_SUM-1){
            for(int i=0;i<BOARD_SUM;i++){
                for(int j=0;j<DEGREE_SUM;j++){
                    isPass[i][j]=0;
                }
            }
        }else{
            String[] passesStr=fileStr.split(String.valueOf(splitChar));
            for(int i=0;i<passesStr.length;i++){
                String curPassStr=passesStr[i];
                for(int j=0;j<curPassStr.length();j++){
                    char s=curPassStr.charAt(j);
                    int curState=Integer.getInteger(String.valueOf(s),0);
                    isPass[i][j]=curState;
                    if(isPass[i][j]==1){
                        currentMaxDone=DEGREE_SUM*i+j;
                    }
                }
            }

        }
    }

    public void setPassDone(int i,int j){
        this.isPass[i][j]=1;
        if(i*DEGREE_SUM+j>currentMaxDone){
            this.currentMaxDone=i*DEGREE_SUM+j;
        }
    }
    public int getCurrentMaxDone(){
        return this.currentMaxDone;
    }
    public int getPassStatus(int i,int j){
       return isPass[i][j];
    }

    public String toString(){
        StringBuffer sbf=new StringBuffer();
        for(int i=0;i<BOARD_SUM;i++){
            for(int j=0;j<DEGREE_SUM;j++){
                sbf.append(isPass[i][j]);
            }
            if(i<BOARD_SUM-1){
                sbf.append(splitChar);
            }
        }
        return sbf.toString();
    }


    public void save(){
        if(fileHelper==null){
            fileHelper=new FileHelper(this.ctx,fileName);
        }
        System.out.println(this.toString());
        fileHelper.save(this.toString());
    }

}
