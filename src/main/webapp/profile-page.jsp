<%@ page import="java.util.List" %>
<%@ page import="Model.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User utente = (User) session.getAttribute("User");
    if(utente.getPermission()){
        session.setAttribute("permission", true);
    } else {
        session.setAttribute("permission", false);
    }
%>

<%
    DecimalFormat df = new DecimalFormat("#.0");
    List<Purchase> acquisti = (List<Purchase>) session.getAttribute("Acquisti");
    for(int i = 0; i < acquisti.size(); i++){
        Purchase acquisto = acquisti.get(i);
        String totaleFormattato = df.format(acquisto.getPrezzoTotale());
        double totale = Double.parseDouble(totaleFormattato.replace(',', '.'));
        acquisti.get(i).setPrezzoTotale(totale);
    }
    session.setAttribute("Acquisti", acquisti);
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProfilePage</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/profile-page-style.css">
</head>
<body>
<header>
    <a href="home-page.jsp">
        <img class="logo" src="images/Logo.png" alt="logo">
    </a>
    <form>
        <div class="search">
            <input class="search-input" type="search" placeholder="Cerca...">
        </div>
    </form>
    <div class="cta">
        <a href="cart.jsp">
            <button>Carrello</button>
        </a>
        <form action="Logout" method="post">
            <button type="submit" class="cta">Logout</button>
        </form>
        <c:if test="${permission}">
            <a href="#">
                <button>Admin</button>
            </a>
        </c:if>
    </div>
</header>
<div class="categorie">
    <a href="articoli-categoria.jsp?categoria=fumetti">Fumetti</a>
    <a href="articoli-categoria.jsp?categoria=carte">Carte</a>
    <a href="articoli-categoria.jsp?categoria=action-figure">Action Figure</a>
</div>
<main>
    <div class="wishlist">
        <h2>La tua WISHLIST</h2>
        <c:if test="${empty Wishlist}">
            <h3>La Wishlist è vuota... Sicuro che non desideri niente ;)</h3>
        </c:if>
        <c:if test="${not empty Wishlist}">
            <div class="lista-articoli">
                <%
                    WishlistDAO getWishlist = new WishlistDAO();
                    List<Articolo> articoli = getWishlist.getWishlist(utente.getId_Utente());
                    session.setAttribute("wishlist", articoli);
                %>
                <c:forEach items="${wishlist}" var="articolo">
                    <div class="item">
                        <a href="product-page.jsp?codice=${articolo.codice}">
                            <img src="${articolo.urlImmagine}" alt="${articolo.nome}" class="item-img">
                        </a>
                        <h3>${articolo.nome}</h3>
                        <p>${articolo.prezzo}0 €</p>
                        <form action="AddToCart" method="post" class="item-form">
                            <input type="hidden", name="codice", value="${articolo.codice}">
                            <button type="submit" class="btn-add-cart">Aggiungi al Carrello</button>
                        </form>
                        <form action="RemoveFromWishlist" method="post" class="item-form">
                            <input type="hidden", name="codice", value="${articolo.codice}">
                            <button type="submit", class="btn-add-wishlist">Rimuovi dalla Wishlist</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <div class="acquisti">
        <h2>Tutti i tuoi acquisti precedenti:</h2>
        <c:if test="${empty Acquisti}">
            <h3>Non hai ancora acquistato niente... Affrettati prima che finiamo le scorte!</h3>
        </c:if>
        <c:if test="${not empty Acquisti}">
            <table border="1">
                <thead>
                <tr>
                    <th>Data Acquisto</th>
                    <th>Prezzo Totale Acquisto</th>
                    <th>Immagine</th>
                    <th>Nome Articolo</th>
                    <th>Prezzo</th>
                    <th>Quantità</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="acquisto" items="${sessionScope.Acquisti}">
                    <c:forEach var="articolo" items="${acquisto.articoli}">
                        <tr>
                            <td><c:out value="${acquisto.dataAcquisto}" /></td>
                            <td><c:out value="${acquisto.prezzoTotale}0 €" /></td>
                            <td><img src="${articolo.urlImmagine}" alt="${articolo.nome}" style="width: 100px;" /></td>
                            <td><c:out value="${articolo.nome}" /></td>
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
