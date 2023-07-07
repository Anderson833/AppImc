package com.example.saude;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView totalImc;
     private EditText peso,altura;
     private Button butao,limpar;
     private  ConexaoBd conect;
     private  SQLiteDatabase BD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // criaBaseDeDados();
       //  criaTabela();
         //cadastro();
        conect = new ConexaoBd(this);
        conect.onCreate(BD);
        peso = findViewById(R.id.editPeso);
        altura =findViewById(R.id.editAltuta);
        butao = findViewById(R.id.calc);
        totalImc = findViewById(R.id.textResultado);
        limpar=findViewById(R.id.limpar);
        getSupportActionBar().hide();
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
           public void add(View view){
                Imc imc = new Imc();

               Dadosimc d = new Dadosimc();
               d.setCoisa(peso.getText().toString());
               imc.Salvar(d);
               //imc.getInformacao();
               Toast.makeText(MainActivity.this, conect.getMsg()+""+imc.getInformacao(), Toast.LENGTH_SHORT).show();
           }
            @Override
            public void onClick(View v) {
              totalImc.setText("");
                peso.setText("");
                altura.setText("");
               // whatsappp();
               //  add(v);
              // cadastro();
            }
        });
        //Ação do butão calculor
        butao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método para realizar as operações do imc
                setaDados();
              //  verificarCamposVazios();

            }
        });
    }

     SQLiteDatabase banco_bd;
    public void criaBaseDeDados(){

     banco_bd=openOrCreateDatabase("Banco_IMC",MODE_PRIVATE,null);
        Toast.makeText(MainActivity.this, "Banco criado com sucesso!", Toast.LENGTH_SHORT).show();

    }
    public  void criaTabela(){

        banco_bd.execSQL("CREATE TABLE IF NOT EXISTS COISA(id Integer primary key autoincrement," +
                "nome varchar(200))");
        Toast.makeText(MainActivity.this, "Tabela criada com Sucesso!", Toast.LENGTH_SHORT).show();

    }

    public  void cadastro(){
        banco_bd.execSQL("INSERT INTO COISA(NOME)VALUES('Anderson')");
        Toast.makeText(MainActivity.this, "Dados Salvo sucesso!", Toast.LENGTH_SHORT).show();

    }
    int nota=10;
    String fone="991958497",mensagem="Olá, De 1 á 10 quanto você daria? !"+nota+" Obrigado!";
   public void whatsappp(){

       String zap ="https://api.whatsapp.com/send/?phone=5584"+fone+"&text="+mensagem+"&type=phone_number&app_absent=0";
               Uri whatsapp = Uri.parse(zap);
                Intent tel = new Intent(Intent.ACTION_VIEW,whatsapp);
                startActivity(tel);

              /* Uri link = Uri.parse("https://www.globo.com");
                Intent sit = new Intent(Intent.ACTION_VIEW,link);

                if(sit.resolveActivity(getPackageManager())!=null){
                    startActivity(sit);
                }
     */
    }

     // Método para verificar os campos de textos
     public boolean verificarCamposVazios(){
         // variável para armazenar um valor lógico
         boolean campos=false;
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
    public void setaDados() {
        // Condição para saber se tem campos vazios
        if (verificarCamposVazios()) {
            // caso o método verificarCamposVarios retorna true entra aqui
        } else {
            // Caso os campos não esteja varios vai se processado todos esses algoritmos abaixo
            DecimalFormat decimal = new DecimalFormat("#,##0.00");
            String h = String.valueOf(altura.getText().toString());
            String p = String.valueOf(peso.getText().toString());
            // Convertendo um caract para outro tipo
            String convertAltura = h.replace(",", ".");
            // Convertendo um caract para outro tipo
            String convertPeso = p.replace(",", ".");
            // Passando os componentes para double
            double ps = Double.parseDouble(convertPeso);
            double alt = Double.parseDouble(convertAltura);
            // variavel para fezar o calculor do imc
            double imc = (ps / (alt * alt));
            // Convertendo para casas decimais
            String resultado = decimal.format(imc);
                // variavel para armazenar as mensagens
                String msg = "";
                if (imc < 18.5) {
                    msg = "Você está abaixo do normal!";
                    Toast.makeText(this, msg + ". Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                 totalImc.setText(" Resultado do seu imc "+resultado);
                } else if (imc > 18.5 && imc <= 24.9) {
                    msg = "Parabéns você está em normal";
                    Toast.makeText(this, msg + ". Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                    totalImc.setText(" Resultado do seu imc "+resultado);
                } else if (imc >= 25 && imc <= 29.9) {
                    msg = "Você está  sobrepeso";
                    totalImc.setText(" Resultado do seu imc "+resultado);
                } else if (imc >= 30 && imc <= 34.9) {
                    msg = "Você está com obesidade dde primeiro grau";
                    Toast.makeText(this, msg + ". Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                    totalImc.setText(" Resultado do seu imc "+resultado);
                } else if (imc >= 35 && imc <= 39.9) {
                    msg = "Você está com obesidade de segundo grau";
                    Toast.makeText(this, msg + ". Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                    totalImc.setText(" Resultado do seu imc "+resultado);
                } else if (imc >= 40) {
                    msg = "Você está com obesidade mórbida";
                    Toast.makeText(this, msg + ". Seu imc é " + resultado, Toast.LENGTH_LONG).show();
                    totalImc.setText(" Resultado do seu imc "+resultado);
                }
            }
        }


    }
