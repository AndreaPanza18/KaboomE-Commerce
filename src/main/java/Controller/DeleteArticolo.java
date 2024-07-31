package Controller;

import Model.ArticoloDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeleteArticolo")
public class DeleteArticolo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String codiceStr = request.getParameter("codice");
        long codice = Long.parseLong(codiceStr);

        ArticoloDAO deleteArticolo = new ArticoloDAO();
        if(deleteArticolo.deleteArticolo(codice)){
            session.setAttribute("successMessage", "Articolo eliminato con successo!");
        } else {
            session.setAttribute("errorMessage", "Errore durante l'eliminazione dell'articolo.");
        }

        response.sendRedirect("elimina-articoli.jsp");
    }
}
