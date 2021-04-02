package com.company;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

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

        //la funzione forEach si appoggia all'itnerfaccia funzionale Consumer, che richiede di
        //implementare un solo metodo chiamato action()

        /** employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

        System.out.println("Employees over 30");
        employees.forEach(employee -> {
            if(employee.getAge() > 30)
                System.out.println(employee.getName());
        });

        System.out.println("Employees 30 and younger");
        employees.forEach(employee -> {
            if(employee.getAge() <= 30)
                System.out.println(employee.getName());
        }); */

        /*Queste due funzioni che agiscono sul .forEach() sono identiche a meno di un operatore
        relazionale sulla condizione if. Possiamo unificarle utilizzando il Predicate
         */

        printEmployeesAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesAge(employees, "Employees 30 or younger", employee -> employee.getAge() <= 30);
        printEmployeesAge(employees, "Employees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        //Alternativa con lambda della terza funzione

        printEmployeesAge(employees, "Employees younger than 25", employee -> employee.getAge() < 25);

        IntPredicate greaterThan15 = i -> i >= 15;
        IntPredicate lessThan100 = i -> i < 100;

        System.out.println(greaterThan15.test(10));
        int a = 20;
        System.out.println(greaterThan15.test(a));

        System.out.println(greaterThan15.and(lessThan100).test(15));

        //Esempio sull'interfaccia Supplier -----------------

        Random random = new Random();

        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);

        for(int i = 0; i < 10; i++)
            System.out.println(randomSupplier.get());
        //equivalente a random.nextInt(1000); ----------------

        //la seguente funzione operante sul forEach non fa altro che stampare i cognomi degli impiegati

        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(' '));
            //eventualmente in substring possiamo mettere anche il secondo parametro per indicare dove
            //finire di estrarre, altrimenti di default va a fine stringa
            System.out.println("Last name is: " + lastName);
        });


    }

    private static void printEmployeesAge(List<Employee> employees, String ageText,
                                        Predicate<Employee> ageCondition){

        System.out.println(ageText);
        for(Employee employee : employees){
            if(ageCondition.test(employee))
                System.out.println(employee.getName());
        }

    }
}
