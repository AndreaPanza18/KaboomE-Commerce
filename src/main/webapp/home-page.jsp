<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomePage</title>
    <link rel="stylesheet" href="CSS/nav-style.css">
    <link rel="stylesheet" href="CSS/home-page.css">
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
    <div class="categorie">
        <a href="articoli.jsp?categoria=fumetti">Fumetti</a>
        <a href="articoli.jsp?categoria=carte">Carte</a>
        <a href="articoli.jsp?categoria=action-figure">Action Figure</a>
    </div>
    <div class="ultime-uscite">
        <h2>Ultime Uscite</h2>
        <div class="lista-articoli">
            <!--<c:forEach var="articolo" items="${ultimeUscite}">
                <div class="item">
                    <a href="prodotto.jsp?codice=${articolo.codiceABarre}">
                        <img src="${articolo.immagineUrl}" alt="${articolo.nome}" class="item-img">
                    </a>
                    <h3>${articolo.nome}</h3>
                    <p>Prezzo: ${articolo.prezzo} €</p>
                    <button class="btn-add-cart">Aggiungi al Carrello</button>
                    <button class="btn-add-wishlist">Aggiungi alla Wishlist</button>
                </div>
            </c:forEach>-->
        </div>
    </div>
</main>
</body>
</html>
