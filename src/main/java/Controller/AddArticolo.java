package Controller;

import Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/AddArticolo")
public class AddArticolo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("addError", null);

        String codice = request.getParameter("codice");
        String nome = request.getParameter("nome");
        String prezzoStr = request.getParameter("prezzo");
        String dataUscita = request.getParameter("dataUscita");
        String descrizione = request.getParameter("descrizione");
        String personaggio = request.getParameter("personaggio");
        String materiale = request.getParameter("materiale");
        String idAutoreStr = request.getParameter("idAutore");
        String colorista = request.getParameter("colorista");
        String scrittore = request.getParameter("scrittore");
        String disegnatore = request.getParameter("disegnatore");
        String url = request.getParameter("urlImmagine");

        if (personaggio.isEmpty()){
            personaggio = null;
        }

        if (materiale.isEmpty()){
            materiale = null;
        }

        if (idAutoreStr.isEmpty()){
            idAutoreStr = null;
        }

        if(personaggio == null && materiale == null && idAutoreStr == null){
            request.setAttribute("addError", "Non hai inserito la categoria specifica dell'articolo");
            RequestDispatcher dispatcher = request.getRequestDispatcher("aggiungi-articoli.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if(personaggio != null && materiale != null && idAutoreStr != null){
            request.setAttribute("addError", "Devi scegliere una sola categoria specifica NON riempirle tutte!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("aggiungi-articoli.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if(personaggio != null && materiale != null && idAutoreStr == null ||
                personaggio != null && materiale == null && idAutoreStr != null ||
                personaggio == null && materiale != null && idAutoreStr != null){
            request.setAttribute("addError", "Devi scegliere una sola categoria specifica NON riempirle tutte!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("aggiungi-articoli.jsp");
            dispatcher.forward(request, response);
            return;
        }

        double prezzo = 0;
        if (!prezzoStr.isEmpty()) {
            prezzo = Double.parseDouble(prezzoStr);
        }

        AutoreDAO getAutore = new AutoreDAO();
        long idAutore = 0;
        if (!idAutoreStr.isEmpty()) {
            idAutore = Long.parseLong(idAutoreStr);
        }

        Articolo articolo = new Articolo();
        articolo.setCodice(Long.parseLong(codice));
        articolo.setNome(nome);
        articolo.setPrezzo(prezzo);
        articolo.setData_uscita(LocalDate.parse(dataUscita));
        articolo.setDescrizione(descrizione);
        articolo.setPersonaggio(personaggio);
        articolo.setMateriale(materiale);
        articolo.setUrlImmagine(url);
        if(getAutore.getAutoreFromId(idAutore) == null){
            Autore autore = new Autore();
            autore.setCodice(idAutore);
            autore.setColorista(colorista);
            autore.setScrittore(scrittore);
            autore.setDisegnatore(disegnatore);
            getAutore.createAutore(autore);
            articolo.setId_Autore(idAutore);
        } else {
            articolo.setId_Autore(idAutore);
        }

        ArticoloDAO getArticolo = new ArticoloDAO();
        getArticolo.addArticolo(articolo);

        response.sendRedirect("admin-page.jsp");
    }
}
