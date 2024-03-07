<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Panier" %>
<%@ page import="Model.metier.LignePanier" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.DAO.ClientDAO" %>
<%@ page import="java.text.DecimalFormat" %>

<%
    // Récupération du panier depuis la session
    Panier panier = (Panier) session.getAttribute("Panier");

    // Récupération du total du panier depuis la requête
    Double totalPanier = (Double) request.getAttribute("totalPanier");

    // Formatage du total du panier avec deux chiffres après la virgule
    DecimalFormat df = new DecimalFormat("#.##");
    String totalPanierFormatted = df.format(totalPanier);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Panier</title>

    <link rel="stylesheet" type="text/css" media="all" href="https://fonts.googleapis.com/css?family=Montserrat:400,700">
    <link rel="stylesheet" type="text/css" media="all" href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic">
    <link rel="stylesheet" type="text/css" media="all" href="assets/fonts/sb-bistro/sb-bistro.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/fonts/font-awesome/font-awesome.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/o2system-ui/o2system-ui.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/owl-carousel/owl-carousel.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/cloudzoom/cloudzoom.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/thumbelina/thumbelina.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/packages/bootstrap-touchspin/bootstrap-touchspin.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/css/theme.css">

    <script type="text/javascript">
        // Exposer la variable totalPanierString au script JavaScript
        var totalPanierString = '<%= totalPanierFormatted %>';
    </script>
</head>
<body>

    <section id="cart">
        <button id="voirPointsFidelitebtn" class="btn btn-primary">Débloquer mes points de fidélité</button>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
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
                    // Fonction pour effectuer le calcul du nouveau total
                    function calculerNouveauTotal(pointsFidelite) {
                        // Convertir la valeur du total en nombre
                        var totalPanier = parseFloat(totalPanierString.replace(",", "."));

                        // Vérifier si la conversion est un nombre valide
                        if (!isNaN(totalPanier)) {
                            var reductionEnEuros = pointsFidelite / 10.0;
                            var nouveauTotalPanier = totalPanier - reductionEnEuros;

                            // Mettre à jour le nouveau total dans l'interface utilisateur
                            document.getElementById('nouveauTotalPanier').
                               innerText = nouveauTotalPanier.toFixed(2) + ' €';
                                                       // Afficher une alerte pour déboguer
                        alert("Nouveau total calculé : " + nouveauTotalPanier.toFixed(2) + ' €' + '\nPoints de fidélité : ' + pointsFidelite);
                    } else {
                        // Gérer l'erreur si la conversion n'est pas un nombre valide
                        console.error("Erreur de conversion du total en nombre.");
                    }
                }

                // Code existant pour l'événement 'voirPointsFidelitebtn'
                document.getElementById('voirPointsFidelitebtn').addEventListener('click', function () {
                    // Utiliser une requête Ajax pour récupérer les points de fidélité du serveur
                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var pointsFidelite = parseInt(xhr.responseText);
                            // Appeler la fonction pour calculer et mettre à jour le nouveau total
                            calculerNouveauTotal(pointsFidelite);
                        }
                    };
                    xhr.open("GET", "GetPointsFideliteServlet", true);
                    xhr.send();
                });
            </script>
            <h6 class="mt-3">Total: <span id="totalPanier"><%= String.format("%.2f", totalPanier) %>&#8364;</span></h6>
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
</section>

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