<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Model.metier.Client" %>
<%@ page import="Model.DAO.ClientDAO" %>
<%@ page import="Model.metier.Panier" %>
<%@ page import="Model.metier.LignePanier" %>
<!DOCTYPE html>
<html>
<head>
<title>Valider mon panier</title>
<meta charset="utf-8">
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

</head>
<body>
<jsp:include flush="true" page="head.jsp"></jsp:include>

	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Valider mon panier</h1>
					<p class="lead">Economisez du temps et confiez-nous vos courses</p>
				</div>
			</div>
		</div>

		<section id="checkout">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-7">
						<h5 class="mb-3">DETAILS DE FACTURATION</h5>
                        <%
                            int clientId = 1; 
                            Client clientConnecte = new ClientDAO().getClientById(clientId);
                        %>

            			<form action="#" class="bill-detail">
							<fieldset>
								<div class="form-group row">
									<div class="col">
										<input class="form-control" placeholder="Nom Prénom" type="text" value="<%= clientConnecte.getNomCompletClient() %>">
									</div>
									<div class="col">
										<input class="form-control" placeholder="Nom utilisateur" type="text" value="<%= clientConnecte.getNomUtilisateurClient() %>">
									</div>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Email"
										type="text" value="<%= clientConnecte.getEmailClient() %>">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Téléphone"
										type="text" value="<%= clientConnecte.getTelephoneClient() %>">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Pays"
										type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Code postal"
										type="text">
								</div>

							</fieldset>
						</form>
					</div>
                     <%
                        
                        Panier panierClient = clientConnecte.getPanier();
                     %>
					<div class="col-xs-12 col-sm-5">
						<div class="holder">
							<h5 class="mb-3">VOTRE COMMANDE</h5>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Produits</th>
											<th class="text-right">Sous-total</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
									<tfooter>
									

									<tr>
										<td><strong>Sous-total du panier</strong></td>
										<td class="text-right"></td>
									</tr>
									<tr>
										<td><strong>Frais de livraison</strong></td>
										<td class="text-right"></td>
									</tr>
									<tr>
										<td><strong>TOTAL DE LA COMMANDE</strong></td>
										<td class="text-right"><strong></strong></td>
									</tr>
									</tfooter>
								</table>
							</div>

							<h5 class="mb-3">Payment Methods</h5>
							<div class="form-check-inline">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="exampleRadios"
									id="exampleRadios1" value="option1" checked> Virement bancaire direct
								</label>
							</div>
							<div class="form-check-inline">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="exampleRadios"
									id="exampleRadios2" value="option2"> Carte de crédit
								</label>
							</div>
						</div>
						<p class="text-right mt-3">
							<input checked="" type="checkbox"> J'ai lu et j'accepte les <a href='#'>conditions générales</a>
						</p>
                         						 
						 <button type="submit" class="btn btn-primary float-right">Annuler<i class="fa fa-check"></i></button>
						                         
                         <button type="submit" class="btn btn-primary float-right">Confirmer<i class="fa fa-check"></i></button>
						
						<%
    double montantTotal = panierClient.calculerMontantTotal();  // Assurez-vous d'avoir une méthode pour calculer le montant total dans votre modèle.

    Commande nouvelleCommande = new Commande(clientConnecte, new Date(), montantTotal, statutCommande, magasin);

    CommandeDAO commandeDAO = new CommandeDAO();
    commandeDAO.insertCommande(nouvelleCommande);
    commandeDAO.close();
%>
						
						</a>
						<div class="clearfix"></div>
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
</body>
</html>
