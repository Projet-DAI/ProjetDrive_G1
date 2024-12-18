<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@page import="Model.metier.Produit"%>
<%@page import="Model.metier.Categories"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Page d'achat</title>

</head>
<body>
    <jsp:include flush="true" page="head.jsp"></jsp:include>

<%
    List<Produit> liste = (List<Produit>) request.getAttribute("liste_msg");
    List<Produit> listeFiltree = new ArrayList<>();

    for (Produit produit : liste) {
        Categories categorie = produit.getCategorie();
        if (categorie != null && categorie.getIdCategorie() == 1 ) {
            listeFiltree.add(produit);
        }
    }
%>

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
                <h2 class="title">Produits en promotion</h2>
                <div class="product-carousel owl-carousel">
                    <%
    for(Produit produit : listeFiltree) {
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
                    <span class="badge badge-primary"><%= produit.getPourcentagePromotion()*100 %>% OFF</span>
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
    </div>
<%
    }
%>

                </div>
            </div>
        </div>
    </div>
</section>



    <jsp:include flush="true" page="footer.jsp"></jsp:include>
</body>
</html>


<style>
        .card-badge {
            height: 250px;
        }
    </style>
