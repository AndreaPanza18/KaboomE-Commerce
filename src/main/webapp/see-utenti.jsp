<%@ page import="Model.UserDAO" %>
<%@ page import="Model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    UserDAO getUtenti = new UserDAO();
    List<User> utenti = getUtenti.getAllUser();
    session.setAttribute("allUtenti", utenti);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utenti</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/see-utenti-style.css">
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
    <div class="see-utenti">
        <h2>Tutti gli Utenti creati nel sito</h2>
        <c:if test="${empty allUtenti}">
            <h3>Non è stato creato ancora nessun utente</h3>
        </c:if>
        <c:if test="${not empty allUtenti}">
            <c:forEach var="user" items="${allUtenti}">
                <div class="user-container">
                    <div class="user-header">
                        <span>ID Utente: ${user.id_Utente}</span>
                        <span class="arrow">&#9662;</span>
                    </div>
                    <div class="user-details">
                        <p><strong>Nome:</strong> ${user.nome}</p>
                        <p><strong>Cognome:</strong> ${user.cognome}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Permission:</strong> ${user.permission ? 'Admin' : 'User'}</p>
                        <div class="button-group">
                            <form action="DeleteUser" method="post" style="display: inline;">
                                <input type="hidden" name="codice" value="${user.id_Utente}">
                                <button type="submit" class="delete-btn">Elimina Utente</button>
                            </form>
                            <form action="ToggleAdmin" method="post" style="display: inline;">
                                <input type="hidden" name="codice" value="${user.id_Utente}">
                                <button type="submit" class="admin-btn">
                                        ${user.permission ? 'Rimuovi Permessi Admin' : 'Diventa Admin'}
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <script>
                const userContainers = document.querySelectorAll('.user-container');

                userContainers.forEach(container => {
                    const header = container.querySelector('.user-header');
                    const details = container.querySelector('.user-details');
                    const arrow = container.querySelector('.arrow');

                    header.addEventListener('click', () => {
                        const isOpen = details.style.display === 'block';
                        details.style.display = isOpen ? 'none' : 'block';
                        header.classList.toggle('open', !isOpen);
                    });
                });
            </script>
        </c:if>
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
