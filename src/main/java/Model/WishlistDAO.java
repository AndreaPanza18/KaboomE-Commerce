package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistDAO {

    public boolean addToWishList(long idUtente, long codice){
        if(!isIn(idUtente, codice)){
            String query = "INSERT INTO Wishlist(U_ID_Utente, A_Codice_A_Barre) VALUES(?, ?)";

            try (Connection con = ConPool.getConnection()){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setLong(1, idUtente);
                ps.setLong(2, codice);
                ps.executeQuery();
                return true;
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }

    public void removeFromWishlist(long idUtente, long codice){
        String query = "DELETE FROM Wishlist WHERE U_ID_Utente = ? AND A_Codice_A_Barre = ?";

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, idUtente);
            ps.setLong(2, codice);
            ps.executeQuery();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isIn(long idUtente, long codice){
        String query = "SELECT COUNT(*) FROM Wishlist WHERE U_ID_Utente = ? AND A_Codice_A_Barre = ?";

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, idUtente);
            ps.setLong(2, codice);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
}
