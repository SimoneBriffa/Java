package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Impiegato implements Serializable {

    String nome, cognome, mansione;
    private long serialVersioneUID = 1L;

    public Impiegato(String nome, String cognome, String mansione) {
        this.nome = nome;
        this.cognome = cognome;
        this.mansione = mansione;
    }


    static final Comparator<Impiegato> IMPIEGATO_COMPARATOR = new Comparator<Impiegato>() {
        @Override
        public int compare(Impiegato o1, Impiegato o2) {
            if(o1.cognome.compareTo(o2.cognome)>0)
                return 1;
            else if(o1.cognome.compareTo(o2.cognome)==0){
                return 0;
            }

            else
                return -1;
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Impiegato impiegato = (Impiegato) o;
        return nome.equals(impiegato.nome) &&
                cognome.equals(impiegato.cognome) &&
                mansione.equals(impiegato.mansione);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + nome.hashCode();
        result = 31*result + cognome.hashCode();
        result = 31*result + mansione.hashCode();
        return result;

    }

    @Override
    public String toString() {
        return "COGNOME: " + cognome + "\t\t NOME: " + nome + "\t\t MANSIONE: " + mansione;
    }

}
