package com.company;

import java.io.Serializable;
import java.util.Objects;

public class Amico implements Serializable {

    private String nome, cognome, lavoro;
    private static final long serialVersionUID = 7618241488437359058L;

    public Amico(String nome, String cognome, String lavoro) {
        this.nome = nome;
        this.cognome = cognome;
        this.lavoro = lavoro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getLavoro() {
        return lavoro;
    }

    public void setLavoro(String lavoro) {
        this.lavoro = lavoro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amico amico = (Amico) o;
        return Objects.equals(nome, amico.nome) &&
                Objects.equals(cognome, amico.cognome) &&
                Objects.equals(lavoro, amico.lavoro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, lavoro);
    }

    @Override
    public String toString() {
        return "Cognome: " + cognome + " | Nome: " + nome + " | Lavoro: " + lavoro;
    }
}
