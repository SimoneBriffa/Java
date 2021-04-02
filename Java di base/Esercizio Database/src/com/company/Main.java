package com.company;

public class Main {

    static Funzionalità funzionalità = new Funzionalità();

    public static void main(String[] args) {

        funzionalità.open();
        //for(Canzone canzone: funzionalità.scaricaCanzoni())
         //   System.out.println(canzone.toString());

        funzionalità.cercaCanzone("2000 Blues");


    }
}
