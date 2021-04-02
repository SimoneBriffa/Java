package com.timbuchalka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Locations locations = new Locations();

    public static void main(String[] args) throws IOException {
        /*Normalmente, si tende a prendere un file e a leggerlo da cima a fondo senza saltare niente.
        In applicazioni reali, però, accade molto spesso che i file contengano un numero esagerato di informazioni al punto
        tale da rendere impensabile leggerlo da cima a fondo per cercare una specifica informazione. Quì entra in gioco
        la modalità di lettura Random Access File. Inoltre c'è da considerare che spesso i file non vengono letti dal disco
        perchè immagazzinare troppe informazioni non sarebbe efficiente, ragion per cui questo tipo di operazione vanno fatte
        con i database (per ora facciamolo meccanicamente)
        Bisogna considerare di muovere il puntatore al file in una determinata posizione e da lì scorrerlo finchè ci interessa.
        Per come è organizzato il file in questo esempio avremo:
        1. I primi quattro byte (int) conterranno il numero delle locations (bytes 0-3)
        2. I successivi quattro byte conterranno (...) the start offset of the locations section (bytes4-7)
        3. La successiva sezione del file conterrà l'indice (lungo 1692 byte)
        4. La sezione finale del file conterrà i dati

         */

	    Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        Location currentLocation = locations.getLocation(1);
        while(true) {
            System.out.println(currentLocation.getDescription());

            if(currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction));

            } else {
                System.out.println("You cannot go in that direction");
            }
        }

        locations.close();

    }
}
