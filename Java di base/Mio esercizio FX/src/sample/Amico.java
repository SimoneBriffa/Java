package sample;

public class Amico {

    private String nome, cognome, lavoro;

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
    public String toString() {
        return "Amico{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", lavoro='" + lavoro + '\'' +
                '}';
    }
}
