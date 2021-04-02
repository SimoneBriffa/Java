package com.timbuchalka;

import java.io.*;
import java.util.*;

/**
 * Created by timbuchalka on 2/04/2016.
 */
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {
        try(BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
            //N.B. mettere tra parentesi del try il Writer è un'operazione che si chiama "Try With Resources" e ci consente
            //di fare a meno del blocco catch
            BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions.txt"))) {
            for(Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for(String direction : location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q"))
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }
            }
        }


        /**VERSIONE ESTESA (non appare "throws IOException accanto la dichiarazione del main)
        * La prima semplificazione sta appunto nell'aggiungere throws IOException al main.
        * Il senso di throws è "informare" un dato metodo che ciò che ha al suo interno
        * potrebbe lanciare determinate eccezioni.
        * Una volta aggiunto, tutti i catch(IOException e) possono essere eliminati in quanto
        * ridondanti.
        * La seconda sta nel fatto che dichiarando il file all'interno di try, viene chiuso
        * automaticamente*/

      /*  FileWriter locFile = null;

        try {
            locFile = new FileWriter("locations.txt");
            for (Location location : locations.values()) {
                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
            }
             locFile.close();
        }catch(IOException e){
            System.out.println("In catch block");
            e.printStackTrace();
        }
            *//*se lasciamo la chiusura del file dove era prima, potrebbe accadere che verificandosi
            effettivamente l'eccezione, il comando di chiusura verrebbe saltato, lasciando il file
            aperto. Allora è meglio metterlo alla fine nel blocco "finally" (ma a sua volta la chiusura
            del file richiedere un blocco try/catch *//*

        finally{
            System.out.println("In finally block");
            try {
                if (locFile != null)
                    locFile.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }*/
    }

    static {  //dichiarando questa roba come STATIC accade che il medoto è eseguito PRIMA del main, quindi il file è
        //elaborato ancora prima che il main venga lanciato !!!

        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")))){
            //si può anche fare a meno di BufferedReader, ma è più efficiente
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        /**Versione estesa*/

        /*Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }*/

        // Now read the exits
        //Non serve il blocco finally perchè la classe Scanner chiude automaticamente ciò che apre
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String direction = scanner.next();
                scanner.skip(scanner.delimiter());
                String dest = scanner.nextLine();
                int destination = Integer.parseInt(dest);

                /**OPPURE*/

                /*String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);*/

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        }


//        Map<String, Integer> tempExit = new HashMap<String, Integer>();
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest",tempExit));

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
