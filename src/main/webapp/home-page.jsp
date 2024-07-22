<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomePage</title>
    <link rel="stylesheet" href="nav-style.css">
</head>
<body>
    <header>
        <img class="logo" src="images/Logo.png" alt="logo">
        <form>
            <div class="search">
                <input class="search-input" type="search" placeholder="Cerca...">
            </div>
        </form>
        <a class="cta" href="#"><button>Carrello</button></a>
        <c:if test="${empty User}">
            <a class="cta" href="login.jsp"><button>Login</button></a>
        </c:if>
        <c:if test="${not empty User}">
            <a class="cta" href="#"><button>Profilo</button></a>
        </c:if>
    </header>
</body>
</html>
