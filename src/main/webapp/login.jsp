<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Model.DAO.ClientDAO" %>
<%@ page import="Model.metier.Client" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Drive</title>
</head>
<body>

	<jsp:include flush="true" page="head.jsp"></jsp:include>

	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Connexion</h1>


					<div class="card card-login mb-5">
						<div class="card-body">
							<form class="form-horizontal" action="/ProjetDrive_G1/Connexion"
								method="post">
								<div class="form-group row mt-3">
									<div class="col-md-12">
										<input class="form-control" type="email" required=""
											name="email" placeholder="ex.chloe.dupont@hotmail.com">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-12">
										<input class="form-control" type="password" required=""
											name="password" placeholder="Mot de passe">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-12 d-flex justify-content-between align-items-center">
										<a href="register.jsp" class="text-light" style="margin-left:5px;margin-top: 7px;">
										<i class="fa fa-bell"></i> Vous n'avez pas encore de compte ?</a>
									</div>
								</div>
								<div class="form-group row text-center mt-4">
									<div class="col-md-12">
										<button type="submit"
											class="btn btn-primary btn-block text-uppercase">Se
											connecter</button>
									</div>
								</div>
							</form>
							<%
						        String msg = (String)request.getAttribute("msgE");

						        if (msg != null) {
									// Affichez un message d'erreur en cas d'�chec de connexion
									out.println("<p style='color:red;'>Identifiant ou mot de passe incorrect</p>");
						        }
						    %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<footer>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<h5>� propos</h5>
				<p></p>
			</div>
			<div class="col-md-3">
				<h5>Liens Utiles</h5>
				<ul>
					<li><a href="about.html">� propos</a></li>
					<li><a href="contact.html">Contactez-nous</a></li>
					<li><a href="faq.html">FAQ</a></li>
					<li><a href="javascript:void(0)">Comment �a fonctionne</a></li>
					<li><a href="terms.html">Termes et Conditions de Retrait</a></li>
					<li><a href="privacy.html">Politique de confidentialit�</a></li>
				</ul>
			</div>
			<div class="col-md-3">
				<h5>Contact</h5>
				<ul>
					<li><a href="tel:+620892738334"><i class="fa fa-phone"></i>
							00337236723</a></li>
					<li><a href="mailto:hello@domain.com"><i
							class="fa fa-envelope"></i> Drive@G1.com</a></li>
				</ul>

				<h5>Suivez-nous</h5>
				<ul class="social">
					<li><a href="javascript:void(0)" target="_blank"><i
							class="fab fa-facebook-f"></i></a></li>
					<li><a href="javascript:void(0)" target="_blank"><i
							class="fab fa-instagram"></i></a></li>
					<li><a href="javascript:void(0)" target="_blank"><i
							class="fab fa-youtube"></i></a></li>
				</ul>
			</div>
			<div class="col-md-3">
				<h5>Obtenez notre application d�s maintenant</h5>
				<ul class="mb-0">
					<li class="download-app"><a href="#"><img
							src="assets/img/playstore.png"></a></li>
				</ul>
			</div>
		</div>
	</div>
</footer>

</html>
