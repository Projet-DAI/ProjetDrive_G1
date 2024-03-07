<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.DAO.ProduitDAO" %>
<%@ page import="Model.metier.Produit" %>
<%@ page import="Model.metier.Categories" %>
<%@ page import="java.util.HashSet" %>
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
<html>
<head>
<meta charset="UTF-8">
<title>Produits du Rayon</title>
<script>
    function updateProducts() {
        var selectedCategory = document.getElementById("categories").value;
        window.location.href = "Rayon_PRO.jsp?rayonId=<%=rayonId %>&categorie=" + selectedCategory;
    }
</script>
<link rel="stylesheet" href="assets/css/main1.css" />
</head>
<body>
<jsp:include flush="true" page="head.jsp"></jsp:include>
<div class="col-md-12" style="padding-top: 90px;">

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


<section class="product-grids section" id="most-wanted">
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
            </div>
            <div class="col-lg-9 col-12" style="max-width: 30000px; margin: 0 auto;border:0">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-grid" role="tabpanel" aria-labelledby="nav-grid-tab">
                        <div class="row" style="display: grid; grid-template-columns: repeat(3, 1fr); grid-gap: 1px;">
                            <%
                            HashSet<Integer> displayedProductIds = new HashSet<>();
                            if (listeProduits.isEmpty()) {
                        %>
                            <p>Aucun produit n'est disponible pour le moment.</p>
                        <%
                            } else {
                                for (Produit produit : listeProduits) {
                                    if (displayedProductIds.add(produit.getIdProduit())) {
    
                        %>
                            <div class="col-lg-4 col-md-6 col-12" style="height: 70%; width:100%;">
                                <div class="single-product" style="height: 50%; width: 400%;padding: 0px;">
                                    <div class="product-image" style="text-align: center">
                                        <a href="detail?produitId=<%= produit.getIdProduit() %>"></a>
                                        <img src="<%= produit.getAdresseImageProduit() %>" alt="Product image" class="card-img-top">
                                         <div class="button" style="background-color: #FF2D2D;">
    <a href="detail?produitId=<%= produit.getIdProduit() %>" class="btn" style="font-size: 10px; background-color: #E91E63; color: white;">Détail</a>
</div>
                                    </div>
                                    <div class="product-info" style="text-align: center;"style="width: 400%;">
                                        <h4 style="height: 65px; overflow: hidden;"><a href="detail?produitId=<%= produit.getIdProduit() %>" style="font-size: 10px; color: black;"><%= produit.getNomProduit() %></a>


                                        </h4>
                                        <div style="height: 30px; display: flex; flex-direction: column; justify-content: center;">
                                            <div style="height: 10px; display: flex; flex-direction: column; justify-content: center;">
                                                <span class="discount" style="margin-bottom: 5px;"><%= new java.text.DecimalFormat("#,###.00").format(produit.getPrixProduit()) %>&euro;</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%
                                    } 
                                } 
                            } 
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
