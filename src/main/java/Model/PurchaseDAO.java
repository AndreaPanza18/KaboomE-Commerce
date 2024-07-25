package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

    public void Purchase(List<Articolo> carrello, long idUtente, LocalDate dataAcquisto){
        String query = "INSERT INTO Acquisti(U_ID_Utente, A_Codice_A_Barre, Quantità, Data_Acquisto, Prezzo_Totale)" +
                        "VALUES(?, ?, ?, ?, ?)";

        try(Connection con = ConPool.getConnection()){
            con.setAutoCommit(false);

            double total = 0.0;
            for(Articolo articolo : carrello){
                total += articolo.getPrezzo() * articolo.getQuantita();
            }

            try(PreparedStatement ps = con.prepareStatement(query)) {
                for (Articolo articolo : carrello) {
                    ps.setLong(1, idUtente);
                    ps.setLong(2, articolo.getCodice());
                    ps.setInt(3, articolo.getQuantita());
                    ps.setDate(4, Date.valueOf(dataAcquisto));
                    ps.setDouble(5, total);
                    ps.addBatch();
                }

                ps.executeBatch();
                con.commit();
            } catch (SQLException e){
                con.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Purchase> getPurchase(long idUtente){
        String query = "SELECT * FROM Acquisti WHERE U_ID_Utente = ?";
        List<Purchase> acquisti = new ArrayList<>();

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, idUtente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Purchase acquisto = new Purchase();
                acquisto.setIdAcquisto(rs.getLong("ID_Acquisto"));
                acquisto.setIdUtente(idUtente);
                acquisto.setDataAcquisto(rs.getDate("Data_Acquisto").toLocalDate());
                acquisto.setPrezzoTotale(rs.getDouble("Prezzo_Totale"));

                List<Articolo> articoli;
                ArticoloDAO getArticolo = new ArticoloDAO();
                articoli = acquisto.getArticoli();
                Articolo articolo = getArticolo.getArticoloById(rs.getLong("A_Codice_A_Barre"));
                articolo.setQuantita(rs.getInt("Quantità"));
                articoli.add(articolo);

                acquisto.setArticoli(articoli);
                acquisti.add(acquisto);
            }

            return acquisti;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
