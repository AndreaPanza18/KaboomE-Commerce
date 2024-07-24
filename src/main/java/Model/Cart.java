package Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Articolo> articoli = new ArrayList<>();

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public void addArticolo(Articolo articolo){
        this.articoli.add(articolo);
    }
    public List<Articolo> getArticoli() {
        return articoli;
    }
}
