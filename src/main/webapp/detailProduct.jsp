<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.DAO.ProduitDAO" %>

<%
    // Récupérer l'ID du produit à partir de l'URL
    String productId = request.getParameter("productId");

    // Vérifier si l'ID du produit est présent dans l'URL
    if(productId != null && !productId.isEmpty()) {
        try {
            // Convertir l'ID du produit en entier
            int idProduit = Integer.parseInt(productId);
            Produit product = ProduitDAO.getProductById(idProduit);

            if(product != null) {
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails du produit</title>
    <jsp:include flush="true" page="head.jsp"></jsp:include>
</head>
<body>

<div id="page-content" class="page-content">
    <div class="banner">
        <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
            <div class="container">
                <h1 class="pt-5">Détails du produit</h1>
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
                    <!-- Autres détails du produit -->
                    <h3><%= product.getNomProduit() %> (<%= product.getMarqueProduit() %>)</h3>
                    <p><strong>Prix:</strong> <%= product.getPrixProduit() %></p>
                    <!-- Autres détails du produit -->
                    <p><strong>Nutriscore:</strong> <%= product.getNutriscore() %></p>
                    <p><strong>Category ID:</strong> <%= product.getCategorie().getIdCategorie() %></p>
                    <p><strong>Description:</strong> <%= product.getDescription() %></p>
                    <!-- Quantity and Add to Cart Button -->
                    <form action="servletCentral" method="get">
                        <input type="hidden" name="method" value="addToCart">
                        <input type="hidden" name="productId" value="<%= productId %>">
                        <p class="mb-1"><strong>Quantité</strong></p>
                        <div class="row mb-3">
                            <div class="col-sm-5">
                                <input type="number" class="form-control" value="1" name="quantity" min="1">
                            </div>
                            <div class="col-sm-7">
                                <button type="submit" class="btn btn-primary btn-lg">
                                    <i class="fa fa-shopping-basket"></i> Ajouter au panier
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<footer>
    <!-- Footer content -->
</footer>

<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/jquery-migrate.js"></script>
<script type="text/javascript" src="assets/packages/bootstrap/libraries/popper.js"></script>
<script type="text/javascript" src="assets/packages/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="assets/packages/o2system-ui/o2system-ui.js"></script>
<script type="text/javascript" src="assets/packages/owl-carousel/owl-carousel.js"></script>
<script type="text/javascript" src="assets/packages/cloudzoom/cloudzoom.js"></script>
<script type="text/javascript" src="assets/packages/thumbelina/thumbelina.js"></script>
<script type="text/javascript" src="assets/packages/bootstrap-touchspin/bootstrap-touchspin.js"></script>
<script type="text/javascript" src="assets/js/theme.js"></script>
</body>
</html>
<%
            } else {
                // Le produit n'existe pas ou une erreur s'est produite lors de la récupération des détails du produit
                out.println("Le produit demandé n'existe pas.");
            }
        } catch (NumberFormatException e) {
            // Gérer l'erreur de conversion de l'ID du produit en entier
            out.println("L'ID du produit n'est pas valide.");
        }
    }
    // Si l'ID du produit est manquant ou vide dans l'URL
    else {
        out.println("L'ID du produit est manquant dans l'URL.");
    }
%>
