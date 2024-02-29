<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Page d'achat</title>

</head>
<body>

	<jsp:include flush="true" page="head.jsp"></jsp:include>

	<% List<Produit> liste = (List<Produit>)request.getAttribute("liste_msg");%>

	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
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
                        for(Produit produit : liste) {
                            out.println("<div class=\"item\">");
                            out.println("    <div class=\"card card-product\">");
                            out.println("        <div class=\"card-ribbon\">");
                            out.println("            <div class=\"card-ribbon-container right\">");
                            out.println("                <span class=\"ribbon ribbon-primary\">SPECIAL</span>");
                            out.println("            </div>");
                            out.println("        </div>");
                            out.println("        <div class=\"card-badge\">");
                            out.println("            <div class=\"card-badge-container left\">");
                            out.println("                <span class=\"badge badge-default\">Promo</span>");
                            out.println("                <span class=\"badge badge-primary\">" + produit.getPourcentagePromotion()*100 + "% OFF</span>");
                            out.println("            </div>");
                            out.println("            <img src=\"" + produit.getAdresseImageProduit() + "\" alt=\"Product image\" class=\"card-img-top\">");
                            out.println("        </div>");
                            out.println("        <div class=\"card-body\">");
                            out.println("            <h4 class=\"card-title\">");
                            out.println("                <a href=\"detail-product.html?productId=" + produit.getIdProduit() + "\">" + produit.getNomProduit() + "</a>");
                            out.println("            </h4>");
                            out.println("            <div class=\"card-price\">");
                            out.println("                <span class=\"discount\">" + new java.text.DecimalFormat("#,###.00").format(produit.getPrixProduit() / (1 - produit.getPourcentagePromotion())) + "</span>");
                            out.println("                <span class=\"reguler\">" + new java.text.DecimalFormat("#,###.00").format(produit.getPrixProduit()) + "</span>");
                            out.println("            </div>");
                            out.println("            <a href=\"servletCentral?method=detailProduct&productId=" + produit.getIdProduit() + "\" class=\"btn btn-block btn-primary\">");
                            out.println("                Afficher les détails");
                            out.println("            </a>");
                            out.println("        </div>");
                            out.println("    </div>");
                            out.println("</div>");
	                        }
	                    %>
					</div>
				</div>
			</div>
		</div>
	</section>

	</div>
	<a href="servletCentral?method=accueil"><bouton class="bouton">Retour</bouton></a>

	<jsp:include flush="true" page="footer.jsp"></jsp:include>
</body>
</html>