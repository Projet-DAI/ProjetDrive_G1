<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Panier" %>
<%@ page import="Model.metier.LignePanier" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.DAO.ClientDAO" %>
<%-- Récupération du panier depuis la session --%>

<% Panier panier = (Panier) session.getAttribute("Panier"); %>



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
    <jsp:include flush="true" page="head.jsp"></jsp:include>
    

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
                        Save time and leave the groceries to us.
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
									<% if (panier != null && !panier.getLignesPanier().isEmpty()) { %>
             	                        <tbody>
					                  	
	       									 <% for (LignePanier lignePanier : panier.getLignesPanier()) { %>
						                        <tr>
						                        	<td>    </td>
						                            <td><%= lignePanier.getProduit().getNomProduit() %></td>
						                            <td><%= lignePanier.getProduit().getPrixProduit() %></td>
						                            <td>
									                    <div class="input-group">
													        <span class="input-group-btn">
													            
													        </span>
													        <input class="vertical-spin form-control input-number" type="text" id="quantite_<%= lignePanier.getProduit().getIdProduit() %>" name="quantite_<%= lignePanier.getProduit().getIdProduit() %>" value="<%= lignePanier.getQuantite() %>" min="1" data-bts-button-down-class="btn btn-primary" data-bts-button-up-class="btn btn-primary">
													            </button>
													        </span>
													    </div>
									                </td>
						                            <td><%= lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite() %></td>
													<td><a href="#" class="text-danger" onclick="supprimerArticle('<%= lignePanier.getProduit().getIdProduit() %>')"><i class="fa fa-times"></i></a></td>
						                        	
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
                        <a href="shop.jsp" class="btn btn-default">Continuer mes achats</a>
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
            alert("Nouveau total calculé : " + nouveauTotalPanier.toFixed(2) + ' €' + '\nPoints de fidélité : ' + pointsFidelite);
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

                        <a href="checkout.html" class="btn btn-lg btn-primary">Checkout <i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <h5>About</h5>
                    <p>Nisi esse dolor irure dolor eiusmod ex deserunt proident cillum eu qui enim occaecat sunt aliqua anim eiusmod qui ut voluptate.</p>
                </div>
                <div class="col-md-3">
                    <h5>Links</h5>
                    <ul>
                        <li>
                            <a href="about.html">About</a>
                        </li>
                        <li>
                            <a href="contact.html">Contact Us</a>
                        </li>
                        <li>
                            <a href="faq.html">FAQ</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">How it Works</a>
                        </li>
                        <li>
                            <a href="terms.html">Terms</a>
                        </li>
                        <li>
                            <a href="privacy.html">Privacy Policy</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3">
                     <h5>Contact</h5>
                     <ul>
                         <li>
                            <a href="tel:+620892738334"><i class="fa fa-phone"></i> 08272367238</a>
                        </li>
                        <li>
                            <a href="mailto:hello@domain.com"><i class="fa fa-envelope"></i> hello@domain.com</a>
                         </li>
                     </ul>

                     <h5>Follow Us</h5>
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
                     <h5>Get Our App</h5>
                     <ul class="mb-0">
                         <li class="download-app">
                             <a href="#"><img src="assets/img/playstore.png"></a>
                         </li>
                         <li style="height: 200px">
                             <div class="mockup">
                                 <img src="assets/img/mockup.png">
                             </div>
                         </li>
                     </ul>
                </div>
            </div>
        </div>
        <p class="copyright">&copy; 2018 Freshcery | Groceries Organic Store. All rights reserved.</p>
    </footer>
	
    <script type="text/javascript" src="assets/js/jquery.js"></script>
    <script type="text/javascript" src="assets/js/totalPanier.js"></script>
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