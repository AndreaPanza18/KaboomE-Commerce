package Model;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void createAccount(User user){
        String hashedPassword = hashPassword(user.getPassword());
        String query = "INSERT INTO Utente(Nome, Cognome, Email, Password, Admi_Permission) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getCognome());
            ps.setString(3, user.getEmail());
            ps.setString(4, hashedPassword);
            ps.setBoolean(5, false);
            ps.executeQuery();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User loginUser(String email, String password){
        String query = "SELECT * FROM Utente WHERE Email = ?";

        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(checkpassword(password, rs.getString("Password"))){
                User user = null;
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
}
