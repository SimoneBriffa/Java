package com.company;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static Funzionalità funzionalità = new Funzionalità();

    static {

        Path locPath = FileSystems.getDefault().getPath("amici.dat");

        try (ObjectInputStream readFile = new ObjectInputStream(new BufferedInputStream(
                Files.newInputStream(locPath)))){
            boolean eof = false;
            while (!eof) {
                try {
                    Amico amico = (Amico) readFile.readObject();
                    funzionalità.amici.add(amico);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        funzionalità.caricaDaFileSuMappa();

        for(String key: funzionalità.mappaAmici.keySet()){
            System.out.println(key + "; " + funzionalità.mappaAmici.get(key));
        }

        Path locPath = FileSystems.getDefault().getPath("amici_chiavi.txt");

    }

        /*Thread visualizzaDescrizione = new Thread(new Runnable() {
            @Override
            public void run() {
                funzionalità.visualizzaDescrizione("claudia", "ferro", "ingegnere elettronico");
            }
        });


        Scanner scanner = new Scanner(System.in);

        System.out.println("In attesa di conferma...");
        byte start = scanner.nextByte();

        if(start == 1)
            visualizzaDescrizione.start();

    }*/

    }

