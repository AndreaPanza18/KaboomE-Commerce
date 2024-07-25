<%@ page import="Model.Articolo" %>
<%@ page import="Model.ArticoloDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    ArticoloDAO getArticolo = new ArticoloDAO();
    String str = request.getParameter("codice");
    long codice = Long.parseLong(str);
    Articolo articolo = getArticolo.getArticoloById(codice);
    session.setAttribute("articolo", articolo);
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${articolo.nome}</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/product-page-style.css">
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
<div class="categorie">
    <a href="articoli-categoria.jsp?categoria=fumetti">Fumetti</a>
    <a href="articoli-categoria.jsp?categoria=carte">Carte</a>
    <a href="articoli-categoria.jsp?categoria=action-figure">Action Figure</a>
</div>
<main>
    <div class="product-container">
        <div class="product-image">
            <img src="${articolo.urlImmagine}" alt="${articolo.nome}">
        </div>
        <div class="product-details">
            <h1>${articolo.nome}</h1>
            <p class="price">${articolo.prezzo}0 €</p>
            <form action="AddToCart" method="post">
                <input type="hidden" name="codice" value="${articolo.codice}">
                <button type="submit" class="btn-add-cart">Aggiungi al Carrello</button>
            </form>
            <form action="AddToWishlist" method="post">
                <input type="hidden" name="codice" value="${articolo.codice}">
                <button type="submit" class="add-to-wishlist">Aggiungi alla Wishlist</button>
            </form>
            <div class="description">
                <h2>Descrizione:</h2>
                <p>${articolo.descrizione}</p>
            </div>
        </div>
    </div>
</main>
</body>
</html>
