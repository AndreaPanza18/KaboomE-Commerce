<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/categoris-style.css">
    <link rel="stylesheet" href="CSS/login-style.css">
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
    <c:if test="${empty User}">
        <a class="cta" href="login.jsp"><button>Login</button></a>
    </c:if>
    <c:if test="${not empty User}">
        <a class="cta" href="#"><button>Profilo</button></a>
    </c:if>
</header>
<div class="categorie">
    <a href="articoli.jsp?categoria=fumetti">Fumetti</a>
    <a href="articoli.jsp?categoria=carte">Carte</a>
    <a href="articoli.jsp?categoria=action-figure">Action Figure</a>
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
            <input type="email" class="form-control" name="email" pattern="^[\w\-\.]+@([\w-]+\.)+[\w-]{2,}$" required autofocus>
        </div>
        <div class="input-box">
            <label>Password</label>
            <input type="password" class="form-control" name="password" required>
        </div>
        <input type="submit" value="Login" class="btn">
    </form>
    <div class="login-register">
        <p>Non hai ancora un account?</p> <a href="create-account.jsp" class="refister-link"> Registrati</a>
    </div>
</div>
</body>
</html>
