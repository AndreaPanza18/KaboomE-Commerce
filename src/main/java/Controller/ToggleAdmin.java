package Controller;

import Model.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ToggleAdmin")
public class ToggleAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String codiceStr = request.getParameter("codice");
        long codice = Long.parseLong(codiceStr);

        UserDAO toggleUser = new UserDAO();
        if(toggleUser.toggleAdmin(codice)){
            session.setAttribute("successMessage", "Permessi assegnati con successo!");
        } else {
            session.setAttribute("errorMessage", "Errore durante l'assegnazione dei permessi");
        }

        response.sendRedirect("see-utenti.jsp");
    }
}
