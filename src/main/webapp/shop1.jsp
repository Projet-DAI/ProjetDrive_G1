<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/css/LineIcons.3.0.css" />
    <link rel="stylesheet" href="assets/css/tiny-slider.css" />
    <link rel="stylesheet" href="assets/css/glightbox.min.css" />
    <link rel="stylesheet" href="assets/css/main.css" />
    
    <link rel="stylesheet" href="assets/css/main1.css" />
</head>
<body>
  <jsp:include flush="true" page="head.jsp"></jsp:include>

    <% List<Produit> liste = (List<Produit>)request.getAttribute("liste");%>
	
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
                <h2 class="title">Produits en promotion</h2>
            </div>
            <div class="col-lg-9 col-12" style="max-width: 30000px; margin: 0 auto;">
                <div class="product-grids-head">
                    <div class="product-grid-topbar">
                        <div class="col-lg-7 col-md-8 col-12">
                            <div class="product-sorting">
                                <label for="sorting">Sort by:</label>
                                <select class="form-control" id="sorting">
                                    <option>Popularity</option>
                                    <option>Low - High Price</option>
                                    <option>High - Low Price</option>
                                    <option>Average Rating</option>
                                    <option>A - Z Order</option>
                                    <option>Z - A Order</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-grid" role="tabpanel" aria-labelledby="nav-grid-tab">
                        <div class="row" style="display: grid; grid-template-columns: repeat(3, 1fr); grid-gap: 1px;">
                            <% for (Produit produit : liste) { %>
                            <div class="col-lg-4 col-md-6 col-12" style="height: 100%; width:200%;">
                                <div class="single-product" style="height: 100%; width: 410%;">
                                    <div class="product-image" style="text-align: center;">
                                        <img src="<%= produit.getAdresseImageProduit() %>" alt="Product Image" style="width: 80%;">
                                        <div class="button" style="background-color: #FF2D2D;">
                                            <a href="detail?produitId=<%= produit.getIdProduit() %>" class="btn" style="font-size: 10px; background-color: #E91E63;">Détail</a>
                                        </div>
                                    </div>
                                    <div class="product-info" style="text-align: center;">
                                        <h4 style="height: 65px; overflow: hidden;">
                                           <a href="detail?produitId=<%= produit.getIdProduit() %>" style="font-size: 10px;"><%= produit.getNomProduit() %></a>

                                        </h4>
                                       <div style="height: 10px; display: flex; flex-direction: column; justify-content: center;">
    <span class="discount" style="margin-bottom: 5px;"><del><%= new java.text.DecimalFormat("#,###.00").format(produit.getPrixProduit()) %>&euro;</del></span>
    <span style="font-weight: bold; color: blue;"><%= new java.text.DecimalFormat("#,###.00").format(produit.getPrixProduit() * produit.getPourcentagePromotion()) %>&euro;</span>
</div>

                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<a href="#" class="scroll-top" style="background-color: #E91E63;">
    <i class="bi bi-arrow-up-short" style="background-color: #E91E63;"></i>
</a>

 <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/tiny-slider.js"></script>
    <script src="assets/js/glightbox.min.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>