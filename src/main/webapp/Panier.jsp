<!-- Supprimez le script JavaScript existant pour l'événement 'voirPointsFidelitebtn' -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Panier"%>
<%@ page import="Model.metier.LignePanier"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.DAO.ClientDAO"%>
<%@ page import="Model.DAO.PanierDAO"%>

<%-- Récupération du panier depuis la session --%>

<%
Panier panier = (Panier) session.getAttribute("panier");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

<title>Mon Panier</title>
<head>
<title>Freshcery | Groceries Organic Store</title>


</head>
<body>

	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Mon panier</h1>
					<p class="lead">Économisez du temps et confiez-nous vos courses
					</p>
				</div>
			</div>
		</div>

		<jsp:include flush="true" page="head.jsp"></jsp:include>

		<section id="cart">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<button id="voirPointsFidelitebtn" class="btn btn-primary"
							onclick="calculerNouveauTotal(<%=new ClientDAO().getPointsFideliteById(1)%>);">Débloquer
							mes points de fidélité</button>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th width="10%"></th>
										<th>Produits</th>
										<th>Prix unitaire</th>
										<th width="15%">Quantité</th>
										<th>Sous-total</th>
										<th></th>
									</tr>
								</thead>
								<%-- Boucle pour afficher chaque produit dans le panier --%>
								<%
								if (panier != null && !panier.getLignesPanier().isEmpty()) {
								%>
								<tbody>

									<%
									for (LignePanier lignePanier : panier.getLignesPanier()) {
									%>
									<tr>
										<td></td>
										<td><%=lignePanier.getProduit().getNomProduit()%></td>
										<td><%=lignePanier.getProduit().getPrixProduit()%></td>
										<td>
											<form action="ModifierQuantitePanierServlet" method="post">
												<input type="hidden" name="panierId"
													value="<%=panier.getIdPanier()%>"> <input
													type="hidden" name="produitId"
													value="<%=lignePanier.getProduit().getIdProduit()%>">
												<input class="vertical-spin form-control input-number"
													type="text" name="nouvelleQuantite"
													value="<%=lignePanier.getQuantite()%>" min="1">
												<button type="submit" class="btn btn-primary">Modifier</button>
											</form>
										</td>
										<td><%=lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite()%></td>
										<td><a href="#" class="text-danger"
											onclick="supprimerLignePanier('<%=lignePanier.getProduit().getIdProduit()%>')"><i
												class="fa fa-times"></i></a></td>
										<script>
														function supprimerLignePanier(idProduit) {
														    var idPanier = <%=panier.getIdPanier()%>;
														    if (confirm("Voulez-vous vraiment supprimer cet article du panier?")) {
														        window.location.href = 'SupprimerLignePanierServlet?idPanier=' + idPanier + '&idProduit=' + idProduit;
														    }
														}
														</script>
									</tr>
									<%
									}
									%>
								</tbody>

								<%
								} else {
								%>
								<tr>
									<td colspan="4">Le panier est vide.</td>
								</tr>
								<%
								}
								%>

							</table>
						</div>
					</div>
					<div class="col">
						<a href="index.jsp" class="btn btn-default">Continuer mes
							achats</a>
					</div>

					<%
					PanierDAO panierDAO = new PanierDAO();
					double total = panierDAO.calculerTotalPanier(panier);
					%>
					<h6 class="mt-3">
						Total: <span id="totalPanier"> <%=total%> &#8364
						</span>
					</h6>



					<h6 class="mt-3">
						Points de fidelite :
						<%=new ClientDAO().getPointsFideliteById(1)%></h6>
					<br>

					<h6 class="mt-3">
						Total après réduction : <span id="nouveauTotalPanier"></span>&#8364
					</h6>
					<br>

					<form id="checkoutForm" action="ChoixCreneauServlet" method="POST">

						<button type="submit" class="btn btn-lg btn-primary">Checkout</button>
					</form>

					<i class="fa fa-long-arrow-right"></i> </a>
				</div>
			</div>
	</div>
	</section>
	</div>


	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<h5>À propos</h5>
					<p></p>
				</div>
				<div class="col-md-3">
					<h5>Liens Utiles</h5>
					<ul>
						<li><a href="about.html">À propos</a></li>
						<li><a href="contact.html">Contactez-nous</a></li>
						<li><a href="faq.html">FAQ</a></li>
						<li><a href="javascript:void(0)">Comment ça fonctionne</a></li>
						<li><a href="terms.html">Termes et Conditions de Retrait</a>
						</li>
						<li><a href="privacy.html">Politique de confidentialité</a></li>
					</ul>
				</div>
				<div class="col-md-3">
					<h5>Contact</h5>
					<ul>
						<li><a href="tel:+620892738334"><i class="fa fa-phone"></i>
								00337236723</a></li>
						<li><a href="mailto:hello@domain.com"><i
								class="fa fa-envelope"></i> Drive@G1.com</a></li>
					</ul>

					<h5>Suivez-nous</h5>
					<ul class="social">
						<li><a href="javascript:void(0)" target="_blank"><i
								class="fab fa-facebook-f"></i></a></li>
						<li><a href="javascript:void(0)" target="_blank"><i
								class="fab fa-instagram"></i></a></li>
						<li><a href="javascript:void(0)" target="_blank"><i
								class="fab fa-youtube"></i></a></li>
					</ul>
				</div>
				<div class="col-md-3">
					<h5>Obtenez notre application dès maintenant</h5>
					<ul class="mb-0">
						<li class="download-app"><a href="#"><img
								src="assets/img/playstore.png"></a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>


	<script type="text/javascript" src="assets/js/jquery.js"></script>
	<script type="text/javascript" src="assets/js/totalPanier.js"></script>
	<script type="text/javascript" src="assets/js/jquery-migrate.js"></script>
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
	<script type="text/javascript">
    function calculerNouveauTotal(pointsFidelite, totalPanier) {
        var totalPanier = parseFloat(totalPanier);
        var reduction = pointsFidelite / 10; // Supposons que chaque point de fidélité équivaut à 0.10 euro de réduction
        var nouveauTotal = totalPanier - reduction;
        document.getElementById("nouveauTotalPanier").textContent = nouveauTotal.toFixed(2);
    }
</script>

	document.getElementById("voirPointsFidelitebtn").addEventListener("click",
	function() { var pointsFidelite =
	<%=new ClientDAO().getPointsFideliteById(1)%>; var totalPanier =
	<%=total%>; // Assurez-vous que la variable 'total' est correctement
	définie dans votre script JSP calculerNouveauTotal(pointsFidelite,
	totalPanier); // Passez le montant total du panier en paramètre });






</body>
</html>
