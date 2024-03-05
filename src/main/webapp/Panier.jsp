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
	 <script type="text/javascript">
        function modifierQuantiteProduit(idProduit) {
            var nouvelleQuantite = document.getElementById('quantite_' + idProduit).value;
            // Appeler la servlet pour modifier la quantité du produit dans le panier
            window.location.href = 'ModifierQuantitePanierServlet?idProduit=' + idProduit + '&nouvelleQuantite=' + nouvelleQuantite;
        }
    </script>
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
									                    <form action="ModifierQuantitePanierServlet" method="post">
									                        <input type="hidden" name="panierId" value="<%= panier.getIdPanier() %>">
									                        <input type="hidden" name="produitId" value="<%= lignePanier.getProduit().getIdProduit() %>">
									                        <input class="vertical-spin form-control input-number" type="text" name="nouvelleQuantite" value="<%= lignePanier.getQuantite() %>" min="1">
									                        <button type="submit" class="btn btn-primary">Modifier</button>
									                    </form>
									                </td>
						                            <td><%= lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite() %></td>
													<td><a href="#" class="text-danger" onclick="supprimerLignePanier('<%= lignePanier.getProduit().getIdProduit() %>')"><i class="fa fa-times"></i></a></td>
						                        	<script>
														function supprimerLignePanier(idProduit) {
														    var idPanier = <%= panier.getIdPanier() %>;
														    if (confirm("Voulez-vous vraiment supprimer cet article du panier?")) {
														        window.location.href = 'SupprimerLignePanierServlet?idPanier=' + idPanier + '&idProduit=' + idProduit;
														    }
														}
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
						
<script type="text/javascript">
    // Récupérer le total du panier du côté serveur
    var total = <%= total %>;
    
    // Fonction pour mettre à jour le nouveau total dans l'interface utilisateur
    function updateNouveauTotalPanier(nouveauTotal) {
        // Mettre à jour l'élément HTML avec le nouveau total
        document.getElementById('nouveauTotalPanier').innerText = nouveauTotal.toFixed(2) + ' €';
    }

    // Fonction pour effectuer le calcul du nouveau total
    function calculerNouveauTotal(pointsFidelite) {
        // Vérifier si le total est un nombre valide
        if (!isNaN(total)) {
            var reductionEnEuros = pointsFidelite / 10.0;
            var nouveauTotal = total - reductionEnEuros;

            // Mettre à jour le nouveau total dans l'interface utilisateur
            updateNouveauTotalPanier(nouveauTotal);
        } else {
            console.error("Erreur: le total du panier n'est pas un nombre valide.");
        }
    }

    // Écouter l'événement 'voirPointsFidelitebtn'
    document.getElementById('voirPointsFidelitebtn').addEventListener('click', function() {
        var pointsFidelite = <%= new ClientDAO().getPointsFideliteById(1) %>;

        // Appeler la fonction pour calculer et mettre à jour le nouveau total
        calculerNouveauTotal(pointsFidelite);
    });
</script>

		<h6 class="mt-3">Total: <span id="nouveauTotalPanier">&#8364</span></h6>
		<br>
		                  
		<h6 class="mt-3">Points de fidelite : <%= new ClientDAO().getPointsFideliteById(1) %></h6>                    
		<br>
		
		<h6 class="mt-3">Total après réduction : <span id="nouveauTotalPanier"></span></h6>
		<br>
                        

                        <a href="Checkout.jsp" class="btn btn-lg btn-primary">Checkout 
                        <i class="fa fa-long-arrow-right"></i>
                       </a>
                    </div>
                </div>
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