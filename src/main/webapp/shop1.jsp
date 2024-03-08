<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List"%>
<%@page import="Model.metier.Produit"%>
<%@page import="Model.metier.Panier"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Comparator" %>
<%@ page import="Model.metier.LignePanier"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@page import="Model.DAO.PanierDAO"%>

<%-- Récupération du total du panier depuis la requête --%>
<% Double totalPanier = (Double) request.getAttribute("totalPanier");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href="assets/fonts/sb-bistro/sb-bistro.css" rel="stylesheet" type="text/css">
    <link href="assets/fonts/font-awesome/font-awesome.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="assets/css/main1.css" />
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/o2system-ui/o2system-ui.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/owl-carousel/owl-carousel.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/cloudzoom/cloudzoom.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/thumbelina/thumbelina.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/css/theme.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
                        <%     Panier panier = (Panier)session1.getAttribute("panier");%>

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
                                <label for="sorting">TRIER</label>
                                <select class="form-control" id="sorting" style="font-size:11px" onchange="sortProducts()">
                                    <option>Pertinence</option>
                                    <option>Prix (croissant)</option>
                                    <option>Prix (décroissant)</option>
                                    <option>Prix au Kg/l (croissant)</option>
                                    <option>Prix au Kg/l (décroissant)</option>
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
                                       <div style="height: 40px; display: flex; flex-direction: column; justify-content: center;">
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


	<script type="text/javascript" src="assets/js/drive.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap/bootstrap.js"></script>
    <script type="text/javascript" src="assets/packages/owl-carousel/owl-carousel.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap-touchspin/bootstrap-touchspin.js"></script>
    <script type="text/javascript" src="assets/js/theme.js"></script>
    <script type="text/javascript" src="assets/js/headJSP.js"></script>
	
	
	
<script>
document.getElementById('sorting').addEventListener('change', function() {
    var selectedValue = this.value;

    var originalProductsContainer = document.getElementById('nav-grid');

    var newProductsContainer = document.createElement('div');
    newProductsContainer.classList.add('row', 'new-products-container');

    var products = originalProductsContainer.getElementsByClassName('single-product');
    var productsArray = Array.prototype.slice.call(products);

    if (selectedValue === "Prix (croissant)") {
        productsArray.sort(function(a, b) {
            var priceA = parseFloat(a.querySelector('.discount del').textContent.replace(/[^0-9.-]+/g,""));
            var priceB = parseFloat(b.querySelector('.discount del').textContent.replace(/[^0-9.-]+/g,""));
            return priceA - priceB;
        });
    } else if (selectedValue === "Prix (décroissant)") {
        productsArray.sort(function(a, b) {
            var priceA = parseFloat(a.querySelector('.discount del').textContent.replace(/[^0-9.-]+/g,""));
            var priceB = parseFloat(b.querySelector('.discount del').textContent.replace(/[^0-9.-]+/g,""));
            return priceB - priceA;
        });
    } else if (selectedValue === "Prix au Kg/l (croissant)") {
        productsArray.sort(function(a, b) {
            var pricePerKgA = parseFloat(a.querySelector('.product-info div:nth-child(2)').textContent.replace(/[^0-9.-]+/g,""));
            var pricePerKgB = parseFloat(b.querySelector('.product-info div:nth-child(2)').textContent.replace(/[^0-9.-]+/g,""));
            return pricePerKgA - pricePerKgB;
        });
    } else if (selectedValue === "Prix au Kg/l (décroissant)") {
        productsArray.sort(function(a, b) {
            var pricePerKgA = parseFloat(a.querySelector('.product-info div:nth-child(2)').textContent.replace(/[^0-9.-]+/g,""));
            var pricePerKgB = parseFloat(b.querySelector('.product-info div:nth-child(2)').textContent.replace(/[^0-9.-]+/g,""));
            return pricePerKgB - pricePerKgA;
        });
    }

    var productWidth = 'calc(100% / 3 - 10px)';

    var colClass = 'new-col';

    productsArray.forEach(function(product, index) {
        var newCol = document.createElement('div');
        newCol.classList.add(colClass);
        newCol.style.width = '200px'; // 设置 .single-product 元素的宽度
        newCol.innerHTML = product.outerHTML;
        newProductsContainer.appendChild(newCol);
        colClass = 'new-col-' + (index + 1);
    });


    originalProductsContainer.innerHTML = '';
    originalProductsContainer.appendChild(newProductsContainer);

    var tabContent = document.getElementById('nav-tabContent');
    tabContent.style.width = '250px';
    tabContent.style.height = '150px';
});
</script>

<style>
.new-product {
    width: calc(100% / 3 - 20px);
    padding: 5px;
    box-sizing: border-box;
}

.new-products-container .new-product {
    width: 750px;
    height: 392px;
    display: inline-block;
    margin-bottom: 10px;
    vertical-align: top;
}

.new-product .single-product {
    width: 250px;
    height: 392px;
    float: left;
    margin: 5px;
}

.new-product .product-image {
    width: 70%;
    text-align: center;
}

.new-product .product-image img {
    width: 100%;
    max-width: 100%;
    height: auto;
    display: block;
    margin: 0 auto;
}
</style>
</body>
</html>