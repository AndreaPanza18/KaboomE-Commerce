package Controller;

import Model.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String codiceStr = request.getParameter("codice");
        long codice = Long.parseLong(codiceStr);

        UserDAO deleteUser = new UserDAO();
        if(deleteUser.deleteUser(codice)){
            session.setAttribute("successMessage", "Utente eliminato con successo!");
        } else {
            session.setAttribute("errorMessage", "Errore durante l'eliminazione dell'utente");
        }

        response.sendRedirect("see-utenti.jsp");
    }
}
