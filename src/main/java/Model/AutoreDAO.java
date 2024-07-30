package Model;

import java.sql.*;

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

    public void createAutore(Autore autore){
        String query = "INSERT INTO Autore (ID_Autore, Colorista, Scrittore, Disegnatore) VALUES (?, ?, ?, ?)";

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, autore.getCodice());
            if(autore.getColorista() != null){
                ps.setString(2, autore.getColorista());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }
            if(autore.getScrittore() != null){
                ps.setString(3, autore.getScrittore());
            } else {
                ps.setNull(3, Types.VARCHAR);
            }
            if(autore.getDisegnatore() != null){
                ps.setString(4, autore.getDisegnatore());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }

            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
