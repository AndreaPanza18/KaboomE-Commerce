package Model;

import java.time.LocalDate;

public class Articolo {
    private long codice;
    private String nome;
    private double prezzo;
    private LocalDate data_uscita;
    private String descrizione;
    private String personaggio;
    private String materiale;
    private long id_Autore;
    private int quantita;

    public void setCodice(long codice) {
        this.codice = codice;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setData_uscita(LocalDate data_uscita) {
        this.data_uscita = data_uscita;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPersonaggio(String personaggio) {
        this.personaggio = personaggio;
    }

    public void setMateriale(String materiale) {
        this.materiale = materiale;
    }

    public void setId_Autore(long id_Autore) {
        this.id_Autore = id_Autore;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public long getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public LocalDate getData_uscita() {
        return data_uscita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getPersonaggio() {
        return personaggio;
    }

    public String getMateriale() {
        return materiale;
    }

    public long getId_Autore() {
        return id_Autore;
    }

    public int getQuantita() {
        return quantita;
    }
}
