package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
