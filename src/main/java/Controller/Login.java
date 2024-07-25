package Controller;

import Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User utente;
        UserDAO check = new UserDAO();
        utente = check.loginUser(email, password);

        if(utente == null){
            request.setAttribute("loginError", "L'account non è valido o la password è errata");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("User", utente);
            session.setAttribute("Permission", utente.getPermission());

            CartDAO getCart = new CartDAO();
            if(session.getAttribute("Cart") != null){
                List<Articolo> cart = (List<Articolo>) session.getAttribute("Cart");
                for(Articolo a : cart){
                    getCart.addOrUpdateToCart(utente.getId_Utente(), a.getCodice(), a.getQuantita());
                }
                List<Articolo> realCart = getCart.getCart(utente.getId_Utente());
                session.setAttribute("Cart", realCart);
            } else {
                List<Articolo> realCart = getCart.getCart(utente.getId_Utente());
                session.setAttribute("Cart", realCart);
            }

            WishlistDAO getWishlist = new WishlistDAO();
            List<Articolo> wishlist = getWishlist.getWishlist(utente.getId_Utente());
            List<Long> codici = new ArrayList<>();
            for(int i = 0; i < wishlist.size(); i++){
                codici.add(wishlist.get(i).getCodice());
            }
            session.setAttribute("codiciWishlist", codici);
            session.setAttribute("Wishlist", wishlist);

            PurchaseDAO getAcquisti = new PurchaseDAO();
            List<Purchase> acquisti = getAcquisti.getPurchase(utente.getId_Utente());
            Collections.sort(acquisti, new Comparator<Purchase>() {
                @Override
                public int compare(Purchase a1, Purchase a2) {
                    return Double.compare(a2.getIdAcquisto(), a1.getIdAcquisto());
                }
            });
            session.setAttribute("Acquisti", acquisti);

            response.sendRedirect("profile-page.jsp");
        }
    }
}
