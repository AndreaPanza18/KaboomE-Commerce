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
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
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
                session.setAttribute("Cart", cart);
            }

            WishlistDAO getWishlist = new WishlistDAO();
            List<Articolo> wishlist = getWishlist.getWishlist(utente.getId_Utente());
            session.setAttribute("Wishlist", wishlist);

            PurchaseDAO getAcquisti = new PurchaseDAO();
            List<Articolo> acquisti = getAcquisti.BoughtArticles(utente.getId_Utente());
            session.setAttribute("BoughtArticles", acquisti);

            RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.jsp");
            dispatcher.forward(request, response);
        }
    }
}
