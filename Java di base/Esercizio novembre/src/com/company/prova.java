package com.company;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class prova {

    private static Map<String, Amico> mappaAmici = new HashMap<>();

    static Funzionalità funzionalità = new Funzionalità();

    static {

        Path locPath = FileSystems.getDefault().getPath("amici.dat");

        try (ObjectInputStream readFile = new ObjectInputStream(new BufferedInputStream(
                Files.newInputStream(locPath)))) {
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

        }
    }

}


