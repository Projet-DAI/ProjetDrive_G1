<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.DAO.ProduitDAO" %>

<%
    // Récupérer l'ID du produit à partir de l'URL
    String productId = request.getParameter("productId");

    // Vérifier si l'ID du produit est présent dans l'URL
    if(productId != null && !productId.isEmpty()) {
        // Convertir l'ID du produit en entier
        int idProduit = Integer.parseInt(productId);

        // Récupérer les détails du produit en fonction de son ID
        Produit product = null;
        try {
            product = ProduitDAO.getProductById(idProduit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vérifier si le produit existe
        if(product != null) {
%>
<!DOCTYPE html>
<html>
<head>
      <meta charset="UTF-8">
    <title>Détails du produit</title>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
        <script>
        function verifierQuantite() {
            // Récupérer la quantité demandée
            var quantiteDemandee = parseInt(document.getElementById("quantiteInput").value);

            // Faire une requête Ajax pour récupérer la quantité en stock
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "GetQuantiteEnStock?idProduit=<%= product.getIdProduit() %>", false);
            xhr.send();

            // Vérifier si la quantité demandée est inférieure à la quantité en stock
            if (quantiteDemandee > parseInt(xhr.responseText)) {
                alert("La quantité demandée est supérieure à la quantité en stock. Veuillez ajuster la quantité.");
                return false; // Annuler l'envoi du formulaire
            }

            return true; // Envoyer le formulaire
        }
         </script>

</head>
<body>
	
    <div id="page-content" class="page-content">
        <div class="banner">
            <div class="jumbotron jumbotron-bg text-center rounded-0" style="background-image: url('assets/img/bg-header.jpg');">
                <div class="container">
                    <h1 class="pt-5">Détails du produit</h1>
                </div>
            </div>
        </div>

        <div class="product-detail">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <img src="<%= product.getAdresseImageProduit() %>" alt="Image du produit" style="width: 100%;">
                    </div>
                    <div class="col-sm-6">
                        <h3><%= product.getNomProduit() %> (<%= product.getMarqueProduit() %>)</h3>
                        <p><strong>Prix:</strong> <%= product.getPrixProduit() %></p>
                        <!-- Autres détails du produit -->
                       <%--  <% if(product.promotion) { %>
                            <p><strong>Promotion:</strong> Yes (${product.pourcentagePromotion * 100}% off)</p>
                        <% } else { %>
                            <p><strong>Promotion:</strong> No</p>
                        <% } %> --%>
                        <p><strong>Nutriscore:</strong> <%= product.getNutriscore() %></p>
                        <p><strong>Category ID:</strong> <%= product.getCategorie().getIdCategorie() %></p>
                          <!-- Formulaire pour ajouter au panier -->
                        <form action="AjouterPanierServlet" method="post" onsubmit="return verifierQuantite()">
						    <input type="hidden" name="productId" value="<%= product.getIdProduit() %>"> 
						    <p class="mb-1"><strong>Quantité</strong></p>
						    <div class="row mb-3">
						        <div class="col-sm-5">
						            <input type="number" class="form-control" value="1" name="quantite" min="1">
						        </div>
						    </div>
						    <button type="submit" class="btn btn-primary btn-lg">
						        <i class="fa fa-shopping-basket"></i> Ajouter au panier
						    </button>
						</form>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>

<%
        } else {
            // Le produit n'existe pas ou une erreur s'est produite lors de la récupération des détails du produit
            out.println("Le produit demandé n'existe pas.");
        }
    } else {
        // L'ID du produit n'est pas présent dans l'URL
        out.println("L'ID du produit n'a pas été spécifié dans l'URL.");
    }
%>
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
