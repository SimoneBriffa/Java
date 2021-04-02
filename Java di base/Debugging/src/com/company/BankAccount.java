package com.company;

public class BankAccount {

    private String firstName, lastName;
    private double balance;

    public BankAccount(String firstName, String lastName, double balance, int typeOfAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.accountType = typeOfAccount;
    }

    public static final int CHECKING = 1;
    public static final int SAVINGS = 2;

    private int accountType;

    /*
    il boolean è branch è true se il deposito è effettuato in filiale, false se
    effettuato al bancomat

     */
    public double deposit(double amount, boolean branch){
        balance += amount;
        return balance;
    }

    public double withdraw(double amount, boolean branch){
        if(amount > 500.00 && !branch)
            throw new IllegalArgumentException();

        balance -= amount;
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isChecking(){
        return accountType == CHECKING;
    }



}
