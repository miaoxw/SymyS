package cn.edu.nju.software.jksc.symys.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by jingliu on 15/6/26.
 */
public class FileHelper {
    private String fileName;
    private Context ctx;
    FileOutputStream out;
    BufferedWriter writer;
    FileInputStream in;
    BufferedReader reader;
    public FileHelper(Context ctx,String fileName){
        this.fileName=fileName;
        this.ctx=ctx;
    }
    public void save(String text){
        try{
            out=ctx.openFileOutput(fileName,Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public String load(){
        StringBuffer sbf=new StringBuffer();
        try{
            in=ctx.openFileInput(fileName);
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=reader.readLine())!=null){
                sbf.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return sbf.toString();
    }
}

