package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public void addOrUpdateToCart(long id_Utente, long codice_Articolo, int quantita){
        String check = "SELECT Quantità FROM Carrello WHERE U_ID_Utente = ? AND A_Codice_A_Barre = ?";
        String insert = "INSERT INTO Carrello(U_ID_Utente, A_Codice_A_Barre, Quantità) VALUES (?, ?, ?)";
        String update = "UPDATE Carrello SET Quantità = Quantità + ? WHERE U_ID_Utente = ? AND A_Codice_A_Barre = ?";

        try (Connection con = ConPool.getConnection()){
            try (PreparedStatement checkps = con.prepareStatement(check)) {
                checkps.setLong(1, id_Utente);
                checkps.setLong(2, codice_Articolo);
                ResultSet checkrs = checkps.executeQuery();

                if (checkrs.next()){
                    try (PreparedStatement updateps = con.prepareStatement(update)){
                        updateps.setInt(1, quantita);
                        updateps.setLong(2, id_Utente);
                        updateps.setLong(3, codice_Articolo);
                        updateps.executeUpdate();
                    }
                } else {
                    try(PreparedStatement insertps = con.prepareStatement(insert)){
                        insertps.setLong(1, id_Utente);
                        insertps.setLong(2, codice_Articolo);
                        insertps.setInt(3, quantita);
                        insertps.executeUpdate();
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Articolo> getCart(long id_Utente){
        String query = "SELECT a.Codice_A_Barre, c.Quantità " +
                        "FROM Carrello c " +
                        "JOIN Articolo a ON c.A_Codice_A_Barre = a.Codice_A_Barre " +
                        "WHERE c.U_ID_Utente = ?";
        List<Articolo> articoli = new ArrayList<>();

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id_Utente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                long codice = rs.getLong("Codice_A_Barre");
                int quantita = rs.getInt("Quantità");

                ArticoloDAO getArticolo = new ArticoloDAO();
                Articolo articolo = getArticolo.getArticoloById(codice);
                articolo.setQuantita(quantita);
                articoli.add(articolo);
            }

            return articoli;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeFromCart(long idUtente, long codice, List<Articolo> articoli){
        String update = "UPDATE Carrello SET Quantità = Quantità - 1 WHERE U_ID_Utente = ? AND A_Codice_A_Barre = ?";
        String delete = "DELETE FROM Carrello WHERE U_ID_Utente = ? AND A_Codice_A_Barre = ?";

        try (Connection con = ConPool.getConnection()){
            for(Articolo articolo : articoli){
                if(articolo.getCodice() == codice){
                    if(articolo.getQuantita() == 1){
                        try (PreparedStatement deleteps = con.prepareStatement(delete)){
                            deleteps.setLong(1, idUtente);
                            deleteps.setLong(2, codice);
                            deleteps.executeUpdate();
                        }
                    } else {
                        try (PreparedStatement updateps = con.prepareStatement(update)){
                            updateps.setLong(1, idUtente);
                            updateps.setLong(2, codice);
                            updateps.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteCart(long idUtente){
        String query = "DELETE FROM Carrello WHERE U_ID_Utente = ?";

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, idUtente);
            ps.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}






