package Controller;

import Model.Articolo;
import Model.User;
import Model.WishlistDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddToWishlist")
public class AddToWishlist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String originPage = request.getHeader("Referer");
        String str = request.getParameter("codice");
        long codice = Long.parseLong(str);

        HttpSession session = request.getSession(true);
        User utente = (User) session.getAttribute("User");

        if(utente == null){
            response.sendRedirect("login.jsp");
        } else {
            WishlistDAO getWishlist = new WishlistDAO();
            if(!getWishlist.isIn(utente.getId_Utente(), codice)){
                throw new RuntimeException("Gioco già presente in wishlist");
            } else {
                getWishlist.addToWishList(utente.getId_Utente(), codice);
                List<Articolo> wishlist = getWishlist.getWishlist(utente.getId_Utente());
                List<Long> codici = new ArrayList<>();
                for(int i = 0; i < wishlist.size(); i++){
                    codici.add(wishlist.get(i).getCodice());
                }

                session.setAttribute("codiciWishlist", codici);
                session.setAttribute("Wishlist", wishlist);

                if(originPage != null) {
                    session.setAttribute("notification", "Articolo aggiunto alla wishlist");
                    response.sendRedirect(originPage);
                } else {
                    session.setAttribute("notification", "Articolo aggiunto alla wishlist");
                    response.sendRedirect("home-page.jsp");
                }
            }
        }
    }
}
