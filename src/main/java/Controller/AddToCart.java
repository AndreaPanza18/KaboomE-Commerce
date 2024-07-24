package Controller;

import Model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = request.getParameter("codice");
        long codice = Long.parseLong(str);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("User");

        if(user != null){
            CartDAO newCart = new CartDAO();
            newCart.addOrUpdateToCart(user.getId_Utente(), codice, 1);
        } else {
            Cart cart = (Cart) session.getAttribute("Cart");
            if(cart == null){
                cart = new Cart();
                session.setAttribute("Cart", cart);
            }

            ArticoloDAO getArticolo = new ArticoloDAO();
            Articolo articolo = getArticolo.getArticoloById(codice);
            articolo.setQuantita(1);

            boolean trovato = false;

            for (Articolo a : cart.getArticoli()){
                if(a.getCodice() == articolo.getCodice()) {
                    int quantita = a.getQuantita() + 1;
                    a.setQuantita(quantita);
                    trovato = true;
                    break;
                }
            }

            if(!trovato) {
                cart.addArticolo(articolo);
            }

            session.setAttribute("Cart", cart);
        }

        response.sendRedirect("home-page.jsp");
    }
}
