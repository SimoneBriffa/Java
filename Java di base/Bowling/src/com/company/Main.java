package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main{

    public static List<Impiegato> impiegati = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

    static {

        try (ObjectInputStream readFile = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream("file.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Impiegato impiegato = (Impiegato) readFile.readObject();
                    impiegati.add(impiegato);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: ClassNotFoundException");
        } catch (IOException e) {
            System.out.println("Errore: IOException");

        }
    }

    public static void main(String[] args) throws IOException {

        boolean quit = false;
        int choice = 0;

        menu();

        while (!quit) {

            choice = scanner.nextInt();

            switch(choice){

                case 1:
                    aggiungiImpiegato();
                    break;

                case 2:
                    visualizzaImpiegati(impiegati);
                    break;

                /*case 3:
                    eliminaImpiegato();
                    break;*/

                case 4:
                    cercaImpiegato();
                    break;

                /*case 5:
                    modificaImpiegato();
                    break;*/

                case 6:
                    scriviSuFile(impiegati);
                    quit = true;
            }

        }

    }

    public static void aggiungiImpiegato() throws IOException {

        scanner.nextLine();
        System.out.print("Inserisci nome: -> ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci cognome: -> ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci mansione: -> ");
        String mansione = scanner.nextLine();
        Impiegato nuovoImpiegato = new Impiegato(nome, cognome, mansione);
        impiegati.add(nuovoImpiegato);
        System.out.println("Impiegato aggiunto con successo");

    }

    public static void cercaImpiegato() {
        scanner.nextLine();
        System.out.print("Inserisci nome: -> ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci cognome: -> ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci mansione: -> ");
        String mansione = scanner.nextLine();
        Impiegato impiegato = new Impiegato(nome, cognome, mansione);
                int trovato = Collections.binarySearch(impiegati, impiegato, Impiegato.IMPIEGATO_COMPARATOR);
                if (trovato >= 0)
                    System.out.println("Impiegato trovato: " + impiegato.toString());
                else
                    System.out.println("Impiegato non trovato");
            }


  /* public static void eliminaImpiegato() throws IOException{

        scanner.nextLine();
       System.out.print("Inserire nome: -> ");
       String nome = scanner.nextLine();
       System.out.print("Inserire cognome: -> ");
       String cognome = scanner.nextLine();
       System.out.print("Inserire mansione: -> ");
       String mansione = scanner.nextLine();
       Impiegato impiegatoDaEliminare = new Impiegato(nome, cognome, mansione);

       if(impiegati.contains(impiegatoDaEliminare)){
           impiegati.remove(impiegatoDaEliminare);

           try(BufferedWriter bw = new BufferedWriter(new FileWriter("file.dat"))){
               bw.flush();
               for(Impiegato impiegatoCorrente: impiegati){
                   bw.write(impiegatoCorrente.nome+","+impiegatoCorrente.cognome+","+impiegatoCorrente.mansione);
               } System.out.println("Impiegato eliminato");
           }catch(IOException e){
               System.out.println("Errore");
           }
       }
       else
           System.out.println("Impiegato inesistente");

   }*/

   public static void scriviSuFile(List<Impiegato> lista) throws IOException {

       try (ObjectOutputStream writeFile = new ObjectOutputStream(new BufferedOutputStream(
               new FileOutputStream("file.dat")))) {
           for(Impiegato impiegato: lista) {
               writeFile.writeObject(impiegato);
           }
       }catch (IOException e){
           System.out.println("Errore: " + e.getMessage());
       }
   }


    /**

    public static void caricaFile() {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("file.dat")))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String nome = scanner.next();
                scanner.skip(scanner.delimiter());
                String cognome = scanner.next();
                scanner.skip(scanner.delimiter());
                String mansione = scanner.nextLine();
                Impiegato impiegato = new Impiegato(nome, cognome, mansione);
                impiegati.add(impiegato);
            }
        } catch (IOException e) {
            System.out.println("Errore");
        }finally {
            impiegati.sort(Impiegato.IMPIEGATO_COMPARATOR);
        }
    }


    public static void scriviSuFile(String nome, String cognome, String mansione) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("file.dat", true))) {
            bw.write("\n" + nome + "," + cognome + "," + mansione);
            System.out.println("Nuovo impiegato registrato");
        } catch (IOException e) {
            System.out.println("Errore");
        }

    }

     */

    public static void visualizzaImpiegati(List<Impiegato> lista) {

        if (lista.size() == 0)
            System.out.println("Non ci sono impiegati registrati");
        else {
            impiegati.sort(Impiegato.IMPIEGATO_COMPARATOR);
            for (Impiegato impiegatoCorrente : lista) {
                System.out.println(impiegatoCorrente.toString());
            }
        }
    }

    public static void menu() {
        System.out.println("1 - Per aggiungere un impiegato");
        System.out.println("2 - Per visualizzare la lista degli impiegati");
        System.out.println("3 - Per eliminare un impiegato");
        System.out.println("4 - Per ricercare un impiegato");
        System.out.println("5 - Per modificare un impiegato");
        System.out.println("6 - Per uscire");
        System.out.print("\t\t scelta: -> ");
    }

}






