package com.company;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Funzionalità {

    List<Amico> amici = new ArrayList<>();
    Map<String, Amico> mappaAmici = new HashMap<>();

    public String primaLetteraMaiuscola(String stringa){
        return stringa.substring(0, 1).toUpperCase() + stringa.substring(1);
    }

    public void aggiungiAmico(String nome, String cognome, String lavoro){

            String nomeModificato = primaLetteraMaiuscola(nome);
            String cognomeModificato = primaLetteraMaiuscola(cognome);
            String lavoroModificato = primaLetteraMaiuscola(lavoro);

        amici.add(new Amico(nomeModificato, cognomeModificato, lavoroModificato));
    }

    public void stampaLista(){

        amici.sort(
                (amico1, amico2) -> amico1.getCognome().compareTo(amico2.getCognome())
        );

        for(Amico amico: amici){
            System.out.println(amico.toString());
        }
    }

    public boolean cercaAmico(String nome, String cognome, String lavoro){

        String nomeModificato = nome.substring(0, 1).toUpperCase() + nome.substring(1);
        String cognomeModificato = cognome.substring(0, 1).toUpperCase() + cognome.substring(1);
        String lavoroModificato = lavoro.substring(0,1).toUpperCase() + lavoro.substring(1);

        Amico amico = new Amico(nomeModificato, cognomeModificato, lavoroModificato);

        return amici.contains(amico);

    }

    public void aggiungiDescrizione() {

        Path percorsoDescrizioni = FileSystems.getDefault().getPath("descrizioni");

        if (!Files.exists(percorsoDescrizioni)) {
            try {
                Files.createDirectory(percorsoDescrizioni);
            } catch (IOException e) {
                System.out.println("Problema durante la creazione della directory\n\n");
                e.getMessage();
                e.printStackTrace();
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digitare, nell'ordine, nome, cognome e lavoro dell'interessato/a");
        String nome = scanner.nextLine();
        String cognome = scanner.nextLine();
        String lavoro = scanner.nextLine();

        if (!cercaAmico(nome, cognome, lavoro)) {
            System.out.println("Non esistente");
            return;
        }

        Path percorso = FileSystems.getDefault().getPath("descrizioni", nome + "_" + cognome + ".txt");

        System.out.println("Aggiungi descrizione");

        StringBuilder descrizione = new StringBuilder();
        boolean fine = false;

        while (!fine) {
            String frase = scanner.nextLine();
            if (!frase.equals("0"))
                descrizione.append(" ").append(frase).append("\n");
            else fine = true;
        }

        try (BufferedWriter bw = Files.newBufferedWriter(percorso)) {

            bw.write(String.valueOf(descrizione));

        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }

    }

    public void continuaDescrizione() {

        Path percorsoDescrizioni = FileSystems.getDefault().getPath("descrizioni");

        if (!Files.exists(percorsoDescrizioni)) {
            try {
                Files.createDirectory(percorsoDescrizioni);
            } catch (IOException e) {
                System.out.println("Problema durante la creazione della directory\n\n");
                e.getMessage();
                e.printStackTrace();
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digitare, nell'ordine, nome, cognome e lavoro dell'interessato/a");
        String nome = scanner.nextLine();
        String cognome = scanner.nextLine();
        String lavoro = scanner.nextLine();

        if (!cercaAmico(nome, cognome, lavoro)) {
            System.out.println("Non esistente");
            return;
        }

        Path percorso = FileSystems.getDefault().getPath("descrizioni", nome + "_" + cognome + ".txt");

        if (Files.exists(percorso)) {

            System.out.println("Inserisci descrizione");

            StringBuilder descrizione = new StringBuilder();
            boolean fine = false;

            while (!fine) {
                String frase = scanner.nextLine();
                if (!frase.equals("0"))
                    descrizione.append(" ").append(frase).append("\n");
                else fine = true;
            }

            try (BufferedWriter bw = Files.newBufferedWriter(percorso, StandardOpenOption.APPEND)) {

                bw.write(String.valueOf(descrizione));

            } catch (IOException e) {
                e.getMessage();
                e.printStackTrace();
            }

        } else System.out.println("Descrizione non esistente");
    }



        public void visualizzaDescrizione(String nome, String cognome, String lavoro) {

            if (cercaAmico(nome, cognome, lavoro)) {

                try {
                    Path percorso = FileSystems.getDefault().getPath("descrizioni", nome + "_" + cognome + ".txt");
                    if (Files.exists(percorso)) {
                        for (String riga : Files.readAllLines(percorso)) {
                            System.out.println(riga);
                        }
                    } else
                        System.out.println("Non esiste descrizione per questa persona.");

                } catch (IOException e) {
                    e.getMessage();
                    e.printStackTrace();
                }
            } else System.out.println("Non esiste questa persona.");

        }

        public void caricaDaFileSuMappa(){

            Path locPath = FileSystems.getDefault().getPath("amici_chiavi.txt");

            try{
                for (String riga : Files.readAllLines(locPath)) {
                    String[] pieces = riga.split(",");
                    String[] piecesOfValue = pieces[1].split("-");
                    Amico amico = new Amico(piecesOfValue[0], piecesOfValue[1], piecesOfValue[2]);
                    mappaAmici.put(pieces[0], amico);
                }

            }catch(IOException e){
                e.getMessage();
                e.printStackTrace();
            }

        }

    public void spegni(){

        Path locPath = FileSystems.getDefault().getPath("amici.dat");

        try(ObjectOutputStream fileDiSalvataggio = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(locPath)))) {

            for(Amico amico: amici){
                fileDiSalvataggio.writeObject(amico);
            }

        }catch(IOException e){
            e.getMessage();
            e.printStackTrace();
        }

    }

}
