<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.DAO.ProduitDAO" %>
<%@ page import="Model.metier.Produit" %>
<%@ page import="Model.metier.Categories" %>
<%@ page import="java.util.List" %>

<%
    ProduitDAO produitDAO = new ProduitDAO(); 
    int rayonId = Integer.parseInt(request.getParameter("rayonId"));
    String nomRayon = produitDAO.getNomRayonById(rayonId);
    List<String> categories = produitDAO.getCategoriesByRayon(rayonId);
    String selectedCategory = request.getParameter("categorie");
    if (selectedCategory == null && !categories.isEmpty()) {
        selectedCategory = categories.get(1);  
    }
    List<Produit> listeProduits = produitDAO.getProduitsByRayonAndCategory(rayonId, selectedCategory);
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produits du Rayon</title>
    
    <script>
        function updateProducts() {
            var selectedCategory = document.getElementById("categories").value;
            window.location.href = "rayon_products.jsp?rayonId=<%= rayonId %>&categorie=" + selectedCategory;
        }
    </script>
</head>
<body>

        <jsp:include flush="true" page="head.jsp"></jsp:include>
        
            <div id="page-content" class="page-content">
    	<div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">Page d'achat</h1>
                </div>
            </div>
        </div>

        <jsp:include flush="true" page="rayon.jsp"></jsp:include>

    </div>
 <section id="most-wanted">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
			  <h2 class="title">Produits du Rayon - <%= nomRayon %>  </h2>
			    <label for="categories">Choisir une catégorie :</label>
			    <select id="categories" name="categories" onchange="updateProducts()">
			        <%
			            if (categories.isEmpty()) {
			        %>
			                <p>Aucune categorie n'est disponible pour le moment.</p>
			        <%
			            } else {
			                for (String category : categories) {
			        %>
			                    <option value="<%= category %>" <%= category.equals(selectedCategory) ? "selected" : "" %>><%= category %></option>
			        <%
			                }
			            }
			        %>
			    </select>               
                <div class="product-carousel owl-carousel">
            
					<%					
					    if (listeProduits.isEmpty()) {
					%>
					    <p>Aucun produit n'est disponible pour le moment.</p>
					<%
					    } else {
					        for (Produit produit : listeProduits) {
					%>
						<div class="item">
					        <div class="card card-product">
					            <div class="card-ribbon">
					                <div class="card-ribbon-container right">
					                    <span class="ribbon ribbon-primary">SPECIAL</span>
					                </div>
					            </div>
					            <div class="card-badge">
					                <div class="card-badge-container left">
					                    <span class="badge badge-default">Promo</span>
					                    <span class="badge badge-primary">OFF</span>
					                </div>
					                <a href="detail?productId=<%= produit.getIdProduit() %>">
					                    <img src="<%= produit.getAdresseImageProduit() %>" alt="Product image" class="card-img-top">
					                </a>
					            </div>
					            <div class="card-body">
					                <a href="servletCentral?method=ajouterList&productId=<%= produit.getIdProduit() %>">
					                    <svg class="bi bi-archive-fill text-danger" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					                        <path fill-rule="evenodd" d="M12.643 15C13.979 15 15 13.845 15 12.5V5H1v7.5C1 13.845 2.021 15 3.357 15h9.286zM6 7a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1H6zM.8 1a.8.8 0 0 0-.8.8V3a.8.8 0 0 0 .8.8h14.4A.8.8 0 0 0 16 3V1.8a.8.8 0 0 0-.8-.8H.8z"/>
					                    </svg>
					                </a>
					                <h4 class="card-title">
					                    <a href="detail?productId=<%= produit.getIdProduit() %>"><%= produit.getNomProduit() %></a>
					                </h4>
					                <div class="card-price">
					                    <span class="discount"><%= new java.text.DecimalFormat("#,###.00").format(produit.getPrixProduit() / (1 - produit.getPourcentagePromotion())) %></span>
					                
					                    <span class="reguler"><%= new java.text.DecimalFormat("#,###.00").format(produit.getPrixProduit()) %></span>
					                </div>
					                <a href="ServletPanier?method=ajouterPanier&productId=<%= produit.getIdProduit() %>" class="btn btn-block btn-primary">
					                    Ajouter au panier
					                </a>
					            </div>
					        </div>
					    </div>					    <%
					            }
					        }
					    %>

                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="footer.jsp" %> 

</body>
</html>

<style>
    .card-badge {
        height: 250px; 
    }
</style>
