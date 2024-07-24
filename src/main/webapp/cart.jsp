<%@ page import="Model.ArticoloDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Articolo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/cart-style.css">
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
<main>
    <div class="categorie">
        <a href="articoli-categoria.jsp?categoria=fumetti">Fumetti</a>
        <a href="articoli-categoria.jsp?categoria=carte">Carte</a>
        <a href="articoli-categoria.jsp?categoria=action-figure">Action Figure</a>
    </div>
    <div class="cart-list">
        <h2>Articoli nel carrello</h2>
        <div class="lista-articoli">
            <c:if test="${empty Cart}">
                <h3>Il tuo carrello si sente vuoto... Vai a scegliere qualche articolo da inserirci!</h3>
            </c:if>
            <c:if test="${not empty Cart}">
                <c:forEach items="${Cart}" var="articolo">
                    <div class="item">
                        <a href="product-page.jsp?codice=${articolo.codice}">
                            <img src="${articolo.urlImmagine}" alt="${articolo.nome}" class="item-img">
                        </a>
                        <h3>${articolo.nome}</h3>
                        <p>${articolo.prezzo}0 €</p>
                        <form action="AddToWishlist" method="post" class="item-form">
                            <input type="hidden", name="codice", value="${articolo.codice}">
                            <button type="submit", class="btn-add-wishlist">Aggiungi alla wishlist</button>
                        </form>
                        <form action="RemoveFromCart" method="post" class="item-form">
                            <input type="hidden", name="codice", value="${articolo.codice}">
                            <button type="submit", class="btn-remove">Rimuovi</button>
                        </form>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="footer-bar">
        <div class="subtotal">
            Subtotale:
        </div>
        <div class="buttons">
            <form action="" method="">
                <button type="submit">Acquista</button>
            </form>
            <form action="" method="">
                <button type="submit">Svuota Carrello</button>
            </form>
        </div>
    </div>
</main>
</body>
</html>
