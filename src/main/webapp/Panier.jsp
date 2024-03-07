<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Panier" %>
<%@ page import="Model.metier.LignePanier" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.DAO.ClientDAO" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="assets/fonts/sb-bistro/sb-bistro.css" rel="stylesheet" type="text/css">
    <link href="assets/fonts/font-awesome/font-awesome.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/o2system-ui/o2system-ui.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/owl-carousel/owl-carousel.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/cloudzoom/cloudzoom.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/thumbelina/thumbelina.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/css/theme.css">

<title>Mon Panier</title>
<head>
    <title>Freshcery | Groceries Organic Store</title>

</head>
<body>
    <div class="page-header">
        <!--=============== Navbar ===============-->
        <nav class="navbar fixed-top navbar-expand-md navbar-dark bg-transparent" id="page-navigation">
            <div class="container">
                <!-- Navbar Brand -->
                <a href="index.html" class="navbar-brand">
                    <img src="" alt="">
                </a>

                <!-- Toggle Button -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarcollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarcollapse">
                    <!-- Navbar Menu -->
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a href="shop.html" class="nav-link">Faire ses courses</a>
                        </li>
                        <li class="nav-item">
                            <a href="register.html" class="nav-link">S'inscrire</a>
                        </li>
                        <li class="nav-item">
                            <a href="login.html" class="nav-link">Se connecter</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <div class="avatar-header"><img src="assets/img/logo/avatar.jpg"></div> Mon profil
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="transaction.html">Mon historique de commandes</a>
                                <a class="dropdown-item" href="setting.html">Paramètres</a>
                            </div>
                          </li>
                        <li class="nav-item dropdown">
                            <a href="javascript:void(0)" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-shopping-basket"></i> <span class="badge badge-primary">5</span>
                            </a>
                            <div class="dropdown-menu shopping-cart">
                                <ul>
                                    <li>
                                        <div class="drop-title">Mon panier</div>
                                    </li>
                                    <li>
                                        <div class="shopping-cart-list">
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                            <div class="media">
                                                <img class="d-flex mr-3" src="assets/img/logo/avatar.jpg" width="60">
                                                <div class="media-body">
                                                    <h5><a href="javascript:void(0)">Carrot</a></h5>
                                                    <p class="price">
                                                        <span class="discount text-muted">Rp. 700.000</span>
                                                        <span>Rp. 100.000</span>
                                                    </p>
                                                    <p class="text-muted">Qty: 1</p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="drop-title d-flex justify-content-between">
                                            <span>Total:</span>
                                            <span class="text-primary"><strong>Rp. 2000.000</strong></span>
                                        </div>
                                    </li>
                                    <li class="d-flex justify-content-between pl-3 pr-3 pt-3">
                                        <a href="cart.html" class="btn btn-default">View Cart</a>
                                        <a href="checkout.html" class="btn btn-primary">Checkout</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
    </div>
    <div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">
                        Mon panier
                    </h1>
                    <p class="lead">
                        Économisez du temps et confiez-nous vos courses
                    </p>
                </div>
            </div>
        </div>

        <section id="cart">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                      <button id="voirPointsFidelitebtn" class="btn btn-primary">Débloquer mes points de fidélité</button>
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
					                <% if (request.getAttribute("panier") != null ){ %>
					                  	<% Panier panier = (Panier) request.getAttribute("panier"); %>
             	                        <tbody>
					                  	
	       									 <% for (LignePanier lignePanier : panier.getLignesPanier()) { %>
						                        <tr>
						                        	<td>    </td>
						                            <td><%= lignePanier.getProduit().getNomProduit() %></td>
						                            <td><%= lignePanier.getProduit().getPrixProduit() %></td>
						                            <td><%= lignePanier.getQuantite() %></td>
						       	                    <td><%= lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite() %></td>
						                            
						                        </tr>
						                    <% } %>
					                    </tbody>
					                    
					                <% } else { %>
					                    <tr>
					                        <td colspan="4">Le panier est vide.</td>
					                    </tr>
					                <% } %>

                            </table>
                        </div>
                    </div>
                    <div class="col">
                        <a href="shop.html" class="btn btn-default">Continue Shopping</a>
                    </div>
                    <div class="col text-right">
                        <div class="input-group w-50 float-right">
                            <input class="form-control" placeholder="Coupon Code" type="text">
                            <div class="input-group-append">
                                <button class="btn btn-default" type="button">Apply</button>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <%
						    double total = 0.0;
						    if (request.getAttribute("panier") != null) {
						        Panier panier = (Panier) request.getAttribute("panier");
						        for (LignePanier lignePanier : panier.getLignesPanier()) {
						            total += lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite();
						        }
						    }
						%>
						
						<h6 class="mt-3">Total: <span id="totalPanier"> <%= total %> € </span> </h6>
						    
<script type="text/javascript">
    // Initialiser le total à partir de la valeur côté serveur (en tant que chaîne de caractères)
    var totalPanierString = '<%= String.valueOf(total) %>';
    // Initialiser le total mis à jour à zéro
    var nouveauTotalPanier = 0;

    // Fonction pour mettre à jour le nouveau total dans l'interface utilisateur
    function updateNouveauTotalPanier() {
        // Mettre à jour l'élément HTML avec le nouveau total
        document.getElementById('nouveauTotalPanier').innerText = nouveauTotalPanier.toFixed(2) + ' €';
    }

    // Fonction pour effectuer le calcul du nouveau total
    function calculerNouveauTotal(pointsFidelite) {
        // Convertir la valeur du total en nombre
        var totalPanier = parseFloat(totalPanierString.replace(",", "."));

        // Vérifier si la conversion est un nombre valide
        if (!isNaN(totalPanier)) {
            var reductionEnEuros = pointsFidelite / 10.0;
            nouveauTotalPanier = totalPanier - reductionEnEuros;

            // Mettre à jour le nouveau total dans l'interface utilisateur
            updateNouveauTotalPanier();

            // Afficher une alerte pour déboguer
            //alert("Nouveau total calculé : " + nouveauTotalPanier.toFixed(2) + ' €' + '\nPoints de fidélité : ' + pointsFidelite);
        } else {
            // Gérer l'erreur si la conversion n'est pas un nombre valide
            console.error("Erreur de conversion du total en nombre.");
        }
    }

    // Code existant pour l'événement 'voirPointsFidelitebtn'
    document.getElementById('voirPointsFidelitebtn').addEventListener('click', function() {
        var pointsFidelite = <%= new ClientDAO().getPointsFideliteById(1) %>;

        // Appeler la fonction pour calculer et mettre à jour le nouveau total
        calculerNouveauTotal(pointsFidelite);
    });
</script>                        <h6 class="mt-3">Points de fidelite : <%= new ClientDAO().getPointsFideliteById(1) %></h6>                    
                        <h6 class="mt-3">Total après réduction : <span id="nouveauTotalPanier"></span></h6>

                        <a href="checkout.html" class="btn btn-lg btn-primary">Valider Mom Panier <i class="fa fa-long-arrow-right"></i></a>
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
                        <li>
                            <a href="about.html">À propos</a>
                        </li>
                        <li>
                            <a href="contact.html">Contactez-nous</a>
                        </li>
                        <li>
                            <a href="faq.html">FAQ</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">Comment ça fonctionne</a>
                        </li>
                        <li>
                            <a href="terms.html">Termes et Conditions de Retrait</a>
                        </li>
                        <li>
                            <a href="privacy.html">Politique de confidentialité</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3">
                     <h5>Contact</h5>
                     <ul>
                         <li>
                            <a href="tel:+620892738334"><i class="fa fa-phone"></i> 00337236723</a>
                        </li>
                        <li>
                            <a href="mailto:hello@domain.com"><i class="fa fa-envelope"></i> Drive@G1.com</a>
                         </li>
                     </ul>

                     <h5>Suivez-nous</h5>
                     <ul class="social">
                         <li>
                            <a href="javascript:void(0)" target="_blank"><i class="fab fa-facebook-f"></i></a>
                         </li>
                         <li>
                            <a href="javascript:void(0)" target="_blank"><i class="fab fa-instagram"></i></a>
                         </li>
                         <li>
                            <a href="javascript:void(0)" target="_blank"><i class="fab fa-youtube"></i></a>
                         </li>
                     </ul>
                </div>
                <div class="col-md-3">
                     <h5>Obtenez notre application dès maintenant</h5>
                     <ul class="mb-0">
                         <li class="download-app">
                             <a href="#"><img src="assets/img/playstore.png"></a>
                         </li>
                     </ul>
                </div>
            </div>
        </div>
    </footer>
	
	<script type="text/javascript" src="assets/js/totalPanier.js"></script>
    <script type="text/javascript" src="assets/js/jquery.js"></script>
    <script type="text/javascript" src="assets/js/jquery-migrate.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap/libraries/popper.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap/bootstrap.js"></script>
    <script type="text/javascript" src="assets/packages/o2system-ui/o2system-ui.js"></script>
    <script type="text/javascript" src="assets/packages/owl-carousel/owl-carousel.js"></script>
    <script type="text/javascript" src="assets/packages/cloudzoom/cloudzoom.js"></script>
    <script type="text/javascript" src="assets/packages/thumbelina/thumbelina.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap-touchspin/bootstrap-touchspin.js"></script>
    <script type="text/javascript" src="assets/js/theme.js"></script>
    



</body>
</html>

<%-- <!-- Supprimez le script JavaScript existant pour l'événement 'voirPointsFidelitebtn' -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Panier" %>
<%@ page import="Model.metier.LignePanier" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.DAO.ClientDAO" %>
<%@ page import="Model.DAO.PanierDAO" %>

Récupération du panier depuis la session

<% Panier panier = (Panier) session.getAttribute("panier"); %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="assets/fonts/sb-bistro/sb-bistro.css" rel="stylesheet" type="text/css">
    <link href="assets/fonts/font-awesome/font-awesome.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/o2system-ui/o2system-ui.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/owl-carousel/owl-carousel.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/cloudzoom/cloudzoom.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/thumbelina/thumbelina.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/css/theme.css">
	
<title>Mon Panier</title>
<head>
    <title>Freshcery | Groceries Organic Store</title>
    

</head>
<body>

    <div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">
                        Mon panier
                    </h1>
                    <p class="lead">
                        Économisez du temps et confiez-nous vos courses
                    </p>
                </div>
            </div>
        </div>

	    <jsp:include flush="true" page="head.jsp"></jsp:include>
	
        <section id="cart">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                     
                      <button id="voirPointsFidelitebtn" class="btn btn-primary" >Débloquer mes points de fidélité</button>
                     
                      
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
								    Boucle pour afficher chaque produit dans le panier
									<% if (panier != null && !panier.getLignesPanier().isEmpty()) { %>
             	                        <tbody>
					                  	
	       									 <% for (LignePanier lignePanier : panier.getLignesPanier()) { %>
						                        <tr>
						                        	<td>    </td>
						                            <td><%= lignePanier.getProduit().getNomProduit() %></td>
						                            <td><%= lignePanier.getProduit().getPrixProduit() %></td>
						                            <td>
									                    <form action="ModifierQuantitePanierServlet" method="post">
									                        <input type="hidden" name="panierId" value="<%= panier.getIdPanier() %>">
									                        <input type="hidden" name="produitId" value="<%= lignePanier.getProduit().getIdProduit() %>">
									                        <input type="number" class="vertical-spin form-control input-number quantity-input"  value="<%= lignePanier.getQuantite() %>" min="1">
									                        <button type="button" class="btn btn-primary modify-quantity">Modifier</button>
									                    </form>
									                </td>
									                <script>
													    $(document).ready(function() {
													        $(".modify-quantity").on("click", function() {
													            var parentRow = $(this).closest("tr");
													            var panierId = <%= panier.getIdPanier() %>;
													            var produitId = parentRow.find("[name='produitId']").val();
													            var nouvelleQuantite = parentRow.find(".quantity-input").val();
													
													            $.ajax({
													                type: "POST",
													                url: "ModifierQuantitePanierServlet",
													                data: {
													                    panierId: panierId,
													                    produitId: produitId,
													                    nouvelleQuantite: nouvelleQuantite
													                },
													                success: function(response) {
													                    // Update the quantity and total price on the page based on the server's response
													                    // For example, you can update the quantity and total elements here
													                },
													                error: function(xhr, status, error) {
													                    console.error(error); // Log any errors to the console
													                }
													            });
													        });
													    });
													</script>
						                            <td><%= lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite() %></td>
													<td><a href="#" class="text-danger delete-item" data-id="<%= lignePanier.getProduit().getIdProduit() %>"><i class="fa fa-times"></i></a></td>
						                        	<script>
						                        	$(document).ready(function() {
						                        	    // Attachement de l'événement de suppression uniquement une fois
						                        	    $(".delete-item").off("click").on("click", function(e) {
						                        	        e.preventDefault();
						                        	        var deleteButton = $(this); // Garder une référence au bouton supprimer
						                        	        var productId = deleteButton.data("id");
						                        	        var panierId = <%= panier.getIdPanier() %>;

						                        	        // Afficher directement la fenêtre contextuelle de confirmation sans le if(confirm(...))
						                        	        var confirmation = confirm("Voulez-vous vraiment supprimer cet article du panier?");

						                        	        // Si l'utilisateur confirme la suppression
						                        	        if (confirmation) {
						                        	            $.ajax({
						                        	                type: "GET",
						                        	                url: "SupprimerLignePanierServlet",
						                        	                data: {
						                        	                    idPanier: panierId,
						                        	                    idProduit: productId
						                        	                },
						                        	                success: function(response) {
						                        	                    // Mettre à jour le contenu du panier ou tout autre élément d'interface utilisateur pertinent
						                        	                    deleteButton.closest('tr').remove(); // Supprimer la ligne de tableau correspondante
						                        	                    alert("Article supprimé avec succès"); // Afficher un message de confirmation
						                        	                },
						                        	                error: function(xhr, status, error) {
						                        	                    console.error(error); // Journaliser les erreurs éventuelles dans la console
						                        	                }
						                        	            });
						                        	        }
						                        	    });
						                        	});

													</script>
						                        </tr>
						                    <% } %>
					                    </tbody>
					                    
					                <% } else { %>
					                    <tr>
					                        <td colspan="4">Le panier est vide.</td>
					                    </tr>
					                <% } %>

                            </table>
                        </div>
                    </div>
                    <div class="col">
                        <a href="index.jsp" class="btn btn-default">Continuer mes achats</a>
                    </div>
                    <div class="clearfix"></div>
                        <%
						    double total = 0.0;
						    if (request.getAttribute("panier") != null) {
						        for (LignePanier lignePanier : panier.getLignesPanier()) {
						            total += lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite();
						        }
						    }
						%>
						
                    
						                  
								<h6 class="mt-3">Total: <span id="totalPanier"> <%= total %>  &#8364</span></h6>
                    
                    <script type="text/javascript">
    // Initialiser le total à partir de la valeur côté serveur (en tant que chaîne de caractères)
    var totalPanierString = '<%= String.valueOf(total) %>';
    // Initialiser le total mis à jour à zéro
    var nouveauTotalPanier = 0;

    // Fonction pour mettre à jour le nouveau total dans l'interface utilisateur
    function updateNouveauTotalPanier() {
        // Mettre à jour l'élément HTML avec le nouveau total
        document.getElementById('nouveauTotalPanier').innerText = nouveauTotalPanier.toFixed(2) + ' €';
    }

    // Fonction pour effectuer le calcul du nouveau total
    function calculerNouveauTotal(pointsFidelite) {
        // Convertir la valeur du total en nombre
        var totalPanier = parseFloat(totalPanierString.replace(",", "."));

        // Vérifier si la conversion est un nombre valide
        if (!isNaN(totalPanier)) {
            var reductionEnEuros = pointsFidelite / 10.0;
            nouveauTotalPanier = totalPanier - reductionEnEuros;

            // Mettre à jour le nouveau total dans l'interface utilisateur
            updateNouveauTotalPanier();

            // Afficher une alerte pour déboguer
            //alert("Nouveau total calculé : " + nouveauTotalPanier.toFixed(2) + ' €' + '\nPoints de fidélité : ' + pointsFidelite);
        } else {
            // Gérer l'erreur si la conversion n'est pas un nombre valide
            console.error("Erreur de conversion du total en nombre.");
        }
    }

    // Code existant pour l'événement 'voirPointsFidelitebtn'
    document.getElementById('voirPointsFidelitebtn').addEventListener('click', function() {
        var pointsFidelite = <%= new ClientDAO().getPointsFideliteById(1) %>;

        // Appeler la fonction pour calculer et mettre à jour le nouveau total
        calculerNouveauTotal(pointsFidelite);
    });
</script>
                
		<h6 class="mt-3">Points de fidelite : <%= new ClientDAO().getPointsFideliteById(1)%></h6>               
		<br>
		
		<h6 class="mt-3">Total après réduction : <span id="nouveauTotalPanier"></span>&#8364</h6>
		<br>
                        
				<form id="checkoutForm" action="ChoixCreneauServlet" method="POST">
				
    						<button type="submit" class="btn btn-lg btn-primary">Checkout</button>
                        </form>
                         
                        <i class="fa fa-long-arrow-right"></i>
                       </a>
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
                        <li>
                            <a href="about.html">À propos</a>
                        </li>
                        <li>
                            <a href="contact.html">Contactez-nous</a>
                        </li>
                        <li>
                            <a href="faq.html">FAQ</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">Comment ça fonctionne</a>
                        </li>
                        <li>
                            <a href="terms.html">Termes et Conditions de Retrait</a>
                        </li>
                        <li>
                            <a href="privacy.html">Politique de confidentialité</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3">
                     <h5>Contact</h5>
                     <ul>
                         <li>
                            <a href="tel:+620892738334"><i class="fa fa-phone"></i> 00337236723</a>
                        </li>
                        <li>
                            <a href="mailto:hello@domain.com"><i class="fa fa-envelope"></i> Drive@G1.com</a>
                         </li>
                     </ul>

                     <h5>Suivez-nous</h5>
                     <ul class="social">
                         <li>
                            <a href="javascript:void(0)" target="_blank"><i class="fab fa-facebook-f"></i></a>
                         </li>
                         <li>
                            <a href="javascript:void(0)" target="_blank"><i class="fab fa-instagram"></i></a>
                         </li>
                         <li>
                            <a href="javascript:void(0)" target="_blank"><i class="fab fa-youtube"></i></a>
                         </li>
                     </ul>
                </div>
                <div class="col-md-3">
                     <h5>Obtenez notre application dès maintenant</h5>
                     <ul class="mb-0">
                         <li class="download-app">
                             <a href="#"><img src="assets/img/playstore.png"></a>
                         </li>
                     </ul>
                </div>
            </div>
        </div>
    </footer>
    
	
    <script type="text/javascript" src="assets/js/jquery.js"></script>
    <script type="text/javascript" src="assets/js/jquery-migrate.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap/libraries/popper.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap/bootstrap.js"></script>
    <script type="text/javascript" src="assets/packages/o2system-ui/o2system-ui.js"></script>
    <script type="text/javascript" src="assets/packages/owl-carousel/owl-carousel.js"></script>
    <script type="text/javascript" src="assets/packages/cloudzoom/cloudzoom.js"></script>
    <script type="text/javascript" src="assets/packages/thumbelina/thumbelina.js"></script>
    <script type="text/javascript" src="assets/packages/bootstrap-touchspin/bootstrap-touchspin.js"></script>
    <script type="text/javascript" src="assets/js/theme.js"></script>
  

							
	


</body>
</html>
 --%>