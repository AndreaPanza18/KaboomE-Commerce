<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CreateAccount</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categories-style.css">
    <link rel="stylesheet" href="CSS/create-style.css">
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
    </div>
</header>
<div class="categorie">
    <a href="articoli-categoria.jsp?categoria=fumetti">Fumetti</a>
    <a href="articoli-categoria.jsp?categoria=carte">Carte</a>
    <a href="articoli-categoria.jsp?categoria=action-figure">Action Figure</a>
</div>
<div class="container">
    <form action="CreateAccount" method="post">
        <h1>Registrati</h1>
        <div class="input-box">
            <label>Nome</label>
            <input type="text" class="form-control" name="nome" required>
            <label>Cognome</label>
            <input type="text" class="form-control" name="cognome" required>
            <label>Email</label>
            <input type="email" class="form-control" name="email" pattern="^[\w\-\.]+@([\w-]+\.)+[\w-]{2,}$" required autofocus>
            <label>Password</label>
            <input type="password" class="form-control" name="password" required>
        </div>
        <input type="submit" value="INVIA" class="btn">
    </form>
</div>
</body>
</html>
