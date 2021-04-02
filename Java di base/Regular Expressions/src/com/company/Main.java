package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        /*String string = "I am a string. Yes, I am.";
        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphanumeric.replaceAll(".", "y"));

        System.out.println(alphanumeric.replaceAll("abcDeee", "YYY"));

        String secondString = "abcDeeeF12GhhiabcDeeeiiijkl99zabcDeee";
        System.out.println(secondString.replaceAll("abcDeee$", "ciaombare"));

        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));

        System.out.println(alphanumeric.replaceAll("[^ej]", "X"));

        System.out.println(alphanumeric.replaceAll("(?i)[a-f3-8]", "X"));

        System.out.println("Ciao mbare come va \t tutto ok".replaceAll("\\s", "X"));

        System.out.println("Ciao mbare come va \t tutto ok".replaceAll("\\W", "X"));

        System.out.println("Ciao mbare come va \t tutto ok 1234".replaceAll("\\B", "X"));

        System.out.println("suconeeeeeesgdhtht".replaceAll("ne{2,5}", "X"));*/



        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub Heading</h2>");
        htmlText.append("<p>molti suconi molti pomponi</p>");
        htmlText.append("<p>altri suconi altri pomponi ma con burro</p>");
        htmlText.append("<h2>Riassunto</h2>");
        htmlText.append("<p>suconi</p>");

       /* String h2Pattern = "<h2>"; //il punto significa tutti i caratteri, * significa 0 o più
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        int count = 0;

        matcher.reset();
        while(matcher.find()){
            count++;
            System.out.println("occurence " + count + " : " + matcher.start() +
                    " to " + matcher.end());
        }*/

        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while(groupMatcher.find()){
            System.out.println("Occurence: " + groupMatcher.group(1));
        }


        //Le regular expression sono spesso usate per verificare la velidità dell'input dell'utente

        String phone1 = "1234567890"; //non valido
        String phone2 = "(123) 456-7890"; //valido
        String phone3 = "123 456-7890"; //non valido
        String phone4 = "(123)456-7890"; //non valido

        System.out.println("phone1 = " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone2 = " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone3 = " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone4 = " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        /*
        Significa:

        ^([\(]{1}   deve cominciare (^) con una ({1}) parentesi
        [0-9]{3}    poi tre numeri
        [\)]{1}     chiusa parentesi
        [ ]{1}      uno spazio
        [0-9]{3}    tre numeri
        [\-]{1}     un trattino
        [0-9]{4})$  quattro numeri alla fine ($ specifica alla fine)

         */

        //Carta di credito visa --> // ^4[0-9]{12}([0-9]{3})?$
        //Considerando che le vecchie carte avevano 13 numeri e non sedici

        // 1) ^4             deve partire con il 4
        // 2) [0-9]{12}      dodici numeri successivi
        // 3) ([0-9]{3})?$   eventualmente (?) altri tre numeri alla fine ($)

        String visa1 = "4444444444444"; // should match
        String visa2 = "5444444444444"; // shouldn't match
        String visa3 = "4444444444444444";  // should match
        String visa4 = "4444";  // shouldn't match

        System.out.println("visa1 " + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa2 " + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa3 " + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa4 " + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));


    }
}
