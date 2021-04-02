package com.company;

import org.junit.Test;

import javax.swing.text.Utilities;

import static org.junit.Assert.*;

public class UtilitesTest {

    private Utilites utilites;

    @org.junit.Before
    public void setup(){
        utilites = new Utilites();
    }

    @org.junit.Test
    public void everyNthChar() throws Exception{
        char[] output = utilites.everyNthChar(new char[] {'h', 'e', 'l', 'l', 'o'}, 2);
        assertArrayEquals(new char[] {'e', 'l'}, output);
        //con assertEquals non funziona
    }

    @org.junit.Test
    public void removePairs() throws Exception{
        assertEquals("ABCDEF", utilites.removePairs("AABCDDEFF"));
    }

    @org.junit.Test
    public void converter() throws Exception{
        assertEquals(300, utilites.converter(10,5));
    }

    @org.junit.Test(expected = ArithmeticException.class)
    public void converter2() throws Exception{
        utilites.converter(10,0);
    }

    @org.junit.Test
    public void nullIfOddLength() throws Exception{
        assertNull(utilites.nullIfOddLength("pio"));
        assertNotNull(utilites.nullIfOddLength("ciao"));
    }
}