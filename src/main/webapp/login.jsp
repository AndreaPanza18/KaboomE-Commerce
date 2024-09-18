<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/login-style.css">
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
<div class="container">
    <c:if test="${not empty loginError}">
        <div class="error-message">
            <p>${loginError}</p>
        </div>
    </c:if>
    <form action="Login" method="post">
        <h1>Accedi</h1>
        <div class="input-box">
            <label>Email</label>
            <input type="email" class="form-control" name="email" pattern="/^[\w\-\.]+@([\w-]+\.)+[\w-]{2,}$" required autofocus>
            <label>Password</label>
            <input type="password" class="form-control" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{};':&quot\\|,.<>\/?]).{8,}$" required>
        </div>
        <input type="submit" value="LOGIN" class="btn">
    </form>
    <div class="login-register">
        <p>Non hai ancora un account?</p> <a href="create-account.jsp" class="refister-link"> Registrati</a>
    </div>
</div>
</body>
</html>
