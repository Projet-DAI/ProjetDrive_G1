<%@page import="java.io.Console"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Model.metier.Panier"%>
<%@page import="Model.metier.Produit"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Locale"%>

<%@ page import="Model.metier.LignePanier"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@page import="Model.DAO.PanierDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> Freshcery | Groceries Organic Store</title>
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body>
	<div class="page-header">
		<!--=============== Navbar ===============-->
		<nav
			class="navbar fixed-top navbar-expand-md navbar-dark bg-transparent"
			id="page-navigation">

			<div class="container">
				<!-- Navbar Brand -->
				<a href="index.jsp" class="navbar-brand"> <img
					src="assets/img/logo/fresh_4x-removebg-preview.png" alt="">
				</a>

				<!-- Toggle Button -->
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarcollapse" aria-controls="navbarCollapse"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarcollapse">
					<!-- Navbar Menu -->
					<ul class="navbar-nav ml-auto">


						<form action="RechercheParMotCle" method="get" style="margin-top: 4px;">

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<button id="btnMode" name="mode" class="btn btn-outline-secondary dropdown-toggle"
										type="button" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Produit</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" value="Rayon" onclick="selectOption('Rayon')">Rayon</a> 
										<a class="dropdown-item" value="Catégorie" onclick="selectOption('Catégorie')">Catégorie</a> 
										<a class="dropdown-item" value="Produit" onclick="selectOption('Produit')">Produit</a>							
									</div>
								</div>
								<input type="text" id="motcle" name="motcle" class="form-control" aria-label="Text input with dropdown button">
							</div>
						</form>

					

						<!-- ajouter Drive pour choisir magasin-->
						<li class="nav-item" id="drive">
							<a href="#" class="nav-link drive-link" data-toggle="modal" data-target="#locationModal">Drive</a>
						</li>



						<li class="nav-item">
							<a href="#" id="faireCoursesBtn" class="nav-link">Faire ses courses</a>
						</li>
						
						<%
							HttpSession session1 = request.getSession();
							String nomU = (String)session1.getAttribute("username");
							if (nomU != null) {
						%>

						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="javascript:void(0)" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<div class="avatar-header">
								<img src="assets/img/logo/avatar.jpg">
							</div> <%=nomU%>
						</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="TransactionPreloadServlet">Mon historique de commandes</a> 
								<a class="dropdown-item" href="ListCoursePreloadServlet">Liste de courses</a> 
								<a class="dropdown-item" href="TableauDeBordPreloadServlet">Tableaude bord</a> 
								<a class="dropdown-item" href="setting.html">Paramètres</a>
								<a class="dropdown-item" href="DeconnexionServlet">Déconnexion</a>
							</div>
						</li>
						
						<li class="nav-item dropdown">
						
							<% 	Panier panier = (Panier)session1.getAttribute("panier");%>
							
							<% if (panier != null && !panier.getLignesPanier().isEmpty()) { %> 
							<a href="javascript:void(0)" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-shopping-basket"></i> <span class="badge badge-primary"><%=panier.getLignesPanier().size()%></span>
							</a>
							
							<div class="dropdown-menu shopping-cart">
								<ul>
									<li>
										<div class="drop-title" style="color:pink;">Mon Panier</div>
									</li>

									<%
										Double prixTotal = 0.0;
									for (LignePanier lignePanier : panier.getLignesPanier()) {
										if (lignePanier.getProduit().isPromotion()){
											prixTotal += lignePanier.getProduit().getPrixProduit() * lignePanier.getProduit().getPourcentagePromotion() * lignePanier.getQuantite();							
										} else {
											prixTotal += lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite();
										}
										
										
							
									%>

									<%-- Contenu du panier --%>
									<li>
										<div class="shopping-cart-list">

											<div class="media">
												<img class="d-flex mr-3"
													src="<%=lignePanier.getProduit().getAdresseImageProduit()%>"
													width="60">
												<div class="media-body">
													<h5>
														<a href="javascript:void(0)"><%=lignePanier.getProduit().getNomProduit()%></a>
													</h5>
													<p class="price">
														<span class="discount text-muted"><%=lignePanier.getProduit().getPrixProduit()%>&#8364</span>
														<%-- <span><%= lignePanier.getProduit().getPrixProduit() %></span>--%>
													</p>
													<p class="price">
														<span><%=Math.round((lignePanier.getProduit().getPrixProduit() * lignePanier.getProduit().getPourcentagePromotion()) * 100.0) / 100.0%>&#8364</span>
													</p>
													<p class="text-muted">
														Quantité:
														<%=lignePanier.getQuantite()%></p>
												</div>
											</div>
										</div>
									</li>
									<% }%>

									<li>
										<div class="drop-title d-flex justify-content-between">
											
											<% prixTotal = Math.round(prixTotal * 100.0) / 100.0; %>

											<h6 class="mt-3">
												Total: <span id="nouveauTotalPanier"><%=prixTotal %>&#8364</span>
											</h6>

											<%-- <span>Total:</span>
									            <span class="text-primary"><strong><%=total %> €</strong></span>
									           --%>
										</div>
									</li>

									<li class="d-flex justify-content-between pl-3 pr-3 pt-3">
										<a href="Panier" class="btn btn-default">Voir mon panier</a>
									</li>

									<%-- Fin contenu du panier --%>
								</ul>
							</div> 
							<% } else { %>
								<a href="javascript:void(0)" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-shopping-basket"></i> <span class="badge badge-primary"></span>
							</a>
							
							<div class="dropdown-menu shopping-cart">
								<ul>
									<li>
										<div class="drop-title">
											<a href="Panier" class="nav-link">Mon Panier</a>
										</div>
									</li>

									<%-- Contenu du panier --%>
									<li>
										<div class="shopping-cart-list">

											<div class="media">
												<div class="media-body">
													<h5>
														<a href="javascript:void(0)">Vous n'avez aucun article dans votre panier</a>
													</h5>
													<p class="price">
														<span class="discount text-muted"></span>
														<%-- <span><%= lignePanier.getProduit().getPrixProduit() %></span>--%>
													</p>
													<p class="text-muted">
														</p>
												</div>
											</div>
										</div>
									</li>
									

									<li>
										<div class="drop-title d-flex justify-content-between">
											<h6 class="mt-3">
												Total: <span id="nouveauTotalPanier">0&#8364</span>
											</h6>
										</div>
									</li>

									<li class="d-flex justify-content-between pl-3 pr-3 pt-3">
										<a href="Panier" class="btn btn-default">Voir mon panier</a>
									</li>

									<%-- Fin contenu du panier --%>
								</ul>
							</div> 
						</li>
						<% }%>
				
						<% } else {%>
							
						<li class="nav-item"><a href="login.jsp" class="nav-link">
							Se connecter</a>
						</li>
						
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="javascript:void(0)" id="navbarDropdown" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<div class="avatar-header">
								<img src="assets/img/logo/avatar.jpg">
							</div> Mon Profil
						</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="login.jsp">Mon historique de commandes</a> 
								<a class="dropdown-item" href="login.jsp">Liste de courses</a> 
								<a class="dropdown-item" href="login.jsp">Tableau de bord</a> 
								<a class="dropdown-item" href="login.jsp">Paramètres</a>
								<a class="dropdown-item" href="DeconnexionServlet">Déconnexion</a>
							</div>
						</li>
						<% } %>		
					</ul>
				</div>
			</div>
		</nav>
	</div>
	
	<!-- Drive Page -->
	<div class="modal fade" id="locationModal" tabindex="-1" role="dialog"
		aria-labelledby="locationModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="locationModalLabel">Choisissez votre magasin</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="locationForm">
						<div class="form-group">
							<label for="userLocation" class="col-form-label">Location:</label>
							<input type="text" class="form-control" id="userLocation">
						</div>
						<div id="magasinsList"></div>
						<!-- Conteneur de la liste d'achats -->
						  <!-- Ajout du champ d'entrée caché pour l'ID du magasin choisi -->
                    	<input type="hidden" id="magasinId" name="magasinId" value="">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
					<button type="button" class="btn btn-primary" onclick="submitLocation()">Valider</button>
					
				</div>
			</div>
		</div>
	</div>


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
