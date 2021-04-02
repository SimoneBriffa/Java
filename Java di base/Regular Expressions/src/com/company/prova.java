package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class prova {

    public static void main(String[] args) {

       /* //   ^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"
        //  ^(["I"]{1}[ ]{1}[a-z]{4}[ ]{1}["a"]{1}[ ]{1}[a-z]{4}["."]{1})$
        System.out.println("I want a bike.".matches("^([\"I\"]{1}[ ]{1}[a-z]{4}[ ]{1}[\"a\"]{1}[ ]{1}[a-z]{4}[\".\"]{1})$"));


        //Fare in modo che "I want a bike." e "I want a ball." risultino in matches.

        String string1 = "I want a \\w+.";
        String string2 = "I want a ball.";

        System.out.println(string2.matches(string1));

        //oppure

        String string = "I want a (bike|ball).";
        System.out.println(string1.matches(string));
        System.out.println(string2.matches(string));

        //Facciamo la stessa cosa con le classi Pattern e Matcher

        String regExp3 = "I want a \\w+.";
        Pattern pattern = Pattern.compile(regExp3);
        Matcher matcher = pattern.matcher(string2);
        System.out.println(matcher.matches());

        //Sostituire tutti gli spazi bianchi con un underscore

        String challenge4 = "Replace all blanks with underscores";
        System.out.println(challenge4.replaceAll("\\s", "_"));
        //oppure più semplicemente
        System.out.println(challenge4.replaceAll(" ", "_"));

        //Verificare la corrispondenza con la seguente stringa

        String challenge5 = "aaabccccccccdddefffg";
        System.out.println(challenge5.matches("^([a]{3}[b][c]{8}[d]{3}[e][f]{3}[g])$"));
        //più semplicemente
        System.out.println(challenge5.matches("^a{3}bc{8}d{3}ef{3}g$"));
        //oppure
        System.out.println(challenge5.matches("[abcdefg]+"));
        System.out.println(challenge5.matches("[a-g]+"));
        System.out.println(challenge5.replaceAll("a{3}bc{8}d{3}ef{3}g$", "REPLACED"));

        //Verificare la corrispondenza

        System.out.println("Abcd.135".matches("^((?i)[a-z]{4}.[0-9]{3}$)"));*/

        //Verificare la stessa corrispondenza se ripetuta

        String challenge8 = "abcd.123efgh.456ilmn.789";
        Pattern pattern2 = Pattern.compile("^[A-Za-z]+\\.(\\d+)");
        Matcher matcher2 = pattern2.matcher(challenge8);
        while(matcher2.find()){
            System.out.println("Occurrence: " + matcher2.group(1));
        }

        //----------------------------

        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern9 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
        Matcher matcher9 = pattern9.matcher(challenge9);
        while(matcher9.find()){
            System.out.println("Occurence: Start -> " + matcher9.start(1) + " | " + matcher9.group(1) + " | End -> " + matcher9.end(1));
        }

        //Estrarre quanto dentro le parentesi graffe

        String challenge11 = "{0, 2},{0, 5},{1, 3},{2, 4}";
        Pattern pattern11 = Pattern.compile("[{](.+?)[}]");
        // ( ) specifica un gruppo; . specifica qualsiasi carattere; + specifica almeno un carattere
        // se rimuoviamo ? ci darà un solo risultato contenente tutto ciò che è dentro la prima { e l'ultima } in assoluto
        Matcher matcher11 = pattern11.matcher(challenge11);

        while(matcher11.find()){
            System.out.println(matcher11.group(1));
        }

        System.out.println("+++++++++++++++++");

        //Ignora tutto ciò che non è numero

        String challenge12 = "{0, 2},{0, 5},{1, 3},{2, 4}, {x, y}, {4, 1}";
        Pattern pattern12 = Pattern.compile("[{](\\d+, \\d+)[}]");
        // ( ) specifica un gruppo; . specifica qualsiasi carattere; + specifica almeno un carattere
        // se rimuoviamo ? ci darà un solo risultato contenente tutto ciò che è dentro la prima { e l'ultima } in assoluto
        Matcher matcher12 = pattern12.matcher(challenge12);

        while(matcher12.find()){
            System.out.println(matcher12.group(1));
        }

        //Verificare che la stringa corrisponda a 5 numeri

        String challenge13 = "11111";
        System.out.println(challenge13.matches("\\d{5}"));


        //Eventualmente sono ammessi altri cinque numeri separati da un trattino

        String challenge14 = "11111-11111";
        System.out.println(challenge14.matches("^\\d{5}(-\\d{5})?$"));

    }



}
