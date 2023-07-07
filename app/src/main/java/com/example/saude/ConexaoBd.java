package com.example.saude;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoBd extends SQLiteOpenHelper {
    private static final String name="Banco_imc";
    private static final int version =1;
    private String msg;
    public void setMsg(String MSG) {
        this.msg = MSG;
    }

    public String getMsg(){
        return msg;
    }


    public ConexaoBd(Context context){

        super(context,name,null,version);
    }


    @Override
    public  void onCreate(SQLiteDatabase db) {
        try {



        }catch (Exception e){
            e.printStackTrace();
            setMsg("Tabela não criada");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
