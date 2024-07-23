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
    <link rel="stylesheet" href="CSS/articoli-style.css">
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
    <a class="cta" href="#">
        <button>Carrello</button>
    </a>
    <a class="cta" href="login.jsp">
        <button>Login</button>
    </a>
</header>
<main>
    <%
        String categoria = request.getParameter("categoria");
        ArticoloDAO getArticoli = new ArticoloDAO();
        List<Articolo> articoli = getArticoli.getArticoloByCategories(categoria);
    %>
    <div class="lista-articoli">
        <c:forEach var="articolo" items="${articoli}">
            <div class="item">

            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
