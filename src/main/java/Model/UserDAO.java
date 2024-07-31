package Model;

import org.springframework.security.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean createAccount(User user){
        String hashedPassword = hashPassword(user.getPassword());
        String query = "INSERT INTO Utente(Nome, Cognome, Email, Password, Admin_Permission) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getCognome());
            ps.setString(3, user.getEmail());
            ps.setString(4, hashedPassword);
            ps.setBoolean(5, false);
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User loginUser(String email, String password){
        String query = "SELECT * FROM Utente WHERE Email = ?";

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if (checkpassword(password, rs.getString("Password"))) {
                    User user = new User();
                    user.setId_Utente(rs.getLong("ID_Utente"));
                    user.setNome(rs.getString("Nome"));
                    user.setCognome(rs.getString("Cognome"));
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setPermission(rs.getBoolean("Admin_Permission"));
                    return user;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkpassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }

    public List<User> getAllUser(){
        String query = "SELECT * FROM Utente";
        List<User> utenti = new ArrayList<>();

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                User utente = new User();
                utente.setId_Utente(rs.getLong("ID_Utente"));
                utente.setNome(rs.getString("Nome"));
                utente.setCognome(rs.getString("Cognome"));
                utente.setEmail(rs.getString("Email"));
                utente.setPermission(rs.getBoolean("Admin_Permission"));
                utenti.add(utente);
            }

            return utenti;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(long codice){
        String carrello = "DELETE FROM Carrello WHERE U_ID_Utente = ?";
        String wishlist = "DELETE FROM Wishlist WHERE U_ID_Utente = ?";
        String acquisti = "DELETE FROM Acquisti WHERE U_ID_Utente = ?";
        String utente = "DELETE FROM Utente WHERE ID_Utente = ?";

        try(Connection con = ConPool.getConnection()){
            PreparedStatement psCarrello = con.prepareStatement(carrello);
            PreparedStatement psWishlist = con.prepareStatement(wishlist);
            PreparedStatement psAcquisti = con.prepareStatement(acquisti);
            PreparedStatement psUtente = con.prepareStatement(utente);

            psCarrello.setLong(1, codice);
            psCarrello.executeUpdate();

            psWishlist.setLong(1, codice);
            psWishlist.executeUpdate();

            psAcquisti.setLong(1, codice);
            psAcquisti.executeUpdate();

            psUtente.setLong(1, codice);
            psUtente.executeUpdate();

            return true;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean toggleAdmin(long codice){
        String select = "SELECT Admin_Permission FROM Utente WHERE ID_Utente = ?";
        String update = "UPDATE Utente SET Admin_Permission = ? WHERE ID_Utente = ?";

        try(Connection con = ConPool.getConnection()){
            PreparedStatement psSelect = con.prepareStatement(select);
            PreparedStatement psUpdate = con.prepareStatement(update);

            psSelect.setLong(1, codice);
            ResultSet rs = psSelect.executeQuery();

            if(rs.next()){
                if(rs.getBoolean("Admin_Permission")){
                    psUpdate.setBoolean(1, false);
                    psUpdate.setLong(2, codice);
                    psUpdate.executeUpdate();
                    return true;
                } else {
                    psUpdate.setBoolean(1, true);
                    psUpdate.setLong(2, codice);
                    psUpdate.executeUpdate();
                    return true;
                }
            }
            return false;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
