package com.example.saude;

public class CalcImc {

    public static void main(String[] args) {


    }
    public static double xdoPeso(double peso,double tamanho) {
        int cont=0;
        double qtdKl =0;
        for (double i = peso; i > 0; i--) {
           double imc=i/(tamanho * tamanho);
            if (imc < 18.5) {
                //  msg = "Você está abaixo do normal!";
                //  Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
            } else if (imc > 18.5 && imc <= 24.9) {
                //   msg = "Parabéns você está em normal";
                //  Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
            } else if (imc >= 25 && imc <= 29.9) {
                cont++;
                qtdKl = cont;
                //  msg = "Você está  sobrepeso";
                //  Toast.makeText(this, msg + " Seu imc é " + resultado + " total diminuir " + qtdPeso, Toast.LENGTH_LONG).show();
            } else if (imc >= 30 && imc <= 34.9) {
                //  msg = "Você está com obesidade dde primeiro grau";
                // Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
            } else if (imc >= 35 && imc <= 39.9) {
                //   msg = "Você está com obesidade de segundo grau";
                //   Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
            } else if (imc >= 40) {
                //  msg = "Você está com obesidade mórbida";
                // Toast.makeText(this, msg + " Seu imc é " + resultado, Toast.LENGTH_LONG).show();
            }
        }
        return qtdKl;
    }
}
