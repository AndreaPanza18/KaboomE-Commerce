package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoreDAO {
    public Autore getAutoreFromId(long idAutore){
        String query = "SELECT * FROM Autore WHERE ID_Autore = ?";

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, idAutore);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Autore autore = new Autore();
                autore.setCodice(idAutore);
                autore.setColorista(rs.getString("Colorista"));
                autore.setDisegnatore(rs.getString("Disegnatore"));
                autore.setScrittore(rs.getString("Scrittore"));
                return autore;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
}
