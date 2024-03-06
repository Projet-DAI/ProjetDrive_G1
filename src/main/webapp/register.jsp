<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Drive</title>
<script type="text/JavaScript" src="assets/js/register.js"></script>
</head>
<body>
	<jsp:include flush="true" page="head.jsp"></jsp:include>

	<div id="page-content" class="page-content">
		<div class="banner">
			<div class="jumbotron jumbotron-bg text-center rounded-0"
				style="background-image: url('assets/img/bg-header.jpg');">
				<div class="container">
					<h1 class="pt-5">Inscription</h1>
					<p class="lead">Créer mon compte</p>
					<div class="card card-login mb-5">
						<div class="card-body">
							<form class="form-horizontal" action="InscriptionServlet" method="GET">

								<div class="form-group row mt-3" id="divNomComplet">
									<div class="col-md-12">
										<input class="form-control" type="text" required="" id="nomComplet"
											name="nomComplet" placeholder="Nom complet">
									</div>
									
								</div>
								<div class="form-group row mt-3" id="divEmail">
									<div class="col-md-12">
										<input class="form-control" type="email" required="" id="inputEmail"
											name="adresseEmail" placeholder="Adresse e-mail">
									</div>
								</div>
								<div class="form-group row mt-3">
									<div class="col-md-12">
										<input class="form-control" type="phone" required=""
											name="telephone" placeholder="Téléphone">
									</div>
								</div>
								<div class="form-group row mt-3">
									<div class="col-md-12">
										<input class="form-control" type="text" required=""
											name="nomUtilisateur" placeholder="Nom d'utilisateur">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-12">
										<input class="form-control" type="password" required=""
											name="motDePasse" placeholder="Mot de passe">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-12">
										<input class="form-control" type="password" required=""
											placeholder="Confirmer le mot de passe">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-12">
										<div class="checkbox">
											<input id="checkbox0" type="checkbox" name="terms"> <label
												for="checkbox0" class="mb-0">J'accepte <a
												href="terms.html" class="text-light">les conditions
													d'utilisation</a>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group row text-center mt-4">
									<div class="col-md-12">
										<button type="submit"
											class="btn btn-primary btn-block text-uppercase">Créer
											mon compte</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<jsp:include flush="true" page="footer.jsp"></jsp:include>
</html>