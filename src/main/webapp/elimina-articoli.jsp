<%@ page import="Model.ArticoloDAO" %>
<%@ page import="Model.Articolo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    ArticoloDAO getArticoli = new ArticoloDAO();
    List<Articolo> allArticoli = getArticoli.getAllArticoli();

    String path = application.getRealPath("/suggerimenti.txt");
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
        for(Articolo a : allArticoli){
            writer.write(a.getNome());
            writer.newLine();
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    session.setAttribute("allArticoli", allArticoli);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Elimina Articoli</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/elimina-articoli-style.css">
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
        <li>
            <a href="admin-page.jsp">Admin</a>
        </li>
    </ul>
    <div class="cta">
        <a href="cart.jsp">
            <button>Carrello</button>
        </a>
        <a href="profile-page.jsp">
            <button>Profilo</button>
        </a>
        <a href="admin-page.jsp">
            <button>Admin</button>
        </a>
    </div>
    <div onclick="showSidebar()" class="menu-button">
        <a href="#"><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e8eaed"><path d="M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z"/></svg></a>
    </div>
</header>
<main>
    <div class="categorie">
        <a href="aggiungi-articoli.jsp">Aggiungi Articoli</a>
        <a href="elimina-articoli.jsp">Elimina Articoli</a>
        <a href="see-utenti.jsp">Utenti</a>
    </div>
    <div class="elimina">
        <h2>Tutti gli articoli presenti nel database</h2>
        <table border="1">
            <thead>
            <tr>
                <th>Codice</th>
                <th>Nome Articolo</th>
                <th>Immagine</th>
                <th>Descrizione</th>
                <th>Data di Uscita</th>
                <th>Prezzo</th>
                <th>Elimina</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="articolo" items="${sessionScope.allArticoli}">
                <tr>
                    <td><c:out value="${articolo.codice}" /></td>
                    <td><a href="product-page.jsp?codice=${articolo.codice}"><c:out value="${articolo.nome}" /></a></td>
                    <td><img src="${articolo.urlImmagine}" alt="${articolo.nome}" style="width: 100px;" /></td>
                    <td class="col-limited-width"><c:out value="${articolo.descrizione}" /></td>
                    <td><c:out value="${articolo.data_uscita}" /></td>
                    <td><c:out value="${articolo.prezzo}" /></td>
                    <td>
                        <form action="DeleteArticolo" method="post" class="elimina-form">
                            <input type="hidden" name="codice" value="${articolo.codice}" />
                            <button type="submit">Elimina</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="popup" class="popup-container">
        <div class="popup-message">
            <p id="popup-text"></p>
            <button id="popup-btn">OK</button>
        </div>
    </div>

    <c:if test="${not empty sessionScope.successMessage}">
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var popup = document.getElementById("popup");
                var popupText = document.getElementById("popup-text");
                var popupBtn = document.getElementById("popup-btn");

                popupText.innerText = "${sessionScope.successMessage}";
                popup.classList.add("show");

                popupBtn.addEventListener("click", function () {
                    popup.classList.remove("show");
                });
            });
        </script>
        <c:remove var="successMessage" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.errorMessage}">
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var popup = document.getElementById("popup");
                var popupText = document.getElementById("popup-text");
                var popupBtn = document.getElementById("popup-btn");

                popupText.innerText = "${sessionScope.errorMessage}";
                popupText.style.color = "#f44336";
                popupBtn.style.backgroundColor = "#f44336";
                popup.classList.add("show");

                popupBtn.addEventListener("click", function () {
                    popup.classList.remove("show");
                });
            });
        </script>
        <c:remove var="errorMessage" scope="session"/>
    </c:if>
</main>
</body>
</html>
