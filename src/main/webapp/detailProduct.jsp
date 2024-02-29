<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.DAO.hibernateMethode" %>

<%
    // Récupérer l'ID du produit à partir de l'URL
    String productId = request.getParameter("productId");

    // Vérifier si l'ID du produit est présent dans l'URL
    if(productId != null && !productId.isEmpty()) {
        // Convertir l'ID du produit en entier
        int idProduit = Integer.parseInt(productId);

        // Récupérer les détails du produit en fonction de son ID
        Produit product = null;
        try {
            product = hibernateMethode.getProductById(idProduit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vérifier si le produit existe
        if(product != null) {
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails du produit</title>
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
                                <i class="fa fa-shopping-basket"></i> <span class="badge badge-primary">5</span>
                            </a>
                            <div class="dropdown-menu shopping-cart">
                                <ul>
                                    <li>
                                        <div class="drop-title">Your Cart</div>
                                    </li>
                                    <li>
                                        <div class="shopping-cart-list">
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="drop-title d-flex justify-content-between">
                                            <span>Total:</span>
                                            <span class="text-primary"><strong>Rp. 2000.000</strong></span>
                                        </div>
                                    </li>
                                    <li class="d-flex justify-content-between pl-3 pr-3 pt-3">
                                        <a href="cart.html" class="btn btn-default">View Cart</a>
                                        <a href="checkout.html" class="btn btn-primary">Checkout</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
    </div>
    <div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">Détails du produit</h1>
                     <p class="lead">
                        Save time and leave the groceries to us.
                    </p>
                </div>
            </div>
        </div>

        <div class="product-detail">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <img src="<%= product.getAdresseImageProduit() %>" alt="Image du produit" style="width: 100%;">
                    </div>
                    <div class="col-sm-6">
                        <h3><%= product.getNomProduit() %> (<%= product.getMarqueProduit() %>)</h3>
                        <p><strong>Prix:</strong> <%= product.getPrixProduit() %></p>
                        <!-- Autres détails du produit -->
                       <%--  <% if(product.promotion) { %>
                            <p><strong>Promotion:</strong> Yes (${product.pourcentagePromotion * 100}% off)</p>
                        <% } else { %>
                            <p><strong>Promotion:</strong> No</p>
                        <% } %> --%>
                        <p><strong>Nutriscore:</strong> <%= product.getNutriscore() %></p>
                        <p><strong>Category ID:</strong> <%= product.getCategorie().getIdCategorie() %></p>
                          <!-- Formulaire pour ajouter au panier -->
                        <form action="servletCentral" method="get">
							 <input type="hidden" name="method" value="addToCart">
							 <input type="hidden" name="productId" value="<%= product.getIdProduit() %>"> 
							 <p class="mb-1"><strong>Quantity</strong></p>
                            <div class="row mb-3">
                                <div class="col-sm-5">
                                    <input type="number" class="form-control" value="1" name="quantity" min="1">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="fa fa-shopping-basket"></i> Add to Cart
                            </button>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<%
        } else {
            // Le produit n'existe pas ou une erreur s'est produite lors de la récupération des détails du produit
            out.println("Le produit demandé n'existe pas.");
        }
    } else {
        // L'ID du produit n'est pas présent dans l'URL
        out.println("L'ID du produit n'a pas été spécifié dans l'URL.");
    }
%>
