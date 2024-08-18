<%@ page import="Model.ArticoloDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Articolo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risultati della Ricerca</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/search-result-style.css">
    <script src="JS/search.js" defer></script>
    <script src="JS/navbar.js" defer></script>
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
    <ul class="sidebar">
        <li onclick="hideSidebar()"><a href="#"><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e8eaed"><path d="m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z"/></svg></a></li>
        <li><a href="home-page.jsp">Homepage</a></li>
        <li><a href="cart.jsp">Carrello</a></li>
        <li><c:if test="${empty User}">
            <a href="login.jsp">Login</a>
        </c:if>
            <c:if test="${not empty User}">
                <a href="profile-page.jsp">Profilo</a>
            </c:if>
        </li>
    </ul>
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
    <div onclick="showSidebar()" class="menu-button">
        <a href="#"><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e8eaed"><path d="M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z"/></svg></a>
    </div>
</header>
<div class="categorie">
    <a href="articoli-categoria.jsp?categoria=fumetti">Fumetti</a>
    <a href="articoli-categoria.jsp?categoria=carte">Carte</a>
    <a href="articoli-categoria.jsp?categoria=action-figure">Action Figure</a>
</div>
<main>
    <c:if test="${not empty notification}">
        <div id="notificationMessage" class="fade-in-out">
                ${notification}
        </div>
        <script>
            document.getElementById('notificationMessage').classList.add('show');

            setTimeout(function() {
                document.getElementById('notificationMessage').classList.remove('show');
            }, 5000);

            setTimeout(function() {
                document.getElementById('notificationMessage').style.display = 'none';
            }, 5500);
        </script>
        <%
            session.setAttribute("notification", null);
        %>
    </c:if>
    <div class="articoli-results">
        <h2>Risultati della Ricerca</h2>
        <div class="lista-articoli">
            <%
                String query = request.getParameter("query");
                ArticoloDAO getArticolo = new ArticoloDAO();
                List<Articolo> articoli = getArticolo.getArticoliByName(query);
                session.setAttribute("parametroRicerca", query);
                session.setAttribute("risultatiRicerca", articoli);
            %>
            <c:if test="${empty risultatiRicerca}">
                <h3>Non abbiamo trovato nessun corrispondente di "${parametroRicerca}"</h3>
            </c:if>
            <c:if test="${not empty risultatiRicerca}">
                <c:forEach items="${risultatiRicerca}" var="articolo">
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
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
