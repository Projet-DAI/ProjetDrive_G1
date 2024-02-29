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
            product = hibernateMethode.getProduitById(idProduit);
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
