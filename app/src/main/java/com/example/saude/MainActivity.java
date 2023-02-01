package com.example.saude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

     private EditText peso,altura;
     private Button butao,limpar;
     int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = findViewById(R.id.editPeso);
        altura =findViewById(R.id.editAltuta);
        butao = findViewById(R.id.calc);
        limpar=findViewById(R.id.limpar);
/*
       altura.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

               Toast.makeText(MainActivity.this, "inicio", Toast.LENGTH_SHORT).show();

               return true;
           }
       });
*/

         // Ação do butão para limpa os campos
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peso.setText("");
                altura.setText("");
            }
        });
        //Ação do butão calculor
        butao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método para realizar as operações do imc
                setaDados();
              //  verificarCamposVazios();
            /*
                Uri link = Uri.parse("https://www.globo.com");

                Intent sit = new Intent(Intent.ACTION_VIEW,link);

                if(sit.resolveActivity(getPackageManager())!=null){
                    startActivity(sit);
                }
               */
            }
        });
    }
     // Método para verificar os campos de textos
     public boolean verificarCamposVazios(){
         // variável para armazenar um valor lógico
         boolean campos=false;
         // Passando os componetes para Strings
         String h=String.valueOf(altura.getText().toString());
         String p=String.valueOf(peso.getText().toString());
         //Condição para saber se os dois campos estão vazios
         if(p.isEmpty() && h.isEmpty()) {
             //A mensagem que vai se exibida
             String msg = "Preenchar os campos!";
             // Método para informa mensagem
             Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
             // caso entre nesse if o valor lógico vai se verdadeiro
             campos = true;
         }else 
         // condição para saber se o campo de peso esta vázio
               if(p.isEmpty()) {
             //A mensagem que vai se exibida
             String  msg = "Preenchar o campo de Peso!";
             // Método para informa mensagem
             Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
             // Colocando um sinal dentro do campo para ser digitado
          peso.requestFocus();
          // caso entre nesse if o valor lógico vai se verdadeiro
          campos =true;
         }else if(h.isEmpty()){
             //A mensagem que vai se exibida
             String  msg = "Preenchar o campo de Altura!";
             // Método para informa mensagem
             Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
             // Colocando um sinal dentro do campo para ser digitado
             altura.requestFocus();
             // caso entre nesse if o valor lógico vai se verdadeiro
             campos=true;
         }
         // O retorno desse método vai se o valor lógico que estive na variável campos
         return campos;
     }
    // Método para realiza a operação do imc
    public void setaDados(){
             // Condição para saber se tem campos vazios
             if(verificarCamposVazios()){
               // caso o método verificarCamposVarios retorna true entra aqui
             }else {
                 // Caso os campos não esteja varios vai se processado todos esses algoritmos abaixo
                 DecimalFormat decimal = new DecimalFormat("#,##0.00");
                 String h=String.valueOf(altura.getText().toString());
                 String p=String.valueOf(peso.getText().toString());
                 // Convertendo um caract para outro tipo
                 String convertAltura=h.replace(",",".");
                 // Convertendo um caract para outro tipo
                 String convertPeso=p.replace(",",".");
                 // Passando os componentes para double
                 double ps=Double.parseDouble(convertPeso);
                 double alt=Double.parseDouble(convertAltura);
                 // variavel para fezar o calculor do imc
                 double imc=(ps/(alt*alt));
                 // Convertendo para casas decimais
                 String resultado=decimal.format(imc);
                 // variavel para armazenar as mensagens
                 String msg="";
                 if (imc < 18.5) {
                     msg = "Você está abaixo do normal!";
                     Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                 } else if (imc > 18.5 && imc <= 24.9) {
                     msg = "Parabéns você está em norma";
                     Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                 } else if (imc >= 25 && imc <= 29.9) {
                     msg = "Você está  sobrepeso";
                     Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                 } else if (imc >= 30 && imc <= 34.9) {
                     msg = "Você está com obesidade dde primeiro grau";
                     Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                 } else if (imc >= 35 && imc <= 39.9) {
                     msg = "Você está com obesidade de segundo grau";
                     Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                 } else if (imc >= 40) {
                     msg = "Você está com obesidade mórbida";
                     Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                 }
             }
    }

}