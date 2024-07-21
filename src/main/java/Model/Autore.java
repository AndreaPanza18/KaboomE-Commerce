package Model;

public class Autore {

    private long codice;
    private String Colorista;
    private String Scrittore;
    private String Disegnatore;

    public void setCodice(long codice) {
        this.codice = codice;
    }

    public void setColorista(String colorista) {
        Colorista = colorista;
    }

    public void setScrittore(String scrittore) {
        Scrittore = scrittore;
    }

    public void setDisegnatore(String disegnatore) {
        Disegnatore = disegnatore;
    }

    public long getCodice() {
        return codice;
    }

    public String getColorista() {
        return Colorista;
    }

    public String getScrittore() {
        return Scrittore;
    }

    public String getDisegnatore() {
        return Disegnatore;
    }
}
