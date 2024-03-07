<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
<%@page import="Model.metier.Panier"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Locale"%>

<%@ page import="Model.metier.LignePanier"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@page import="Model.DAO.PanierDAO"%>
<% Panier panier = (Panier) session.getAttribute("Panier");%>

<%-- Récupération du total du panier depuis la requête --%>
<% Double totalPanier = (Double) request.getAttribute("totalPanier");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<link href="assets/fonts/sb-bistro/sb-bistro.css" rel="stylesheet"
	type="text/css">
<link href="assets/fonts/font-awesome/font-awesome.css" rel="stylesheet"
	type="text/css">

<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/o2system-ui/o2system-ui.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/owl-carousel/owl-carousel.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/cloudzoom/cloudzoom.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/thumbelina/thumbelina.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/css/theme.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    
    <link rel="stylesheet" href="assets/css/main1.css" />
</head>
<body>
<jsp:include flush="true" page="head.jsp"></jsp:include>
<!--  debut contenu de page d'achat -->
    <div id="page-content" class="page-content">
    	<div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">Page d'achat</h1>
                </div>
            </div>
        </div>

    <% List<Produit> liste = (List<Produit>)request.getAttribute("liste");%>
	
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
                                <div class="single-product" style="height: 100%; width: 410%;margin-top: 0px;">
                                    <div class="product-image" style="text-align: center;">
                                        <img src="<%= produit.getAdresseImageProduit() %>" alt="Product Image" style="width: 80%;">
                                        <div class="button" style="background-color: #FF2D2D;">
    <a href="detail?produitId=<%= produit.getIdProduit() %>" class="btn" style="font-size: 10px; background-color: #E91E63; color: white;">Détail</a>
</div>

                                    </div>
                                    <div class="product-info" style="text-align: center;">
                                        <h4 style="height: 65px; overflow: hidden;"><a href="detail?produitId=<%= produit.getIdProduit() %>" style="font-size: 10px; color: black;"><%= produit.getNomProduit() %></a>


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

	<script type="text/javascript" src="assets/js/jquery.js"></script>
	<script type="text/javascript" src="assets/js/jquery.js"></script>
	<script type="text/javascript" src="assets/js/jquery-migrate.js"></script>
	<script type="text/javascript" src="assets/js/drive.js"></script>
	<script type="text/javascript"
		src="assets/packages/bootstrap/libraries/popper.js"></script>
	<script type="text/javascript"
		src="assets/packages/bootstrap/bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/packages/o2system-ui/o2system-ui.js"></script>
	<script type="text/javascript"
		src="assets/packages/owl-carousel/owl-carousel.js"></script>
	<script type="text/javascript"
		src="assets/packages/cloudzoom/cloudzoom.js"></script>
	<script type="text/javascript"
		src="assets/packages/thumbelina/thumbelina.js"></script>
	<script type="text/javascript"
		src="assets/packages/bootstrap-touchspin/bootstrap-touchspin.js"></script>
	<script type="text/javascript" src="assets/js/theme.js"></script>
	<script type="text/javascript" src="assets/js/headJSP.js"></script>

</body>

</html>