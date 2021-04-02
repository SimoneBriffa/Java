package com.company;

public class Main3 {

    public static void main(String[] args) {

        AnAnotherClass x = new AnAnotherClass();
        x.printValue();

    }

    static class AnAnotherClass {

        //Con classe anonima

        public String doSomething() {

            int i = 0;
            {

                UpperConcat uc = new UpperConcat() {
                    @Override
                    public String upperAndConcat(String s1, String s2) {
                        return s1.toUpperCase() + s2.toUpperCase();
                    }
                };

                i++;
                System.out.println("i = " + i);
                System.out.println("The anotherClass class's name is " + getClass().getSimpleName());
                return Main2.doStringStuff(uc, "String1", "String2");
            }

        }

        //Eventuali variabili devono essere dichiarate final per essere usate all'interno di una lambda

        /* EQUIVALENTE CON LAMBDA EXPRESSION */

        public String doSomething2(){

            UpperConcat uc = (s1, s2) -> {
                System.out.println("The lambda expression's class is " + getClass().getSimpleName());
                String result = s1.toUpperCase() + s2.toUpperCase();
                return result;
            };

            System.out.println("The anotherClass class's name is " + getClass().getSimpleName());
            return Main2.doStringStuff(uc, "String1", "String2");

        }

        public void printValue(){

            int number = 25;

            Runnable r = () -> {
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                //number = 30; questo sarebbe un errore
                System.out.println("The value is " + number);
            };

        }

        }

    }

