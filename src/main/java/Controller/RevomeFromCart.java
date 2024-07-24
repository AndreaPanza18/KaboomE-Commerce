package Controller;

import Model.Articolo;
import Model.ArticoloDAO;
import Model.CartDAO;
import Model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/RemoveFromCart")
public class RevomeFromCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = request.getParameter("codice");
        long codice = Long.parseLong(str);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("User");

        List<Articolo> cartList = (List<Articolo>) session.getAttribute("Cart");

        if(user != null){
            CartDAO removeCart = new CartDAO();
            removeCart.removeFromCart(user.getId_Utente(), codice, cartList);
            ArticoloDAO articoloDao = new ArticoloDAO();
            Articolo articolo = articoloDao.getArticoloById(codice);
            for(Articolo a : cartList){
                if(a.getCodice() == articolo.getCodice()){
                    cartList.remove(a);
                    break;
                }
            }
            session.setAttribute("Cart", cartList);
        } else {
            ArticoloDAO articoloDao = new ArticoloDAO();
            Articolo articolo = articoloDao.getArticoloById(codice);
            for(Articolo a : cartList){
                if(a.getCodice() == articolo.getCodice()){
                    cartList.remove(a);
                    break;
                }
            }
            session.setAttribute("Cart", cartList);
        }
        response.sendRedirect("cart.jsp");
    }
}
