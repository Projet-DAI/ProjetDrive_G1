<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.metier.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.DAO.hibernateMethode" %>

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
            product = hibernateMethode.getProductById(idProduit);
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
                        <form action="servletCentral" method="get">
							 <input type="hidden" name="method" value="addToCart">
							 <input type="hidden" name="productId" value="<%= product.getIdProduit() %>"> 
							 <p class="mb-1"><strong>Quantité</strong></p>
                            <div class="row mb-3">
                                <div class="col-sm-5">
                                    <input type="number" class="form-control" value="1" name="quantity" min="1">
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