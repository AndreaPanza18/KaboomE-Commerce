<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grazie per aver acquisato!</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet", href="CSS/acquisto-style.css">
    <script type="text/javascript">
        function redirectAfterTimeout() {
            setTimeout(function() {
                window.location.href = "home-page.jsp";
            }, 5000);
        }
    </script>
</head>
<body onload="redirectAfterTimeout()">
<header>
    <a href="home-page.jsp">
        <img class="logo" src="images/Logo.png" alt="logo">
    </a>
    <form action="search-results.jsp" method="get" class="search-form">
        <div class="search">
            <input class="search-input" type="search" name="query" placeholder="Cerca..." required>
            <div class="suggestions"></div>
        </div>
        <button type="submit" class="search-button">Cerca</button>
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
<div class="scritte">
    <h2>Grazie per l'acquisto!</h2>
    <h3>Sappi che i tuoi soldi stanno finanziando l'impero galattico</h3>
    <h3>Stai per tornare alla homepage tra 5 secondi...</h3>
</div>
</body>
</html>
