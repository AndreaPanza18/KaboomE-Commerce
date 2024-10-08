package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArticoloDAO {
    public Articolo getArticoloById(long codice){
        String query = "SELECT Nome, Prezzo, DDU, Descrizione, Personaggio, Materiale, A_ID_Autore, Url_immagine FROM Articolo WHERE Codice_A_Barre = ?";
        Articolo articolo= new Articolo();
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
                articolo.setUrlImmagine(rs.getString(8));
            }
            return articolo;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Articolo> getArticoloByCategories(String categoria){
        String fumetti = "SELECT Codice_A_Barre, Nome, Prezzo, DDU, Descrizione, A_ID_Autore, Url_immagine FROM Articolo " +
                            "WHERE A_ID_Autore IS NOT NULL AND Personaggio IS NULL AND Materiale IS NULL";
        String carte = "SELECT Codice_A_Barre, Nome, Prezzo, DDU, Descrizione, Materiale, Url_immagine FROM Articolo " +
                            "WHERE A_ID_Autore IS NULL AND Personaggio IS NULL AND Materiale IS NOT NULL";
        String actionFigure = "SELECT Codice_A_Barre, Nome, Prezzo, DDU, Descrizione, Personaggio, Url_immagine FROM Articolo " +
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
                    articolo.setUrlImmagine(rs.getString("Url_immagine"));
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
                    articolo.setUrlImmagine(rs.getString("Url_immagine"));
                    System.out.println(articolo.getNome());
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
                    articolo.setUrlImmagine(rs.getString("Url_immagine"));
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

    public List<Articolo> getLastdrop(){
        String query = "SELECT * FROM Articolo ORDER BY DDU DESC LIMIT 10";
        List<Articolo> articoli = new ArrayList<>();

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Articolo articolo = new Articolo();
                articolo.setCodice(rs.getLong("Codice_A_Barre"));
                articolo.setNome(rs.getString("Nome"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setData_uscita(rs.getDate("DDU").toLocalDate());
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPersonaggio(rs.getString("Personaggio"));
                articolo.setMateriale(rs.getString("Materiale"));
                articolo.setId_Autore(rs.getLong("A_ID_Autore"));
                articolo.setUrlImmagine(rs.getString("Url_immagine"));
                articoli.add(articolo);
            }
            return articoli;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Articolo> getArticoliByName(String nomeArticolo){
        String query = "SELECT * FROM Articolo WHERE Nome LIKE ?";
        List<Articolo> articoli = new ArrayList<>();

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + nomeArticolo + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Articolo articolo = new Articolo();
                articolo.setCodice(rs.getLong("Codice_A_Barre"));
                articolo.setNome(rs.getString("Nome"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setData_uscita(rs.getDate("DDU").toLocalDate());
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPersonaggio(rs.getString("Personaggio"));
                articolo.setMateriale(rs.getString("Materiale"));
                articolo.setId_Autore(rs.getLong("A_ID_Autore"));
                articolo.setUrlImmagine(rs.getString("Url_immagine"));
                articoli.add(articolo);
            }

            return articoli;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Articolo> getAllArticoli(){
        String query = "SELECT * FROM Articolo";
        List<Articolo> articoli = new ArrayList<>();

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Articolo articolo = new Articolo();
                articolo.setCodice(rs.getLong("Codice_A_Barre"));
                articolo.setNome(rs.getString("Nome"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setData_uscita(rs.getDate("DDU").toLocalDate());
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPersonaggio(rs.getString("Personaggio"));
                articolo.setMateriale(rs.getString("Materiale"));
                articolo.setId_Autore(rs.getLong("A_ID_Autore"));
                articolo.setUrlImmagine(rs.getString("Url_immagine"));
                articoli.add(articolo);
            }

            Collections.sort(articoli, new Comparator<Articolo>() {
                @Override
                public int compare(Articolo a1, Articolo a2) {
                    return String.CASE_INSENSITIVE_ORDER.compare(a1.getNome(), a2.getNome());
                }
            });

            return articoli;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean addArticolo(Articolo articolo){
        String query = "INSERT INTO Articolo (Codice_A_Barre, Nome, Prezzo, DDU, Descrizione, Personaggio, Materiale, A_ID_Autore, Url_immagine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, articolo.getCodice());
            ps.setString(2, articolo.getNome());
            ps.setDouble(3, articolo.getPrezzo());
            ps.setDate(4, Date.valueOf(articolo.getData_uscita()));
            ps.setString(5, articolo.getDescrizione());
            if (articolo.getPersonaggio() != null){
                ps.setString(6, articolo.getPersonaggio());
            } else {
                ps.setNull(6, Types.VARCHAR);
            }
            if (articolo.getMateriale() != null){
                ps.setString(7, articolo.getMateriale());
            } else {
                ps.setNull(7, Types.VARCHAR);
            }
            if (articolo.getId_Autore() != 0) {
                ps.setLong(8, articolo.getId_Autore());
            } else {
                ps.setNull(8, Types.BIGINT);
            }
            ps.setString(9, articolo.getUrlImmagine());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean deleteArticolo(long codice){
        String acquisti = "DELETE FROM Acquisti WHERE A_Codice_A_Barre = ?";
        String wishlist = "DELETE FROM Wishlist WHERE A_Codice_A_Barre = ?";
        String carrello = "DELETE FROM Carrello WHERE A_Codice_A_Barre = ?";
        String articolo = "DELETE FROM Articolo WHERE Codice_A_Barre = ?";

        try(Connection con = ConPool.getConnection()){
            PreparedStatement psAcquisti = con.prepareStatement(acquisti);
            PreparedStatement psWishlist = con.prepareStatement(wishlist);
            PreparedStatement psCarrello = con.prepareStatement(carrello);
            PreparedStatement psArticolo = con.prepareStatement(articolo);

            psAcquisti.setLong(1, codice);
            psAcquisti.executeUpdate();

            psWishlist.setLong(1, codice);
            psWishlist.executeUpdate();

            psCarrello.setLong(1, codice);
            psCarrello.executeUpdate();

            psArticolo.setLong(1, codice);
            psArticolo.executeUpdate();

            return true;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}




