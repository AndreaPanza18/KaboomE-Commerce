package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticoloDAO {
    public Articolo getArticoloById(long codice){
        String query = "SELECT Nome, Prezzo, DDU, Descrizione, Personaggio, Materiale, A_ID_Autore FROM Articolo WHERE Codice_A_Barre = ?";
        Articolo articolo = null;
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, codice);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                articolo.setCodice(codice);
                articolo.setNome(rs.getString(1));
                articolo.setPrezzo(rs.getDouble(2));
                articolo.setData_uscita(rs.getDate(3).toLocalDate());
                articolo.setDescrizione(rs.getString(4));
                articolo.setPersonaggio(rs.getString(5));
                articolo.setMateriale(rs.getString(6));
                articolo.setId_Autore(rs.getLong(7));
            }
            return articolo;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Articolo> getArticoloByCategories(String categoria){
        String fumetti = "SELECT Codice_A_Barre, Nome, Prezzo, DDU, Descrizione, A_ID_Autore FROM Articolo " +
                            "WHERE A_ID_Autore IS NOT NULL AND Personaggio IS NULL AND Materiale IS NULL";
        String carte = "SELECT Codice_A_Barre, Nome, Prezzo, DDU, Descrizione, Materiale FROM Articolo " +
                            "WHERE A_ID_Autore IS NULL AND Personaggio IS NULL AND Materiale IS NOT NULL";
        String actionFigure = "SELECT Codice_A_Barre, Nome, Prezzo, DDU, Descrizione, Personaggio FROM Articolo " +
                                "WHERE A_ID_Autore IS NULL AND Personaggio IS NOT NULL AND Materiale IS NULL";
        List<Articolo> articoli = new ArrayList<>();

        try(Connection con = ConPool.getConnection()){
            if(categoria.equals("fumetti")){
                PreparedStatement psFumetti = con.prepareStatement(fumetti);
                ResultSet rs = psFumetti.executeQuery();
                while(rs.next()){
                    Articolo articolo = new Articolo();
                    articolo.setCodice(rs.getLong("Codice_A_Barre"));
                    articolo.setNome(rs.getString("Nome"));
                    articolo.setPrezzo(rs.getDouble("Prezzo"));
                    articolo.setData_uscita(rs.getDate("DDU").toLocalDate());
                    articolo.setDescrizione(rs.getString("Descrizione"));
                    articolo.setId_Autore(rs.getLong("A_ID_Autore"));
                    articoli.add(articolo);
                }
                return articoli;
            } else if (categoria.equals("carte")) {
                PreparedStatement psCarte = con.prepareStatement(carte);
                ResultSet rs = psCarte.executeQuery();
                while(rs.next()){
                    Articolo articolo = new Articolo();
                    articolo.setCodice(rs.getLong("Codice_A_Barre"));
                    articolo.setNome(rs.getString("Nome"));
                    articolo.setPrezzo(rs.getDouble("Prezzo"));
                    articolo.setData_uscita(rs.getDate("DDU").toLocalDate());
                    articolo.setDescrizione(rs.getString("Descrizione"));
                    articolo.setMateriale(rs.getString("Materiale"));
                    articoli.add(articolo);
                }
                return articoli;
            } else if (categoria.equals("action-figure")) {
                PreparedStatement psActionFigure = con.prepareStatement(actionFigure);
                ResultSet rs = psActionFigure.executeQuery();
                while(rs.next()){
                    Articolo articolo = new Articolo();
                    articolo.setCodice(rs.getLong("Codice_A_Barre"));
                    articolo.setNome(rs.getString("Nome"));
                    articolo.setPrezzo(rs.getDouble("Prezzo"));
                    articolo.setData_uscita(rs.getDate("DDU").toLocalDate());
                    articolo.setDescrizione(rs.getString("Descrizione"));
                    articolo.setPersonaggio(rs.getString("Personaggio"));
                    articoli.add(articolo);
                }
                return articoli;
            } else {
                return null;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}




