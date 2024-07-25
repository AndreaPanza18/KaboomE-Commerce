package Controller;

import Model.User;
import Model.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User user = new User();
        UserDAO newUser = new UserDAO();
        user.setNome(request.getParameter("nome"));
        user.setCognome(request.getParameter("cognome"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        if(newUser.createAccount(user)){
            HttpSession session = request.getSession(true);
            user = newUser.loginUser(request.getParameter("email"), request.getParameter("password"));
            session.setAttribute("User", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.jsp");
            dispatcher.forward(request, response);
        } else {
            out.println("<html><body>");
            out.println("<p>ERROR: INVALID INPUT</p>");
            out.println("</html></body>");
        }
   }
}
