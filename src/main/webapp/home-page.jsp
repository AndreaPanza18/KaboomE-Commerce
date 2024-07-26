<%@ page import="Model.ArticoloDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Articolo" %>
<%@ page import="Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomePage</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/homepage-style.css">
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
        <a href="articoli-categoria.jsp?categoria=fumetti">Fumetti</a>
        <a href="articoli-categoria.jsp?categoria=carte">Carte</a>
        <a href="articoli-categoria.jsp?categoria=action-figure">Action Figure</a>
    </div>
    <div class="ultime-uscite">
        <h2>Ultime Uscite</h2>
        <div class="lista-articoli">
            <%
                ArticoloDAO getArticolo = new ArticoloDAO();
                List<Articolo> articoli = getArticolo.getLastdrop();
                session.setAttribute("ultimeUscite", articoli);
            %>
            <c:forEach items="${ultimeUscite}" var="articolo">
                <div class="item">
                    <a href="product-page.jsp?codice=${articolo.codice}">
                        <img src="${articolo.urlImmagine}" alt="${articolo.nome}" class="item-img">
                    </a>
                    <h3>${articolo.nome}</h3>
                    <p>${articolo.prezzo}0 €</p>
                    <form action="AddToCart" method="post" class="item-form">
                        <input type="hidden" name="codice" value="${articolo.codice}">
                        <button type="submit" class="btn-add-cart">Aggiungi al Carrello</button>
                    </form>
                    <c:choose>
                        <c:when test="${not empty Wishlist && fn:contains(codiciWishlist, articolo.codice)}">
                            <form action="RemoveFromWishlist" method="post" class="item-form">
                                <input type="hidden" name="codice" value="${articolo.codice}">
                                <button type="submit" class="btn-remove-wishlist">Rimuovi dalla Wishlist</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="AddToWishlist" method="post" class="item-form">
                                <input type="hidden" name="codice" value="${articolo.codice}">
                                <button type="submit" class="btn-add-wishlist">Aggiungi alla Wishlist</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
