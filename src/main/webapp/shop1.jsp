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
    <%
Panier panier = (Panier) session.getAttribute("Panier");
%>

<%-- Récupération du total du panier depuis la requête --%>
<%
Double totalPanier = (Double) request.getAttribute("totalPanier");
%>
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
							<a href="#" class="nav-link drive-link" data-toggle="modal"
								data-target="#locationModal">Drive</a></li>



						<li class="nav-item"><a href="#" id="faireCoursesBtn"
							class="nav-link">Faire ses courses</a></li>
						<%
						String nomU = (String) session.getAttribute("username");
						if (nomU != null) {
						%>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="javascript:void(0)"
							id="navbarDropdown" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
								<div class="avatar-header">
									<img src="assets/img/logo/avatar.jpg">
								</div> <%=nomU%>
						</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="TransactionPreloadServlet">Mon
									historique de commandes</a> <a class="dropdown-item"
									href="ListCoursePreloadServlet">Liste de courses</a> <a
									class="dropdown-item" href="TableauDeBordPreloadServlet">Tableau
									de bord</a> <a class="dropdown-item" href="setting.html">Paramètres</a>
								<a class="dropdown-item" href="DeconnexionServlet">Déconnexion</a>

							</div></li>

						<%
						} else {
						%>
						<li class="nav-item"><a href="login.jsp" class="nav-link">Se
								connecter</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="javascript:void(0)"
							id="navbarDropdown" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
								<div class="avatar-header">
									<img src="assets/img/logo/avatar.jpg">
								</div> Mon Profil
						</a>

							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="login.jsp">Mon historique de
									commandes</a> <a class="dropdown-item" href="login.jsp">Liste
									de courses</a> <a class="dropdown-item" href="login.jsp">Tableau
									de bord</a> <a class="dropdown-item" href="login.jsp">Paramètres</a>
								<a class="dropdown-item" href="DeconnexionServlet">Déconnexion</a>

							</div></li>
						<%
						}
						%>

						<li class="nav-item dropdown">
							<%
							if (panier != null && !panier.getLignesPanier().isEmpty()) {
							%> <a href="javascript:void(0)" class="nav-link dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-shopping-basket"></i> <span
								class="badge badge-primary"><%=panier.getLignesPanier().size()%></span>
						</a>
							<div class="dropdown-menu shopping-cart">
								<ul>
									<li>
										<div class="drop-title">
											<a href="Panier" class="nav-link">Mon Panier</a>
										</div>
									</li>

									<%
									for (LignePanier lignePanier : panier.getLignesPanier()) {
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
														<span class="discount text-muted"><%=lignePanier.getProduit().getPrixProduit()%></span>
														<%-- <span><%= lignePanier.getProduit().getPrixProduit() %></span>--%>
													</p>
													<p class="text-muted">
														Quantité:
														<%=lignePanier.getQuantite()%></p>
												</div>
											</div>
										</div>
									</li>
									<%
									}
									%>

									<li>

										<div class="drop-title d-flex justify-content-between">



											<h6 class="mt-3">
												Total: <span id="nouveauTotalPanier"><%=String.format    ("%.2f",totalPanier)%>&#8364</span>
											</h6>

											<%-- <span>Total:</span>
									            <span class="text-primary"><strong><%=total %> €</strong></span>
									           --%>

										</div>
									</li>


									<li class="d-flex justify-content-between pl-3 pr-3 pt-3">
										<a href="Panier.jsp" class="btn btn-default">Voir mon
											panier</a>
									</li>

									<%-- Fin contenu du panier --%>
								</ul>
							</div> <%
 }
 %>
						</li>
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
					<h5 class="modal-title" id="locationModalLabel">Choisissez
						votre magasin</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
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
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fermer</button>
					<button type="button" class="btn btn-primary"
						onclick="submitLocation()">Valider</button>
				</div>
			</div>
		</div>
	</div>
  
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