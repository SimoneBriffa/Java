package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {

        //Una lambda più complessa

        new Thread(() -> {
            System.out.println("Printing from the Runnable");
            System.out.println("Line 2");
            System.out.println("This is line 3");
        }).start();

        Employee John = new Employee("John Doe", 30);
        Employee Tim = new Employee("Tim Buchalka", 30);
        Employee Jack = new Employee("Jack Hill", 40);
        Employee Snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(John);
        employees.add(Tim);
        employees.add(Jack);
        employees.add(Snow);

        //Comparator è un'interfaccia che ben si presta alle lambda perchè ha un solo metodo da definire

        employees.sort((employee1, employee2) ->
                employee1.getName().compareTo(employee2.getName()));

        /*
        PER ESTESO

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });*/

        for(Employee employee : employees){
            System.out.println(employee.getName());
        }

        //Le lambdas hanno introdotto una particolare funzione utilizzabile sulle Collections:

        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

        //--------------------INTERFACCIA HOME MADE

        UpperConcat uc = (s1,s2) -> (s1.toUpperCase() + s2.toUpperCase());
        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);

        /*
        * VERSIONE ESTESA
        *
        * String sillyString = doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, employees.get(0).getName(), employees.get(1).getName());

        System.out.println(sillyString);
        *
        * */

        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();
        System.out.println(s);

    }

    public static String doStringStuff(UpperConcat uc, String s1, String s2){
        return uc.upperAndConcat(s1, s2);
    }

}

class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat{
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass{

    public String doSomething(){
        return Main2.doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, "String1", "String2");

    }

}