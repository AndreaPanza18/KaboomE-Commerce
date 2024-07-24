package Controller;

import Model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String originPage = request.getHeader("Referer");
        String str = request.getParameter("codice");
        long codice = Long.parseLong(str);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("User");

        if(user != null){
            CartDAO newCart = new CartDAO();
            List<Articolo> articoli = (List<Articolo>) session.getAttribute("Cart");
            ArticoloDAO getArticolo = new ArticoloDAO();
            Articolo articolo = getArticolo.getArticoloById(codice);
            articolo.setQuantita(1);

            if(articoli == null){
                newCart.addOrUpdateToCart(user.getId_Utente(), codice, 1);
                articoli = newCart.getCart(user.getId_Utente());
                session.setAttribute("Cart", articoli);
            } else {
                boolean trovato = false;
                for(Articolo a : articoli){
                    if (a.getCodice() == codice){
                        a.setQuantita(a.getQuantita() + 1);
                        trovato = true;
                        break;
                    }
                }

                if(!trovato){
                    newCart.addOrUpdateToCart(user.getId_Utente(), codice, 1);
                    articoli.add(articolo);
                }

                session.setAttribute("Cart", articoli);
            }
        } else {
            List<Articolo> articoli = (List<Articolo>) session.getAttribute("Cart");
            ArticoloDAO getArticolo = new ArticoloDAO();
            Articolo articolo = getArticolo.getArticoloById(codice);
            articolo.setQuantita(1);

            if(articoli == null){
                articoli = new ArrayList<>();
                articoli.add(articolo);
                session.setAttribute("Cart", articoli);
            } else {
                boolean trovato = false;
                for (Articolo a : articoli) {
                    if (a.getCodice() == articolo.getCodice()) {
                        int quantita = a.getQuantita() + 1;
                        a.setQuantita(quantita);
                        trovato = true;
                        break;
                    }
                }

                if (!trovato) {
                    articoli.add(articolo);
                }
                session.setAttribute("Cart", articoli);
            }
        }
        if(originPage != null) {
            response.sendRedirect(originPage);
        } else {
            response.sendRedirect("home-page.jsp");
        }
    }
}
