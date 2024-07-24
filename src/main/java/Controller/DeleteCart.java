package Controller;

import Model.CartDAO;
import Model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        User utente = (User) session.getAttribute("User");

        if(utente != null){
            CartDAO cartDAO = new CartDAO();
            cartDAO.deleteCart(utente.getId_Utente());
        }

        session.removeAttribute("Cart");
        response.sendRedirect("cart.jsp");
    }
}
