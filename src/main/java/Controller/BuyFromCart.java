package Controller;

import Model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/BuyFromCart")
public class BuyFromCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("User");

        if(user == null){
            response.sendRedirect("login.jsp");
        } else {
            CartDAO cartDAO = new CartDAO();
            cartDAO.deleteCart(user.getId_Utente());
            List<Articolo> cart = (List<Articolo>) session.getAttribute("Cart");

            PurchaseDAO purchase = new PurchaseDAO();
            purchase.Purchase(cart, user.getId_Utente(), LocalDate.now());
            List<Purchase> acquisti = purchase.getPurchase(user.getId_Utente());
            Collections.sort(acquisti, new Comparator<Purchase>() {
                @Override
                public int compare(Purchase a1, Purchase a2) {
                    return Double.compare(a2.getIdAcquisto(), a1.getIdAcquisto());
                }
            });

            session.setAttribute("Acquisti", acquisti);
            session.removeAttribute("Cart");
            response.sendRedirect("acquisto.jsp");
        }
    }
}
