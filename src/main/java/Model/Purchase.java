package Model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private long idAcquisto;
    private long idUtente;
    private LocalDate dataAcquisto;
    private double prezzoTotale;
    private List<Articolo> articoli = new ArrayList<>();

    public long getIdAcquisto() {
        return idAcquisto;
    }

    public long getIdUtente() {
        return idUtente;
    }

    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setIdAcquisto(long idAcquisto) {
        this.idAcquisto = idAcquisto;
    }

    public void setIdUtente(long idUtente) {
        this.idUtente = idUtente;
    }

    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }
}
