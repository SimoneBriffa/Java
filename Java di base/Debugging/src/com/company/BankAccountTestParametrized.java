package com.company;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BankAccountTestParametrized {

    private BankAccount account;
    private final double amount;
    private final boolean branch;
    private final double expected;

    public BankAccountTestParametrized(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;
    }

    @org.junit.Before
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
    }

    @Parameterized.Parameters
    public static Collection<Object> testConditions() {
        return Arrays.asList(new Object[][]{
                {100.00, true, 1100.00},
                {200.00, true, 1200.00},
                {325.14, true, 1325.14},
                {489.33, true, 1489.33},
                {1000.00, true, 3000.00}
                //1 colonna amount
                //2 colonna branch
                //3 colonna excepted
        });

    }

    @org.junit.Test
    public void deposit() throws Exception{
        account.deposit(amount, branch);
        assertEquals(expected, account.getBalance(), .01);
    }
}
