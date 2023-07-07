package com.example.saude;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class Imc implements Serializable {

    private  ConexaoBd conexao;
    private SQLiteDatabase banco;

    private  String informacao;

    public void SetInformacao (String infor){
        this.informacao=infor;
    }
    public String getInformacao(){
        return informacao;
    }

    public Imc() {
        Context context = null;
        conexao = new ConexaoBd(context);
        banco = conexao.getWritableDatabase();

    }


    public long Salvar(Dadosimc imc){
        long bd=0;

        try {
            ContentValues valores = new ContentValues();
             valores.put("nome",imc.getCoisa());
              bd= banco.insert("COISA",null,valores);
          if(bd>0){
                SetInformacao("Dados Salvos");
             // ConexaoBd = new ConexaoBd(context context).setMsg();
          }

        }catch (Exception e){
            e.printStackTrace();
            SetInformacao("Error ao salvar os dados");
        }
        return bd;
    }


}
