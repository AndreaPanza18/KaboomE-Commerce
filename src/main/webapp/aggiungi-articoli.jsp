<%@ page import="java.util.List" %>
<%@ page import="Model.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Articoli</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/aggiungi-articoli-style.css">
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
            <button type="submit" class="search-button">Cerca</button>
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
        <a href="aggiungi-articoli.jsp">Aggiungi Articoli</a>
        <a href="#">Elimina Articoli</a>
        <a href="see-utenti.jsp">Utenti</a>
    </div>
    <div class="aggiungi">
        <h2>Inserisci i dati dell'articolo da aggiungere</h2>
        <c:if test="${not empty addError}">
            <div class="error-message">
                <p>${addError}</p>
            </div>
        </c:if>
        <form action="AddArticolo" method="post" class="input">
            <label>Codice a barre </label>
            <input type="text" name="codice" required><br>
            <label>Nome </label>
            <input type="text" name="nome" required><br>
            <label>Prezzo </label>
            <input type="number" step="0.01" name="prezzo" required><br>
            <label>Data di uscita </label>
            <input type="date" name="dataUscita" required><br>
            <label>Descrizione </label>
            <input type="text" name="descrizione" required><br>
            <lable>I prossimi campi non compilarli tutti: solo quelli necessari per l'articolo</lable>
            <label>Personaggio action figure </label>
            <input type="text" name="personaggio"><br>
            <label>Materiale carta </label>
            <input type="text" name="materiale"><br>
            <label>ID Autori </label>
            <input type="number" name="idAutore"><br>
            <label>Colorista </label>
            <input type="text" name="colorista"><br>
            <label>Scrittore </label>
            <input type="text" name="scrittore"><br>
            <label>Disegnatore </label>
            <input type="text" name="disegnatore"><br>
            <label>URL Immagine </label>
            <input type="text" name="urlImmagine" required><br><br>
            <input type="submit" value="Add" class="btn">
        </form>
    </div>
</main>
</body>
</html>
