package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> someBingoNumbers = Arrays.asList("N40", "N36", "B12", "B6",
                                       "G53", "G49", "G60", "G50", "I26", "I17", "I29", "071");

        List<String> gNumbers = new ArrayList<>();

        someBingoNumbers.stream()
                .map(String::toUpperCase) //nel caso non lo fosse
                .filter(s->s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "D71");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I17", "I29", "D71");
        Stream<String> concateStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.println("===================================");
        System.out.println(concateStream
                                .distinct()
                                .peek(System.out::println)
                                .count());
        //distinct non tiene conto degli elementi doppi. peek ritorna gli elementi della lista,
        //quindi, passando come argomento la print, li stampa

        /*

        VERSIONE ESTESA

        someBingoNumbers.forEach(number -> {
            if(number.toUpperCase().startsWith("G")){
                gNumbers.add(number);
                System.out.println(number);
            }
        });

        gNumbers.sort((String s1, String s2) -> s1.compareTo(s2)); //è l'equivalente di compareTo()
        gNumbers.forEach((String s) ->System.out.println(s));*/


        Employee John = new Employee("John Doe", 30);
        Employee Jane = new Employee("Jane Deer", 25);
        Employee Jack = new Employee("Jack Hill", 40);
        Employee SnowWhite = new Employee("Snow White", 22);

        Department hr = new Department("Human Resources");
        hr.addEmployee(Jane); hr.addEmployee(Jack); hr.addEmployee(SnowWhite);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(John);

        List<Department> departments = new ArrayList<>();
        departments.add(hr); departments.add(accounting);

        System.out.println("====================================");

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        //la flatmap è uno strumento da usare su una lista quando la lista stessa non è la sorgente.
        //In questo caso infatti la lista è un campo di Department

        System.out.println("========================================");

        //La funzione collect invece estrae una cernita di elementi da una determinata lista.
        //di fatto non è niente di diverso da ciò che abbiamo atto prima, la differenza è che qui
        //abbiamo un'altra lista con cui, eventualmente, continuare a lavorare

        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());
        //oppure .collect(ArrayList::new. ArrayList::add, ArrayList::addAll);

        for(String s: sortedGNumbers)
            System.out.println(s);

        //Se volessimo raggruppare gli impiegati per età...

        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        //Vogliamo trovare il più giovane

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        //alle condizioni attuali non possiamo rielaborare i flussi

        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();
        //.count() è una funzione di "terminal" senza la quale non vengono stampate le info

    }
}
