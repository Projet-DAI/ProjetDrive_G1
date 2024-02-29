<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Model.metier.Panier"%>
<%@page import="Model.metier.Produit"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.metier.LignePanier" %>
<%@ page import="java.util.ArrayList" %>

<% 
    // Récupérer le panier depuis la requête
    Panier panierAttribut = (Panier) request.getAttribute("panier");
    // Vérifier si le panier est null
    int panierSize = (panierAttribut != null) ? panierAttribut.getLignesPanier().size() : 0;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Freshcery | Groceries Organic Store</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="assets/fonts/sb-bistro/sb-bistro.css" rel="stylesheet" type="text/css">
    <link href="assets/fonts/font-awesome/font-awesome.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/o2system-ui/o2system-ui.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/owl-carousel/owl-carousel.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/cloudzoom/cloudzoom.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/thumbelina/thumbelina.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/css/theme.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

</head>
<body>
    <div class="page-header">
        <!--=============== Navbar ===============-->
        <nav class="navbar fixed-top navbar-expand-md navbar-dark bg-transparent" id="page-navigation">
            <div class="container">
                <!-- Navbar Brand -->
                <a href="index.html" class="navbar-brand">
                    <img src="assets/img/logo/logo.png" alt="">
                </a>

                <!-- Toggle Button -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarcollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarcollapse">
                    <!-- Navbar Menu -->
                    <ul class="navbar-nav ml-auto">
                        <li>
                            <div>
                                <form action="RechercheParMotCle" method="get">
                                    <input name="motcle" placeholder="Search..." style="background-color: transparent;color: white; height: 25px">
                                    <button type="submit" style="height:25px;"><i class="bi bi-search"></i></button>
                                </form>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="shop.html" class="nav-link">Faire ses courses</a>
                        </li>
                        <li class="nav-item">
                            <a href="register.html" class="nav-link">S'inscrire</a>
                        </li>
                        <li class="nav-item">
                            <a href="login.html" class="nav-link">Se connecter</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <div class="avatar-header"><img src="assets/img/logo/avatar.jpg"></div> Mon profil
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="transaction.html">Mon historique de commandes</a>
                                <a class="dropdown-item" href="setting.html">Paramètres</a>
                            </div>
                          </li>
                        <li class="nav-item dropdown">
                            <a href="javascript:void(0)" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-shopping-basket"></i> <span class="badge badge-primary"><%= panierSize %></span>
                            </a>
                            <div class="dropdown-menu shopping-cart">
                                <ul>
                                    <li>
                                        <div class="drop-title">Mon Panier</div>
                                    </li>
                                    <%-- Contenu du panier --%>
									<%
										Panier panier = (Panier) request.getAttribute("panier");
										List<LignePanier> listeLignesPanier = (panier != null) ? panier.getLignesPanier() : new ArrayList<>();
									%>
                                    <% if (listeLignesPanier != null && !listeLignesPanier.isEmpty()) { %>
                                        <% for (LignePanier lignePanier : listeLignesPanier) { %>
                                            <li>
                                                <div class="shopping-cart-list">
                                                    <div class="media">
                                                        <img class="d-flex mr-3" src="<%= lignePanier.getProduit().getAdresseImageProduit() %>" width="60">
                                                        <div class="media-body">
                                                            <h5><a href="javascript:void(0)"><%= lignePanier.getProduit().getNomProduit() %></a></h5>
                                                            <p class="price">
                                                                <span class="discount text-muted"><%= lignePanier.getProduit().getPrixProduit() %></span>
                                                                <span><%= lignePanier.getProduit().getPrixProduit() %></span>
                                                            </p>
                                                            <p class="text-muted">Qty: <%= lignePanier.getQuantite() %></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        <% } %>
                                        <li>
                                            <div class="drop-title d-flex justify-content-between">
                                                <%-- Calcul du total --%>
                                                <%
                                                    double totalPrice = 0.0;
                                                    for (LignePanier lignePanier : listeLignesPanier) {
                                                        totalPrice += lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite();
                                                    }
                                                %>
                                                <span>Total:</span>
                                                <span class="text-primary"><strong><%= totalPrice %> €</strong></span>
                                            </div>
                                        </li>
                                    <% } else { %>
                                        <li><p>Aucun produit dans le panier</p></li>
                                    <% } %>
                                    <%-- Fin contenu du panier --%>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</body>
</html>
