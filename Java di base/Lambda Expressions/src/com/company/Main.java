package com.company;

//Le lambdas facilitano il lavoro con le interfacce che offrono un solo metodo, per esempio Runnable

public class Main {

    public static void main(String[] args) {

        new Thread(new CodeTuRun()).start();

        //oppure, equivalente, utilizzando una "classe anonima"

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from the Runnable");
            }
        }).start();

        //In questo semplice esempio, vogliamo semplificarlo ulteriormente semplicemente
        //passando la S.OUT direttamente alla dichiarazione del Thread

        new Thread(() -> System.out.println("Printing from the Runnable")).start();

        /*distinguiamo tre parti: la lista argomenti, la freccia e il corpo.
        In questo caso la lista argomenti è vuota e il corpo è la S.OUT
         */

    }
}

class CodeTuRun implements Runnable{

    @Override
    public void run() {
        System.out.println("Printing from the Runnable");
    }



}
