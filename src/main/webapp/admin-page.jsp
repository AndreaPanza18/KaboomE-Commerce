<%@ page import="java.util.List" %>
<%@ page import="Model.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    PurchaseDAO getAcquisti = new PurchaseDAO();
    List<Purchase> acquisti = getAcquisti.getAllAcquisti();
    if(acquisti == null){
        session.setAttribute("allAcquisti", acquisti);
    } else {
        DecimalFormat df = new DecimalFormat("#.0");
        for(int i = 0; i < acquisti.size(); i++){
            Purchase acquisto = acquisti.get(i);
            String totaleFormattato = df.format(acquisto.getPrezzoTotale());
            double totale = Double.parseDouble(totaleFormattato.replace(',', '.'));
            acquisti.get(i).setPrezzoTotale(totale);
        }
        session.setAttribute("allAcquisti", acquisti);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/admin-page-style.css">
    <script src="JS/search.js" defer></script>
</head>
<body>
<header>
    <a href="home-page.jsp">
        <img class="logo" src="images/Logo.png" alt="logo">
    </a>
    <form action="search-results.jsp" method="get" class="search-form">
        <div class="search">
            <input class="search-input" type="search" id="search-input" name="query" placeholder="Cerca..." required>
            <button type="submit" class="search-button">Cerca</button>
            <div class="suggestions" id="results"></div>
        </div>
    </form>
    <div class="cta">
        <a href="cart.jsp">
            <button>Carrello</button>
        </a>
        <c:if test="${empty User}">
            <a href="login.jsp">
                <button>Login</button>
            </a>
        </c:if>
        <c:if test="${not empty User}">
            <a href="profile-page.jsp">
                <button>Profilo</button>
            </a>
        </c:if>
    </div>
</header>
<main>
    <div class="categorie">
        <a href="aggiungi-articoli.jsp">Aggiungi Articoli</a>
        <a href="elimina-articoli.jsp">Elimina Articoli</a>
        <a href="see-utenti.jsp">Utenti</a>
    </div>
    <div class="acquisti">
        <h2>Tutti gli acquisti effettuati dagli utenti:</h2>
        <c:if test="${empty allAcquisti}">
            <h3>Nessun utente ha ancora acquistato niente nel sito</h3>
        </c:if>
        <c:if test="${not empty allAcquisti}">
            <table border="1">
                <thead>
                <tr>
                    <th>ID Utente</th>
                    <th>Codice Acquisto</th>
                    <th>Data Acquisto</th>
                    <th>Prezzo Totale Acquisto</th>
                    <th>Immagine</th>
                    <th>Nome Articolo</th>
                    <th>Prezzo</th>
                    <th>Quantità</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="acquisto" items="${sessionScope.allAcquisti}">
                    <c:forEach var="articolo" items="${acquisto.articoli}">
                        <tr>
                            <td><c:out value="${acquisto.idUtente}" /></td>
                            <td><c:out value="${acquisto.idAcquisto}" /></td>
                            <td><c:out value="${acquisto.dataAcquisto}" /></td>
                            <td><c:out value="${acquisto.prezzoTotale}0 €" /></td>
                            <td><img src="${articolo.urlImmagine}" alt="${articolo.nome}" style="width: 100px;" /></td>
                            <td><a href="product-page.jsp?codice=${articolo.codice}"><c:out value="${articolo.nome}" /></a></td>
                            <td><c:out value="${articolo.prezzo}0 €" /></td>
                            <td><c:out value="${articolo.quantita}" /></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</main>
</body>
</html>
