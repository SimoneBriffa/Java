package com.company;

import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {


        //Convertire in lambda la seguente funzione anonima:

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for (String part : parts)
                    System.out.println(part);
            }
        };

        //Soluzione

        Runnable runnable1 = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts)
                System.out.println(part);
        };

        //Scrivere una lambda che usi l'interfaccia Supplier per restituire una stringa

        Supplier<String> iLoveJava = () -> {
            return "I Love Java";
        };

        System.out.println(iLoveJava.get());

        /*Scrivere un codice per stampare gli elementi della seguente lista in ordine alfabetico
        e con la prima lettera maiuscola. */

        List<String> topName2015 = Arrays.asList("amelia", "olivia", "emily",
                "isla", "ava", "oliver", "jack", "charlie", "harry", "jacob");

        List<String> firstUpperCaseList = new ArrayList<>();
        topName2015.forEach(name ->
                firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
        firstUpperCaseList.sort((s1, s2) -> s1.compareTo(s2));

        firstUpperCaseList.forEach(s -> System.out.println(s));

        /* Utilizzando i method references:

        List<String> firstUpperCaseList = new ArrayList<>();
        topName2015.forEach(name ->
                firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
        firstUpperCaseList.sort(String::compareTo);

        firstUpperCaseList.forEach(System.out::println);
        */

        //Fare la stessa cosa utilizzando un Flusso

        topName2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .sorted(String::compareTo)
                .forEach(System.out::println);

        //Stampare quanti e quali sono i nomi che iniziano per A

        long nameBeginWithA = topName2015
                                .stream()
                                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                                .filter(name -> name.startsWith("A"))
                                .count();
        System.out.println(nameBeginWithA);

        //Utilizzando peek()

        topName2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList()); //oppure .count()

    }


    //Convertire in lambda la seguente funzione:

    public static String everySecondChar(String source) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == i)
                returnVal.append(source.charAt(i));
        }
        return returnVal.toString();
    }


    Function<String, String> lambdaFunction = s -> {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == i)
                returnVal.append(s.charAt(i));
        }
        return returnVal.toString();

    };

    //Eventualmente per farla partire:
    // System.out.print(lambdaFunction.apply("stringa..."));

    //Mettendo le due cose insieme in un solo metodo:

    public static String everySecondCharacter(Function<String, String> func, String daProcessare){
        return func.apply(daProcessare);
    }


}


