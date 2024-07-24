<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.ArticoloDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Articolo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Articoli</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/articoli-categoria-style.css">
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
    <div class="articoli-categoria">
        <c:choose>
            <c:when test="${param.categoria == 'fumetti'}">
                <h2>Tutti i nostri FUMETTI</h2>
            </c:when>
            <c:when test="${param.categoria == 'carte'}">
                <h2>Tutti le nostre CARTE</h2>
            </c:when>
            <c:when test="${param.categoria == 'action-figure'}">
                <h2>Tutti le nostre ACTION FIGURE</h2>
            </c:when>
            <c:otherwise>
                <h2>Categoria non trovata</h2>
            </c:otherwise>
        </c:choose>
        <div class="lista-articoli">
            <%
                String categoria = request.getParameter("categoria");
                ArticoloDAO getArticoli = new ArticoloDAO();
                List<Articolo> articoli = getArticoli.getArticoloByCategories(categoria);
                session.setAttribute("articoli", articoli);
            %>
            <c:forEach items="${articoli}" var="articolo">
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
                    <form action="AddToWishlist" method="post" class="item-form">
                        <input type="hidden", name="codice", value="${articolo.codice}">
                        <button type="submit", class="btn-add-wishlist">Aggiungi alla Wishlist</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
