package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Functions {

    public static void main(String[] args) {

        Employee John = new Employee("John Doe", 30);
        Employee Tim = new Employee("Tim Buchalka", 21);
        Employee Jack = new Employee("Jack Hill", 40);
        Employee Snow = new Employee("Snow White", 22);
        Employee Red = new Employee("Red Ridnig", 35);
        Employee Simone = new Employee("Simone Briffa", 26);

        List<Employee> employees = new ArrayList<>();
        employees.add(John);
        employees.add(Tim);
        employees.add(Jack);
        employees.add(Snow);
        employees.add(Red);
        employees.add(Simone);

        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };
        //Il primo tipo, in questo caso Employee, è il tipo dell'argomento, mentre il secondo,
        //in questo caso String, è il tipo di ritorno

        for (Employee employee : employees)
            System.out.println(getLastName.apply(employee));

        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        Random random1 = new Random();

        for(Employee employee: employees){
            if(random1.nextBoolean())
                System.out.println(getAName(getFirstName, employee));
            else
                System.out.println(getAName(getLastName, employee));
        }


        //le seguente funzioni restituiscono ciò che è dopo la -> a partire da ciò che c'è prima
        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0))); //JOHN


        //nel seguente costrutto, il terzo parametro è quello restituito
        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) ->{
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));


        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello World");

    }

        private static String getAName(Function<Employee, String> getName, Employee employee){
            return getName.apply(employee);
        }

}
